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
                        Rabbit rabbit = new Rabbit(p, p.random(100f, 800f), p.random(100f, 800f), entityUniqueID, true, p.random(1f,5f), p.random(0.5f,2f), "Male",false, p.random(80,110));
                        arrayOfEntities.add(rabbit);
                        entityUniqueID++;
                    }
                    if (i % 2 == 1) {
                        Rabbit rabbit = new Rabbit(p, p.random(100f, 800f), p.random(100f, 800f), entityUniqueID, true, p.random(1f,5f), p.random(0.5f,2f), "Female",false, p.random(80,110));
                        arrayOfEntities.add(rabbit);
                        entityUniqueID++;
                    }
                }
                break;

            case "Grass":
                for (int i = 0; i < entityListSize; i++) {
                    Grass grass = new Grass(p,p.random(100f, 800f),p.random(100f, 800f));
                    arrayOfEntities.add(grass);
                    //make unique ID later
                }
                break;

            case "Fox":
                for (int i = 0; i < entityListSize; i++) {
                    Fox fox = new Fox(p,p.random(100f,800f),p.random(100f,800f),1f,5f);
                    arrayOfEntities.add(fox);
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

        for (int i = 0; i < arrayOfEntities.size(); i++) {
                arrayOfEntities.get(i).update();

        }
        for (int i = 0; i < arrayOfEntities.size(); i++) {

            arrayOfEntities.get(i).update();
        }

    }
    //one of these getter per arraylist
    public ArrayList<Living> getEntities() {
        return arrayOfEntities;
    }

}

