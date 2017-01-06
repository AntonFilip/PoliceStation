package Model;


public class DnevnikPretrazivanja {

	private Integer ID;
	private String ipAdresa;
	private String textUpita;
	private String sqlUpit;
	private Integer brojPolicajca;
	private String vrijemeUpita;
	
	public String getVrijemeUpita() {
		return vrijemeUpita;
	}
	public void setVrijemeUpita(String vrijemeUpita) {
		this.vrijemeUpita = vrijemeUpita;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getIpAdresa() {
		return ipAdresa;
	}
	public void setIpAdresa(String ipAdresa) {
		this.ipAdresa = ipAdresa;
	}
	public String getTextUpita() {
		return textUpita;
	}
	public void setTextUpita(String textUpita) {
		this.textUpita = textUpita;
	}
	public String getSqlUpit() {
		return sqlUpit;
	}
	public void setSqlUpit(String sqlUpit) {
		this.sqlUpit = sqlUpit;
	}
	public Integer getBrojPolicajca() {
		return brojPolicajca;
	}
	public void setBrojPolicajca(Integer brojPolicajca) {
		this.brojPolicajca = brojPolicajca;
	}
	
	@Override
	public String toString() {
		return "DnevnikPretrazivanja [ID=" + ID + ", ipAdresa=" + ipAdresa + ", textUpita=" + textUpita + ", sqlUpit="
				+ sqlUpit + ", brojPolicajca=" + brojPolicajca + ", vrijemeUpita=" + vrijemeUpita + "]";
	}
}
