import java.util.ArrayList;
import processing.core.PApplet;

//population class which holds the different populations of animals / living things.
public class Entities {
    //Making Rabbits via ArrayList
    ArrayList<Living> arrayOfEntities;

    //making an ID, which is unique-being public and static so we can iterate it whenever we make something new
    public static int entityUniqueID = 0;

    //Constructor
    public Entities() {
        //making a new array, containing rabbits.
        arrayOfEntities = new ArrayList<Living>();
    }

    public void createEntities(PApplet p, int entityListSize, Class entityClass) {
        //Check entityclass to see  what kind of list we're making
        if (entityClass == Rabbit.class) {
            //run amount of times of the size of the population
            for (int i = 0; i < entityListSize/2; i++) {
                Rabbit randomRabbit =
                        new Rabbit(
                                p,
                                Entity.NextGlobalEntityId(),
                                Main.theEnvironment.RandomLocation(),
                                Rabbit.male(),
                                true,
                                5,
                                /*1f,*/   p.random(0.5f, 2f),  // animalSpeed
                                false,
                                Rabbit.RABBIT_DEFAULT_VISION_RANGE,
                                0
                        );
                arrayOfEntities.add(randomRabbit);
            }
            for (int i = 0; i < entityListSize/2; i++) {
                Rabbit randomRabbit =
                        new Rabbit(
                                p,
                                Entity.NextGlobalEntityId(),
                                Main.theEnvironment.RandomLocation(),
                                Rabbit.female(),
                                true,
                                5,// topspeed
                                /*1f,*/   p.random(0.5f, 2f),  // animalSpeed
                                false,                   // IsKid
                                Rabbit.RABBIT_DEFAULT_VISION_RANGE,
                                0// VisionRange
                        );
                arrayOfEntities.add(randomRabbit);
            }
        } else if (entityClass == Grass.class) {
            for (int i = 0; i < entityListSize; i++) {
                //Grass grass = new Grass(p,p.random(100f, 800f),p.random(100f, 800f));
                Grass grass = new Grass(p, Entity.NextGlobalEntityId(), Main.theEnvironment.RandomLocation());
                arrayOfEntities.add(grass);
                //make unique ID later
            }
        } else if (entityClass == Fox.class) {
            for (int i = 0; i < entityListSize; i++) {
                //Fox fox = new Fox(p,p.random(100f,800f),p.random(100f,800f),1f,5f);
                Fox fox = new Fox(p, Entity.NextGlobalEntityId(), Main.theEnvironment.RandomLocation(), 1f, 5f);
                arrayOfEntities.add(fox);
                //make unique ID later
            }
        }
    }

    //update function which runs in the main class, and updates the positions of the entities.
    //Layering the update functions in the classes
    public void update() {

        for (int i = 0; i < arrayOfEntities.size(); i++) {
            ((Living) arrayOfEntities.get(i)).update(Main.theEnvironment);
        }
    }

    public void display() {
        for (int i = 0; i < arrayOfEntities.size(); i++) {
            ((Living) arrayOfEntities.get(i)).display(Main.theEnvironment);
        }
    }

    //one of these getter per arraylist
    public ArrayList<Living> getEntities() {
        return arrayOfEntities;
    }
}