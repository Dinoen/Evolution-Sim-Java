import processing.core.PConstants;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Rabbit {

    private int x = 0;
    private int y = 0;
    private int movementSpeed = 1;
    public Random randomNumber = new Random();
    int newX = randomNumber.nextInt(500);
    int newY = randomNumber.nextInt(500);




    public Rabbit(int x, int y) // Contructor
    {
        this.setX(x);
        this.setY(y);
    }

    public void drawRabbit()  // Graphical representation
    {
        Main.processing.rectMode(PConstants.CENTER);
        Main.processing.fill(255);
        Main.processing.rect(getX(), getY(),10,10);
        Main.processing.ellipseMode(PConstants.CENTER);
        Main.processing.noFill();
        Main.processing.ellipse(getX(),getY(),50,50);

        //moveDirection();
        rabbitMovement();

        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void rabbitMovement()
    {
        int currentX = this.x;
        int currentY = this.y;
        //if (currentX == newX && currentY == newY)
        if (currentX < newX-5 || currentX > newX+5 && currentY < newY+5 || currentY > newY-5)
        {
            this.newX = randomNumber.nextInt(Main.processing.width);
            this.newY = randomNumber.nextInt(Main.processing.height);
        }

        if (currentX < newX)
        {
            x = x + movementSpeed;
        }
        if (currentX > newX)
        {
            x = x - movementSpeed;
        }
        if (currentY < newY)
        {
            y = y + movementSpeed;
        }
        if (currentY > newY)
        {
            y = y - movementSpeed;
        }
    }

    public void moveDirection() {

        int a =randomNumber.nextInt(5);

        switch (a){

            case 0:
                if(x < Main.processing.width){
                    this.x += movementSpeed;
                }
                break;
            case 1:
                if ( x > 0){
                    this.x -= movementSpeed;
                }
                break;
            case 2:
                if (y < Main.processing.height -3){
                    this.y += movementSpeed;
                }
                break;
            case 3:
                if (y > 3 ){
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
}
