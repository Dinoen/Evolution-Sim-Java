import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class Fox extends Living {

    PApplet p;

    float topSpeed;
    int setNewTargetTimerStartTime;
    int setNewTargetTimerDurationTime;
    PVector location; // our current X AND Y
    PVector velocity; // OUR ANGLE
    PVector acceleration; // GETTING TO OUR SPEED, which is created later on

    public Fox(PApplet pApplet, float x, float y) {
        p = pApplet;
        this.x = x;
        this.y = y;
    }

    public void wanderingMovement() {
        newLocation();
        //set topspeed, and this just ties the angle we're going to that MAX speed
        //setting the maximum speed to be moving along the velocity vector
        velocity.limit(topSpeed);
        //adding acceleration
        velocity.add(acceleration);
        //adds the velocity (our angle) to our location
        location.add(velocity);

        if (isSetTargetTimerIsOut()) {
            //("5 secs");
            PVector direction = PVector.sub(newLocation(), this.location);
            //figure it out for later
            //setting the magnitude of the vector to 1 (making it easier to scale)
            direction.normalize();
            //multiplying the movementSpeed with the direction
            direction.mult(this.movementSpeed);
            //set the angle to the same angle as the direction
            velocity.set(direction);

            startSetTargetTimer((int) p.random(2000, 5000));
        }

        if (this.location.x >= p.width / 2 + 400 || this.location.x <= p.width / 2 - 400) {
            //velocity = new PVector(p.width/2 ,p.height/2);
            velocity.rotate(p.HALF_PI);

        }
        if (this.location.y >= p.height / 2 + 400 || this.location.y <= p.height / 2 - 400) {
            velocity.rotate(p.HALF_PI);

        }
    }

    public boolean isSetTargetTimerIsOut() {
        int timeElapsed = p.millis() - setNewTargetTimerStartTime;
        return timeElapsed > setNewTargetTimerDurationTime;
    }

    //function which takes an input of time to run, EG 5000 = 5 SECS
    public void startSetTargetTimer(int timeToRun) {
        setNewTargetTimerStartTime = p.millis();
        setNewTargetTimerDurationTime = timeToRun; //make random later
    }

    public PVector newLocation() {
        //make new vector
        PVector newlocation = new PVector();
        //chose random coordinates from the middle of the screen
        newlocation.x = p.width / 2 + p.random(-400, 400);
        newlocation.y = p.height / 2 + p.random(-400, 400);
        //return the new location
        return newlocation;
    }

    @Override
    public void display(){
        p.triangle(this.location.x,this.location.y,this.location.x+10,this.location.y+10,this.location.x-10,this.location.y-10);
    }


    public void update() {
        display();
    }


    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public float getY() {
        return super.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public float getMovementSpeed() {
        return super.getMovementSpeed();
    }

    @Override
    public void setMovementSpeed(int movementSpeed) {
        super.setMovementSpeed(movementSpeed);
    }

    @Override
    public float getHunger() {
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
    public int getThirst() {
        return super.getThirst();
    }

    @Override
    public void setThirst(int thirst) {
        super.setThirst(thirst);
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
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
    public void setAlive(boolean alive) {
        super.setAlive(alive);
    }

    @Override
    public void setHungery(boolean hungery) {
        super.setHungery(hungery);
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
    public void EatFood() {
        super.EatFood();
    }

    @Override
    public void DrinkWater() {
        super.DrinkWater();
    }

    @Override
    public void SearchForFood() {
        super.SearchForFood();
    }

    @Override
    public void SearchForWater() {
        super.SearchForWater();
    }
}