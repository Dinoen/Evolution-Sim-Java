import processing.core.PApplet;
import processing.core.PVector;
import java.awt.*;

//superclass, holding all the relevant things for living objects, eg rabbits and foxes
public abstract class Living extends Entity {

    protected final int birthDayTime = p.millis();
    protected int generationCounter;

    public Living(PApplet papplet, int id, PVector location, Color color) {
        super(papplet, id, location, color);
    }

    public Living(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape) {
        super(papplet, id, location, color, size, shape);
    }

    public int setGenerationNumber(int fatherGeneration, int motherGeneration) {
        int newGenerationNumber;
        newGenerationNumber = fatherGeneration;
        if (newGenerationNumber < motherGeneration) {
            newGenerationNumber = motherGeneration;
        }
        newGenerationNumber++;
        return newGenerationNumber;
    }
}

