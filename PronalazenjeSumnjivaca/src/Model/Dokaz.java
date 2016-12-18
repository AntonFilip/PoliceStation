package Model;

public class Dokaz {

	
	private Integer ID;
	private String nazivSlucaja;
	private String naziv;
	private String krvnaGrupa;
	private String DNASekvenca;
	private String tipOruzja;
	private String otisakPrsta; 
	
	
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNASekvenca == null) ? 0 : DNASekvenca.hashCode());
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((fotogragija == null) ? 0 : fotogragija.hashCode());
		result = prime * result + ((krvnaGrupa == null) ? 0 : krvnaGrupa.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((nazivSlucaja == null) ? 0 : nazivSlucaja.hashCode());
		result = prime * result + ((otisakPrsta == null) ? 0 : otisakPrsta.hashCode());
		result = prime * result + ((tipOruzja == null) ? 0 : tipOruzja.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dokaz other = (Dokaz) obj;
		if (DNASekvenca == null) {
			if (other.DNASekvenca != null)
				return false;
		} else if (!DNASekvenca.equals(other.DNASekvenca))
			return false;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (fotogragija == null) {
			if (other.fotogragija != null)
				return false;
		} else if (!fotogragija.equals(other.fotogragija))
			return false;
		if (krvnaGrupa == null) {
			if (other.krvnaGrupa != null)
				return false;
		} else if (!krvnaGrupa.equals(other.krvnaGrupa))
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (nazivSlucaja == null) {
			if (other.nazivSlucaja != null)
				return false;
		} else if (!nazivSlucaja.equals(other.nazivSlucaja))
			return false;
		if (otisakPrsta == null) {
			if (other.otisakPrsta != null)
				return false;
		} else if (!otisakPrsta.equals(other.otisakPrsta))
			return false;
		if (tipOruzja == null) {
			if (other.tipOruzja != null)
				return false;
		} else if (!tipOruzja.equals(other.tipOruzja))
			return false;
		return true;
	}

}
