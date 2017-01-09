package Model;

import java.util.Map;
import Controller.RazinaPristupa;

public class Pozornik extends Osoba {

	protected RazinaPristupa razinaPristupa;
	private String username;
	private String password;
	private Integer jedinstveniBroj;

	public Pozornik() {
		razinaPristupa = RazinaPristupa.NISKA;
	}

	public Pozornik(String ime, String prezime,Integer jedinstveniBroj) {
		super.setIme(ime);
		super.setPrezime(prezime);
		this.jedinstveniBroj=jedinstveniBroj;
		razinaPristupa = RazinaPristupa.NISKA;
	}

	public Integer getJedinstveniBroj() {
		return jedinstveniBroj;
	}

	public void setJedinstveniBroj(Integer jedinstveniBroj) {
		this.jedinstveniBroj = jedinstveniBroj;
	}

	public RazinaPristupa getAccess() {
		return this.razinaPristupa;
	}

	static public Pozornik prijava(String korisnickoIme, String lozinka) {
		return PristupBaziPodataka.prijava(korisnickoIme, lozinka);
	}

	public Dokaz dohvatiPodatkeDokaz(String id) {
		return PristupBaziPodataka.dohvatiPodatkeDokaz(id);
	}
	public Slucaj dohvatiPodatkeSlucaj(String id) {
		return PristupBaziPodataka.dohvatiPodatkeSlucaj(id);
	}
	public Osumnjiceni dohvatiPodatkeOsumnjiceni(String oib) {
		return PristupBaziPodataka.dohvatiPodatkeOsumnjiceni(oib);
		
	}
	public Map<Dokaz, Float> posaljiUpit(Dokaz dokaz){		
		Context<Dokaz> dokazi=new Context<>(new Dokaz());
		return dokazi.posaljiUpit(dokaz,jedinstveniBroj);	
	}

	public Map<Slucaj, Float> posaljiUpit(Slucaj slucaj){
		Context<Slucaj> slucaji=new Context<>(new Slucaj());
		return slucaji.posaljiUpit(slucaj,jedinstveniBroj);
	}

	public Map<Osumnjiceni, Float> posaljiUpit(Osumnjiceni osumnjiceni){
		Context<Osumnjiceni> os=new Context<>(new Osumnjiceni());
		return os.posaljiUpit(osumnjiceni,jedinstveniBroj);
	}

	
	@Override
	public String toString() {
            return "Pozornik "+ime+" "+prezime+" ("+super.getOib()+")";
	}


}
