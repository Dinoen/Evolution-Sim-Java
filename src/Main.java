import processing.core.PApplet;

public class Main extends PApplet {
    public static PApplet p;
    Rabbit rabbit;

    public static void main(String[] args) {

        PApplet.main("Main", args);

    }
    public void setup() {
        p = this;
        rabbit = new Rabbit(rabbit.getVisionrange(),rabbit.isAlive(),rabbit.isHungry(),rabbit.isThirsty(),rabbit.getX(),rabbit.getY(),rabbit.getMovementSpeed(),rabbit.getSightDist(),rabbit.getSizeOfAnimal(),rabbit.getHunger(),
                rabbit.getMAXHunger(),rabbit.getThirst(), rabbit.getMAXThirst(), rabbit.getUrgeToReproduce(), rabbit.getMAXUrgeToReproduce());
    }

    public void settings() {

        int widthOfWindow = 800;
        int heightOfWindow = 800;
        size(widthOfWindow, heightOfWindow);
        smooth();


    }



    public void draw() {

    }
}