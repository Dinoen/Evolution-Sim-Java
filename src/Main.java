import processing.core.PApplet;

import java.util.concurrent.TimeUnit;

public class Main extends PApplet {
    public static PApplet processing;

    Rabbit rabbitOne = new Rabbit(10,10);
    Rabbit rabbitTwo = new Rabbit(50,50);
    public static void main (String[] args) {

        PApplet.main("Main", args);

    }

    public void settings () {

        size(800,800);
    }

    public void setup() {
        processing = this;
    }

    public void draw()
    {
        background(200);
        rabbitOne.drawRabbit();
        rabbitTwo.drawRabbit();

    }
}
