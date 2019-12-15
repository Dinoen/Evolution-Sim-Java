public class ActionTimer {
    private int _startTime;
    public  int Duration;

    public ActionTimer(int duration) {
        _startTime = Main.theEnvironment.p.millis();
        Duration = duration;
    }

    public  boolean IsDone() {
        return (Main.theEnvironment.p.millis() > (_startTime + Duration));
    }

    public void Reset() { _startTime = Main.theEnvironment.p.millis(); }

    public void Reset(int duration) {
        _startTime = Main.theEnvironment.p.millis();
        Duration = duration;
    }
}
