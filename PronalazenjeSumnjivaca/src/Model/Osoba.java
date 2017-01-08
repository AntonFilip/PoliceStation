package Model;

public class Osoba {
	protected String ime;
	protected String prezime;
	private Long oib;
	private AdresaIMjestoStanovanja adresaPrebivalista;
	
	
	public AdresaIMjestoStanovanja getAdresaPrebivalista() {
		return adresaPrebivalista;
	}

	public void setAdresaPrebivalista(AdresaIMjestoStanovanja adresaPrebivalista) {
		this.adresaPrebivalista = adresaPrebivalista;
	}

	public void setOib(Long oib) {
		this.oib = oib;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Long getOib() {
		return oib;
	}

	public void setOib(long l) {
		this.oib = l;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oib == null) ? 0 : oib.hashCode());
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
		Osoba other = (Osoba) obj;
		if (oib == null) {
			if (other.oib != null)
				return false;
		} else if (!oib.equals(other.oib))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ime+" "+prezime+" ("+oib+")";
	}
	
	
	
}
