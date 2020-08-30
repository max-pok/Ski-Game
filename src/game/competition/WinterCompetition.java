package game.competition;

import game.arena.IArena;
import game.arena.WinterArena;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.ValidationUtils;

public class WinterCompetition extends Competition {
    private Discipline discipline;
    private League league;
    private Gender gender;

    public WinterCompetition(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender){
        super(arena,maxCompetitors);
        try {
            ValidationUtils.assertNotNull(discipline);
            this.discipline = discipline;
            ValidationUtils.assertNotNull(league);
            this.league = league;
            ValidationUtils.assertNotNull(gender);
            this.gender = gender;
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }
    }

    protected boolean isValidCompetitor(Competitor competitor){
        if (competitor instanceof WinterSportsman) {
            WinterSportsman winterSportsman = (WinterSportsman) competitor;
            return this.getGender() == ((WinterSportsman) competitor).getGender() &&
                    this.getDiscipline() == ((WinterSportsman) competitor).getDiscipline() &&
                    this.league.isInLeague(((WinterSportsman) competitor).getAge());
        }
        return false;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public League getLeague() {
        return league;
    }

    public Gender getGender() {
        return gender;
    }

}
