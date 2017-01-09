package Model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import java.util.Set;

public class Context <E> {
	private StrategijaUpit<E> strategy;

	public Context(StrategijaUpit <E> strategy){
		this.strategy = strategy;
	}
	public String izgenerirajUpit(String vrijednostPretrage,String relacijaAtribut){
		return strategy.generirajSQLupit(vrijednostPretrage,relacijaAtribut);
	}

	private String izgenerirajTekstualniOpis(Set <String> listaAtributa){
		return strategy.generirajTextualniOpis(listaAtributa);
	}
	private Set<String> generirajListuAtributaPretrage(){
		return strategy.generirajListuAtributaPretrage();
	};
	private Set<String> generirajListuIzmjenjenihAtributa(E izmjenjeniCon){
		return strategy.generirajListuIzmjenjenihAtributa(izmjenjeniCon);
	}
	
	private boolean izbrisiIzListe(String ID,String atributID,String vrijednost, String relacija1,String atribut1,String relacija2,String atribu2){
		if(relacija2.equals("ListaOružja")) atributID=strategy.vratiAtributID();
		if(relacija1.equals("FotografijeKriminalca")) atributID="OibKriminalac";
		String atributID2=PristupBaziPodataka.provjeriUnos(atribu2, vrijednost, relacija1, atribut1);
		String query=StrategijaUpit.generirajDelete(relacija2, atributID, atribu2, ID, atributID2);
		return PristupBaziPodataka.izvrsiUnos(query);
	}
	
	private boolean dodajUlistu(String ID,String atributID,String vrijednost, String relacija1,String atribut1,String relacija2,String atribu2){
		String id;
		if(relacija1.equals(relacija2)) id=vrijednost;
		else if(relacija1.equals("ListaDogađaja")){
			Dogadaj dogadaj=new Dogadaj();
			String [] parts=vrijednost.split(",");
			String pbrMjesto=parts[0];
			String adresa=parts[1];
			String vrijeme=parts[2];
			String naziv=parts[3];
			dogadaj.setAdresa(adresa);
			dogadaj.setNaziv(naziv);
			dogadaj.setVrijeme(LocalDateTime.parse(vrijeme));
			dogadaj.setPbrMjesto(Integer.parseInt(pbrMjesto));
			dogadaj.setBrojSlucaja(Integer.parseInt(ID));
			return PristupBaziPodataka.dodajNoviDogadaj(dogadaj);
		}
		else{
			System.out.println("tu sam");
			id=PristupBaziPodataka.provjeriUnos(atribu2, vrijednost, relacija1, atribut1);
			if(id.equals("nema")){
				String query=StrategijaUpit.upitUnos(relacija1, atribut1, atribu2, vrijednost, "NULL");
				id=PristupBaziPodataka.izvrsiUpit(query);
			}}
		
		if(relacija2.equals("ListaOružja")) atributID=strategy.vratiAtributID();
		if(relacija1.equals("FotografijeKriminalca")) atributID="OibKriminalac";
		
		List<String> listaAtributa=new LinkedList<>();
		List<String> listaVrijednosti=new LinkedList<>();
		listaAtributa.add(atributID);
		listaAtributa.add(atribu2);
		System.out.println(ID);
		listaVrijednosti.add(ID);
		listaVrijednosti.add(id);
		String query=StrategijaUpit.upitUnos(relacija2, listaAtributa, listaVrijednosti);
		return PristupBaziPodataka.izvrsiUnos(query);
	}
	private Set<E> vratiCon(String upit )throws SQLException{
		return strategy.vratiContext(upit);
	}

	public Set<E> dohvatiSveContexte () throws SQLException{
		String query=strategy.generirajSelectOsnovniPodaci();
		return this.vratiCon(query);
	}
	
