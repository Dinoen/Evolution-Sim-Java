import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.Random;

//Rabbit class
public class Rabbit extends Living {

    //papplet creation
    PApplet p;
    //make 3 PVectors
    PVector location; // our current X AND Y
    PVector velocity; // OUR ANGLE
    PVector acceleration; // GETTING TO OUR SPEED, which is created later on
    //create a float controlling topspeed
    float topSpeed;
    //make ints for timers, in real time
    int setNewTargetTimerStartTime;
    int setNewTargetTimerDurationTime;
    //make random for random number creation, for positional stuff
    Random rand;
    //visionRange distance the rabbits can see
    float visionRange;

    //constructor, taking all of the inputs it needs to create a new rabbit
    public Rabbit(PApplet pApplet, float x, float y, int ID, boolean readyForMating, float topSpeed, float movementSpeed, String gender) {
        rand = new Random();
        this.p = pApplet;
        //making it so the incoming X AND Y is tied to the rabbit instance
        this.x = x;
        this.y = y;
        //size of animal is the size of the cicle when we draw the rabbits, should be changable later
        setSizeOfAnimal(50);
        //take the X AND Y input, and puts it in a vector
        location = new PVector(x, y);
        //angle is nothing to start out with.
        velocity = new PVector(0, 0);
        // limit the amount of speed the rabbits can achieve
        this.topSpeed = topSpeed;
        //make a timer, with 5 sec of run, standard timer
        startSetTargetTimer(5000);
        //distance the rabbits can see from standard
        visionRange = 30f;
        //defining the type of living, in this case rabbit, for creating different entities in the entities class
        this.typeOfLiving = "Rabbit";
        //This is the start of the movement queue/system, which needs to work together with the priority system later
        movingState = 0;
        //seeing if the animal is ready for mating
        this.readyForMating = readyForMating;
        //make the movement work, with the acceleration, norm of how we learned to use Vectors in processing
        this.acceleration = new PVector(velocity.x, velocity.y);
        //movement speed the speed of the animal, the default speed
        this.movementSpeed = movementSpeed;
        //recieving the gender from input
        this.gender = gender;
    }

    //function which takes an input of time to run, EG 5000 = 5 SECS
    public void startSetTargetTimer(int timeToRun) {
        setNewTargetTimerStartTime = p.millis();
        setNewTargetTimerDurationTime = timeToRun; //make random later
    }

