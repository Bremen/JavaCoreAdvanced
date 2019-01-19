import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket clientSocket; //сокет для общения
    private static Scanner consoleScanner = new Scanner(System.in); // нам нужен ридер читающий с консоли

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 8189, такой же как у сервера
                clientSocket = new Socket("localhost", 8189); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение

                Scanner sc = new Scanner(clientSocket.getInputStream());
                PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                // если соединение произошло и потоки успешно созданы - мы можем
                //  работать дальше и предложить клиенту что то ввести
                // если нет - вылетит исключение

                Thread takingServerMessageThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            String serverWord = sc.nextLine(); // ждём, что скажет сервер
                            System.out.println(serverWord); // получив - выводим на экран
                        }
                    }
                });

                takingServerMessageThread.setDaemon(true);
                takingServerMessageThread.start();

                while (true){
                    String word = consoleScanner.nextLine(); // ждём пока клиент что-нибудь
                    // не напишет в консоль
                    pw.write(word + "\n"); // отправляем сообщение на сервер
                    pw.flush();
                    if(word.equals("end")){
                        break;
                    }
                }
            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}

