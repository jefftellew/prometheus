package game;

import java.util.List;

public class BasicTower implements Tower
{
	//**********************************
	//	INSTANCE VARIABLES
	//**********************************

	private static int towerIDcounter = 0;
	private int towerID;

	private int damage;
	private int range;
	private int attackDelay;

	private int rank;
	private int cost;
        private int upgradeCost;
	private int lifetime;

	private Location loc;
        private Enemy target;
        private boolean selected;
        private boolean attacking;

	//**********************************
	//          CONSTRUCTORS
	//**********************************

	public BasicTower()
	{
            towerID = towerIDcounter;
            towerIDcounter++;

            damage = 50;
            range = 125;
            attackDelay = 1000;

            rank = 1;
            cost = 50;
            upgradeCost = 100;
            lifetime = 0;

            loc = null;
            target = null;
            selected = false;
            attacking = false;
	}

	public BasicTower(int dmg, int rng, int atk, int c, int upgrade)
	{
            towerID = towerIDcounter;
            towerIDcounter++;

            damage = dmg;
            range = rng;
            attackDelay = atk;

            rank = 1;
            cost = c;
            upgradeCost = upgrade;
            lifetime = 0;

            loc = null;
            target = null;
            selected = false;
            attacking = false;
	}

	//**********************************
	//          ACCESSOR METHODS
	//**********************************

        @Override
	public int getDamage()
	{
            return damage;
	}

        @Override
	public int getRange()
	{
            return range;
	}

        @Override
	public int getAttackSpeed()
	{
            return attackDelay;
	}

        @Override
	public int getRank()
	{
            return rank;
	}

        @Override
	public int getCost()
	{
            return cost;
	}

        @Override
        public int getUpgradeCost()
        {
            return upgradeCost;
        }

        @Override
	public Location getLocation()
	{
            return loc;
	}

        @Override
	public int getTowerID()
	{
            return towerID;
	}
        
        @Override
        public Enemy getTarget()
        {
            return target;
        }
        
        @Override
        public int getLifetime()
        {
            return lifetime;
        }

	//**********************************
	//          MUTATOR METHODS
	//**********************************

        @Override
	public void setDamage(int dmg)
	{
            damage = dmg;
	}

        @Override
	public void setRange(int rn)
	{
            range = rn;
	}

        @Override
	public void setAttackSpeed(int speed)
	{
            attackDelay = speed;
	}

        @Override
	public void setRank(int r)
	{
            rank = r;
	}

        @Override
	public void setCost(int c)
	{
            cost = c;
	}

        @Override
        public void setUpgradeCost(int u)
        {
            upgradeCost = u;
        }

        @Override
	public void setLocation(Location l)
	{
            loc = l;
	}

        @Override
        public void setTowerID(int id)
        {
            towerID = id;
        }  
        
        @Override
        public void setTarget(Enemy e)
        {
            target = e;
        }
        
        @Override
        public void setLifetime(int l)
        {
            lifetime = l;
        }

	//**********************************
	//          SPECIAL METHODS
	//**********************************

        @Override
	public void upgrade()
	{
            rank++;

            switch(rank)
            {
                case 2:
                    damage += 25;
                    range += 25;
                    attackDelay -= 100;
                    cost += 25;
                    break;

                case 3:
                    damage += 50;
                    range += 50;
                    attackDelay -= 150;
                    cost = 0;
                    break;
            }
	}

        @Override
	public void attack(List<Enemy> enemies)
	{
            target = null;

            for(Enemy e : enemies)
            {
                if(e.getDistanceFromTower(this) <= range)
                {
                    if(target == null)
                        target = e;
                    else if(target.getDistanceTravelled() < e.getDistanceTravelled())
                        target = e;
                }
            }

            if(target != null)
            {
                target.damage(damage);
            }           
	}

        @Override
        public boolean hasEnemiesInRange(List<Enemy> enemies)
        {
            for(Enemy e : enemies)
            {
            	if(e.getDistanceFromTower(this) <= range)
                    return true;
            }
            return false;
        }

        @Override
        public Enemy getLastTarget()
        {
            return target;
        }

        @Override
        public int updateLifetime(int a)
        {
            lifetime += a;

            return lifetime;
        }

        @Override
        public int getTowerType()
        {
            return 0;
        }

        @Override
        public boolean isSelected()
        {
            return selected;
        }

        @Override
        public void setSelected(boolean b)
        {
            selected = b;
        }

        @Override
        public void decrID()
        {
            towerID--;
        }

        public static void decrIDcounter()
        {
            towerIDcounter--;
        }

        public static void resetIDcounter()
        {
            towerIDcounter = 0;
        }
        
        @Override
        public void setAttacking(boolean b)
        {
            attacking = b;
        }
        
        @Override
        public boolean isAttacking()
        {
            return attacking;
        }
}