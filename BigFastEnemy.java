package game;

public class BigFastEnemy extends BasicEnemy
{
    public BigFastEnemy()
    {
        super(150, 4, 10);
    }
    
    @Override
    public int getEnemyType()
    {
        return 7;
    }
}

