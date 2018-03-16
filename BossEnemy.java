package game;

public class BossEnemy extends BasicEnemy
{
    public BossEnemy()
    {
        super(10000, 1, 100);
    }

    @Override
    public int getEnemyType()
    {
        return 5;
    }
}
