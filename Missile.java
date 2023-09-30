import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * This class contains the necessary methods to construct and draw a missile, 
 * set its color, move it, and get the speed of the missile.
 * 
 * @author Adam Barner
 */
public class Missile extends JComponent {
    
    /**
     * The speed of the missile, the speed is set to 5 in the constructor
     * and is displayed with the getMissileSpeed() method.
     */
    private int missileSpeed;
    
    /**
     * The color of the missile, updated by the setColor() method.
     */
    private Color missileColor;

    /**
     * A constructor that makes a Missile object of size 15x15 and speed 5 
     * with passed-in coordinates. 
     * 
     * @param x The x-coordinate 
     * @param y The y-coordinate 
     */
    public Missile(int x, int y) {
        setBounds(x, y, 12, 15);
        missileSpeed = 5;
    }
    
    /**
     * A method that sets the missile color to a random color.
     * 
     */
    public void setColor() {
        Color c = new Color((int)Math.random() * 0x1000000);
        missileColor = c;
    }
    
    /**
     * A method overriding the Container method that makes the display 
     * of the missile on the gui.
     * 
     * @param g The graphic we're changing to look like our enemy.
     */
    public void paintComponent(Graphics g) {
        g.setColor(missileColor);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    /**
     * A method that first checks if a missile is within the bound of the frame:
     * and if it isn't then it removes the missile from the list of missiles,
     * and if it is then moves the missile along the Y-axis according to speed.
     * 
     * @param panelWidth The width of the frame bounds
     * @param panelHeight The height of the frame bounds
     * @param list The ArrayList of missiles
     * @param missile The index of the current missile 
     */
    public void move(int panelWidth, int panelHeight, ArrayList<Missile> list, 
        int  missile) {
        if (this.getY() > panelHeight || this.getX() > panelWidth
            || this.getY() <= 0 || this.getX() <= 0) {
            list.remove(this);
        } else { 
            this.setLocation(this.getX(), this.getY() - missileSpeed);
        }
    }
    
    /**
     * A method that returns the missile speed.
     * 
     * @return int The enemy speed
     */
    public int getMissileSpeed() {
        return missileSpeed;
    }
}
