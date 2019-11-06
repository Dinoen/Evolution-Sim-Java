import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Rabbit extends Animal {
    PVector location;
    PVector velocity;
    PVector acceleration;
    float topspeed;



    public Rabbit(PApplet pApplet, int x, int y){
        this.x = x;
        this.y = y;
        setSizeOfAnimal(50);
        location = new PVector(Main.p.random(Main.p.width), Main.p.random(Main.p.height));
        velocity = new PVector(0,0);
        topspeed = 4;
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
    public void Movement()
    {
        // Our algorithm for calculating acceleration:
        PVector mouse = new PVector(Main.p.mouseX,Main.p.mouseY);
        PVector dir = PVector.sub(mouse,location);  // Find vector pointing towards mouse
        dir.normalize();     // Normalize
        dir.mult(0.5f);       // Scale
        acceleration = dir;  // Set to acceleration

        // Motion 101!  Velocity changes by acceleration.  Location changes by velocity.
        velocity.add(acceleration);
        velocity.limit(topspeed);
        location.add(velocity);





    }//Movement method


    @Override
    public void draw() {
        Main.p.stroke(0);
        Main.p.fill(175);
        Main.p.ellipse(location.x,location.y,16,16);

    }//Displaying method




    public void copulate(){

    }//Copulating method
}
