import processing.core.PApplet;

import static processing.core.PApplet.dist;

public class newRabbit {

    InstantiationTheRabbits instantiation = new InstantiationTheRabbits();

    public void newRabbitBaby() {

        for (int i = 0; i < instantiation.arrayOfRabbits.size(); i++) {
            Rabbit theRabbit = instantiation.arrayOfRabbits.get(i);

            for (int j = 0; j < instantiation.arrayOfRabbits.size(); j++) {

                Rabbit theRabbitCompare = instantiation.arrayOfRabbits.get(j);
                float x1 = theRabbit.getX();
                float y1 = theRabbit.getY();
                float x2 = theRabbitCompare.getX();
                float y2 = theRabbitCompare.getY();

                float dist = dist(x1, y1, x2, y2);
                System.out.println(dist);
                System.out.println(theRabbit.getViewDistance());    //.rabbitDrawing().get(0).getViewDistance());
                System.out.println(x1 + " " +  y1 + " " + x2 + " " + y2);
                //When rabbits are in a specific distance from each other we fill the ArrayList with a new Rabbit.
                if (dist < theRabbit.getViewDistance() && dist > theRabbitCompare.getViewDistance()-3) {
                    newRabbitBaby();
                }
            }
        }
    }
    public void print() {
        System.out.println(instantiation.arrayOfRabbits.size());
    }
}
