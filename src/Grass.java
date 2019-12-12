import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class Grass extends Plant {

    private static final Color DEFAULT_GRASS_COLOR = new Color(0,230,0);

    private static final Dimension DEFAULT_GRASS_SIZE = new Dimension(15,15);

    // No matter what, there will not be more grass than this
    private static final int  MAXIMUM_AMOUNT_OF_GRASS = 20;

    private static final  int DEFAULT_GRASS_DESPAWN_TIMER = 15000;


    protected  ActionTimer DespawnTimer = new ActionTimer(DEFAULT_GRASS_DESPAWN_TIMER);

    public Grass(PApplet papplet, int id, PVector location) {
        super(papplet, id, location, DEFAULT_GRASS_COLOR, DEFAULT_GRASS_SIZE, EntityShape.Ellipse);
    }


    public static ArrayList<Grass> Create(PApplet p, int count) {

        ArrayList<Grass> grass = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            grass.add(new Grass(p, Entity.NextGlobalEntityId(), Main.theEnvironment.RandomLocation()));
        }

        return grass;
    }


    private void grassSpawn() {

        if (DespawnTimer.IsDone()) {

            // My life os over!
            Main.allEntities.get(1).arrayOfEntities.remove(this);
        }


                int currentAmountOfGrass = Main.allEntities.get(1).arrayOfEntities.size();

                while (currentAmountOfGrass++ < MAXIMUM_AMOUNT_OF_GRASS) {
                    PVector targetVector = PVector.sub(Main.theEnvironment.RandomLocation(), getLocation());
                    targetVector.normalize();
                    targetVector.mult(100);
                    PVector xy = new PVector();
                    xy = PVector.add(getLocation(), targetVector);

                    Grass x = Grass.Create(p, 1).get(0);
                    Main.allEntities.get(1).arrayOfEntities.add(x);
                }
    }


    @Override
    protected void EntityUpdate(Environment env) {
        // Grass will spawn when eaten, so no need to update it
        grassSpawn();
    }
}


