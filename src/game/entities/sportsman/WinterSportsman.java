package game.entities.sportsman;

import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import utilities.ValidationUtils;

public class WinterSportsman extends Sportsman implements Competitor, Cloneable, IWinterSportsman {
    private Discipline discipline;

    public WinterSportsman(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(name, age, gender, acceleration, maxSpeed);
        try {
            ValidationUtils.assertNotNull(discipline);
            this.discipline = discipline;
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }
    }

    public Discipline getDiscipline() { return discipline; }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public IWinterSportsman makeWinterSportsman() {
        return this;
    }
}
