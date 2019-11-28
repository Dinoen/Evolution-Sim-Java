import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class Rabbit extends Animal {
    PApplet p;
    PVector location;
    PVector velocity;
    PVector acceleration;
    float topspeed;
    int setNewTargetTimerStartTime;
    int setNewTargetTimerDurationTime;
    Random rand;


    public Rabbit(PApplet pApplet, int x, int y) {
        rand = new Random();
        this.p = pApplet;
        this.x = x;
        this.y = y;
        setSizeOfAnimal(50);
        location = new PVector(p.random(p.width), p.random(p.height));
        velocity = new PVector(0, 0);
        topspeed = 5;
        startSetTargetTimer();
    }

    public void startSetTargetTimer() {
        setNewTargetTimerStartTime = p.millis();
        setNewTargetTimerDurationTime = 5000; //make random later
    }


    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public int getSizeOfAnimal() {
        return super.sizeOfAnimal;
    }

    @Override
    public void setSizeOfAnimal(int sizeOfAnimal) {
        this.sizeOfAnimal = sizeOfAnimal;
    }

    @Override
    public int getMovementSpeed() {
        return super.getMovementSpeed();
    }

    @Override
    public void setMovementSpeed(int movementSpeed) {
        super.setMovementSpeed(movementSpeed);
    }

    @Override
    public int getHunger() {
        return super.getHunger();
    }

    @Override
    public void setHunger(int hunger) {
        super.setHunger(hunger);
    }

    @Override
    public int getVisionrange() {
        return super.getVisionrange();
    }

    @Override
    public void setVisionrange(int visionrange) {
        super.setVisionrange(visionrange);
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void setAlive(boolean alive) {
        super.setAlive(alive);
    }

    @Override
    public void setHungery(boolean hungry) {
        super.setHungery(hungry);
    }

    @Override
    public void setThirsty(boolean thirsty) {
        super.setThirsty(thirsty);
    }

    @Override
    public void setRandomNumber(Random randomNumber) {
        super.setRandomNumber(randomNumber);
    }

    @Override
    public boolean isHungry() {
        return super.isHungry();
    }

    @Override
    public boolean isThirsty() {
        return super.isThirsty();
    }

    @Override
    public int getSightDist() {
        return super.getSightDist();
    }

    @Override
    public void setSightDist(int sightDist) {
        super.setSightDist(sightDist);
    }

    @Override
    public int getMAXHunger() {
        return super.getMAXHunger();
    }

    @Override
    public void setMAXHunger(int MAXHunger) {
        super.setMAXHunger(MAXHunger);
    }

    @Override
    public int getMAXThirst() {
        return super.getMAXThirst();
    }

    @Override
    public void setMAXThirst(int MAXThirst) {
        super.setMAXThirst(MAXThirst);
    }

    @Override
    public int getUrgeToReproduce() {
        return super.getUrgeToReproduce();
    }

    @Override
    public void setUrgeToReproduce(int urgeToReproduce) {
        super.setUrgeToReproduce(urgeToReproduce);
    }

    @Override
    public int getMAXUrgeToReproduce() {
        return super.getMAXUrgeToReproduce();
    }

    @Override
    public void setMAXUrgeToReproduce(int MAXUrgeToReproduce) {
        super.setMAXUrgeToReproduce(MAXUrgeToReproduce);
    }

    @Override
    public void EatFood() {
        super.EatFood();
    }//Method for eating

    @Override
    public void DrinkWater() {
        super.DrinkWater();
    }//Method for drinking

    @Override
    public void SearchForFood() {
        super.SearchForFood();
    }

    @Override
    public void SearchForWater() {
        super.SearchForWater();
    }

    @Override
    public int getThirst() {
        return super.getThirst();
    }

    @Override
    public void setThirst(int thirst) {
        super.setThirst(thirst);
    }

    @Override
    public void Movement() {
    PVector randomPosition;
        // Our algorithm for calculating acceleration:
        //PVector mouse = new PVector(Main.p.mouseX,Main.p.mouseY);
        //PVector randomPosition = new PVector(Main.p.random(Main.p.width),(Main.p.random(Main.p.height)));
        if(isSetTargetTimerIsOut()) {
            System.out.println("tid er gået");
            randomPosition = new PVector(p.random(0,800), p.random(0,800));
            //we have not a target yet so we have to make a PVector target. We will set a new target by target.set
            //Call the start timer again. Reset timer
            PVector dir = PVector.sub(randomPosition, this.location);
            dir.normalize();
            dir.mult(0.5f);
            velocity.set(dir);
            startSetTargetTimer();

        }

        //We will have a target instead of a random position. Location - currentposition. Then normalize and scale it.

        //acceleration = dir;

        //velocity.add(dir);
        velocity.limit(topspeed);
        location.add(velocity);
        System.out.println(velocity.x);
        System.out.println(velocity.y);



        /*PVector mouse = new PVector(Main.p.mouseX,Main.p.mouseY);
        PVector dir = PVector.sub(mouse,location);  // Find vector pointing towards mouse
        dir.normalize();     // Normalize
        dir.mult(0.5f);       // Scale
        acceleration = dir;  // Set to acceleration

        // Motion 101!  Velocity changes by acceleration.  Location changes by velocity.
        velocity.add(acceleration);
        velocity.limit(topspeed);
        location.add(velocity);*/

    }//Movement method

    //WE NEED AN UPDATE FUNCTION
    @Override
    public void display() {
        p.stroke(0);
        p.fill(175);
        p.ellipse(this.location.x,this.location.y, 16, 16);

    }//Displaying method

    public void update() {
        Movement();

        this.display();
    }

    public boolean isSetTargetTimerIsOut() {
        int timeElapsed = p.millis() - setNewTargetTimerStartTime;
        return timeElapsed > setNewTargetTimerDurationTime;

    }
}
