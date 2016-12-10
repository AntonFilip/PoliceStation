package View;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.ViewDelegate;

public class JPrijava extends JAbstractPanel {
	ViewDelegate delegate;
	JTextField username = new JTextField();
	JPasswordField password = new JPasswordField();
	JButton prijava = new JButton();

	public JPrijava(ViewDelegate del) {
		delegate = del;
		setLayout(new BorderLayout());
		this.add(username, BorderLayout.NORTH);
		this.add(password, BorderLayout.CENTER);
		this.add(prijava, BorderLayout.SOUTH);

		prijava.setText("Prijava");

		prijava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						delegate.logIn(username.getText(), password.getText());
					}
				});
				t.start();

			}
		});

	}

}
