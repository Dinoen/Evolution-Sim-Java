import processing.core.PApplet;

public class Main extends PApplet {
    public static PApplet processing;

    public static void main(String[] args) {

        PApplet.main("Main", args);

    }

    public void settings() {

        size(800, 800);
    }

    public void setup() {
        processing = this;
    }

    public void draw() {
    }
}
