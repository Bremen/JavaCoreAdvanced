package Lesson_5;

public class MainThreadDeamon {

    public static void main(String[] args) {

        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                int seconds = 0;

                while (true) {
                    seconds++;
                    System.out.println("time " + seconds);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        timer.start();



        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Client Bye!");

    }

}
