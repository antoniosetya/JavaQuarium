import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lib.Aquarium;

public class Main {

	private JFrame frmArkavquarium;
	//private JPanel mainPanel;
	private Aquarium overworld;

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
		overworld = new Aquarium();
		overworld.initialize();
		overworld.drawAquarium(frmArkavquarium);
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
		/*mainPanel = new JPanel();
		mainPanel.setBounds(0, -5, 640, 480);
		frmArkavquarium.getContentPane().add(mainPanel);*/
	}

}
