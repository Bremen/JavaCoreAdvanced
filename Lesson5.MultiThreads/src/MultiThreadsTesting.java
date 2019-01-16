/**
 * Задача для отработки для понимания многопоточноти в программе на Java
 *
 * в задаче сравнивается время выполнения двух методов последовательного и параллельного рассчета элементов массива
 * с помощью заданного математического выражения.
 * 
 * 1) Создают одномерный длинный массив, например: 
 * static final int size = 10000000;
 * static final int divStep = size / 2;
 * float[] arr = new float[size];
 * 2) Заполняют этот массив единицами;
 * 3) Засекают время выполнения: long a = System.currentTimeMillis();
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis();
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 *
 * Пример деления одного массива на два:
 * System.arraycopy(arr, 0, a1, 0, divStep);
 * System.arraycopy(arr, divStep, a2, 0, divStep);
 *
 * Пример обратной склейки:
 * System.arraycopy(a1, 0, arr, 0, divStep);
 * System.arraycopy(a2, 0, arr, divStep, divStep);
 *
 * Примечание:
 * System.arraycopy() копирует данные из одного массива в другой:
 * System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
 * По замерам времени:
 * Для первого метода надо считать время только на цикл расчета:
 * for (int i = 0; i < size; i++) {
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * }
 * Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 * 
 * В качестве дополнительного задания количество потоков задавать через параметр
 */

public class MultiThreadsTesting {
    static final int SIZE = 10000000; //если 100000000 то Exception in thread "main" java.lang.OutOfMemoryError: Java heap space Объясните пожалуйста где именно и из-за чего место в памяти закончилось?
    static final int threadsCount = 7; //количество потоков
    static final int divStep = SIZE / threadsCount; //шаг разбиения
    static float[] arr = new float[SIZE]; //исходный массив

    public static void main(String[] args) {
        //Заполняем массив единицами
        java.util.Arrays.fill(arr, 1);

        System.out.println("Последовательный метод.");
        long startTime = System.currentTimeMillis();
        sequentialMethod();
        System.out.println("Времени затрачено последовательным методом " + (System.currentTimeMillis() - startTime));

        //Выводим на экран контрольную сумму, чтобы убедиться, что два метода отработали одинаково
        printControlSum();

        //Снова заполняем массив единицами, приводя массив в исходное состояние
        java.util.Arrays.fill(arr, 1);

        System.out.println("Параллельный метод.");
        startTime = System.currentTimeMillis();
        parallelMethod();
        System.out.println("Времени затрачено параллельным методом " + (System.currentTimeMillis() - startTime));

        //Выводим на экран контрольную сумму, чтобы убедиться, что два метода отработали одинаково
        printControlSum();
    }

    private static void printControlSum() {
        float sum = 0;
        for (float a : arr) {
            sum += a;
        }
        System.out.println("Сумма элементов массива после преобразования: " + sum);
    }

    private static void parallelMethod() {
        float a[][] = new float[threadsCount][divStep];

        //Разрезаем массив на части
        int sizeRemain = SIZE % threadsCount;
        for (int i = 0; i < threadsCount; i++) {
            if (sizeRemain != 0 && i == threadsCount - 1) //Условие учитывает нечетность элементов массива и количество потоков
            {
                a[threadsCount - 1] = new float[SIZE - i*divStep];
                System.arraycopy(arr, i*divStep, a[i], 0, SIZE - i*divStep);
            }else{
                System.arraycopy(arr, i*divStep, a[i], 0, divStep);
            }
        }

        //Создаем потоки для каждого отрезка
        Thread[] threads = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            float bufArray[] = a[i]; // Эту переменную пришлось ввести из-за вопроса ниже
            int index = i*divStep; // Эту переменную пришлось ввести из-за вопроса ниже
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    //transformArray(a[i], i*divStep); ВОПРОС: почему такая запись не работает??? Проблема: подчеркивается красным переменная i
                    transformArray(bufArray, index);
                }
            });
        }

        //Запускаем все потоки
        for (int i = 0; i < threadsCount; i++) {
            threads[i].start();
        }

        //Ждем выполнения всех потоков
        try {
            for (int i = 0; i < threadsCount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Склеиваем кусочки обратно в массив исходного размера
        for (int i = 0; i < threadsCount; i++) {
            if (sizeRemain != 0 && i == threadsCount - 1)
            {
                System.arraycopy(a[i], 0, arr, i*divStep, SIZE - i*divStep);
            }else{
                System.arraycopy(a[i], 0, arr, i*divStep, divStep);
            }
        }
    }

    private static void sequentialMethod() {
        transformArray(arr, 0);
    }

    /**
     * функция преобразования отрезка массива
     * @param arr - отрезок массива для преобразования
     * @param absoluteIndex - индекс первого элемента отрезка в изначальном целом массиве, нужен для мат.выражения
     */
    private static void transformArray(float []arr, int absoluteIndex) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = calculateExpression(arr[i], i + absoluteIndex);
        }
    }

    /**
     * Математическое выражение для преобразования элементов исходного массива
     * @param a - элемент исходного массива
     * @param i - индекс элемента в исходном целом массиве
     * @return
     */
    private static float calculateExpression(float a, int i) {
        return (float)(a * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}
