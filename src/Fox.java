import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.Collection;

public class Fox extends Animal {


    protected static final Color DEFAULT_FOX_COLOR = new Color(152,86,41);

    protected static final Dimension DEFAULT_FOX_SIZE = new Dimension(20,20);


//    PApplet p;
//    float topSpeed; //ANIMAL
//    //TIMER CLASS
//    int setNewTargetTimerStartTime;
//    int setNewTargetTimerDurationTime;
//    PVector location; // our current X AND Y //LIVING
//    PVector velocity; // OUR ANGLE //TODO: SHOULD BE MOVED TO ANIMAL
//    PVector acceleration; // GETTING TO OUR SPEED, which is created later on //TODO: SHOULD BE MOVED TO ANIMAL
//    float visionRange; //TODO: MOVED TO ANIMAL


    public Fox(PApplet papplet, int id, PVector location, float movementSpeed, float topSpeed) {
        super(papplet, id, location, DEFAULT_FOX_COLOR, DEFAULT_FOX_SIZE, EntityShape.Triangle);

        this.setMovementSpeed(movementSpeed);
        this.setTopSpeed(topSpeed);
        this.setVisionRange(20);    // TODO: Fox Default Vision Range

    }


    //@Override
    public void wanderingMovement() {
        //newLocation();
        //set topspeed, and this just ties the angle we're going to that MAX speed
        //setting the maximum speed to be moving along the velocity vector
        velocity.limit(animalTopSpeed);
        //adding acceleration
        velocity.add(acceleration);
        //adds the velocity (our angle) to our location
        entityLocation.add(velocity);


        if (this.DirectionTimer.IsDone()) {
            //("5 secs");
            PVector direction = PVector.sub(Main.theEnvironment.RandomLocation(), this.entityLocation);
            //figure it out for later
            //setting the magnitude of the vector to 1 (making it easier to scale)
            direction.normalize();
            //multiplying the movementSpeed with the direction
            direction.mult(this.movementSpeed);
            //set the angle to the same angle as the direction
            velocity.set(direction);

            this.DirectionTimer.Reset((int) p.random(2000, 5000));
        }

        if (this.entityLocation.x >= p.width / 2 + 400 || this.entityLocation.x <= p.width / 2 - 400) {
            //velocity = new PVector(p.width/2 ,p.height/2);
            velocity.rotate(p.HALF_PI);

        }
        if (entityLocation.y >= p.height / 2 + 400 || this.entityLocation.y <= p.height / 2 - 400) {
            velocity.rotate(p.HALF_PI);
        }

        stopWhenSeeingRabbit(vision());
    }

//    private PVector newLocation() {
//        //make new vector
//        PVector newlocation = new PVector();
//        //chose random coordinates from the middle of the screen
//        newlocation.x = p.width / 2 + p.random(-400, 400);
//        newlocation.y = p.height / 2 + p.random(-400, 400);
//        //return the new location
//        return newlocation;
//    }
//    private boolean isSetTargetTimerIsOut() {
//        int timeElapsed = p.millis() - setNewTargetTimerStartTime;
//        return timeElapsed > setNewTargetTimerDurationTime;
//    }
//
//    //function which takes an input of time to run, EG 5000 = 5 SECS
//    private void startSetTargetTimer(int timeToRun) {
//        setNewTargetTimerStartTime = p.millis();
//        setNewTargetTimerDurationTime = timeToRun; //make random later
//    }


    public Living vision() {
        Living target = null;
        for (int i = 0; i < Main.allEntities.size(); i++) {
            // run through all rabbits
            for (int j = 0; j < Main.allEntities.get(i).getEntities().size(); j++) {
                // checks if the distance is less than the vision range and if the distance is not zero
                if (Main.allEntities.get(i).getEntities().get(j) != null) {
                    if (p.dist(this.entityLocation.x, this.entityLocation.y,
                            Main.allEntities.get(i).arrayOfEntities.get(j).getLocation().x
                            , Main.allEntities.get(i).arrayOfEntities.get(j).getLocation().y) < this.visionRange &&
                            p.dist(this.entityLocation.x, this.entityLocation.y, Main.allEntities.get(i).getEntities().get(j).getLocation().x
                                    , Main.allEntities.get(i).getEntities().get(j).getLocation().y) != 0.0f) {
                        //System.out.println("RABBITS CAN SEE");

                        target = Main.allEntities.get(i).getEntities().get(j);
                    }
                }
            }
        }
        return target;
    }

    private void stopWhenSeeingRabbit(Living target) {
        if (target != null) {
            if (target instanceof Rabbit) {
                this.movingState = 1;
            }
        }
    }

