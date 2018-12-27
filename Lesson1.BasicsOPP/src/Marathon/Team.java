package Marathon;

import Marathon.Competitor.Competitor;

public class Team {
    private String name;
    Competitor[] competitors;

    public Team(String name, Competitor... competitors){
        this.name = name;
        this.competitors = competitors;
    }

    /**
     * выводит информацию о всех участниках команды
     */
    public void teamInfo(){
        System.out.println("Информация об участниках команды: " + name);

        for (Competitor c : competitors) {
            c.info();
        }

        System.out.println();
    }

    /**
     * Метод выводящий информацию только об участниках оставшихся на дистанции
     */
    public void teamOnDistanceInfo(){
        System.out.println("Полосу препятствий прошли участники команды: " + name);

        for (Competitor c : competitors) {
            if (c.isOnDistance()){
                c.info();
            }
        }

        System.out.println();
    }
}
