import processing.core.PApplet;

import java.util.concurrent.TimeUnit;

public class Main extends PApplet {
    public static PApplet processing;

    Rabbit rabbitOne = new Rabbit(10,10);
    Rabbit rabbitTwo = new Rabbit(50,50);
    Rabbit rabbitThree = new Rabbit(20,30);
    private int widthOfWindow = 800;
    private int heightOfWindow = 800;


    public static void main (String[] args) {

        PApplet.main("Main", args);

    }

    public void settings () {

        size(widthOfWindow,heightOfWindow);
    }

    public void setup() {
        processing = this;
    }

    public void draw()
    {
        background(200);
        rabbitOne.drawRabbit();
        rabbitTwo.drawRabbit();
        rabbitThree.drawRabbit();


    }
}
