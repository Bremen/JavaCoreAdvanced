package Lesson_1;

public class Cat {
    private String name;
    protected String color;
    private int age;

    public void setAge(int age) {
        if(age > 0) {
            this.age = age;
        } else {
            System.out.println("Возраст не может быть отрицательным!");
        }
    }

    public Cat() {

    }

    public Cat(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    void info() {
        System.out.println(name + " " + color + " " + age);
    }
}

class MainCat{
    public static void main(String[] args) {
        Cat cat = new Cat();

//        Cat cat1 = new Cat("Cat1", "Red", 10);
//        Cat cat2 = new Cat("Cat2");
//
//        cat1.info();
//        cat2.info();
//        sum(5,4, 6);
//        sum(5);
//        sum(5,4, 6,7,8,9,5,20,555,65);
//        sum(5,4, -6,1,5);
//        sum(5,4);
     //   abc();

        Cat[] cats = {new Cat(), new Cat()};
//        cats[0].name ="a";
//        cats[1].age =10;

        cats[0].info();
        cats[1].info();
    }


     static void abc() {
        System.out.println("abc");
    }

//    static void sum(int... mass) {
//        int res = 0;
//        for (int i = 0; i < mass.length; i++) {
//            res += mass[i];
//        }
//        System.out.println(res);
//    }
}




class MainTest {
    public static void main(String[] args) {
        Box box = new Box("box1", 10);
        Tools tools = new Tools("hummer");
        box.startTest(tools);
    }
}


class Box {
    String name;
    int weight;

    public Box(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    Box box = new Box("sad", 50);

    void startTest(Tools tools) {
        tools.info(box);
    }
}


class Tools {
    String tools;
    public Tools(String tools) {
        this.tools = tools;
    }

    void info(Box box) {
        System.out.println(tools + " " + box.name + " " + box.weight);
    }

}


interface A {
    void infoA();
}

interface B {
    void infoB();
}



abstract class Animals {
    abstract void run();

    void info() {
        System.out.println("info");
    }

}

class Dog extends Animals implements A, B{

    String dog_unic;

    public Dog() {


    }

    @Override
    void run() {

    }

    @Override
    public void infoA() {

    }

    @Override
    public void infoB() {

    }
}

//class SuperDog extends Dog {
//
//    String super_dog_unic;
//
//    public SuperDog() {
//
//    }
//
//    @Override
//    void voice() {
//        System.out.println("SuperDog voice");
//    }
//
//    void test() {
//        super.y = 20;
//    }
//}
//
//
//
//class MainAnimal {
//    public static void main(String[] args) {
//        Animals[] animals = {new Animals(), new Dog(), new SuperDog()};
//
//        for (Animals a: animals) {
//            a.voice();
//        }
//    }
//}


















