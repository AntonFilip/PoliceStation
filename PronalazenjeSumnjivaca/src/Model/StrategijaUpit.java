package Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface StrategijaUpit <E> {

	public String generirajTextualniOpis(Set< String> kombinacija);
	public String generirajSQLupit(String vrijednostPretrage,String relacijaAtributDB);
	public Set<String>  generirajListuAtributa();
	public List<E> vratiCon(String vrijednostPretrage,String relacijaAtributDB) throws SQLException;


	public static String generirajWhere(String relacijaAtributDB,String vrijednostPretrage){
		return " WHERE "+relacijaAtributDB+"=\""+vrijednostPretrage+"\"";
	}

	public static String generirajWhere(String relacijaAtributDB,String vrijednostPretrageMin,String vrijednostPretrageMax){
		return " WHERE "+relacijaAtributDB+" between "+vrijednostPretrageMin+" and "+vrijednostPretrageMax;
	}

	public static String generirajWhere(String relacijaAtribut1,String relacijaAtribut2,String vrijednostPretrage1,String vrijednostPretrage2){
		return " WHERE "+relacijaAtribut1+"=\""+vrijednostPretrage1+"\""
				+ "  and "+relacijaAtribut2+"=\""+vrijednostPretrage2+"\"";
	}

	public static  String generirajFrom (String relacija1,String relacijaAtribut1, String relacijaAtribut2){
		return " left join "+relacija1+"  on "+relacijaAtribut1+"="+relacijaAtribut2;
	}

	public static String upitUnos(String relacija,List<String> atribut,List<String> vrijednost) {
		String insert="INSERT INTO " + "`" +relacija+ "`" +"(";
		for(int i=0;i<atribut.size();i++) {
			if(i<atribut.size()-1) 
				insert+="`" + atribut.get(i) + "`" + ",";
			else insert+="`" + atribut.get(i)+"`";
		}
		insert+=") VALUES (";
		for(int i=0;i<vrijednost.size();i++) {
			if(i<vrijednost.size()-1) {
				if(vrijednost.get(i).equals("NULL")) {
					insert+=vrijednost.get(i) + ",";
				}
				else {
					insert+="'"+vrijednost.get(i)+"'" + ",";
				}
			}
			else{
				if(vrijednost.get(i).equals("NULL")) {
					insert+=vrijednost.get(i);
				}
				else {
					insert+="'"+vrijednost.get(i)+"'";
				}
			}
		}
		insert+=")";
		return insert;
	}

	public static String generirajUpdate(String atribut,String vrijednost) {
		return "`"+atribut+"`='"+vrijednost+"'";
	}
}
