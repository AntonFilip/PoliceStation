package Controller;

import javax.swing.SwingUtilities;

import Model.*;
import View.*;

public class Controller implements ViewDelegate {

	MainWindow mW;
	Pozornik policajac;

	Slucaj slucaj;
	Dokaz dokaz;
	Osumnjiceni osumjiceni;

	public static void main(String[] args) {
		Controller c = new Controller();
		SwingUtilities.invokeLater(() -> {
			c.mW = new MainWindow();
			c.mW.add(new JPrijava(c));
		});

	}

	@Override
	public void logIn(String username, String password) {
		System.out.println(username + " " + password);
		policajac = PristupBaziPodataka.logIn(username, password);

		if (policajac == null) {
			return;
		} else {
			SwingUtilities.invokeLater(() -> {
				mW.getContentPane().removeAll();
				System.out.println("Ime: " + policajac.getIme());
				System.out.println("Razina pristupa: " + policajac.getAccess());
				// System.out.println("tu sam");
				mW.getContentPane().add(new JGlavniIzbornik(policajac));
				mW.getContentPane().validate();
				mW.getContentPane().repaint();
			});

		}
	}// Otprilike

	@Override
	// TODO popunit sve varijable
	public void posaljiUpitOsumnjiceni(String ime, String prezime) {
		policajac.posaljiUpitZaOsumnjicenog(ime);
	}

}
