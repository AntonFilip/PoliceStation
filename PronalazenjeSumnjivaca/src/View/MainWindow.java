package View;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	public MainWindow() {
		initGUI();
		setBounds(20, 20, 500, 500);
		setVisible(true);
		setTitle("Program");
	}

	private void initGUI() {
		JPanel contentPane = (JPanel) getContentPane();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane.setLayout(new BorderLayout(5, 5));
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

	}

}
