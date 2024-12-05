/**
 * 1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
 * как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать «Привет», нажать Enter,
 * то сообщение должно передаться на сервер и там отпечататься в консоли. Если сделать то же самое на серверной стороне,
 * то сообщение передается клиенту и печатается у него в консоли. Есть одна особенность, которую нужно учитывать:
 * клиент или сервер может написать несколько сообщений подряд. Такую ситуацию необходимо корректно обработать.
 */
package mainTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerTalker {
    private static Scanner consoleScanner = new Scanner(System.in); //ридер читающий с консоли
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            Thread takingClientMessageThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String clientWord = "сообщения от клиента ещё не приходили";

                        //Обрабатываем случай если клиент отключился без сообщения "end"
                        try{
                            clientWord = sc.nextLine(); // ждём, что скажет сервер
                        }catch (NoSuchElementException e){
                            clientWord = "выходим из цикла приема сообщений от клиента";
                            break;
                        }finally {
                            System.out.println(clientWord); // получив - выводим на экран
                        }

                        if (clientWord.equals("end")){
                            break;
                        }
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
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
