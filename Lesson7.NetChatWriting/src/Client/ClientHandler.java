package Client;

import Server.AuthService;
import Server.ServerTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHandler {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static Matcher matcher;
    private static final String PRIVATE_MESSAGE_PATTERN = "(?<=/w )[a-zA-Z][a-zA-Z0-9-_.]{3,}";
    private static Pattern pattern = Pattern.compile(PRIVATE_MESSAGE_PATTERN);

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ServerTest server;

    public String getNick() {
        return nick;
    }

    private String nick;

    public ClientHandler(ServerTest server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();

                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickLoginAndPass(tokens[1], tokens[2]);
                                if (newNick != null) {
                                    nick = newNick;
                                    if (server.subscribe(ClientHandler.this)){
                                        sendMsg("/authok");
                                        break;
                                    }else{
                                        sendMsg("Ошибка авторизации: пользователь с таким ником уже авторизован в чате.");
                                    }
                                } else {
                                    sendMsg("Ошибка авторизации: Неверный логин/пароль!");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if(str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }

                            server.broadcastMsg(wrapMsgWithNickAndDate(str));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }

                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String wrapMsgWithNickAndDate(String msg) {
        Date date = Calendar.getInstance().getTime();
        String strDate = dateFormat.format(date);

        String personally  = "";
        matcher = pattern.matcher(msg);
        if (matcher.find())
        {
            msg = msg.replaceFirst("/w " + matcher.group() + " ", "");
            personally  = " personally " + matcher.group();
        }

        return nick + personally + " (" + strDate + "): \n" + msg;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
