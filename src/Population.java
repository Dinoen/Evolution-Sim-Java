import java.util.ArrayList;
import processing.core.PApplet;

public class Population {

    //Making Rabbits via ArrayList.
    public ArrayList<Rabbit> arrayOfRabbits = new ArrayList<>();

    public Population(PApplet p ,int populationSize) {

        for (int i = 0; i < populationSize; i++) {
            Rabbit rabbit = new Rabbit(p, 30, 20);
            arrayOfRabbits.add(rabbit);
        }
    }

    public void update() {
        for (int i = 0; i < arrayOfRabbits.size(); i++) {
            arrayOfRabbits.get(i).update();
        }
    }
}

