package game;

public class ShortTower extends BasicTower
{

    public ShortTower()
    {
        super(50, 125, 1000, 50, 100);
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
                setRange(getRange() + 25);
                setAttackSpeed(getAttackSpeed() - 100);
                setUpgradeCost(150);
                break;

            case 3:
                setDamage(getDamage() + 30);
                setRange(getRange() + 35);
                setAttackSpeed(getAttackSpeed() - 150);
                setUpgradeCost(0);
                break;
        }
    }

    @Override
    public int getTowerType()
    {
        return 1;
    }

    public static int getInitialCost()
    {
        return 50;
    }
}
