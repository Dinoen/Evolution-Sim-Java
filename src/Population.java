import java.util.ArrayList;
import processing.core.PApplet;

public class Population {

    PApplet p = new PApplet();
    //Making Rabbits via ArrayList.
    public ArrayList<Rabbit> arrayOfRabbits = new ArrayList<>();


    public Population(int populationSize) {

        for (int i = 0; i < populationSize; i++) {
            Rabbit rabbit = new Rabbit(p, 30, 20);
            arrayOfRabbits.add(rabbit);
        }
    }
    public void print2() {
        System.out.println(arrayOfRabbits.size());
    }
}

