package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StartPanel extends JPanel implements ActionListener
{
    public final static int BUTTON_X = 310;
    public final static int BUTTON_WIDTH = 380;
    public final static int BUTTON_HEIGHT = 100;
    public final static int NEWGAME_Y = 200;
    public final static int HOWTO_Y = 320;
    public final static int OPTIONS_Y = 440;
    
    private Timer timer;
    private int[] xpos;
    
    private final static Color BACKGROUND_COLOR = new Color(217, 217, 217);
    private final static Color CYAN = new Color(27, 226, 217);
    private final static Color DEEPSKYBLUE = new Color(0,191,255);
    private final static Color DODGERBLUE = new Color(28,134,238);
    
    private static Font TITLE_FONT;
    private static Font BUTTON_FONT;
    private static Font BUTTON_FONT_2;
    
    private Image img1, img2, img3, img4, img5, img6, img7, pathImage;
    
    private ImageIcon background;
    
    public StartPanel()
    {
        timer = new Timer(25, this);
        xpos = new int[7];
        
        xpos[0] = -100;
        xpos[1] = -350;
        xpos[2] = -600;
        xpos[3] = -850;
        xpos[4] = -1100;
        xpos[5] = -1350;
        xpos[6] = -1600;
        
        try
        {
            TITLE_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(100f);
            BUTTON_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(54f);
            BUTTON_FONT_2 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(42f);
            
            img1 = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/jetpack_right.png"));
            img2 = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/ancient_right.png"));
            img3 = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/osprey_right.png"));
            img4 = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/runner_right.png"));
            img5 = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/kela_right.png"));
            img6 = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/sargas_right.png"));
            img7 = ImageIO.read(getClass().getResourceAsStream("res/images/enemies/vor_right.png"));
            pathImage = ImageIO.read(getClass().getResourceAsStream("res/images/tiles/path/path_white_1.png"));
            
            background = new ImageIcon(getClass().getResource("res/images/scoreboard/background.gif"));
        }
        catch(IOException  | FontFormatException ex)
        {
            System.out.println(ex);
        }
        
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        
        timer.start();
    }
    
    public void stopTimer()
    {
    	timer.stop();
    }
    
    public void startTimer()
    {
        timer.start();
    }
    
    public void reset()
    {
    	xpos[0] = -100;
        xpos[1] = -350;
        xpos[2] = -600;
        xpos[3] = -850;
        xpos[4] = -1100;
        xpos[5] = -1350;
        xpos[6] = -1600;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        background.paintIcon(this, g, 0, 0);
        
        g2d.setFont(TITLE_FONT);
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("PROMETHEUS", 135, 140);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(BUTTON_X, NEWGAME_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        g.setColor(CYAN);
        g.fillRect(BUTTON_X + 10, NEWGAME_Y + 10, BUTTON_WIDTH - 20, BUTTON_HEIGHT - 20);

        g2d.setFont(BUTTON_FONT_2);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("NEW GAME", BUTTON_X + 58, NEWGAME_Y + 66);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(BUTTON_X, HOWTO_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        g.setColor(DEEPSKYBLUE);
        g.fillRect(BUTTON_X + 10, HOWTO_Y + 10, BUTTON_WIDTH - 20, BUTTON_HEIGHT - 20);

        g2d.setFont(BUTTON_FONT_2);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("HOW TO PLAY", BUTTON_X + 23, HOWTO_Y + 66);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(BUTTON_X, OPTIONS_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        g.setColor(DODGERBLUE);
        g.fillRect(BUTTON_X + 10, OPTIONS_Y + 10, BUTTON_WIDTH - 20, BUTTON_HEIGHT - 20);

        g2d.setFont(BUTTON_FONT_2);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("OPTIONS", BUTTON_X + 83, OPTIONS_Y + 66);

        for(int i = 0; i < 15; i++)
        {
            g.drawImage(pathImage, i * 70 - 23, 600, null);
        }

        g.drawImage(img1, xpos[0], 590, null);
        g.drawImage(img2, xpos[1], 580, null);
        g.drawImage(img3, xpos[2], 615, null);
        g.drawImage(img4, xpos[3], 587, null);
        g.drawImage(img5, xpos[4], 570, null);
        g.drawImage(img6, xpos[5], 570, null);
        g.drawImage(img7, xpos[6], 560, null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == timer)
        {            
            for(int i = 0; i < xpos.length; i++)
            {
                if(xpos[i] > 1050)
                    xpos[i] = -700;
                else
                    xpos[i] += 2;
            }
            repaint();
        }
    }
}
