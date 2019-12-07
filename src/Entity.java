import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.awt.*;



// abstract superclass for all entities (living and dead things)
// This class holds common properties, ie: location and such
public abstract class Entity {


    protected PApplet p;

    public enum EntityShape {
        Ellipse,
        Rect,
        Triangle
    }

    public static final Dimension DEFAULT_ENTITY_SIZE = new Dimension(30,30);

    public static  final  EntityShape DEFAULT_ENTITY_SHAPE = EntityShape.Ellipse;



    // All entities have different id
    private static int GlobalUniqueEntityId = 0;

    // Returns next global entity id used when creating entities
    public static int NextGlobalEntityId() { return GlobalUniqueEntityId++; }



    // Immutable id for this entity
    private final int _id;




    protected PVector entityLocation;


    private Color entityColor;


    private  Dimension entitySize;


    private final EntityShape entityShape;


    // Used if we want entities to write to the ui
    protected String Caption;


//    private boolean deleted = false;

//    public boolean getDeleted() { return deleted; }

//    public void Delete() { deleted = true; }
//


    // Now for the fun stuff


    // TODO: Fix CenterLocation
    protected Entity(PApplet papplet,int id, Color color) {

        this(papplet, id,  Main.CenterLocation,  color);
    }

    protected Entity(PApplet papplet, int id, PVector location, Color color) {
        this(papplet, id, location, color, DEFAULT_ENTITY_SIZE, DEFAULT_ENTITY_SHAPE);
    }

    protected Entity(PApplet papplet, int id, PVector location, Color color, Dimension size, EntityShape shape) {

         p = papplet;

        _id = id;
        entityLocation = location;
        setEntityColor(color);
        setEntitySize(size);
        entityShape = shape;


        Caption = String.format("ID: %d", _id);
    }

    public int getId() { return _id; }

    public PVector getLocation() { return entityLocation; }

    public void setEntityLocation(PVector location) {

        // TODO: Check Bounderies of Ground

        this.entityLocation = location;
    }


    public Color getEntityColor() {
        return entityColor;
    }

    public void setEntityColor(Color entityColor) {
        this.entityColor = entityColor;
    }

    public Dimension getEntitySize() {
        return entitySize;
    }

    public void setEntitySize(Dimension entitySize) {
        this.entitySize = entitySize;
    }

    public EntityShape getEntityShape() {
        return entityShape;
    }




    protected void EntityUpdate(Environment env) {
        // Entities can override this method in order to update their state
    }

    // update named like Processing library suggest
    public final void update(Environment env) {

        //if (this.getDeleted()) return;

        EntityUpdate(env);
    }




    public void display(Environment env) {
        //if (this.getDeleted()) return;

        PVector currentLocation = this.getLocation();
        Color currentColor = this.getEntityColor();
        Dimension currentSize = this.getEntitySize();
        EntityShape currentShape = this.getEntityShape();

        env.p.stroke(0);
        env.p.fill(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
        env.p.rectMode(PConstants.CENTER);

        switch (currentShape) {

            case Ellipse:
                env.p.ellipse(currentLocation.x, currentLocation.y, currentSize.width, currentSize.height);
                break;

            case Rect:
                env.p.rect(currentLocation.x, currentLocation.y, currentSize.width, currentSize.height);
                break;

            case Triangle:
                env.p.triangle(currentLocation.x, currentLocation.y, currentLocation.x + 10, currentLocation.y + 14, currentLocation.x - 10, currentLocation.y + 14);
                break;
        }

        // TODO: SKal kun bruges hvis entities skal tegne deres caption
        env.p.fill(0);
        env.p.textSize(10);
        env.p.text(Caption, currentLocation.x+15, currentLocation.y);
    }



}
