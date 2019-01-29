package Lesson_5;

import java.util.Arrays;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.System.currentTimeMillis;


public class DZ {
    public static void main(String[] args) {
        final int SIZE = 100000000;
        final int THREADS_COUNT = 4;
        // определяем размерность двумерного массива
        final int PART_SIZE = SIZE / THREADS_COUNT;
        float[] mas = new float[SIZE];
        Arrays.fill(mas, 1f);
        long a = currentTimeMillis();
        // разделяем данные
        final float[][] m = new float[THREADS_COUNT][PART_SIZE];
        // создадим массив потоков
        Thread[] t = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            // будем копировать в двумерный массив данные из основного потока со сдвигом
            System.arraycopy(mas, PART_SIZE * i, m[i], 0, PART_SIZE);
            final int u = i;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // считаем массив со сдвигом
                    int n = u * PART_SIZE;
                    for (int j = 0; j < PART_SIZE; j++, n++) {
                        m[u][j] = (float) (m[u][j] * sin(0.2f + n / 5) * cos(0.2f + n / 5) * cos(0.4f + n / 2));
                    }
                }
            });
            t[i].start();
        }
        try {
            for (int i = 0; i < THREADS_COUNT; i++) {
                t[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // складываем массив обратно в исходный массив
        for (int i = 0; i < THREADS_COUNT; i++) {
            System.arraycopy(m[i], 0, mas, i * PART_SIZE, PART_SIZE);
        }
        // определяем время
        System.out.println(currentTimeMillis() - a);
    }


}






























//
//class MyThread extends Thread{
//    float[]array;
//    int i;//номер потока
//    long time;
//    public MyThread(float[] array,int i) {
//        this.array = array;
//        this.i=i;
//    }
//    @Override
//    public void run() {
//        long timeStart=System.currentTimeMillis();
//        for (int i=0;i<array.length;i++){
//            array[i]=(float)(array[i] * sin(0.2f + i / 5) * cos(0.2f + i / 5) * cos(0.4f + i / 2));
//        }
//        long timeEnd=System.currentTimeMillis();
//        time=timeEnd-timeStart;
//    }
//    public void info(){
//        System.out.println("- время просчета массива в потоке "+(i+1)+": "+time);
//    }
//}
//
/////////////////
//
//
//    public void arrCalc() {
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//    }
//
//
///////////////////
//
//
//    public static float[] calculatingValuesInArray(float[] arr) {
//        for (int i = 0; i < arr.length ; i++) {
//            arr[i] = (float)((arr[i] + 14) * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//        return arr;
//    }
//
//
////////////////////
//
//
//     startTime = System.currentTimeMillis();
//             for (int i = 0; i < arr.length; i++) {
//        arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        // System.out.println(arr[i]);
//        }
//
/////////////////
//
//
//
//
//
//
//        Thread t1 = new Thread(new Runnable() {
//@Override
//public void run() {
//        for (int j = 0; j < a1.length; j++) {
//        a1[j] = (float) (a1[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
//        }
//        System.out.println(1);
//        }
//        });
//        Thread t2 = new Thread(new Runnable() {
//@Override
//public void run() {
//        for (int j = 0; j < a2.length; j++) {
//        a2[j] = (float) (a2[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
//        }
//        System.out.println(2);
//        }
//        });
//        t1.start();
//        t2.start();
//
//
//        ////////////////
//
//
//
//        long start = System.currentTimeMillis();
//        float[] arr1 = new float[h];
//        float[] arr2 = new float[h];
//        System.arraycopy(arr, 0, arr1, 0, h);
//        System.arraycopy(arr, h, arr2, 0, h);
//        Thread t1 = new Thread(() -> {
//        for (int i = 0; i < arr1.length; i++) {
//        arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//        });
//        Thread t2 = new Thread(() -> {
//        for (int i = 0; i < arr2.length; i++) {
//        arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//        });
//
//
//
//
////////////////////
//
//
//
//        void doWithThreads() {
//        Arrays.fill(arr, 1);
//        long a = System.currentTimeMillis();
//        System.arraycopy(arr, 0, a1, 0, h);
//        System.arraycopy(arr, h, a2, 0, h);
//
//        Multithread runner = new Multithread();
//        Thread first = new Thread(runner);
//        Thread second  = new Thread(runner);
//        first.setName("first");
//        second.setName("second");
//        first.start();
//        second.start();
//
//        System.arraycopy(a1, 0, arr, 0, h);
//        System.arraycopy(a2, 0, arr, h, h);
//        System.out.println("Длительность работы второго метода " + (System.currentTimeMillis() - a) + " мс");
//        }
//
//         public void run() {
//        for (int i = 0; i < h; i++) {
//        if (Thread.currentThread().getName() == "first") {
//        a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        } else {
//        a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//        }
//
//
//
///////////////////////
//
//
//
//        Thread t1 = new Thread(new Runnable() {
//@Override
//public void run() {
//        for (int i = 0; i < a1.length; i++) {
//        a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//        System.out.println("Время выполнения расчета для первого массива " + (System.currentTimeMillis() - a)+" мс.");
//        }
//        });
//        Thread t2 = new Thread(new Runnable() {
//@Override
//public void run() {
//        for (int i = 0; i < a2.length; i++) {
//        a2[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//        System.out.println("Время выполнения расчета для второго массива " + (System.currentTimeMillis() - a)+" мс.");
//        }
//        });
//
//
////////////////
//
//
//        Thread threadA1 = new Thread(()-> {for (int i = 0; i <h ; i++) {
//        a1[i]=(float)(arr[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));}
//        });
//        threadA1.start();
//        Thread threadA2 = new Thread(()-> {for (int i = 0; i <h ; i++) {
//        a2[i]=(float)(arr[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));}
//        });
//
//////////////
//
//
//        package thread_lesson_5;
//
//public class FirstClass {
//    private static final int SIZE = 10000000;
//    private static final int HALF_SIZE = SIZE / 2;
//
//    public float[] calculate(float[] arr) {
//        for (int i = 0; i < arr.length; i++)
//            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));
//        return arr;
//    }
//
//    public void runOneThread() {
//        float[] arr = new float[SIZE];
//        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
//        long a = System.currentTimeMillis();
//        calculate(arr);
//        System.out.println("Один поток завершен за: " + (System.currentTimeMillis() - a) + " мс.");
//    }
//
//    public void runTwoThreads() {
//        float[] arr = new float[SIZE];
//        float[] arr1 = new float[HALF_SIZE];
//        float[] arr2 = new float[HALF_SIZE];
//        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
//
//        long a = System.currentTimeMillis();
//        System.arraycopy(arr, 0, arr1, 0, HALF_SIZE);
//        System.arraycopy(arr, HALF_SIZE, arr2, 0, HALF_SIZE);
//
//        new Thread() {
//            public void run() {
//                float[] a1 = calculate(arr1);
//                System.arraycopy(a1, 0, arr1, 0, a1.length);
//            }
//        }.start();
//
//        new Thread() {
//            public void run() {
//                float[] a2 = calculate(arr2);
//                System.arraycopy(a2, 0, arr2, 0, a2.length);
//            }
//        }.start();
//
//        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
//        System.arraycopy(arr2, 0, arr, HALF_SIZE, HALF_SIZE);
//        System.out.println("Два потока завершены за: " + (System.currentTimeMillis() - a) + " мс.");
//    }
//
//    public static void main(String s[]) {
//        FirstClass o = new FirstClass();
//        o.runOneThread();
//        o.runTwoThreads();
//    }
//}
//
//
//
//////////////////////

