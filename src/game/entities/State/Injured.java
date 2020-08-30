package game.entities.State;

public class Injured implements IState {
    private String state;

    public Injured() {
        this.state = "Injured";
    }

    @Override
    public String toString() { return this.state; }
}
