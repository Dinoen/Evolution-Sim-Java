import processing.core.PApplet;
import processing.core.PVector;
//Used to be our animal superclass
import java.util.Random;

//superclass, holding all the relevant things for living objects, eg rabbits and foxes
class Living {
    PApplet p;
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
    public float hunger; // will go from 0 to 100, increasing with activity
    public int MAXHunger; // 100 at which point they die
    public int Thirst;  // will go from 0 to 100, increasing with activity
    public int MAXThirst;
    public int urgeToReproduce;
    public int MAXUrgeToReproduce;
    public String typeOfLiving;
    public int movingState;
    public boolean readyForMating;
    public String gender;
    boolean isKid;
    Random randomNumber = new Random();
    PVector location;
    int ID;
    int generationCounter;

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


    public float getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
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

    public PVector getLocation() {return location;}

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

    public void update() {

    }

    //Getting mommy and daddy genes into the baby rabbit
    //takes two arguments, which is the speed of both parents
    public float reCombinationSpeed(float fatherSpeed, float motherSpeed) {
        //for now it is random, but later it will have a relation for the food / energy expenditure
        //create a random generator
        Random rand = new Random();
        Random mutateRand = new Random();
        //Make random number from 0-100, so we have a percent chance
        float ourRandomNumber = rand.nextInt(100);
        //reusing a random, which is being
        //float rand.nextFloat() makes a float from 0-1
        float mutatedSpeed = mutateRand.nextFloat();

        //first 45 % chance 0-44, then just return the female original speed
        if (ourRandomNumber <= 44) {
            return fatherSpeed;
        }

        //next 45% chance of the fathers genes being given to the children
        else if (ourRandomNumber >= 45 && rand.nextInt() <= 89) {
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

    public int setGenerationNumber(int fatherGeneration, int motherGeneration) {
        int newGenerationNumber;
        newGenerationNumber = fatherGeneration;
        if (newGenerationNumber < motherGeneration) {
            newGenerationNumber = motherGeneration;
        }
        newGenerationNumber++;

        return newGenerationNumber;
    }

    public int returnLastestRabbitGeneration() {
        int latestGeneration;
        latestGeneration = 0;

        for (int i = 0; i < Main.allEntities.size(); i++) {
            // run through all rabbits
            for (int j = 0; j < Main.allEntities.get(i).getEntitiesRabbits().size(); j++) {
                if (latestGeneration < this.generationCounter) {
                    this.generationCounter = latestGeneration;
                }
            }
        }
        return latestGeneration;
    }
}

