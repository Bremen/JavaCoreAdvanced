package Marathon;

import Marathon.Competitor.Competitor;
import Marathon.Obstacle.Obstacle;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle... obstacles){
        this.obstacles = obstacles;
    }

    public void doIt(Team team){
        for (Competitor competitor : team.competitors) {
            for (Obstacle obstacle : obstacles) {
                obstacle.doIt(competitor);
                if (!competitor.isOnDistance()) break;
            }
            System.out.println();
        }
    }
}
