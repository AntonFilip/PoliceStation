package Model;

import Controller.RazinaPristupa;

public class Pozornik extends Osoba {
	
	RazinaPristupa access;
	String username;
	String password;
	
	public Pozornik() {
		access = RazinaPristupa.LOW;
	}
	
	public Pozornik(String ime, String prezime) {
		
		this.ime = ime;
		this.prezime = prezime;
		access = RazinaPristupa.LOW;
		
	}
	
	public RazinaPristupa getAccess(){
		return this.access;
	}
	
	static public Pozornik logIn(String username, String password){
		return PristupBaziPodataka.logIn(username, password);
	}
	
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
