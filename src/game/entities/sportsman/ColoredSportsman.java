package game.entities.sportsman;

import game.enums.CompetitorColor;

public class ColoredSportsman extends WSDecorator {

    public ColoredSportsman(IWinterSportsman sportsman) {
        super(sportsman);
    }

    public void setColor(CompetitorColor color) {
        makeWinterSportsman().setColor(color);
    }
}
