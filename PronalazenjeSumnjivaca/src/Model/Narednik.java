package Model;

import java.sql.SQLException;

import Controller.RazinaPristupa;

public class Narednik extends Pozornik {
	public Narednik() {
		razinaPristupa = RazinaPristupa.SREDNJA;
	}

	public Narednik(String ime, String prezime,Integer jedinstveniBrojPolicajca) {
		this.ime = ime;
		this.prezime = prezime;
		super.setJedinstveniBroj(jedinstveniBrojPolicajca);
		razinaPristupa = RazinaPristupa.SREDNJA;

	}
	
	public static boolean dodajNoviDokaz(Dokaz dokaz) {
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
