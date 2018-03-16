package game;

import java.util.List;

public interface Tower
{
    //ACCESSOR METHODS
    
    public int getDamage();

    public int getRange();

    public int getAttackSpeed();

    public int getRank();

    public int getCost();

    public int getUpgradeCost();

    public Location getLocation();

    public int getTowerID();

    public Enemy getTarget();

    public int getLifetime();
    
    //MUTATOR METHODS

    public void setDamage(int dmg);

    public void setRange(int rn);

    public void setAttackSpeed(int speed);

    public void setRank(int r);

    public void setCost(int c);

    public void setUpgradeCost(int c);

    public void setLocation(Location loc);

    public void setTowerID(int id);

    public void setTarget(Enemy e);

    public void setLifetime(int l);

    //SPECIAL METHODS
    
    public void upgrade();

    public void attack(List<Enemy> enemies);

    public Enemy getLastTarget();

    public int updateLifetime(int a);

    public boolean hasEnemiesInRange(List<Enemy> enemies);

    public int getTowerType();

    public boolean isSelected();

    public void setSelected(boolean b);

    public void decrID();

    public void setAttacking(boolean b);

    public boolean isAttacking();
}
