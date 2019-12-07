import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.awt.*;
import java.util.RandomAccess;

//Rabbit class
public class Rabbit extends Animal { // Living {

    protected static final Color DEFAULT_RABBIT_COLOR = new Color(255,255,255);

    protected static final Dimension DEFAULT_RABBIT_SIZE = new Dimension(15,15);

    public static final int  RABBIT_DEFAULT_VISION_RANGE_MIN = 80;
    public static final int  RABBIT_DEFAULT_VISION_RANGE_MAX = 200;



    protected static final Color MaleRabbitColor = new Color(0,0,255);
    protected static final Color FemaleRabbitColor = new Color(241,41,71);
    protected static final Color BoyRabbitColor = new Color(102,255,255);
    protected static final Color GirlRabbitColor = new Color(255,204,255);

    private static Color GetRabbitColor(AnimalGender gender, boolean isKid) {
        return (gender == AnimalGender.MALE ?                       //if   what gives then
                (isKid ? BoyRabbitColor : MaleRabbitColor) :        //  if barn then boy else male
                (isKid ? GirlRabbitColor : FemaleRabbitColor));     // else
                                                                    //  if barn then girl else female
    }

    //TIMER CLASS
    //make ints for timers, in real time
    int setReadyForMatingStartTime;
    int setReadyForMatingDurationTime;
    int setNewHungerTimerStartTime;
    int setNewHungerTimerDurationTime;

    protected int amountOfChildren = (int)p.random(0,6); //SPECIFIC FOR RABBIT TODO: make this proper


    // TODO: Move to Rabbit
    //RABBIT SPECIFIC
    //public String gender; //USED A LOT //SHOULD BE ENUM
    private boolean _iskid;

    public boolean readyForMating; //USED A LOT
    public int urgeToReproduce; //NOT USED //SHOULD BE USED
    public int MAXUrgeToReproduce; //NOT USED


    public boolean getIsKid() {
        return _iskid;
    }

    public void setIsKid(boolean value) {
        _iskid = value;
        this.setEntityColor(GetRabbitColor(this.Gender, this.getIsKid()));
    }



    public Rabbit(PApplet papplet, int id, PVector location, AnimalGender gender,  boolean readyformating, float topspeed, float movementspeed,  boolean iskid, float visionrange) {
        super(papplet, id, location, GetRabbitColor(gender, iskid), DEFAULT_RABBIT_SIZE, EntityShape.Ellipse, gender);

        readyForMating = readyformating;
        animalTopSpeed = topspeed;
        movementSpeed = movementspeed;
        this.setIsKid(iskid);
        visionRange = visionrange;

        movingState = 0;
    }



