public class ExceptionsTesting {
    static private final int SIZE = 4;

    public static void main(String[] args) {
        String[][] normalArray = {{"1","2","3","4"},{"5","6","7","8"},{"1","2","3","4"},{"5","6","7","8"}};
        get4x4ArrayElementsSumTesting(normalArray);

        String[][] wrongRowsCountArray = {{"1","2","3","4"},{"5","6","7","8"},{"1","2","3","4"},{"5","6","7","8"},{"9","9","9"}};
        get4x4ArrayElementsSumTesting(wrongRowsCountArray);

        String[][] wrongRowElementsCountArray = {{"1","2","3","4"},{"5","6","7","8","9"},{"1","2","3","4"},{"5","6","7","8"}};
        get4x4ArrayElementsSumTesting(wrongRowElementsCountArray);

        String[][] wrongElementDataArray = {{"1","2","3","4"},{"5","6","7","8"},{"f","2","3","4"},{"5","6","7","8"}};
        get4x4ArrayElementsSumTesting(wrongElementDataArray);

        String[][] wrongEverythingArray = {{"f","2","3"},{"5","g","7","8","9"},{"f","2","3","4"}};
        get4x4ArrayElementsSumTesting(wrongEverythingArray);

        String[][] nullPointerArray = null;
        get4x4ArrayElementsSumTesting(nullPointerArray);
    }

    private static void get4x4ArrayElementsSumTesting(String[][] array) {
        try{
            printArray(array);
            int sum = get4x4ArrayElementsSum(array);
            System.out.printf("Сумма элементов массива: %d\n", sum);
        }catch(MyArraySizeException | MyArrayDataException e){
            System.out.println(e.getMessage() + " Сумма не посчиталась!");
        }catch(NullPointerException e){
            System.out.println("Вместо массива пустая ссылка! Вывод трека:");
            e.printStackTrace();
        }
        System.out.println("-------------------------------------------------------------------------------------------------\n");
    }

    private static void printArray(String[][] array) {
        for (String[] row : array) {
            for (String element : row) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
    }

    //оператор throws можно не указывать т.к. MyArrayDataException и MyArraySizeException являются подклассом RuntimeException
    private static int get4x4ArrayElementsSum(String[][] array) /*throws MyArraySizeException, MyArrayDataException*/{
        checkArraySize(array);
        return getArraySum(array);
    }

    //оператор throws можно не указывать т.к. MyArraySizeException является подклассом RuntimeException
    private static void checkArraySize(String[][] array) /*throws MyArraySizeException*/{
        if(array.length != SIZE){
            String msg = String.format("Количество строк в массиве неравно %d!", SIZE);
            throw new MyArraySizeException(msg);
        }else{
            for (int row = 0; row < array.length; row++) {
                if(array[row].length != SIZE) {
                    String msg = String.format("Количество элементов в %d строке массива неравно %d!", row+1, SIZE);
                    throw new MyArraySizeException(msg);
                }
            }
        }
    }

    //оператор throws можно не указывать т.к. MyArrayDataException является подклассом RuntimeException
    private static int getArraySum(String[][] array) /*throws MyArrayDataException*/{
        int sum = 0;
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                try {
                    Integer element = Integer.parseInt(array[row][col]);
                    sum += element;
                }catch (NumberFormatException e){
                    String msg = String.format("Елемент \"%s\" %d строки и %d столбца не может быть преобраззован в целое число!", array[row][col], row+1, col+1);
                    throw new MyArrayDataException(msg);
                }
            }
        }
        return sum;
    }
}

class MyArraySizeException extends ArrayIndexOutOfBoundsException{
    MyArraySizeException(String msg){
        super(msg);
    }
}

class MyArrayDataException extends NumberFormatException {
    MyArrayDataException(String msg){
        super(msg);
    }
}