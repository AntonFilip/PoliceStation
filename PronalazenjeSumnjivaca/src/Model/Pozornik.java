package Model;

import java.sql.SQLException;
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

	public Pozornik(String ime, String prezime) {
		super.setIme(ime);
		super.setPrezime(prezime);
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

	static public Pozornik prijava(String korisnickoIme, String lozinka) throws SQLException {
		return PristupBaziPodataka.prijava(korisnickoIme, lozinka);
	}

	public Dokaz dohvatiPodatkeDokaz(String id) {
		try {
			return PristupBaziPodataka.dohvatiPodatkeDokaz(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Slucaj dohvatiPodatkeSlucaj(String id) {
		try {
			return PristupBaziPodataka.dohvatiPodatkeSlucaj(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*public Osumnjiceni dohvatiPodatkeOsumnjiceni(String oib) {
		try {
			return PristupBaziPodataka.dohvatiPodatkeOsumnjiceni(oib);
		}
		catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	public Map<Dokaz, Float> posaljiUpit(Dokaz dokaz) throws SQLException{		
		Context<Dokaz> dokazi=new Context<>(new Dokaz());
		return dokazi.posaljiUpit(dokaz);	
	}

	public Map<Slucaj, Float> posaljiUpit(Slucaj slucaj){
		Context<Slucaj> slucaji=new Context<>(new Slucaj());
		return slucaji.posaljiUpit(slucaj);
	}

	public Map<Osumnjiceni, Float> posaljiUpit(Osumnjiceni osumnjiceni){
		Context<Osumnjiceni> os=new Context<>(new Osumnjiceni());
		return os.posaljiUpit(osumnjiceni);
	}

}
