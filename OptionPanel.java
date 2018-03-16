package game;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class OptionPanel extends JPanel
{
    public final static int OPTIONWINDOW_WIDTH = 400;
    public final static int OPTIONWINDOW_HEIGHT = 500;

    public final static int OPTIONBUTTON_WIDTH = 110;
    public final static int OPTIONBUTTON_HEIGHT = 40;
    public final static int BACKBUTTON_WIDTH = 150;
    public final static int BACKBUTTON_HEIGHT = 50;

    public final static int OPTIONBUTTON_X = 449;
    public final static int BACKBUTTON_X = 429;
    public final static int BACKBUTTON_Y = 570;

    public final static int PAUSE_Y = 223;
    public final static int COST_Y = 343;
    public final static int MUSIC_Y = 463;
	
    private final static Color CYAN = new Color(27, 226, 217);
    private final static Color DODGERBLUE = new Color(28,134,238);
	
    private Font FONT_72;
    private Font FONT_36;
    private Font FONT_24;
    private Font FONT_16;
    
    private boolean pauseBetweenWaves;
    private boolean showTowerCosts;
    private boolean playMusic;
    
    private ImageIcon background;

    public OptionPanel()
    {	
    	pauseBetweenWaves = true;
    	showTowerCosts = true;
    	playMusic = true;
    	
    	try
        {
            FONT_72 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(72f);
            FONT_36 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(36f);
            FONT_24 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(24f);
            FONT_16 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(19f);
            
            background = new ImageIcon(getClass().getResource("res/images/scoreboard/background.gif"));
        }
        catch(IOException  | FontFormatException ex)
        {
            System.out.println(ex);
        }
    	
    	setBackground(Color.LIGHT_GRAY);
    	setVisible(true);
    }
    
    public void setPauseBetweenWaves(boolean b)
    {
        pauseBetweenWaves = b;
    }
    
    public void setShowTowerCosts(boolean b)
    {
        showTowerCosts = b;
    }
    
    public void setPlayMusic(boolean b)
    {
        playMusic = b;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        background.paintIcon(this, g, 0, 0);
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(FONT_72);
        g2d.drawString("OPTIONS", 325, 100);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(304, 150, OPTIONWINDOW_WIDTH, OPTIONWINDOW_HEIGHT);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(314, 160, OPTIONWINDOW_WIDTH - 20, OPTIONWINDOW_HEIGHT - 20);
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(FONT_24);
        g2d.drawString("Pause between waves", 365, 200);
        g2d.drawString("Show tower buy costs", 365, 320);
        g2d.drawString("Play music", 440, 440);
        
        g.fillRect(OPTIONBUTTON_X - 5, PAUSE_Y - 5, OPTIONBUTTON_WIDTH + 10, OPTIONBUTTON_HEIGHT + 10);
        g.fillRect(OPTIONBUTTON_X - 5, COST_Y - 5, OPTIONBUTTON_WIDTH + 10, OPTIONBUTTON_HEIGHT + 10);
        g.fillRect(OPTIONBUTTON_X - 5, MUSIC_Y - 5, OPTIONBUTTON_WIDTH + 10, OPTIONBUTTON_HEIGHT + 10);
        
        if (pauseBetweenWaves) {
            g.setColor(CYAN);
            g.fillRect(OPTIONBUTTON_X, PAUSE_Y, OPTIONBUTTON_WIDTH, OPTIONBUTTON_HEIGHT);
            g2d.setColor(Color.DARK_GRAY);
            g2d.setFont(FONT_16);
            g2d.drawString("ENABLED", OPTIONBUTTON_X + 7, PAUSE_Y + 27);
        } else {
            g.setColor(DODGERBLUE);
            g.fillRect(OPTIONBUTTON_X, PAUSE_Y, OPTIONBUTTON_WIDTH, OPTIONBUTTON_HEIGHT);
            g2d.setColor(Color.DARK_GRAY);
            g2d.setFont(FONT_16);
            g2d.drawString("DISABLED", OPTIONBUTTON_X + 3, PAUSE_Y + 27);
        }

        if (showTowerCosts) {
            g.setColor(CYAN);
            g.fillRect(OPTIONBUTTON_X, COST_Y, OPTIONBUTTON_WIDTH, OPTIONBUTTON_HEIGHT);
            g2d.setColor(Color.DARK_GRAY);
            g2d.setFont(FONT_16);
            g2d.drawString("ENABLED", OPTIONBUTTON_X + 7, COST_Y + 27);
        } else {
            g.setColor(DODGERBLUE);
            g.fillRect(OPTIONBUTTON_X, COST_Y, OPTIONBUTTON_WIDTH, OPTIONBUTTON_HEIGHT);
            g2d.setColor(Color.DARK_GRAY);
            g2d.setFont(FONT_16);
            g2d.drawString("DISABLED", OPTIONBUTTON_X + 3, COST_Y + 27);
        }

        if (playMusic) {
            g.setColor(CYAN);
            g.fillRect(OPTIONBUTTON_X, MUSIC_Y, OPTIONBUTTON_WIDTH, OPTIONBUTTON_HEIGHT);
            g2d.setColor(Color.DARK_GRAY);
            g2d.setFont(FONT_16);
            g2d.drawString("ENABLED", OPTIONBUTTON_X + 7, MUSIC_Y + 27);
        } else {
            g.setColor(DODGERBLUE);
            g.fillRect(OPTIONBUTTON_X, MUSIC_Y, OPTIONBUTTON_WIDTH, OPTIONBUTTON_HEIGHT);
            g2d.setColor(Color.DARK_GRAY);
            g2d.setFont(FONT_16);
            g2d.drawString("DISABLED", OPTIONBUTTON_X + 3, MUSIC_Y + 27);
        }
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(BACKBUTTON_X, BACKBUTTON_Y, BACKBUTTON_WIDTH, BACKBUTTON_HEIGHT);
        g.setColor(CYAN);
        g.fillRect(BACKBUTTON_X + 5, BACKBUTTON_Y + 5, BACKBUTTON_WIDTH - 10, BACKBUTTON_HEIGHT - 10);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(FONT_36);
        g2d.drawString("BACK", BACKBUTTON_X + 19, BACKBUTTON_Y + 40);
    }
}
