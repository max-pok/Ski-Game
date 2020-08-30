package game.entities.State;

public class Disabled implements IState {
    private String state;

    public Disabled() {
        this.state = "Disabled";
    }

    @Override
    public String toString() { return this.state; }
}
