import java.util.ArrayList;
import processing.core.PApplet;


public class Population {
    ArrayList<Rabbit> arrayOfRabbits;
    //Making Rabbits via ArrayList

    public Population() {
        arrayOfRabbits = new ArrayList<>();

    }

    public void createPopulation(PApplet p ,int populationSize, String typeOfAnimal){
        if (typeOfAnimal == "Rabbit") {
            for (int i = 0; i < populationSize; i++) {
                Rabbit rabbit = new Rabbit(p, 30, 20, i);
                arrayOfRabbits.add(rabbit);
            }
        }
    }

    public void update() {
        for (int i = 0; i < arrayOfRabbits.size(); i++) {
            arrayOfRabbits.get(i).update();
        }
    }

    public ArrayList<Rabbit> getPopulation(){
        return arrayOfRabbits;
    }
}

