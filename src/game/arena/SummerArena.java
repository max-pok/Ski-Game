package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import utilities.ValidationUtils;

public class SummerArena implements IArena {
    private double length;
    private SnowSurface surface;
    private WeatherCondition condition;

    /**
     * Constructor
     */
    public SummerArena(double length, SnowSurface surface, WeatherCondition condition) {
        ValidationUtils.assertNotNegative(length);
        this.length = length;
        ValidationUtils.assertNotNull(surface);
        this.surface = surface;
        ValidationUtils.assertNotNull(condition);
        this.condition = condition;
    }

    @Override
    public double getFriction() { return surface.getFriction(); }

    /**
     * @return true if the competitor crossed the finish line, false if not
     */
    @Override
    public boolean isFinished(IMobileEntity me) { return me.getLocation().getY() >= getLength(); }

    public double getLength() { return length; }

    @Override
    public String toString() {
        return "SummerArena";
    }

}
