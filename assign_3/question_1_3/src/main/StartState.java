public class StartState implements GameState{
    public void doAction(Context context) {
        context.setState(this);
    }
    public String toString(){
        return "StartState";
    }
}
