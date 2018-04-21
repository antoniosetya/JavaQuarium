import java.util.LinkedHashMap;
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
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import lib.Aquarium;

public class MainBoard extends JPanel implements ActionListener {

	private final int FishFoodPrice = 5;
	private final int MenubarOffset = 50;
	private final int width = 640;
	private final int height = 480;
	private final double interval = 0.033;
	private long numOfCoins = 150;
	private int egg_price = 100;
	private int num_egg = 0;
	private JTextArea coinText;
	private JTextArea eggText;
	private LinkedHashMap<String,JButton> menuButtons = new LinkedHashMap<String,JButton>();
	private Aquarium overworld = new Aquarium(width,height);
	
	private static final long serialVersionUID = 6546742554971391289L;
	private Image background;
	
	public MainBoard() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(width,height + 100));
		setDoubleBuffered(true);
		setLayout(null);
		
		loadBackground();
		initMenubar();
		
		overworld.initialize();
		
		// Create mouse event listener
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (isMouseInPlayArea(me)) {
					if (numOfCoins >= FishFoodPrice) {
						overworld.createNewObject('F',me.getX(),me.getY() - MenubarOffset);
						numOfCoins -= FishFoodPrice;
						updateCoinLabel();
					}
				}
			}
		});
				
		Timer timer = new Timer((int)(interval * 1000),this);
		timer.start();
	}
	
	private void loadBackground() {
		background = (new ImageIcon("./bin/assets/background.jpg")).getImage();
	}
	
	private void initLabels() {
		coinText = new JTextArea();
		coinText.setEditable(false);
		coinText.setText("Coins : \n" + numOfCoins);
		coinText.setBounds(555, 5, 80, 40);
		add(coinText);
		
		eggText = new JTextArea();
		eggText.setEditable(false);
		eggText.setText("Eggs : \n" + num_egg);
		eggText.setBounds(500, 5, 50, 40);
		add(eggText);
	}
	
	private void updateCoinLabel() {
		coinText.setText("Coins : \n" + numOfCoins);
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
		temp.setPreferredSize(temp.getPreferredSize());
		menuButtons.put("GupButton", temp);
		add(menuButtons.get("GupButton"));
		
		temp = new JButton();
		temp.setEnabled(true);
		temp.setText("<html>Buy Piranha<br />120 Coins</html>");
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE,0,true));
		temp.setBounds(85, 5, 75, 40);
		temp.setPreferredSize(temp.getPreferredSize());
		menuButtons.put("PirButton", temp);
		add(menuButtons.get("PirButton"));
		
		temp = new JButton();
		temp.setEnabled(true);
		temp.setText("<html>Buy Egg<br />" + egg_price + " Coins</html>");
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE,0,true));
		temp.setBounds(165, 5, 75, 40);
		temp.setPreferredSize(temp.getPreferredSize());
		menuButtons.put("EggButton", temp);
		add(menuButtons.get("EggButton"));
		initLabels();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,0,MenubarOffset,this);
		overworld.drawAquarium(g,MenubarOffset,this);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		overworld.timeHasPassed(interval);
		repaint();
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