    //basic movement function
    @Override
    public void wanderingMovement() {
        //get new location
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


        //We will have a target instead of a random position. Location - currentposition. Then normalize and scale it.
        stopWhenSeeingARabbit(vision());
        stopWhenSeeingGrass(vision());

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

    //WE NEED AN UPDATE FUNCTION
    @Override
    public void display() {
        p.ellipseMode(PConstants.CENTER);
        p.stroke(0);

        if (gender.equals("Male")) {
            p.fill(0, 0, 255);
        }
        if (gender.equals("Female")) {
            p.fill(255, 0, 0);
        }

        p.ellipse(this.location.x, this.location.y, 16, 16);

    }//Displaying method

    public void update() {
wanderingMovement();



        switch (movingState) {
            case 0:
                wanderingMovement();
                break;
            case 1:
                matingFunction(vision(), this);
                break;
            case 2:
                eatingFunction(vision(), this);
                wanderingMovement();

        }

        testMethod(vision());
        this.display();
    }

    public boolean isSetTargetTimerIsOut() {
        int timeElapsed = p.millis() - setNewTargetTimerStartTime;
        return timeElapsed > setNewTargetTimerDurationTime;
    }


    public Living vision() {
        Living target = null;

        for (int i = 0; i < Main.allEntities.size(); i++) {
            for (int j = 0; j < Main.allEntities.get(i).getEntitiesRabbits().size(); j++) {
                if (p.dist(this.location.x, this.location.y, Main.allEntities.get(i).getEntitiesRabbits().get(j).location.x
                        , Main.allEntities.get(i).getEntitiesRabbits().get(j).location.y) < this.visionRange &&
                        p.dist(this.location.x, this.location.y, Main.allEntities.get(i).getEntitiesRabbits().get(j).location.x
                                , Main.allEntities.get(i).getEntitiesRabbits().get(j).location.y) != 0.0f) {
                    //System.out.println("RABBITS CAN SEE");
                    target = Main.allEntities.get(i).getEntitiesRabbits().get(j);


                }
            }

            for (int j = 0; j < Main.allEntities.get(i).getEntitiesGrass().size(); j++) {
                if (p.dist(this.location.x, this.location.y, Main.allEntities.get(i).getEntitiesGrass().get(j).location.x
                        , Main.allEntities.get(i).getEntitiesGrass().get(j).location.y) < this.visionRange &&
                        p.dist(this.location.x, this.location.y, Main.allEntities.get(i).getEntitiesGrass().get(j).location.x
                                , Main.allEntities.get(i).getEntitiesGrass().get(j).location.y) != 0.0f) {
                    //System.out.println("RABBITS CAN SEE");
                    target = Main.allEntities.get(i).getEntitiesGrass().get(j);

                }
            }
        }
        return target;
    }

    public void testMethod(Living target) {
        if (target != null) {
            if (target.typeOfLiving.equals("Rabbit")) {
                //System.out.println("we've found a rabbit");
            }
        }
    }

    public void stopWhenSeeingARabbit(Living target) {
        //if the animals are the same type, stop when getting close.

        if (!gender.equals(this.gender)) {
            if (target != null) {
                if (this.readyForMating && target.readyForMating) {
                    if (this.typeOfLiving.equals(target.typeOfLiving)
                            && target.movingState != 1 && this.movingState != 1) {
                        //System.out.println("IT IS ANOTHER RABBIT");
                        this.movingState = 1;
                        target.movingState = 1;
                    }
                }
            }
        }
    }

    public void matingFunction(Living target, Living mySelf) {
        //get pair of rabbits
        if (mySelf.gender.equals("Female")) {
            if (target.readyForMating && mySelf.readyForMating) {
                for (int i = 0; i < 2; i++) {
                    Main.allEntities.get(0).arrayOfRabbits.add(
                            new Rabbit(p, (int) this.location.x, (int) this.location.y,
                                    Entities.entityUniqueID, false, this.topSpeed,
                                    reCombinationSpeed(mySelf.movementSpeed, target.movementSpeed), maleOrFemale()));
                    Entities.entityUniqueID++;
                }
                target.readyForMating = false;
                mySelf.readyForMating = false;
                System.out.println(Main.allEntities.get(0).arrayOfRabbits.size());
            }
        }
        if (isSetTargetTimerIsOut()) {
            target.movingState = 0;
            mySelf.movingState = 0;
            startSetTargetTimer(2000);
        }
        //mix genes (for speed)
        //spawn two new rabbits
    }

    public void stopWhenSeeingGrass(Living target) {
        if(target != null) {
            if (target.typeOfLiving.equals("Grass")) {
                this.movingState = 2;
                target.movingState = 2;
            }
        }
    }

    public void eatingFunction(Living target, Living mySelf) {
        if (target != null) {
            if (target.typeOfLiving.equals("Grass")) {

                PVector targetVector = PVector.sub(((Grass) target).location, ((Rabbit) mySelf).location);

                targetVector.normalize();
                targetVector.mult(mySelf.movementSpeed);
                ((Rabbit) mySelf).velocity.set(targetVector);
                //if()
                Main.allEntities.get(1).arrayOfGrass.remove(((Grass) target));

            }
        }
    }
        //quick function which spits out either a string "Male" or "Female"
        public String maleOrFemale () {
            String gender;
            Random rand = new Random();
            if (rand.nextInt(1) == 0) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            return gender;
        }
        @Override
        public float getX () {
            return super.getX();
        }

        @Override
        public void setX ( int x){
            super.setX(x);
        }

        @Override
        public float getY () {
            return super.getY();
        }

        @Override
        public void setY ( int y){
            super.setY(y);
        }

        @Override
        public int getSizeOfAnimal () {
            return super.sizeOfAnimal;
        }

        @Override
        public void setSizeOfAnimal ( int sizeOfAnimal){
            this.sizeOfAnimal = sizeOfAnimal;
        }

        @Override
        public float getMovementSpeed () {
            return super.getMovementSpeed();
        }

        @Override
        public void setMovementSpeed ( int movementSpeed){
            super.setMovementSpeed(movementSpeed);
        }

        @Override
        public int getHunger () {
            return super.getHunger();
        }

        @Override
        public void setHunger ( int hunger){
            super.setHunger(hunger);
        }

        @Override
        public int getVisionrange () {
            return super.getVisionrange();
        }

        @Override
        public void setVisionrange ( int visionrange){
            super.setVisionrange(visionrange);
        }

        @Override
        public boolean isAlive () {
            return super.isAlive();
        }

        @Override
        public void setAlive ( boolean alive){
            super.setAlive(alive);
        }

        @Override
        public void setHungery ( boolean hungry){
            super.setHungery(hungry);
        }

        @Override
        public void setThirsty ( boolean thirsty){
            super.setThirsty(thirsty);
        }

        @Override
        public void setRandomNumber (Random randomNumber){
            super.setRandomNumber(randomNumber);
        }

        @Override
        public boolean isHungry () {
            return super.isHungry();
        }

        @Override
        public boolean isThirsty () {
            return super.isThirsty();
        }

        @Override
        public int getSightDist () {
            return super.getSightDist();
        }

        @Override
        public void setSightDist ( int sightDist){
            super.setSightDist(sightDist);
        }

        @Override
        public int getMAXHunger () {
            return super.getMAXHunger();
        }

        @Override
        public void setMAXHunger ( int MAXHunger){
            super.setMAXHunger(MAXHunger);
        }

        @Override
        public int getMAXThirst () {
            return super.getMAXThirst();
        }

        @Override
        public void setMAXThirst ( int MAXThirst){
            super.setMAXThirst(MAXThirst);
        }

        @Override
        public int getUrgeToReproduce () {
            return super.getUrgeToReproduce();
        }

        @Override
        public void setUrgeToReproduce ( int urgeToReproduce){
            super.setUrgeToReproduce(urgeToReproduce);
        }

        @Override
        public int getMAXUrgeToReproduce () {
            return super.getMAXUrgeToReproduce();
        }

        @Override
        public void setMAXUrgeToReproduce ( int MAXUrgeToReproduce){
            super.setMAXUrgeToReproduce(MAXUrgeToReproduce);
        }

        @Override
        public void EatFood () {
            super.EatFood();
        }//Method for eating

        @Override
        public void DrinkWater () {
            super.DrinkWater();
        }//Method for drinking

        @Override
        public void SearchForFood () {
            super.SearchForFood();
        }

        @Override
        public void SearchForWater () {
            super.SearchForWater();
        }

        @Override
        public int getThirst () {
            return super.getThirst();
        }

        @Override
        public void setThirst ( int thirst){
            super.setThirst(thirst);
        }

    }
