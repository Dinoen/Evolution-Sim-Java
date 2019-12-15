import processing.core.PApplet;

public class InteractiveObject {
    int interActiveObjectX;
    int interActiveObjectY;
    int interActiveObjectSizeX;
    int interActiveObjectSizeY;
    PApplet p;
    protected String Caption;

    InteractiveObject() {
        this.interActiveObjectX = 0;
        this.interActiveObjectY = 0;
        this.interActiveObjectSizeX = 0;
        this.interActiveObjectSizeY = 0;
    }

    public InteractiveObject createInteractiveObject(PApplet p, int x, int y, int sX, int sY, String theNameOfYourInteractiveObject, boolean hasFont) {
        this.p = p;
        InteractiveObject name = new InteractiveObject();
        this.interActiveObjectX = x;
        this.interActiveObjectY = y;
        this.interActiveObjectSizeX = sX;
        this.interActiveObjectSizeY = sY;
        Caption = String.format("Start");
        return name;
    }

    public void display() {
        p.fill(255);
        p.rect(this.interActiveObjectX, this.interActiveObjectY, this.interActiveObjectSizeX, this.interActiveObjectSizeY);

        p.fill(0);
        p.textSize(20);
        p.text(Caption, p.width/2-47, p.height/2-17);
    }

    public void update() {
        display();
        KeyPressed();
    }

    public void KeyPressed() {
        if(p.mousePressed) {
            System.out.println("GO!");
            if(p.mouseX >= 400 && p.mouseX <= 450 && p.mouseY >= 400 && p.mouseY <= 450) {
                System.out.println("The Program Should run now");
                Main.stateOfProgram = 1;
                Caption="";
            }
        }
    }
}