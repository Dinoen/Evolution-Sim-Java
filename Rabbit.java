import java.util.Random;

public class Rabbit {

    private int x = 0;
    private int y = 0;
    private int movementSpeed = 3;
    public Random randomNumber = new Random();
    public Rabbit(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    public void drawRabbit() {

        Main.processing.rect(getX(), getY(),10,10);
        moveDirection();
    }

    public void moveDirection() {

      int a =randomNumber.nextInt(5);

        switch (a){

        case 0:
            if(x < Main.processing.width){
                this.x += movementSpeed;
            }
            break;
            case 1:
                if ( x > 0){
                    this.x -= movementSpeed;
                }
                break;
            case 2:
                if (y < Main.processing.height -3){
                    this.y += movementSpeed;
                }
                break;
            case 3:
                if (y > 3 ){
                    this.y -= movementSpeed;
                }
                break;
        }

    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
