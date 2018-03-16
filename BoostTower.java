package game;

import java.util.List;

public class BoostTower extends BasicTower
{

    public BoostTower()
    {
        super(25, 150, 50, 100, 100);
    }

    @Override
    public void upgrade()
    {
        setRank(getRank() + 1);
        setCost(getCost() + getUpgradeCost());

        switch (getRank())
        {
            case 2:
                setDamage(getDamage() + 20);
                setRange(getRange() + 20);
                setUpgradeCost(150);
                break;
            case 3:
                setDamage(getDamage() + 30);
                setRange(getRange() + 30);
                setUpgradeCost(0);
                break;
        }
    }

    @Override
    public int getTowerType()
    {
        return 3;
    }

    @Override
    public void attack(List<Enemy> enemies)
    {}

    public static int getInitialCost()
    {
        return 100;
    }

    public int getDistanceFromTower(Tower t)
    {
        Location tloc = t.getLocation();

        int xdiff = getLocation().getX() - tloc.getX();
        int ydiff = getLocation().getY() - tloc.getY();

        int dist = Math.abs((int) (Math.sqrt((xdiff * xdiff) + (ydiff * ydiff))));

        return dist;
    }

    public void boostAll(List<Tower> towers)
    {
        for (Tower t : towers)
        {
            if (t.getTowerType() != 3 && getDistanceFromTower(t) <= getRange())
            {
                t.setDamage(t.getDamage() + getDamage());
                t.setAttackSpeed(t.getAttackSpeed() - getAttackSpeed());
                t.setRange(t.getRange() + (getRange() / 10));
            }
        }
    }

    public void boostSingle(Tower t)
    {
        if (t.getTowerType() != 3 && getDistanceFromTower(t) <= getRange())
        {
            t.setDamage(t.getDamage() + getDamage());
            t.setAttackSpeed(t.getAttackSpeed() - getAttackSpeed());
            t.setRange(t.getRange() + (getRange() / 10));
        }
    }

    public void deboostAll(List<Tower> towers)
    {
        for (Tower t : towers)
        {
            if (t.getTowerType() != 3 && getDistanceFromTower(t) <= getRange())
            {
                t.setDamage(t.getDamage() - getDamage());
                t.setAttackSpeed(t.getAttackSpeed() + getAttackSpeed());
                t.setRange(t.getRange() - (getRange() / 10));
            }
        }
    }
}
