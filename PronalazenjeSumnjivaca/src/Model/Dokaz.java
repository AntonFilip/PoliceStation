package Model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.StringUtils;



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
	
	public static String izmjenaKrvnaGrupa(String value){
		return value+"*KrvnaGrupa.nazivKrvnaGrupa*ListaKrvnihGrupaNaDokaznomMaterijalu.krvnaGrupaID";
	}
	public static String izmjenaOružje(String value){
		return value+"*TipOružja.nazivOružja*ListaOružja.tipOružjaID";
	}
	public static String izmjenaDnaSekvenca(String value){
		return value+"*DNASekvenca.nazivDNASekvenca*ListaDNASekvenciNaDokaznomMaterijalu.dnaSekvencaID";
	}
	public static String izmjenaOtisakPrsta(String value){
		return value+"*OtisakPrsta.fotografijaURL*ListaOtisakaPrstijuNaDokaznomMaterijalu.otisakPrstaID";
	}
        
	
	@Override
	public String toString() {
		return "Dokaz [ID=" + ID + ", nazivSlucaja=" + nazivSlucaja + ", brojSlucaja=" + brojSlucaja + ", naziv="
				+ naziv + ", fotografija=" + fotografija + ", krvnaGrupa=" + krvnaGrupa + ", DNASekvenca=" + DNASekvenca
				+ ", tipOruzja=" + tipOruzja + ", otisakPrsta=" + otisakPrsta + "]";
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
	public String vratiID(){
		return this.ID.toString();
	}
	
	@Override
	public String vratiAtributID(){
		return "brojDokaznogMaterijala";
	}

	public Set<Dokaz> vratiContext(String upit) throws SQLException {
		return PristupBaziPodataka.vratiDokaze(upit);
	}

	@Override
	public String generirajTextualniOpis(Set<String> listaAtributa) {
		StringBuilder sbBuilder=new StringBuilder();
		sbBuilder.append("Traži se dokaz sa sljedećim obilježjima: ");

		for (String string: listaAtributa){
			String [] parts=string.split("\\*");
			String obiljezje=parts[1];
			String vrijednost=parts[0];
			switch (obiljezje){

			case "DNASekvenca.nazivDNASekvenca":
				sbBuilder.append("dna sekvenca - "+vrijednost+", ");
				System.out.println(sbBuilder.toString());
				break;
			case "DokazniMaterijal.brojDokaznogMaterijala": 
				sbBuilder.append("broj dokaznog materijala - "+vrijednost+", ");
				break;
			case "PolicijskiSlučaj.nazivSlučaja":
				sbBuilder.append("naziv slučaja - "+vrijednost+", ");
				break;
			case "KrvnaGrupa.nazivKrvnaGrupa":
				sbBuilder.append("krvna grupa - "+vrijednost+", ");
				break;
			case "TipOružja.nazivOružja":
				sbBuilder.append("oružje - "+vrijednost+", ");
				break;
			case "DokazniMaterijal.nazivDokaznogMaterijala":
				sbBuilder.append("naziv - "+vrijednost+", ");
				break;
			}
		}
		String opis=sbBuilder.toString();
		System.out.println(opis.substring(0, opis.lastIndexOf(",")));
		return opis.substring(0, opis.lastIndexOf(","));
	}

	@Override
	public Set<String> generirajListuIzmjenjenihAtributa(Dokaz izmjenjeniDokaz){
		Set<String> atributiSlucaja=new LinkedHashSet<>();
		if(!this.naziv.equals(izmjenjeniDokaz.getNaziv()) && !StringUtils.isEmptyOrWhitespaceOnly(izmjenjeniDokaz.getNaziv())) atributiSlucaja.add(izmjenjeniDokaz.getNaziv()+"*nazivDokaznogMaterijala");
		if(this.brojSlucaja!=izmjenjeniDokaz.getBrojSlucaja() && izmjenjeniDokaz.getBrojSlucaja()!=null) atributiSlucaja.add(izmjenjeniDokaz.brojSlucaja.toString()+"*brojSlučaja");
		if(!this.fotografija.equals(izmjenjeniDokaz.getFotografija()) && !StringUtils.isEmptyOrWhitespaceOnly(izmjenjeniDokaz.getFotografija())) atributiSlucaja.add(izmjenjeniDokaz.getFotografija()+"*fotografijaDokaznogMaterijalaURL"); 
		System.out.println(atributiSlucaja);
		return atributiSlucaja;
	}
	
	@Override
	public Set<String>  generirajListuAtributaPretrage() {
		Set <String> listaAtributa=new HashSet<>();
		if(DNASekvenca!=null && !DNASekvenca.isEmpty()){
			for (String s:DNASekvenca){
				if (!StringUtils.isEmptyOrWhitespaceOnly(s)) listaAtributa.add(s+ "*DNASekvenca.nazivDNASekvenca"); 
			}
		}

		if(!StringUtils.isEmptyOrWhitespaceOnly(nazivSlucaja)){
			listaAtributa.add( nazivSlucaja+"*PolicijskiSlučaj.nazivSlučaja");
		}

		if(krvnaGrupa!=null && !krvnaGrupa.isEmpty()) {
			for (String s:krvnaGrupa){
				if(!StringUtils.isEmptyOrWhitespaceOnly(s)) listaAtributa.add(s+"*KrvnaGrupa.nazivKrvnaGrupa");
			}
		}

		if(tipOruzja!=null && !tipOruzja.isEmpty()) {
			for(String s:tipOruzja){
				if (!StringUtils.isEmptyOrWhitespaceOnly(s)) listaAtributa.add( s+"*TipOružja.nazivOružja");
			}
		}
		if(!StringUtils.isEmptyOrWhitespaceOnly(naziv)) {
			listaAtributa.add(naziv+"*DokazniMaterijal.nazivDokaznogMaterijala");
		}

		return listaAtributa;
	}

	@Override
	public String generirajSQLupit(String vrijednostPretrage,String relacijaAtribut){
		String select=generirajSelectOsnovniPodaci();
		String from=" ";
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

	@Override
	public String generirajSelectOsnovniPodaci() {
		return " SELECT distinct  DokazniMaterijal.brojDokaznogMaterijala,"
				+ "						DokazniMaterijal.nazivDokaznogMaterijala, "
				+ "						PolicijskiSlučaj.nazivSlučaja "
				+ " FROM  DokazniMaterijal  "
				+ "		left  join PolicijskiSlučaj on PolicijskiSlučaj.brojSlučaja=DokazniMaterijal.brojSlučaja ";
	}

	@Override
	public String generirajUpdateSQL() {
		return "UPDATE DokazniMaterijal SET ";
	}

	@Override
	public String vratiAtributID2() {
		return "dokazniMaterijalID";
	}

	
}
