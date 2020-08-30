package game.entities.State;

public class Completed implements IState{
    private String state;

    public Completed() {
        this.state = "Completed";
    }

    @Override
    public String toString() { return this.state; }
}
