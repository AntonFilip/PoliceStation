package Model;

import java.util.ArrayList;
import java.util.Map;

public class Context <E> {
	   private StrategijaUpit<E> strategy;

	   public Context(StrategijaUpit <E> strategy){
	      this.strategy = strategy;
	   }
	   public ArrayList<String> izgenerirajUpit(Map<String, String> mapa){
	      return strategy.generirajSQLupit(mapa);
	   }
	   

		public Integer generirajKombinacijeAtributa(E con,Map<String, String> listaAtributa){
			return strategy.generirajKombinacijeAtributa(con, listaAtributa);
		};
	   
	}