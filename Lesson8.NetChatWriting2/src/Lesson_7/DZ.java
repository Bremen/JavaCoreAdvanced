package Lesson_7;

public class DZ {
}



// реализация отдельный класс и подписку на интерфейс
//
//public class Client {
//    public static void main(String[] args) {
//        Client cl1 = new Client();
//        cl1.start();
//    }
//    Socket sockA = null;
//    DataInputStream DIS = null;
//    DataOutputStream DOS = null;
//    void start(){
//        try {
//            sockA = new Socket("127.0.0.1",9000);
//            while (!sockA.isClosed()) {
//                System.out.println("Я подключился");
//                DIS = new DataInputStream(sockA.getInputStream());
//                DOS = new DataOutputStream(sockA.getOutputStream());
//                Thread inR = new Thread(new Reader(DIS));
//                Thread outW = new Thread(new Writer(DOS));
//                inR.start();
//                outW.start();
//                inR.join();
//                outW.join();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//
//
//
//
//
//
//public class Reader implements Runnable  {
//    DataInputStream DIS ;
//    public Reader(DataInputStream DIS) {
//        this.DIS = DIS;
//    }
//    private void incomeReader() throws IOException {
//        while (true){
//            String text = DIS.readUTF();
//            System.out.println(text);}
//    }
//    @Override
//    public void run()  {
//        try {
//            incomeReader();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//
//
//
//
//
//public class Server  {
//    ServerSocket SS = null;
//    Socket sockA = null;
//    DataInputStream DIS = null;
//    DataOutputStream DOS = null;
//    void start() {
//        try {
//            SS = new ServerSocket(9000);
//            while (!SS.isClosed()) {
//                sockA = SS.accept();
//                System.out.println("соединение получено");
//                DIS = new DataInputStream(sockA.getInputStream());
//                DOS = new DataOutputStream(sockA.getOutputStream());
//                Thread inR = new Thread(new Reader(DIS));
//                Thread outW = new Thread(new Writer(DOS));
//                inR.start();
//                outW.start();
//                inR.join();
//                outW.join();
//                return;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) {
//        Server ser1 = new Server();
//        ser1.start();
//    }
//}
//
//
//
//
//
//
//
//
///////////////////////
//
//
// чтение с консоли и входящий поток в одном цикле
//
//public class Client {
//    public static void main(String[] ar) {
//        final String SERVER_ADDR = "localhost";
//        final int SERVER_PORT = 8189;
//
//        try {
//            Socket socket = new Socket(SERVER_ADDR, SERVER_PORT); // создаем сокет используя адрес и порт сервера.
//
//            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
//            DataInputStream in = new DataInputStream(socket.getInputStream());
//            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//
//            // Создаем поток для чтения с клавиатуры.
//            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//            String msgIn;
//            String msgOut;
//            System.out.println("Вы подключились к серверу. Введите сообщение: ");
//
//            while (true) {
//                msgOut = bf.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
//                out.writeUTF(msgOut); // отсылаем введенную строку текста серверу.
//                out.flush(); // заставляем поток закончить передачу данных.
//                msgIn = in.readUTF(); // ждем пока сервер отошлет строку текста.
//                System.out.println("Сервер отправил сообщение: " + msgIn);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//public class Server {
//
//    public static void main(String[] ar)    {
//        try {
//            ServerSocket ss = new ServerSocket(8189); // создаем сокет сервера и привязываем его к вышеуказанному порту
//            System.out.println("Сервер запущен. Ожидание клиента...");
//
//            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
//            System.out.println("Клиент подключился.");
//            System.out.println();
//
//            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
//            DataInputStream in = new DataInputStream(socket.getInputStream());
//            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//            // Создаем поток для чтения с клавиатуры.
//            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//            String msgIn;
//            String msgOut;
//
//            while(true) {
//                msgIn = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
//                System.out.println("Клиент отправил сообщение: " + msgIn); //печатаем в консоль сообщение клиента
//                msgOut = bf.readLine(); //пишем сообщение в ответ
//                out.writeUTF(msgOut); // отсылаем клиенту сообщение.
//                out.flush(); // заставляем поток закончить передачу данных.
//                System.out.println();
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//
///////////
//
//
//
//
//public class Server {
//    public static void main(String[] args) {
//        ServerSocket server = null;
//        Socket socket = null;
//        DataOutputStream out;
//        BufferedReader bufferedReader;
//
//        try {
//            server = new ServerSocket(8189);
//            System.out.println("Server started");
//
//            socket = server.accept();
//            System.out.println("Client connected");
//
//            Receiver receiver = new Receiver(socket);
//            receiver.start();
//
//            out = new DataOutputStream(socket.getOutputStream());
//            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//            while (true){
//                String msg = bufferedReader.readLine();
//                out.writeUTF(msg);
//            }
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//
//            try {
//
//                socket.close();
//                server.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
//
//public class Client {
//    public static void main(String[] args) {
//        final String IP_ADDRESS = "localhost";
//        int PORT = 8189;
//        Socket socket = null;
//        DataOutputStream out;
//        BufferedReader bufferedReader;
//
//
//        try {
//            socket = new Socket(IP_ADDRESS, PORT);
//            out = new DataOutputStream(socket.getOutputStream());
//            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//            Receiver receiver = new Receiver(socket);
//            receiver.start();
//
//            while (true){
//                String msg = bufferedReader.readLine();
//                out.writeUTF(msg);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//}
//
//
//
//public class Receiver extends Thread{
//    Socket socket;
//    DataInputStream in;
//
//    Receiver(Socket socket){
//        this.socket = socket;
//    }
//
//    public void run(){
//        try {
//            while (true) {
//                in = new DataInputStream(socket.getInputStream());
//                String str = in.readUTF();
//                System.out.println(str);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
/////////////////////
//
//
//
//
//
//
//
//    public void GoServer () {
//        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//        String line = null;
//        System.out.println("Type in something and press enter");
//        System.out.println();
//
//        try {
//            ServerSocket ss = new ServerSocket(Port);
//            Socket socket = ss.accept();
//            InputStream sin = socket.getInputStream();
//            OutputStream sout = socket.getOutputStream();
//            DataInputStream in = new DataInputStream(sin);
//            DataOutputStream out = new DataOutputStream(sout);
//
//            String linein = null;
//            String lineout = null;
//            System.out.println("Type in something and press enter");
//            System.out.println();
//
//            while(true) {
//                linein = keyboard.readLine();
//                lineout = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
//                out.writeUTF(linein); // отсылаем клиенту обратно ту самую строку текста.
//                out.flush(); // заставляем поток закончить передачу данных.
//                System.out.println("The message from client : " + lineout);
//            }
//
//
//
//
//////////////////////////////////
//// несколько блоков try
//
//            try {
////                try {
////                    server = new ServerSocket(8080);
////                    System.out.println("сервер запущен!");
////                    serverSocket = server.accept();
////                    try {
////
////////////////////////////
//
//
//
//
//
//                        public class Writer implements Runnable  {
//                            DataOutputStream DOS;
//                            public Writer(DataOutputStream DOS) {
//                                this.DOS = DOS;
//                            }
//                            private void outWriter() throws IOException {
//                                while (true){
//                                    Scanner in = new Scanner(System.in);
//                                    String text = in.next();
//                                    DOS.writeUTF(text);
//                                }
//                            }
//                            @Override
//                            public void run()  {
//                                try {
//                                    outWriter();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }