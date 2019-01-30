package Lesson_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {


        Calendar cal = Calendar.getInstance();
        Date date1 = cal.getTime();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Calendar cal2 = Calendar.getInstance();
        Date date2 = cal2.getTime();

        long difference = (date2.getTime() - date1.getTime())/1000;
        System.out.println(difference);
    }


}



// 1 --------------------------
//class Mountain {
//    static String name = "Himalaya";
//    static Mountain getMountain() {
//        System.out.println("Getting Name ");
//        return null;
//    }
//    public static void main(String[ ] args) {
//        System.out.println( getMountain().name );
//    }
//}
// -----------------------------------















// 2 ---------------------
//class Test2 {
//    static void method(int... a) {
//        System.out.println("inside int...");
//    }
//    static void method(long a, long b) {
//        System.out.println("inside long");
//    }
//    static void method(Integer a, Integer b) {
//        System.out.println("inside INTEGER");
//    }
//    public static void main(String[] args) {
//        int a = 2;
//        int b = 3;
//        method(a,b);
//    }
//}
// ------------------------------










// 3 -------------------
//class Test4 {
//    public static void main(String[] args) {
//        String str = null;
//        int x = 10;
//        if(x < 0 && str.equals("hello")) {
//            System.out.println("java");
//        }
//
//    }
//}
//--------------------










//class Test1 {
//    public static void main(String[] args) {
//        int i = 5;
//        System.out.print(i++);
//        System.out.print(++i);
//        System.out.print(i + 1);
//        System.out.print(i);
//
//    }
//}


//




















class Application {
    public static void main(String[] args) {
        infoT(1);
    }

    public static void infoT(int arg) {
        if(arg < 37) {
            infoT(arg + 10);
        }
        System.out.println(arg);
    }
}