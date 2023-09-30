import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * This class contains the necessary methods to construct and draw 
 * an enemy and check its speed and color values as well as abstract methods 
 * that are used by its child enemy classes.
 * 
 * @author Adam Barner
 */
public abstract class Enemy extends JComponent {

    /**
     * The speed of the enemy, updated and displayed with the set and get
     * enemySpeed() methods.
     */
    private double enemySpeed;
    
    /**
     * The color of the enemy, updated and displayed with the set and get
     * enemyColor() methods.
     */
    private Color enemyColor;
    
    /**
     * A constructor that makes an Enemy object on given coordinates of given 
     * height and updates enemySpeed to be the passed in enemySpeed.
     * 
     * @param x The X-coordinate
     * @param y The y-coordinate
     * @param height The height of the enemy
     * @param width The width of the enemy
     * @param enemySpeed the speed of the enemy as a double
     */
    public Enemy(int x, int y, int height, int width, double enemySpeed) {
        setBounds(x, y, width, height);
        this.enemySpeed = enemySpeed;
    }
    
    /**
     * An abstract method that is used by both child classes, called when
     * a missile collides with an enemy.
     * 
     * @param list An ArrayList of Enemies
     * @param enemy The index of the Enemy
     * @see BigEnemy and SmallEnemy.processCollision
     */
    public abstract void processCollision(ArrayList<Enemy> list, int enemy);
    
    /**
     * An abstract method that is used by both child classes to set color.
     * 
     * @see BigEnemy and SmallEnemy.setColor
     */
    public abstract void setColor();
    
    /**
     * An abstract method that is used by both child classes to move.
     * 
     * @param frameWidth The width of the frame bounds
     * @param frameHeight The height of the frame bounds
     * @see BigEnemy and SmallEnemy.move
     */
    public abstract void move(int frameWidth, int frameHeight);
    
    /**
     * A method overriding the Container method that makes the display 
     * of the enemy on the gui.
     * 
     * @param g The graphic we're changing to look like our enemy.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(enemyColor);
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    /**
     * A method that returns the enemy speed.
     * 
     * @return double The enemy speed
     */
    public double getEnemySpeed() {
        return enemySpeed;
    }
    
    /**
     * A method that sets the enemy speed to the passed in value.
     * 
     * @param u The enemy speed to set to as a double.
     */
    public void setEnemySpeed(double u) {
        enemySpeed = u;
    }
    
    /**
     * A method that returns the enemy color.
     * 
     * @return Color The enemy color
     */
    public Color getEnemyColor() {
        return enemyColor;
    }
    
    /**
     * A method that sets the enemy color to the passed in value.
     * 
     * @param c The color enemyColor will change to
     */
    public void setEnemyColor(Color c) {
        enemyColor = c;
    }
}
