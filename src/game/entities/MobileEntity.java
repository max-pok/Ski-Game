package game.entities;


import game.arena.IArena;
import game.entities.State.Active;
import game.entities.State.IState;
import utilities.Point;
import utilities.ValidationUtils;

public abstract class MobileEntity extends Entity implements IMobileEntity, Runnable {
    private double maxSpeed;
    private double acceleration;
    private double speed;
    private IArena arenaType;
    private IState state;
    private String destiny;
    private long time;

    public MobileEntity(double maxSpeed, double acceleration) {
        try {
            ValidationUtils.assertNotNegative(maxSpeed);
            this.maxSpeed = maxSpeed;
            ValidationUtils.assertNotNegative(acceleration);
            this.acceleration = acceleration;
            state = new Active();
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }
    }

    public void setArena(IArena arena) {
        this.arenaType = arena;
    }

    /**
     * Sets the competitor at the start location
     */
    public void initRace(int x, int y) {
        setLocation(new Point(x, y));
        setSpeed(0);
        destiny();
    }

    /**
     * Sets the new speed and moves the competitor
     * @param friction - arena friction amount
     */
    @Override
    public synchronized void move(double friction) {
        double newSpeed = getSpeed() + (1 - friction) * getAcceleration();
        setSpeed(newSpeed);
        setLocation(getLocation().offset(0, getSpeed()));
    }


    @Override
    public Point getLocation() {
        return super.getLocation();
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        ValidationUtils.assertNotNegative(speed);
        if (speed > maxSpeed) this.speed = getMaxSpeed();
        else this.speed = speed;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void setLocation(Point location) {
        super.setLocation(location);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (getLocation().getY() < arenaType.getLength()) {
            try {
                move(arenaType.getFriction());
                setChanged();
                notifyObservers("Update GUI");
                Thread.sleep(100);
                long end = System.currentTimeMillis();
                if (time != 0 && end-start >= getTime()) {
                    setChanged();
                    notifyObservers(destiny);
                    this.time = 0;
                    if (this.destiny.equals("Disabled")) break;
                    if (this.destiny.equals("Injured")) injuredDestiny();
                }
                if (arenaType.isFinished(this)) {
                    this.setLocation(new Point(getLocation().getX(), arenaType.getLength()));
                    setChanged();
                    notifyObservers("Finished");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* sets the competitor destiny in the competition */
    public void destiny() {
        String[] types = { "Active", "Injured", "Disabled" };
        int num = (int)(Math.random() * 10 + 1);
        String s_ran;
        if (num > 0 && num < 7) {
            s_ran = types[0];
            this.time = 0;
        }
        else if (num >= 7 && num < 9) {
            s_ran = types[1];
            this.time = (long)(Math.random() * 4000 + 1);
        }
        else {
            s_ran = types[2];
            this.time = (long) (Math.random() * 4000 + 1);
        }
        this.destiny = s_ran;
    }

    /* sets the destiny of the injured competitor */
    public void injuredDestiny() {
        int num = (int)(Math.random() * 10 + 1);
        if (num > 0 && num < 4) {
            this.destiny = "Disabled"; /* becomes disabled */
        } else this.time = 0; /* stays injured until he finishes the race */
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public long getTime() {
        return time;
    }
}

