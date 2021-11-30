public class StopState implements GameState{
    public void doAction(Context context) {
        context.setState(this);
    }
    public String toString(){
        return "GameOver";
    }
}
