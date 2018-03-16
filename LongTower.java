package game;

public class LongTower extends BasicTower
{

    public LongTower()
    {
        super(35, 175, 800, 50, 100);
    }

    @Override
    public void upgrade()
    {
        setRank(getRank() + 1);
        setCost(getCost() + getUpgradeCost());

        switch (getRank())
        {
            case 2:
                setDamage(getDamage() + 15);
                setRange(getRange() + 35);
                setAttackSpeed(getAttackSpeed() - 50);
                setUpgradeCost(100);
                break;

            case 3:
                setDamage(getDamage() + 25);
                setRange(getRange() + 50);
                setAttackSpeed(getAttackSpeed() - 100);
                setUpgradeCost(150);
                break;
        }
    }

    @Override
    public int getTowerType()
    {
        return 2;
    }

    public static int getInitialCost()
    {
        return 50;
    }
}
