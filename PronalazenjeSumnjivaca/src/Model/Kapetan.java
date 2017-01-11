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
	public static String dodajNoviSlucaj(Slucaj slucaj) {
		return PristupBaziPodataka.dodajNoviSlucaj(slucaj);	
	}
	public static boolean dodajNovogKriminalca(Osumnjiceni osumnjicen) {
		Context<Osumnjiceni> osumnjiceni=new Context<>(new Osumnjiceni());
		return osumnjiceni.dodajNovogKriminalca(osumnjicen);
	}

    @Override
    public String toString() {
        return "Kapetan "+ime+" "+prezime+" ("+super.getOib()+")";
    }
	
        

}
