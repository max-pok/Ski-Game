package game.entities.sportsman;

import game.entities.MobileEntity;
import game.enums.CompetitorColor;
import game.enums.Gender;
import game.enums.League;
import utilities.ValidationUtils;

public class Sportsman extends MobileEntity {
    private String name;
    private double age;
    private Gender gender;
    private long ID;
    private CompetitorColor color;

    public Sportsman(String name, double age, Gender gender, double acceleration, double maxSpeed) {
        super(maxSpeed, acceleration + League.calcAccelerationBonus(age));
        try {
            ValidationUtils.assertNotNullOrEmptyString(name);
            this.name = name;
            ValidationUtils.assertNotNegative(age);
            this.age = age;
            ValidationUtils.assertNotNull(gender);
            this.gender = gender;
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }
        generateID();
    }

    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void generateID() { this.ID = Math.round(Math.random() * 89999) + 10000; }

    public void setColor(CompetitorColor color) { this.color = color; }

    public long getID() {
        return ID;
    }

    public CompetitorColor getColor() {
        return color;
    }
}

