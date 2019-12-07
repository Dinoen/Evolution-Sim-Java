import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class Grass extends Plant {

    private static final Color DEFAULT_GRASS_COLOR = new Color(0,230,0);

    private static final Dimension DEFAULT_GRASS_SIZE = new Dimension(15,15);


    // Not used?
//    float timeintervalDespawn; //TIMER CLASS
//    float grassDespawn; //PLANT ABSTRACT CLASS
//    public boolean occupied; //NOT USED


    // Respawn grass
    float timeIntervalSpawn; //TIMER CLASS
    float grassSpawn; //PLANT ABSTRACT CLASS

    // TODO: Use timer class
    //TIMER CLASS
    int time;
    int timer = 10000;



    public Grass(PApplet papplet, int id, PVector location) {
        super(papplet, id, location, DEFAULT_GRASS_COLOR, DEFAULT_GRASS_SIZE, EntityShape.Ellipse);


        grassSpawn = p.millis();
//        grassDespawn = p.millis();
//
         this.time = p.millis();
//        occupied = false;
    }


    public static ArrayList<Grass> Create(PApplet p, int count) {

        ArrayList<Grass> grass = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            grass.add(new Grass(p, Entity.NextGlobalEntityId(), Main.theEnvironment.RandomLocation()));
        }

        return grass;
    }


//    float x; //LIVING
//    float y; //LIVING
//    PApplet p;
//    public PVector location; //LIVING


//
//    Grass(PApplet p, float x, float y) {
//        this.p = p;
//        this.x = x;
//        this.y = y;
//        location = new PVector(this.x, this.y);
//        grassSpawn = p.millis();
//
//        grassDespawn = p.millis();
//
//        typeOfLiving = "Grass";
//        this.time = p.millis();
//
//        occupied = false;
//
//    }

//
//    @Override
//    public void display() {
//        p.stroke(0);
//        p.fill(0, 230, 0);
//        p.ellipse(x, y, 15, 15);
//    }

    private void grassSpawn() {
        if (p.millis() > grassSpawn + timeIntervalSpawn && p.millis() - time >= timer && Main.allEntities.get(1).arrayOfEntities.size() < 150) {

//            PVector targetVector = PVector.sub(
//                    newGrassLocation() , this.location);
            PVector targetVector = PVector.sub(Main.theEnvironment.RandomLocation(), entityLocation);
            targetVector.normalize();
            targetVector.mult(100);
            PVector xy = new PVector();
            xy = PVector.add(entityLocation, targetVector);

            //Grass x = new Grass(p, xy.x, xy.y);
            Grass x = Grass.Create(p, 1).get(0);

            Main.allEntities.get(1).arrayOfEntities.add(x);
            this.time = p.millis();
        }
    }

    //
//    public PVector newGrassLocation() {
//        //make new vector
//        PVector newlocation = new PVector();
//        //chose random coordinates from the middle of the screen
//        newlocation.x = p.width / 2 + p.random(-400, 400);
//        newlocation.y = p.height / 2 + p.random(-400, 400);
//        //return the new location
//        return newlocation;
//    }


    /*public void grassDespawn() {

        if (!Main.allEntities.get(1).getEntitiesGrass().isEmpty()) {

            for (int i = 0; i < (int) p.random(0, 4); i++) {

                Main.allEntities.get(1).getEntitiesGrass().remove((int) p.random(0, Main.allEntities.get(1).getEntitiesGrass().size()));

                System.out.println("nej");
            }
        }

    }*/


    @Override
    protected void EntityUpdate(Environment env) {

        // TODO: Breaking change - display
        // It will be displayed later
        //display();

        grassSpawn();
        //  if (p.millis() > grassDespawn + timeintervalDespawn) {
        //    grassDespawn();
        //  grassDespawn = p.millis();
        //}

    }


//    public void update() {
//        display();
//        grassSpawn();
//        //  if (p.millis() > grassDespawn + timeintervalDespawn) {
//        //    grassDespawn();
//        //  grassDespawn = p.millis();
//        //}
//    }

//    @Override
//    public PVector getLocation() {
//        return location;
//    }
//
//    public String getTypeOfLiving() {
//        return typeOfLiving;
//    }

}


