import processing.core.PApplet;

import java.util.concurrent.TimeUnit;

public class Main extends PApplet {
    public static PApplet processing;

    Rabbit rabbitOne = new Rabbit(10,10);
    public static void main (String[] args) {

        PApplet.main("Main", args);

    }

    public void settings () {

        size(500,500);
    }

    public void setup() {
        processing = this;
    }

    public void draw()
    {
        background(200);
        rabbitOne.drawRabbit();

    }
}
