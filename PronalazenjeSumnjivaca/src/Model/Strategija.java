package Model;

import java.util.Map;

public interface Strategija <E> {
	public String generirajTextualniOpis(Map<String, String> kombinacija);
	public String generirajSQLupit(Map<String, String> kombinacija);
	//public List<E> posaljiUpit(Map<String, String> mapa);

}
