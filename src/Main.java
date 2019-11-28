import processing.core.PApplet;
import processing.core.PVector;

import java.util.Vector;

public class Main extends PApplet {
    Rabbit rabbit;
    Population populate;



    public static void main(String[] args) {

        PApplet.main("Main", args);

    }
    public void setup() {
        //rabbit = new Rabbit(p,50,50);
        populate = new Population(this,4);
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
        //need a separate function for the creation of the new target. SetTarget. Timer.
        //Separate update and drawing in all the animal classes.
        //
        populate.update();


    }
}