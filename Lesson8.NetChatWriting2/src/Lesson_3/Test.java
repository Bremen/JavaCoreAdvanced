package Lesson_3;

public class Test {
}



class TestNew {
    public static void main(String[] args) {
        String strA = "text";
        String strB = "text";
        strA += "1";
        strB += "1";
        System.out.println(strA == strB);
        strA = "text1";
        strB = "text1";
        System.out.println(strA == strB);
    }
}













/////////////////////////////////











interface I {
    public final static int EASY = 5;
}

class Main2 implements I {
    public static void main(String[] args) {
        int a = 5;
        test(++a);
    }

    static void test(int a) {
        a += EASY + a++;
        System.out.println(a);
    }

}













/////////////////////////////












class Test3 {
     {
        System.out.println("1 ");
    }

    Test3() {
        System.out.println("2 ");
    }

    public static void main(String[] args) {
        System.out.println("3 ");
        Test3 test = new Test3();
        System.out.println("4 ");
    }

    static {
        System.out.println("5 ");
    }
}
