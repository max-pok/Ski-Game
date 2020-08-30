package game.competition;

import game.entities.IMobileEntity;
import game.entities.sportsman.Sportsman;

import java.awt.*;

public interface Competitor extends IMobileEntity, Runnable {
    void initRace(int x,int y);
}
