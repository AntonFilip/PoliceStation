package Controller;

import Model.*;
import View.*;

public class Controller implements ViewDelegate {
	
	MainWindow mW = new MainWindow();
	Policajac policajac;
	
	Slucaj slucaj;
	Dokaz dokaz;
	Osumnjiceni osumjiceni;
	
	public static void main(String[] args) {
		//Postaviti mW (velicina prozora, close operations, itd.)
	}

	@Override
	public void logIn(String username, String password) {
		policajac=PristupBaziPodataka.logIn(username, password);
		if(policajac==null){
			return;
		}
		else{
			mW.removeAll();
			mW.add(new JGlavniIzbornik());
		}
	}//Otprilike

	@Override
	//TODO popunit sve varijable
	public void posaljiUpitOsumnjiceni(String ime, String prezime) {
		policajac.posaljiUpitZaOsumnjicenog(ime);
	}

}
