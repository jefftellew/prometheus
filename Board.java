package game;

import java.util.Observable;
import java.util.List;
import java.util.ArrayList;

public class Board extends Observable {

    private final static int GRID_SIZE = 10;
    private final static int TILE_SIZE = BoardLocation.getTilesize();
    private final int SIZE = GRID_SIZE * TILE_SIZE;

    private BoardLocation[][] board;
    private List<Enemy> enemies;
    private List<Enemy> activeWave;
    private List<Tower> towers;
    private WaveMap wavemap;

    private int lives;
    private int coins;
    private int score;
    private int wave;

    private boolean gameOver;

    public Board() {
        lives = 10;
        coins = 100;
        score = 0;
        wave = 0;

        gameOver = false;

        board = new BoardLocation[GRID_SIZE][GRID_SIZE];
        towers = new ArrayList<Tower>();
        wavemap = new WaveMap();
        activeWave = null;
        enemies = new ArrayList<Enemy>();

        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                board[col][row] = new BoardLocation(new Location(col, row));
            }
        }

        createBoard();
    }

    //**********************************
    //          SCORE METHODS
    //**********************************
    public boolean gameOver() {
        return gameOver;
    }

    public int getTilesize() {
        return TILE_SIZE;
    }

    public int getLives() {
        return lives;
    }

    public int getCoins() {
        return coins;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentWave() {
        return wave;
    }

    public int getSize() {
        return SIZE;
    }

    private void createBoard() {
        board[0][1] = new BoardLocation(new Location(0, 1), 1, true, false);
        board[1][1] = new BoardLocation(new Location(1, 1), 1, true, false);
        board[2][1] = new BoardLocation(new Location(2, 1), 1, true, false);
        board[3][1] = new BoardLocation(new Location(3, 1), 1, true, false);
        board[4][1] = new BoardLocation(new Location(4, 1), 2, true, true);
        board[6][1] = new BoardLocation(new Location(6, 1), 1, true, true);
        board[7][1] = new BoardLocation(new Location(7, 1), 1, true, false);
        board[8][1] = new BoardLocation(new Location(8, 1), 2, true, true);
        board[4][2] = new BoardLocation(new Location(4, 2), 2, true, false);
        board[6][2] = new BoardLocation(new Location(6, 2), 4, true, false);
        board[8][2] = new BoardLocation(new Location(8, 2), 2, true, false);

        board[1][3] = new BoardLocation(new Location(1, 3), 2, true, true);
        board[2][3] = new BoardLocation(new Location(2, 3), 3, true, false);
        board[3][3] = new BoardLocation(new Location(3, 3), 3, true, false);
        board[4][3] = new BoardLocation(new Location(4, 3), 3, true, true);
        board[6][3] = new BoardLocation(new Location(6, 3), 4, true, false);
        board[8][3] = new BoardLocation(new Location(8, 3), 2, true, false);

        board[1][4] = new BoardLocation(new Location(1, 4), 2, true, false);
        board[6][4] = new BoardLocation(new Location(6, 4), 4, true, false);
        board[8][4] = new BoardLocation(new Location(8, 4), 2, true, false);

        board[1][5] = new BoardLocation(new Location(1, 5), 2, true, false);
        board[4][5] = new BoardLocation(new Location(4, 5), 1, true, true);
        board[5][5] = new BoardLocation(new Location(5, 5), 1, true, false);
        board[6][5] = new BoardLocation(new Location(6, 5), 4, true, true);
        board[8][5] = new BoardLocation(new Location(8, 5), 2, true, false);

        board[1][6] = new BoardLocation(new Location(1, 6), 2, true, false);
        board[4][6] = new BoardLocation(new Location(4, 6), 4, true, false);
        board[8][6] = new BoardLocation(new Location(8, 6), 2, true, false);

        board[1][7] = new BoardLocation(new Location(1, 7), 2, true, false);
        board[4][7] = new BoardLocation(new Location(4, 7), 4, true, false);
        board[8][7] = new BoardLocation(new Location(8, 7), 2, true, false);

        board[1][8] = new BoardLocation(new Location(1, 8), 1, true, true);
        board[2][8] = new BoardLocation(new Location(2, 8), 1, true, false);
        board[3][8] = new BoardLocation(new Location(3, 8), 1, true, false);
        board[4][8] = new BoardLocation(new Location(4, 8), 4, true, true);
    }

    //**********************************
    //          ENEMY METHODS
    //**********************************
    public void addEnemy() {
        enemies.add(new BasicEnemy());
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean hasActiveEnemies() {
        return !enemies.isEmpty();
    }

    public int enemiesLeft() {
        return enemies.size();
    }

    public int enemiesInactive() {
        if (activeWave != null) {
            return activeWave.size();
        }
        return 0;
    }

    public void spawnNextEnemy() {
        enemies.add(activeWave.remove(0));
    }

    private void removeDeadEnemies() {
        Enemy e;

        for (int i = enemies.size() - 1; i >= 0; i--) {
            e = enemies.get(i);

            if (e.getHealth() <= 0) {
                score += e.getCoinValue();

                if (coins + e.getCoinValue() < 999) {
                    coins += e.getCoinValue();
                } else if (coins != 999) {
                    coins = 999;
                }

                enemies.remove(i);
            }
        }
    }

    public void startNextWave() {
        wave++;
        activeWave = wavemap.getWave(wave);
    }

    public boolean isInBase(Enemy e) {
        Location eloc = e.getLocation();
        return (eloc.getX() >= 8 * TILE_SIZE) && (eloc.getX() < 9 * TILE_SIZE) && (eloc.getY() >= 8 * TILE_SIZE) && (eloc.getY() < 9 * TILE_SIZE);
    }

    private void updateEnemyDirections() {
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                if (board[col][row].isPathCorner()) {
                    board[col][row].setEnemyDirections(enemies);
                }
            }
        }
    }

    private void updateLives() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (isInBase(enemies.get(i))) {
                if (enemies.get(i).getEnemyType() == 5) {
                    lives -= 5;
                } else if (enemies.get(i).getEnemyType() == 4) {
                    lives -= 3;
                } else {
                    lives--;
                }

                enemies.remove(i);
            }

            if (lives <= 0) {
                gameOver = true;
                break;
            }
        }
    }

    public void updateFrozenEnemies(int time) {
        for (Enemy e : enemies) {
            if (e.getFreezeTime() > 0) {
                e.updateFreezeTime(time);
            } else if (e.getFreezeTime() <= 0 && e.getSpeed() <= 0) {
                e.unfreeze();
            }
        }
    }

    public void moveEnemies() {
        updateLives();
        removeDeadEnemies();
        updateEnemyDirections();

        for (Enemy e : enemies) {
            switch (e.getDirection()) {
                case 1:
                    e.setX(e.getX() + e.getSpeed());
                    e.travel(e.getSpeed());
                    break;
                case 2:
                    e.setY(e.getY() + e.getSpeed());
                    e.travel(e.getSpeed());
                    break;
                case 3:
                    e.setX(e.getX() - e.getSpeed());
                    e.travel(e.getSpeed());
                    break;
                case 4:
                    e.setY(e.getY() - e.getSpeed());
                    e.travel(e.getSpeed());
                    break;
            }
        }

        setChanged();
        notifyObservers();
    }

    //**********************************
    //          TOWER METHODS
    //**********************************
    public List<Tower> getTowers() {
        return towers;
    }

    public int getActiveTowers() {
        return towers.size();
    }

    public void addTower(Location l, Tower t) {
        if (coins >= t.getCost()) {
            BoardLocation b = board[l.getX() / TILE_SIZE][l.getY() / TILE_SIZE];

            if (!b.hasTower() && !b.isPath()) {
                towers.add(t);
                b.build(t);

                if (t.getTowerType() == 3) {
                    BoostTower booster = (BoostTower) t;
                    booster.boostAll(towers);
                } else {
                    for (Tower w : towers) {
                        if (w.getTowerType() == 3) {
                            BoostTower booster = (BoostTower) w;
                            if (booster.getDistanceFromTower(t) <= booster.getRange()) {
                                booster.boostSingle(t);
                            }
                        }
                    }
                }

                coins -= t.getCost();
            }
            else {
                BasicTower.decrIDcounter();
            }
        }

        setChanged();
        notifyObservers();
    }

    public void upgradeTower(Tower t) {
        if (coins >= t.getUpgradeCost()) {
            coins -= t.getUpgradeCost();
            t.upgrade();
        }
    }

    public void sellTower(Location l) {
        BoardLocation b = board[l.getX() / TILE_SIZE][l.getY() / TILE_SIZE];

        coins += ((b.getTower().getCost() / 25) / 2 * 25);

        Tower removedTower = b.getTower();
        int towerid = removedTower.getTowerID();

        if (removedTower.getTowerType() == 3) {
            BoostTower boostah = (BoostTower) removedTower;

            boostah.deboostAll(towers);
        }

        towers.remove(b.getTower().getTowerID());

        b.sellTower();

        for (Tower t : towers) {
            if (t.getTowerID() > towerid) {
                t.decrID();
            }
        }

        BasicTower.decrIDcounter();

        setChanged();
        notifyObservers();
    }

    public void towersAttack() {
        for (Tower t : towers) {
            if (t.hasEnemiesInRange(enemies)) {
                if (t.getLifetime() % t.getAttackSpeed() == 0) {
                    t.attack(enemies);
                    t.setAttacking(true);
                } else if (t.getLifetime() % t.getAttackSpeed() >= 100) {
                    t.setAttacking(false);
                }
                t.updateLifetime(25);
            } else {
                if (t.getLifetime() > 0) {
                    t.updateLifetime(25);
                    if (t.getLifetime() % t.getAttackSpeed() == 0) {
                        t.setLifetime(0);
                    }
                } else {
                    t.setLifetime(0);
                }

                t.setAttacking(false);
            }
        }
    }

    public Tower getTowerAt(Location l) {
        BoardLocation b = board[l.getX() / TILE_SIZE][l.getY() / TILE_SIZE];
        if (b.hasTower()) {
            return b.getTower();
        }
        return null;
    }

    public BoardLocation[][] getBoard() {
        return board;
    }
}
