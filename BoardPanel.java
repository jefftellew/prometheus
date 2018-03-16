package game;

import java.util.Observer;
import java.util.Observable;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BoardPanel extends JPanel implements Observer
{
    public final static int TOWER_BUTTON_SIZE = 80;
    
    public final static int SHORT_BUTTON_X = 755;
    public final static int SHORT_BUTTON_Y = 300;
    
    public final static int LONG_BUTTON_X = 865;
    public final static int LONG_BUTTON_Y = 300;
    
    public final static int BOOST_BUTTON_X = 865;
    public final static int BOOST_BUTTON_Y = 440;
    
    public final static int FREEZE_BUTTON_X = 755;
    public final static int FREEZE_BUTTON_Y = 440;
    
    private final int HEALTHBAR_HEIGHT = 6;
    private final int HEALTHBAR_WIDTH = 30;
    
    private ImageIcon COIN;
    private ImageIcon HEART;
    
    private ImageIcon SPACESHIP;
    
    private Image LENSFLARE_BLUE;
    private Image LENSFLARE_RED;
    private Image LENSFLARE_LIGHTBLUE;
    
    private Image EXPLOSION_BLUE;
    
    private Image RESTART;
    private Image BACK;

    private Image BASE_IMAGE;
    private Image TILE_IMAGE, TILE_BLUETOWER, TILE_REDTOWER, TILE_GREENTOWER, TILE_WHITETOWER;
    private Image PATH_IMAGE_1;
    private Image PATH_IMAGE_2;
    
    private Image CORNER_IMAGE_1, CORNER_IMAGE_2, CORNER_IMAGE_3, CORNER_IMAGE_4;

    private Image TILE_GRASS1;
    private Image TILE_GRASS2;

    private Image BASIC_ENEMY_IMAGE_RIGHT, BASIC_ENEMY_IMAGE_LEFT;
    private Image BIG_ENEMY_IMAGE_RIGHT, BIG_ENEMY_IMAGE_LEFT;
    private Image FAST_ENEMY_IMAGE_RIGHT, FAST_ENEMY_IMAGE_LEFT;
    private Image MINIBOSS_ENEMY_IMAGE_RIGHT, MINIBOSS_ENEMY_IMAGE_LEFT;
    private Image BOSS_ENEMY_IMAGE_RIGHT, BOSS_ENEMY_IMAGE_LEFT;
    private Image HUGE_ENEMY_IMAGE_RIGHT, HUGE_ENEMY_IMAGE_LEFT;
    private Image BIGFAST_ENEMY_IMAGE_RIGHT, BIGFAST_ENEMY_IMAGE_LEFT;
    
    private Image SHORT_IMAGE_1_LEFT, SHORT_IMAGE_1_RIGHT;
    private Image SHORT_IMAGE_2_LEFT, SHORT_IMAGE_2_RIGHT;
    private Image SHORT_IMAGE_3_LEFT, SHORT_IMAGE_3_RIGHT;
    
    private Image LONG_IMAGE_1_LEFT, LONG_IMAGE_1_RIGHT;
    private Image LONG_IMAGE_2_LEFT, LONG_IMAGE_2_RIGHT;
    private Image LONG_IMAGE_3_LEFT, LONG_IMAGE_3_RIGHT;
    
    private Image BOOST_IMAGE_1_LEFT, BOOST_IMAGE_1_RIGHT;
    private Image BOOST_IMAGE_2_LEFT, BOOST_IMAGE_2_RIGHT;
    private Image BOOST_IMAGE_3_LEFT, BOOST_IMAGE_3_RIGHT;
    
    private Image FREEZE_IMAGE_1_LEFT, FREEZE_IMAGE_1_RIGHT;
    private Image FREEZE_IMAGE_2_LEFT, FREEZE_IMAGE_2_RIGHT;
    private Image FREEZE_IMAGE_3_LEFT, FREEZE_IMAGE_3_RIGHT;
    
    private final static Color SHORT_LASER_COLOR = new Color(72,118,255);
    private final static Color LONG_LASER_COLOR = new Color(255,50,50);
    private final static Color FREEZE_LASER_COLOR = new Color(190,190,190);
    private final static Color FREEZE_HEALTH_COLOR = new Color(135,206,255);
    
    private final static Color SHORT_BUTTON_COLOR = new Color(72,118,255);
    private final static Color LONG_BUTTON_COLOR = new Color(255,50,50);
    private final static Color BOOST_BUTTON_COLOR = new Color(0,185,0);
    private final static Color FREEZE_BUTTON_COLOR = new Color(220,220,220);

    private final static Color BACKGROUND_COLOR = Color.GRAY;
    private final static Color VICTORY_COLOR = new Color(0,238,0);
    private final static Color RANGE_COLOR = new Color(240, 0, 0, 55);
    private final static Color HEALTH_COLOR = new Color(255,64,64);
    private final static Color COIN_COLOR = new Color(238,220,40);
    
    private final static Color CYAN = new Color(27, 226, 217);
    private final static Color DEEPSKYBLUE = new Color(0,191,255);
    private final static Color DODGERBLUE = new Color(28,134,238);

    private static Font HEALTH_FONT;
    private static Font SCORE_FONT;
    private static Font INFO_FONT;
    private static Font NOTIFY_FONT;

    private final static int SHORT_TOWER_COST = ShortTower.getInitialCost();
    private final static int LONG_TOWER_COST = LongTower.getInitialCost();
    private final static int BOOST_TOWER_COST = BoostTower.getInitialCost();
    private final static int FREEZE_TOWER_COST = FreezeTower.getInitialCost();

    private Board board;

    private boolean showNextWaveButton, showingNextWave, victory, gameOver, paused, showTowerCosts;
    
    private boolean addTowerVis;
    private int addTowerIDVis;

    public BoardPanel()
    {
        board = new Board();

        showNextWaveButton = true;
        showingNextWave = false;
        victory = false;
        gameOver = false;
        paused = false;
        showTowerCosts = true;

        setBackground(BACKGROUND_COLOR);

        try
        {   
            //SCOREBOARD IMAGES
            SPACESHIP = new ImageIcon(getClass().getResource("res/images/tiles/base/prometheus_nofire4.png"));
            
            COIN = new ImageIcon(getClass().getResource("res/images/scoreboard/coin.gif"));
            HEART = new ImageIcon(getClass().getResource("res/images/scoreboard/heart.gif"));
            RESTART = ImageIO.read(getClass().getResourceAsStream("res/images/scoreboard/restart.png"));
            BACK = ImageIO.read(getClass().getResourceAsStream("res/images/scoreboard/backarrow.png"));  
            
            //EFFECTS IMAGES
            LENSFLARE_BLUE = ImageIO.read(getClass().getResourceAsStream("res/images/effects/lensflare_blue.png"));
            LENSFLARE_RED = ImageIO.read(getClass().getResourceAsStream("res/images/effects/lensflare_red.png"));
            LENSFLARE_LIGHTBLUE = ImageIO.read(getClass().getResourceAsStream("res/images/effects/lensflare_lightblue.png"));
            
            EXPLOSION_BLUE = ImageIO.read(getClass().getResourceAsStream("res/images/effects/explosion_blue.png"));
            
            //TOWER IMAGES
            
            //short
            SHORT_IMAGE_1_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/rhino2_left.png"));
            SHORT_IMAGE_1_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/rhino2_right.png"));
            
            SHORT_IMAGE_2_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/volt_left.png"));
            SHORT_IMAGE_2_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/volt_right.png"));
            
            SHORT_IMAGE_3_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/loki1b_left.png"));
            SHORT_IMAGE_3_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/loki1b_right.png"));
    
            //long
            LONG_IMAGE_1_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/rhino4_left.png"));
            LONG_IMAGE_1_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/rhino4_right.png"));
            
            LONG_IMAGE_2_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/rhino3_left.png"));
            LONG_IMAGE_2_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/rhino3_right.png"));
            
            LONG_IMAGE_3_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/loki2_left.png"));
            LONG_IMAGE_3_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/loki2_right.png"));
    
            //boost
            BOOST_IMAGE_1_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/ash1_green_left.png"));
            BOOST_IMAGE_1_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/ash1_green_right.png"));
            
            BOOST_IMAGE_2_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/ember_greener_left.png"));
            BOOST_IMAGE_2_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/ember_greener_right.png"));
            
            BOOST_IMAGE_3_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/ash2_greener_left.png"));
            BOOST_IMAGE_3_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/ash2_greener_right.png"));
            
            //freeze
            FREEZE_IMAGE_1_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/saryn4_left.png"));
            FREEZE_IMAGE_1_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/saryn4_right.png"));
            
            FREEZE_IMAGE_2_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/trinity2_left.png"));
            FREEZE_IMAGE_2_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/trinity2_right.png"));
            
            FREEZE_IMAGE_3_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/frost2_left.png"));
            FREEZE_IMAGE_3_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/towers/frost2_right.png"));
            
            //ENEMY IMAGES

            BASIC_ENEMY_IMAGE_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/jetpack_left.png"));
            BIG_ENEMY_IMAGE_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/ancient_left.png"));
            FAST_ENEMY_IMAGE_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/osprey_left.png"));
            MINIBOSS_ENEMY_IMAGE_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/sargas_left.png"));
            BOSS_ENEMY_IMAGE_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/vor_left.png"));
            HUGE_ENEMY_IMAGE_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/kela_left.png"));
            BIGFAST_ENEMY_IMAGE_LEFT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/runner_left.png"));
            
            BASIC_ENEMY_IMAGE_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/jetpack_right.png"));
            BIG_ENEMY_IMAGE_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/ancient_right.png"));
            FAST_ENEMY_IMAGE_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/osprey_right.png"));
            MINIBOSS_ENEMY_IMAGE_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/sargas_right.png"));
            BOSS_ENEMY_IMAGE_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/vor_right.png"));
            HUGE_ENEMY_IMAGE_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/kela_right.png"));
            BIGFAST_ENEMY_IMAGE_RIGHT = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/runner_right.png"));
            
            //BOARD IMAGES

            BASE_IMAGE = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/base/base_cyan.png"));
            TILE_IMAGE = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/tile/tile_gray.png"));
            
            TILE_BLUETOWER = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/tile/tile_bluetower.png"));
            TILE_REDTOWER = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/tile/tile_redtower.png"));
            TILE_GREENTOWER = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/tile/tile_greentower.png"));
            TILE_WHITETOWER = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/tile/tile_whitetower.png"));
            
            PATH_IMAGE_1 = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/path/path_white_1.png"));
            PATH_IMAGE_2 = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/path/path_white_2.png"));
            
            CORNER_IMAGE_1 = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/corner/corner_white_1.png"));
            CORNER_IMAGE_2 = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/corner/corner_white_2.png"));
            CORNER_IMAGE_3 = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/corner/corner_white_3.png"));
            CORNER_IMAGE_4 = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/corner/corner_white_4.png"));
        } catch (IOException e){
            System.out.println(e);
        }
        
        try
        {
            HEALTH_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(12f);
            SCORE_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(24f);
            INFO_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(36f);
            NOTIFY_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(72f);
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")));
        } catch (FontFormatException | IOException e){
            System.out.println(e);
        }
    }
    
    public void setShowNextWaveButton(boolean b)
    {
    	showNextWaveButton = b;
    }

    public void setShowingNextWave(boolean b)
    {
        showingNextWave = b;
    }

    public void setAddTowerVis(boolean b)
    {
        addTowerVis = b;
    }

    public void setAddingTowerIDVis(int i)
    {
        addTowerIDVis = i;
    }
    
    public void setPaused(boolean b)
    {
        paused = b;
    }

    public void setVictory(boolean b)
    {
        victory = b;
    }

    public void setGameOver(boolean b)
    {
        gameOver = b;
    }
    
    public void setShowTowerCosts(boolean b)
    {
        showTowerCosts = b;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        //**********************************
        //           BOARD TILES
        //**********************************

        int tilesize = board.getTilesize();

        BoardLocation[][] boardArray = board.getBoard();
        
        for(int col = 0; col < boardArray.length; col++)
        {
            for(int row = 0; row < boardArray[0].length; row++)
            {    
            	BoardLocation boardLoc = boardArray[col][row];
                
            	int boardX = boardLoc.getLocation().getX();
                int boardY = boardLoc.getLocation().getY();

                if(boardLoc.isPath() && !boardLoc.isPathCorner())
                {                   
                    if(boardLoc.getTravelDirection() == 1 || boardLoc.getTravelDirection() == 3)
                        g.drawImage(PATH_IMAGE_1, boardX * tilesize, boardY * tilesize, null);
                    else
                        g.drawImage(PATH_IMAGE_2, boardX * tilesize, boardY * tilesize, null);  
                }

                else
                {
                    if(boardLoc.hasTower()) {
                        switch(boardLoc.getTower().getTowerType()) {
                            case 1:
                                g.drawImage(TILE_BLUETOWER, boardX * tilesize, boardY * tilesize, null);
                                break;
                            case 2:
                                g.drawImage(TILE_REDTOWER, boardX * tilesize, boardY * tilesize, null);
                                break;
                            case 3:
                                g.drawImage(TILE_GREENTOWER, boardX * tilesize, boardY * tilesize, null);
                                break;
                            case 4:
                                g.drawImage(TILE_WHITETOWER, boardX * tilesize, boardY * tilesize, null);
                                break;
                            default:
                                break;
                        }
                    }
                    else {
                        g.drawImage(TILE_IMAGE, boardX * tilesize, boardY * tilesize, null);
                    }
                }
            }
        }
        
        //DRAW CORNERS
        g.drawImage(CORNER_IMAGE_2, 4 * tilesize, 1 * tilesize, null);
        g.drawImage(CORNER_IMAGE_3, 4 * tilesize, 3 * tilesize, null);
        g.drawImage(CORNER_IMAGE_1, 1 * tilesize, 3 * tilesize, null);
        g.drawImage(CORNER_IMAGE_4, 1 * tilesize, 8 * tilesize, null);
        g.drawImage(CORNER_IMAGE_3, 4 * tilesize, 8 * tilesize, null);
        g.drawImage(CORNER_IMAGE_1, 4 * tilesize, 5 * tilesize, null);
        g.drawImage(CORNER_IMAGE_3, 6 * tilesize, 5 * tilesize, null);
        g.drawImage(CORNER_IMAGE_1, 6 * tilesize, 1 * tilesize, null);
        g.drawImage(CORNER_IMAGE_2, 8 * tilesize, 1 * tilesize, null);   

        //DRAW BASE
        g.drawImage(BASE_IMAGE, 8 * tilesize, 8 * tilesize, null);

        //**********************************
        //              TOWERS
        //**********************************

        Tower selectedTower = null;
        
        if(!board.getTowers().isEmpty())
        {
            for(Tower t : board.getTowers())
            {   
                if(t.isSelected())
                {
                    selectedTower = t;
                    g.setColor(Color.WHITE);
                    g.fillRect(t.getLocation().getX() - 20, t.getLocation().getY() + 20, 40, 3);
                }
                
                if(t.getTarget() == null || t.getTarget().getX() <= t.getLocation().getX())
                {
                    //SHORT TOWERS
                    if(t.getTowerType() == 1)
                    {
                        switch(t.getRank())
                        {
                            case 1:
                                g.drawImage(SHORT_IMAGE_1_LEFT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 2:
                                g.drawImage(SHORT_IMAGE_2_LEFT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 3:
                                g.drawImage(SHORT_IMAGE_3_LEFT, t.getLocation().getX() - 30, t.getLocation().getY() - 50, null);
                                break;
                        }
                    }

                    //LONG TOWERS
                    else if(t.getTowerType() == 2)
                    {
                        switch(t.getRank())
                        {
                            case 1:
                                g.drawImage(LONG_IMAGE_1_LEFT, t.getLocation().getX() - 20, t.getLocation().getY() - 50, null);
                                break;
                            case 2:
                                g.drawImage(LONG_IMAGE_2_LEFT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 3:
                                g.drawImage(LONG_IMAGE_3_LEFT, t.getLocation().getX() - 30, t.getLocation().getY() - 50, null);
                                break;
                        }
                    }

                    //BOOST TOWERS
                    else if(t.getTowerType() == 3)
                    {
                        switch(t.getRank())
                        {
                            case 1:
                                g.drawImage(BOOST_IMAGE_1_LEFT, t.getLocation().getX() - 23, t.getLocation().getY() - 50, null);
                                break;
                            case 2:
                                g.drawImage(BOOST_IMAGE_2_LEFT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 3:
                                g.drawImage(BOOST_IMAGE_3_LEFT, t.getLocation().getX() - 30, t.getLocation().getY() - 50, null);
                                break;
                        }
                    }
                    
                  //FREEZE TOWERS
                    else if(t.getTowerType() == 4)
                    {
                        switch(t.getRank())
                        {
                            case 1:
                                g.drawImage(FREEZE_IMAGE_1_LEFT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 2:
                                g.drawImage(FREEZE_IMAGE_2_LEFT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 3:
                                g.drawImage(FREEZE_IMAGE_3_LEFT, t.getLocation().getX() - 20, t.getLocation().getY() - 50, null);
                                break;
                        }
                    }
                }
                else if(t.getTarget() != null && t.getTarget().getX() > t.getLocation().getX())
                {
                    //SHORT TOWERS
                    if(t.getTowerType() == 1)
                    {
                        switch(t.getRank())
                        {
                            case 1:
                                g.drawImage(SHORT_IMAGE_1_RIGHT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 2:
                                g.drawImage(SHORT_IMAGE_2_RIGHT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 3:
                                g.drawImage(SHORT_IMAGE_3_RIGHT, t.getLocation().getX() - 30, t.getLocation().getY() - 50, null);
                                break;
                        }
                    }

                    //LONG TOWERS
                    else if(t.getTowerType() == 2)
                    {
                        switch(t.getRank())
                        {
                            case 1:
                                g.drawImage(LONG_IMAGE_1_RIGHT, t.getLocation().getX() - 20, t.getLocation().getY() - 50, null);
                                break;
                            case 2:
                                g.drawImage(LONG_IMAGE_2_RIGHT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 3:
                                g.drawImage(LONG_IMAGE_3_RIGHT, t.getLocation().getX() - 30, t.getLocation().getY() - 50, null);
                                break;
                        }
                    }

                    //BOOST TOWERS
                    else if(t.getTowerType() == 3)
                    {
                        switch(t.getRank())
                        {
                            case 1:
                                g.drawImage(BOOST_IMAGE_1_RIGHT, t.getLocation().getX() - 23, t.getLocation().getY() - 50, null);
                                break;
                            case 2:
                                g.drawImage(BOOST_IMAGE_2_RIGHT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 3:
                                g.drawImage(BOOST_IMAGE_3_RIGHT, t.getLocation().getX() - 30, t.getLocation().getY() - 50, null);
                                break;
                        }
                    }
                    
                    //FREEZE TOWERS
                    else if(t.getTowerType() == 4)
                    {
                        switch(t.getRank())
                        {
                            case 1:
                                g.drawImage(FREEZE_IMAGE_1_RIGHT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 2:
                                g.drawImage(FREEZE_IMAGE_2_RIGHT, t.getLocation().getX() - 25, t.getLocation().getY() - 50, null);
                                break;
                            case 3:
                                g.drawImage(FREEZE_IMAGE_3_RIGHT, t.getLocation().getX() - 20, t.getLocation().getY() - 50, null);
                                break;
                        }
                    }
                }
            }
            
            
        }
        
        //**********************************
    	//              ENEMIES
    	//**********************************

            for(int i = board.enemiesLeft() - 1; i >= 0; i--)
            {
                Enemy e = board.getEnemies().get(i);

                if(e.getDirection() == 1 || e.getDirection() == 2)
                {
                    switch (e.getEnemyType())
                    {
                        //Basic
                        case 1:
                            g2d.drawImage(BASIC_ENEMY_IMAGE_RIGHT, e.getX() - 20, e.getY() - 43, null);
                            break;
                        //Big enemy
                        case 2:
                            g2d.drawImage(BIG_ENEMY_IMAGE_RIGHT, e.getX() - 30, e.getY() - 52, null);
                            break;
                        //Fast enemy
                        case 3:
                            g2d.drawImage(FAST_ENEMY_IMAGE_RIGHT, e.getX() - 20, e.getY() - 20, null);
                            break;
                        //MiniBoss enemy
                        case 4:
                            g2d.drawImage(MINIBOSS_ENEMY_IMAGE_RIGHT, e.getX() - 28, e.getY() - 63, null);
                            break;
                        //Boss enemy
                        case 5:
                            g2d.drawImage(BOSS_ENEMY_IMAGE_RIGHT, e.getX() - 35, e.getY() - 73, null);
                            break;
                        //Huge enemy
                        case 6:
                            g2d.drawImage(HUGE_ENEMY_IMAGE_RIGHT, e.getX() - 33, e.getY() - 65, null);
                            break;
                        case 7:
                        	g2d.drawImage(BIGFAST_ENEMY_IMAGE_RIGHT, e.getX() - 22, e.getY() - 46, null);
                        	break;
                        default:
                            break;
                    }
                }
                else
                {
                    switch (e.getEnemyType())
                    {
                        //Basic
                        case 1:
                            g2d.drawImage(BASIC_ENEMY_IMAGE_LEFT, e.getX() - 20, e.getY() - 43, null);
                            break;
                        //Big enemy
                        case 2:
                            g2d.drawImage(BIG_ENEMY_IMAGE_LEFT, e.getX() - 30, e.getY() - 52, null);
                            break;
                        //Fast enemy
                        case 3:
                            g2d.drawImage(FAST_ENEMY_IMAGE_LEFT, e.getX() - 20, e.getY() - 20, null);
                            break;
                        //MiniBoss enemy
                        case 4:
                            g2d.drawImage(MINIBOSS_ENEMY_IMAGE_LEFT, e.getX() - 40, e.getY() - 63, null);
                            break;
                        //Boss enemy
                        case 5:
                            g2d.drawImage(BOSS_ENEMY_IMAGE_LEFT, e.getX() - 35, e.getY() - 73, null);
                            break;
                        //Huge enemy
                        case 6:
                            g2d.drawImage(HUGE_ENEMY_IMAGE_LEFT, e.getX() - 23, e.getY() - 65, null);
                            break;
                        case 7:
                            g2d.drawImage(BIGFAST_ENEMY_IMAGE_LEFT, e.getX() - 22, e.getY() - 46, null);
                            break;
                        default:
                            break;
                    }
                }
                

                //DRAW HEALTHBAR BORDER
                g.setColor(Color.BLACK);
                g.fillRect(e.getX() - 13, e.getY() + 27, HEALTHBAR_WIDTH + 6, HEALTHBAR_HEIGHT + 6);
                g.setColor(Color.WHITE);
                g.fillRect(e.getX() - 12, e.getY() + 28, HEALTHBAR_WIDTH + 4, HEALTHBAR_HEIGHT + 4);
                g.setColor(Color.BLACK);
                g.fillRect(e.getX() - 10, e.getY() + 30, HEALTHBAR_WIDTH,  HEALTHBAR_HEIGHT);

                //DETERMINE HEALTHBAR FILL COLOR
                if(e.getSpeed() == 0) {
                	g.setColor(FREEZE_HEALTH_COLOR);
                } else if (e.getHealthPercent() > 0.60) {
                    g.setColor(Color.GREEN);
                } else if (e.getHealthPercent() > 0.30) {
                    g.setColor(Color.ORANGE);
                } else {
                    g.setColor(Color.RED);
                }

                //FILL HEALTHBAR
                if(e.getHealth() > 0)
                	g.fillRect(e.getX() - 10, e.getY() + 30, (int)(HEALTHBAR_WIDTH * e.getHealthPercent()), HEALTHBAR_HEIGHT);
            }
            
            for(Tower t : board.getTowers())
            {
                if (t.isAttacking() && t.getTarget() != null) {
                    switch (t.getTowerType()) {
                        case 1:
                            g2d.setColor(SHORT_LASER_COLOR);
                            break;
                        case 2:
                            g2d.setColor(LONG_LASER_COLOR);
                            break;
                        case 3:
                            break;
                        case 4:
                            g2d.setColor(FREEZE_LASER_COLOR);
                            break;
                        default:
                            break;
                    }

                    double thickness = 4;
                    Stroke oldStroke = g2d.getStroke();
                    g2d.setStroke(new BasicStroke((float) thickness));

                    if (t.getTarget().getX() <= t.getLocation().getX()) {
                        g2d.drawLine(t.getLocation().getX() - 20, t.getLocation().getY() - 10, t.getTarget().getX(), t.getTarget().getY());
                    } else {
                        g2d.drawLine(t.getLocation().getX() + 20, t.getLocation().getY() - 10, t.getTarget().getX(), t.getTarget().getY());
                    }

                    thickness = 0.25;
                    g2d.setStroke(new BasicStroke((float) thickness));
                    g2d.setColor(Color.WHITE);
                    
                    int flareX;
                    
                    if (t.getTarget().getX() <= t.getLocation().getX()) {
                        g2d.drawLine(t.getLocation().getX() - 20, t.getLocation().getY() - 10, t.getTarget().getX(), t.getTarget().getY());
                        flareX = t.getLocation().getX() - 32;
                    } else {
                        g2d.drawLine(t.getLocation().getX() + 20, t.getLocation().getY() - 10, t.getTarget().getX(), t.getTarget().getY());
                        flareX = t.getLocation().getX() + 8;
                    }
                    
                    g2d.setStroke(oldStroke);
                    
                    switch (t.getTowerType()) {
                        case 1:
                            g.drawImage(LENSFLARE_BLUE, flareX, t.getLocation().getY() - 25, null);
                            break;
                        case 2:
                            g.drawImage(LENSFLARE_RED, flareX, t.getLocation().getY() - 25, null);
                            break;
                        case 3:
                            break;
                        case 4:
                            g.drawImage(LENSFLARE_LIGHTBLUE, flareX, t.getLocation().getY() - 25, null);
                            break;
                        default:
                            break;
                    }
                }
            }
            
            SPACESHIP.paintIcon(this, g2d, 435, 575);
            
            //Gotta draw that range circle yo
            if(selectedTower != null)
            {
            	Tower t = selectedTower;
            	
            	g2d.setColor(Color.RED);
            	double thickness = 3;
                Stroke oldStroke = g2d.getStroke();
                g2d.setStroke(new BasicStroke((float) thickness));
                g2d.drawOval(t.getLocation().getX() - t.getRange(), t.getLocation().getY() - t.getRange(), 2 * t.getRange(), 2 * t.getRange());
                g2d.setStroke(oldStroke);
                
                g2d.setColor(RANGE_COLOR);
                g2d.fillOval(t.getLocation().getX() - t.getRange(), t.getLocation().getY() - t.getRange(), 2 * t.getRange(), 2 * t.getRange());
            }

        //**********************************
	//          SCOREBOARD
	//**********************************
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(700, 0, 302, 710);
        
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(711, 11, 280, 678);
        
        g2d.setFont(SCORE_FONT);

        //Score
        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + board.getScore(), 800, 50);

        g2d.setFont(INFO_FONT);
        
        //Lives
        g2d.setColor(HEALTH_COLOR);
        if (board.getLives() > 0) {
            g2d.drawString("" + board.getLives(), 780, 110);
        } else {
            g2d.drawString("0", 780, 110);
        }
        
        HEART.paintIcon(this, g2d, 720, 65);

        //Coins
        g2d.setColor(COIN_COLOR);
        if(board.getCoins() > 999)
        	g2d.setFont(SCORE_FONT);
        g2d.drawString("" + board.getCoins(), 900, 110);
        COIN.paintIcon(this, g2d, 840, 70);

        g2d.setFont(SCORE_FONT);
        
        //Current wave
        g2d.setColor(Color.WHITE);
        g2d.drawString("Wave: " + board.getCurrentWave(), 800, 170);

        //Control button
        g.setColor(Color.BLACK);
        g.fillRect(745, 195, 210, 60);
        g.setColor(Color.WHITE);
        g.fillRect(750, 200, 200, 50);
        g2d.setFont(SCORE_FONT);
        g2d.setColor(Color.BLACK);
        
        if(paused && !showNextWaveButton)
        {               
            g2d.drawString("Resume", 803, 235);
        }
        else if(!showNextWaveButton)
        {    
            g2d.drawString("Pause", 812, 235);
        }
        
        if(showNextWaveButton)
        {    
            g2d.drawString("Start Wave", 780, 235);
        }

        //Draw box around selected tower if one is being built
        if(addTowerVis)
        {
            g.setColor(Color.WHITE);
            switch(addTowerIDVis)
            {
                case 1:
                    g.fillRect(SHORT_BUTTON_X - 5, SHORT_BUTTON_Y - 5, TOWER_BUTTON_SIZE + 10, TOWER_BUTTON_SIZE + 10);
                    break;
                case 2:
                    g.fillRect(LONG_BUTTON_X - 5, LONG_BUTTON_Y - 5, TOWER_BUTTON_SIZE + 10, TOWER_BUTTON_SIZE + 10);
                    break;
                case 3:
                    g.fillRect(BOOST_BUTTON_X - 5, BOOST_BUTTON_Y - 5, TOWER_BUTTON_SIZE + 10, TOWER_BUTTON_SIZE + 10);
                    break;
                case 4:
                    g.fillRect(FREEZE_BUTTON_X - 5, FREEZE_BUTTON_Y - 5, TOWER_BUTTON_SIZE + 10, TOWER_BUTTON_SIZE + 10);
                    break;
            }
        }

        //Draw tower build buttons
        g2d.setFont(SCORE_FONT);

        //Draw short tower buy button
        g.setColor(SHORT_BUTTON_COLOR);
        g.fillRect(SHORT_BUTTON_X, SHORT_BUTTON_Y, TOWER_BUTTON_SIZE, TOWER_BUTTON_SIZE);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(SHORT_BUTTON_X + 5, SHORT_BUTTON_Y + 5, TOWER_BUTTON_SIZE - 10, TOWER_BUTTON_SIZE - 10);

        if (showTowerCosts) {
            if (board.getCoins() >= SHORT_TOWER_COST) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.RED);
            }

            g.drawString("" + SHORT_TOWER_COST, SHORT_BUTTON_X + 22, SHORT_BUTTON_Y + TOWER_BUTTON_SIZE + 27);
        }
        
        g.drawImage(SHORT_IMAGE_1_LEFT, SHORT_BUTTON_X + 14, SHORT_BUTTON_Y + 5, null);

        //Draw long tower buy button
        g.setColor(LONG_BUTTON_COLOR);
        g.fillRect(LONG_BUTTON_X, LONG_BUTTON_Y, TOWER_BUTTON_SIZE, TOWER_BUTTON_SIZE);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(LONG_BUTTON_X + 5, LONG_BUTTON_Y + 5, TOWER_BUTTON_SIZE - 10, TOWER_BUTTON_SIZE - 10);

        if (showTowerCosts) {
            if (board.getCoins() >= LONG_TOWER_COST) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.RED);
            }
            g2d.drawString("" + LONG_TOWER_COST, LONG_BUTTON_X + 22, LONG_BUTTON_Y + TOWER_BUTTON_SIZE + 27);
        } 
        
        g.drawImage(LONG_IMAGE_1_LEFT, LONG_BUTTON_X + 18, LONG_BUTTON_Y + 5, null);
        
        //Draw boost tower buy button
        g.setColor(BOOST_BUTTON_COLOR);
        g.fillRect(BOOST_BUTTON_X, BOOST_BUTTON_Y, TOWER_BUTTON_SIZE, TOWER_BUTTON_SIZE);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(BOOST_BUTTON_X + 5, BOOST_BUTTON_Y + 5, TOWER_BUTTON_SIZE - 10, TOWER_BUTTON_SIZE - 10);

        if (showTowerCosts) {
            if (board.getCoins() >= BOOST_TOWER_COST) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.RED);
            }
            g2d.drawString("" + BOOST_TOWER_COST, BOOST_BUTTON_X + 16, BOOST_BUTTON_Y + TOWER_BUTTON_SIZE + 27);
        }
        
        g.drawImage(BOOST_IMAGE_1_LEFT, BOOST_BUTTON_X + 18, BOOST_BUTTON_Y + 5, null);
        
        //Draw boost tower buy button
        g.setColor(FREEZE_BUTTON_COLOR);
        g.fillRect(FREEZE_BUTTON_X, BOOST_BUTTON_Y, TOWER_BUTTON_SIZE, TOWER_BUTTON_SIZE);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(FREEZE_BUTTON_X + 5, FREEZE_BUTTON_Y + 5, TOWER_BUTTON_SIZE - 10, TOWER_BUTTON_SIZE - 10);

        if (showTowerCosts) {
            if (board.getCoins() >= FREEZE_TOWER_COST) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.RED);
            }
            g2d.drawString("" + FREEZE_TOWER_COST, FREEZE_BUTTON_X + 16, FREEZE_BUTTON_Y + TOWER_BUTTON_SIZE + 27);
        }

        g2d.drawImage(FREEZE_IMAGE_1_LEFT, FREEZE_BUTTON_X + 13, FREEZE_BUTTON_Y + 6, null);
        
        //RESTART BUTTON
        g.setColor(Color.DARK_GRAY);
        g.fillRect(760, 600, 60, 60);
        g.setColor(CYAN);
        g.fillRect(765, 605, 50, 50);
        g2d.drawImage(RESTART, 770, 608, null);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(880, 600, 60, 60);
        g.setColor(CYAN);
        g.fillRect(885, 605, 50, 50);
        g2d.drawImage(BACK, 880, 600, null);

        //**********************************
	//        BOARD NOTIFY TEXT
	//**********************************

        //IF NEXTWAVE, SHOW
        if(showingNextWave)
        {
            g.setFont(NOTIFY_FONT);

            if(board.getCurrentWave() < 9)
            {
                g.setColor(Color.BLACK);
                g.fillRect(170, 290, 360, 120);

                g.setColor(Color.WHITE);
                g.fillRect(180, 300, 340, 100);
                
                g2d.setColor(Color.BLACK);
                g2d.drawString("Wave " + (board.getCurrentWave() + 1), 205, 375);
            }
            else
            {
                g.setColor(Color.BLACK);
                g.fillRect(160, 290, 380, 120);

                g.setColor(Color.WHITE);
                g.fillRect(170, 300, 360, 100);
                
                g2d.setColor(Color.BLACK);
                g2d.drawString("Wave " + (board.getCurrentWave() + 1), 185, 375);
            }
        }

        //DRAW WIN TEXT
        if(victory)
        {
            g.setColor(VICTORY_COLOR);
            g.fillRect(150, 265, 460, 120);
            g.setColor(Color.GRAY);
            g.fillRect(160, 275, 440, 100);

            g2d.setColor(VICTORY_COLOR);
            g2d.setFont(NOTIFY_FONT);
            g2d.drawString("VICTORY", 200, 355);
        }

        //DRAW DEATH TEXT
        if(gameOver)
        {
            g.setColor(Color.RED);
            g.fillRect(90, 265, 530, 120);
            g.setColor(Color.GRAY);
            g.fillRect(100, 275, 510, 100);

            g2d.setColor(Color.RED);
            g2d.setFont(NOTIFY_FONT);
            g2d.drawString("GAME OVER", 115, 355);
        }
    }

    @Override
    public void update(Observable model, Object arg)
    {
        board = (Board) model;
        repaint();
    }
}
