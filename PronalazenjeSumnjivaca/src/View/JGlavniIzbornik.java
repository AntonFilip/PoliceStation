package View;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import Controller.ViewDelegate;
import Model.Pozornik;

public class JGlavniIzbornik extends JAbstractPanel {

	public JGlavniIzbornik(Pozornik policajac) {
		String ispis = "Dobrodosli gosp. " + policajac.getPrezime() + " sa razinom pristupa " + policajac.getAccess();
		
		JLabel poruka = new JLabel(ispis);
		
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		System.out.println("i tuu");
		
		add(poruka, BorderLayout.CENTER);
		
	}

}
