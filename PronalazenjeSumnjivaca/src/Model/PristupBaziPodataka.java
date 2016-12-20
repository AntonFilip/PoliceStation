package Model;

import java.lang.Thread.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;


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

	
	
	
	public static List<Dokaz> vratiDokaze (LinkedHashMap<String, String> kombinacija) throws SQLException {
		List<Dokaz> listaDokaza=new ArrayList<>();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		Context<Dokaz> dokazi=new Context<>(new Dokaz());
		String query = dokazi.izgenerirajUpit(kombinacija);
		System.out.println("***************"+query);
		
		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");
				
			}
			System.out.println(query);
			preparedStatement=dbConnection.prepareStatement(query);
			
			int z=1;
			System.out.println(kombinacija.size());
			for (Entry<String, String> str : kombinacija.entrySet()){
				preparedStatement.setString(z, str.getValue());
				System.out.println(z+str.getValue());
				z++;
				
			}
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(!rs.first()) System.out.println("Nema poklapanja u bazi :( :( :(");
			while (rs.next()) {
				Dokaz dokaz= new Dokaz(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(8), rs.getString(9), rs.getString(7));
				listaDokaza.add(dokaz);
				System.out.println("vide se rez");
			}
			return listaDokaza;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		finally {
			if (preparedStatement != null) {
				System.out.println("zatvor");
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return null;
		
		
		/*try {
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

}
