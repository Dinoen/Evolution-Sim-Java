import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

// TODO: Fix comment
//Used to be our animal superclass


//superclass, holding all the relevant things for living objects, eg rabbits and foxes
public abstract class Living extends Entity {


    //PApplet p;

    //easy access to attributes of all living things.

    protected  final  int birthDayTime = p.millis();
    protected int deathTime = 0;




    //ANIMAL
    //public int visionRange; //NOT USED //SHOULD BE USED
    //public boolean hungry; //NOT USED //SHOULD BE USED
    //public boolean thirsty; //NOT USED
    //public float movementSpeed; //NOT USED //SHOULD BE USED
    //public int sightDist; //NOT USED //SHOULD BE USED
    //public int sizeOfAnimal; //NOT USED
    //public float hunger; // will go from 0 to 100, increasing with activity //NOT USED //SHOULD BE USED
    //public int MAXHunger; // 100 at which point they die //NOT USED //SHOULD BE USED
    //public int Thirst;  // will go from 0 to 100, increasing with activity // NOT USED
    //public int MAXThirst; //NOT USED
    //public int movingState; //USED A LOT
    //int generationCounter; //SHOULD BE USED




    public Living(PApplet papplet, int id, Color color) {
        super(papplet, id, color);
    }

    public Living(PApplet papplet, int id, PVector location,  Color color) {
        super(papplet, id, location, color);
    }

    public Living(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape) {
        super(papplet, id, location, color, size, shape);
    }


    //USED BY BOTH
//    public float x; //NOT USED
//    public float y; //NOT USED
    //public boolean alive; //NOT USED //MAYBE SHOULD BE USED
    //public String typeOfLiving; //USED A LOT //SHOULD NOT RELY ON STRING BUT ON THE OBJECT TYPE
    //PVector location; // USED A LOT
    //int ID; //USED A LOT




//
//// Getters and setters for the above attributes
//
//
//    public int getSizeOfAnimal() {
//        return sizeOfAnimal;
//    }
//
//    public void setSizeOfAnimal(int sizeOfAnimal) {
//        this.sizeOfAnimal = sizeOfAnimal;
//    }
//
//    public int getSightDist() {
//        return sightDist;
//    }
//
//    public void setSightDist(int sightDist) {
//        this.sightDist = sightDist;
//    }
//
//
//    public float getHunger() {
//        return hunger;
//    }
//
//    public void setHunger(int hunger) {
//        this.hunger = hunger;
//    }
//
//    public int getMAXHunger() {
//        return MAXHunger;
//    }
//
//    public void setMAXHunger(int MAXHunger) {
//        this.MAXHunger = MAXHunger;
//    }
//
//    public int getThirst() {
//        return Thirst;
//    }
//
//    public void setThirst(int thirst) {
//        Thirst = thirst;
//    }
//
//    public int getMAXThirst() {
//        return MAXThirst;
//    }
//
//    public void setMAXThirst(int MAXThirst) {
//        this.MAXThirst = MAXThirst;
//    }
//
//    public int getUrgeToReproduce() {
//        return urgeToReproduce;
//    }
//
//    public void setUrgeToReproduce(int urgeToReproduce) {
//        this.urgeToReproduce = urgeToReproduce;
//    }
//
//    public int getMAXUrgeToReproduce() {
//        return MAXUrgeToReproduce;
//    }
//
//    public void setMAXUrgeToReproduce(int MAXUrgeToReproduce) {
//        this.MAXUrgeToReproduce = MAXUrgeToReproduce;
//    }
//
//    public float getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public float getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    public float getMovementSpeed() {
//        return movementSpeed;
//    }
//
//    public void setMovementSpeed(int movementSpeed) {
//        this.movementSpeed = movementSpeed;
//    }
//
//    public int getVisionrange() {
//        return visionRange;
//    }
//
//    public void setVisionrange(int visionrange) {
//        this.visionRange = visionrange;
//    }
//
//    public boolean isAlive() {
//        return alive;
//    }
//
//    public void setAlive(boolean alive) {
//        this.alive = alive;
//    }
//
//    public void setHungery(boolean hungery) {
//        this.hungry = hungery;
//    }
//
//    public void setThirsty(boolean thirsty) {
//        this.thirsty = thirsty;
//    }
//
//    public boolean isHungry() {
//        return hungry;
//    }
//
//    public boolean isThirsty() {
//        return thirsty;
//    }
//
//    public PVector getLocation() {return location;}
//
//    public void wanderingMovement() {//Movement method
//
//    }
//
//    public void EatFood() {//Eating Food method
//
//    }
//
//    public void DrinkWater() {//Drinking Water method
//
//    }
//
//    public void display() {//Displaying method
//
//    }
//
//    public void SearchForFood() {//Method for Searching Food
//
//    }
//
//    public void SearchForWater() {//Method for Searching Water
//
//    }
//
//    public void update() {
//
//    }



    // TODO: Move the mutator function to animals because plants doesnt move. :)
    //Getting mommy and daddy genes into the baby rabbit
    //takes two arguments, which is the speed of both parents
    public float reCombinationSpeed(float fatherSpeed, float motherSpeed) {
        //for now it is random, but later it will have a relation for the food / energy expenditure
        float ourRandomNumber = p.random(100);
        //reusing a random, which is being
        //float rand.nextFloat() makes a float from 0-1
        float mutatedSpeed = (p.random(0,100)/100);

        //first 45 % chance 0-44, then just return the female original speed
        if (ourRandomNumber <= 44) {
            return fatherSpeed;
        }

        //next 45% chance of the fathers genes being given to the children
        else if (ourRandomNumber >= 45 && ourRandomNumber <= 89) {
            return motherSpeed;
        }

        //the last 10 percent from original random
        else if (ourRandomNumber >= 90 && ourRandomNumber <= 94) {
            System.out.println("Mutation has happened");
            //make new float called mutatedFathersSpeed
            float mutatedFathersSpeed = motherSpeed + mutatedSpeed;
            return mutatedFathersSpeed;
        }
        //Last 5 % of the percent chance from the start of the function
        else if (ourRandomNumber >= 95) {
            System.out.println("Mutation has happened");
            float mutatedMothersSpeed = fatherSpeed + mutatedSpeed;
            return mutatedMothersSpeed;
        } else {
            return reCombinationSpeed(fatherSpeed, motherSpeed);
        }
    }


//    public int setGenerationNumber(int fatherGeneration, int motherGeneration) {
//        int newGenerationNumber;
//        newGenerationNumber = fatherGeneration;
//        if (newGenerationNumber < motherGeneration) {
//            newGenerationNumber = motherGeneration;
//        }
//        newGenerationNumber++;
//
//        return newGenerationNumber;
//    }
//
//    public int returnLastestRabbitGeneration() {
//        int latestGeneration;
//        latestGeneration = 0;
//
//        for (int i = 0; i < Main.allEntities.size(); i++) {
//            // run through all rabbits
//            for (int j = 0; j < Main.allEntities.get(i).getEntities().size(); j++) {
//                if (latestGeneration < this.generationCounter) {
//                    this.generationCounter = latestGeneration;
//                }
//            }
//        }
//        return latestGeneration;
//    }
}

