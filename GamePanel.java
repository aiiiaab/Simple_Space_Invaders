import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class contains objects such as the enemies, turret, and missile. 
 * 
 * @author Adam Barner
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    
    /**
    * Determines which enemy to generate next, big or small enemy. 
    * Set to true by default to make a big enemy on the first call of addEnemy()
    * 
    */
    private boolean isNextEnemyBig = true;

    /**
     * A turret object to use throughout the game. 
     */
    public Turret turret;
    
    /**
     * The list of enemies in the game. Enemies are added in the addEnemy
     * method and removed in the detectCollison method.
     */
    public ArrayList<Enemy> enemyList = new ArrayList<>();
    
    /**
     * The list of missiles in the game. Missiles are added in the addMissile
     * method and removed in the detectCollison method.
     */
    private ArrayList<Missile> missileList = new ArrayList<>();
    
    /**
     * A constructor that makes the panel the game's graphics appear in. It
     * spawns the first two enemies and the turret to start the game.
     * 
     */
    public GamePanel() {
        isNextEnemyBig = true;
        turret = new Turret();
        SmallEnemy small = new SmallEnemy(this.getWidth(), this.getHeight());
        BigEnemy big = new BigEnemy(this.getWidth(), this.getHeight());
        enemyList.add(small);
        enemyList.add(big);
    }
    
    /**
     * A method overriding the Container method that first wipes the screen
     * white, then redraws each of the components after they've been moved.
     * 
     * @param g The graphic to repaint with the new positions.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (Enemy small: enemyList) {
            small.paintComponent(g);
        }
        for (Missile miss: missileList) {
            miss.paintComponent(g);
        }
        turret.paintComponent(g);
    }
    
    /**
     * Goes through the list of enemies and missiles, and calls 
     * each of their move() methods within the bounds of the frame.
     * 
     */
    public void move() {
        for (Enemy small: enemyList) {
            small.move(700, 500);;
        }
        for (int i = 0; i < missileList.size(); i++) {
            missileList.get(i).move(700, 500, missileList, i);
        }
    }
    
    /**
     * An overrode method from the Enemy class that moves the enemy
     * along the X-axis according to its speed, first checks if 
     * moving the enemy will cause it to escape the bounds of the
     * frame and if it does it sets the enemySpeed to negative so
     * the enemy can move backwards. Then increases the speed in 
     * both directions by 0.05.
     * 
     * @param frameWidth The width of the frame bounds
     * @param frameHeight The height of the frame bounds
     */
    public void moveTurret(int frameWidth, int frameHeight) {
        Rectangle a = turret.getBounds();
        int x = (int)a.getX();
        do {
            a.setLocation(frameWidth, (int)a.getY());
        } while (x > frameWidth);
        do {
            a.setLocation(0, (int)a.getY());
        } while (x < 0);
        a.setLocation((int)(a.getX() + 50), (int)a.getY());
        turret.setBounds(a);
    }
    
    /**
     * Creates a missile at the coordinates of the barrel of the barrel.
     * 
     */
    public void addMissile() {
        Missile m = new Missile(334, 390);
        missileList.add(m);
    }
    
    /**
     * Creates a new enemy, alternating size each time it's called
     * by updating isNextEnemyBig.
     * 
     */
    public void addEnemy() {
        if (isNextEnemyBig) {
            BigEnemy a = new BigEnemy(700, 500);
            enemyList.add(a);
            isNextEnemyBig = false;
        } else {
            SmallEnemy a = new SmallEnemy(700, 500);
            enemyList.add(a);
            isNextEnemyBig = true;
        }
    }
    
    /**
     * Detects the collision of the missile and all the enemies.
     * Done by drawing invisible rectangles around the enemies and missiles, if
     * they intersect, then they collide.
     */
    public void detectCollision() {
        // Uses bounds for enemies and missiles to detect intersection.
        for (int i = 0; i < enemyList.size(); i++) {
            Rectangle enemyRec = enemyList.get(i).getBounds();
            for (int j = 0; j < missileList.size(); j++) {
                Rectangle missileRec = missileList.get(j).getBounds();
                if (missileRec.intersects(enemyRec)) {
                    // Missile has hit an enemy
                    enemyList.get(i).processCollision(enemyList, i);
                    missileList.remove(j);
                   }
            }
        }
    }
}

