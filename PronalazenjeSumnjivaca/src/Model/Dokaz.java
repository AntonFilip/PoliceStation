package Model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



public class Dokaz implements StrategijaUpit<Dokaz> {

	private Integer ID;
	private String  nazivSlucaja;
	private Integer brojSlucaja;
	private String  naziv;
	private String  fotografija;
	private Set<String> krvnaGrupa;
	private Set<String> DNASekvenca;
	private Set<String> tipOruzja;
	private Set<String> otisakPrsta; 

	public Dokaz() {
		super();
		krvnaGrupa=new LinkedHashSet<>();
		DNASekvenca=new LinkedHashSet<>();
		tipOruzja=new LinkedHashSet<>();
		otisakPrsta=new LinkedHashSet<>();
	}

	public Dokaz(Integer iD, String nazivSlucaja,Integer brojSlucaja, String naziv, List<String> krvnaGrupa, List<String> dNASekvenca,
			List<String> tipOruzja, List<String> otisakPrsta, String fotografija) {

		this.ID = iD;
		this.nazivSlucaja = nazivSlucaja;
		this.naziv = naziv;
		this.fotografija=fotografija;
		this.brojSlucaja=brojSlucaja;
		this.tipOruzja=new LinkedHashSet<>(tipOruzja);
		this.otisakPrsta=new LinkedHashSet<>(otisakPrsta);
		this.DNASekvenca=new LinkedHashSet<>(dNASekvenca);
		this.krvnaGrupa=new LinkedHashSet<>(krvnaGrupa);
	}

	public Dokaz(Integer iD,Integer brojSlucaja, String nazivSlucaja, String naziv, String krvnaGrupa, String dNASekvenca,
			String tipOruzja, String otisakPrsta, String fotografija) {

		this.ID = iD;
		this.nazivSlucaja = nazivSlucaja;
		this.naziv = naziv;
		this.brojSlucaja=brojSlucaja;
		this.tipOruzja=new LinkedHashSet<>();
		this.tipOruzja.add(tipOruzja);
		this.otisakPrsta=new LinkedHashSet<>();
		this.otisakPrsta.add(otisakPrsta);
		this.DNASekvenca=new LinkedHashSet<>();
		this.DNASekvenca.add(dNASekvenca);
		this.krvnaGrupa=new LinkedHashSet<>();
		this.krvnaGrupa.add(krvnaGrupa);
	}

	@Override
	public Set<String>  generirajListuAtributa() {
		Set <String> listaAtributa=new HashSet<>();
		if(DNASekvenca!=null){
			for (String s:DNASekvenca){
				listaAtributa.add(s+ "*DNASekvenca.nazivDNASekvenca");
			}
		}

		if(nazivSlucaja!=null){
			listaAtributa.add( nazivSlucaja+"*PolicijskiSlučaj.nazivSlučaja");
		}

		if(krvnaGrupa!=null) {
			for (String s:krvnaGrupa){
				listaAtributa.add(s+"*KrvnaGrupa.nazivKrvnaGrupa");
			}
		}

		if(tipOruzja!=null) {
			for(String s:tipOruzja){
				listaAtributa.add( s+"*TipOružja.nazivOružja");
			}
		}
		if(naziv!=null) {
			listaAtributa.add(naziv+"*DokazniMaterijal.nazivDokaznogMaterijala");
		}

		return listaAtributa;
	}

