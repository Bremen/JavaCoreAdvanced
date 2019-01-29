package Lesson_5;


import static java.lang.System.*;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;


    public  float[] fullArr(float[] arr) {
        for (int i = 0; i < h; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        return arr;
    }

    public void firstMethod() {
        long a = System.currentTimeMillis();
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {arr[i] = 1.0f;}
        System.out.println("1.Время заполнения массива единицами: ");
        System.out.println(System.currentTimeMillis()-a);
        fullArr(arr);
        System.out.println("1.Время заполнения массива по формуле: ");
        System.out.println(System.currentTimeMillis()-a);
    }

    public  void secondMethod() {
        long a = currentTimeMillis();
        float[] arr = new float[size];
//        float[] arr1 = new float[h];
//        float[] arr2 = new float[h];
        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }
        System.out.println("2.Время заполнения массива единицами: ");
        System.out.println(System.currentTimeMillis()-a);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] arr1 = new float[h];
                long a = currentTimeMillis();
                System.arraycopy(arr, 0, arr1, 0, h);
                System.out.println("Время разбивки для массива №1: ");
                System.out.println(System.currentTimeMillis() - a);
                long b = currentTimeMillis();
                float[] a1 = fullArr(arr1);
                System.out.println("Время расчета для массива №1: ");
                System.out.println(System.currentTimeMillis() - b);
                long c = currentTimeMillis();
                System.arraycopy(a1, 0, arr, 0, h);
                System.out.println("Время вклеивания 1й части: ");
                System.out.println(System.currentTimeMillis() - c);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] arr2 = new float[h];
                long a = currentTimeMillis();
                System.arraycopy(arr, h, arr2, 0, h);
                System.out.println("Время разбивки для массива №2: ");
                System.out.println(System.currentTimeMillis() - a);
                long b = currentTimeMillis();
                float[] a2 = fullArr(arr2);
                System.out.println("Время расчета для массива №2: ");
                System.out.println(System.currentTimeMillis() - b);
                long c = currentTimeMillis();
                System.arraycopy(a2, 0, arr, h, h);
                System.out.println("Время вклеивания 2й части: ");
                System.out.println(System.currentTimeMillis() - c);

            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Время склеивания массивов №1 и №2: ");
        System.out.println(System.currentTimeMillis() - a);

    }

    public static void main(String[] args) {
        Main o= new Main();
        o.firstMethod();
        o.secondMethod();

    }
}