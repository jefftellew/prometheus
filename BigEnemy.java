package game;

public class BigEnemy extends BasicEnemy
{
    public BigEnemy()
    {
        super(500, 2, 10);
    }

    @Override
    public int getEnemyType()
    {
        return 2;
    }
}