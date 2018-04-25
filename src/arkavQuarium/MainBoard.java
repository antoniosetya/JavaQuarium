package arkavquarium;

import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import arkavquarium.lib.Aquarium;

/**
 * The type Main board.
 */
public class MainBoard extends JPanel implements ActionListener {
    /**
     * Variable for fish food price.
     */
    private final int fishFoodPrice = 5;
    /**
     * Variable for guppy price.
     */
    private final int guppyPrice = 100;
    /**
     * Variable for piranha price.
     */
    private final int piranhaPrice = 120;
    /**
     * Variable for menu bar offset.
     */
    private final int menubarOffset = 50;
    /**
     * Variable for width.
     */
    private final int width = 640;
    /**
     * Variable for height.
     */
    private final int height = 480;
    /**
     * Variable for interval.
     */
    private final double interval = 0.033;
    /**
     * Constant for one hundred.
     */
    private static final int ONE_HUNDRED = 100;
    /**
     * Constant for 1000.
     */
    private static final int ONE_THOUSAND = 1000;
    /**
     * Constant for 3.
     */
    private static final int THREE = 3;
    /**
     * Constant for 575.
     */
    private static final int FIVE_SEVEN_FIVE = 575;
    /**
     * Constant for 5.
     */
    private static final int FIVE = 5;
    /**
     * Constant for 65.
     */
    private static final int SIXTY_FIVE = 65;
    /**
     * Constant for 50.
     */
    private static final int FIFTY = 50;
    /**
     * Constant for 40.
     */
    private static final int FORTY = 40;
    /**
     * Constant for 75.
     */
    private static final int SEVENTY_FIVE = 75;
    /**
     * Constant for 85.
     */
    private static final int EIGHTY_FIVE = 85;
    /**
     * Constant for 165.
     */
    private static final int ONE_SIX_FIVE = 165;
    /**
     * Constant for 465.
     */
    private static final int FOUR_SIX_FIVE = 465;
    /**
     * Constant for 235.
     */
    private static final int TWO_THREE_FIVE = 235;
    /**
     * Constant for 180.
     */
    private static final int ONE_EIGHT_ZERO = 180;
    /**
     * Constant for 260.
     */
    private static final int TWO_SIX_ZERO = 260;
    /**
     * Constant for 215.
     */
    private static final int TWO_ONE_FIVE = 215;
    /**
     * Constant for 270.
     */
    private static final int TWO_SEVEN_ZERO = 270;
    /**
     * Constant for 10.
     */
    private static final int TEN = 10;
    /**
     * Constant for 184.
     */
    private static final int ONE_EIGHT_FOUR = 184;
    /**
     * Constant for 190.
     */
    private static final int ONE_NINE_ZERO = 190;
    /**
     * Constant for 272.
     */
    private static final int TWO_SEVEN_TWO = 272;
    /**
     * Constant for 243.
     */
    private static final int TWO_FOUR_THREE = 243;
    /**
     * Constant for 146.
     */
    private static final int ONE_FOUR_SIX = 146;
    /**
     * Constant for 245.
     */
    private static final int TWO_FOUR_FIVE = 245;
    /**
     * Constant for 15.
     */
    private static final int FIFTEEN = 15;
    /**
     * Constant for 520.
     */
    private static final int FIVE_TWO_ZERO = 520;
    /**
     * Constant for 419.
     */
    private static final int FOUR_ONE_ZERO = 410;
    /**
     * Variable main board.
     */
    private MainBoard thisMainBoard = this;
    /**
     * Variable alert time out.
     */
    private double alertTimeout = 0;
    /**
     * Variable alert text.
     */
    private String alertText;
    /**
     * Variable egg's price.
     */
    private int eggPrice = ONE_HUNDRED;
    /**
     * Variable num of egg.
     */
    private int numEgg = 0;
    /**
     * Variable coin text.
     */
    private JTextArea coinText;
    /**
     * Variable egg text.
     */
    private JTextArea eggText;
    /**
     * Variable multipurpose.
     */
    private JLabel multipurpose;
    /**
     * Variable linkedhashmap.
     */
    private LinkedHashMap<String, JButton> menuButtons
            = new LinkedHashMap<String, JButton>();
    /**
     * Variable overworld.
     */
    private Aquarium overworld = new Aquarium(width, height);
    /**
     * Variable serial version uid.
     */
    private static final long SERIAL_VERSION_UID = 6546742554971391289L;
    /**
     * Variable background.
     */
    private Image background;
    /**
     * Variable winning.
     */
    private boolean winning;
    /**
     * Variable loading.
     */
    private boolean losing;
    /**
     * Variable gamerunning.
     */
    private boolean gameRunning;
    /**
     * Variable timer.
     */
    private Timer timer;

