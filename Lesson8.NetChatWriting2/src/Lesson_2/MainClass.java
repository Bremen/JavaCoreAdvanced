package Lesson_2;

import java.util.Random;

public class MainClass {
    public static void main(String[] args) {
        int sum = 0;
        Random random = new Random();
        String[][] sArray = new String[4][4];

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                sArray[i][j] = String.valueOf(random.nextInt(50));
            }
        }

        try {
            sum = sumArray(sArray);
        } catch (MyArraySizeExeption e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

    }

    public static int sumArray(String[][] sArray){
        int sum = 0;
        if(sArray.length != 4) throw new MyArraySizeExeption();
        for(int i = 0; i < sArray.length; i++) {
            if(sArray[i].length != 4) throw new MyArraySizeExeption();
            for(int j = 0; j < sArray[0].length; j++) {
                try {
                    sum += Integer.parseInt(sArray[i][j]);
                }catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }

            }
        }
        return sum;
    }


}

class MyArrayDataException extends RuntimeException{
    int row, column;

    public MyArrayDataException(int row, int column) {
        super(row + " " + column);
        this.row = row;
        this.column = column;
    }
}

class MyArraySizeExeption extends RuntimeException{

    public MyArraySizeExeption() {
        super("wrong size");
    }
}























//
//
//
//
//    static int workWithMassive(String[][] varMassive)throws MyArraySizeException,MyArrayDataException{
//        int summ=0;
//        if (varMassive.length!=4 || varMassive[0].length!=4){
//            throw new MyArraySizeException("Wrong size",String.valueOf(varMassive.length)+" "+String.valueOf(varMassive[0].length));
//        }
//
////////////////
//
//// запонение массива
//        public class MainClass {
//            public static void main(String[] args) {
//                String[][] arr = {
//                        {"1", "2", "3", "4"},
//                        {"1", "2", "3", "4"},
//                        {"1", "2", "3", "4"},
//                        {"1", "2", "3", "4"}
//                };
//
//
///////////////
//
//
//                String[][] myArray = {{"2","1","6","5"},{"2","3","6","5"},{"2","3","6","5"},{"2","3","6","5"}};
//
//
//
/////////////////////
//
// int[] mass = new int[2][];
//  mass[0] = new int[10];
//  mass[1] = new int[15];
//                //корректный массив
//                final String[][] arrCurrent = {{"5","5","5","5"},{"4","4","4","4"},{"3","3","3","3"},{"3","3","3","3"}};
//                //массив с некорректным размером
//                final String[][] arrBadSize = {{"5","5","5","5"},{"4","4","4"},{"3","3","3","3"},{"3","3","3","3"}};
//                //массив с некорректным форматом данных
//                final String[][] arrBadFormat = {{"5","5","5","5"},{"4","no","4","4"},{"3","3","3","3"},{"3","3","3","3"}};
//
//
//////////////
//
//
//                public static void main(String[] args){
//                    String[][] array = new String[4][4];
//                    array[0][0] = "1";
//                    array[0][1] = "2";
//                    array[0][2] = "3";
//                    array[0][3] = "4";
//                    array[1][0] = "1";
//                    array[1][1] = "2";
//                    array[1][2] = "3";
//                    array[1][3] = "4";
//                    array[2][0] = "1";
//                    array[2][1] = "2";
//                    array[2][2] = "3";
//                    array[2][3] = "4";
//                    array[3][0] = "1";
//                    array[3][1] = "2";
//                    array[3][2] = "3G";
//                    array[3][3] = "4hh";
//
//
////////////////////////////
//
//
//
//                    if (array.length != 4 || array[0].length != 4 ||
//                 array[1].length != 4 || array[2].length != 4 || array[3].length != 4) throw new MyArraySizeException();
//
//
//                    ////////
//
//
//
//                    for (int i = 0; i < arrayStr.length; i++) {
//                        for (j = 0; j < arrayStr[i].length; j++) {
//                            try {
//                                boolean canProceed = checkArray(arrayStr[i][j]);
//                                if (canProceed) {
//                                    valueOfIndex = Integer.parseInt(arrayStr[i][j]);
//                                    sum += valueOfIndex;
//                                } else {
//                                    throw new MyArrayDataException("Не корректные данные в столбце: " + (i + 1) + ", поле в строке: " + (j + 1) + ", информация в поле:" + arrayStr[i][j]);
//                                }
//                            } catch (MyArrayDataException ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                        if (j != 4) throw new MySizeArrayException("Массив должен быть 4 на 4");
//                    }
//                    System.out.println(sum);
//
//
//
//
//
//
//
//                    ///////////////////////////
//
//
//
//                    if(arr.length !=4) {
//                        throw new MyArraySizeException("array size must be equal 4");
//                    }
//                    else {
//                        for (int i=0; i<4; i++){
//                            if (arr[i].length !=4) {
//                                throw new MyArraySizeException("array size must be equal 4");
//                            }
//                        }
//                    }
//                    for (int i=0; i<4; i++){
//                        for (int j=0; j<4; j++){
//                            try {
//                                summ = summ + Integer.parseInt(arr[i][j]);
//                            }
//
//                            catch(Exception e) {
//                                throw new MyArrayDataException(" wrong data type at " + arr[i][j]);
//                            }
//                        }
//                    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//                    public class AdditionalTask {
//                        public static final int workingTimeInADay = 8;
//                        public static void main(String[] args) {
//                            int time = timeToWeekends(DayOfWeek.WEDNESDAY);
//                            if (time != 0) {
//                                System.out.println("До начала выходных осталось " + time + " рабочих часов.");
//                            } else {
//                                System.out.println("Вообще-то уже выходные!");
//                            }
//                        }
//                        public static int timeToWeekends(DayOfWeek day) {
//                            int count = 0;
//                            switch (day) {
//                                case MONDAY:
//                                    count += workingTimeInADay;
//                                case THURSEDAY:
//                                    count += workingTimeInADay;
//                                case WEDNESDAY:
//                                    count += workingTimeInADay;
//                                case TUESDAY:
//                                    count += workingTimeInADay;
//                                case FRIDAY:
//                                    count += workingTimeInADay;
//                            }
//                            return count;
//                        }
//                    }
//
//
//
//package additional_task;
//                    public enum DayOfWeek {
//                        MONDAY, TUESDAY, WEDNESDAY, THURSEDAY, FRIDAY, SATURDAY, SUNDAY;
//                    }
//