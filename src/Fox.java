import processing.core.PApplet;
import processing.core.PVector;
import java.awt.*;

public class Fox extends Animal {

    protected static final Color DEFAULT_FOX_COLOR = new Color(152, 86, 41);
    protected static final Dimension DEFAULT_FOX_SIZE = new Dimension(20, 20);
    private static final float DEFAULT_FOX_VISION_RANGE = 30;

    public Fox(PApplet papplet, int id, PVector location, float movementSpeed, float topSpeed) {
        super(papplet, id, location, DEFAULT_FOX_COLOR, DEFAULT_FOX_SIZE, EntityShape.Triangle);
        this.setMovementSpeed(movementSpeed);
        this.setTopSpeed(topSpeed);
        this.setVisionRange(DEFAULT_FOX_VISION_RANGE);
    }

    public void wanderingMovement() {
        //setting the maximum speed to be moving along the velocity vector
        getVelocity().limit(animalTopSpeed);
        //adding acceleration
        getVelocity().add(acceleration);
        //adds the velocity (our angle) to our location
        this.setEntityLocation(PVector.add(getLocation(), getVelocity()));
        if (this.DirectionTimer.IsDone()) {
            PVector direction = PVector.sub(Main.theEnvironment.RandomLocation(), this.getLocation());
            //setting the magnitude of the vector to 1 (making it easier to scale)
            direction.normalize();
            //multiplying the movementSpeed with the direction
            direction.mult(this.movementSpeed);
            //set the angle to the same angle as the direction
            getVelocity().set(direction);
            this.DirectionTimer.Reset((int) p.random(2000, 5000));
        }

        if (this.getLocation().x >= p.width / 2 + 400 || this.getLocation().x <= p.width / 2 - 400) {
            getVelocity().rotate(p.HALF_PI);
        }
        if (getLocation().y >= p.height / 2 + 400 || this.getLocation().y <= p.height / 2 - 400) {
            getVelocity().rotate(p.HALF_PI);
        }
        stopWhenSeeingRabbit(EnhancedVision());
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
            //if (this.FoxEatTimer.IsDone()) {
                if (target instanceof Rabbit) {
                    PVector targetVector = PVector.sub(target.getLocation(), this.getLocation());
                    targetVector.normalize();
                    targetVector.mult(this.movementSpeed);
                    this.getVelocity().set(targetVector);
                    Main.allEntities.get(0).arrayOfEntities.remove(((Rabbit) target));
                    this.movingState = 0;
                }
            }
        }

    @Override
    protected void EntityUpdate(Environment env) {
        switch (movingState) {
            case 0:
                wanderingMovement();
                break;
            case 1:
                huntingRabbit(EnhancedVision());
                break;
        }
        display2();
    }

    public void display2() {
        p.stroke(208, 78, 26);
        p.noFill();
        p.ellipse(this.getLocation().x, this.getLocation().y, this.visionRange, this.visionRange);
        p.stroke(0, 0, 0);
    }
}