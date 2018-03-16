package game;

import java.util.List;

public class FreezeTower extends BasicTower
{

    public FreezeTower()
    {
        super(500, 100, 1500, 100, 150);
    }

    @Override
    public void upgrade()
    {
        setRank(getRank() + 1);
        setCost(getCost() + getUpgradeCost());

        switch (getRank())
        {
            case 2:
                setDamage(getDamage() + 100);
                setRange(getRange() + 15);
                setAttackSpeed(getAttackSpeed() - 100);
                setUpgradeCost(175);
                break;
            case 3:
                setDamage(getDamage() + 150);
                setRange(getRange() + 25);
                setAttackSpeed(getAttackSpeed() - 150);
                setUpgradeCost(0);
                break;
        }
    }

    @Override
    public int getTowerType()
    {
        return 4;
    }

    public static int getInitialCost()
    {
        return 100;
    }

    @Override
    public void attack(List<Enemy> enemies)
    {
        setTarget(null);

        for (Enemy e : enemies) {
            if (e.getDistanceFromTower(this) <= getRange() && e.getSpeed() > 0) {
                if (getTarget() == null) {
                    setTarget(e);
                } else if (getTarget().getDistanceTravelled() < e.getDistanceTravelled()) {
                    setTarget(e);
                }
            }
        }

        if (getTarget() != null) {
            getTarget().freeze(getDamage());
        }
    }
}
