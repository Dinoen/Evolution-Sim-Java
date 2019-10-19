import processing.core.PApplet;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main extends PApplet {
    //public PApplet p; //Just called P

    public ArrayList<Rabbit> arrayOfRabbits = new ArrayList<>(2);

    public ArrayList<Rabbit> getArrayOfRabbits() {
        arrayOfRabbits.add(new Rabbit(this, 10, 10));
        arrayOfRabbits.add(new Rabbit(this, 400, 400));
        return arrayOfRabbits;
    }

    /*Rabbit rabbitOne = new Rabbit(this, 10,10);
    Rabbit rabbitTwo = new Rabbit(this,50,50);
    Rabbit rabbitThree = new Rabbit(this,20,30);
    Rabbit rabbitFour = new Rabbit(this, 70,70);
    Rabbit rabbitFive = new Rabbit(this,100,100);
    Rabbit rabbitSix = new Rabbit(this,200,200);*/

    /*public float viewDistanceFloat() {

        for (int i = 0; i < 2; i++) {



        }
    }*/


    public static void main(String[] args) {

        PApplet.main("Main", args);

    }

    public void settings() {

        size(800, 800);
    }

    public void setup() {

    }

    public void draw() {
        background(200);

        getArrayOfRabbits().get(1).drawRabbit();
        getArrayOfRabbits().get(2).drawRabbit();

    }
        /*rabbitOne.drawRabbit();
        rabbitTwo.drawRabbit();
        rabbitThree.drawRabbit();
        rabbitFour.drawRabbit();
        rabbitFive.drawRabbit();
        rabbitSix.drawRabbit();*/
}