    /**
     * Instantiates a new Main board.
     */
    public MainBoard() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height + ONE_HUNDRED));
        setDoubleBuffered(true);
        setLayout(null);

        loadBackground();
        winning = false;
        gameRunning = false;

        drawMainMenu();
    }

    /**
     * Gets num egg.
     *
     * @return the num egg
     */
    public int getNumEgg() {
        return numEgg;
    }

    /**
     * Gets overworld.
     *
     * @return the overworld
     */
    public Aquarium getOverworld() {
        return overworld;
    }

    /**
     * Sets num egg.
     *
     * @param num the num egg
     */
    public void setNumEgg(final int num) {
        this.numEgg = num;
    }

    /**
     * Sets overworld.
     *
     * @param ow the overworld
     */
    public void setOverworld(final Aquarium ow) {
        this.overworld = ow;
    }

    /**
     * Run timer.
     */
    public void runTimer() {
        timer = new Timer((int) (interval * ONE_THOUSAND), this);
        timer.start();
    }

    /**
     * Pause timer.
     */
    public void pauseTimer() {
        timer.stop();
    }

    /**
     * Restart timer.
     */
    public void restartTimer() {
        timer.restart();
    }

    /**
     * Initialize game.
     *
     * @param newGame refers to new game
     */
    private void initGame(final boolean newGame) {
        initMenubar();

        if (newGame) {
            overworld.initialize();
        }

        gameRunning = true;

        // Create mouse event listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)
                        && isMouseInPlayArea(me)) {
                    boolean success = overworld.collectCoin(me.getX(),
                            me.getY() - menubarOffset);
                    if (!success) {
                        if (overworld.getNumOfCoins() >= fishFoodPrice) {
                            overworld.createNewObject('F', me.getX(),
                                    me.getY() - menubarOffset);
                            overworld.setNumOfCoins(overworld.getNumOfCoins()
                                    - fishFoodPrice);
                        } else {
                            showAlert("Not enough coins!");
                        }
                    }
                }
            }
        });
        runTimer();
    }

    /**
     * Load background.
     */
    private void loadBackground() {
        background = (new ImageIcon("./assets/background.jpg")).getImage();
    }

    /**
     * Check Winning.
     *
     * @return boolean check winning
     */
    private boolean checkWinning() {
        return (numEgg >= THREE);
    }

    /**
     * Check losing.
     *
     * @return boolean check losing
     */
    private boolean checkLosing() {
        return (overworld.getGuppies().isEmpty()
                && overworld.getPiranhas().isEmpty()
                && (overworld.getNumOfCoins() < guppyPrice));
    }

    /**
     * Initialize label.
     */
    private void initLabels() {
        coinText = new JTextArea();
        coinText.setEditable(false);
        coinText.setText("Coins : \n" + overworld.getNumOfCoins());
        coinText.setBounds(FIVE_SEVEN_FIVE, FIVE,
                SIXTY_FIVE, FORTY);
        add(coinText);

        eggText = new JTextArea();
        eggText.setEditable(false);
        eggText.setText("Eggs : \n" + numEgg);
        eggText.setBounds(FIVE_TWO_ZERO, FIVE,
                FIFTY, FORTY);
        add(eggText);
    }

    /**
     * Update coin label.
     */
    private void updateCoinLabel() {
        coinText.setText("Coins : \n" + overworld.getNumOfCoins());
    }

    /**
     * Update egg label.
     */
    private void updateEggLabel() {
        eggText.setText("Eggs : \n" + numEgg);
    }

    /**
     * Initialize menu bar.
     */
    private void initMenubar() {
        JButton temp = new JButton();
        temp.setEnabled(true);
        temp.setText("<html>Buy Guppy<br />100 Coins</html>");
        temp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0, true));
        temp.setBounds(FIVE, FIVE, SEVENTY_FIVE, FORTY);
        menuButtons.put("GupButton", temp);
        add(menuButtons.get("GupButton"));
        menuButtons.get("GupButton").addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (overworld.getNumOfCoins() >= guppyPrice) {
                        overworld.createNewObject('G');
                        overworld.setNumOfCoins(
                                overworld.getNumOfCoins() - guppyPrice);
                    } else {
                        showAlert("Not enough coins!");
                    }
                }
            }
        });

        temp = new JButton();
        temp.setEnabled(true);
        temp.setText("<html>Buy Piranha<br />120 Coins</html>");
        temp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0, true));
        temp.setBounds(EIGHTY_FIVE, FIVE, SEVENTY_FIVE, FORTY);
        menuButtons.put("PirButton", temp);
        add(menuButtons.get("PirButton"));
        menuButtons.get("PirButton").addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (overworld.getNumOfCoins() >= piranhaPrice) {
                        overworld.createNewObject('P');
                        overworld.setNumOfCoins(
                                overworld.getNumOfCoins() - piranhaPrice);
                    } else {
                        showAlert("Not enough coins!");
                    }
                }
            }
        });

        temp = new JButton();
        temp.setEnabled(true);
        temp.setText("<html>Buy Egg<br />" + eggPrice + " Coins</html>");
        temp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0, true));
        temp.setBounds(ONE_SIX_FIVE, FIVE, SEVENTY_FIVE, FORTY);
        menuButtons.put("EggButton", temp);
        add(menuButtons.get("EggButton"));
        menuButtons.get("EggButton").addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (overworld.getNumOfCoins() >= eggPrice) {
                        numEgg++;
                        overworld.setNumOfCoins(
                                overworld.getNumOfCoins() - eggPrice);
                        eggPrice += ONE_HUNDRED;
                        menuButtons.get("EggButton").setText(
                                "<html>Buy Egg<br />" + eggPrice
                                        + " Coins</html>");
                    } else {
                        showAlert("Not enough coins!");
                    }
                }
            }
        });

        temp = new JButton();
        temp.setEnabled(true);
        temp.setText("<html>Save<br />Game</html>");
        temp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0, true));
        temp.setBounds(FOUR_SIX_FIVE, FIVE, FIFTY, FORTY);
        menuButtons.put("SaveButton", temp);
        add(menuButtons.get("SaveButton"));
        menuButtons.get("SaveButton").addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    pauseTimer();
                    JFileChooser jfc = new JFileChooser();
                    if (jfc.showSaveDialog(thisMainBoard)
                            == JFileChooser.APPROVE_OPTION) {
                        FileHandler fh = new FileHandler(
                                jfc.getSelectedFile().getAbsolutePath(),
                                thisMainBoard, 'w');
                        if (fh.process()) {
                            showAlert("Saved to file "
                                    + jfc.getSelectedFile().getName() + "!");
                        } else {
                            showAlert("Failed to save file!");
                        }
                    }
                    restartTimer();
                }
            }
        });

        temp = new JButton();
        temp.setEnabled(true);
        temp.setText("Pause");
        temp.setBorder(BorderFactory.createLineBorder(
                Color.BLUE, 0, true));
        temp.setBounds(FOUR_ONE_ZERO, FIVE,
                FIFTY, FORTY);
        menuButtons.put("PauseButton", temp);
        add(menuButtons.get("PauseButton"));
        menuButtons.get("PauseButton").addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (menuButtons.get("PauseButton").
                            getText().equals("Resume")) {
                        restartTimer();
                        menuButtons.get("PauseButton").setText("Pause");
                    } else {
                        pauseTimer();
                        menuButtons.get("PauseButton").setText("Resume");
                    }
                }
            }
        });
        initLabels();
    }

    /**
     * Show alert.
     *
     * @param alert refer to alert message
     */
    private void showAlert(final String alert) {
        alertTimeout = 2;
        alertText = alert;
    }

    /**
     * Hide menu bar.
     */
    private void hideMenubar() {
        for (Map.Entry<String, JButton> entry : menuButtons.entrySet()) {
            entry.getValue().setVisible(false);
        }
        coinText.setVisible(false);
        eggText.setVisible(false);
    }

    /**
     * Draw main menu.
     */
    private void drawMainMenu() {
        multipurpose = new JLabel();
        multipurpose.setText("<html><h1 align=\"center\" style=\"color"
                + " : blue;\">ArkavQuarium</h1></html>");
        multipurpose.setBounds(TWO_THREE_FIVE, 0, ONE_EIGHT_ZERO, FIFTY);
        multipurpose.setVisible(true);
        add(multipurpose);

        JButton temp = new JButton("<html><h2 align=\"center\" "
                + "style=\"color : white; "
                + "font-family : 'Univers';\">Start Game!</h2></html>");
        temp.setEnabled(true);
        temp.setBackground(Color.BLUE);
        temp.setBorder(BorderFactory.createLineBorder(
                Color.BLUE, 0, true));
        temp.setBounds(TWO_SIX_ZERO, TWO_ONE_FIVE
                + menubarOffset, ONE_HUNDRED, FIFTY);
        menuButtons.put("StartButton", temp);
        add(menuButtons.get("StartButton"));
        menuButtons.get("StartButton").addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    hideMainMenu();
                    initGame(true);
                }
            }
        });

        temp = new JButton("<html><h2 align=\"center\" style=\""
                + "color : white; font-family : 'Univers';\">"
                + "Load<br/>Game</h2></html>");
        temp.setEnabled(true);
        temp.setBackground(Color.BLUE);
        temp.setBorder(BorderFactory.createLineBorder(
                Color.BLUE, 0, true));
        temp.setBounds(TWO_SIX_ZERO, TWO_SEVEN_ZERO + menubarOffset,
                ONE_HUNDRED, FIFTY);
        menuButtons.put("LoadButton", temp);
        add(menuButtons.get("LoadButton"));
        menuButtons.get("LoadButton").addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    JFileChooser jfc = new JFileChooser();
                    if (jfc.showOpenDialog(thisMainBoard)
                            == JFileChooser.APPROVE_OPTION) {
                        FileHandler fh = new FileHandler(jfc.getSelectedFile().
                                getAbsolutePath(), thisMainBoard, 'r');
                        if (fh.process()) {
                            hideMainMenu();
                            initGame(false);
                        } else {
                            JOptionPane.showMessageDialog(thisMainBoard,
                                    fh.getStatDetail(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    /**
     * Hide main menu.
     */
    private void hideMainMenu() {
        menuButtons.get("StartButton").setEnabled(false);
        menuButtons.get("StartButton").setVisible(false);
        menuButtons.get("LoadButton").setEnabled(false);
        menuButtons.get("LoadButton").setVisible(false);
        multipurpose.setVisible(false);
    }

    /**
     * Draw winning condition.
     */
    private void drawWin() {
        hideMenubar();
        multipurpose.setBackground(Color.ORANGE);
        multipurpose.setOpaque(true);
        multipurpose.setBorder(BorderFactory.createLineBorder(
                Color.ORANGE, TEN, true));
        multipurpose.setText("<html><h1 align=\"center\" style=\""
                + "color : green;\">CONGRATULATIONS!<br/>YOU WON!</h1></html>");
        multipurpose.setBounds(ONE_EIGHT_FOUR, ONE_NINE_ZERO
                + menubarOffset, TWO_SEVEN_TWO, ONE_HUNDRED);
        multipurpose.setVisible(true);
    }

    /**
     * Draw losing condition.
     */
    private void drawLose() {
        hideMenubar();
        multipurpose.setBackground(Color.ORANGE);
        multipurpose.setOpaque(true);
        multipurpose.setBorder(BorderFactory.createLineBorder(
                Color.ORANGE, TEN, true));
        multipurpose.setText("<html><h1 align=\"center\" style=\"color"
                + " : red;\">Uh oh...<br/>You lost...!</h1></html>");
        multipurpose.setBounds(TWO_FOUR_THREE, ONE_NINE_ZERO
                + menubarOffset, ONE_FOUR_SIX, ONE_HUNDRED);
        multipurpose.setVisible(true);
    }

    /**
     * Paint component.
     *
     * @param g refer to graphic to be paint
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, menubarOffset, this);
        if (gameRunning) {
            overworld.drawAquarium(g, menubarOffset, this);
            updateCoinLabel();
            updateEggLabel();
            if (alertTimeout > 0) {
                g.drawString(alertText, TWO_FOUR_FIVE, FIFTEEN);
                alertTimeout -= interval;
            }
        }
    }

    /**
     * Perform action.
     *
     * @param ae refer action performed
     */
    @Override
    public void actionPerformed(final ActionEvent ae) {
        if (gameRunning) {
            overworld.timeHasPassed(interval);
        }
        repaint();
        winning = checkWinning();
        losing = checkLosing();
        if (winning || losing) {
            gameRunning = false;
            if (winning) {
                drawWin();
            } else {
                drawLose();
            }
        }
    }

    /**
     * Paint component.
     *
     * @param me refer to mouse event
     * @return boolean is mouse in play area
     */
    private boolean isMouseInPlayArea(final MouseEvent me) {
        if ((me.getY() > menubarOffset)
                && (me.getY() <= height + menubarOffset)) {
            return true;
        }
        return false;
    }
}
