import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

// Superclass for animals
public abstract class Animal extends Living {


    protected static final int MAX_HUNGER = 100; // This should most likely be somewhere else

    private static final  int DEFAULT_ANIMAL_CHANGEDIRECTION_TIMER_MIN = 2000;
    private static final  int DEFAULT_ANIMAL_CHANGEDIRECTION_TIMER_MAX = 5000;

    private static final  float DEFAULT_ANIMAL_MOVEMENT_SPEED = 2;
    private static final  float DEFAULT_ANIMAL_TOP_SPEED = 100;


    protected  void setTopSpeed(float value) {
        this.animalTopSpeed = value;
    }

    protected  float getTopSpeed() {
        return animalTopSpeed;
    }





    public void setMovementSpeed(float value) {
        this.movementSpeed = value;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setVisionRange(float value) {
        this.visionRange = value;
    }

    public float getVisionRange() {
        return visionRange;
    }

    protected PVector getVelocity() {
        return velocity;
    }

    protected void setVelocity(PVector value) {
        this.velocity = value;
    }

    public enum AnimalGender {
        FEMALE,
        MALE
    }


    // keyword: protected -> only sub classes can see and change properties
    protected float visionRange;
    protected float hunger; // level of hunger 0-100
    protected float animalTopSpeed = DEFAULT_ANIMAL_TOP_SPEED;
    protected float movementSpeed = DEFAULT_ANIMAL_MOVEMENT_SPEED;

    protected int movingState = 0;


    public AnimalGender Gender;

    private PVector velocity = new PVector(0,0);
    protected PVector acceleration = new PVector(0,0);


    // Controls when this entity randomly change its direction of it has nothing better to do
    protected ActionTimer DirectionTimer = new ActionTimer((int) Main.theEnvironment.p.random(DEFAULT_ANIMAL_CHANGEDIRECTION_TIMER_MIN, DEFAULT_ANIMAL_CHANGEDIRECTION_TIMER_MAX));
    protected ActionTimer FoxEatTimer = new ActionTimer(100);



    // Used by fox who does not have specific gender
    public Animal(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape) {
        super(papplet, id, location, color, size, shape);
    }

    // Used by rabbits and other animals with gender
    public Animal(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape, AnimalGender gender) {
        super(papplet, id, location, color, size, shape);

        Gender = gender;
    }

    // Used for: EnhancedVision Animal Target-acquiring system:  EAT-System (copyright, SFA-group 2019)
    private class Target {

        public final Living CurrentTarget;

        public final float CurrentTargetDistance;

        public Target(Living potentialTarget, float currentDistance) {

            CurrentTarget = potentialTarget;
            CurrentTargetDistance = currentDistance;
        }
    }


    // EAT-System, look around, find nearest interesting target
    public Living EnhancedVision() {

        //create living thing call target
        Target currentTarget = null;

        //run through all lists of entities
        for (int i = 0; i < Main.allEntities.size(); i++) {
            // run through all rabbits
            for (int j = 0; j < Main.allEntities.get(i).getEntities().size(); j++) {
                // checks if the distance is less than the vision range and if the distance is not zero
                if (Main.allEntities.get(i).getEntities().get(j) != null) {

                    Living potentialTarget = Main.allEntities.get(i).getEntities().get(j);

                    float currentDistance = p.dist(this.getLocation().x, this.getLocation().y, potentialTarget.getLocation().x, potentialTarget.getLocation().y);
                    if (currentDistance < this.visionRange && currentDistance != 0.0f) {

                        // Only update our current target if closer!
                        if (currentTarget == null || currentDistance < currentTarget.CurrentTargetDistance) {
                            currentTarget = new Target(potentialTarget, currentDistance);
                        }
                    }
                }
            }
        }

        // ternary
        return (currentTarget != null ? currentTarget.CurrentTarget : null);
    }


    //Getting mommy and daddy genes into the baby rabbit
    //takes two arguments, which is the speed of both parents
    public float reCombinationSpeed(float fatherSpeed, float motherSpeed) {
        //for now it is random, but later it will have a relation for the food / energy expenditure
        float ourRandomNumber = p.random(100);
        //reusing a random, which is being
        //float rand.nextFloat() makes a float from 0-1
        float mutatedSpeed = (p.random(0,50)/100); //random number from 0 to 0.50

        //first 45 % chance 0-44, then just return the female original speed
        if (ourRandomNumber <= 30) {
            return fatherSpeed;
        }

        //next 45% chance of the fathers genes being given to the children
        else if (ourRandomNumber >= 31 && ourRandomNumber <= 60) {
            return motherSpeed;
        }

        //the last 10 percent from original random
        else if (ourRandomNumber >= 61 && ourRandomNumber <= 80) {
            System.out.println("Mutation has happened");
            //make new float called mutatedFathersSpeed
            float mutatedFathersSpeed = fatherSpeed +- mutatedSpeed;
            return mutatedFathersSpeed;
        }
        //Last 5 % of the percent chance from the start of the function
        else if (ourRandomNumber >= 81) {
            System.out.println("Mutation has happened");
            float mutatedMothersSpeed = motherSpeed +- mutatedSpeed;
            return mutatedMothersSpeed;
        } else {
            return reCombinationSpeed(fatherSpeed, motherSpeed);
        }
    }
}
