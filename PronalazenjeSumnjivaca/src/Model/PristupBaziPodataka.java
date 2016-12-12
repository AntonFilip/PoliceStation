package Model;

import java.sql.*;
import com.mysql.jdbc.Driver;

public class PristupBaziPodataka {

	public static String posaljiUpit(String ime) {
		return null;
	}

	public static int izmjena(String ime) {
		return 0;
	}

	public static Pozornik prijava(String username, String password) {
		// Ovdje se spaja na server i ide provjera u bazu podataka
		String query = "SELECT osoba.imeosobe, osoba.prezimeosobe, policajac.razinapristupa FROM osoba JOIN policajac ON osoba.oib=policajac.osobaoib WHERE "
				+ "policajac.korisnickoime=\""
				+ username
				+ "\" AND policajac.lozinka=\"" + password + "\"";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7144607",
					"sql7144607", "PJ87Rlph4r");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				switch (rs.getString(3)) {
				case "osnovna":
					Pozornik pozornik = new Pozornik(rs.getString(1),
							rs.getString(2));
					con.close();
					return pozornik;
					// break;

				case "srednja":
					Pozornik narednik = new Narednik(rs.getString(1),
							rs.getString(2));
					return narednik;
					// break;

				case "visoka":
					Pozornik kapetan = new Kapetan(rs.getString(1),
							rs.getString(2));
					return kapetan;
					// break;

				}

			}
			// con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

		// if () postoji
		// return new Pozornik();
		// else return null;
	}

}
