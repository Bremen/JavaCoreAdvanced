package Lesson_5;

import java.io.InputStream;

public class TE {
    public static void main(String[] args)  {
        //System.out.println(calc(2,1,3,5,6));
        int arr1[] = { 0, 1, 2, 3, 4, 5 };
        int arr2[] = { 5, 10, 20, 30, 40, 50 };

        // copies an array from the specified source array
        System.arraycopy(arr1, 0, arr2, 0, 2);
        System.out.print("array2 = ");
        System.out.print(arr2[0] + " ");
        System.out.print(arr2[1] + " ");
        System.out.print(arr2[2] + " ");
        System.out.print(arr2[3] + " ");
        System.out.print(arr2[4] + " ");
        System.out.print(arr2[5] + " ");

    }

    static void task2(){
        byte a = -128;
        short b = 1678;
        char c = 'A';
        int d = 22;
        long L = 11234L;
        float fl = 12.25132f;
        double dl = 53.2592;
        boolean bool = true;
    }
    static double calc(double a, double b, double c, double d,double result){
        return a * (b + (c / d));

    }
}
