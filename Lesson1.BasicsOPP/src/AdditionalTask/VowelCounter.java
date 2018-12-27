package AdditionalTask;

class VowelCounter {
    private String[] strings; //данные для подсчета в них гласных
    private final Character[] vowels = {'a', 'o', 'u', 'i', 'e', 'y'}; //Массив гласных которые нужно считать

    VowelCounter(String input){
        setInputString(input);
    }

    /**
     * Функция приема входной информации
     * @param input - входные данные передаются как единая строка с разделителем по умолчанию '\n'
     */
    void setInputString(String input) {
        this.strings = input.split("\n");
    }

    /**
     * Функция считает количество гласных в каждой строке и выводит в консоль количество гласных для каждой строки
     */
    void count(){
        for (int strNumber = 0; strNumber < strings.length; strNumber++) {
            int vowelCount = getVowelCount(strings[strNumber]);
            System.out.printf("Строка №%d содержит %d гласных букв.\n", strNumber+1, vowelCount);
        }
    }

    /**
     * Функция подсчитывает количество гласных букв на латинице в строке
     * @param string - строка в которой нужно подсчитать количество гласных
     * @return - возвращается количество гласных в переданной строке
     */
    private int getVowelCount(String string) {
        int vowelCount = 0;
        for (int i = 0; i < string.length(); i++) {
            if (java.util.Arrays.asList(vowels).indexOf(string.charAt(i)) != -1){
                vowelCount++;
            }
        }
        return vowelCount;
    }


}
