import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTalker {
    private static Scanner consoleScanner = new Scanner(System.in); // нам нужен ридер читающий с консоли
    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;
        try {
            serv = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            Scanner sc = new Scanner(sock.getInputStream());
            PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);

            Thread takingClientMessageThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String clientWord = sc.nextLine(); // ждём, что скажет сервер
                        if (clientWord.equals("end")){
                            break;
                        }
                        System.out.println(clientWord); // получив - выводим на экран
                    }
                }
            });

            Thread sendingServerMessageThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String word = consoleScanner.nextLine(); // ждём пока сервер что-нибудь
                        // не напишет в консоль
                        pw.write(word + "\n"); // отправляем сообщение на сервер
                        pw.flush();
                    }
                }
            });

            takingClientMessageThread.start();

            sendingServerMessageThread.setDaemon(true);
            sendingServerMessageThread.start();

            try {
                takingClientMessageThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
