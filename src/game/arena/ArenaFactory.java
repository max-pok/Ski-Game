package game.arena;

import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class ArenaFactory {

    public ArenaFactory() { }

    public IArena getArena(String arena, double length, SnowSurface surface, WeatherCondition condition ) {
        if (arena.equals("Summer")) {
            return new SummerArena(length,surface, condition);
        }
        if (arena.equals("Winter")) {
            return new WinterArena(length, surface, condition);
        }
        return null;
    }
}
