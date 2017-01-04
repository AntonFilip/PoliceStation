package Model;

import java.sql.SQLException;

import Controller.RazinaPristupa;

public class Narednik extends Pozornik {
	public Narednik() {
		razinaPristupa = RazinaPristupa.SREDNJA;
	}

	public Narednik(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
		razinaPristupa = RazinaPristupa.SREDNJA;

	}
	
	public boolean dodajNoviDokaz(Dokaz dokaz) {
		PristupBaziPodataka.dodajNoviDokaz(dokaz);
		return true;	
	}
	
	public boolean izmjeniSlucaj(Slucaj slucaj){
		try {
			PristupBaziPodataka.izmjenaSlucaja(slucaj);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}
