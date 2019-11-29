import processing.core.PApplet;

import java.util.Random;

abstract class Living {

    public int visionrange;
    public boolean alive;
    public boolean hungry;
    public boolean thirsty;
    public int x;
    public int y;
    public int movementSpeed;
    public int sightDist;
    public int sizeOfAnimal;
    public int Hunger; // will go from 0 to 100, increasing with activity
    public int MAXHunger;
    public int Thirst;  // will go from 0 to 100, increasing with activity
    public int MAXThirst;
    public int urgeToReproduce;
    public int MAXUrgeToReproduce;
    public String typeOfLiving;
    public int movingState;
    public boolean readyForMating;
    Random randomNumber = new Random();

    public Living(){

    }

   /* public Animal(int visionrange, boolean alive, boolean hungry, boolean thirsty, int x, int y, int movementSpeed, int sightDist, int sizeOfAnimal, int hunger, int MAXHunger, int thirst, int MAXThirst, int urgeToReproduce, int MAXUrgeToReproduce) {
        this.visionrange = visionrange;
        this.alive = alive;
        this.hungry = hungry;
        this.thirsty = thirsty;
        this.x = x;
        this.y = y;
        this.movementSpeed = movementSpeed;
        this.sightDist = sightDist;
        this.sizeOfAnimal = sizeOfAnimal;
        this.Hunger = hunger;
        this.MAXHunger = MAXHunger;
        this.Thirst = thirst;
        this.MAXThirst = MAXThirst;
        this.urgeToReproduce = urgeToReproduce;
        this.MAXUrgeToReproduce = MAXUrgeToReproduce;
    }*/
   public Living (PApplet pApplet){

   }


    public int getSizeOfAnimal() {
        return sizeOfAnimal;
    }

    public void setSizeOfAnimal(int sizeOfAnimal) {
        this.sizeOfAnimal = sizeOfAnimal;
    }

    public int getSightDist() {
        return sightDist;
    }

    public void setSightDist(int sightDist) {
        this.sightDist = sightDist;
    }


    public int getHunger() {
        return Hunger;
    }

    public void setHunger(int hunger) {
        Hunger = hunger;
    }

    public int getMAXHunger() {
        return MAXHunger;
    }

    public void setMAXHunger(int MAXHunger) {
        this.MAXHunger = MAXHunger;
    }

    public int getThirst() {
        return Thirst;
    }

    public void setThirst(int thirst) {
        Thirst = thirst;
    }

    public int getMAXThirst() {
        return MAXThirst;
    }

    public void setMAXThirst(int MAXThirst) {
        this.MAXThirst = MAXThirst;
    }

    public int getUrgeToReproduce() {
        return urgeToReproduce;
    }

    public void setUrgeToReproduce(int urgeToReproduce) {
        this.urgeToReproduce = urgeToReproduce;
    }

    public int getMAXUrgeToReproduce() {
        return MAXUrgeToReproduce;
    }

    public void setMAXUrgeToReproduce(int MAXUrgeToReproduce) {
        this.MAXUrgeToReproduce = MAXUrgeToReproduce;
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

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getVisionrange() {
        return visionrange;
    }

    public void setVisionrange(int visionrange) {
        this.visionrange = visionrange;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setHungery(boolean hungery) {
        this.hungry = hungery;
    }

    public void setThirsty(boolean thirsty) {
        this.thirsty = thirsty;
    }

    public void setRandomNumber(Random randomNumber) {
        this.randomNumber = randomNumber;
    }

    public boolean isHungry() {
        return hungry;
    }

    public boolean isThirsty() {
        return thirsty;
    }



    public void wanderingMovement(){//Movement method

    }
    public void EatFood(){//Eating Food method

    }
    public void DrinkWater(){//Drinking Water method

    }
    public void display(){//Displaying method

    }
    public void SearchForFood(){//Method for Searching Food

    }
    public void SearchForWater(){//Method for Searching Water

    }


}