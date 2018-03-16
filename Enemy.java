package game;

/**
 * @(#)Enemy.java
 *
 *
 * @author
 * @version 1.00 2016/5/19
 */


public interface Enemy
{
    public int getHealth();
    public int getSpeed();
    public int getDistanceTravelled();
    public int getDirection();
    public Location getLocation();
    public int getCoinValue();
    public int getX();
    public int getY();

    public void setHealth(int h);
    public void setSpeed(int s);
    public void setDistanceTravelled(int dist);
    public void setDirection(int dir);
    public void setLocation(Location l);
    public void setCoinValue(int c);
    public void setX(int x);
    public void setY(int y);

    public void damage(int dmg);
    public void freeze(int time);
    public int getFreezeTime();
    public void updateFreezeTime(int t);
    public void unfreeze();
    public int getDistanceFromTower(Tower t);
    public void travel(int t);
    public int getEnemyType();
    public double getHealthPercent();
}