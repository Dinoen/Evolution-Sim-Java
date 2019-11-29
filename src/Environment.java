import processing.core.PApplet;
import processing.core.PConstants;

public class Environment {
    PApplet p;
    int groundWidth;
    int groundHeight;

    Environment(PApplet pApplet, int width, int height){
        this.groundHeight = height;
        this.groundWidth = width;
        p = pApplet;
    }

    public void displayGround(PApplet p){
        p.rectMode(PConstants.CENTER);
        p.fill(40,150,100);
        p.rect(p.width/2,p.height/2,this.groundWidth,this.groundHeight);
    }

    public void update(){
       displayGround(this.p);
    }
}
