/**
 * 1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
 * как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать «Привет», нажать Enter,
 * то сообщение должно передаться на сервер и там отпечататься в консоли. Если сделать то же самое на серверной стороне,
 * то сообщение передается клиенту и печатается у него в консоли. Есть одна особенность, которую нужно учитывать:
 * клиент или сервер может написать несколько сообщений подряд. Такую ситуацию необходимо корректно обработать.
 */

package mainTask;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private static Socket clientSocket; //сокет для общения
    private static Scanner consoleScanner = new Scanner(System.in); //ридер читающий с консоли

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 8189, такой же как у сервера
                clientSocket = new Socket("localhost", 8189); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение

                Scanner sc = new Scanner(clientSocket.getInputStream());
                PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

                System.out.println("Начинайте общение с сервером:");
                // если соединение произошло и потоки успешно созданы - мы можем
                // работать дальше и предложить клиенту что то ввести
                // если нет - вылетит исключение

                Thread takingServerMessageThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            String serverWord = "инициилизирующее сообщение, от сервера сообщений не было..";
                            try {
                                serverWord = sc.nextLine(); // ждём, что скажет сервер
                            }catch (NoSuchElementException e)
                            {
                                serverWord = "выходим из цикла приема сообщений от сервера.";
                                break;
                            }finally {
                                System.out.println(serverWord); // получив - выводим на экран
                            }
                        }
                    }
                });

                Thread sendingClientMessageThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            String word = consoleScanner.nextLine(); // ждём пока клиент что-нибудь
                            // не напишет в консоль
                            pw.write(word + "\n"); // отправляем сообщение на сервер
                            pw.flush();
                            if(word.equals("end")){
                                break;
                            }
                        }
                    }
                });

                takingServerMessageThread.setDaemon(true);
                takingServerMessageThread.start();

                sendingClientMessageThread.start();

                try {
                    sendingClientMessageThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

