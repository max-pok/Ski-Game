package game.competition;

import game.arena.ArenaFactory;
import game.arena.IArena;
import game.entities.sportsman.Skier;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;
import game.enums.*;

public class CompetitionBuilder {
    private IArena arena;
    private Competitor competitor;

    public CompetitionBuilder() {}

    /**
     * Default Competition Builder
     */
    public SkiCompetition DefaultCompetitionBuilder(int competitorsAmount) {
        arena = new ArenaFactory().getArena("Winter",700, SnowSurface.ICE, WeatherCondition.SUNNY);
        SkiCompetition competition = new SkiCompetition(arena,competitorsAmount, Discipline.DOWNHILL, League.ADULT, Gender.MALE);
        competitor = new Skier("Skier ",22,Gender.MALE,3,10,Discipline.DOWNHILL);
        ((Sportsman)competitor).setColor(CompetitorColor.Black);
        competition.addCompetitor(competitor);
        for (int i = 0 ; i < competitorsAmount - 1 ; i++) {
            try {
                competition.addCompetitor((Competitor) ((WinterSportsman) competitor).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return competition;
    }

    public IArena getArena() {
        return arena;
    }

    public Competitor getCompetitor() {
        return competitor;
    }
}
