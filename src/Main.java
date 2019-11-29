import processing.core.PApplet;
import java.util.ArrayList;


public class Main extends PApplet {
    Population populationOfRabbits;
    static Environment theEnvironment;
    public static ArrayList<Population> allEntities;



    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {
        int widthOfWindow = 900;
        int heightOfWindow = 900;
        size(widthOfWindow, heightOfWindow);
        smooth();

    }
    public void setup() {
        int theGroundWidth = 800;
        int theGroundHeight = 800;
        populationOfRabbits = new Population();
        theEnvironment = new Environment(this,theGroundWidth,theGroundHeight);
        populationOfRabbits.createPopulation(this,10,"Rabbit");
        allEntities = new ArrayList<>();
        allEntities.add(populationOfRabbits);
    }

    public void draw() {
        background(0,0,0);
        theEnvironment.update();
        populationOfRabbits.update();
    }
}