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
import lib.Aquarium;

public class MainBoard extends JPanel implements ActionListener {

	private final int FishFoodPrice = 5;
	private final int GuppyPrice = 100;
	private final int PiranhaPrice = 120;
	private final int MenubarOffset = 50;
	private final int width = 640;
	private final int height = 480;
	private final double interval = 0.033;
	
	private MainBoard thisMainBoard = this;
	private double alertTimeout = 0;
	private String alertText;
	private int egg_price = 100;
	private int num_egg = 0;
	private JTextArea coinText;
	private JTextArea eggText;
	private JLabel multipurpose;
	private LinkedHashMap<String,JButton> menuButtons = new LinkedHashMap<String,JButton>();
	private Aquarium overworld = new Aquarium(width,height);
	private static final long serialVersionUID = 6546742554971391289L;
	private Image background;
	private boolean winning;
	private boolean losing;
	private boolean gameRunning;
	private Timer timer;
	
	public MainBoard() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(width,height + 100));
		setDoubleBuffered(true);
		setLayout(null);

		loadBackground();
		winning = false;
		gameRunning = false;
		
		drawMainMenu();
	}
	
	public int getNumEgg() {
		return num_egg;
	}

	public Aquarium getOverworld() {
		return overworld;
	}

	public void setNumEgg(int num_egg) {
		this.num_egg = num_egg;
	}

	public void setOverworld(Aquarium overworld) {
		this.overworld = overworld;
	}
	
	public void runTimer() {
		timer = new Timer((int)(interval * 1000),this);
		timer.start();
	}
	
	public void pauseTimer() {
		timer.stop();
	}
	
	public void restartTimer() {
		timer.restart();
	}

	private void initGame(boolean newGame) {
		initMenubar();
		
		if (newGame) overworld.initialize();
		
		gameRunning = true;
		
		
		// Create mouse event listener
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me) && isMouseInPlayArea(me)) {
					// Determines if click is above a coin or not.
					boolean success = overworld.collectCoin(me.getX(), me.getY() - MenubarOffset);
					if (!success) {
						// Click is not above a coin -> drop FishFood if possible
						if (overworld.getNumOfCoins() >= FishFoodPrice) {
							overworld.createNewObject('F',me.getX(),me.getY() - MenubarOffset);
							overworld.setNumOfCoins(overworld.getNumOfCoins() - FishFoodPrice);
						}
						else {
							showAlert("Not enough coins!");
						}
					}
				}
			}
		});
		runTimer();
	}
	
	private void loadBackground() {
		background = (new ImageIcon("./assets/background.jpg")).getImage();
	}
	
	private boolean checkWinning() {
		return (num_egg >= 3);
	}
	
	private boolean checkLosing() {
		return (overworld.getGuppies().isEmpty() && 
				overworld.getPiranhas().isEmpty() && 
				(overworld.getNumOfCoins() < GuppyPrice)); 
	}
	
	private void initLabels() {
		coinText = new JTextArea();
		coinText.setEditable(false);
		coinText.setText("Coins : \n" + overworld.getNumOfCoins());
		coinText.setBounds(555, 5, 80, 40);
		add(coinText);
		
		eggText = new JTextArea();
		eggText.setEditable(false);
		eggText.setText("Eggs : \n" + num_egg);
		eggText.setBounds(500, 5, 50, 40);
		add(eggText);
	}
	
	private void updateCoinLabel() {
		coinText.setText("Coins : \n" + overworld.getNumOfCoins());
	}
	
	private void updateEggLabel() {
		eggText.setText("Eggs : \n" + num_egg);
	}
	
	private void initMenubar() {
		JButton temp = new JButton();
		temp.setEnabled(true);
		temp.setText("<html>Buy Guppy<br />100 Coins</html>");
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE,0,true));
		temp.setBounds(5, 5, 75, 40);
		menuButtons.put("GupButton", temp);
		add(menuButtons.get("GupButton"));
		menuButtons.get("GupButton").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					if (overworld.getNumOfCoins() >= GuppyPrice) {
						overworld.createNewObject('G');
						overworld.setNumOfCoins(overworld.getNumOfCoins() - GuppyPrice);
					}
					else {
						showAlert("Not enough coins!");
					}
				}
			}
		});
		
		temp = new JButton();
		temp.setEnabled(true);
		temp.setText("<html>Buy Piranha<br />120 Coins</html>");
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE,0,true));
		temp.setBounds(85, 5, 75, 40);
		menuButtons.put("PirButton", temp);
		add(menuButtons.get("PirButton"));
		menuButtons.get("PirButton").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					if (overworld.getNumOfCoins() >= PiranhaPrice) {
						overworld.createNewObject('P');
						overworld.setNumOfCoins(overworld.getNumOfCoins() - PiranhaPrice);
					}
					else {
						showAlert("Not enough coins!");
					}
				}
			}
		});
		
		temp = new JButton();
		temp.setEnabled(true);
		temp.setText("<html>Buy Egg<br />" + egg_price + " Coins</html>");
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE,0,true));
		temp.setBounds(165, 5, 75, 40);
		menuButtons.put("EggButton", temp);
		add(menuButtons.get("EggButton"));
		menuButtons.get("EggButton").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					if (overworld.getNumOfCoins() >= egg_price) {
						num_egg++;
						overworld.setNumOfCoins(overworld.getNumOfCoins() - egg_price);
						egg_price += 100;
						menuButtons.get("EggButton").setText("<html>Buy Egg<br />" + egg_price + " Coins</html>");
					}
					else {
						showAlert("Not enough coins!");
					}
				}
			}
		});
		
		temp = new JButton();
		temp.setEnabled(true);
		temp.setText("<html>Save<br />Game</html>");
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE,0,true));
		temp.setBounds(420, 5, 75, 40);
		menuButtons.put("SaveButton",temp);
		add(menuButtons.get("SaveButton"));
		menuButtons.get("SaveButton").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					pauseTimer();
					JFileChooser jfc = new JFileChooser();
					if (jfc.showSaveDialog(thisMainBoard) == JFileChooser.APPROVE_OPTION) {
						FileHandler fh = new FileHandler(jfc.getSelectedFile().getAbsolutePath(),thisMainBoard,'w');
						if (fh.process()) {
							showAlert("Saved to file " + jfc.getSelectedFile().getName() + "!");
						}
						else {
							showAlert("Failed to save file!");
						}
					}
					restartTimer();
				}
			}
		});
		initLabels();
	}
	
	private void showAlert(String alert) {
		alertTimeout = 2;
		alertText = alert;
	}
	
	private void hideMenubar() {
		for (Map.Entry<String, JButton> entry : menuButtons.entrySet()) {
			entry.getValue().setVisible(false);
		}
		coinText.setVisible(false);
		eggText.setVisible(false);
	}
	
	private void drawMainMenu() {
		multipurpose = new JLabel();
		multipurpose.setText("<html><h1 align=\"center\" style=\"color : blue;\">ArkavQuarium</h1></html>");
		multipurpose.setBounds(235,0,180,50);
		multipurpose.setVisible(true);
		add(multipurpose);
		
		JButton temp = new JButton("<html><h2 align=\"center\" style=\"color : white; font-family : 'Univers';\">Start Game!</h2></html>");
		temp.setEnabled(true);
		temp.setBackground(Color.BLUE);
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE,0,true));
		temp.setBounds(260,215 + MenubarOffset,100,50);
		menuButtons.put("StartButton", temp);
		add(menuButtons.get("StartButton"));
		menuButtons.get("StartButton").addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					hideMainMenu();
					initGame(true);
				}
			}
		});
		
		temp = new JButton("<html><h2 align=\"center\" style=\"color : white; font-family : 'Univers';\">Load<br/>Game</h2></html>");
		temp.setEnabled(true);
		temp.setBackground(Color.BLUE);
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE,0,true));
		temp.setBounds(260,270 + MenubarOffset,100,50);
		menuButtons.put("LoadButton", temp);
		add(menuButtons.get("LoadButton"));
		menuButtons.get("LoadButton").addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					JFileChooser jfc = new JFileChooser();
					if (jfc.showOpenDialog(thisMainBoard) == JFileChooser.APPROVE_OPTION) {
						FileHandler fh = new FileHandler(jfc.getSelectedFile().getAbsolutePath(),thisMainBoard,'r');
						if (fh.process()) {
							hideMainMenu();
							initGame(false);
						}
						else {
							JOptionPane.showMessageDialog(thisMainBoard, 
									fh.getStatDetail(), 
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	
	private void hideMainMenu() {
		menuButtons.get("StartButton").setEnabled(false);
		menuButtons.get("StartButton").setVisible(false);
		menuButtons.get("LoadButton").setEnabled(false);
		menuButtons.get("LoadButton").setVisible(false);
		multipurpose.setVisible(false);
	}
	
	private void drawWin() {
		hideMenubar();
		multipurpose.setBackground(Color.ORANGE);
		multipurpose.setOpaque(true);
		multipurpose.setBorder(BorderFactory.createLineBorder(Color.ORANGE,10,true));
		multipurpose.setText("<html><h1 align=\"center\" style=\"color : green;\">CONGRATULATIONS!<br/>YOU WON!</h1></html>");
		multipurpose.setBounds(184,190 + MenubarOffset,272,100);
		multipurpose.setVisible(true);
	}
	
	private void drawLose() {
		hideMenubar();
		multipurpose.setBackground(Color.ORANGE);
		multipurpose.setOpaque(true);
		multipurpose.setBorder(BorderFactory.createLineBorder(Color.ORANGE,10,true));
		multipurpose.setText("<html><h1 align=\"center\" style=\"color : red;\">Uh oh...<br/>You lost...!</h1></html>");
		multipurpose.setBounds(243,190 + MenubarOffset,146,100);
		multipurpose.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,0,MenubarOffset,this);
		if (gameRunning) {
			overworld.drawAquarium(g,MenubarOffset,this);
			updateCoinLabel();
			updateEggLabel();
			if (alertTimeout > 0) {
				g.drawString(alertText,245,15);
				alertTimeout -= interval;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
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
			}
			else {
				drawLose();
			}
		}
	}
	
	private boolean isMouseInPlayArea(MouseEvent me) {
		if ((me.getY() > MenubarOffset) && (me.getY() <= height + MenubarOffset)) {
			return true;
		}
		else {
			return false;
		}
	}
}
