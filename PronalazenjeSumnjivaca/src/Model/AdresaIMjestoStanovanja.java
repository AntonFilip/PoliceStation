package Model;

public class AdresaIMjestoStanovanja {
	Integer pbrMjesto;
	String adresa;
	String nazivMjesta;

	
	public AdresaIMjestoStanovanja() {
		super();
	}

	public AdresaIMjestoStanovanja( Integer pbrMjesto, String adresa, String nazivMjesta) {
		super();
		
		this.pbrMjesto = pbrMjesto;
		this.adresa = adresa;
		this.nazivMjesta = nazivMjesta;
	}
	

	
	public Integer getPbrMjesto() {
		return pbrMjesto;
	}
	public void setPbrMjesto(Integer pbrMjesto) {
		this.pbrMjesto = pbrMjesto;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getNazivMjesta() {
		return nazivMjesta;
	}
	public void setNazivMjesta(String nazivMjesta) {
		this.nazivMjesta = nazivMjesta;
	}
	

}
