package Model;

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

}
