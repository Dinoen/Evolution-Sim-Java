import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import javax.swing.*;
import java.awt.*;
import java.util.RandomAccess;

//Rabbit class
// TODO: Make so the vision range is being passed on like speed gene
// TODO: Make so the amount of children is a gene being passed on

public class Rabbit extends Animal { // Living {

    protected static final Color DEFAULT_RABBIT_COLOR = new Color(255, 255, 255);
    protected static final Dimension DEFAULT_RABBIT_SIZE = new Dimension(15, 15);
    //public static final int  RABBIT_DEFAULT_VISION_RANGE_MIN = 60;//80
    //public static final int  RABBIT_DEFAULT_VISION_RANGE_MAX = 80;//200
    public static final int RABBIT_DEFAULT_VISION_RANGE = 70;
    public static int RABBIT_DEFAULT_STARTING_GENERATION = 0;
    public static final float DEFAULT_RABBIT_LOOK_FOR_FOOD_LEVEL = 40f;
    private static final int DEFAULT_MATING_DURATION = 2000;
    private static final int DEFAULT_RABBIT_MINIMUM_CHILDREN = 0;
    private static final int DEFAULT_RABBIT_MAXIMUM_CHILDREN = 6;
    protected static final Color MaleRabbitColor = new Color(0, 0, 255);
    protected static final Color FemaleRabbitColor = new Color(241, 41, 71);
    protected static final Color BoyRabbitColor = new Color(102, 255, 255);
    protected static final Color GirlRabbitColor = new Color(255, 204, 255);


    private static Color GetRabbitColor(AnimalGender gender, boolean isKid) {
        return (gender == AnimalGender.MALE ?                       //if   what gives then
                (isKid ? BoyRabbitColor : MaleRabbitColor) :        //  if barn then boy else male
                (isKid ? GirlRabbitColor : FemaleRabbitColor));     // else
        //  if barn then girl else female
    }

    public ActionTimer MatingInProgressTimer = new ActionTimer(DEFAULT_MATING_DURATION);
    public ActionTimer RunFromFox = new ActionTimer(100); //TIMER FOR CHECKING AFTER FOXES BEING NEAR

    //TIMER CLASS
    //make ints for timers, in real time
    int setReadyForMatingStartTime;
    int setReadyForMatingDurationTime;
    int setNewHungerTimerStartTime;
    int setNewHungerTimerDurationTime;

    protected int amountOfChildren = (int) p.random(DEFAULT_RABBIT_MINIMUM_CHILDREN, DEFAULT_RABBIT_MAXIMUM_CHILDREN);
    private boolean _iskid;
    public boolean readyForMating; //USED A LOT


    public boolean getIsKid() {
        return _iskid;
    }

    public void setIsKid(boolean value) {
        _iskid = value;
        this.setEntityColor(GetRabbitColor(this.Gender, this.getIsKid()));
    }


    public Rabbit(PApplet papplet, int id, PVector location, AnimalGender gender, boolean readyformating, float topspeed, float movementspeed, boolean iskid, float visionrange, int generationNumber) {
        super(papplet, id, location, GetRabbitColor(gender, iskid), DEFAULT_RABBIT_SIZE, EntityShape.Ellipse, gender);

        readyForMating = readyformating;
        animalTopSpeed = topspeed;
        movementSpeed = movementspeed;
        generationCounter = generationNumber;

        this.setIsKid(iskid);

        if (iskid) {
            this.hunger = 50f;
        }


        visionRange = visionrange;
        movingState = 0;
    }

