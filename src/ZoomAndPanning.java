import static javafx.scene.transform.Transform.translate;
import static processing.core.PConstants.P2D;
import processing.core.PApplet;
public class ZoomAndPanning {
    //pan around the sketch
    PApplet p;
    float scale = 1;
    float xPan = 720;
    float yPan = 450;
    boolean zoomIn = false;
    boolean zoomOut = false;
    boolean panUp = false;
    boolean panDown = false;
    boolean panLeft = false;
    boolean panRight = false;
    float panSpeed = 5;
    float zoomSpeed = (float) 1.04;


    void updateZoomAndPanning(PApplet pApplet){
        this.p = pApplet;
        p.translate(p.width/2, p.height/2);
        p.scale(scale);
        p.translate(-xPan, -yPan);
        if(zoomIn){
            scale *= zoomSpeed;
        }
        if(zoomOut){
            scale /= zoomSpeed;
        }
        if(panUp){
            yPan -= panSpeed;
        }
        if(panDown){
            yPan += panSpeed;
        }
        if(panLeft){
            xPan -= panSpeed;
        }
        if(panRight){
            xPan += panSpeed;
        }
    }

    void keyPressed(){
        if(p.keyCode == p.UP){
            zoomIn = true;
            zoomOut = false;
        }
        if(p.keyCode == p.DOWN){
            zoomOut = true;
            zoomIn = false;
        }
        if(p.key == 'w'){
            panUp = true;
            panDown = false;
        }
        if(p.key == 's'){
            panDown = true;
            panUp = false;
        }
        if(p.key == 'a'){
            panLeft = true;
            panRight = false;
        }
        if(p.key == 'd'){
            panRight = true;
            panLeft = false;
        }
    }
    void keyReleased(){
        if(p.keyCode == p.UP){
            zoomIn = false;
        }
        if(p.keyCode == p.DOWN){
            zoomOut = false;
        }
        if(p.key == 'w'){
            panUp = false;
        }
        if(p.key == 's'){
            panDown = false;
        }
        if(p.key == 'a'){
            panLeft = false;
        }
        if(p.key == 'd'){
            panRight = false;
        }
    }

}
