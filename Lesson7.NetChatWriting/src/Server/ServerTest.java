package Server;

import Client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerTest {

    private Vector<ClientHandler> clients;

    public ServerTest(){
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this,socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public boolean subscribe(ClientHandler client) {
        boolean newClientSubscribed = false;
        ClientHandler listedClient = getClientByNickName(client.getNick()); //Пытаемся найти клиента с таким же ником
        if (listedClient == null){ //если такого нет, то подписываем нового клиента
            clients.add(client);
            newClientSubscribed = true;
        }

        return newClientSubscribed;
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        String[] msgHead = msg.split(" ", 4);
        if (msgHead[1].contains("personally")){
            String sender = msgHead[0];
            String recipient = msgHead[2];

            ClientHandler recipientClient = getClientByNickName(recipient);
            ClientHandler senderClient = getClientByNickName(sender);

            if (recipientClient != null){
                recipientClient.sendMsg(msg);
            }else{
                msg += String.format("\nПользователя с ником %s нет в чате.", recipient);
            }
            if (senderClient != null){
                senderClient.sendMsg(msg);
            }
        }else{
            for (ClientHandler o: clients) {
                o.sendMsg(msg);
            }
        }
    }

    private ClientHandler getClientByNickName(String nickName) {
        ClientHandler client = null;
        for (ClientHandler c: clients) {
            if(c.getNick().contains(nickName)){
                client = c;
                break;
            }
        }

        return client;
    }
}
