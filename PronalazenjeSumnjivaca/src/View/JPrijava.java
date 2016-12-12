package View;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.ViewDelegate;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class JPrijava extends JAbstractPanel {
	ViewDelegate delegate;
	private JTextField username;
	private JPasswordField password;
	private JButton btnLogIn;

	public JPrijava(ViewDelegate del) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };

		setLayout(gridBagLayout);

		username = new JTextField();
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 0);
		gbc_username.fill = GridBagConstraints.CENTER;
		gbc_username.gridx = 0;
		gbc_username.gridy = 0;
		add(username, gbc_username);
		username.setColumns(10);

		password = new JPasswordField();
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = new Insets(0, 0, 5, 0);
		gbc_password.fill = GridBagConstraints.CENTER;
		gbc_password.gridx = 0;
		gbc_password.gridy = 1;
		add(password, gbc_password);
		password.setColumns(10);

		btnLogIn = new JButton("Prijava");
		GridBagConstraints gbc_btnPrijava = new GridBagConstraints();
		gbc_btnPrijava.gridx = 0;
		gbc_btnPrijava.gridy = 2;
		add(btnLogIn, gbc_btnPrijava);
		delegate = del;

		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						delegate.prijava(username.getText(), password.getText());
					}
				});
				t.start();

			}
		});

	}

}
