package Model;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
	
	public String izgenerirajTekstualniOpis(Set <String> listaAtributa){
		return strategy.generirajTextualniOpis(listaAtributa);
	}
	
	public Set<String> generirajListuAtributa(){
		return strategy.generirajListuAtributa();
	};

	public List<E> vratiCon(String vrijednostPretrage,String relacijaAtribut )throws SQLException{
		return strategy.vratiCon(vrijednostPretrage,relacijaAtribut);
	}

	public Map<E, Float>  posaljiUpit(StrategijaUpit<E> con){
		Set<String> listaAtributa=new HashSet<>();
		Map<E, Integer> listaContexta= new LinkedHashMap<>();    // pohranjeni contexti (key) koji odgovaraju za n (value) broja upita  
		Map<E, Float> rezultat=new LinkedHashMap<>();
		Context<E> context=new Context<E>(con);
		listaAtributa=context.generirajListuAtributa();	// stvori listu atributa i vrati broj atributa pretrage
		
		for(String s : listaAtributa){
			String [] temp=s.split("\\*");
			List<E> liCon;
			try {
				System.out.println("\nAtribut "+s);
				liCon = this.vratiCon(temp[0],temp[1]);
				if(liCon!=null){
					for(E cont: liCon){
						listaContexta.merge(cont, 1, (o,n)->o+1);     
						System.out.println("Context "+cont);
					}
				}
			} catch (SQLException e) {                                   
				e.printStackTrace();
			}  

		}		
	
		for (Entry<E, Integer> entry: listaContexta.entrySet()){
			E cont=entry.getKey();
			Integer brojOdgovarajućihAtributa=entry.getValue();
			Float postotakSlaganja=(float) (brojOdgovarajućihAtributa)/(float) listaAtributa.size() *100;
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
}