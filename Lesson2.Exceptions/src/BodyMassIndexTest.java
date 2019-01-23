public class BodyMassIndexTest {
    static final double MAX_WEIGHT = 570;
    static final double MAX_HEIGHT = 2.72;

    public static void main(String[] args) {
        double[][] inputData = {
                {118 ,2.05},
                {106 ,1.77},
                {87  ,1.83},
                {45  ,1.12},
                {70  ,1.87},
                {54  ,1.57},
                {105 ,1.76},
                {50  ,1.96},
                {114 ,1.76},
                {72  ,2.45},
                {53  ,2.10},
                {66  ,2.25},
                {54  ,1.50},
                {95  ,1.62},
                {86  ,1.72},
                {600 ,1.72},
                {62  ,1.57},
                {65  ,2.24},
                {72  ,1.43},
                {93  ,2.01},
                {109 ,3.01},
                {106 ,2.97},
                {77  ,1.69},
                {114 ,2.09},
                {98  ,1.72},
                {85  ,2.46},
                {113 ,1.94},
                {53  ,1.77},
                {106 ,2.30},
                {-106 ,0}
        };

        for (double[] weightHeight : inputData) {
            try{
                reportBmiWithDiagnosis(weightHeight[0], weightHeight[1]);
            }catch (WongBmiInputException e ){
                System.out.println(e.getMessage());
            }

        }
    }

    private static double calculateBMI(double weight, double height){
        if (weight <= 0 || height <= 0){
            throw new WongBmiInputException("Вес и рост должны задаваться положительными числами. Вес в килограммах. Рост в метрах");
        }else if(weight >= MAX_WEIGHT){
            throw new WongBmiInputException("Вес " + weight + "кг!!! С таким весом срочно обратитесь в службу Книги Рекордов Гинесса!");
        }if(height >= MAX_HEIGHT){
            throw new WongBmiInputException("Рост " + height + "м!!! С таким ростом срочно обратитесь в службу Книги Рекордов Гинесса!");
        }

        return weight / (height*height);
    }

    private static void reportBmiWithDiagnosis(double weight, double height) {
        double BMI = calculateBMI(weight, height);
        BodyMassIndexGrade diagnosis = BodyMassIndexGrade.getDiagnosis(BMI);
        System.out.printf("Индекс массы тела при росте %.2fм и весе %.2fкг равен %.2f. Диагноз: %s\n",
                weight, height, BMI, diagnosis.getRus());
    }

    enum BodyMassIndexGrade{
        UNDER("недовес"),
        NORMAL("норма"),
        OVER("избыток"),
        OBESE("ожирение");

        public String getRus() {
            return rus;
        }

        String rus;

        BodyMassIndexGrade(String rus){
            this.rus = rus;
        }

        static BodyMassIndexGrade getDiagnosis(double MBI){
            BodyMassIndexGrade diagnosis;
            if (MBI < 18.5){
                diagnosis = UNDER;
            }else if (18.5 <= MBI && MBI < 25.0){
                diagnosis = NORMAL;
            }else if (25.0 <= MBI && MBI < 30.0){
                diagnosis = OVER;
            }else /*if (MBI > 30.0)*/{
                diagnosis = OBESE;
            }
            return diagnosis;
        }
    }
}

class WongBmiInputException extends IllegalArgumentException{
    WongBmiInputException(String msg){
        super(msg);
    }
}
