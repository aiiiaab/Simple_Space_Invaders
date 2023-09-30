import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class contains the necessary methods to construct a big enemy, set its
 * color, move it, and to process collision when a missile intersects it.
 * 
 * @author Adam Barner
 */
public class SmallEnemy extends Enemy {

    /**
     * A constructor that makes a randomly colored SmallEnemy object
     * of size 30x30 and speed 6 with random coordinates 
     * within the bounds of panelWidth/Height.
     * 
     * @param panelWidth The x-coordinate bounds of the panel
     * @param panelHeight The y-coordinate bounds of the panel
     */
    public SmallEnemy(int panelWidth, int panelHeight) {
        super((int)Math.floor(Math.random() * (panelWidth - 100)), 
              (int)Math.floor(Math.random() * 360), 30, 30, 6);
        setColor();
    }

    /**
     * An overrode method from the Enemy class that checks the size of the
     * enemy to determine if it's still alive, and shrinks it by 10
     * if it is. This is called when a missile collides with an enemy.
     * 
     * @param list An ArrayList of Enemies
     * @param enemy The index of the Enemy
     */
    @Override
    public void processCollision(ArrayList<Enemy> list, int enemy) {
        if (this.getWidth() - 10 <= 0 || this.getHeight() - 10 <= 0) {
            list.remove(enemy);
        }
        this.setSize(this.getWidth() - 10, this.getHeight() - 10);
    }
    
    /**
     * A method that sets the enemy color to a random color.
     * 
     */
    @Override
    public void setColor() {
        Color c = new Color((int)Math.floor(Math.random() * 0x1000000));
        setEnemyColor(c);
    }

    /**
     * An overrode method from the Enemy class that moves the enemy
     * along the X-axis according to its speed, it first checks if 
     * moving the enemy will cause it to escape the bounds of the
     * frame and if it does it sets the enemySpeed to negative so
     * the enemy can move backwards. It then increases the speed in 
     * both directions by 0.05.
     * 
     * @param frameWidth The width of the frame bounds
     * @param frameHeight The height of the frame bounds
     */
    @Override
    public void move(int frameWidth, int frameHeight) {
        Rectangle a = this.getBounds();
        int x = (int)a.getX();
        if (x + getEnemySpeed() > frameWidth 
            || x + getEnemySpeed() < 0) {
            setEnemySpeed(-1 * (getEnemySpeed()));
        }
        a.setLocation((int)(a.getX() + this.getEnemySpeed()), (int)a.getY());
        this.setBounds(a);
        if (getEnemySpeed() < 0) {
            setEnemySpeed(getEnemySpeed() - 0.05);
        } else {
            setEnemySpeed(getEnemySpeed() + 0.05);
        }
    }

}
