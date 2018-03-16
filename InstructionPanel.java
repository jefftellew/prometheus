package game;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class InstructionPanel extends JPanel
{
    public static final int BACK_X = 50;
    public static final int NEXT_X = 800;
    public static final int BUTTON_Y = 620;

    public final static int BUTTON_WIDTH = 150;
    public final static int BUTTON_HEIGHT = 50;

    public final static int INSTRUCTION_IMAGE_X = 104;
    public final static int INSTRUCTION_IMAGE_Y = 20;

    private final static Color CYAN = new Color(27, 226, 217);

    private Font FONT_72;
    private Font FONT_36;

    private int slide;

    private ImageIcon background;

    private Image slide1;
    private Image slide2;
    private Image slide3;
    private Image slide4;

    public InstructionPanel() {
        slide = 1;

        try {
            FONT_72 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(72f);
            FONT_36 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("res/fonts/calm.ttf")).deriveFont(36f);

            background = new ImageIcon(getClass().getResource("res/images/scoreboard/background.gif"));

            slide1 = ImageIO.read(getClass().getResourceAsStream("res/images/instructions/slide1.png"));
            slide2 = ImageIO.read(getClass().getResourceAsStream("res/images/instructions/slide2.png"));
            slide3 = ImageIO.read(getClass().getResourceAsStream("res/images/instructions/slide3.png"));
            slide4 = ImageIO.read(getClass().getResourceAsStream("res/images/instructions/slide4.png"));
        } catch (IOException | FontFormatException ex) {
            System.out.println(ex);
        }

        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }
    
    public int getSlide()
    {
    	return slide;
    }
    
    public void setSlide(int s)
    {
    	slide = s;
    }
    
    public void nextSlide()
    {
    	slide++;
    }
    
    public void prevSlide()
    {
    	slide--;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        background.paintIcon(this, g, 0, 0);
        
        g2d.setFont(FONT_72);
        g2d.setColor(Color.DARK_GRAY);
        //g2d.drawString("HOW TO PLAY", 200, 100);
        
        g2d.setColor(Color.WHITE);
        switch(slide) {
        	case 1:
        		g2d.drawImage(slide1, INSTRUCTION_IMAGE_X, INSTRUCTION_IMAGE_Y, null);
 
        		break;
        	case 2:
        		g2d.drawImage(slide2, INSTRUCTION_IMAGE_X, INSTRUCTION_IMAGE_Y, null);
        		break;
        	case 3:
        		g2d.drawImage(slide3, INSTRUCTION_IMAGE_X, INSTRUCTION_IMAGE_Y, null);
        		break;
        	case 4:
        		g2d.drawImage(slide4, INSTRUCTION_IMAGE_X, INSTRUCTION_IMAGE_Y, null);
        		break;
        	default:
        		break;
        }
        
        //DRAW NEXT BUTTON
        g.setColor(Color.DARK_GRAY);
        g.fillRect(NEXT_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        g.setColor(CYAN);
        g.fillRect(NEXT_X + 5, BUTTON_Y + 5, BUTTON_WIDTH - 10, BUTTON_HEIGHT - 10);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(FONT_36);
        if(slide < 4) {
        	g2d.drawString("NEXT", NEXT_X + 23, BUTTON_Y + 40);
        } else {
        	g2d.drawString("DONE", NEXT_X + 15, BUTTON_Y + 40);
        }
        
        //DRAW BACK BUTTON
        g.setColor(Color.DARK_GRAY);
        g.fillRect(BACK_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        g.setColor(CYAN);
        g.fillRect(BACK_X + 5, BUTTON_Y + 5, BUTTON_WIDTH - 10, BUTTON_HEIGHT - 10);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(FONT_36);
        if(slide > 1) {
        	g2d.drawString("BACK", BACK_X + 20, BUTTON_Y + 40);
        } else {
        	g2d.drawString("DONE", BACK_X + 15, BUTTON_Y + 40);
        }
    }
}
