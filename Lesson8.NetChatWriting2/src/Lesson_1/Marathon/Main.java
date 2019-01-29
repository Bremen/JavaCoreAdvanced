package Lesson_1.Marathon;

import Lesson_1.Marathon.Competitor.Cat;
import Lesson_1.Marathon.Competitor.Dog;
import Lesson_1.Marathon.Competitor.Human;
import Lesson_1.Marathon.Competitor.Team;
import Lesson_1.Marathon.Obstacle.Course;
import Lesson_1.Marathon.Obstacle.Cross;
import Lesson_1.Marathon.Obstacle.Wall;

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Rocket", new Human("Bob"), new Cat("Vaska"), new Dog("Bobik"));
        Team team1 = new Team("Rocket123", new Dog("Bobik"));
        Course course = new Course(new Cross(80), new Wall(2), new Wall(1), new Cross(120));
        course.doIt(team);
        team.showWinners();
    }
}














































//
//
//
//
//
//public abstract class MarathonMember {
//    String name = "";
//    abstract boolean run(int obstacleDistance);
//    abstract boolean swim(int obstacleDistance);
//    abstract boolean jump(int obstacleHeight);
//    boolean doAction(Obstacle obstacle) {
//        boolean result;
//        if (obstacle instanceof Cross) {
//            result = run(obstacle.getObstacleDistance());
//        } else if (obstacle instanceof SwimmingPool) {
//            result = swim(obstacle.getObstacleDistance());
//        } else if (obstacle instanceof Wall) {
//            result = jump(obstacle.getObstacleDistance());
//        } else {
//            result = false;
//        }
//        return result;
//    }
//    String getName() {
//        return name;
//    }
//}
//
//
//public abstract class NonOrganicLife extends MarathonMember {
//    int batteryCharge;
//    @Override
//    public boolean run(int obstacleDistance) {
//        boolean isDistanceFinished;
//        if (batteryCharge >= obstacleDistance) {
//            isDistanceFinished = true;
//        } else {
//            isDistanceFinished = false;
//        }
//        batteryCharge -= obstacleDistance;
//        return isDistanceFinished;
//    }
//    //Роботы не умеют плавать
//    @Override
//    public boolean swim(int obstacleDistance) {
//        return false;
//    }
//    @Override
//    public boolean jump(int obstacleHeight) {
//        boolean isDistanceFinished;
//        if (batteryCharge >= obstacleHeight * 100) {
//            isDistanceFinished = true;
//        } else {
//            isDistanceFinished = false;
//        }
//        batteryCharge -= obstacleHeight * 100;
//        return isDistanceFinished;
//    }
//}
//
//
//
//
//    public Team() {
//        for (int i = 0; i < 5; i++) {
//            ourTeam[i] = lookingForMember();
//        }
//    }
//    private MarathonMember lookingForMember() {
//        MarathonMember member;
//        int randomNumber = random.nextInt(5);
//        switch (randomNumber) {
//            case 0:
//                member = new Dog();
//                break;
//            case 1:
//                member = new Cat();
//                break;
//            case 2:
//                member = new Human();
//                break;
//            case 3:
//                member = new Robot();
//                break;
//            case 4:
//                member = new Cyborg();
//                break;
//            default:
//                member = new Dog();
//        }
//        return member;
//    }
//
//
//
//////////////////
//
//
//
//
//
//
//
//
//
//public class Team {
//    Competitor[] competitors;
//    public Team(Competitor[] competitors) {
//        createTeam();
//    }
//    public Competitor[] createTeam() {
//        Competitor[] competitors = {new OldLady("Елена Петровна"), new OldMan("Виктор Петрович"), new SportsmanA1("Николай"), new Boy("Пашка")};
//        this.competitors = competitors;
//        return competitors;
//    }
//    public void showTeamMates(String teamName) {
//        System.out.println("The name of the team is " + teamName);
//        System.out.println("New Team was created and the new Members are " + competitors[0].getName() + " and " + competitors[1].getName()
//                + " and " + competitors[2].getName() + " and " + competitors[3].getName());
//    }
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
//    ///////////////
//
//
//
//
//
//
//    public class Team implements Competitor {
//        Competitor[] competitor;
//        String teamname;
//
//
//        public Team(String teamname) {
//            Competitor[] competitors = new Competitor[4];
//            competitors[0] = new Human("Игнат", 200, 5, 1);
//            competitors[1] = new Human("Олег", 250, 15, 2);
//            competitors[2] = new Cat("Кот", "Барсик", 50, 10, 2);
//            competitors[3] = new Dog("Пес", "Бобик", 200, 15, 3);
//            this.competitor = competitors;
//            this.teamname = teamname;
//        }
//
//
//
//
//
//
//
//
//
//        /////////////
//
//        Настроить логирование действий пользователя для ИС Управление знаниями.
//        Разработать алгоритм анализа активности пользователей ИС Управление знаниями за отчетный период - 180 дней.
//        Разработать процедуру автоматической блокировки неактивных пользователей ИС Управление знаниями.
//
//
//
//
//
//
//
//
//
//        public class Team implements Iterable<Competitor> {
//            private Competitor competitors[];
//
//            public Team(Competitor... competitors) {
//                this.competitors = competitors;
//            }
//
//            public Team(Competitor c1, Competitor c2, Competitor c3, Competitor c4) {
//                this(new Competitor[]{ c1, c2, c3, c4 });
//            }
//
//
//
//
//
//
//
//
//
//            //////////////
//
//
//
//
//
//            class Team {
//                private String teamName = "команда победителей по жизни";
//                private Competitor[] competitors = {new Human("человек", "серега", 50, 40, 1),
//                        new Dog("пес", "бобик", 100, 50, 3),
//                        new Cat("кот", "барсик", 90, 40, 2),
//                        new Fish("рыбка", "поньо", 0, 100, 0)};
//                Competitor[] getCompetitors() {
//                    return competitors;
//                }
//                void showResults() {
//                    System.out.println("\n" + teamName + "\n");
//                    for (Competitor competitor : competitors) {
//                        competitor.info();
//                    }
//                }
//            }
//
//
//
//
//
//
//
//            /////////////
//
//
//
//
//
//
//
//
//            switch (ch){
//                case 'a' : {//Если символ соответствует гласной, увеличиваем значение numberOfVovelsInString и выходим
//                    numberOfVovelsInString++;
////                    System.out.println(ch);//для отладки, чтобы видеть что выводим
//                    break;
//                }
//                case 'e' : {
//                    numberOfVovelsInString++;
////                    System.out.println(ch);
//                    break;
//                }
//                case 'i' : {
//                    numberOfVovelsInString++;
////                    System.out.println(ch);
//                    break;
//                }
//                case 'o' : {
//                    numberOfVovelsInString++;
////                    System.out.println(ch);
//                    break;
//                }
//                case 'u' : {
//                    numberOfVovelsInString++;
////                    System.out.println(ch);
//                    break;
//                }
//                case 'y' : {
//                    numberOfVovelsInString++;
////                    System.out.println(ch);
//                    break;
//                }
//                case '\n' : {//Если символ соответствует переносу строки
//                    numberOfStrings++;                                                                                       //Увеличиваем значение numberOfStrings и выходим
//                    System.out.println("В строке №" + (numberOfStrings) + ": " + numberOfVovelsInString + " гласных");      //Выводим сообщение о количестве гласных в строке
//                    numberOfVovelsInText += numberOfVovelsInString;                                                          //Увеличиваем numberOfVovelsInText
//                    numberOfVovelsInString = 0;                                                                              //Обнуляем количество гласных в строке
////                    System.out.println(ch);
//                    break;
//                }
//
//
//
//
//
//
//////////////////////
//
//
//
//
//
//
//
//
//
//                public class Team {
//                    Competitor[] team = new Competitor[4];
//
//                    public Team(Competitor member1, Competitor member2, Competitor member3, Competitor member4) {
//                        team[0] = member1;
//                        team[1] = member2;
//                        team[2] = member3;
//                        team[3] = member4;
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
/////////////////
//
//
//
//
//
//
//                    public class TeamMembers extends Team {
//                        private String NAME_TEAM = "Sparta";
//                        public String[] membersArray;
//                        public TeamMembers() {
//                            membersArray = new String[]{"Bob", "Tom", "Jack", "Robert"};
//                        }
//
//
///////////////
//
//
//
//
//
//
//                        private String name;
//                        private Competitor[] competitors = new Competitor[4];
//
//                        public String getName() {
//                            return name;
//                        }
//
//                        public void setName(String name) {
//                            this.name = name;
//                        }
//
//                        public Competitor[] getCompetitors() {
//                            return competitors;
//                        }
//
//                        public void setCompetitors(Competitor[] competitors) {
//                            this.competitors = competitors;
//                        }
//
//                        public Team(String name, Competitor...competitors) {
//                            this.name = name;
                      //      this.competitors = competitors;
//                            for (int i = 0; i < competitors.length; i++) {
//                                this.competitors[i] = competitors[i];
//                            }
//                        }
