import java.util.ArrayList;

import processing.core.PApplet;

//population class which holds the different populations of animals / living things.
public class Entities {
    //Making Rabbits via ArrayList
    ArrayList<Rabbit> arrayOfRabbits;
    ArrayList<Grass> arrayOfGrass;
    //making an ID, which is unique-being public and static so we can iterate it whenever we make something new
    public static int entityUniqueID = 0;

    //Constructor
    public Entities() {
        //making a new array, containing rabbits.
        arrayOfRabbits = new ArrayList<>();
        arrayOfGrass = new ArrayList<>();
        //OTHER ARRAYS OF STUFF OF FOOD FX.
    }

    public void createEntities(PApplet p, int entityListSize, String typeOfEntities) {
        //if the type is rabbits
        String EntityType = typeOfEntities;
        //Switchcase controlling what kind of list we're making
        switch (EntityType) {
            case "Rabbit":
                //run amount of times of the size of the population
                for (int i = 0; i < entityListSize; i++) {
                    //change modulus stuff, bcause its messy AF
                    if (i % 2 == 0) {
                        Rabbit rabbit = new Rabbit(p, p.random(100f, 800f), p.random(100f, 800f), entityUniqueID, true, 3f, 1f, "Male",false);
                        arrayOfRabbits.add(rabbit);
                        entityUniqueID++;
                    }
                    if (i % 2 == 1) {
                        Rabbit rabbit = new Rabbit(p, p.random(100f, 800f), p.random(100f, 800f), entityUniqueID, true, 3f, 1f, "Female",false);
                        arrayOfRabbits.add(rabbit);
                        entityUniqueID++;
                    }
                }
                break;

            case "Grass":
                for (int i = 0; i < entityListSize; i++) {
                    Grass grass = new Grass(p);
                    arrayOfGrass.add(grass);
                    //make unique ID later
                }
                break;
            default:
                //Errormessaging
                break;
        }
    }
    //update function which runs in the main class, and updates the positions of the entities.
    //Layering the update functions in the classes
    public void update() {
        for (int i = 0; i < arrayOfRabbits.size(); i++) {
                arrayOfRabbits.get(i).update();

        }
        for (int i = 0; i < arrayOfGrass.size(); i++) {

            arrayOfGrass.get(i).update();
        }

    }
    //one of these getter per arraylist
    public ArrayList<Rabbit> getEntitiesRabbits() {
        return arrayOfRabbits;
    }

    public ArrayList<Grass> getEntitiesGrass() {
        return arrayOfGrass;
    }

}

