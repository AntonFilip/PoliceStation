package Model;

import java.time.LocalDateTime;

public class Dogadaj {
	private Integer dogadajID;
	private String naziv;
	private Integer pbrMjesto;
	private Integer brojSlucaja;
	private String adresa;
	private LocalDateTime vrijeme;
	private String nazivMjesto;



	public Dogadaj() {
		super();
	}

	public Dogadaj(Integer dogadajID, String naziv, Integer pbrMjesto, Integer brojSlucaja, String adresa,
			LocalDateTime vrijeme,String nazivMjesto) {
		super();
		this.dogadajID = dogadajID;
		this.naziv = naziv;
		this.pbrMjesto = pbrMjesto;
		this.brojSlucaja = brojSlucaja;
		this.adresa = adresa;
		this.vrijeme = vrijeme;
		this.nazivMjesto=nazivMjesto;
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
	
	public String getNazivMjesto() {
		return nazivMjesto;
	}

	public void setNazivMjesto(String nazivMjesto) {
		this.nazivMjesto = nazivMjesto;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public LocalDateTime getVrijeme() {
		return vrijeme;
	}
	public void setVrijeme(LocalDateTime vrijeme) {
		this.vrijeme = vrijeme;
	}

}
