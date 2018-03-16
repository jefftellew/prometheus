package game;

import java.util.List;

public class BoardLocation
{
	//**********************************
	//	INSTANCE VARIABLES
	//**********************************

	private final static int TILE_SIZE = 70;

	private Location loc;
	private int direction;

	private boolean isPath;
	private boolean isPathCorner;
	private boolean hasTower;

	private Tower tower;

	//**********************************
	//          CONSTRUCTORS
	//**********************************

	public BoardLocation(Location l)
	{
            loc = l;
            direction = 0;

            isPath = false;
            isPathCorner = false;
            hasTower = false;

            tower = null;
	}

	public BoardLocation(Location l, int dir, boolean path, boolean corner)
	{
            loc = l;
            direction = dir;

            isPath = path;
            isPathCorner = corner;
            hasTower = false;

            tower = null;
	}

	//**********************************
	//          ACCESSOR METHODS
	//**********************************

	public Location getLocation()
	{
            return loc;
	}

	public int getTravelDirection()
	{
            return direction;
	}

	public boolean isPath()
	{
            return isPath;
	}

	public boolean isPathCorner()
	{
            return isPathCorner;
	}

	public boolean hasTower()
	{
            return hasTower;
	}

	public Tower getTower()
	{
            return tower;
	}

	//**********************************
	//          MUTATOR METHODS
	//**********************************

	public void setLocation(Location l)
	{
            loc = l;
	}

	public void setTravelDirection(int d)
	{
            direction = d;
	}

	public void setIsPath(boolean path)
	{
            isPath = path;
	}

	public void setIsPathCorner(boolean corner)
	{
            isPathCorner = corner;
	}

	public void setHasTower(boolean t)
	{
            hasTower = t;
	}

	//**********************************
	//          SPECIAL METHODS
	//**********************************

        public static int getTilesize()
        {
            return TILE_SIZE;
        }

	public boolean build(Tower t)
	{
            if(hasTower)
                return false;

            hasTower = true;
            tower = t;
            tower.setLocation(new Location((loc.getX() * TILE_SIZE) + 35, (loc.getY() * TILE_SIZE) + 35));
            return true;
	}

	public boolean sellTower()
	{
            if(!hasTower)
                return false;

            hasTower = false;
            tower = null;
            return true;
	}

        public boolean upgradeTower()
        {
            if(!hasTower || tower.getRank() == 3)
                return false;

            tower.upgrade();
            return true;
        }

	public boolean hasInTile(Enemy e)
	{
            Location eloc = e.getLocation();
            return ((eloc.getX() >= loc.getX() * TILE_SIZE + 34) && (eloc.getX() <= loc.getX() * TILE_SIZE + TILE_SIZE - 33) &&
                    (eloc.getY() >= loc.getY() * TILE_SIZE + 34) && (eloc.getY() <= loc.getY() * TILE_SIZE + TILE_SIZE - 33));
	}

	public void setEnemyDirections(List<Enemy> enemies)
	{
            for(Enemy e : enemies)
            {
                if(this.hasInTile(e))
                    e.setDirection(direction);
            }
	}
}