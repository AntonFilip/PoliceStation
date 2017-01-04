package Model;

import java.time.LocalTime;

public class Dogadaj {
	private Integer dogadajID;
	private String naziv;
	private Integer pbrMjesto;
	private Integer brojSlucaja;
	private String adresa;
	private LocalTime vrijeme;



	public Dogadaj() {
		super();
	}

	public Dogadaj(Integer dogadajID, String naziv, Integer pbrMjesto, Integer brojSlucaja, String adresa,
			LocalTime vrijeme) {
		super();
		this.dogadajID = dogadajID;
		this.naziv = naziv;
		this.pbrMjesto = pbrMjesto;
		this.brojSlucaja = brojSlucaja;
		this.adresa = adresa;
		this.vrijeme = vrijeme;
	}
	public Integer getDogadajID() {
		return dogadajID;
	}
	public void setDogadajID(Integer dogadajID) {
		this.dogadajID = dogadajID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Integer getPbrMjesto() {
		return pbrMjesto;
	}
	public void setPbrMjesto(Integer pbrMjesto) {
		this.pbrMjesto = pbrMjesto;
	}
	public Integer getBrojSlucaja() {
		return brojSlucaja;
	}
	public void setBrojSlucaja(Integer brojSlucaja) {
		this.brojSlucaja = brojSlucaja;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public LocalTime getVrijeme() {
		return vrijeme;
	}
	public void setVrijeme(LocalTime vrijeme) {
		this.vrijeme = vrijeme;
	}

}
