package game.entities.sportsman;

public class SpeedySportsman extends WSDecorator {
    public SpeedySportsman(IWinterSportsman sportsman) {
        super(sportsman);
    }

    public void setAcceleration(double acceleration) {
        makeWinterSportsman().setAcceleration(makeWinterSportsman().getAcceleration() + acceleration);
    }
}
