package Model;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class PristupBaziPodataka {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7150265?useUnicode=yes&characterEncoding=UTF-8";	
	private static final String DB_USER = "sql7150265";
	private static final String DB_PASSWORD = "Xshx7bdSHe";
	private static List<String> vrati=new ArrayList<String>();
	private static Connection dbConnection = null;
	private static Statement statement = null;
	
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
	
	
	
	public static Pozornik prijava (String username, String password) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String query = "SELECT Osoba.imeosobe, Osoba.prezimeosobe, Policajac.razinapristupa "
						+ "FROM Osoba JOIN policajac ON osoba.oib=policajac.osobaoib "
						+ "WHERE Policajac.korisnickoime= ? AND policajac.lozinka= ? ";
		
		try {
			dbConnection = getDBConnection();
			preparedStatement=dbConnection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet rs =preparedStatement.executeQuery();
			
		
			while (rs.next()) {
				switch (rs.getString(3)) {
				case "osnovna":
					Pozornik pozornik = new Pozornik(rs.getString(1),
							rs.getString(2));
					return pozornik;

				case "srednja":
					Pozornik narednik = new Narednik(rs.getString(1),
							rs.getString(2));
					return narednik;
				
				case "visoka":
					Pozornik kapetan = new Kapetan(rs.getString(1),
							rs.getString(2));
					return kapetan;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return null;
	}
	
	public static String posaljiUpit(String upit) {
		return null;
	}

	public static int izmjena(String upit) {
		return 0;
	}

	
	public static List<Dokaz> vratiDokaze (Map<String, String> mapa) throws SQLException {
		//Connection dbConnection = null;
		//Statement statement = null;
		Context<Dokaz> dokazi=new Context<>(new Dokaz());
		ArrayList<String> upit=dokazi.izgenerirajUpit(mapa);
		String query = upit.get(0);
		//System.out.println(query);
		Integer brojDNA=Integer.parseInt(upit.get(1));
		Integer brojKrvnihGrupa=Integer.parseInt(upit.get(2));
		Integer brojOruzja=Integer.parseInt(upit.get(3));
		List<Dokaz> listaDokaza=new LinkedList<>();
	
		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}
			
			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);
			
				while (rs.next()) {
					Dokaz dokaz=new Dokaz();
					dokaz.setID(rs.getInt(1));
					dokaz.setNaziv(rs.getString(2));
					dokaz.setFotografija(rs.getString(3));
					dokaz.setNazivSlucaja(rs.getString(4));
					//System.out.println("novi   "+ dokaz.getID());

					if(!(vratiListuDokaza(dokaz.getID(),"KrvnaGrupa").isEmpty())) {
						dokaz.addAllKrvnaGrupa(vrati);
						vrati.removeAll(vrati);
					}
				
					
					
					if(!(vratiListuDokaza(dokaz.getID(),"DNASekvenca").isEmpty())) {
						dokaz.addAllDNASekvenca(vrati);
						vrati.removeAll(vrati);
					}
					
					if(!(vratiListuDokaza(dokaz.getID(),"OtisakPrsta").isEmpty())) {
						
						for(String s: vrati) 
							dokaz.addOtisakPrsta(s);
						vrati.removeAll(vrati);
						
					}
					
					
					if(!(vratiListuDokaza(dokaz.getID(),"TipOružja").isEmpty())) {
						
						dokaz.addAllTipOruzja(vrati);
						vrati.removeAll(vrati);
					}

					
					
					listaDokaza.add(dokaz);
				}

				
		
			return listaDokaza;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return null;
	}



	private static List<String> vratiListuDokaza(Integer id, String atribut) {
		//Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String query=null;
		List<String> lista=new ArrayList<>();
		if(atribut!="TipOružja" && atribut!="OtisakPrsta") {
			if(atribut=="KrvnaGrupa") {
				lista.add(atribut);
				lista.add("ListaKrvnihGrupaNaDokaznomMaterijalu");
			}
			if(atribut=="DNASekvenca") {
				lista.add(atribut);
				lista.add("ListaDNASekvenciNaDokaznomMaterijalu");
			}
			query = "SELECT " + lista.get(0) + ".naziv" + lista.get(0)
			+ " FROM DokazniMaterijal"
			+ " LEFT JOIN " + lista.get(1) + " ON DokazniMaterijal.brojDokaznogMaterijala = " + lista.get(1) + ".dokazniMaterijalID"
			+ " LEFT JOIN " + lista.get(0) + " ON " + lista.get(1) + "." + lista.get(0) +"ID = " + lista.get(0) + "." +lista.get(0) +"ID"
			+ " WHERE DokazniMaterijal.brojDokaznogMaterijala = ?";
	
		}
		
		else if(atribut=="OtisakPrsta") {
				lista.add(atribut);
				lista.add("ListaOtisakaPrstijuNaDokaznomMaterijalu");
				query = "SELECT " + lista.get(0) + ".fotografijaURL" 
				+ " FROM DokazniMaterijal"
				+ " LEFT JOIN " + lista.get(1) + " ON DokazniMaterijal.brojDokaznogMaterijala = " + lista.get(1) + ".dokazniMaterijalID"
				+ " LEFT JOIN " + lista.get(0) + " ON " + lista.get(1) + "." + lista.get(0) +"ID = " + lista.get(0) + "." +lista.get(0) +"ID"
				+ " WHERE DokazniMaterijal.brojDokaznogMaterijala = ?";
			}
			
		
		else {
			query = "SELECT TipOružja.nazivOružja"
					+ " FROM DokazniMaterijal"
					+ " LEFT JOIN ListaOružja ON DokazniMaterijal.brojDokaznogMaterijala = ListaOružja.brojDokaznogMaterijala"
					+ " LEFT JOIN TipOružja ON ListaOružja.tipOružjaID = TipOružja.tipOružjaID"
					+ " WHERE DokazniMaterijal.brojDokaznogMaterijala = ?";
			
		}
		//System.out.println(query);
		try {
			//dbConnection = getDBConnection();
			preparedStatement=dbConnection.prepareStatement(query);
			preparedStatement.setString(1, id.toString());		
			ResultSet rs =preparedStatement.executeQuery();
			
			while (rs.next()) {
				vrati.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return vrati;
	}



	public String generirajSQLupit(Map<String, String> kombinacija) {
		// TODO Auto-generated method stub
		return null;
	}

}
