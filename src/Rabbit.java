import processing.core.PApplet;
import processing.core.PConstants;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Rabbit {


    PApplet p;
    private int x = 0;
    private int y = 0;
    int currentX;
    int currentY;
    public int matingCondition = 0;
    private int viewDistance = 100;
    private int movementSpeed = 3;
    public Random randomNumber = new Random();
    int newX = randomNumber.nextInt(500);
    int newY = randomNumber.nextInt(500);
    int timeUntilMature = 10000;


    public Rabbit(PApplet p, int x, int y) // Contructor
    {
        this.setX(x);
        this.setY(y);
        this.p = p;

        int savedTime = p.millis();
        int passedTime = p.millis() - savedTime;

        if (passedTime > timeUntilMature)
        {
            matingCondition = 1;
        }

    }

    public void drawRabbit()  // Graphical representation
    {
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
        //if (currentX == newX && currentY == newY)
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public void setNewY(int newY) {
        this.newX = newX;
    }


    public int getViewDistance() {
        return viewDistance;
    }

    public void setmatingCondition(int matingCondition) {
        this.matingCondition = matingCondition;
    }
}
