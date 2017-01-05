package Model;

import Controller.RazinaPristupa;

public class Kapetan extends Narednik {
	public Kapetan() {
		razinaPristupa = RazinaPristupa.VISOKA;
	}
	public Kapetan(String ime, String prezime,Integer jedbrojpol) {
		this.ime = ime;
		this.prezime = prezime;
		super.setJedinstveniBroj(jedbrojpol);
		razinaPristupa = RazinaPristupa.VISOKA;

	}
	public boolean dodajNoviSlucaj(Slucaj slucaj) {
		Context<Slucaj> slucaji=new Context<>(new Slucaj());
		return slucaji.dodajNoviSlucaj(slucaj);	
	}

}
