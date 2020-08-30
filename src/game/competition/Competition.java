package game.competition;

import game.arena.IArena;
import game.entities.State.Completed;
import game.entities.State.Disabled;
import game.entities.State.Injured;
import game.entities.sportsman.Sportsman;
import utilities.ValidationUtils;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class Competition implements Observer {
    private IArena arena;
    private int maxCompetitors;
    private ArrayList<Competitor> activeCompetitors = new ArrayList<Competitor>();
    private ArrayList<Competitor> finishedCompetitors = new ArrayList<Competitor>();
    private ArrayList<Competitor> inactiveCompetitors = new ArrayList<Competitor>();
    private int playerLocation = 0;

    /**
     * Constructor
     * @param arena - interface
     * @param maxCompetitors - amount of max competitors
     */
    public Competition(IArena arena, int maxCompetitors) {
        try {
            ValidationUtils.assertNotNull(arena);
            this.arena = arena;
            ValidationUtils.assertNotNegative(maxCompetitors);
            this.maxCompetitors = maxCompetitors;
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Adds a competitor to the competition
     *
     * @param competitor interface
     */
    public void addCompetitor(Competitor competitor) {
        ValidationUtils.assertNotNull(competitor);
        if (activeCompetitors.size() < maxCompetitors) {
            if (isValidCompetitor(competitor)) {
                activeCompetitors.add(competitor);
                ((Sportsman) competitor).addObserver(this);
                ((Sportsman) competitor).setArena(arena);
                competitor.initRace(playerLocation, 0);
                playerLocation += 55;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid competitor " + competitor, "Message", 0);
                throw new IllegalArgumentException("Invalid competitor " + competitor);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Reached the max players.", "Message", 0);
            throw new IllegalStateException(arena + " is full max = " + maxCompetitors);
        }
    }

    /**
     * Checks if the competitor is a valid competitor
     * @param competitor interface
     * @return true if he is, false if he is not
     */
    protected abstract boolean isValidCompetitor(Competitor competitor);

    /**
     * Checks if there are active competitors left
     * @return true if there are, false if not
     */
    public boolean hasActiveCompetitors() {
        return activeCompetitors.size() > 0;
    }

    /**
     * @return the finished competitors
     */
    public ArrayList<Competitor> getFinishedCompetitors() {
        return finishedCompetitors;
    }

    public ArrayList<Competitor> getActiveCompetitors() {
        return activeCompetitors;
    }

    public ArrayList<Competitor> getInactiveCompetitors() {
        return inactiveCompetitors;
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        if (arg.equals("Finished")) {
            ((Sportsman) o).setState(new Completed());
            finishedCompetitors.add((Competitor) o);
            activeCompetitors.remove(o);
        }
        if (arg.equals("Injured")) {
            ((Sportsman) o).setState(new Injured());
        }
        if (arg.equals("Disabled")) {
            ((Sportsman) o).setState(new Disabled());
            inactiveCompetitors.add((Competitor) o);
            activeCompetitors.remove(o);
        }
    }

    public int getMaxCompetitors() {
        return maxCompetitors;
    }


}

