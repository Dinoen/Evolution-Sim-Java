import java.util.ArrayList;
import processing.core.PApplet;

public class Population {
    PApplet p = new PApplet();
    //Making Rabbits via ArrayList.
    public ArrayList<Rabbit> arrayOfRabbits = new ArrayList<>();


    public Population() {
        Rabbit rabbit = new Rabbit(p, 30, 20);
        Rabbit rabbit1 = new Rabbit(p, 80, 200);
        arrayOfRabbits.add(rabbit);
        arrayOfRabbits.add(rabbit1);
    }

    public void print2() {
        System.out.println(arrayOfRabbits.size());
    }
}

