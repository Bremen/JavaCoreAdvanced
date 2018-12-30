public class DayOfWeekMain {
    public static void main(final String[] args) {
        reportRemainingWorkingHours(DayOfWeek.MONDAY);
        reportRemainingWorkingHours(DayOfWeek.TUESDAY);
        reportRemainingWorkingHours(DayOfWeek.WEDNESDAY);
        reportRemainingWorkingHours(DayOfWeek.THURSDAY);

        reportRemainingWorkingHours(null);

        reportRemainingWorkingHours(DayOfWeek.FRIDAY);
        reportRemainingWorkingHours(DayOfWeek.SATURDAY);
        reportRemainingWorkingHours(DayOfWeek.SUNDAY);
    }

    private static void reportRemainingWorkingHours(DayOfWeek currentDay) {
        try {
            int remainsWorkHours = DayOfWeek.getWorkingHours(currentDay);
            System.out.println("Текущий день: "
                    + currentDay.getRus()
                    + ". Рабочих часов на этой неделе осталось: "
                    + remainsWorkHours);
            if (remainsWorkHours == 0){
                System.out.println("Работа закончилась можно отдохнуть!");
            }
        }catch (NullPointerException e){
            System.out.println("Вместо дня недели была передана пустая ссылка!");
        }
        System.out.println();
    }

    enum DayOfWeek{
        MONDAY("понедельник"),
        TUESDAY("вторник"),
        WEDNESDAY("среда"),
        THURSDAY("четверг"),
        FRIDAY("пятница"),
        SATURDAY("суббота"),
        SUNDAY("воскресение");

        static int getWorkingHours(DayOfWeek day){
            int remainsWorkHours = 0;
            if (day.ordinal() <= 5){
                remainsWorkHours = (5-day.ordinal())*8;
            }
            return remainsWorkHours;
        }

        private String rus;
        public String getRus() {
            return rus;
        }

        DayOfWeek(String rus) {
            this.rus = rus;
        }

    }
}

