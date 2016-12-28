package Model;

import java.util.ArrayList;
import java.util.Map;

public interface StrategijaUpit <E> {
	public String generirajTextualniOpis(Map<String, String> kombinacija);
	public ArrayList<String> generirajSQLupit(Map<String, String> kombinacija);
	public Integer generirajKombinacijeAtributa(E con,Map<String, String> mapa);
	
	//public List<E> posaljiUpit(Map<String, String> mapa);

}
