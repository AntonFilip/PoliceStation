package Model;

import Controller.RazinaPristupa;

public class Pozornik extends Osoba {

	RazinaPristupa razinaPristupa;
	String username;
	String password;

	public Pozornik() {
		razinaPristupa = RazinaPristupa.NISKA;
	}

	public Pozornik(String ime, String prezime) {

		this.ime = ime;
		this.prezime = prezime;
		razinaPristupa = RazinaPristupa.NISKA;

	}

	public RazinaPristupa getAccess() {
		return this.razinaPristupa;
	}

	static public Pozornik logIn(String username, String password) {
		return PristupBaziPodataka.logIn(username, password);
	}

	public Osumnjiceni posaljiUpitZaOsumnjicenog(String ime) {
		PristupBaziPodataka.posaljiUpit(ime);
		return new Osumnjiceni();
	}

	public Slucaj posaljiUpitZaSlucaj(String ime) {
		PristupBaziPodataka.posaljiUpit(ime);
		return new Slucaj();
	}

	public Dokaz posaljiUpitZaDokaz(String ime) {
		PristupBaziPodataka.posaljiUpit(ime);
		return new Dokaz();
	}

	public int izmjeni(String ime) {
		if (!razinaPristupa.equals(RazinaPristupa.VISOKA)) {
			return -1;
		}
		return PristupBaziPodataka.izmjena(ime);
	}

}
