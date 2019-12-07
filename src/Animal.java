import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

// Superclass for animals
public abstract class Animal extends Living {

    // TODO: Figure out how to handle hunger
    protected static final int MAX_HUNGER = 100; // This should most likely be somewhere else


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
    // TODO: This should most likely be somewhere else
    protected float visionRange;
    protected float hunger;
    protected float animalTopSpeed = 100; // TODO: fix values
    protected float movementSpeed = 2; // TODO: fix vallues
    protected boolean readyForMating = false;


    // How long an animal can keep its focus ie. Duration of its timer
    private int animalFocusDuration = 5000; // TODO: Fix values


    // TODO: Lav enumeration
    protected int movingState = 0;




    public AnimalGender Gender;

    private PVector velocity = new PVector(0,0);
    protected PVector acceleration = new PVector(0,0);


    // Controls when this entity randomly change its direction of it has nothing better to do
    protected ActionTimer DirectionTimer = new ActionTimer((int) Main.theEnvironment.p.random(2000, 5000));



    public Animal(PApplet papplet, int id, Color color) {
        super(papplet, id, color);
    }

    public Animal(PApplet papplet, int id, PVector location,  Color color) {
        super(papplet, id, location, color);
    }

    public Animal(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape) {
        super(papplet, id, location, color, size, shape);
    }

    public Animal(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape, AnimalGender gender) {
        super(papplet, id, location, color, size, shape);

        Gender = gender;
    }






}
