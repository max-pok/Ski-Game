package game.entities;

import utilities.Point;
import utilities.ValidationUtils;
import java.util.Observable;

public abstract class Entity extends Observable {
    private Point location;

    public Entity() {
        location = new Point(0,0);
    }

    public Entity(Point location) {
        ValidationUtils.assertNotNull(location);
        this.location = new Point(location);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        ValidationUtils.assertNotNull(location);
        this.location = location;
    }
}
