import processing.core.PApplet;

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

    public InteractiveObject createInteractiveObject(PApplet p , int x, int y, int sX, int sY, String theNameOfYourInteractiveObject) {
        this.p = p;
        InteractiveObject name = new InteractiveObject();
        this.interActiveObjectX = x;
        this.interActiveObjectY = y;
        this.interActiveObjectSizeX = sX;
        this.interActiveObjectSizeY = sY;

        return name;
    }

    public void display() {
        p.rect(this.interActiveObjectX, this.interActiveObjectY,this.interActiveObjectSizeX,this.interActiveObjectSizeY);
    }

    public void update() {
        display();
    }
}
