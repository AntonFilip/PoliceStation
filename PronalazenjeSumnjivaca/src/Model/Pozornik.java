package Model;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import Controller.RazinaPristupa;

public class Pozornik extends Osoba {

	RazinaPristupa razinaPristupa;
	String username;
	String password;

	public Pozornik() {
		razinaPristupa = RazinaPristupa.NISKA;
	}

	public Pozornik(String ime, String prezime) {

		this.ime = ime;
		this.prezime = prezime;
		razinaPristupa = RazinaPristupa.NISKA;

	}

	public RazinaPristupa getAccess() {
		return this.razinaPristupa;
	}

	static public Pozornik prijava(String korisnickoIme, String lozinka) throws SQLException {
		return PristupBaziPodataka.prijava(korisnickoIme, lozinka);
	}

	public Osumnjiceni posaljiUpitZaOsumnjicenog(String ime) {
		PristupBaziPodataka.posaljiUpit(ime);
		return new Osumnjiceni();
	}

	public Slucaj posaljiUpitZaSlucaj(String ime) {
		PristupBaziPodataka.posaljiUpit(ime);
		return new Slucaj();
	}

	public Dokaz posaljiUpitZaDokaz(String ime) {
		PristupBaziPodataka.posaljiUpit(ime);
		return new Dokaz();
	}

	public int izmjeni(String ime) {
		if (!razinaPristupa.equals(RazinaPristupa.VISOKA)) {
			return -1;
		}
		return PristupBaziPodataka.izmjena(ime);
	}

	public Map<Dokaz, Float> posaljiUpit(Dokaz dokaz) throws SQLException{		
		Map<String, String> listaAtributa=new LinkedHashMap<>();
		Integer brojAtributaDokaza;
		Map<Dokaz, Float> unos=new LinkedHashMap<>();
		Context<Dokaz> dokazi=new Context<>(new Dokaz());
		brojAtributaDokaza=dokazi.generirajKombinacijeAtributa(dokaz, listaAtributa);
		
		List<Map<String, String>> kombAtributa=Kombinacije.sloziKombinacije(listaAtributa);
		
		kombAtributa.toArray();
		for (int i=kombAtributa.size()-1;i>=0;i--){
			Map<String, String> mapa=kombAtributa.get(i);
			if (PristupBaziPodataka.vratiDokaze(mapa)!=null){
				List<Dokaz> liDokaza=PristupBaziPodataka.vratiDokaze(mapa);
				Float postotakSlaganja=(float) (mapa.size())/(float) brojAtributaDokaza *100;	
				for(Dokaz dok: liDokaza){
					if (dok!=null) {
						if(!unos.containsKey(dok)) unos.put(dok, postotakSlaganja);
						
					}
				}
			}
		}
		
		for (Entry<Dokaz, Float> en:unos.entrySet()){
			System.out.println("PostotakSlaganja: "+en.getValue()+ "   Dokaz: " + en.getKey());
		}
		
		return unos;
	}

}
