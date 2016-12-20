package Model;

import java.util.Map;
import java.util.Map.Entry;

import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;

public class Dokaz extends PristupBaziPodataka implements Strategija<Dokaz> {

	
	private Integer ID;
	private String  nazivSlucaja;
	private String  naziv;
	private String  krvnaGrupa;
	private String  DNASekvenca;
	private String  tipOruzja;
	private String  otisakPrsta; 
	private String  fotografija;
	

	public Dokaz() {
		super();
	}

	public Dokaz(Integer iD, String naziv,String fotografija, String krvnaGrupa, String nazivSlucaja,String dNASekvenca, String tipOruzja,
			String otisakPrsta) {
		super();
		ID = iD;
		this.nazivSlucaja = nazivSlucaja;
		this.naziv = naziv;
		this.krvnaGrupa = krvnaGrupa;
		DNASekvenca = dNASekvenca;
		this.tipOruzja = tipOruzja;
		this.otisakPrsta = otisakPrsta;
		this.fotografija = fotografija;
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
	public String generirajSQLupit(Map<String, String> kombinacija){
		String where="";
		String query;
		Boolean prvi= true;
		
		for (Entry<String, String> entry: kombinacija.entrySet()){
			if(prvi) {
				where+=entry.getKey()+"="+" ?"; prvi=false;
			}
			else where+= " AND "+entry.getKey()+"="+" ?";	
		}
		query = "SELECT distinct DokazniMaterijal.*, KrvnaGrupa.nazivKrvnaGrupa, "
				+"				 PolicijskiSlučaj.nazivSlučaja, OtisakPrsta.fotografijaURL, "
				+"				 DNASekvenca.nazivDNASekvenca, TipOružja.nazivOružja "
				+"FROM  DokazniMaterijal" 
				+"		left  join ListaDNASekvenciNaDokaznomMaterijalu "
				+"   		on ListaDNASekvenciNaDokaznomMaterijalu.dokazniMaterijalID=DokazniMaterijal.brojDokaznogMaterijala"
				+"      left  join DNASekvenca" 
				+"   		on DNASekvenca.dnaSekvencaID=ListaDNASekvenciNaDokaznomMaterijalu.dnaSekvencaID"
				+"      left  join ListaKrvnihGrupaNaDokaznomMaterijalu" 	 
				+"   		on ListaKrvnihGrupaNaDokaznomMaterijalu.dokazniMaterijalID=DokazniMaterijal.brojDokaznogMaterijala"
				+"      left  join KrvnaGrupa" 	  
				+"  		on KrvnaGrupa.krvnaGrupaID=ListaKrvnihGrupaNaDokaznomMaterijalu.krvnaGrupaID"
				+"      left  join ListaOružja" 	  
				+"   		on ListaOružja.brojDokaznogMaterijala=DokazniMaterijal.brojDokaznogMaterijala"
				+"      left  join TipOružja"    
				+"   		on TipOružja.tipOružjaID=ListaOružja.tipOružjaID "
				+"      left  join PolicijskiSlučaj" 	   
				+"   		on PolicijskiSlučaj.brojSlučaja=DokazniMaterijal.brojSlučaja "
				+"      left  join ListaOtisakaPrstijuNaDokaznomMaterijalu" 	
				+" 	    	on ListaOtisakaPrstijuNaDokaznomMaterijalu.dokazniMaterijalID=DokazniMaterijal.brojDokaznogMaterijala"				
				+"      left  join OtisakPrsta "    
				+"			on OtisakPrsta.otisakPrstaID=ListaOtisakaPrstijuNaDokaznomMaterijalu.otisakPrstaID "
				+"WHERE ";
		
		System.out.println(query+where);
		return query+where;
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

	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}

	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}

	public String getDNASekvenca() {
		return DNASekvenca;
	}

	public void setDNASekvenca(String dNASekvenca) {
		DNASekvenca = dNASekvenca;
	}

	public String getTipOruzja() {
		return tipOruzja;
	}

	public void setTipOruzja(String tipOruzja) {
		this.tipOruzja = tipOruzja;
	}

	public String getOtisakPrsta() {
		return otisakPrsta;
	}

	public void setOtisakPrsta(String otisakPrsta) {
		this.otisakPrsta = otisakPrsta;
	}

	public String getFotografija() {
		return fotografija;
	}

	public void setFotografija(String fotografija) {
		this.fotografija = fotografija;
	}

	@Override
	public String toString() {
		return "Dokaz [ID=" + ID + ", nazivSlucaja=" + nazivSlucaja + ", naziv=" + naziv + ", krvnaGrupa=" + krvnaGrupa
				+ ", DNASekvenca=" + DNASekvenca + ", tipOruzja=" + tipOruzja + ", otisakPrsta=" + otisakPrsta
				+ ", fotografija=" + fotografija + "]";
	}
	
	
}
