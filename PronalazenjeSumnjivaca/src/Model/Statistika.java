package Model;

import java.util.Map;

public class Statistika {
	private Integer brojKriminalaca;
	private Float postotakRiješenihSlučajeva;
	private Map<String, Integer> udioTipovaOružja;
	
	
	public Statistika() {
		super();
	}

	public Statistika(Integer brojKriminalaca, Float postotakRiješenihSlučajeva,
			Map<String, Integer> udioTipovaOružja) {
		super();
		this.brojKriminalaca = brojKriminalaca;
		this.postotakRiješenihSlučajeva = postotakRiješenihSlučajeva;
		this.udioTipovaOružja = udioTipovaOružja;
	}
	
	


	public Integer getBrojKriminalaca() {
		return brojKriminalaca;
	}


	public void setBrojKriminalaca(Integer brojKriminalaca) {
		this.brojKriminalaca = brojKriminalaca;
	}


	public Float getPostotakRiješenihSlučajeva() {
		return postotakRiješenihSlučajeva;
	}


	public void setPostotakRiješenihSlučajeva(Float postotakRiješenihSlučajeva) {
		this.postotakRiješenihSlučajeva = postotakRiješenihSlučajeva;
	}


	public Map<String, Integer> getUdioTipovaOružja() {
		return udioTipovaOružja;
	}


	public void setUdioTipovaOružja(Map<String, Integer> udioTipovaOružja) {
		this.udioTipovaOružja = udioTipovaOružja;
	}
	
	
}
