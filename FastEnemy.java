package game;

public class FastEnemy extends BasicEnemy
{
    public FastEnemy()
    {
        super(75, 4, 10);
    }
    
    @Override
    public int getEnemyType()
    {
        return 3;
    }
}
