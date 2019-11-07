import processing.core.PApplet;
import processing.core.PVector;

import java.util.Vector;

public class Main extends PApplet {
    public static PApplet p;
    Rabbit rabbit;
    Population populate;



    public static void main(String[] args) {

        PApplet.main("Main", args);

    }
    public void setup() {
        p = this;
        rabbit = new Rabbit(p,50,50);
        populate = new Population(10);
    }

    public void settings() {

        int widthOfWindow = 800;
        int heightOfWindow = 800;
        size(widthOfWindow, heightOfWindow);
        smooth();


    }



    public void draw() {

        background(200);
        /*rabbit.Movement();
        rabbit.draw();*/

        for (int i = 0; i < populate.arrayOfRabbits.size(); i++) {
            populate.arrayOfRabbits.get(i).Movement();
            populate.arrayOfRabbits.get(i).draw();
        }


    }
}