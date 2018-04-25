package arkavquarium;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * The type Main.
 */
public class Main extends JFrame {

    /**
     * Constant for serial version uid.
     */
    private static final long SERIAL_VERSION_UID = 4648172894076113183L;

    /**
     * Instantiates a new Main.
     */
    public Main() {
        MainBoard mainBoard = new MainBoard();
        getContentPane().add(mainBoard);
        setResizable(false);
        pack();
        setTitle("ArkavQuarium");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Launch the application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
