import java.util.Random;

public class Animal {



    private int visionrange;
    private boolean alive = true;
    private boolean hungery = false;
    private boolean thirsty = false;
    private int x = 0;
    private int y = 0;
    private int movementSpeed = 1;
    private int sightDist = 50;
    private int sizeOfAnimal ;
    private int Hunger = 0; // will go from 0 to 100, increasing with activity
    private int MAXHunger = 100;
    private int Thirst = 0;  // will go from 0 to 100, increasing with activity
    private int MAXThirst = 100;
    private int urgeToReproduce = 0;
    private int MAXUrgeToReproduce = 100;
    Random randomNumber = new Random();


    public Animal(int visionrange, boolean alive, boolean hungery, boolean thirsty, int x, int y, int movementSpeed, int sightDist, int sizeOfAnimal, int hunger, int MAXHunger, int thirst, int MAXThirst, int urgeToReproduce, int MAXUrgeToReproduce) {
        this.visionrange = visionrange;
        this.alive = alive;
        this.hungery = hungery;
        this.thirsty = thirsty;
        this.x = x;
        this.y = y;
        this.movementSpeed = movementSpeed;
        this.sightDist = sightDist;
        this.sizeOfAnimal = sizeOfAnimal;
        Hunger = hunger;
        this.MAXHunger = MAXHunger;
        Thirst = thirst;
        this.MAXThirst = MAXThirst;
        this.urgeToReproduce = urgeToReproduce;
        this.MAXUrgeToReproduce = MAXUrgeToReproduce;
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
        this.hungery = hungery;
    }

    public void setThirsty(boolean thirsty) {
        this.thirsty = thirsty;
    }

    public void setRandomNumber(Random randomNumber) {
        this.randomNumber = randomNumber;
    }

    public boolean isHungery() {
        return hungery;
    }

    public boolean isThirsty() {
        return thirsty;
    }



    public void Movement(){//Movement method

    }
    public void EatFood(){//Eating Food method

    }
    public void DrinkWater(){//Drinking Water method

    }
    public void draw(){//Displaying method

    }
    public void SearchForFood(){//Method for Searching Food

    }
    public void SearchForWater(){//Method for Searching Water

    }


}