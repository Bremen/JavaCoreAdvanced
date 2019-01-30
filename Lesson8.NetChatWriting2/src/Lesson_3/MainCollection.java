package Lesson_3;

import java.util.*;

public class MainCollection {
    public static void main(String[] args) {
//        int[] mass = {1,2,3,4};
//        System.out.println(Arrays.toString(mass));
//
//        int[] arr2 = new int[20];
//
//        System.arraycopy(mass, 0, arr2, 0, mass.length);
//        System.out.println(Arrays.toString(arr2));

//        ArrayList<Integer> ai = new ArrayList<>();
//
//        System.out.println(ai.size());
//        ai.add(1);
//        ai.add(2);
//        ai.add(3);
//        ai.add(4);
//        ai.remove((Integer)3);
//
//        //System.out.println(ai.size());
//        System.out.println(ai.get(2));
//
//        if(ai.contains(20)) {
//            System.out.println("У нас есть число 2");
//        }


//        ArrayList<String> states = new ArrayList<>();
//
//        states.add("Германия");
//        states.add("Германия");
//        states.add("Россия");
//        states.add("Франция");
//        states.add("Италия");
//        states.add("США");
//
////        for (int i = 0; i < states.size(); i++) {
////            if(states.get(i).equalsIgnoreCase("Германия")) {
////                states.remove(i);
////            }
////        }
//
//        states.remove("Германия");
//        states.remove("Германия");
//
////        Iterator<String> iter = states.iterator();
////        while (iter.hasNext()) {
////            if(iter.next().equalsIgnoreCase("Германия")) {
////                iter.remove();
////            }
////        }
//
//
//        System.out.println(states);
//        LinkedList<String> ll = new LinkedList<>();
//
//
//        ll.add("A");
//        ll.add("B");
//        ll.add("C");
//        ll.add("D");
//        ll.add("E");
//        ll.addFirst("Z");
//
//
//        ll.add(2,"Y");
//
//
//        System.out.println(ll);


//        Map<String, Integer> hm = new HashMap<>();
//
//        hm.put("Васька", 10);
//        hm.put("Мурзик", 5);
//        hm.put("Васька", 6);
//        hm.put("Барсик", 6);

       // System.out.println(hm);

//        Set<Map.Entry<String, Integer>> set = hm.entrySet();
//
//        for(Map.Entry<String, Integer> me: set) {
//            System.out.print(me.getKey() + ": ");
//            System.out.println(me.getValue() + ": ");
//        }
//
//        System.out.println(hm.get("Васька"));

//        Random random = new Random();
//
//        Map<Integer, Integer> hm = new HashMap<>();
//
//        for (int i = 0; i < 100; i++) {
//            int number = random.nextInt(10);
//            Integer current = hm.get(number);
//            hm.put(number, current == null ? 1 : current + 1);
//        }
//
//        System.out.println(hm);

//
//        HashMap<Integer, String> hm = new HashMap<>();
//        hm.put(777,"test1");
//        hm.put(778,"test2");
//
//        System.out.println(hm.getOrDefault(77, "Нет такого значения!"));

//        HashSet<String> hs = new HashSet<>();
//
//        hs.add("D");
//        hs.add("Z");
//        hs.add("A");
//        hs.add("Z");
//        hs.add("T");
//
//
//        System.out.println(hs);ne==

        TreeSet<Empl> tr = new TreeSet<>(new MySalaryComp());

        tr.add(new Empl("name1", 500));
        tr.add(new Empl("name2", 600));
        tr.add(new Empl("name3", 700));

       // System.out.println(tr);

        for (Empl e: tr ) {
            e.info();
        }

     //   ArrayList<Empl> empls = new ArrayList<Empl>();


    }
}

class MySalaryComp implements Comparator<Empl> {

    @Override
    public int compare(Empl o1, Empl o2) {
        if(o1.getSalary() < o2.getSalary()){
            return 1;
        } else {
            return -1;
        }
    }
}


class Empl {
    private String name;
    private int salary;

    public Empl(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void info() {
        System.out.println(name + " " + salary);
    }
}












class Box {
    String color;
    int size;

    public Box(String color, int size) {
        this.color = color;
        this.size = size;
    }

    public static void main(String[] args) {
        // создаем массив
        int[][] mass = {{1,2,3}, {4,5,6}};
        // передаем массив
        setMass(mass);

        int[][] mass1 = new int[2][];
        mass1[0] = new int[3];
        mass1[1] = new int[4];
        fillMass(mass1);
    }
    // метод в параметре принимает массив
    public static void setMass(int[][] mass) {
        System.out.println(mass);
    }

    // метод для заполнения массива
    public static void fillMass(int[][] mass) {
        // заполняем массив
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[i].length ; j++) {
                mass[i][j] = 1;
            }
        }

        // выводим на печать
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[i].length; j++) {
                System.out.print(mass[i][j] + " ");
            }
            System.out.println();
        }
    }
}


class MainBox {
    public static void main(String[] args) {
        Box b1 = new Box("White", 5);
        Box b2 = new Box("White", 5);

//        System.out.println(b1.hashCode());
//        System.out.println(b2.hashCode());

        System.out.println(b1.equals(b2));
    }
}
















