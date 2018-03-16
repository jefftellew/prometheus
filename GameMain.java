package game;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.sound.sampled.Clip;

public class GameMain extends JFrame implements ActionListener, MouseListener, ComponentListener
{
    private final DecimalFormat dec = new DecimalFormat("0.00");
	
    private final static int TIMER_SPEED = 25;
    private final static int BAR_SIZE = 25;
    private final static int SPAWN_TIME = 900;

    private Container container;
    private Board board;
    private BoardPanel boardPanel;
    private StartPanel startPanel;
    private OptionPanel optionPanel;
    private InstructionPanel instructionPanel;
    private Timer timer;

    private int lifetime;
    private int delay;
    
    private boolean paused;
    private boolean betweenWaves;
    private boolean delayEnabled;
    private boolean pauseBetweenWaves;
    private boolean showTowerCosts;
    private boolean playMusic;
    
    private boolean showingMainMenu;
    private boolean showingInstructions;
    private boolean showingOptions;
    private boolean showingGame;

    private boolean addingTower;
    private int addTowerID;
    private Tower selectedTower;

    private JPopupMenu towerPopupMenu;
    private JMenuItem upgradeTower;
    private JMenuItem sellTower;
    
    private JPopupMenu towerStatsMenu;
    private JMenuItem towerDamage;
    private JMenuItem towerRange;
    private JMenuItem towerSpeed;
    
    private Clip backgroundmusic;

