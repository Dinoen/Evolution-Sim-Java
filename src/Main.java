import processing.core.PApplet;

public class Main extends PApplet {

    private int widthOfWindow = 800;
    private int heightOfWindow = 800;
    InstantiationTheRabbits instantiation = new InstantiationTheRabbits();
    newRabbit newBorn = new newRabbit();

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {

        size(widthOfWindow,heightOfWindow);
    }

    public void setup() {
        //Setting up the rabbits in the ArrayList
        System.out.println(instantiation.arrayOfRabbits.size());
        newBorn.print();
        instantiation.print2();

    }

    public void draw() {

        background(200);
        //New rabbit function that creates rabbits,
        newBorn.newRabbitBaby();
        //Running through the ArrayList to draw the rabbits.
        instantiation.arrayOfRabbits.get(0).drawRabbit(this);
        instantiation.arrayOfRabbits.get(0).rabbitMovement();

        instantiation.arrayOfRabbits.get(1).drawRabbit(this);
        instantiation.arrayOfRabbits.get(1).rabbitMovement();


    }
}
