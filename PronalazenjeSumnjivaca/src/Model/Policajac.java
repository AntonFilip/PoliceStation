package Model;

import Controller.RazinaPristupa;

public class Policajac extends Osoba {
	
	RazinaPristupa access;
	String username;
	String password;
	
	public Osumnjiceni posaljiUpitZaOsumnjicenog(String ime){
		PristupBaziPodataka.posaljiUpit(ime);
		return new Osumnjiceni();
	}
	
	public Slucaj posaljiUpitZaSlucaj(String ime){
		PristupBaziPodataka.posaljiUpit(ime);
		return new Slucaj();
	}
	
	public Dokaz posaljiUpitZaDokaz(String ime){
		PristupBaziPodataka.posaljiUpit(ime);
		return new Dokaz();
	}
	
	public int izmjeni(String ime){
		if (!access.equals(RazinaPristupa.HIGH)){
			return -1;
		}
		return PristupBaziPodataka.izmjena(ime);
	}

}
