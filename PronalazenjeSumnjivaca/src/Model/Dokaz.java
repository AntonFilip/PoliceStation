package Model;

public class Dokaz {

	private Integer ID;
	private String nazivSlucaja;
	private String naziv;
	
	public String getFotogragija() {
		return fotogragija;
	}
	public void setFotogragija(String fotogragija) {
		this.fotogragija = fotogragija;
	}
	private String fotogragija;
	
	public Integer getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getNazivSlucaja() {
		return nazivSlucaja;
	}
	public void setNazivSlucaja(String nazivSlucaja) {
		this.nazivSlucaja = nazivSlucaja;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}
	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}
	public String getDNASekvenca() {
		return DNASekvenca;
	}
	public void setDNASekvenca(String dNASekvenca) {
		DNASekvenca = dNASekvenca;
	}
	public String getTipOruzja() {
		return tipOruzja;
	}
	public void setTipOruzja(String tipOruzja) {
		this.tipOruzja = tipOruzja;
	}
	public String getOtisakPrsta() {
		return otisakPrsta;
	}
	public void setOtisakPrsta(String otisakPrsta) {
		this.otisakPrsta = otisakPrsta;
	}
	private String krvnaGrupa;
	private String DNASekvenca;
	private String tipOruzja;
	private String otisakPrsta; // OVO NEBU STRING

}