    //basic movement function
    //@Override
    public void wanderingMovement() {
        seeIfKidIsOldEnoughToBecomeAdult();
        seeIfRabbitIsOldEnoughToDie();
        //get new location
        //newLocation();
        //set topspeed, and this just ties the angle we're going to that MAX speed
        //setting the maximum speed to be moving along the velocity vector
        getVelocity().limit(animalTopSpeed); // Danger!!

        //adding acceleration
        getVelocity().add(acceleration);
        //adds the velocity (our angle) to our location
        entityLocation.add(getVelocity());

        if (DirectionTimer.IsDone()) {
            //("5 secs");
            PVector direction = PVector.sub(newLocation(), entityLocation);
            //figure it out for later
            //setting the magnitude of the vector to 1 (making it easier to scale)
            direction.normalize();
            //multiplying the movementSpeed with the direction
            direction.mult(this.movementSpeed);
            //set the angle to the same angle as the direction
            getVelocity().set(direction);

            this.DirectionTimer.Reset((int) p.random(2000, 5000));
        }

        if (this.entityLocation.x >= p.width / 2 + 400 || this.entityLocation.x <= p.width / 2 - 400) {
            //velocity = new PVector(p.width/2 ,p.height/2);
            getVelocity().rotate(p.HALF_PI);

        }
        if (this.entityLocation.y >= p.height / 2 + 400 || this.entityLocation.y <= p.height / 2 - 400) {
            getVelocity().rotate(p.HALF_PI);

        }
        //make the adults have sex again
        if (isReadyForMatingAgain()) {
            if (!this.getIsKid()) {
                this.readyForMating = true;
            }
            startMatingTimerForRabbit(10000);
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
//
//    //WE NEED AN UPDATE FUNCTION
//    @Override
//    public void display() {
//        p.ellipseMode(PConstants.CENTER);
//        p.stroke(0);
//
//        if (gender.equals("Male")) {
//            p.fill(0, 0, 255);
//        }
//        if (gender.equals("Female")) {
//            p.fill(255, 0, 0);
//        }
//        if (gender.equals("Male") && isKid) {
//            p.fill(102, 255, 255);
//        }
//        if (gender.equals("Female") && isKid) {
//            p.fill(255, 204, 255);
//        }
//
//        p.ellipse(this.location.x, this.location.y, 16, 16);
//
//    }//Displaying method


    // TODO: Remember Vision Circle!!!! Generalize to Display
    public void display2() {
        p.noFill();
        p.ellipse(this.entityLocation.x, this.entityLocation.y, this.visionRange, this.visionRange);
    }

    @Override
    protected void EntityUpdate(Environment env) {




        // a switch case that control behavier
        switch (movingState) {
            //just walking around
            case 0:
                wanderingMovement();
                break;
            //run mating function
            case 1:
                matingFunction(vision());
                break;
            case 2:
                //run the eating function when vision returns a food target
                eatingFunction(vision());
                wanderingMovement();
                break;

        }
//        display();
        hungerFunction();
        this.display2();
    }

//    public void update() {
//        // a switch case that control behavier
//        switch (movingState) {
//            //just walking around
//            case 0:
//                wanderingMovement();
//                break;
//            //run mating function
//            case 1:
//                matingFunction(vision(), this);
//                break;
//            case 2:
//                //run the eating function when vision returns a food target
//                eatingFunction(vision(), this);
//                wanderingMovement();
//                break;
//
//        }
//        display();
//        hungerFunction();
//        this.display2();
//    }

//    public boolean isSetTargetTimerIsOut() {
//        int timeElapsed = p.millis() - setNewTargetTimerStartTime;
//        return timeElapsed > setNewTargetTimerDurationTime;
//    }
//
//    //function which takes an input of time to run, EG 5000 = 5 SECS
//    public void startSetTargetTimer(int timeToRun) {
//        setNewTargetTimerStartTime = p.millis();
//        setNewTargetTimerDurationTime = timeToRun; //make random later
//    }



    private  ActionTimer ReadyForMatingTimer;
    private ActionTimer HungerTimer;


    public boolean isReadyForMatingAgain() {
        int timeElapsed = p.millis() - setReadyForMatingStartTime;
        return timeElapsed > setReadyForMatingDurationTime;
    }

    public void startMatingTimerForRabbit(int timeToRun) {
        setReadyForMatingStartTime = p.millis();
        setReadyForMatingDurationTime = timeToRun; //make random later
    }

    public boolean isHungerTimerOut() {
        int timeElapsed = p.millis() - setNewHungerTimerStartTime;
        return timeElapsed > setNewHungerTimerDurationTime;
    }

    public void startHungerTimer(int timeToRun) {
        setNewHungerTimerStartTime = p.millis();
        setNewHungerTimerDurationTime = timeToRun; //make random later
    }


    public Living vision() {
        //System.out.println(Main.allEntities.get(1).getEntities().ge);
        //create living thing call target
        Living target = null;
        //run through all lists of entities
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
        if (target != null) {
            //System.out.println(target.typeOfLiving);
        }
        return target;
    }


    public void stopWhenSeeingARabbit(Living target) {
        if (target != null) {
            if (target instanceof Rabbit) {
                //if the animals are the same type and gender, stop when getting close.
                if (this.Gender != ((Rabbit) target).Gender) {
                    //if they are both ready for mating
                    if (this.readyForMating && ((Rabbit) target).readyForMating) {
                        //and they are both the same type of living(rabbits) and are not mating
                        if ( /*this.typeOfLiving.equals(target.typeOfLiving) &&*/ ((Rabbit) target).movingState != 1 && this.movingState != 1) {
                            //System.out.println("IT IS ANOTHER RABBIT");
                            //if the number is one it is mating if it is 0 it is just wandering around
                            this.movingState = 1;
                            ((Rabbit) target).movingState = 1;
                        }
                    }
                }
            }
        }
    }

    //this only runs when we see another rabbit, target is the rabbit we se and myself is the rabbit we are, these are inputs
    public void matingFunction(Living target) {
        //get pair of rabbits
        if (target != null) {
            if (target instanceof Rabbit) { //needs to be a rabbit, because we'll get a null pointer exception because grass has no gender

                Rabbit targetRabbit = (Rabbit)target;

                if (this.Gender == AnimalGender.MALE && targetRabbit.Gender == AnimalGender.FEMALE) {
                    //check if myself and the target is ready for mating
                    if (targetRabbit.readyForMating && this.readyForMating) {
                        //a loop that runs a random amount of times between 0-6 and create the same amount of new rabbits
                        for (int i = 0; i < amountOfChildren; i++) {
                            //will have to check for the array of rabbits insted of 0
                            Main.allEntities.get(0).arrayOfEntities.add(
                                    //creatig a new rabbit
                                    new Rabbit(
                                            p,
                                            Entity.NextGlobalEntityId(),
                                            new PVector(this.entityLocation.x + -10,this.entityLocation.y + -10),
                                            maleOrFemale(),
                                            false,
                                            this.animalTopSpeed,
                                            reCombinationSpeed(this.movementSpeed, targetRabbit.movementSpeed),
                                            true,
                                            this.visionRange)); // TODO: Change to both parents vision range

                            //iterate the unique id
                            //Entities.entityUniqueID++;
                        }
                        //change there ready for mating false so they cant mate for 2 sec
                        ((Rabbit)target).readyForMating = false;
                        this.readyForMating = false;
                        //print out the array of rabbit so the new rabbits it counted as well
                        System.out.println(Main.allEntities.get(0).arrayOfEntities.size());

                        //System.out.println(this.generationCounter);
                    }
                }
            }

            //move around again after 2 sec
            //if (isSetTargetTimerIsOut()) {
            if (DirectionTimer.IsDone() && target instanceof Rabbit) {
                ((Rabbit)target).movingState = 0;
                this.movingState = 0;
                DirectionTimer.Reset(2000);
            }

            //mix genes (for speed)
            //spawn two new rabbits
        }
    }

    public void stopWhenSeeingGrass(Living target) {
        if (target != null) {

            if (target instanceof Grass) {
                this.movingState = 2;
                //((Grass)target).movingState = 2;
            }
        }
    }

    public void eatingFunction(Living target) {
        if (target != null) {
            if (this.hunger >= 60f) {
                if ( target instanceof Grass) {
                    PVector targetVector = PVector.sub(((Grass) target).entityLocation, this.entityLocation);
                    targetVector.normalize();
                    targetVector.mult(this.movementSpeed);
                    this.getVelocity().set(targetVector);
                    //if()

                    Main.allEntities.get(1).arrayOfEntities.remove(((Grass) target));


                    this.hunger = this.hunger - 25f;
                }
            }
        }
    }

    //quick function which spits out either a string "Male" or "Female"
    public static AnimalGender maleOrFemale() {
        AnimalGender gender;
        if ((int)Main.theEnvironment.p.random(0,2) == 1) {
            gender = AnimalGender.MALE;
        } else {
            gender = AnimalGender.FEMALE;
        }
        return gender;
    }
    //
    public void seeIfKidIsOldEnoughToBecomeAdult() {
        // timesincebirth is the start of the kids life, and if 10 seconds have elapsed then the kids become adults
        if (this.birthDayTime + 10000 < p.millis()) {
            this.setIsKid(false);
        }
    }

    //find this. in the allEntities array
    //compare the found rabbit.ID to this.ID
    //Add the found rabbit to a new "heaven" array
    //remove the found rabbit from ArrayOfRabbits
    public void seeIfRabbitIsOldEnoughToDie() {
        if (this.birthDayTime + p.random(40000, 45000) < p.millis()) {
            for (int i = 0; i < Main.allEntities.size(); i++) {
                for (int j = 0; j < Main.allEntities.get(i).getEntities().size(); j++) {
                    //if the currently looked at rabbits id is equal to the current rabbits id
                    if (Main.allEntities.get(i).getEntities().get(j).getId() == this.getId()) {
                        //then add the current rabbit to the array of dead rabbits in the deadEntities ArrayLists Of Arraylists
                        //and then remove it so it will appear dead
                        //and then remove it so it will appear dead
                        Living x = Main.allEntities.get(i).getEntities().get(j);
                        Main.allEntities.get(i).getEntities().remove(j);
                        Main.allDeadEntities.get(0).arrayOfEntities.add(x);
                        System.out.println(Main.allDeadEntities.get(0).arrayOfEntities.size());
                    }
                }
            }
        }
    }

    public void hungerFunction() {
        //set timer 1 sek
        if (isHungerTimerOut()) {
            this.hunger = this.hunger + 2f * this.movementSpeed * 1.5f; //needs to be tied to movementspeed somehow
            startHungerTimer(1000);
            //System.out.println(this.hunger);
        }

        //increase hunger, based on the movemenet speed, with a penalty for moving too fast, exponential curve
        if (this.hunger >= 100) {

            for (int j = 0; j < Main.allEntities.get(0).getEntities().size(); j++) {
                //if the currently looked at rabbits id is equal to the current rabbits id
                if (Main.allEntities.get(0).getEntities().get(j).getId() == this.getId()) {
                    //then add the current rabbit to the array of dead rabbits in the deadEntities ArrayLists Of Arraylists
                    //and then remove it so it will appear dead
                    Living x = Main.allEntities.get(0).getEntities().get(j);
                    Main.allEntities.get(0).getEntities().remove(j);

                    Main.allDeadEntities.get(0).arrayOfEntities.add(x);
                    System.out.println("Rabbit" + this.getId() + "Died of hunger");
                }
            }


        }
        //if 100 hunger, move to heaven array
        //if food is eaten decrease hunger by certain amount EG 10

    }


}
