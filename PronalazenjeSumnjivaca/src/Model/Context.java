package Model;

import java.util.Map;

public class Context <E> {
	   private Strategija<E> strategy;

	   public Context(Strategija <E> strategy){
	      this.strategy = strategy;
	   }
	   public String izgenerirajUpit(Map<String, String> mapa){
	      return strategy.generirajSQLupit(mapa);
	   }
	   
	}