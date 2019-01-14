public class MultiThreadsTesting {
    static final int SIZE = 100000000;
    static final int h = SIZE / 2;
    static float[] arr = new float[SIZE];

    public static void main(String[] args) {

        java.util.Arrays.fill(arr, 1);

        long startTime = System.currentTimeMillis();
        sequentialMethod();
        System.out.println("Времени затрачено последовательным методом " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        parallelMethod();
        System.out.println("Времени затрачено параллельным методом " + (System.currentTimeMillis() - startTime));

    }

    private static void parallelMethod() {
        float a1[] = new float[h];
        float a2[] = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                transformArray(a1, 0);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                transformArray(a2, h);
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

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
    }

    private static void sequentialMethod() {
        transformArray(arr, 0);
    }

    private static void transformArray(float []arr, int absoluteIndex) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = calculateExpression(arr[i], i + absoluteIndex);
        }
    }

    private static float calculateExpression(float a, int i) {
        return (float)(a * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}
