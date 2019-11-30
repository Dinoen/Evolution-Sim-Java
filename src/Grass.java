import processing.core.PApplet;

public class Grass extends Plant {

    float x;
    float y;
    PApplet p;
    float timeintervalSpawn;
    float grassSpawn;
    float timeintervalDespawn;
    float grassDespawn;

    Grass(PApplet p) {
        this.p = p;
        x = p.random(100f, 800f);
        y = p.random(100f, 800f);
        grassSpawn = p.millis();
        timeintervalSpawn = p.random(3000, 5000);
        grassDespawn = p.millis();
        timeintervalDespawn = p.random(7000, 7000);
    }


    @Override
    public void display() {
        p.stroke(0);
        p.fill(0, 230, 0);
        p.ellipse(x, y, 15, 15);
    }

    public void grassSpawn() {


        if (p.millis() > grassSpawn + timeintervalSpawn) {
            grassSpawn = p.millis();


        }

    }

    public void grassDespawn() {

        if (!Main.allEntities.get(1).getEntitiesGrass().isEmpty()) {


            for (int i = 0; i < (int) p.random(0, 4); i++) {

                Main.allEntities.get(1).getEntitiesGrass().remove((int) p.random(0, Main.allEntities.get(1).getEntitiesGrass().size()));


                System.out.println("nej");
            }
        }

    }


    public void update() {
        display();
        grassSpawn();
        if (p.millis() > grassDespawn + timeintervalDespawn) {
            grassDespawn();
            grassDespawn = p.millis();
        }
    }

}