	public Map<E, Float>  posaljiUpit(StrategijaUpit<E> con,Integer jedinstveniBrojPolicajca){
		Set<String> listaAtributa=new HashSet<>();
		Map<E, Integer> listaContexta= new LinkedHashMap<>();    // pohranjeni contexti (key) koji odgovaraju za n (value) broja upita  
		Map<E, Float> rezultat=new LinkedHashMap<>();
		Context<E> context=new Context<E>(con);
		
		
		listaAtributa=context.generirajListuAtributaPretrage();	// stvori listu atributa i vrati broj atributa pretrage
		if(listaAtributa.isEmpty()) return null;
		
		String textOpis=this.izgenerirajTekstualniOpis(listaAtributa);
		String upiti="";

		for(String s : listaAtributa){
			String [] temp=s.split("\\*");
			String upit=context.izgenerirajUpit(temp[0],temp[1]);
			upiti+=upit+"; ";
			Set<E> liCon;
			try {
				liCon = this.vratiCon(upit);
				if(liCon!=null){
					for(E cont: liCon){
						listaContexta.merge(cont, 1, (o,n)->o+1);     
					}
				}
			} catch (SQLException e) {                                   
				e.printStackTrace();
			}  

		}		

		PristupBaziPodataka.upisiDnevnikPretrazivanja(textOpis, upiti, jedinstveniBrojPolicajca);

		for (Entry<E, Integer> entry: listaContexta.entrySet()){
			E cont=entry.getKey();
			Integer brojOdgovarajučihAtributa=entry.getValue();
			Float postotakSlaganja=(float) (brojOdgovarajučihAtributa)/(float) listaAtributa.size() *100;
			rezultat.put(cont, postotakSlaganja);
		}

		for (Entry<E, Float> en:rezultat.entrySet()){
			System.out.println("PostotakSlaganja: "+en.getValue()+ "   Context: " + en.getKey());
		}

		return rezultat;
	}
	public boolean dodajNoviDokaz(Dokaz dokaz) {
		PristupBaziPodataka.dodajNoviDokaz(dokaz);
		return true;

	}
	public boolean dodajNoviSlucaj(Slucaj slucaj) {
		PristupBaziPodataka.dodajNoviSlucaj(slucaj);
		return true;
	}
	public boolean dodajNovogKriminalca(Osumnjiceni osumnjicen) {
		return PristupBaziPodataka.dodajNovogKriminalca(osumnjicen);
	}
	public boolean izmjeni(E izmjenjeniContext,Set<String> dodaniAtributi, Set<String> izbrisaniAtributi){
		Boolean uspjeh=true;
		System.out.println("This:"+this.strategy+"\nIzmjena:"+izmjenjeniContext);
		Set<String> atributiSlucaja=this.generirajListuIzmjenjenihAtributa(izmjenjeniContext); 
		System.out.println(atributiSlucaja);
		String ID=strategy.vratiID();
		String atributID=strategy.vratiAtributID();
		String atributID2=strategy.vratiAtributID2();
		
		if(atributiSlucaja.isEmpty()&& dodaniAtributi.isEmpty()&&izbrisaniAtributi.isEmpty()) return false;
		
		if(!atributiSlucaja.isEmpty()) {
			String updateSQL=strategy.generirajUpdateSQL();
			String updateWhere=" WHERE "+atributID+"="+ID;
			uspjeh=uspjeh && PristupBaziPodataka.izmjenaContexta(updateSQL,updateWhere,atributiSlucaja);
		}
		if(!dodaniAtributi.isEmpty()){
			for(String s:dodaniAtributi){
				System.out.println("s_"+s);
				String [] parts=s.split("\\*");
				String vrijednost=parts[0];
				String [] relacijaAtribut1=parts[1].split("\\.");
				String [] relacijaAtribut2=parts[2].split("\\.");
				String relacija1=relacijaAtribut1[0];
				String atribut1=relacijaAtribut1[1];
				String relacija2=relacijaAtribut2[0];
				String atribut2=relacijaAtribut2[1];
				Boolean uspjesno=dodajUlistu(ID,atributID2,vrijednost,relacija1,atribut1,relacija2,atribut2);
				uspjeh=uspjeh && uspjesno;
			}
		}
		if(!izbrisaniAtributi.isEmpty()){
			for(String s:izbrisaniAtributi){
				String [] parts=s.split("\\*");
				String vrijednost=parts[0];
				String [] relacijaAtribut1=parts[1].split("\\.");
				String [] relacijaAtribut2=parts[2].split("\\.");
				String relacija1=relacijaAtribut1[0];
				String atribut1=relacijaAtribut1[1];
				String relacija2=relacijaAtribut2[0];
				String atribut2=relacijaAtribut2[1];
				boolean uspjesno=izbrisiIzListe(ID,atributID2,vrijednost,relacija1,atribut1,relacija2,atribut2);
				uspjeh=uspjeh && uspjesno;
			}
		}
		return uspjeh;
	}

}