    //basic movement function
    //@Override
    public void wanderingMovement() {
        seeIfKidIsOldEnoughToBecomeAdult();
        seeIfRabbitIsOldEnoughToDie();
        //get new location
        //set topspeed, and this just ties the angle we're going to that MAX speed
        //setting the maximum speed to be moving along the velocity vector
        getVelocity().limit(animalTopSpeed); // Danger!!

        //adding acceleration
        getVelocity().add(acceleration);
        //adds the velocity (our angle) to our location
        //getLocation().add(getVelocity());
        this.setEntityLocation(PVector.add(getLocation(), getVelocity()));

        if (DirectionTimer.IsDone()) {
            //("5 secs");
            PVector direction = PVector.sub(newLocation(), getLocation());
            //figure it out for later
            //setting the magnitude of the vector to 1 (making it easier to scale)
            direction.normalize();
            //multiplying the movementSpeed with the direction
            direction.mult(this.movementSpeed);
            //set the angle to the same angle as the direction
            getVelocity().set(direction);

            this.DirectionTimer.Reset((int) p.random(2000, 5000));
        }

        if (this.getLocation().x >= p.width / 2 + 400 || this.getLocation().x <= p.width / 2 - 400) {
            //velocity = new PVector(p.width/2 ,p.height/2);
            getVelocity().rotate(p.HALF_PI);

        }
        if (this.getLocation().y >= p.height / 2 + 400 || this.getLocation().y <= p.height / 2 - 400) {
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
        stopWhenSeeingARabbit(EnhancedVision());
        stopWhenSeeingGrass(EnhancedVision());
        turnAwayWhenSeeingFox(EnhancedVision());
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


    public void display2() {

        if (this.hunger > DEFAULT_RABBIT_LOOK_FOR_FOOD_LEVEL) {
            p.stroke(255, 0, 0);
        } else {
            p.stroke(255, 255, 255);
        }


        p.noFill();
        p.ellipse(this.getLocation().x, this.getLocation().y, this.visionRange, this.visionRange);

        p.stroke(0, 0, 0);
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
                matingFunction(EnhancedVision());
                break;
            case 2:
                //run the eating function when vision returns a food target
                //eatingFunction(vision());
                wanderingMovement();
                break;

            case 3:
                // Mating in progress!
                CheckMatingPartner();
                break;


        }

        hungerFunction();
        this.display2();


        // test
        int partnerId = (currentPartnerRabbit != null ? currentPartnerRabbit.getId() : -1);
        this.Caption = String.format("ID: %d, Hunger: %1f, most: %d, partid: %d", this.getId(), this.hunger, this.movingState, partnerId);

    }

    private ActionTimer ReadyForMatingTimer;
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

                            // TEST
//                            this.movingState = 1;
//                            ((Rabbit) target).movingState = 1;

                            // Mate straight away
                            matingFunction(target);

                        }
                    }
                }
            }
        }
    }


    // Check status of mating
    private void CheckMatingPartner() {
        if (this.currentPartnerRabbit == null || MatingInProgressTimer.IsDone()) {
            // Stop Mating
            if (this.currentPartnerRabbit != null) {
                currentPartnerRabbit.movingState = 0;
                currentPartnerRabbit.currentPartnerRabbit = null;
                this.currentPartnerRabbit = null;
            }

            this.movingState = 0;
        }
    }


    public Rabbit currentPartnerRabbit = null;


    //this only runs when we see another rabbit, target is the rabbit we se and myself is the rabbit we are, these are inputs
    public void matingFunction(Living target) {
        //get pair of rabbits
        if (target != null) {
            if (target instanceof Rabbit) { //needs to be a rabbit, because we'll get a null pointer exception because grass has no gender
                if (this.Gender == AnimalGender.MALE && ((Rabbit) target).Gender == AnimalGender.FEMALE) {
                    //check if myself and the target is ready for mating
                    if (((Rabbit) target).readyForMating && this.readyForMating) {

                        // Here we save our mating partner so we can check its status
                        Rabbit currentPartnerRabbit = (Rabbit) target;
                        currentPartnerRabbit.currentPartnerRabbit = this; // ahem

                        //a loop that runs a random amount of times between 0-6 and create the same amount of new rabbits
                        for (int i = 0; i < amountOfChildren; i++) {
                            //will have to check for the array of rabbits insted of 0
                            Main.allEntities.get(0).arrayOfEntities.add(
                                    //Creating a new rabbit
                                    new Rabbit(
                                            p,
                                            Entity.NextGlobalEntityId(),
                                            new PVector(this.getLocation().x + -10, this.getLocation().y + -10),
                                            maleOrFemale(),
                                            false,
                                            this.animalTopSpeed,
                                            reCombinationSpeed(this.movementSpeed, currentPartnerRabbit.movementSpeed),
                                            true,
                                            this.visionRange,
                                            setGenerationNumber(this.generationCounter, currentPartnerRabbit.generationCounter))); //


                        }
                        //change there ready for mating false so they cant mate for 2 sec
                        ((Rabbit) target).readyForMating = false;
                        this.readyForMating = false;
                        //print out the array of rabbit so the new rabbits it counted as well
                        System.out.println(Main.allEntities.get(0).arrayOfEntities.size());


                        //move around again after 2 sec
                        this.movingState = 3;
                        currentPartnerRabbit.movingState = 3;
                        MatingInProgressTimer.Reset();
                        currentPartnerRabbit.MatingInProgressTimer.Reset();

                    }
                }
            }

            // If mating nosuccess-ful, go back to 0
            if (this.movingState != 3) {
                this.movingState = 0;

                if (target != null && target instanceof Rabbit) {
                    ((Rabbit) target).movingState = 0;
                }
            }

//            //move around again after 2 sec
//            if (DirectionTimer.IsDone() /*&& target instanceof Rabbit*/) {
//                ((Rabbit)target).movingState = 3;
//                this.movingState = 3;
//                DirectionTimer.Reset(2000);
//            }

            //mix genes (for speed)
            //spawn two new rabbits
        }
        System.out.println("Generation: " + getLastestRabbitGeneration());
    }

    public void stopWhenSeeingGrass(Living target) {
        if (target != null) {

            if (target instanceof Grass) {
                this.movingState = 2;

                // Eat the grass
                eatingFunction(target);
            }
        }
    }

    public void turnAwayWhenSeeingFox(Living target) {
        if (target != null) {
            if (RunFromFox.IsDone()) {
                if (target instanceof Fox) {
                    getVelocity().rotate(p.HALF_PI);
                    PVector direction = PVector.sub(newLocation(), getLocation());
                    //figure it out for later
                    //setting the magnitude of the vector to 1 (making it easier to scale)
                    direction.normalize();
                    //multiplying the movementSpeed with the direction
                    direction.mult(this.movementSpeed);
                    //set the angle to the same angle as the direction
                    getVelocity().set(direction);
                }
                RunFromFox.Reset();
            }
        }
    }

    public void eatingFunction(Living target) {
        if (target != null) {
            if (this.hunger >= DEFAULT_RABBIT_LOOK_FOR_FOOD_LEVEL) {
                if (target instanceof Grass) {
                    PVector targetVector = PVector.sub(((Grass) target).getLocation(), this.getLocation());
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
        if ((int) Main.theEnvironment.p.random(0, 2) == 1) {
            gender = AnimalGender.MALE;
        } else {
            gender = AnimalGender.FEMALE;
        }
        return gender;
    }

    public static AnimalGender male() {
        AnimalGender gender;
        gender = AnimalGender.MALE;
        return gender;
    }

    public static AnimalGender female() {
        AnimalGender gender;
        gender = AnimalGender.FEMALE;
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
            this.hunger = this.hunger + 3f + this.movementSpeed * 1.2f; //needs to be tied to movementspeed somehow
            startHungerTimer(1000);
            //System.out.println(this.hunger);
        }

        //increase hunger, based on the movemenet speed, with a penalty for moving too fast, exponential curve
        if (this.hunger >= Animal.MAX_HUNGER) {

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
