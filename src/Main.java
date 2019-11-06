import processing.core.PApplet;
import processing.core.PVector;

import java.util.Vector;

public class Main extends PApplet {
    public static PApplet p;
    Rabbit rabbit;




    public static void main(String[] args) {

        PApplet.main("Main", args);

    }
    public void setup() {
        p = this;
        rabbit = new Rabbit(p,50,50);

    }

    public void settings() {

        int widthOfWindow = 800;
        int heightOfWindow = 800;
        size(widthOfWindow, heightOfWindow);
        smooth();


    }



    public void draw() {

        background(200);
        rabbit.Movement();
        rabbit.draw();


    }
}