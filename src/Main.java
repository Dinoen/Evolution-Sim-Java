import processing.core.PApplet;

import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main extends PApplet {
    public PApplet p; //Just called P

    int savedTime = millis();
    int savedTime1 = millis();
    int matingTimeRequired = 5000;
    int matureTimerRequired = 5000;

    //Making Rabbits via ArrayList.
    public ArrayList<Rabbit> arrayOfRabbits = new ArrayList<>();

    public ArrayList<Rabbit> getArrayOfRabbits(PApplet p, int x, int y) {
        arrayOfRabbits.add(new Rabbit(p,x,y));
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
        //Setting up the rabbits in the ArrayList.
        getArrayOfRabbits(this,10,10);
        getArrayOfRabbits(this,400,400);
    }

    public void draw() {

        background(200);

        //New rabbit function that creates rabbits,
        newRabbit();

        //Running through the ArrayList to draw the rabbits.
        for (int i = 0; i < arrayOfRabbits.size(); i++) {
            arrayOfRabbits.get(i).drawRabbit();
        }



        /*rabbitOne.drawRabbit();
        rabbitTwo.drawRabbit();
        rabbitThree.drawRabbit();
        rabbitFour.drawRabbit();
        rabbitFive.drawRabbit();
        rabbitSix.drawRabbit();*/

    }

    public void newRabbit() {

        //Getting the coordinates from two rabbits
        int x1 = arrayOfRabbits.get(0).getX();
        int y1 = arrayOfRabbits.get(0).getY();
        int x2 = arrayOfRabbits.get(1).getX();
        int y2 = arrayOfRabbits.get(1).getY();

        //Making a float using coordinates from rabbits.
        float dist = dist(x1, y1, x2, y2);
        System.out.println(dist);
        System.out.println(arrayOfRabbits.get(0).getViewDistance());

        //When rabbits are in a specific distance from each other we fill the ArrayList with a new Rabbit.
        if (dist < arrayOfRabbits.get(0).getViewDistance() && dist > arrayOfRabbits.get(0).getViewDistance()-3) {
            getArrayOfRabbits(this, 10, 10);

        }
    }
}
