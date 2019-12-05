import processing.core.PApplet;
// forum.processing.org/two/discussion/1725/millis-and-timer
// partly by gotoloop

class Timer {

    // this small class provides a timer based on millis() to
    // do something after a certain amount of time.
    // Its aim is to capsule all methods and data for a timer.
    // You can start it and then check if it is finished.
    //
    // by different constructors you can :
    //  * pass only how long to wait
    //  * pass name and how long to wait
    //  * pass how long to wait and whether to start immediately (false = on hold)
    //  * pass name, how long to wait and whether to start immediately (false = on hold)
    //  * plus ID for a command (not in use in this sketch)
    PApplet p;

    boolean hasStarted=true;
    int Wait_Time = (int) (3.0 * 1000); // 3.0 seconds in millis
    int startTime = 0;
    String name   = "Timer 1";

    // cmd ID
    final int cmdID_not_ON  = -1;    // the value it gets When timer is OFF
    int setPublicCommmandID = -1;    // the value it gets When timer is ON
    int setcurrentID        = cmdID_not_ON;         // the current value

    // ------------------------------------------

    // constr I
    Timer( String name, int Wait_Time ) {
        this.name      = name;
        this.Wait_Time = Wait_Time;
        startTime=p.millis();
    }
    // constr I

    // constr II
    Timer( int Wait_Time ) {
        this.Wait_Time= Wait_Time;
        startTime=p.millis();
    }
    // constr II

    // constr III
    Timer( int Wait_Time, boolean hasStarted ) {
        this.Wait_Time= Wait_Time;
        startTime=p.millis();
        this.hasStarted=hasStarted;
    }
    // constr III

    // constr IV
    Timer( String name, int Wait_Time, boolean hasStarted ) {
        this.name      = name;
        this.Wait_Time = Wait_Time;
        startTime=p.millis();
        this.hasStarted=hasStarted;
    }
    // constr IV

    // constr V
    Timer( String name, int Wait_Time, boolean hasStarted, int setPublicCommmandID ) {
        this.name      = name;
        this.Wait_Time = Wait_Time;
        this.hasStarted=hasStarted;
        this.setPublicCommmandID = setPublicCommmandID;
        startTime=p.millis();
    }
    // constr V

    // ----------------------------------

    boolean hasFinished() {
        // check the timer.
        boolean buffer = (hasStarted)  &&  (p.millis() - startTime >= Wait_Time);
        if (buffer) {
            setcurrentID=setPublicCommmandID;
            // println ("yes " + setcurrentID);
        }
        else
        {
            // setcurrentID=-1;
        }
        //
        return buffer;
    }

    boolean hasFinishedAndStartAgain() {
        // check the timer and restart.
        // this is useful when you want to restart the timer
        // automatically after it was over
        boolean buffer = hasFinished();
        if (buffer) {
            startTimer();
        }
        return buffer;
    }

    // ----------------------------------

    void startTimer() {
        // start / reset
        startTime = p.millis();
        hasStarted=true;
        // setcurrentID = cmdID_not_ON ;
    }

    void stopTimer() {
        hasStarted=false;
        setcurrentID = cmdID_not_ON ;
    }

    // ----------------------------------

    int getCommandID () {
        return setcurrentID;
    }

    void switchCommandIDOff() {
        setcurrentID = cmdID_not_ON ;
    }
} // class

//