	@Override
	public String generirajSQLupit(String vrijednostPretrage,String relacijaAtribut){
		String select=" SELECT distinct  DokazniMaterijal.brojDokaznogMaterijala,"
				+ "						DokazniMaterijal.nazivDokaznogMaterijala, "
				+ "						DokazniMaterijal.fotografijaDokaznogMaterijalaURL, "
				+ "						PolicijskiSlučaj.nazivSlučaja ";

		String from=" FROM  DokazniMaterijal  "
				+"		left  join PolicijskiSlučaj on PolicijskiSlučaj.brojSlučaja=DokazniMaterijal.brojSlučaja ";
		String where=StrategijaUpit.generirajWhere(relacijaAtribut, vrijednostPretrage);
		String relAtr2="DokazniMaterijal.brojDokaznogMaterijala";

		switch (relacijaAtribut) {
		case "DNASekvenca.nazivDNASekvenca":
			from+=StrategijaUpit.generirajFrom("ListaDNASekvenciNaDokaznomMaterijalu", "ListaDNASekvenciNaDokaznomMaterijalu.dokazniMaterijalID",relAtr2);
			from+=StrategijaUpit.generirajFrom("DNASekvenca", "DNASekvenca.dnaSekvencaID", "ListaDNASekvenciNaDokaznomMaterijalu.dnaSekvencaID");
			break;
		case "KrvnaGrupa.nazivKrvnaGrupa":
			from+=StrategijaUpit.generirajFrom("ListaKrvnihGrupaNaDokaznomMaterijalu", "ListaKrvnihGrupaNaDokaznomMaterijalu.dokazniMaterijalID", relAtr2);
			from+=StrategijaUpit.generirajFrom("KrvnaGrupa","KrvnaGrupa.krvnaGrupaID" , "ListaKrvnihGrupaNaDokaznomMaterijalu.krvnaGrupaID");
			break;
		case "TipOružja.nazivOružja":
			from+=StrategijaUpit.generirajFrom("ListaOružja", "ListaOružja.brojDokaznogMaterijala", relAtr2);
			from+=StrategijaUpit.generirajFrom("TipOružja", "TipOružja.tipOružjaID", "ListaOružja.tipOružjaID");
			break;
		default: break;
		}
		System.out.println(select+from+where);
		return select+from+where;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
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
	
	public Integer getBrojSlucaja() {
		return brojSlucaja;
	}

	public void setBrojSlucaja(Integer brojSlucaja) {
		this.brojSlucaja = brojSlucaja;
	}

	public Set<String> getKrvnaGrupa() {
		return krvnaGrupa;
	}

	public boolean addKrvnaGrupa(String krvnaGrupa){
		return this.krvnaGrupa.add(krvnaGrupa);
	}

	public void addAllKrvnaGrupa(Collection<String> krvnaGrupa) {
		this.krvnaGrupa.addAll(krvnaGrupa);
	}

	public Set<String> getDNASekvenca() {
		return DNASekvenca;
	}

	public boolean addDNASekvenca(String dna){
		return DNASekvenca.add(dna);
	}

	public void addAllDNASekvenca(Collection<String> dNASekvenca) {
		DNASekvenca.addAll(dNASekvenca);
	}

	public Set<String> getTipOruzja() {
		return tipOruzja;
	}

	public boolean addTipOruzja(String oruzje){
		return tipOruzja.add(oruzje);
	}

	public void addAllTipOruzja(Collection <String> tipOruzja) {
		this.tipOruzja.addAll(tipOruzja);
	}

	public Set<String> getOtisakPrsta() {
		return otisakPrsta;
	}

	public boolean addOtisakPrsta(String otisak){
		return otisakPrsta.add(otisak);
	}

	public void setOtisakPrsta(Collection<String> otisakPrsta) {
		this.otisakPrsta.addAll(otisakPrsta);
	}

	public String getFotografija() {
		return fotografija;
	}

	public void setFotografija(String fotografija) {
		this.fotografija = fotografija;
	}

	@Override
	public String toString() {
		return "Dokaz [ID=" + ID + ", nazivSlucaja=" + nazivSlucaja + ", naziv=" + naziv + ", fotografija="
				+ fotografija + ", krvnaGrupa=" + krvnaGrupa + ", DNASekvenca=" + DNASekvenca + ", tipOruzja="
				+ tipOruzja + ", otisakPrsta=" + otisakPrsta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
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
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public List<Dokaz> vratiCon(String vrijednostPretrage,String relacijaAtribut) throws SQLException {
		return PristupBaziPodataka.vratiDokaze(vrijednostPretrage,relacijaAtribut);
	}

	@Override
	public String generirajTextualniOpis(Set<String> listaAtributa) {
		return null;
	}
}
