package Model;

import java.util.HashMap;
import java.util.Map;

public class Statistika {
	private Integer brojKriminalaca;
	private Float postotakRiješenihSlučajeva;
	private Map<String, Float> udioTipovaOružja;
	
	
	public Statistika() {
		super();
		udioTipovaOružja=new HashMap<>();
	}

	public Statistika(Integer brojKriminalaca, Float postotakRiješenihSlučajeva,
			Map<String, Float> udioTipovaOružja) {
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


	public Map<String, Float> getUdioTipovaOružja() {
		return udioTipovaOružja;
	}


	public void setUdioTipovaOružja(Map<String, Float> udioTipovaOružja) {
		this.udioTipovaOružja = udioTipovaOružja;
	}
	
	public void addUdioTipOružja(String key, Float value){
		this.udioTipovaOružja.put(key, value);
	}
	
	@Override
	public String toString() {
		return "Statistika [brojKriminalaca=" + brojKriminalaca + ", postotakRiješenihSlučajeva="
				+ postotakRiješenihSlučajeva + ", udioTipovaOružja=" + udioTipovaOružja + "]";
	}
}
