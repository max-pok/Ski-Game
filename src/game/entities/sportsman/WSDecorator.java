package game.entities.sportsman;

public abstract class WSDecorator implements IWinterSportsman {
    private IWinterSportsman sportsman;

    public WSDecorator(IWinterSportsman sportsman) {
        this.sportsman = sportsman;
    }

    @Override
    public WinterSportsman makeWinterSportsman() {
        return (WinterSportsman) this.sportsman.makeWinterSportsman();
    }
}
