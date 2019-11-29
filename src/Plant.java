import processing.core.PApplet;

//Make this super, super ,superclass, which extends into all others, because everything can be become food

public class Plant {

float x;
float y;


    public Plant(PApplet pApplet){

    }

    public void drawPlant(PApplet p){

        p.rect(x,y,50,50);

    }
}
