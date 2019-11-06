import processing.core.PApplet;
import processing.core.PVector;

import java.util.Vector;

public class Main extends PApplet {
    public static PApplet p;
    Rabbit rabbit;
    PVector location;
    PVector velocity;



    public static void main(String[] args) {

        PApplet.main("Main", args);

    }
    public void setup() {
        p = this;
        rabbit = new Rabbit(p,50,50);
        location = new PVector(100,100); //OUR NEW X AND Y
        velocity = new PVector(2.5f, 5); // OUR NEW X AND Y SPEEDS

    }

    public void settings() {

        int widthOfWindow = 800;
        int heightOfWindow = 800;
        size(widthOfWindow, heightOfWindow);
        smooth();


    }



    public void draw() {


        Main.p.rect(0,0,width,height);
        rabbit.draw();

    }
}