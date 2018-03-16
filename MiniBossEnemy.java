package game;

public class MiniBossEnemy extends BasicEnemy
{
    public MiniBossEnemy()
    {
        super(2000, 1, 50);
    }
    
    @Override
    public int getEnemyType()
    {
        return 4;
    }
}