    private void huntingRabbit(Living target) {
        if (target != null) {
            if (target instanceof Rabbit) {
                PVector targetVector = PVector.sub(target.entityLocation, this.entityLocation);
                targetVector.normalize();
                targetVector.mult(this.movementSpeed);
                this.velocity.set(targetVector);

                // TODO: Interdasting!
                Main.allEntities.get(0).arrayOfEntities.remove(((Rabbit) target));
                //((Rabbit)target).Kill();

                //this.hunger = this.hunger - 25f;
                this.movingState = 0;
            }
        }
    }

//
//    @Override
//    public void display() {
//
//        p.fill(0);
//        p.triangle(this.location.x, this.location.y, this.location.x + 10, this.location.y + 14, this.location.x - 10, this.location.y + 14);
//
//    }


    @Override
    protected void EntityUpdate(Environment env) {

        switch (movingState) {
            case 0:
                wanderingMovement();

                break;
            case 1:
                huntingRabbit(vision());
                break;
        }

        // TODO: Breaking change - display
        //display();
    }



//        public void update() {
//
//        switch (movingState) {
//            case 0:
//                wanderingMovement();
//
//                break;
//            case 1:
//                huntingRabbit(vision(),this);
//                break;
//        }
//        display();
//    }

//    @Override
//    public float getX() {
//        return super.getX();
//    }
//
//    @Override
//    public void setX(int x) {
//        super.setX(x);
//    }
//
//    @Override
//    public float getY() {
//        return super.getY();
//    }
//
//    @Override
//    public void setY(int y) {
//        super.setY(y);
//    }
//
//    @Override
//    public float getMovementSpeed() {
//        return super.getMovementSpeed();
//    }
//
//    @Override
//    public void setMovementSpeed(int movementSpeed) {
//        super.setMovementSpeed(movementSpeed);
//    }
//
//    @Override
//    public float getHunger() {
//        return super.getHunger();
//    }
//
//    @Override
//    public void setHunger(int hunger) {
//        super.setHunger(hunger);
//    }
//
//    @Override
//    public int getVisionrange() {
//        return super.getVisionrange();
//    }
//
//    @Override
//    public void setVisionrange(int visionrange) {
//        super.setVisionrange(visionrange);
//    }
//
//
//    @Override
//    public int getThirst() {
//        return super.getThirst();
//    }
//
//    @Override
//    public void setThirst(int thirst) {
//        super.setThirst(thirst);
//    }
//
//    @Override
//    public boolean isAlive() {
//        return super.isAlive();
//    }
//
//    @Override
//    public int getSightDist() {
//        return super.getSightDist();
//    }
//
//    @Override
//    public void setSightDist(int sightDist) {
//        super.setSightDist(sightDist);
//    }
//
//
//    @Override
//    public int getMAXHunger() {
//        return super.getMAXHunger();
//    }
//
//    @Override
//    public void setMAXHunger(int MAXHunger) {
//        super.setMAXHunger(MAXHunger);
//    }
//
//    @Override
//    public int getMAXThirst() {
//        return super.getMAXThirst();
//    }
//
//    @Override
//    public void setMAXThirst(int MAXThirst) {
//        super.setMAXThirst(MAXThirst);
//    }
//
//    @Override
//    public int getUrgeToReproduce() {
//        return super.getUrgeToReproduce();
//    }
//
//    @Override
//    public void setUrgeToReproduce(int urgeToReproduce) {
//        super.setUrgeToReproduce(urgeToReproduce);
//    }
//
//    @Override
//    public int getMAXUrgeToReproduce() {
//        return super.getMAXUrgeToReproduce();
//    }
//
//    @Override
//    public void setMAXUrgeToReproduce(int MAXUrgeToReproduce) {
//        super.setMAXUrgeToReproduce(MAXUrgeToReproduce);
//    }
//
//    @Override
//    public void setAlive(boolean alive) {
//        super.setAlive(alive);
//    }
//
//    @Override
//    public void setHungery(boolean hungery) {
//        super.setHungery(hungery);
//    }
//
//    @Override
//    public void setThirsty(boolean thirsty) {
//        super.setThirsty(thirsty);
//    }
//
//    @Override
//    public boolean isHungry() {
//        return super.isHungry();
//    }
//
//    @Override
//    public boolean isThirsty() {
//        return super.isThirsty();
//    }
//
//    @Override
//    public void EatFood() {
//        super.EatFood();
//    }
//
//    @Override
//    public void DrinkWater() {
//        super.DrinkWater();
//    }
//
//    @Override
//    public void SearchForFood() {
//        super.SearchForFood();
//    }
//
//    @Override
//    public void SearchForWater() {
//        super.SearchForWater();
//    }
//
//    public PVector getLocation() {
//        return location;
//    }
}