package game;

public class BasicEnemy implements Enemy
{
    //**********************************
    //		INSTANCE VARIABLES
    //**********************************

    private static final int START_X = -50;
    private static final int START_Y = 105;

    private final int maxHealth;
    private final int baseSpeed;

    private int health;
    private int speed;

    private int direction;
    private int distance;
    private int coinValue;
    private int freezeTime;

    private Location loc;

    //**********************************
    //		CONSTRUCTORS
    //**********************************

    public BasicEnemy()
    {
        health = 200;
        speed = 2;
        maxHealth = health;
        baseSpeed = speed;

        direction = 1;
        distance = 0;
        coinValue = 5;
        freezeTime = 0;

        loc = new Location(START_X, START_Y);
    }

    public BasicEnemy(int hp, int spd, int coins)
    {
        health = hp;
        speed = spd;
        maxHealth = health;
        baseSpeed = speed;

        direction = 1;
        distance = 0;
        coinValue = coins;
        freezeTime = 0;

        loc = new Location(START_X, START_Y);
    }

    //**********************************
    //		ACCESSOR METHODS
    //**********************************

    public int getHealth()
    {
	return health;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getDistanceTravelled()
    {
        return distance;
    }

    public int getDirection()
    {
        return direction;
    }

    public Location getLocation()
    {
        return loc;
    }

    public int getCoinValue()
    {
        return coinValue;
    }

    public int getX()
    {
        return loc.getX();
    }

    public int getY()
    {
        return loc.getY();
    }


    //**********************************
    //		MUTATOR METHODS
    //**********************************

    public void setHealth(int h)
    {
        health = h;
    }

    public void setSpeed(int s)
    {
        speed = s;
    }

    public void setDistanceTravelled(int dist)
    {
        distance = dist;
    }

    public void setDirection(int dir)
    {
        direction = dir;
    }

    public void setLocation(Location l)
    {
        loc = l;
    }

    public void setCoinValue(int c)
    {
        coinValue = c;
    }

    public void setX(int x)
    {
        loc.setX(x);
    }

    public void setY(int y)
    {
        loc.setY(y);
    }

    //**********************************
    //		SPECIAL METHODS
    //**********************************

    public void damage(int dmg)
    {
        health -= dmg;
    }

    public void freeze(int time)
    {
        speed = 0;
        freezeTime = time;
    }
    
    public int getFreezeTime()
    {
        return freezeTime;
    }
    
    public void updateFreezeTime(int t)
    {
        freezeTime -= t;
    }
    
    public void unfreeze()
    {
        speed = baseSpeed;
    }

    public int getDistanceFromTower(Tower t)
    {
        Location tloc = t.getLocation();

        int xdiff = loc.getX() - tloc.getX();
        int ydiff = loc.getY() - tloc.getY();

        int dist =  Math.abs((int) (Math.sqrt((xdiff * xdiff) + (ydiff * ydiff))));

        return dist;
    }

    public void travel(int t)
    {
        distance += t;
    }

    public int getEnemyType()
    {
        return 1;
    }

    public double getHealthPercent()
    {
    	return 1.0 * health / maxHealth;
    }
}