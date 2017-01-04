package Model;

import Controller.RazinaPristupa;

public class Kapetan extends Narednik {
	public Kapetan() {
		razinaPristupa = RazinaPristupa.VISOKA;
	}
	public Kapetan(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
		razinaPristupa = RazinaPristupa.VISOKA;

	}
	public boolean dodajNoviSlucaj(Slucaj slucaj) {
		Context<Slucaj> slucaji=new Context<>(new Slucaj());
		return slucaji.dodajNoviSlucaj(slucaj);	
	}

}
