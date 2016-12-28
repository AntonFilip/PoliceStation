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
		Connection dbConnection = null;
		Statement statement = null;
		Context<Dokaz> dokazi=new Context<>(new Dokaz());
		ArrayList<String> upit=dokazi.izgenerirajUpit(mapa);
		String query = upit.get(0);
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
				int i=5;
				
				if(brojKrvnihGrupa!=0){
					for(int j=brojKrvnihGrupa;j>0;j--){
						dokaz.addKrvnaGrupa(rs.getString(i));
						i++;
					}
				}
				if(brojDNA!=0){
					for(int j=brojDNA;j>0;j--){
						dokaz.addDNASekvenca(rs.getString(i));
						i++;
					}
				}
				if(brojOruzja!=0){
					for(int j=brojOruzja;j>0;j--){
						dokaz.addTipOruzja(rs.getString(i));
						i++;
					}
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

}
