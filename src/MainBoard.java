import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import lib.Aquarium;

public class MainBoard extends JPanel implements ActionListener {

	private final int width = 640;
	private final int height = 480;
	private final double interval = 0.033;
	private Aquarium overworld = new Aquarium(width,height);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6546742554971391289L;
	private Image background;
	
	public MainBoard() {
		setPreferredSize(new Dimension(width,height));
		setDoubleBuffered(true);
		setLayout(null);
		
		loadBackground();
		
		overworld.initialize();
		
		Timer timer = new Timer((int)(interval * 1000),this);
		timer.start();
	}
	
	private void loadBackground() {
		try {
			background = ImageIO.read(new File("./bin/assets/background.jpg"));
		}
		catch(IOException ioex) {
			ioex.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,0,0,this);
		overworld.drawAquarium(g,this);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		overworld.timeHasPassed(interval);
		repaint();
	}
}
