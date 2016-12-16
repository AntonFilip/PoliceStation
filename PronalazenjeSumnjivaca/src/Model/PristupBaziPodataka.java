package Model;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mysql.jdbc.Driver;

public class PristupBaziPodataka {

	public static String posaljiUpit(String upit) {
		return null;
	}

	public static int izmjena(String upit) {
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
	
	public static List<Dokaz> vratiDokaze (LinkedHashMap<String, String> kombinacija) {
		String where="";
		int i=0;
		for (Entry<String, String> entry: kombinacija.entrySet()){
			if (i==0) where+= " "+entry.getKey()+"="+entry.getValue()+" ";
			else where+= " AND "+entry.getKey()+"="+entry.getValue()+" ";
			i++;
		}
		
		// Ovdje se spaja na server i ide provjera u bazu podataka
		String query = "SELECT DokazniMaterijal.*, KrvnaGrupa.nazivKrvnaGrupa,PolicijskiSluèaj.nazivSluèaja"
				+ "OtisakPrsta.fotografijaURL,DNASekvenca.nazivDNASekvenca,TipOružja.nazivOružja "
				+ "FROM DokazniMaterijal join ListaDNASekvenciNaDokaznomMaterijalu on dokazniMaterijalID=brojDokaznogMaterijala join"
				+ " DNASekvenca on DNASekvenca.dnaSekvencaID=ListaDNASekvenciNaDokaznomMaterijalu.dnaSekvencaID join "
				+ "ListaKrvnihGrupaNaDokaznomMaterijalu on dokazniMaterijalID=brojDokaznogMaterijala join "
				+ "KrvnaGrupa on KrvnaGrupa.krvnaGrupaID=ListaKrvnihGrupaNaDokaznomMaterijalu.krvnaGrupaID join "
				+ "ListaOružja on ListaOružja.brojDokaznogMaterijala=DokazniMaterijal.brojDokaznogMaterijala join "
				+ "TipOružja on TipOružja.tipOružjaID=ListaOružja.tipOružjaID join "
				+ "PolicijskiSluèaj "
				+ "WHERE "+where;
		
		String kar = "";
		int j=0;
		for (Entry<String, String> entry: kombinacija.entrySet()){
			if (j==0) kar+= " "+entry.getKey()+"-"+entry.getValue();
			else kar+= ", "+entry.getKey()+"-"+entry.getValue()+" ";
			i++;
		}
		
		String textOpis =" Traži se dokaz sa sljedeæim karakteristikama: "+kar;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7144607",
					"sql7144607", "PJ87Rlph4r");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		
			while (rs.next()) {
				System.out.println(rs);
			}
			// con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	

}
