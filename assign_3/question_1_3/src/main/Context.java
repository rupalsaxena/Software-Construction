public class Context {
    private GameState state;

    public Context(){
        state = null;
    }

    public void setState(GameState state){
        this.state = state;
    }

    public GameState getState(){
        return state;
    }
}