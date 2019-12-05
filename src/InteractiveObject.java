import processing.core.PApplet;

import java.security.Key;

public class InteractiveObject {
    int interActiveObjectX;
    int interActiveObjectY;
    int interActiveObjectSizeX;
    int interActiveObjectSizeY;
    PApplet p;

    InteractiveObject() {
        this.interActiveObjectX = 0;
        this.interActiveObjectY = 0;
        this.interActiveObjectSizeX = 0;
        this.interActiveObjectSizeY = 0;

    }

    public InteractiveObject createInteractiveObject(PApplet p, int x, int y, int sX, int sY, String theNameOfYourInteractiveObject) {
        this.p = p;
        InteractiveObject name = new InteractiveObject();
        this.interActiveObjectX = x;
        this.interActiveObjectY = y;
        this.interActiveObjectSizeX = sX;
        this.interActiveObjectSizeY = sY;

        return name;
    }

    public void display() {
        p.rect(this.interActiveObjectX, this.interActiveObjectY, this.interActiveObjectSizeX, this.interActiveObjectSizeY);
    }

    public void update() {
        display();
        KeyPressed();

    }

    public void KeyPressed() {


        if (p.mousePressed) {
            System.out.println("Graph");
            if (p.mouseX >= 875 && p.mouseX <= 925 && p.mouseY >= 75 && p.mouseY <= 125) {
                GraphingTheData.openGraphWhenMouse = true;
                System.out.println("Graph should be open");
                //System.out.println(GraphingTheData.openGraphWhenMouse);
            }
        }

        if(p.keyPressed) {
            if(p.keyCode == 'A') {
                System.out.println('A');
            }
        }

        if(p.mousePressed) {
            System.out.println("GO!");
            if(p.mouseX >= 400 && p.mouseX <= 450 && p.mouseY >= 400 && p.mouseY <= 450) {
                System.out.println("The Program Should run now");
                Main.stateOfProgram = 1;
            }
        }
    }


}
