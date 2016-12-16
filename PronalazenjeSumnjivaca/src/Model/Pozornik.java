package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	static public Pozornik prijava(String korisnickoIme, String lozinka) {
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

	public List<Map<Dokaz, Integer>> posaljiUpit( Dokaz dokaz){
		LinkedHashMap<String, String> listaAtributa=new LinkedHashMap();
		List<Map<Dokaz, Integer>> rezultat=new ArrayList<Map<Dokaz,Integer>>();
		
		if(!(dokaz.getDNASekvenca()==null)){
			listaAtributa.put("nazivDNASekvenca", dokaz.getDNASekvenca());
		}
		if(!(dokaz.getNazivSlucaja()==null)){
		
			listaAtributa.put("nazivSluèaja", dokaz.getNazivSlucaja());
		}
		System.out.println(dokaz.getKrvnaGrupa());
		
		if(!(dokaz.getKrvnaGrupa()==null)) {
			listaAtributa.put("nazivKrvnaGrupa", dokaz.getKrvnaGrupa());
		}
	
		if(!(dokaz.getTipOruzja()==null)) {
			listaAtributa.put("nazivOružja", dokaz.getTipOruzja());;
		}
		if(!(dokaz.getNaziv()==null)) {
			listaAtributa.put("nazivDokaznogMaterijala", dokaz.getNaziv());
		}
		System.out.println("lista atributa: "+listaAtributa+"\n");
		List<LinkedHashMap<String, String>> kombAtributa=Kombinacije.sloziKombinacije(listaAtributa);
		for (Map<String, String> element :kombAtributa){
			System.out.println(element);
		}
		
		for (LinkedHashMap<String, String> mapa:kombAtributa){
			List<Dokaz> liDokaza=PristupBaziPodataka.vratiDokaze(mapa);
			Integer postotakSlaganja=mapa.size()/5;
			for(Dokaz dok: liDokaza){
				LinkedHashMap<Dokaz, Integer> unos=new LinkedHashMap<>();
				unos.put(dok, postotakSlaganja);
				rezultat.add(unos);
			}
		}
		
		return rezultat;
	}
	
}