    public GameMain()
    {  	
        
        //Set basic frame info
        setSize(1008, 732);
        setResizable(false);
        setTitle("Tower Defense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(this);
        addComponentListener(this);

        //Create tower popup menu
        upgradeTower = new JMenuItem("Upgrade Tower");
        upgradeTower.addActionListener(this);
        sellTower = new JMenuItem("Sell Tower");
        sellTower.addActionListener(this);
        towerPopupMenu = new JPopupMenu();
        towerPopupMenu.add(upgradeTower);
        towerPopupMenu.add(sellTower);
        
        //Create tower stats menu
        towerDamage = new JMenuItem("Damage: ");
        towerRange = new JMenuItem("Range: ");
        towerSpeed = new JMenuItem("Attack Speed: ");
        towerStatsMenu = new JPopupMenu();
        towerStatsMenu.add(towerDamage);
        towerStatsMenu.add(towerRange);
        towerStatsMenu.add(towerSpeed);

        //Create board
        board = new Board();
        boardPanel = new BoardPanel();
        startPanel = new StartPanel();
        optionPanel = new OptionPanel();
        instructionPanel = new InstructionPanel();

        board.addObserver(boardPanel);

        container = getContentPane();
        container.add(startPanel);

        boardPanel.repaint();
        startPanel.repaint();

        //Initialize other instance variables
        timer = new Timer(TIMER_SPEED, this);

        showingMainMenu = true;
        showingInstructions = false;
        showingOptions = false;
        showingGame = false;
        
        pauseBetweenWaves = true;
        showTowerCosts = true;
        playMusic = true;
        
        delayEnabled = false;
        betweenWaves = true;
        paused = false;
        lifetime = 0;
        delay = 0;
        addingTower = false;
        addTowerID = 0;
        selectedTower = null;
        
        Sound.backgroundmusic.loop();
    }

    public void resume()
    {
        setTitle("Prometheus");
        paused = false;
        boardPanel.setPaused(false);
        timer.start();
        boardPanel.repaint();
    }

    public void pause()
    {
        setTitle("Prometheus - Paused");
        paused = true;
        boardPanel.setPaused(true);
        timer.stop();
        boardPanel.repaint();
    }

    public void toggleTimer()
    {
        if(timer.isRunning())
            timer.stop();
        else
            timer.start();

        paused = timer.isRunning();
        boardPanel.setPaused(paused);
    }
    
    public void playMusic()
    {
        Sound.backgroundmusic.loop();
    }
    
    public void stopMusic()
    {
        Sound.backgroundmusic.stop();
    }

    public boolean isInBoard(MouseEvent e)
    {
        return e.getX() < board.getSize() && e.getY() < board.getSize();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Upgrade tower is clicked
        if(e.getSource() == upgradeTower && !paused)
        {
            //Upgrade the selected tower
            if (selectedTower.getRank() < 3) {
                board.upgradeTower(selectedTower);
            }

            //Update menu upgrade text
            if(selectedTower.getRank() < 3)
                upgradeTower.setText("Upgrade (" + selectedTower.getUpgradeCost() + ")");
            else
                upgradeTower.setText("Maximum Rank");

            //Update menu sell text
            sellTower.setText("Sell (" + ((selectedTower.getCost() / 25) / 2 * 25) + ")");
            
            towerDamage.setText("Damage: " + selectedTower.getDamage());
            towerRange.setText("Range: " + selectedTower.getRange());
            towerSpeed.setText("Attack Speed: " + dec.format((double) 1000.0 / (selectedTower.getAttackSpeed() * 1.0)));

            boardPanel.repaint();
        }

        //If sell is clicked, sell the selected tower and update board
        if(e.getSource() == sellTower && !paused)
        {
            board.sellTower(new Location(selectedTower.getLocation().getX(), selectedTower.getLocation().getY()));
            towerPopupMenu.setVisible(false);
            towerStatsMenu.setVisible(false);
        }

        if(e.getSource() == timer)
        {
            //Increment game lifetime
            lifetime += TIMER_SPEED;

            //Update screen if game is over
            if(board.gameOver())
            {
                boardPanel.setGameOver(true);
                timer.stop();
                boardPanel.repaint();
            }

            //Check if wave is completed
            if(board.enemiesInactive() == 0 && !board.hasActiveEnemies())
            {
                if(delayEnabled)
                {
                    if(delay < 1500)
                    {
                        boardPanel.setShowingNextWave(true);
                        delay += TIMER_SPEED;
                    }
                    else
                    {
                        delay = 0;
                        delayEnabled = false;
                        boardPanel.setShowingNextWave(false);
                        board.startNextWave();
                    }
                }
                else
                {
                    timer.stop();
                    //End game if last wave
                    if(board.getCurrentWave() == 10)
                    {
                        boardPanel.setVictory(true);
                    }
                    //Start next wave if more are left
                    else if(pauseBetweenWaves)
                    {
                        for(Tower t : board.getTowers())
                        {
                            t.setAttacking(false);
                        }
                        betweenWaves = true;
                        boardPanel.setShowNextWaveButton(true);
                    }
                    else
                    {
                        timer.start();
                        delayEnabled = true;
                        boardPanel.setShowNextWaveButton(false);
                    }
                    boardPanel.repaint();
                }
            }

            //SPAWN NEW ENEMY
            if(lifetime % SPAWN_TIME == 0 && board.enemiesInactive() != 0)
                board.spawnNextEnemy();

            //MOVE ENEMIES AND ATTACK
            if(board.hasActiveEnemies())
            {
                board.updateFrozenEnemies(TIMER_SPEED);
                board.moveEnemies();
                board.towersAttack();
            }

            boardPanel.update(board, null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(showingMainMenu)
        {
            int buttonxmin = StartPanel.BUTTON_X;
            int buttonxmax = StartPanel.BUTTON_X + StartPanel.BUTTON_WIDTH;
            int buttonysize = StartPanel.BUTTON_HEIGHT;
            
            if(e.getX() >= buttonxmin && e.getX() <= buttonxmax && e.getY() >= StartPanel.NEWGAME_Y + BAR_SIZE && e.getY() <= StartPanel.NEWGAME_Y + buttonysize + BAR_SIZE)
            {
				showingMainMenu = false;
				showingGame = true;
				container.remove(startPanel);
				container.add(boardPanel);
				container.revalidate();
				container.repaint();
						
				startPanel.stopTimer();
            	startPanel.reset();
            }
            else if(e.getX() >= buttonxmin && e.getX() <= buttonxmax && e.getY() >= StartPanel.HOWTO_Y + BAR_SIZE && e.getY() <= StartPanel.HOWTO_Y + buttonysize + BAR_SIZE)
            {
            	showingMainMenu = false;
            	showingInstructions = true;
            	container.remove(startPanel);
            	container.add(instructionPanel);
            	container.revalidate();
            	container.repaint();
            	
            	startPanel.stopTimer();
            	startPanel.reset();
            }
            
            else if(e.getX() >= buttonxmin && e.getX() <= buttonxmax && e.getY() >= StartPanel.OPTIONS_Y + BAR_SIZE && e.getY() <= StartPanel.OPTIONS_Y + buttonysize + BAR_SIZE)
            {
            	showingMainMenu = false;
            	showingOptions = true;
            	container.remove(startPanel);
            	container.add(optionPanel);
            	container.revalidate();
            	container.repaint();
            	
            	startPanel.stopTimer();
            	startPanel.reset();
            }
        }
        
        else if(showingInstructions)
        {
            int backx = InstructionPanel.BACK_X;
            int nextx = InstructionPanel.NEXT_X;
            int buttony = InstructionPanel.BUTTON_Y;
            
            int buttonWidth = InstructionPanel.BUTTON_WIDTH;
            int buttonHeight = InstructionPanel.BUTTON_HEIGHT;
            
            if (e.getX() >= backx && e.getX() <= backx + buttonWidth
                    && e.getY() >= buttony + BAR_SIZE && e.getY() <= buttony + buttonHeight + BAR_SIZE) {
                if (instructionPanel.getSlide() == 1) {
                    startPanel.reset();
                    startPanel.startTimer();

                    showingMainMenu = true;
                    showingInstructions = false;

                    container.remove(instructionPanel);
                    container.add(startPanel);
                    container.revalidate();
                    container.repaint();

                    instructionPanel.setSlide(1);
                } else {
                    instructionPanel.prevSlide();
                }
            }
            else if(e.getX() >= nextx && e.getX() <= nextx + buttonWidth && 
            		e.getY() >= buttony+ BAR_SIZE && e.getY() <= buttony + buttonHeight + BAR_SIZE)
            {
            	if(instructionPanel.getSlide() == 4) {
            		startPanel.reset();
            		startPanel.startTimer();
            		
            		showingMainMenu = true;
                    showingInstructions = false;

                    container.remove(instructionPanel);
                    container.add(startPanel);
                    container.revalidate();
                    container.repaint();
                    
                    instructionPanel.setSlide(1);
            	} else {
            		instructionPanel.nextSlide();
            	}
            }
        }
        
        else if(showingOptions)
        {
            if (e.getX() >= OptionPanel.BACKBUTTON_X && e.getX() <= OptionPanel.BACKBUTTON_X + OptionPanel.BACKBUTTON_WIDTH
                    && e.getY() >= OptionPanel.BACKBUTTON_Y + BAR_SIZE && e.getY() <= OptionPanel.BACKBUTTON_Y + OptionPanel.BACKBUTTON_HEIGHT + BAR_SIZE) {
                startPanel.reset();
                startPanel.startTimer();

                showingMainMenu = true;
                showingOptions = false;
                container.remove(optionPanel);
                container.add(startPanel);
                container.revalidate();
                container.repaint();
            }
            
            else if (e.getX() >= OptionPanel.OPTIONBUTTON_X  - 5 && e.getX() <= OptionPanel.OPTIONBUTTON_X - 5 + OptionPanel.OPTIONBUTTON_WIDTH + 10
                    && e.getY() >= OptionPanel.PAUSE_Y - 5 + BAR_SIZE && e.getY() <= OptionPanel.PAUSE_Y - 5 + OptionPanel.OPTIONBUTTON_HEIGHT + 10 + BAR_SIZE) {
                pauseBetweenWaves = !pauseBetweenWaves;
                optionPanel.setPauseBetweenWaves(pauseBetweenWaves);
                optionPanel.repaint();
            }
            
            else if (e.getX() >= OptionPanel.OPTIONBUTTON_X  - 5 && e.getX() <= OptionPanel.OPTIONBUTTON_X - 5 + OptionPanel.OPTIONBUTTON_WIDTH + 10
                    && e.getY() >= OptionPanel.COST_Y - 5 + BAR_SIZE && e.getY() <= OptionPanel.COST_Y - 5 + OptionPanel.OPTIONBUTTON_HEIGHT + 10 + BAR_SIZE) {
                showTowerCosts = !showTowerCosts;
                optionPanel.setShowTowerCosts(showTowerCosts);
                boardPanel.setShowTowerCosts(showTowerCosts);
                optionPanel.repaint();
            }
            
            else if (e.getX() >= OptionPanel.OPTIONBUTTON_X  - 5 && e.getX() <= OptionPanel.OPTIONBUTTON_X - 5 + OptionPanel.OPTIONBUTTON_WIDTH + 10
                    && e.getY() >= OptionPanel.MUSIC_Y - 5 + BAR_SIZE && e.getY() <= OptionPanel.MUSIC_Y - 5 + OptionPanel.OPTIONBUTTON_HEIGHT + 10 + BAR_SIZE) {
                if(playMusic) {
                	stopMusic();
                } else {
                	playMusic();
                }
            	playMusic = !playMusic;
                optionPanel.setPlayMusic(playMusic);
                optionPanel.repaint();
            }
        }
        
        else if(showingGame)
        {
            //See if board was clicked on
             if(isInBoard(e))
            {
                //Check if tower is being added
                if(addingTower)
                {
                    //Check if left mouse button clicked
                    if(SwingUtilities.isLeftMouseButton(e) && !paused)
                    {
                        //Add corresponding tower
                        switch(addTowerID)
                        {
                            case 1:
                                board.addTower(new Location(e.getX(), e.getY() - BAR_SIZE), new ShortTower());
                                break;
                            case 2:
                                board.addTower(new Location(e.getX(), e.getY() - BAR_SIZE), new LongTower());
                                break;
                            case 3:
                                board.addTower(new Location(e.getX(), e.getY() - BAR_SIZE), new BoostTower());
                                break;
                            case 4:
                                board.addTower(new Location(e.getX(), e.getY() - BAR_SIZE), new FreezeTower());
                                break;
                            default:
                                break;
                        }
                        //Update appropriate visual information
                        addingTower = false;
                        boardPanel.setAddTowerVis(false);
                        boardPanel.setAddingTowerIDVis(addTowerID);
                    }
                    //Check if right mouse button was clicked
                    //If so, exit tower build mode
                    else if(SwingUtilities.isRightMouseButton(e))
                    {
                        addingTower = false;
                        addTowerID = 0;
                        boardPanel.setAddTowerVis(false);
                        boardPanel.setAddingTowerIDVis(addTowerID);
                    }
                }
                //If a tower is not being built
                else
                {
                    //If left click, update currently selected tower
                    if(SwingUtilities.isLeftMouseButton(e))
                    {
                        if(selectedTower != null)
                            selectedTower.setSelected(false);

                        //Check if there is a tower at the location clicked
                        selectedTower = board.getTowerAt(new Location(e.getX(), e.getY() - BAR_SIZE));

                        //If a tower is selected, show the popup menu and update selected tower
                        if(selectedTower != null)
                        {
                            selectedTower.setSelected(true);

                            if(selectedTower.getRank() < 3)
                                upgradeTower.setText("Upgrade (" + selectedTower.getUpgradeCost() + ")");
                            else
                                upgradeTower.setText("Maximum Rank");

                            sellTower.setText("Sell (" + ((selectedTower.getCost() / 25) / 2 * 25) + ")");
                            
                            towerPopupMenu.setLocation(this.getX() + selectedTower.getLocation().getX() - 48, this.getY() + selectedTower.getLocation().getY() - 50 - BAR_SIZE);
                            towerPopupMenu.setVisible(true);
                            
                            if(selectedTower.getTowerType() <= 2) {
                                towerDamage.setText("Damage: " + selectedTower.getDamage());
                            } else if(selectedTower.getTowerType() == 4) {
                                towerDamage.setText("Freezetime: " + selectedTower.getDamage());
                            } else if(selectedTower.getTowerType() == 3) {
                                towerDamage.setText("Damage boost: " + selectedTower.getDamage());
                            }
                            
                            towerRange.setText("Range: " + selectedTower.getRange());
                            
                            if(selectedTower.getTowerType() != 3) {
                                towerSpeed.setText("Attack Speed: " + dec.format((double) 1000.0 / (selectedTower.getAttackSpeed() * 1.0)));
                            } else {
                                towerSpeed.setText("Range Boost: " + (selectedTower.getRange() / 10));
                            }
                            
                            
                            towerStatsMenu.setLocation(this.getX() + selectedTower.getLocation().getX() - 57, this.getY() + selectedTower.getLocation().getY() + 80 - BAR_SIZE);
                            towerStatsMenu.setVisible(true);
                        }
                        //If no tower is selected, hide the popup menu
                        else if (selectedTower == null)
                        {
                            towerPopupMenu.setVisible(false);
                            towerStatsMenu.setVisible(false);
                        }
                    }
                }
            }
            //If mouse is clicked not on the board
             else if(SwingUtilities.isLeftMouseButton(e))
            {
                if(selectedTower != null)
                {
                    selectedTower.setSelected(false);
                    selectedTower = null;
                }
                
                towerPopupMenu.setVisible(false);
                towerStatsMenu.setVisible(false);
                
                if(addingTower)
                {
                    addingTower = false;
                    addTowerID = 0;
                    boardPanel.setAddTowerVis(false);
                    boardPanel.setAddingTowerIDVis(0);
                }
                
                boolean inNextWaveButton = (e.getX() >= 720 && e.getX() <= 920 && e.getY() >= 200 + BAR_SIZE && e.getY() <= 250 + BAR_SIZE);
                
                //Check if short tower button was clicked
                if(e.getX() >= BoardPanel.SHORT_BUTTON_X && e.getX() <= BoardPanel.SHORT_BUTTON_X + BoardPanel.TOWER_BUTTON_SIZE &&
                        e.getY() >= BoardPanel.SHORT_BUTTON_Y + BAR_SIZE && e.getY() <= BoardPanel.SHORT_BUTTON_Y + BoardPanel.TOWER_BUTTON_SIZE + BAR_SIZE)
                {
                    addTowerID = 1;
                    addingTower = true;
                    boardPanel.setAddTowerVis(true);
                    boardPanel.setAddingTowerIDVis(addTowerID);
                }
                //Check if long tower button was clicked
                else if(e.getX() >= BoardPanel.LONG_BUTTON_X && e.getX() <= BoardPanel.LONG_BUTTON_X + BoardPanel.TOWER_BUTTON_SIZE &&
                        e.getY() >= BoardPanel.LONG_BUTTON_Y  + BAR_SIZE && e.getY() <= BoardPanel.LONG_BUTTON_Y + BoardPanel.TOWER_BUTTON_SIZE + BAR_SIZE)
                {
                    addTowerID = 2;
                    addingTower = true;
                    boardPanel.setAddTowerVis(true);
                    boardPanel.setAddingTowerIDVis(addTowerID);
                }
                //Check if boost tower button was clicked
                else if(e.getX() >= BoardPanel.BOOST_BUTTON_X && e.getX() <= BoardPanel.BOOST_BUTTON_X + BoardPanel.TOWER_BUTTON_SIZE &&
                        e.getY() >= BoardPanel.BOOST_BUTTON_Y  + BAR_SIZE && e.getY() <= BoardPanel.BOOST_BUTTON_Y + BoardPanel.TOWER_BUTTON_SIZE + BAR_SIZE)
                {
                    addTowerID = 3;
                    addingTower = true;
                    boardPanel.setAddTowerVis(true);
                    boardPanel.setAddingTowerIDVis(addTowerID);
                }
                //Check if freeze tower button was clicked
                else if(e.getX() >= BoardPanel.FREEZE_BUTTON_X && e.getX() <= BoardPanel.FREEZE_BUTTON_X + BoardPanel.TOWER_BUTTON_SIZE &&
                        e.getY() >= BoardPanel.FREEZE_BUTTON_Y  + BAR_SIZE && e.getY() <= BoardPanel.FREEZE_BUTTON_Y + BoardPanel.TOWER_BUTTON_SIZE + BAR_SIZE)
                {
                    addTowerID = 4;
                    addingTower = true;
                    boardPanel.setAddTowerVis(true);
                    boardPanel.setAddingTowerIDVis(addTowerID);
                }
                
                //Check if restart button was clicked
                else if(e.getX() >= 760 && e.getX() <= 820 && e.getY() >= 600 + BAR_SIZE && e.getY() <= 660 + BAR_SIZE)
                {
                    pause();
                	
                    addingTower = false;
                    addTowerID = 0;
                    boardPanel.setAddTowerVis(false);
                    boardPanel.setAddingTowerIDVis(addTowerID);
                	
                    int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to restart?", "Restart Game", JOptionPane.OK_CANCEL_OPTION);
                    if(result == 0)
                    {                 	
                        timer.stop();
                        lifetime = 0;
		        		board = new Board();
		        		board.addObserver(boardPanel);
		        		boardPanel.update(board, null);
		        		boardPanel.setShowNextWaveButton(true);
                        boardPanel.setGameOver(false);
                        boardPanel.setVictory(false);
		        		BasicTower.resetIDcounter();
		        		               
		        		setTitle("Prometheus");
		        		paused = false;
		        		boardPanel.setPaused(false);
                    }
                }
                
                //Check if quit button was clicked
                else if(e.getX() >= 880 && e.getX() <= 940 && e.getY() >= 600 + BAR_SIZE && e.getY() <= 660 + BAR_SIZE)
                {
                    pause();
                	
                    addingTower = false;
                    addTowerID = 0;
                    boardPanel.setAddTowerVis(false);
                    boardPanel.setAddingTowerIDVis(addTowerID);
                	
                    int result = JOptionPane.showConfirmDialog(this, "Exit to main menu?", "Main Menu", JOptionPane.OK_CANCEL_OPTION);
                    if(result == 0)
                    {
                        timer.stop();
		                lifetime = 0;
		        		board = new Board();
		        		board.addObserver(boardPanel);
		        		boardPanel.update(board, null);
		        		boardPanel.setShowNextWaveButton(true);
                        boardPanel.setGameOver(false);
                        boardPanel.setVictory(false);
		        		BasicTower.resetIDcounter();
		        		               
		        		setTitle("Prometheus");
		        		paused = false;
		        		boardPanel.setPaused(false);
		     
		        		showingMainMenu = true;
		        		showingGame = false;
		        		container.remove(boardPanel);
		        		container.add(startPanel);
		                startPanel.startTimer();
		        		container.revalidate();
		        		container.repaint();
                    }
                }

                //START NEXT WAVE BUTTON
                else if(inNextWaveButton && board.enemiesInactive() == 0 && board.enemiesLeft() == 0 && !board.gameOver() && board.getCurrentWave() != 10 && 
                        (pauseBetweenWaves || board.getCurrentWave() == 0))
                {
                    timer.start();
                    delayEnabled = true;
                    boardPanel.setShowNextWaveButton(false);
                }
                
                else if(inNextWaveButton && paused && !board.gameOver() && board.getCurrentWave() != 10)
                {
                    resume();
                }
                
                else if(inNextWaveButton && !paused && !board.gameOver() && board.getCurrentWave() != 10)
                {
                    pause();
                }
            }
        }

        boardPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}
    
    @Override
    public void componentMoved(ComponentEvent evt)
    {
        if(selectedTower != null)
        {
            selectedTower.setSelected(false);
            selectedTower = null;
        }
        towerPopupMenu.setVisible(false);
        towerStatsMenu.setVisible(false);
        
        boardPanel.repaint();  
    }
    
    @Override
    public void componentShown(ComponentEvent evt) {}

    @Override
    public void componentResized(ComponentEvent evt) {}

    @Override
    public void componentHidden(ComponentEvent evt) {}    

    public static void main(String[] args)
    {
        GameMain gamer = new GameMain();
        gamer.setVisible(true);
        gamer.playMusic();
    }
}