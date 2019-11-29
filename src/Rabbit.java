import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class Rabbit extends Living {
    PApplet p;
    PVector location;
    PVector velocity;
    PVector acceleration;
    float topSpeed;
    int setNewTargetTimerStartTime;
    int setNewTargetTimerDurationTime;
    Random rand;
    Population fellowRabbits = new Population();
    float visionRange;
    float speed;


    public Rabbit(PApplet pApplet, int x, int y, int ID, boolean readyForMating, float topSpeed, float speed) {
        rand = new Random();
        this.p = pApplet;
        this.x = x;
        this.y = y;
        setSizeOfAnimal(50);
        location = new PVector(p.random(p.width), p.random(p.height));
        velocity = new PVector(0, 0);
        this.topSpeed = topSpeed;
        startSetTargetTimer(5000);
        visionRange = 30f;
        this.typeOfLiving = "Rabbit";
        movingState = 0;
        this.readyForMating = readyForMating;
        this.acceleration = new PVector(velocity.x,velocity.y);
        this.speed = speed;
    }

    public void startSetTargetTimer(int timeToRun) {
        setNewTargetTimerStartTime = p.millis();
        setNewTargetTimerDurationTime = timeToRun; //make random later
    }

    @Override
    public void wanderingMovement() {
        PVector randomPosition;
        // Our algorithm for calculating acceleration:
        //PVector mouse = new PVector(Main.p.mouseX,Main.p.mouseY);
        //PVector randomPosition = new PVector(Main.p.random(Main.p.width),(Main.p.random(Main.p.height)));
        if (isSetTargetTimerIsOut()) {
            System.out.println("5 secs");
            randomPosition = new PVector(p.random(0, 800), p.random(0, 800));
            //we have not a target yet so we have to make a PVector target. We will set a new target by target.set
            //Call the start timer again. Reset timer
            PVector dir = PVector.sub(randomPosition, this.location);
            dir.normalize();
            dir.mult(this.speed);
            velocity.set(dir);
            startSetTargetTimer(5000);

        }

        //We will have a target instead of a random position. Location - currentposition. Then normalize and scale it.

        velocity.limit(topSpeed);
        velocity.add(acceleration);
        location.add(velocity);

        stopWhenSeeingARabbit(vision());

    }//Movement method

    //WE NEED AN UPDATE FUNCTION
    @Override
    public void display() {
        p.stroke(0);
        p.fill(175);
        p.ellipse(this.location.x, this.location.y, 16, 16);

    }//Displaying method

    public void update() {

        switch (movingState) {
            case 0:
                wanderingMovement();
                break;
            case 1:
                matingFunction(vision(), this);
                break;
            case 2:
        }


        testMethod(vision());
        this.display();
    }

    public boolean isSetTargetTimerIsOut() {
        int timeElapsed = p.millis() - setNewTargetTimerStartTime;
        return timeElapsed > setNewTargetTimerDurationTime;
    }

    public boolean isSetTargetTimerIsOutMating() {
        int timeElapsed = p.millis() - setNewTargetTimerStartTime;
        return timeElapsed > setNewTargetTimerDurationTime;
    }


    public Living vision() {
        Living target = null;
        for (int i = 0; i < Main.allEntities.size(); i++) {
            for (int j = 0; j < Main.allEntities.get(i).getPopulation().size(); j++) {
                if (p.dist(this.location.x, this.location.y, Main.allEntities.get(i).getPopulation().get(j).location.x
                        , Main.allEntities.get(i).getPopulation().get(j).location.y) < this.visionRange &&
                        p.dist(this.location.x, this.location.y, Main.allEntities.get(i).getPopulation().get(j).location.x
                                , Main.allEntities.get(i).getPopulation().get(j).location.y) != 0.0f) {
                    System.out.println("RABBITS CAN SEE");
                    target = Main.allEntities.get(i).getPopulation().get(j);


                }
            }
        }
        return target;
    }

    public void testMethod(Living target) {
        if (target != null) {
            if (target.typeOfLiving.equals("Rabbit")) {
                System.out.println("we've found a rabbit");
            }
        }
    }

    public void stopWhenSeeingARabbit(Living target) {
        //if the animals are the same type, stop when getting close.
        if (target != null) {
            if (this.readyForMating && target.readyForMating) {
                if (this.typeOfLiving.equals(target.typeOfLiving)
                        && target.movingState != 1 && this.movingState != 1) {
                    System.out.println("IT IS ANOTHER RABBIT");
                    this.movingState = 1;
                    target.movingState = 1;
                }
            }
        }
    }

    public void matingFunction(Living target, Living mySelf) {
        //get pair of rabbits
        if (target.readyForMating && mySelf.readyForMating) {
            for (int i = 0; i < 2; i++) {
                Main.allEntities.get(0).arrayOfRabbits.add(
                        new Rabbit(p, (int) this.location.x, (int) this.location.y, Population.populationUniqueID, false,3f, 2f));
                Population.populationUniqueID++;
            }
            target.readyForMating = false;
            mySelf.readyForMating = false;
        }
        if (isSetTargetTimerIsOut()) {
            target.movingState = 0;
            mySelf.movingState = 0;
            startSetTargetTimer(2000);
        }
        //mix genes (for speed)
        //spawn two new rabbits
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

}
