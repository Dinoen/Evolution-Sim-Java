import javafx.util.Pair;
import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    //instantiating the population of the rabbits
    Entities entitiesOfRabbits;
    Entities entitiesOfGrass;
    Entities entitiesOfFoxes;
    InteractiveObject openGraph;
    InteractiveObject startTheProgram;

    static int stateOfProgram = 0;
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

        //creating af graphing object
        GraphingTheData ourGraph = new GraphingTheData();
        //running the graph object to display the graph
        ourGraph.main(args);
    }

    //Settings class, with window size for the program
    public void settings() {
        int widthOfWindow = 900;
        int heightOfWindow = 900;

        //Size of the program window
       size(widthOfWindow, heightOfWindow);
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
        //our grass population
        entitiesOfGrass = new Entities();
        //array of dead rabbits
        entitiesOfDeadRabbits = new Entities();
        //Array of foxes
        entitiesOfFoxes = new Entities();

        //creating an interactive object that is able to open the graph by the push of a button
        openGraph = new InteractiveObject();
        openGraph.createInteractiveObject(this, 875, 100, 50, 50, "Square",true);

        //Instantiating the object that allow us to start the program
        startTheProgram = new InteractiveObject();
        startTheProgram.createInteractiveObject(this,400,400,50,50,"Square", true);

        //instatiating the environment, with the sizes from above
        theEnvironment = new Environment(this, theGroundWidth, theGroundHeight);

        //Creating the population of the rabbits.
        //createPopulation comes from the population class, and holds all the rabbits in an arraylist
        entitiesOfRabbits.createEntities(this, 10, "Rabbit"); //population is the amount of rabbits spawne
        entitiesOfGrass.createEntities(this, 10, "Grass");
        entitiesOfFoxes.createEntities(this,2,"Fox");

        //AllEntities list, which holds all the different population lists
        allEntities = new ArrayList<>();
        allDeadEntities = new ArrayList<>();

        //putting all the rabbits into the AllEntities list, in the main class
        //keeps all the moving parts together, making it easier to compare objects fx rabbits finding food or other rabbits
        allEntities.add(0,entitiesOfRabbits);
        allEntities.add(1,entitiesOfGrass);
        allEntities.add(2,entitiesOfFoxes);
        allDeadEntities.add(entitiesOfDeadRabbits);

    }

    //Drawing, Runs every frame
    public void draw() {
        //Grey background, which removes the tails of the moving elements
        background(0, 0, 0);
        //Running the update function in the environment class, updating the positions of stuff in environment
        //update function is a collection of the methods needing to be updated

        if(stateOfProgram == 0) {
            startTheProgram.update();

        }

        if(stateOfProgram == 1) {
            theEnvironment.update();
            //update function is a collection of the methods needing to be updated
            //Running the update function in the Population class, updating the positions and states of the rabbits.
            entitiesOfRabbits.update();
            entitiesOfGrass.update();
            entitiesOfFoxes.update();
            openGraph.update();
        }
    }
}