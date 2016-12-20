package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.undo.UndoableEditSupport;

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

	public Map<Dokaz, Float> posaljiUpit( Dokaz dokaz) throws SQLException{
		LinkedHashMap<String, String> listaAtributa=new LinkedHashMap();
		List<Map<Dokaz, Integer>> rezultat=new ArrayList<Map<Dokaz,Integer>>();
		LinkedHashMap<Dokaz, Float> unos=new LinkedHashMap<>();
		Integer brojAtributaDokaza=0;

		if(!(dokaz.getDNASekvenca()==null)){
			listaAtributa.put("DNASekvenca.nazivDNASekvenca", dokaz.getDNASekvenca());
			brojAtributaDokaza++;
		}
		if(!(dokaz.getNazivSlucaja()==null)){
			listaAtributa.put("PolicijskiSlučaj.nazivSlučaja", dokaz.getNazivSlucaja());
			brojAtributaDokaza++;
		}

		if(!(dokaz.getKrvnaGrupa()==null)) {
			listaAtributa.put("KrvnaGrupa.nazivKrvnaGrupa", dokaz.getKrvnaGrupa());
			brojAtributaDokaza++;
		}

		if(!(dokaz.getTipOruzja()==null)) {
			listaAtributa.put("TipOružja.nazivOružja", dokaz.getTipOruzja());;
			brojAtributaDokaza++;
		}
		if(!(dokaz.getNaziv()==null)) {
			listaAtributa.put("DokazniMaterijal.nazivDokaznogMaterijala", dokaz.getNaziv());
			brojAtributaDokaza++;
		}


		List<LinkedHashMap<String, String>> kombAtributa=Kombinacije.sloziKombinacije(listaAtributa);
		for (LinkedHashMap<String, String> mapa:kombAtributa){
			if (PristupBaziPodataka.vratiDokaze(mapa)!=null){
				List<Dokaz> liDokaza=PristupBaziPodataka.vratiDokaze(mapa);
				Float postotakSlaganja=(float) (mapa.size())/(float) brojAtributaDokaza *100;	
				for(Dokaz dok: liDokaza){
					if (dok!=null) {
						unos.put(dok, postotakSlaganja);
					}
				}
			}
		}
		System.out.println(unos);
		return unos;
	}

}
