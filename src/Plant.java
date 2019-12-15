import processing.core.PApplet;
import processing.core.PVector;
import java.awt.*;

public abstract class Plant extends Living {

    public static final Color DEFAULT_PLANT_COLOR = new Color(48, 188, 39);

    public Plant(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape) {
        super(papplet, id, location, color, size, shape);
    }
}
