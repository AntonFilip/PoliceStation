package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class Dokaz implements StrategijaUpit<Dokaz> {

	private Integer ID;
	private String  nazivSlucaja;
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


	public Dokaz(Integer iD, String nazivSlucaja, String naziv, List<String> krvnaGrupa, List<String> dNASekvenca,
			List<String> tipOruzja, List<String> otisakPrsta, String fotografija) {

		this.ID = iD;
		this.nazivSlucaja = nazivSlucaja;
		this.naziv = naziv;
		this.fotografija=fotografija;

		this.tipOruzja=new LinkedHashSet<>(tipOruzja);
		this.otisakPrsta=new LinkedHashSet<>(otisakPrsta);
		this.DNASekvenca=new LinkedHashSet<>(dNASekvenca);
		this.krvnaGrupa=new LinkedHashSet<>(krvnaGrupa);
	}

	public Dokaz(Integer iD, String nazivSlucaja, String naziv, String krvnaGrupa, String dNASekvenca,
			String tipOruzja, String otisakPrsta, String fotografija) {

		this.ID = iD;
		this.nazivSlucaja = nazivSlucaja;
		this.naziv = naziv;

		this.tipOruzja=new LinkedHashSet<>();
		this.tipOruzja.add(tipOruzja);
		this.otisakPrsta=new LinkedHashSet<>();
		this.otisakPrsta.add(otisakPrsta);
		this.DNASekvenca=new LinkedHashSet<>();
		this.DNASekvenca.add(dNASekvenca);
		this.krvnaGrupa=new LinkedHashSet<>();
		this.krvnaGrupa.add(krvnaGrupa);
	}


	public String generirajTextualniOpis (Map<String, String> kombinacija) {
		String textOpis="";
		String kar ="";	
		int j=0;
		for (Entry<String, String> entry: kombinacija.entrySet()){
			if (j==0) kar+= " "+entry.getKey()+"-"+entry.getValue();
			else kar+= ", "+entry.getKey()+"-"+entry.getValue()+" ";
			j++;
		}
		textOpis +=" Traži se dokaz sa sljedećim karakteristikama: "+kar;
		return  textOpis;
	}

	@Override
	public Integer generirajKombinacijeAtributa(Dokaz dokaz,Map<String, String> listaAtributa) {
		Integer brojAtributaDokaza=0;
		
		if(dokaz.getDNASekvenca()!=null){
			for (String s:dokaz.getDNASekvenca()){
				listaAtributa.put(s, "DNASekvenca.nazivDNASekvenca");
				brojAtributaDokaza++;
			}
		}
		
		if(dokaz.getNazivSlucaja()!=null){
			listaAtributa.put( dokaz.getNazivSlucaja(),"PolicijskiSlučaj.nazivSlučaja");
			brojAtributaDokaza++;
		}

		if(dokaz.getKrvnaGrupa()!=null) {
			for (String s:dokaz.getKrvnaGrupa()){
				listaAtributa.put(s,"KrvnaGrupa.nazivKrvnaGrupa");
				brojAtributaDokaza++;
			}
		}

		if(dokaz.getTipOruzja()!=null) {
			for(String s:dokaz.getTipOruzja()){
				listaAtributa.put( s,"TipOružja.nazivOružja");
				brojAtributaDokaza++;
			}
		}
		if(dokaz.getNaziv()!=null) {
			listaAtributa.put(dokaz.getNaziv(),"DokazniMaterijal.nazivDokaznogMaterijala");
			brojAtributaDokaza++;
		}
		
		return brojAtributaDokaza;
	}

	@Override
	public ArrayList<String> generirajSQLupit(Map<String, String> kombinacija){
		String select=" SELECT distinct  DokazniMaterijal.brojDokaznogMaterijala,"
				+ "						DokazniMaterijal.nazivDokaznogMaterijala, "
				+ "						DokazniMaterijal.fotografijaDokaznogMaterijalaURL, "
				+ "						PolicijskiSlučaj.nazivSlučaja ";
		
		String from=" FROM  DokazniMaterijal  "
				+"		left  join PolicijskiSlučaj on PolicijskiSlučaj.brojSlučaja=DokazniMaterijal.brojSlučaja ";
		String where="";
		Boolean prvi= true;
		Integer brojKrvnihGrupa=0;
		Integer brojOružja=0;
		Integer brojDNA=0;
		String oruzje="";
		String krvneGrupe="";
		String dnaSek="";



		for (Entry<String, String> entry: kombinacija.entrySet()){
			String value=entry.getValue();
			if(value.equals("DNASekvenca.nazivDNASekvenca")){
				brojDNA++;
				from+="left  join ListaDNASekvenciNaDokaznomMaterijalu l"+brojDNA
						+" on l"+brojDNA+".dokazniMaterijalID=DokazniMaterijal.brojDokaznogMaterijala "
						+"      left  join DNASekvenca dna"+brojDNA 
						+"   		on dna"+brojDNA +".dnaSekvencaID=l"+brojDNA+".dnaSekvencaID ";
				dnaSek+=", dna"+brojDNA+".nazivDNASekvenca ";
				if (prvi) {
					where+=" WHERE dna"+brojDNA+".nazivDNASekvence=\""+entry.getKey()+"\""; 
					prvi=false;
				}else where+= " AND dna"+brojDNA+".nazivDNASekvence=\""+entry.getKey()+"\"";	
			}	

			else if(value.equals("KrvnaGrupa.nazivKrvnaGrupa"))	{
				brojKrvnihGrupa++;
				from+=" left  join ListaKrvnihGrupaNaDokaznomMaterijalu lk"+brojKrvnihGrupa
					+" on lk"+brojKrvnihGrupa+".dokazniMaterijalID=DokazniMaterijal.brojDokaznogMaterijala "
					+ "    left  join KrvnaGrupa k"+brojKrvnihGrupa 	  
					+"  		on k"+brojKrvnihGrupa+".krvnaGrupaID=lk"+brojKrvnihGrupa+".krvnaGrupaID ";
				krvneGrupe+=", k"+brojKrvnihGrupa+".nazivKrvnaGrupa ";
				if(prvi) {where+=" WHERE k"+brojKrvnihGrupa+".nazivKrvnaGrupa=\""+entry.getKey()+"\"";
					prvi=false;
				}
				else where+=" AND k"+brojKrvnihGrupa+".nazivKrvnaGrupa=\""+entry.getKey()+"\"";
			}
			
			else if(value.equals("TipOružja.nazivOružja"))	{
				brojOružja++;
				from+="left  join ListaOružja lo"+brojOružja
						+" 			on lo"+brojOružja+".brojDokaznogMaterijala=DokazniMaterijal.brojDokaznogMaterijala"      
						+"		left  join TipOružja t"+brojOružja    
						+"   		on t"+brojOružja+".tipOružjaID=lo"+brojOružja+".tipOružjaID ";
				
				oruzje+=", t"+brojOružja+".nazivOružja";
				if(prvi) {
					where+=" WHERE t"+brojOružja+".nazivOružja=\""+entry.getKey()+"\"";
					prvi=false;
				}
				else where+=" AND t"+brojOružja+".nazivOružja=\""+entry.getKey()+"\"";
			}
		}
		select+=krvneGrupe+dnaSek+oruzje;
		ArrayList<String> rez=new ArrayList<>();
		rez.add(select+from+where);
		rez.add(brojDNA.toString());
		rez.add(brojKrvnihGrupa.toString());
		rez.add(brojOružja.toString());
		
		return rez;
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
		return "Dokaz [ID=" + ID +  ", naziv=" + naziv + ", krvnaGrupa=" + krvnaGrupa
				+ ", DNASekvenca=" + DNASekvenca + ", tipOruzja=" + tipOruzja + ", otisakPrsta=" + otisakPrsta
				+ ", fotografija=" + fotografija + "]";
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


	



}
