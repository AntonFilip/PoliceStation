package Model;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class FizickeOsobine {
	private Spol spol;
	private String rasa;
	
	private Float visina;
	private Float visinaMin;
	private Float visinaMax;
	
	
	private Float tezina;
	private Float tezinaMin;
	private Float tezinaMax;
	
	
	private Integer godine;
	private Integer godineMin;
	private Integer godineMax;

	private String bojaKose;
	private String oblikGlave;
	private String oblikFrizure;
	private String bojaOciju;
	GradaTijela gradaTijela;
	private Set<String> tetovaze = new HashSet<>();
	private Set<String> fizickiNedostatci=new HashSet<>();
	private Set<String> bolesti=new HashSet<>();
	private Set<String> ostaleFizickeOsobine=new HashSet<>();
	
	public FizickeOsobine() {
		super();
	}

	public FizickeOsobine(Spol spol, String rasa, Float visina, Float tezina, Integer godine, String bojaKose,
			String oblikGlave, String oblikFrizure, String bojaOciju, GradaTijela gradaTijela, Set<String> tetovaze,
			Set<String> fizickiNedostatci, Set<String> bolesti, Set<String> ostaleFizickeOsobine) {
		super();
		this.spol = spol;
		this.rasa = rasa;
		this.visina = visina;
		this.tezina = tezina;
		this.godine = godine;
		this.bojaKose = bojaKose;
		this.oblikGlave = oblikGlave;
		this.oblikFrizure = oblikFrizure;
		this.bojaOciju = bojaOciju;
		this.gradaTijela = gradaTijela;
		this.tetovaze = tetovaze;
		this.fizickiNedostatci = fizickiNedostatci;
		this.bolesti = bolesti;
		this.ostaleFizickeOsobine = ostaleFizickeOsobine;
	}
	
	

	public Float getVisinaMin() {
		return visinaMin;
	}

	public void setVisinaMin(Float visinaMin) {
		this.visinaMin = visinaMin;
	}

	public Float getVisinaMax() {
		return visinaMax;
	}

	public void setVisinaMax(Float visinaMax) {
		this.visinaMax = visinaMax;
	}

	public Float getTezinaMin() {
		return tezinaMin;
	}

	public void setTezinaMin(Float tezinaMin) {
		this.tezinaMin = tezinaMin;
	}

	public Float getTezinaMax() {
		return tezinaMax;
	}

	public void setTezinaMax(Float tezinaMax) {
		this.tezinaMax = tezinaMax;
	}

	public Integer getGodineMin() {
		return godineMin;
	}

	public void setGodineMin(Integer godineMin) {
		this.godineMin = godineMin;
	}

	public Integer getGodineMax() {
		return godineMax;
	}

	public void setGodineMax(Integer godineMax) {
		this.godineMax = godineMax;
	}

	public Spol getSpol() {
		return spol;
	}

	public void setSpol(Spol spol) {
		this.spol = spol;
	}

	public String getRasa() {
		return rasa;
	}

	public void setRasa(String rasa) {
		this.rasa = rasa;
	}

	public Float getVisina() {
		return visina;
	}

	public void setVisina(Float visina) {
		this.visina = visina;
	}

	public Float getTezina() {
		return tezina;
	}

	public void setTezina(Float tezina) {
		this.tezina = tezina;
	}

	public Integer getGodine() {
		return godine;
	}

	public void setGodine(Integer godine) {
		this.godine = godine;
	}

	public String getBojaKose() {
		return bojaKose;
	}

	public void setBojaKose(String bojaKose) {
		this.bojaKose = bojaKose;
	}

	public String getOblikGlave() {
		return oblikGlave;
	}

	public void setOblikGlave(String oblikGlave) {
		this.oblikGlave = oblikGlave;
	}

	public String getOblikFrizure() {
		return oblikFrizure;
	}

	public void setOblikFrizure(String oblikFrizure) {
		this.oblikFrizure = oblikFrizure;
	}

	public String getBojaOciju() {
		return bojaOciju;
	}

	public void setBojaOciju(String bojaOciju) {
		this.bojaOciju = bojaOciju;
	}

	public GradaTijela getGradaTijela() {
		return gradaTijela;
	}

	public void setGradaTijela(GradaTijela gradaTijela) {
		this.gradaTijela = gradaTijela;
	}

	public Set<String> getTetovaze() {
		return tetovaze;
	}

	public void setTetovaze(Set<String> tetovaze) {
		this.tetovaze = tetovaze;
	}
	
	public boolean addTetovaza (String tetovaza){
		return tetovaze.add(tetovaza);
	}
	
	public boolean addAllTetovaze (Collection<String> tetovaze){
		return tetovaze.addAll(tetovaze);
	}

	public Set<String> getFizickiNedostatci() {
		return fizickiNedostatci;
	}

	public void setFizickiNedostatci(Set<String> fizickiNedostatci) {
		this.fizickiNedostatci = fizickiNedostatci;
	}
	
	public boolean addFizickiNedostatak(String fizickiN){
		return fizickiNedostatci.add(fizickiN);
	}
	

	public boolean addAllFizickiNedostatak(Collection<String> fizickiN){
		return fizickiNedostatci.addAll(fizickiN);
	}
	

	public Set<String> getBolesti() {
		return bolesti;
	}

	public void setBolesti(Set<String> bolesti) {
		this.bolesti = bolesti;
	}
	
	public boolean addBolest(String bolest){
		return bolesti.add(bolest);
	}
	
	public boolean addAllBolest(Collection<String> bolest){
		return bolesti.addAll(bolest);
	}
	
	public Set<String> getOstaleFizickeOsobine() {
		return ostaleFizickeOsobine;
	}

	public void setOstaleFizickeOsobine(Set<String> ostaleFizickeOsobine) {
		this.ostaleFizickeOsobine = ostaleFizickeOsobine;
	}
	
	public boolean addOstalo(String ostalo){
		return ostaleFizickeOsobine.add(ostalo);
	}

	public boolean addAllOstalo(Collection <String> ostalo){
		return ostaleFizickeOsobine.addAll(ostalo);
	}

	@Override
	public String toString() {
		return "FizickeOsobine [spol=" + spol + ", rasa=" + rasa + ", visina=" + visina + ", visinaMin=" + visinaMin
				+ ", visinaMax=" + visinaMax + ", tezina=" + tezina + ", godine=" + godine + ", bojaKose=" + bojaKose
				+ ", oblikGlave=" + oblikGlave + ", oblikFrizure=" + oblikFrizure + ", bojaOciju=" + bojaOciju
				+ ", gradaTijela=" + gradaTijela + ", tetovaze=" + tetovaze + ", fizickiNedostatci=" + fizickiNedostatci
				+ ", bolesti=" + bolesti + ", ostaleFizickeOsobine=" + ostaleFizickeOsobine + "]";
	}

}
