import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;
import lib.Aquarium;

public class Main {

	private JFrame frmArkavquarium;
	private Aquarium Overworld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmArkavquarium.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArkavquarium = new JFrame();
		frmArkavquarium.setResizable(false);
		frmArkavquarium.setTitle("ArkavQuarium");
		frmArkavquarium.setBounds(100, 100, 640, 480);
		frmArkavquarium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArkavquarium.getContentPane().setLayout(null);
		
		try {
			BufferedImage back_image = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/assets/background.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(back_image));
			JPanel panel = new JPanel();
			panel.setBounds(0, -5, 640, 480);
			panel.add(picLabel);
			frmArkavquarium.getContentPane().add(panel);
		}
		catch (IOException ex) {
			System.out.println("Cannot open background image!");
		}
		
	}

}
