import processing.core.PApplet;
import processing.core.PVector;
//Used to be our animal superclass
import java.util.Random;

//superclass, holding all the relevant things for living objects, eg rabbits and foxes
class Living {

    //easy access to attributes of all living things.
    public int visionrange;
    public boolean alive;
    public boolean hungry;
    public boolean thirsty;
    public float x;
    public float y;
    public float movementSpeed;
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
    public String gender;
    Random randomNumber = new Random();

    public Living() {

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
    public Living(PApplet pApplet) {

    }

// Getters and setters for the above attributes


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

    public float getMovementSpeed() {
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


    public void wanderingMovement() {//Movement method

    }

    public void EatFood() {//Eating Food method

    }

    public void DrinkWater() {//Drinking Water method

    }

    public void display() {//Displaying method

    }

    public void SearchForFood() {//Method for Searching Food

    }

    public void SearchForWater() {//Method for Searching Water

    }

    //Getting mommy and daddy genes into the baby rabbit
    //takes two arguments, which is the speed of both parents
    public float reCombinationSpeed(float mothersSpeed, float fathersSpeed) {
        //for now it is random, but later it will have a relation for the food / energy expenditure
        //create a random generator
        Random rand = new Random();
        //Make random number from 0-100, so we have a percent chance
        float ourRandomNumber = rand.nextInt(100);

        //first 45 % chance 0-44, then just return the female original speed
        if (ourRandomNumber <= 44) {
            return mothersSpeed;
        }

        //next 45% chance of the fathers genes being given to the children
        if (ourRandomNumber >= 45 && rand.nextInt() <= 89) {
            return fathersSpeed;
        }

        //reusing a random, which is being
        //float rand.nextFloat() makes a float from 0-1
        float mutatedSpeed = rand.nextFloat();
        //the last 10 percent from original random
        if (ourRandomNumber >= 90 && ourRandomNumber <= 94) {
            System.out.println("Mutation has happened");
            //make new float called mutatedFathersSpeed
            float mutatedFathersSpeed = fathersSpeed + mutatedSpeed;
            return mutatedFathersSpeed;
        }
        //Last 5 % of the percent chance from the start of the function
        else {
            System.out.println("Mutation has happened");
            float mutatedMothersSpeed = mothersSpeed + mutatedSpeed;
            return mutatedMothersSpeed;
        }
    }
}