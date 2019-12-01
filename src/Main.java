import javafx.util.Pair;
import processing.core.PApplet;

import java.util.ArrayList;


public class Main extends PApplet {
    //instantiating the population of the rabbits
    Entities entitiesOfRabbits;
    Entities entitiesOfGrass;

    GraphingTheData updateGraph;
    private int startTime;
    private int durationTime;

    Entities entitiesOfDeadRabbits;


    //instantiating the Environment for the animals
    static Environment theEnvironment;
    //An arraylist which holds all the living animals on the field
    public static ArrayList<Entities> allEntities;
    public static ArrayList<Entities> allDeadEntities;


    //the Main class
    public static void main(String[] args) {
        PApplet.main("Main", args);
        GraphingTheData ourGraph = new GraphingTheData();
        ourGraph.main(args);

    }

    //Settings class, with window size for the program
    public void settings() {
        int widthOfWindow = 900;
        int heightOfWindow = 900;
        //Size of the program window
        size(widthOfWindow, heightOfWindow, this.P2D);
        //making the movement smooth, i think, comes from processing
        smooth();

    }

    //Setup, which creates all the elements of the program
    public void setup() {
        //our playing field size
        int theGroundWidth = 800;
        int theGroundHeight = 800;
        //our pouplation of rabbits
        entitiesOfRabbits = new Entities();
        entitiesOfGrass = new Entities();


        updateGraph = new GraphingTheData();

        entitiesOfDeadRabbits = new Entities();

        //instatiating the environment, with the sizes from above
        theEnvironment = new Environment(this, theGroundWidth, theGroundHeight);
        //Creating the population of the rabbits.
        //createPopulation comes from the population class, and holds all the rabbits in an arraylist
        entitiesOfRabbits.createEntities(this, 20, "Rabbit"); //population is the amount of rabbits spawne
        entitiesOfGrass.createEntities(this, 15, "Grass");

        //AllEntities list, which holds all the different population lists
        allEntities = new ArrayList<>();
        allDeadEntities = new ArrayList<>();
        //putting all the rabbits into the AllEntities list, in the main class
        //keeps all the moving parts together, making it easier to compare objects fx rabbits finding food or other rabbits
        allEntities.add(entitiesOfRabbits);
        allEntities.add(entitiesOfGrass);

        allDeadEntities.add(entitiesOfDeadRabbits);

    }

    //Drawing, Runs every frame
    public void draw() {
        //Grey background, which removes the tails of the moving elements
        background(0, 0, 0);
        //Running the update function in the environment class, updating the positions of stuff in environment
        //update function is a collection of the methods needing to be updated
        theEnvironment.update();
        //update function is a collection of the methods needing to be updated
        //Running the update function in the Population class, updating the positions and states of the rabbits.
        entitiesOfRabbits.update();
        entitiesOfGrass.update();


    }
}