import processing.core.PApplet;
//another part of procesing
import processing.core.PConstants;
import processing.core.PVector;

import java.beans.PropertyVetoException;

public class Environment {
    PApplet p;

    public PApplet PAStage() {return p; }



    //size of the green area // ground area
    int groundWidth;
    int groundHeight;

    //environment constructor, takes the PApplet, width and height, when created
    Environment(PApplet pApplet, int width, int height){
        this.groundHeight = height;
        this.groundWidth = width;
        p = pApplet;
    }
    //Display ground draws everything
    public void displayGround(PApplet p){
        //puts the rect in the middle... x and y is from the middle and not the top left
        p.rectMode(PConstants.CENTER);
        //Green color
        p.fill(40,150,100);
        //draw the square, x and y being the middle of the screen,
        p.rect(p.width/2,p.height/2,this.groundWidth,this.groundHeight);
    }

    //update function to be run in the main class, which updates every aspect of the environment
    public void update(){
       displayGround(this.p);
    }

    public PVector RandomLocation() {


//        PVector newlocation = new PVector();
//        //chose random coordinates from the middle of the screen
//        newlocation.x = p.width / 2 + p.random(-400, 400);
//        newlocation.y = p.height / 2 + p.random(-400, 400);
//        //return the new location
//        return newlocation;

        return new PVector(
                p.width / 2 + p.random(-400, 400),
                p.height / 2 + p.random(-400, 400));
    }
}
