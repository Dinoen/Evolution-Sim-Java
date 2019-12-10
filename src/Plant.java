import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;



public abstract class Plant extends Living {

    public static final Color DEFAULT_PLANT_COLOR = new Color(48, 188, 39);

    public Plant(PApplet papplet, int id, PVector location, Color color) {
        super(papplet, id, location, color);
    }

    public Plant(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape) {
        super(papplet, id, location, color, size, shape);
    }

    //initially chose lets say 10 random points for grass "families" to spawn
    //put them in the array of grass
    //from the coordinates of the initial grass, choose a vector
    //draw a vector between the two points
    //normalize the vector
    //set the spawnDistance, from the original spot
    //set a certain amount of children a certain plant can have at maximum, when that is reached, the spawning from this "family" stops.
    //store the data in a tree structure, so, if the "mother" node dies, then one of the children become the new mother, and takes over the tree, still with the total maximum of grass in the family remaining the same
    //spawn a "child" Grass, and add it to the tree of grasses
    //start a timer for X amounts of sec, and spawn another child in the family

    //Give Grass a value, which is how much hunger it satisfies
    //
}
