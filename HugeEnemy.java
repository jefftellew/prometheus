package game;

public class HugeEnemy extends BasicEnemy
{
    public HugeEnemy()
    {
        super(1000, 2, 15);
    }

    @Override
    public int getEnemyType()
    {
        return 6;
    }
}