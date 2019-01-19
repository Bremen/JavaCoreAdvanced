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
            while (true) {
                String clientWord = sc.nextLine();
                System.out.println(clientWord); // получив - выводим на экран
                if (clientWord.equals("end")) break;

                pw.println(consoleScanner.nextLine());
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
