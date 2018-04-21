import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4648172894076113183L;
	
	public Main() {
		add(new MainBoard());
		setResizable(false);
		pack();
		setTitle("ArkavQuarium");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Main frame = new Main();
				frame.setVisible(true);
			}
		});
	}
}
