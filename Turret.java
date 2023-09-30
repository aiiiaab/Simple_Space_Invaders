import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 * This class contains the necessary methods to construct and draw a turret.
 * 
 * @author Adam Barner
 */
public class Turret extends JComponent {

	/**
     * The base of the turret.
     */
    private Rectangle base = new Rectangle();
    
    /**
     * The gun/barrel of the turret.
     */
    private Rectangle turret = new Rectangle();
    
    /**
     * The color of the turret.
     */
    private Color turretColor;
    
    /**
     * A constructor that makes a pink turret object at the bottom middle of 
     * the screen. Sets the bounds of the base and puts the barrel on top of it.
     * 
     */
    public Turret() {
        base.setBounds(325, 405, 30, 20);
        turret.setBounds(334, 390, 12, 15);
        turretColor = Color.pink;
    }
    
    /**
     * A method overriding the Container method that makes the display 
     * of the turret on the gui.
     * 
     * @param g The graphic we're changing to look like our enemy.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(turretColor);
        g.fillRect((int)base.getX(), (int)base.getY(), 
                  (int)base.getWidth(), (int)base.getHeight());
        g.fillRect((int)turret.getX(), (int)turret.getY(), 
                (int)turret.getWidth(), (int)turret.getHeight());
    }
}
