package game.entities.State;

public class Active implements IState {
    private String state;

    public Active() {
        this.state = "Active";
    }

    @Override
    public String toString() { return state; }
}



