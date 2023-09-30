import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * The top-level Frame class class for the Game.
 * Run this to run the game.
 * 
 * @author Adam Barner
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {
    /** Fixed width. */
    private static final int WINDOW_WIDTH = 700;
    /** Fixed height. */
    private static final int WINDOW_HEIGHT = 500;

    /**
     * A label added to the top of the game panel to display time remaining.
     * 
     */
    private JLabel timeLabel = new JLabel();

    /**
     * The remaining number of calls to the gameStep method before a new enemy
     * is added to the game.
     */
    private int enemyGenerationCounter;
    
    /**
     * The time the player has to beat the game.
     */
    private int time = 30;

    /**
     * A button that allows the user to fire a missile,
     * space bar can also be used.
     */
    private JButton fireButton = new JButton("Fire!");
    

    /**
     * The game panel that encapsulates all the enemies, missiles, and
     * the turret.
     */
    private GamePanel panel = new GamePanel();

    /**
     * Default constructor to control the game.
     */
    public GameFrame() {
        // Setup the initial JFrame elements
        setTitle("Space Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        // Setup rest of the GUI
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)); 
        add(timeLabel, BorderLayout.NORTH);
        add(fireButton, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        listeners();
        
    }
    
    /**
     * Action listeners to react to game actions like pressing
     * the fire button.
     */
    @SuppressWarnings("static-access")
	public void listeners() {
        fireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.addMissile();
                panel.moveTurret(700, 500);
                panel.move();
            }
        });
        // Action call when space is pressed instead of fire button
        Action action = new AbstractAction("doSomething") {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.addMissile();
                panel.moveTurret(700, 500);
                panel.move();
            }
        };
        /** 
         * Allows for space bar to be used to fire instead of pressing
         * on-screen button.
         */
        action.putValue(Action.ACCELERATOR_KEY, 
            KeyStroke.getKeyStroke("space"));
        JButton toolBarButton = new JButton(action);
        toolBarButton.getActionMap().put("myAction", action);
        toolBarButton.getInputMap(panel.WHEN_IN_FOCUSED_WINDOW).put(
                (KeyStroke) action.getValue(
                    Action.ACCELERATOR_KEY), "myAction");
    }

    /**
     * This method is called to start operations of the game. It
     * creates a simple timer that calls the gameStep method
     * every 30 milliseconds.
     */
    public void start() {
        // Center the frame on the screen and show it.
        centerFrame(this);
        setVisible(true);
        // Create a gameStep timer to step through the game.
        Timer gameStep = new Timer(30, e -> gameStep());
        Timer gameClock = new Timer(1000, e -> time--);
        gameClock.start();
        gameStep.start();
    }

    /**
     * Method to perform a step in the animation of the game. This method
     * performs the following:
     * 
     * 1. Performs operations associated with detecting collisions between
     *    enemies and missiles by calling GamePanel.detectCollision().
     * 2. Updates score in the display.
     * 3. Moves all the enemies and missiles and repaints the panel to update
     *    the display.
     * 
     */
    private void gameStep() {
        panel.detectCollision();
        timeLabel.setText(Integer.toString(time));
        panel.move();
        panel.repaint();
        if (time == 0 || panel.enemyList.size() == 1) {
            checkScore();
        }
        while (enemyGenerationCounter < 20) {
            panel.addEnemy();
            enemyGenerationCounter++;
        }
    }
    
    /**
     * Method that is called by gameStep() and checks if there are any
     * enemies remaining on screen, displays win or lose message accordingly.
     * 
     * @see gameStep
     * 
     */
    private void checkScore() {
        if (panel.enemyList.size() == 1) {
            JOptionPane.showMessageDialog(null, "You Win!",
                    "Game Finished Message",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "You Lose!",
                    "Game Finished Message",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Method centers the frame in the middle of the screen. This is really
     * just a nicety and does not meaningfully affect the operation of the game.
     * 
     * @param frame to center with respect to the users screen dimensions.
     */
    private void centerFrame(JFrame frame) { 
        int width = frame.getWidth();
        int height = frame.getHeight();
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        // Figure out where the frame needs to be positions to center it.
        int xposition = center.x - width / 2;
        int yposition = center.y - height / 2;
        // Center the frame.
        frame.setBounds(xposition, yposition, width, height);
        frame.validate();
    }

    /**
     * The main method to execute the game.
     * 
     * @param args Command-line arguments if any. Unused in this program.
     */
    public static void main(String[] args) {
        GameFrame main = new GameFrame();
        main.start();
    }
}
