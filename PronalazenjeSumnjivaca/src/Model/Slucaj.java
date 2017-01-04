package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Slucaj implements StrategijaUpit<Slucaj>{

	private Integer brojSlucaja;
	private String nazivSlucaja;
	private String opis;
	private Osumnjiceni glavniOsumnjiceni;
	private Set<Osumnjiceni> popisOsumnjicenih = new HashSet<Osumnjiceni>();
	private Set<Osoba> popisSvjedoka = new HashSet<Osoba>();
	private Set<Dokaz> popisDokaza = new HashSet<Dokaz>();
	private Set<Pozornik> popisPolicajaca = new HashSet<Pozornik>();
	private TrenutniStatusSlucaja status;
	private Set<String> fotografijeSlučaja=new HashSet<>();
//TODO	private Set<Dogadaj> popisDogadaja= new HashSet<>();
	
	
	public Slucaj(){
		super();
	}
	
	public Slucaj(Integer brojSlucaja, String nazivSlucaja, String opis, Osumnjiceni glavniOsumnjiceni,
			Set<Osumnjiceni> popisOsumnjicenih, Set<Osoba> popisSvjedoka, Set<Dokaz> popisDokaza, Set<Pozornik> popisPolicajaca,
			TrenutniStatusSlucaja status, Set<String> fotografijeSlučaja, Set<Dogadaj> popisDogadaja) {
		super();
		this.brojSlucaja = brojSlucaja;
		this.nazivSlucaja = nazivSlucaja;
		this.opis = opis;
		this.glavniOsumnjiceni = glavniOsumnjiceni;
		this.popisOsumnjicenih = popisOsumnjicenih;
		this.popisSvjedoka = popisSvjedoka;
		this.popisDokaza = popisDokaza;
		this.popisPolicajaca = popisPolicajaca;
		this.status = status;
		this.fotografijeSlučaja = fotografijeSlučaja;
		this.popisDogadaja = popisDogadaja;
	}

	public Integer getBrojSlucaja() {
		return brojSlucaja;
	}

	public void setBrojSlucaja(Integer brojSlucaja) {
		this.brojSlucaja = brojSlucaja;
	}

	public String getNazivSlucaja() {
		return nazivSlucaja;
	}

	public void setNazivSlucaja(String nazivSlucaja) {
		this.nazivSlucaja = nazivSlucaja;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Osumnjiceni getGlavniOsumnjiceni() {
		return glavniOsumnjiceni;
	}

	public void setGlavniOsumnjiceni(Osumnjiceni glavniOsumnjiceni) {
		this.glavniOsumnjiceni = glavniOsumnjiceni;
	}

	public Set<Osumnjiceni> getPopisOsumnjicenih() {
		return popisOsumnjicenih;
	}

	public void setPopisOsumnjicenih(Set<Osumnjiceni> popisOsumnjicenih) {
		this.popisOsumnjicenih = popisOsumnjicenih;
	}
	
	public boolean addOsumjiceni(Osumnjiceni os){
		return this.popisOsumnjicenih.add(os);
	}
	
	public boolean addAllOsumnjiceni(Collection<Osumnjiceni> osumnjiceni){
		return this.popisOsumnjicenih.addAll(osumnjiceni);
	}
	
	public Set<Osoba> getPopisSvjedoka() {
		return popisSvjedoka;
	}

	public void setPopisSvjedoka(Set<Osoba> popisSvjedoka) {
		this.popisSvjedoka = popisSvjedoka;
	}
	
	public boolean addSvjedok(Osoba svjedok){
		return this.popisSvjedoka.add(svjedok);
	}
	
	public boolean addAllSvjedoci(Collection<Osoba> svjedoci){
		return this.popisSvjedoka.addAll(svjedoci);
	}
	

	public Set<Dokaz> getPopisDokaza() {
		return popisDokaza;
	}

	public void setPopisDokaza(Set<Dokaz> popisDokaza) {
		this.popisDokaza = popisDokaza;
	}
	
	public boolean addDokaz(Dokaz dokaz){
		return this.popisDokaza.add(dokaz);
	}
	
	public boolean addAllDokazi(Collection< Dokaz> dokazi){
		return this.popisDokaza.addAll(dokazi);
	}
	
	public Set<Pozornik> getPopisPolicajaca() {
		return popisPolicajaca;
	}
	
	public void setPopisPolicajaca(Set<Pozornik> popisPolicajaca) {
		this.popisPolicajaca = popisPolicajaca;
	}

	public boolean addPolicajac(Pozornik p){
		return this.popisPolicajaca.add(p);
	}
	
	public boolean addAllPolicajci(Collection<Pozornik> policajci){
		return this.popisPolicajaca.addAll(policajci);
	}
	
	public TrenutniStatusSlucaja getStatus() {
		return status;
	}

	public void setStatus(TrenutniStatusSlucaja status) {
		this.status = status;
	}

	public Set<String> getFotografijeSlučaja() {
		return fotografijeSlučaja;
	}

	public void setFotografijeSlučaja(Set<String> fotografijeSlučaja) {
		this.fotografijeSlučaja = fotografijeSlučaja;
	}
	
	public boolean addFotografija(String foto){
		return this.fotografijeSlučaja.add(foto);
	}
	
	public boolean addAllFotografije(Collection<String> fotografije){
		return this.fotografijeSlučaja.addAll(fotografije);
	}
	
	public Set<Dogadaj> getPopisDogadaja() {
		return popisDogadaja;
	}

	public void setPopisDogadaja(Set<Dogadaj> popisDogadaja) {
		this.popisDogadaja = popisDogadaja;
	}
	
	public boolean addDogadaj(Dogadaj dog){
		return this.popisDogadaja.add(dog);
	}
	
	public boolean addAllDogadaji (Collection<Dogadaj> dogadaji){
		return this.popisDogadaja.addAll(dogadaji);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brojSlucaja == null) ? 0 : brojSlucaja.hashCode());
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
		Slucaj other = (Slucaj) obj;
		if (brojSlucaja == null) {
			if (other.brojSlucaja != null)
				return false;
		} else if (!brojSlucaja.equals(other.brojSlucaja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Slucaj [brojSlucaja=" + brojSlucaja + ", nazivSlucaja=" + nazivSlucaja + ", opis=" + opis
				+ ", glavniOsumnjiceni=" + glavniOsumnjiceni + ", popisOsumnjicenih=" + popisOsumnjicenih
				+ ", popisSvjedoka=" + popisSvjedoka + ", popisDokaza=" + popisDokaza + ", popisPolicajaca="
				+ popisPolicajaca + ", status=" + status + ", fotografijeSlučaja=" + fotografijeSlučaja
				+ ", popisDogadaja=" + popisDogadaja + "]";
	}

	@Override
	public String generirajTextualniOpis(Map<String, String> kombinacija) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> generirajSQLupit(Map<String, String> kombinacija) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer generirajKombinacijeAtributa(Slucaj con, Map<String, String> listaAtributa) {
		Integer brojAtributaDokaza=0;
		
		if (this.getNazivSlucaja()!=null){}
		//TODO
		return null;
	}
	
	
}
