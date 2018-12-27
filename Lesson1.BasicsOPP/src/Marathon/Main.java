package Marathon;

import Marathon.Competitor.*;
import Marathon.Obstacle.*;

public class Main {
    public static void main(String[] args) {
        Team dreamTeam = new Team("Команда мечты",
                new Marathon.Competitor.Human("Боб",   5000, 10, 200),
                new Cat("Барсик",   200,  5,   0),
                new Dog("Бобик",    500, 15,  20),
                new Cat("Пушистик", 100,  3,   0)
        );

        dreamTeam.teamInfo();

        Course course = new Course(new Cross(80), new Wall(2), new Wall(1), new Cross(120), new Water(10));

        course.doIt(dreamTeam);

        dreamTeam.teamOnDistanceInfo();
    }
}
