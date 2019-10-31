import processing.core.PApplet;
import processing.core.PConstants;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import static processing.core.PApplet.dist;


public class Rabbit {

    PApplet p = new PApplet();
    private int x = 0;
    private int y = 0;
    private int currentX = 0;
    private int currentY = 0;
    private int movementSpeed = 1;
    private int sightDist = 50;
    private int sizeOfRabbit = 10;
    private int Hunger = 0; // will go from 0 to 100, increasing with activity
    private int MAXHunger = 100;
    private int Thirst = 0;  // will go from 0 to 100, increasing with activity
    private int MAXThirst = 100;
    private int urgeToReproduce = 0;
    private int MAXUrgeToReproduce = 100;
    private int viewDistance = 100;
    public Random randomNumber = new Random();
    int newX = randomNumber.nextInt(800); // 800 is the width
    int newY = randomNumber.nextInt(800); // 800 is the height




    public Rabbit(PApplet p, int x, int y) // Contructor
    {
        this.setX(x);
        this.setY(y);
        this.p = p;

        int savedTime = p.millis();
        int passedTime = p.millis() - savedTime;
    }

    public void drawRabbit(PApplet p)  // Graphical representation
    {
        p.rectMode(PConstants.CENTER);
        p.fill(255);
        p.rect(getX(), getY(),sizeOfRabbit, sizeOfRabbit);
        p.ellipseMode(PConstants.CENTER);
        p.noFill();
        p.ellipse(getX(),getY(),sightDist,sightDist);


        p.rectMode(PConstants.CENTER);
        p.fill(255);
        p.rect(getX(), getY(), 10, 10);
        p.ellipseMode(PConstants.CENTER);
        p.noFill();
        p.ellipse(getX(), getY(), viewDistance, viewDistance);
        rabbitMovement();

        //moveDirection();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void rabbitMovement() {
        currentX = this.x;
        currentY = this.y;

        if (currentX < newX - 5 || currentX > newX + 5 && currentY < newY + 5 || currentY > newY - 5) {
            this.newX = randomNumber.nextInt(p.width);
            this.newY = randomNumber.nextInt(p.height);
        }


        if (currentX < newX) {
            x = x + movementSpeed;
        }
        if (currentX > newX) {
            x = x - movementSpeed;
        }
        if (currentY < newY) {
            y = y + movementSpeed;
        }
        if (currentY > newY) {
            y = y - movementSpeed;
        }

    }

    public void moveDirection() {

        int a = randomNumber.nextInt(5);

        switch (a) {

            case 0:
                if (x < p.width) {
                    this.x += movementSpeed;
                }
                break;
            case 1:
                if (x > 0) {
                    this.x -= movementSpeed;
                }
                break;
            case 2:
                if (y < p.height - 3) {
                    this.y += movementSpeed;
                }
                break;
            case 3:
                if (y > 3) {
                    this.y -= movementSpeed;
                }
                break;
        }
    }

    /*public void rabbitVision() {
        for (Rabbit : ) {

        }
    }*/

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getViewDistance() {
        return viewDistance;
    }
}
