import processing.core.PApplet;
import processing.core.PVector;

public class Grass extends Plant {

    float x;
    float y;
    PApplet p;
    public PVector location;
    float timeIntervalSpawn;
    float grassSpawn;
    float timeintervalDespawn;
    float grassDespawn;
    public boolean occupied;
    int time;
    int timer = 7000;

    Grass(PApplet p, float x, float y) {
        this.p = p;
        this.x = x;
        this.y = y;
        location = new PVector(this.x, this.y);
        grassSpawn = p.millis();

        grassDespawn = p.millis();

        typeOfLiving = "Grass";
        this.time = p.millis();


        occupied = false;

    }


    @Override
    public void display() {
        p.stroke(0);
        p.fill(0, 230, 0);
        p.ellipse(x, y, 15, 15);
    }

    public void grassSpawn() {
        if (p.millis() > grassSpawn + timeIntervalSpawn && p.millis() - time >= timer && Main.allEntities.get(1).arrayOfEntities.size() < 150) {

            PVector targetVector = PVector.sub(
                    newGrassLocation(), this.location);
            targetVector.normalize();
            targetVector.mult(100);
            PVector xy = new PVector();
            xy = PVector.add(location, targetVector);
            Grass x = new Grass(p, xy.x, xy.y);
            Main.allEntities.get(1).arrayOfEntities.add(x);
            this.time = p.millis();
        }
    }

    public PVector newGrassLocation() {
        //make new vector
        PVector newlocation = new PVector();
        //chose random coordinates from the middle of the screen
        newlocation.x = p.width / 2 + p.random(-400, 400);
        newlocation.y = p.height / 2 + p.random(-400, 400);
        //return the new location
        return newlocation;

    }


    /*public void grassDespawn() {

        if (!Main.allEntities.get(1).getEntitiesGrass().isEmpty()) {

            for (int i = 0; i < (int) p.random(0, 4); i++) {

                Main.allEntities.get(1).getEntitiesGrass().remove((int) p.random(0, Main.allEntities.get(1).getEntitiesGrass().size()));

                System.out.println("nej");
            }
        }

    }*/


    public void update() {
        display();
        grassSpawn();
        //  if (p.millis() > grassDespawn + timeintervalDespawn) {
        //    grassDespawn();
        //  grassDespawn = p.millis();
        //}
    }

    @Override
    public PVector getLocation() {
        return location;
    }

}


