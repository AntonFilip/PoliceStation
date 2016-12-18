package Model;

import java.util.Map;
import java.util.Map.Entry;

public class OpisDokaza <E> extends PristupBaziPodataka implements Strategija <E>  {

	
	public String generirajTextualniOpis (Map<String, String> kombinacija) {
		String textOpis="";
		String kar ="";
		
		int j=0;
		for (Entry<String, String> entry: kombinacija.entrySet()){
			if (j==0) kar+= " "+entry.getKey()+"-"+entry.getValue();
			else kar+= ", "+entry.getKey()+"-"+entry.getValue()+" ";
			j++;
		}
		textOpis +=" Traži se dokaz sa sljedećim karakteristikama: "+kar;
		return  textOpis;
	}
	
	
	@Override
	public String generirajSQLupit(Map<String, String> kombinacija){
		String where="";
		String query;
		
		for (Entry<String, String> entry: kombinacija.entrySet()){
			where+= " AND "+entry.getKey()+"="+" ?";	
		}
		query = "SELECT distinct dokaznimaterijal.*, krvnagrupa.nazivKrvnaGrupa, "
				+"				 policijskislučaj.nazivSlučaja, otisakprsta.fotografijaURL, "
				+"				 dnasekvenca.nazivDNASekvenca, tiporužja.nazivOružja "
				+"FROM    dokaznimaterijal" 
				+"		  join listadnasekvencinadokaznommaterijalu" 	   
				+"        join dnasekvenca" 	   
				+"        join listakrvnihgrupanadokaznommaterijalu" 	  
				+"        join krvnagrupa" 	  
				+"        join listaoružja" 	  
				+"        join tiporužja"    
				+"        join policijskislučaj" 	   
				+"        join listaotisakaprstijunadokaznommaterijalu" 	  
				+"        join otisakprsta "     
				+"WHERE   (otisakprsta.otisakPrstaID=listaotisakaprstijunadokaznommaterijalu.otisakPrstaID"
				+" 	    and listaotisakaprstijunadokaznommaterijalu.dokazniMaterijalID=dokaznimaterijal.brojDokaznogMaterijala"
				+"  	and krvnagrupa.krvnaGrupaID=listakrvnihgrupanadokaznommaterijalu.krvnaGrupaID"
				+"   	and listakrvnihgrupanadokaznommaterijalu.dokazniMaterijalID=dokaznimaterijal.brojDokaznogMaterijala"
				+"   	and listadnasekvencinadokaznommaterijalu.dokazniMaterijalID=dokaznimaterijal.brojDokaznogMaterijala"
				+"   	and dnasekvenca.dnaSekvencaID=listadnasekvencinadokaznommaterijalu.dnaSekvencaID"
				+"   	and listaoružja.brojDokaznogMaterijala=dokaznimaterijal.brojDokaznogMaterijala"
				+"   	and tiporužja.tipOružjaID=listaoružja.tipOružjaID "
				+"   	and policijskislučaj.brojSlučaja=dokaznimaterijal.brojSlučaja)";
		 	
		return query+where;
	}


	/*@Override
	public List<Map<Dokaz, Integer>> posaljiUpit(Map<String, String> mapa) {
		String where="";
		String [] polje= new String[kombinacija.size()];
		List<String> vr= new ArrayList<>();
		int i=0;
		
		for (Entry<String, String> entry: kombinacija.entrySet()){
			if (i==0) where+= " "+entry.getKey()+"="+" ?";
			else where+= " AND "+entry.getKey()+"="+" ?";
			polje[i]=entry.getValue();
			i++;
		}
		
		
		String query = 
				
		query=query.toLowerCase()+where;
		System.out.println(query);
		
		System.out.println(textOpis);
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7144607",
					"sql7144607", "PJ87Rlph4r");
			
			PreparedStatement st=con.prepareStatement(query);
			int z=0;
			for (z=0;z<kombinacija.size();z++){
				st.setString(z+1, polje[z]);
				System.out.println(z+1+", " +polje[z]);
			}
			//st.executeQuery();
			//Statement stmt = con.createStatement();
			ResultSet rs = st.executeQuery();
			con.commit();
			System.out.println(st);
			if(rs==null)System.out.println("hehehe");
			while (rs.next()) {
				System.out.println(rs);
			}
			// con.close();

		} catch (Exception e) {
	        if (con != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                con.rollback();
	            } catch(SQLException excep) {
	                System.out.println(excep);
	            }
	        }
			System.out.println(e);
		}
		return null;
	}*/
}
