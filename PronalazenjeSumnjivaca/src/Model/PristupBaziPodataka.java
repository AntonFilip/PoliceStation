package Model;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

public class PristupBaziPodataka {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7150265?useUnicode=yes&characterEncoding=UTF-8&allowMultiQueries=true";	
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

		String query = "SELECT Osoba.imeOsobe, Osoba.prezimeOsobe, Policajac.razinaPristupa,jedinstveniBrojPolicajca "
				+ "FROM Osoba JOIN Policajac ON Osoba.oib=Policajac.osobaOib "
				+ "WHERE Policajac.korisnickoIme= ? AND Policajac.lozinka= ? ";

		try {
			dbConnection = getDBConnection();
			preparedStatement=dbConnection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet rs =preparedStatement.executeQuery();

			while (rs.next()) {
				switch (rs.getString(3)) {
				case "osnovna":
					Pozornik pozornik = new Pozornik(rs.getString(1),rs.getString(2),rs.getInt(4));
					return pozornik;

				case "srednja":
					Pozornik narednik = new Narednik(rs.getString(1),rs.getString(2),rs.getInt(4));
					return narednik;

				case "visoka":
					Pozornik kapetan = new Kapetan(rs.getString(1),rs.getString(2),rs.getInt(4));
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

	public static List<Slucaj> vratiSlucajeve(String vrijednostPretrage,String relacijaAtributDB,List<String>upiti) throws SQLException {
		Context<Slucaj> slucajevi=new Context<>(new Slucaj());
		String query=slucajevi.izgenerirajUpit(vrijednostPretrage, relacijaAtributDB);
		upiti.add(query+"; ");
		List<Slucaj> listaSlucajeva=new LinkedList<>();
		

		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}

			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				Slucaj slucaj=new Slucaj();
				slucaj.setBrojSlucaja(rs.getInt(1));
				slucaj.setNazivSlucaja(rs.getString(2));
				String status=rs.getString(3);
				switch (status) {
				case "riješen": slucaj.setStatus(TrenutniStatusSlucaja.riješen);break;
				case "zatvoren ali neriješen": slucaj.setStatus(TrenutniStatusSlucaja.zatvoren);break;
				case "otvoren":slucaj.setStatus(TrenutniStatusSlucaja.otvoren); break;
				}
				Osumnjiceni osumnjiceni=new Osumnjiceni();
				osumnjiceni.setOib(rs.getLong(4));
				slucaj.setGlavniOsumnjiceni(osumnjiceni);
				slucaj.setOpis(rs.getString(5));
				listaSlucajeva.add(slucaj);
			}

			return listaSlucajeva;
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
	
	public static List<Dokaz> vratiDokaze (String vrijednostPretrage,String relacijaAtributDB,List<String>upiti) throws SQLException {
		Context<Dokaz> dokazi=new Context<>(new Dokaz());
		String query=dokazi.izgenerirajUpit(vrijednostPretrage,relacijaAtributDB);
		upiti.add(query+"; ");
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
	
	public static List<Osumnjiceni> vratiOsumnjicene (String vrijednostPretrage,String relacijaAtributDB,List<String>upiti) throws SQLException {
		Context<Osumnjiceni> osumnjiceni=new Context<>(new Osumnjiceni());
		String query=osumnjiceni.izgenerirajUpit(vrijednostPretrage,relacijaAtributDB);
		upiti.add(query+"; ");
		List<Osumnjiceni> listaOsumnjicenih=new LinkedList<>();

		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}

			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				Osumnjiceni osumnjiceni2=new Osumnjiceni();
				osumnjiceni2.setOib(rs.getLong(1));
				osumnjiceni2.setIme(rs.getString(2));
				osumnjiceni2.setPrezime(rs.getString(3));
				String status=rs.getString(4);
				switch (status) {
				case "na slobodi": osumnjiceni2.setStatus(TrenutniStatusKriminalca.sloboda);break;
				case "u zatvoru": osumnjiceni2.setStatus(TrenutniStatusKriminalca.u_zatvoru);break;
				case "u pritvoru":osumnjiceni2.setStatus(TrenutniStatusKriminalca.u_pritvoru); break;
				}
				
				listaOsumnjicenih.add(osumnjiceni2);
			}
			return listaOsumnjicenih;
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

	public static Dokaz dohvatiPodatkeDokaz(String id) throws SQLException{
		String query=" select brojDokaznogMaterijala, nazivDokaznogMaterijala,nazivSlučaja, fotografijaDokaznogMaterijalaURL from DokazniMaterijal join PolicijskiSlučaj on PolicijskiSlučaj.brojSlučaja=DokazniMaterijal.brojSlučaja where brojDokaznogMaterijala=\""+id+"\"";
		Dokaz dokaz=new Dokaz();

		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}

			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);

			
				while (rs.next()) {
					dokaz.setID(rs.getInt(1));
					dokaz.setNaziv(rs.getString(2));
					dokaz.setFotografija(rs.getString(4));
					dokaz.setNazivSlucaja(rs.getString(3));

					if(!(vratiListu(Long.parseLong(id),"KrvnaGrupa.nazivKrvnaGrupa","DokazniMaterijal","ListaKrvnihGrupaNaDokaznomMaterijalu","ListaKrvnihGrupaNaDokaznomMaterijalu.dokazniMaterijalID","DokazniMaterijal.brojDokaznogMaterijala","KrvnaGrupa","KrvnaGrupa.krvnaGrupaID" , "ListaKrvnihGrupaNaDokaznomMaterijalu.krvnaGrupaID").isEmpty())) {
						dokaz.addAllKrvnaGrupa(vrati);
						vrati.removeAll(vrati);
					}
				
					
					
					if(!(vratiListu(Long.parseLong(id),"DNASekvenca.nazivDNASekvenca","DokazniMaterijal","ListaDNASekvenciNaDokaznomMaterijalu", "ListaDNASekvenciNaDokaznomMaterijalu.dokazniMaterijalID","DokazniMaterijal.brojDokaznogMaterijala","DNASekvenca", "DNASekvenca.dnaSekvencaID", "ListaDNASekvenciNaDokaznomMaterijalu.dnaSekvencaID").isEmpty())) {
						dokaz.addAllDNASekvenca(vrati);
						vrati.removeAll(vrati);
					}
					
					
					
					
					if(!(vratiListu(Long.parseLong(id),"TipOružja.nazivOružja", "DokazniMaterijal","ListaOružja", "ListaOružja.brojDokaznogMaterijala", "DokazniMaterijal.brojDokaznogMaterijala","TipOružja", "TipOružja.tipOružjaID", "ListaOružja.tipOružjaID").isEmpty())) {
						dokaz.addAllTipOruzja(vrati);
						vrati.removeAll(vrati);
					}


					
					System.out.println(dokaz);
					return dokaz;
				}

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
	
	public static Slucaj dohvatiPodatkeSlucaj(String id) throws SQLException {
		String query=" select * from PolicijskiSlučaj where brojSlučaja ='"+id+"'";
		Slucaj slucaj=new Slucaj();

		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}

			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);

			
				while (rs.next()) {
					slucaj.setBrojSlucaja(rs.getInt(1));
					slucaj.setNazivSlucaja(rs.getString(2));
					String status=rs.getString(4);
					switch (status) {
					case "riješen": slucaj.setStatus(TrenutniStatusSlucaja.riješen);break;
					case "zatvoren ali neriješen": slucaj.setStatus(TrenutniStatusSlucaja.zatvoren);break;
					case "otvoren":slucaj.setStatus(TrenutniStatusSlucaja.otvoren); break;
					}
					Osumnjiceni osumnjiceni=new Osumnjiceni();
					if(rs.getLong(5)!=0) {
					osumnjiceni.setOib(rs.getLong(5));
					
					slucaj.setGlavniOsumnjiceni(osumnjiceni);
					}
					slucaj.setOpis(rs.getString(3));
					

					if(!(vratiListu(Long.parseLong(id),"Policajac.jedinstveniBrojPolicajca","PolicijskiSlučaj","PolicajciDodijeljeniSlučaju","PolicajciDodijeljeniSlučaju.brojSlučaja","PolicijskiSlučaj.brojSlučaja","Policajac","Policajac.jedinstveniBrojPolicajca" , "PolicajciDodijeljeniSlučaju.jedinstveniBrojPolicajca").isEmpty())) {
						for(String s:vrati) {
							Pozornik pozornik=new Pozornik();
							Integer broj=Integer.parseInt(s);
							pozornik.setJedinstveniBroj(broj);
							slucaj.addPolicajac(pozornik);
						}
						vrati.removeAll(vrati);
					}
				
					
					
					if(!(vratiListu(Long.parseLong(id),"Osoba.oib","PolicijskiSlučaj","ListaOsumnjicenihOsoba", "ListaOsumnjicenihOsoba.brojSlučaja","PolicijskiSlučaj.brojSlučaja","Osoba", "Osoba.oib", "ListaOsumnjicenihOsoba.osobaOib").isEmpty())) {
						for(String s:vrati) {
							if(!s.equals(null)) {
							Osoba osoba=new Osoba();
							long broj=Long.parseLong(s);
							osoba.setOib(broj);
							slucaj.addOsumnjiceni(osoba);
						}
						}
						vrati.removeAll(vrati);
						
					}
					
					
					
					if(!(vratiListu(Long.parseLong(id),"Osoba.oib","PolicijskiSlučaj","ListaSvjedoka", "ListaSvjedoka.brojSlučaja","PolicijskiSlučaj.brojSlučaja","Osoba", "Osoba.oib", "ListaSvjedoka.osobaOib").isEmpty())) {
						for(String s:vrati) {
							if(!s.equals(null)) {
							Osoba osoba=new Osoba();
							osoba.setOib(Long.parseLong(s));
							slucaj.addSvjedok(osoba);
							}
						}
						vrati.removeAll(vrati);
					}
						
					String select="SELECT `fotografijaURL` FROM `FotografijaPolicijskogSlučaja` WHERE `brojSlučaja`='"+id+"'";
					try {
							statement=dbConnection.prepareStatement(select);
							ResultSet r=statement.executeQuery(select);
						while (r.next()) {
							slucaj.addFotografija(r.getString(1));
						}
						vrati.removeAll(vrati);
					
					}
					catch (Exception e) {
						System.out.println(e);
						e.printStackTrace();
					}
			
					
					System.out.println(slucaj);
					return slucaj;
				}
				
		}
		
					
		 catch (Exception e) {
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
	
	public static Osumnjiceni dohvatiPodatkeOsumnjiceni(String oib) {
		String query="select * from Kriminalac where oib='"+oib+"'";
		Osumnjiceni osumnjiceni=new Osumnjiceni();
		FizickeOsobine fOsobina=new FizickeOsobine();
		KarakterneOsobine kOsobine=new KarakterneOsobine();
		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}

			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				osumnjiceni.setOib(rs.getLong(1));
				
				String status=rs.getString(3);
				switch (status) {
				case "na slobodi": osumnjiceni.setStatus(TrenutniStatusKriminalca.sloboda);break;
				case "u pritvoru": osumnjiceni.setStatus(TrenutniStatusKriminalca.u_pritvoru);break;
				case "u zatvoru": osumnjiceni.setStatus(TrenutniStatusKriminalca.u_zatvoru); break;
				}
				
				osumnjiceni.setBrojTelefona(rs.getString(6));
				osumnjiceni.setOpisKriminalnihDjelatnosti(rs.getString(17));
				fOsobina.setBojaKose(rs.getString(10));
				fOsobina.setBojaOciju(rs.getString(13));
				try {
					String upit="SELECT (YEAR( CURRENT_TIMESTAMP )) - YEAR( datumRođenja ) AS godine FROM Kriminalac WHERE oib ='"+oib+"'";
					System.out.println(upit);
					statement=dbConnection.prepareStatement(upit);
					ResultSet r=statement.executeQuery(upit);
					while(r.next()) {
						fOsobina.setGodine(r.getInt(1));
					}
				} catch(Exception e) {
					System.out.println(e);
					e.printStackTrace();
				}
				String gradaTijela=rs.getString(14);
				switch (gradaTijela) {
				case "slabija": fOsobina.setGradaTijela(GradaTijela.slabija);;break;
				case "srednja": fOsobina.setGradaTijela(GradaTijela.srednja);;break;
				case "ja?a": fOsobina.setGradaTijela(GradaTijela.jaca);; break;
				}
				fOsobina.setOblikFrizure(rs.getString(12));
				fOsobina.setOblikGlave(rs.getString(11));
				fOsobina.setRasa(rs.getString(7));
				String spol=rs.getString(5);
				switch (spol) {
				case "M": fOsobina.setSpol(Spol.M);;break;
				case "Ž": fOsobina.setSpol(Spol.Ž);;break;
				}
				fOsobina.setTezina(rs.getFloat(9));
				fOsobina.setVisina(rs.getFloat(8));
				kOsobine.setNacinGovora(rs.getString(16));
				String razinaInt=rs.getString(15);
				switch (razinaInt) {
				case "visoka": kOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.visoka);break;
				case "srednja": kOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.srednja);break;
				case "niska": kOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.niska); break;
				}
				if(!(vratiListu(Long.parseLong(oib),"Bolest.nazivBolesti","Kriminalac","ListaBolestiKriminalca","ListaBolestiKriminalca.kriminalacOib","Kriminalac.oib","Bolest","Bolest.bolestID" , "ListaBolestiKriminalca.bolestID").isEmpty())) {
					Set<String> bolesti=new HashSet<>();
					for(String s:vrati) {
						bolesti.add(s);
						
					}
					fOsobina.setBolesti(bolesti);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"Tetovaža.opisTetovaže","Kriminalac","TetovažeKriminalca","TetovažeKriminalca.kriminalacOib","Kriminalac.oib","Tetovaža","Tetovaža.tetovažaID" , "TetovažeKriminalca.tetovažaID").isEmpty())) {
					Set<String> tetovaže=new HashSet<>();
					for(String s:vrati) {
						tetovaže.add(s);
						
					}
					fOsobina.setTetovaze(tetovaže);;
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"FizičkiNedostatak.fizičkiNedostatakOpis","Kriminalac","FizičkiNedostaciKriminalca","FizičkiNedostaciKriminalca.kriminalacOib","Kriminalac.oib","FizičkiNedostatak","FizičkiNedostatak.fizičkiNedostatakID" , "FizičkiNedostaciKriminalca.fizičkiNedostatakID").isEmpty())) {
					Set<String> fiznedostaci=new HashSet<>();
					for(String s:vrati) {
						fiznedostaci.add(s);
						
					}
					fOsobina.setFizickiNedostatci(fiznedostaci);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"KarakternaOsobina.karakternaOsobinaOpis","Kriminalac","OstaleKarakterneOsobineKriminalca","OstaleKarakterneOsobineKriminalca.kriminalacOib","Kriminalac.oib","KarakternaOsobina","KarakternaOsobina.karakternaOsobinaID" , "OstaleKarakterneOsobineKriminalca.karakternaOsobinaID").isEmpty())) {
					Set<String> karOSobine=new HashSet<>();
					for(String s:vrati) {
						karOSobine.add(s);
						
					}
					kOsobine.setOstaleKarakterneOsobine(karOSobine);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"PsihološkiProblem.psihološkiProblemOpis","Kriminalac","PsihološkiProblemiKriminalca","PsihološkiProblemiKriminalca.kriminalacOib","Kriminalac.oib","PsihološkiProblem","PsihološkiProblem.psihološkiProblemID" , "PsihološkiProblemiKriminalca.psihološkiProblemID").isEmpty())) {
					Set<String> psihProb=new HashSet<>();
					for(String s:vrati) {
						psihProb.add(s);
						
					}
					kOsobine.setPsiholoskiProblemi(psihProb);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"FizičkaOsobina.fizičkaOsobinaOpis","Kriminalac","OstaleFizičkeOsobineKriminalca","OstaleFizičkeOsobineKriminalca.kriminalacOib","Kriminalac.oib","FizičkaOsobina","FizičkaOsobina.fizičkaOsobina" , "OstaleFizičkeOsobineKriminalca.fizičkaOsobinaID").isEmpty())) {
					Set<String> fizOs=new HashSet<>();
					for(String s:vrati) {
						fizOs.add(s);
						
					}
					fOsobina.setOstaleFizickeOsobine(fizOs);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "fotografijaURL", "OtisakPrsta", "Kriminalac", "OtisakPrsta.otisakPrstaID", "Kriminalac.otisakPrstaID","Kriminalac.oib").isEmpty())) {
	
					osumnjiceni.setOtisakPrstaURL(vrati.get(0));
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "FotografijaURL", "FotografijeKriminalca", "Kriminalac", "FotografijeKriminalca.OibKriminalac", "Kriminalac.oib","Kriminalac.oib").isEmpty())) {
					Set<String> foto=new HashSet<>();
					for(String s:vrati) {
						foto.add(s);
						
					}
					osumnjiceni.setFotografijeURL(foto);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "FotografijaURL", "FotografijeKriminalca", "Kriminalac", "FotografijeKriminalca.OibKriminalac", "Kriminalac.oib","Kriminalac.oib").isEmpty())) {
					Set<String> foto=new HashSet<>();
					for(String s:vrati) {
						foto.add(s);
						
					}
					osumnjiceni.setFotografijeURL(foto);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "nazivSlučaja", "ListaOsumnjicenihOsoba", "PolicijskiSlučaj", "ListaOsumnjicenihOsoba.brojSlučaja", "PolicijskiSlučaj.brojSlučaja","ListaOsumnjicenihOsoba.osobaOib").isEmpty())) {
					Set<Slucaj> slucaji=new HashSet<>();
					Slucaj slucaj=new Slucaj();
					for(String s:vrati) {
						slucaj.setNazivSlucaja(s);
						slucaji.add(slucaj);
					}
					osumnjiceni.setPovezaniSlucajevi(slucaji);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "adresaStanovanja", "PoznateAdreseStanovanjaKriminalca", "Kriminalac", "PoznateAdreseStanovanjaKriminalca.kriminalacOib", "Kriminalac.oib","Kriminalac.oib").isEmpty())) {
					Set<AdresaIMjestoStanovanja> adrese=new HashSet<>();
					AdresaIMjestoStanovanja adr=new AdresaIMjestoStanovanja();
					
					for(String s:vrati) {
						adr.setAdresa(s);
						adrese.add(adr);
					}
					vrati.removeAll(vrati);
					vratiListu(Long.parseLong(oib), "pbrMjesto", "PoznateAdreseStanovanjaKriminalca", "Kriminalac", "PoznateAdreseStanovanjaKriminalca.kriminalacOib", "Kriminalac.oib","Kriminalac.oib");
					for(String s:vrati) {
						adr.setPbrMjesto(Integer.parseInt(s));
						String select="select nazivMjesto from Mjesto where pbrMjesto='"+s+"'";
						try {
							statement=dbConnection.prepareStatement(select);
							ResultSet r = statement.executeQuery(select);
							while(r.next()) {
								adr.setNazivMjesta(r.getString(1));
							}
						}
						catch (Exception ex) {
							System.out.println(ex);
						}
						adrese.add(adr);
					}
					osumnjiceni.setPoznateAdrese(adrese);
					vrati.removeAll(vrati);
				}
				
				if(!(vratiListu(Long.parseLong(oib), "povezanSaKriminalacOib", "ListaPovezanihKriminalaca", "Kriminalac", "ListaPovezanihKriminalaca.kriminalacOib", "Kriminalac.oib","Kriminalac.oib").isEmpty())) {
					Set<Osumnjiceni> krimi=new HashSet<>();
					Osumnjiceni osumnjiceni2=new Osumnjiceni();
					for(String s:vrati) {
						osumnjiceni2.setOib(Long.parseLong(s));
						krimi.add(osumnjiceni2);
					}
					osumnjiceni.setPopisPovezanihKriminalaca(krimi);
					vrati.removeAll(vrati);
				}
				osumnjiceni.setKarakterneOsobine(kOsobine);
			
				osumnjiceni.setFizickeOsobine(fOsobina);
				System.out.println(fOsobina);
				
			}

			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(osumnjiceni);
		return osumnjiceni;
	}
	private static List<String> vratiListu(Long id,String selecta,String froma,String join,String atr1,String atr2,String wherea) {
		PreparedStatement preparedStatement = null;
		String select = "";
		String from = "";
		String where = "";
		String query;
		where+=StrategijaUpit.generirajWhere(wherea, id.toString());
		select+="Select "+selecta+" FROM "+froma+" LEFT JOIN "+join+" on "+atr1+"="+atr2;
		query=select+from+where;
		System.out.println(query);
		try {
			preparedStatement=dbConnection.prepareStatement(query);	
			ResultSet rs =preparedStatement.executeQuery();

			while (rs.next()) {
				vrati.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return vrati;
	}
		
	private static List<String> vratiListu(Long id, String selecta,String froma,String join1,String relatr1,String relatr2,String join2,String relatr3,String relatr4) {
		PreparedStatement preparedStatement = null;
		String select = "";
		String from = "";
		String where = "";
		String query;
		where+=StrategijaUpit.generirajWhere(relatr2, id.toString());
		select+="Select "+selecta+" FROM "+froma+" LEFT JOIN "+join1+" on "+relatr1+"="+relatr2+" LEFT JOIN "+join2+" on "+relatr3+"="+relatr4;
		query=select+from+where;

		System.out.println(query);
		try {
			preparedStatement=dbConnection.prepareStatement(query);	
			ResultSet rs =preparedStatement.executeQuery();

			while (rs.next()) {
				vrati.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return vrati;
	}
	
	public static void dodajNoviDokaz(Dokaz dokaz) {
		ArrayList<String> atr=new ArrayList<>();
		ArrayList<String> vrij=new ArrayList<>();
		String query="";
		String naziv=dokaz.getNaziv();
		String brSlucaja=dokaz.getBrojSlucaja().toString();
		String foto=dokaz.getFotografija();
		Integer brDokaza;
		Set<String> krvnaGrupa=dokaz.getKrvnaGrupa();
		Set<String> dnaSekvenca=dokaz.getDNASekvenca();
		Set<String> tipOružja=dokaz.getTipOruzja();
		Set<String> otisakPrsta=dokaz.getOtisakPrsta();

		atr.add("brojDokaznogMaterijala");
		atr.add("nazivDokaznogMaterijala");
		atr.add("brojSlučaja");
		atr.add("fotografijaDokaznogMaterijalaURL");
		vrij.add("NULL");
		vrij.add(naziv);
		vrij.add(brSlucaja);
		vrij.add(foto);
		query=StrategijaUpit.upitUnos("DokazniMaterijal", atr, vrij);
		System.out.println(query);

		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}
			statement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  
			statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);  
			ResultSet keys = statement.getGeneratedKeys();    
			keys.next();  
			brDokaza = keys.getInt(1);
			if(!krvnaGrupa.isEmpty()) {
				upišiUBazu("krvnaGrupa",krvnaGrupa,brDokaza);
				System.out.println("tu san");
				
				}
			if(!dnaSekvenca.isEmpty()) {
				upišiUBazu("dnaSekvenca", dnaSekvenca, brDokaza);
				System.out.println("tu san");
			}
			if(!otisakPrsta.isEmpty()) {
				upišiUBazu("otisakPrsta", otisakPrsta, brDokaza);
				System.out.println("tu san");
			}
			if(!tipOružja.isEmpty()) {
				upišiUBazu("tipOružja", tipOružja, brDokaza);
				System.out.println("tu san");
			}
			
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}


		
		
	}

	private static void upišiUBazu(String ime,Set<String> atribut,Integer brDokaza) {
		String pom="";
		Integer ID;
		String query;
		String relacija="";
		String i="";
		List<String> atr=new ArrayList<>();
		List<String> vrij=new ArrayList<>();
		System.out.println(atribut.toString());
		for(String s:atribut) {
			switch (ime) {
			case "krvnaGrupa":
				pom="SELECT krvnaGrupaID "
						+ "FROM KrvnaGrupa "
						+ "WHERE nazivKrvnaGrupa = \""+s+"\"";
				relacija="ListaKrvnihGrupaNaDokaznomMaterijalu";
				atr.add("dokazniMaterijalID");

				break;
			case "dnaSekvenca":
				pom="SELECT dnaSekvencaID "
						+ "FROM DNASekvenca "
						+ "WHERE nazivDNASekvenca = \""+s+"\"";
				relacija="ListaDNASekvenciNaDokaznomMaterijalu";
				atr.add("dokazniMaterijalID");
				System.out.println("tu san");
				break;
			case "tipOružja":
				pom="SELECT tipOružjaID "
						+ "FROM TipOružja "
						+ "WHERE nazivOružja = \""+s+"\"";
				relacija="ListaOružja";
				atr.add("brojDokaznogMaterijala");
				break;
			case "otisakPrsta":
				pom="SELECT otisakPrstaID "
						+ "FROM OtisakPrsta "
						+ "WHERE fotografijaURL = \""+s+"\"";
				relacija="ListaOtisakaPrstijuNaDokaznomMaterijalu";
				atr.add("dokazniMaterijalID");
				break;
			default:
				break;
				
			}
			i=ime+"ID";
			try {
				System.out.println(pom);
			statement=dbConnection.prepareStatement(pom);
			ResultSet res=statement.executeQuery(pom);
			if(res.next()) {
			ID=res.getInt(1);
			
			atr.add(i);
			vrij.add(brDokaza.toString());
			vrij.add(ID.toString());
			query=StrategijaUpit.upitUnos(relacija, atr, vrij);
			try {
				System.out.println(query);
				statement=dbConnection.prepareStatement(query);
				statement.executeUpdate(query);
			}
		 catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
			}
			else {
				System.out.println("alooo");
				List<String> atributi=new ArrayList<>();
				List<String> vrijednosti=new ArrayList<>();
				String upit="";
				switch (ime) {
				
				case "tipOružja": //ako nema tog tipa oružja dodaj
					atributi.add(ime+"ID");
					atributi.add("nazivOružja");
					vrijednosti.add("NULL");
					vrijednosti.add(s);
					upit=StrategijaUpit.upitUnos("TipOružja", atributi, vrijednosti);
					try {
						statement = dbConnection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);  
						statement.executeUpdate(upit,Statement.RETURN_GENERATED_KEYS);  
						ResultSet keys = statement.getGeneratedKeys();    
						keys.next(); 
						String brtip=keys.getString(1);
						atr.add(ime+"ID");
						vrij.add(brDokaza.toString());
						vrij.add(brtip);
						upit=StrategijaUpit.upitUnos("ListaOružja", atr, vrij);
						try {
							System.out.println(upit);
							statement=dbConnection.prepareStatement(upit);
							statement.executeUpdate(upit);
						}
					 catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
						
					}
				 catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
					
					break;
				case "dnaSekvenca" :
					atributi.add(ime+"ID");
					atributi.add("nazivDNASekvenca");
					vrijednosti.add("NULL");
					vrijednosti.add(s);
					System.out.println("alooo");
					upit=StrategijaUpit.upitUnos("DNASekvenca", atributi, vrijednosti);
					try {
						statement = dbConnection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);  
						statement.executeUpdate(upit,Statement.RETURN_GENERATED_KEYS);  
						ResultSet keys = statement.getGeneratedKeys();    
						keys.next(); 
						String brtip=keys.getString(1);
						atr.removeAll(atr);
						atr.add(ime+"ID");
						atr.add("dokazniMaterijalID");
						vrij.add(brDokaza.toString());
						vrij.add(brtip);
						upit=StrategijaUpit.upitUnos("ListaDNASekvenciNaDokaznomMaterijalu", atr, vrij);
						try {
							System.out.println(upit);
							statement=dbConnection.prepareStatement(upit);
							statement.executeUpdate(upit);
						}
					 catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
						
					}
				 catch (Exception ex) {
					System.out.println(ex.getMessage());
				}


				
				
				case "otisakPrsta" :
					atributi.add(ime+"ID");
					atributi.add("fotografijaURL");
					vrijednosti.add("NULL");
					vrijednosti.add(s);
					upit=StrategijaUpit.upitUnos("OtisakPrsta", atributi, vrijednosti);
					try {
						statement = dbConnection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);  
						statement.executeUpdate(upit,Statement.RETURN_GENERATED_KEYS);  
						ResultSet keys = statement.getGeneratedKeys();    
						keys.next(); 
						String brtip=keys.getString(1);
						atr.add(ime+"ID");
						vrij.add(brDokaza.toString());
						vrij.add(brtip);
						upit=StrategijaUpit.upitUnos("ListaOtisakaPrstijuNaDokaznomMaterijalu", atr, vrij);
						try {
							System.out.println(upit);
							statement=dbConnection.prepareStatement(upit);
							statement.executeUpdate(upit);
						}
					 catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
						
					}
				 catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				
				

				default:
					break;
				}
			}
			
			}
		 catch (Exception e) {
				System.out.println(e.getMessage());
			}
			atr.removeAll(atr);
			vrij.removeAll(vrij);
			}
		
	}


	public static void dodajNoviSlucaj(Slucaj slucaj) {
		String brojSlucaja;
		List<String> atr=new ArrayList<>();
		List<String> vrij=new ArrayList<>();
		String query="";
		atr.add("brojSlučaja");
		atr.add("nazivSlučaja");
		atr.add("opis");
		atr.add("trenutniStatus");
		atr.add("glavnaOsumljicenaOsobaOib");
		vrij.add("NULL");
		vrij.add(slucaj.getNazivSlucaja());
		vrij.add(slucaj.getOpis());
		vrij.add(slucaj.getStatus().toString());
		vrij.add(slucaj.getGlavniOsumnjiceni().getOib().toString());
		query=StrategijaUpit.upitUnos("PolicijskiSlučaj", atr, vrij);
		System.out.println(query);
		Set<String> popisOsumnjicenih = new HashSet<String>();
		for(Osoba o:slucaj.getPopisOsumnjicenih()) {
			popisOsumnjicenih.add(o.getOib().toString());
		}
		Set<String> popisSvjedoka = new HashSet<String>();
		for(Osoba o:slucaj.getPopisSvjedoka()) {
			popisSvjedoka.add(o.getOib().toString());
		}
		Set<Dokaz> popisDokaza = new HashSet<Dokaz>(); //??? treba li onda dodavanje dokaza?
		Set<String> popisPolicajaca = new HashSet<String>();
		for(Osoba o:slucaj.getPopisPolicajaca()) {
			popisPolicajaca.add(o.getOib().toString());
		}
		Set<String> fotografijeSlučaja=slucaj.getFotografijeSlučaja();
		Set<Dogadaj> popisDogadaja= new HashSet<>();
		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}
			statement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  
			statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);  
			ResultSet keys = statement.getGeneratedKeys();    
			keys.next();  
			brojSlucaja=keys.getString(1);
			atr.removeAll(atr);
			vrij.removeAll(vrij);
			if(!popisOsumnjicenih.isEmpty()) {
				atr.removeAll(atr);
				atr.add("osobaOib");
				atr.add("brojSlučaja");
				for(String s:popisOsumnjicenih) {
					vrij.add(s);
					vrij.add(brojSlucaja);
					query=StrategijaUpit.upitUnos("ListaOsumnjicenihOsoba", atr, vrij);
					try {
					System.out.println(query);
					statement=dbConnection.prepareStatement(query);
					statement.executeUpdate(query);
					}
					catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					vrij.removeAll(vrij);
				}
			}
			if(!popisPolicajaca.isEmpty()) {
				atr.removeAll(atr);
				atr.add("brojSlučaja");
				atr.add("jedinstveniBrojPolicajca");
				for(String s:popisPolicajaca) {// s=OIB
					String select="SELECT `jedinstveniBrojPolicajca` FROM Policajac WHERE `osobaOib`="+s;
					System.out.println(select);
					try {
						statement=dbConnection.prepareStatement(select);
						ResultSet res=statement.executeQuery(select); 
						if(res.next()) {
						String br=res.getString(1);
						
						System.out.println(br);
					vrij.add(brojSlucaja);
					vrij.add(br);
					query=StrategijaUpit.upitUnos("PolicajciDodijeljeniSlučaju", atr, vrij);
					try {
					System.out.println(query);
					statement=dbConnection.prepareStatement(query);
					statement.executeUpdate(query);
					}
					catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					}
					}	catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				vrij.removeAll(vrij);
					
					}
			}
			if(!popisSvjedoka.isEmpty()) {
				atr.removeAll(atr);
				atr.add("osobaOib");
				atr.add("brojSlučaja");
				for(String s:popisSvjedoka) {
					vrij.add(s);
					vrij.add(brojSlucaja);
				query=StrategijaUpit.upitUnos("ListaSvjedoka", atr, vrij);
				try {
					System.out.println(query);
					statement=dbConnection.prepareStatement(query);
					statement.executeUpdate(query);
				}
			 catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
				vrij.removeAll(vrij);
			}
			}
			if(!fotografijeSlučaja.isEmpty()) {
				atr.removeAll(atr);
				atr.add("fotografijaID");
				atr.add("fotografijaURL");
				atr.add("brojSlučaja");
				for(String s:fotografijeSlučaja) {
					vrij.add("NULL");
					vrij.add(s);
					vrij.add(brojSlucaja);
				query=StrategijaUpit.upitUnos("FotografijaPolicijskogSlučaja", atr, vrij);
					try {
					System.out.println(query);
					statement=dbConnection.prepareStatement(query);
					statement.executeUpdate(query);
					}
					catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				vrij.removeAll(vrij);
				}
			}
			
			
			
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

			}

	public static boolean izmjenaSlucaja(Slucaj slucaj) throws SQLException {
		String upit="";
		boolean zarez =false;
		upit="UPDATE `PolicijskiSlučaj` SET ";
		if(slucaj.getNazivSlucaja()!=null) {
			upit+=StrategijaUpit.generirajUpdate("nazivSlučaja",slucaj.getNazivSlucaja());
			zarez=true;
		}
		if(slucaj.getOpis()!=null) {
			if(zarez) upit+=",";
			else zarez=true;
			upit+=StrategijaUpit.generirajUpdate("opis",slucaj.getOpis());
		}
		if(slucaj.getStatus()!=null) {
			if(zarez) upit+=",";
			else zarez=true;
			upit+=StrategijaUpit.generirajUpdate("trenutniStatus",slucaj.getStatus().toString());
		}	
		if(slucaj.getGlavniOsumnjiceni()!=null) {
			if(zarez) upit+=",";
			else zarez=true;
			upit+=StrategijaUpit.generirajUpdate("glavnaOsumljicenaOsobaOib",slucaj.getGlavniOsumnjiceni().getOib().toString());
		}
		upit+=" WHERE `brojSlučaja`= '" + slucaj.getNazivSlucaja()+"'";
		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}
			System.out.println(upit);
			statement=dbConnection.prepareStatement(upit);
			statement.executeUpdate(upit);
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		upit="SELECT  `brojSlučaja` FROM  `PolicijskiSlučaj` "
				+ "WHERE  `nazivSlučaja` =  '"+slucaj.getNazivSlucaja()+"'";
		try {
			statement=dbConnection.prepareStatement(upit);
			ResultSet res=statement.executeQuery(upit); 
			if(res.next()) {
			String br=res.getString(1);
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
		return true;
		
	}

	public static boolean upisiDnevnikPretrazivanja (String textUpita,String sqlUpit,Integer jedinstveniBrojPolicajca){
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String query= "SET NAMES utf8mb4; INSERT INTO DnevnikPretraživanja (ipAdresa,tekstUpita,tekstUpitaSQLoblik,vrijemeUpita,jedinstveniBrojPolicajca) VALUES (?,?,?,?,?);";
		InetAddress IP;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		String ipAdresa=IP.getHostAddress();
		System.out.println(sqlUpit);
		try {
			dbConnection = getDBConnection();
			preparedStatement=dbConnection.prepareStatement(query);
			preparedStatement.setString(1, ipAdresa);
			preparedStatement.setString(2,textUpita);
			preparedStatement.setString(3, sqlUpit);
			preparedStatement.setTimestamp(4,java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			preparedStatement.setInt(5, jedinstveniBrojPolicajca);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public static Statistika izracunajStatistiku (){
	Connection dbConnection = null;
		Statement statement = null;
		Statement statement2=null;
		Statement statement3=null;
		Statement statement4=null;
		
		Statistika statistika=new Statistika();
		String query1="SELECT COUNT( Kriminalac.oib ) FROM Kriminalac ";
		String query2="Select count(ps1.brojSlučaja) riješeni, (select count(ps2.brojSlučaja) from PolicijskiSlučaj ps2) as svi from PolicijskiSlučaj ps1 where ps1.trenutniStatus='riješen' ";
		String query3="SELECT COUNT( * ) FROM ListaOružja";
		String query4="Select distinct nazivOružja, count(brojDokaznogMaterijala) from ListaOružja join TipOružja on ListaOružja.tipOružjaID=TipOružja.tipOružjaID Group by nazivOružja; ";

		try {
			dbConnection = getDBConnection();
			statement=dbConnection.prepareStatement(query1);
			ResultSet rs1=statement.executeQuery(query1);
			statement2=dbConnection.prepareStatement(query2);
			ResultSet rs2=statement2.executeQuery(query2);
			statement3=dbConnection.prepareStatement(query2);
			ResultSet rs3=statement3.executeQuery(query3);
			statement4=dbConnection.prepareStatement(query2);
			ResultSet rs4=statement4.executeQuery(query4);
			
			while(rs1.next()){
				statistika.setBrojKriminalaca(rs1.getInt(1));
			}
			while(rs2.next()){
				statistika.setPostotakRiješenihSlučajeva(rs2.getFloat(1)/rs2.getFloat(2));
			}
			Float ukBrojOružjaUSlučajevima=0F;
			while (rs3.next()) ukBrojOružjaUSlučajevima=rs3.getFloat(1);
			while (rs4.next()){
				String naziv=rs4.getString(1);
				Float brojOdređenogOružja=rs4.getFloat(2);
				statistika.addUdioTipOružja(naziv, brojOdređenogOružja/ukBrojOružjaUSlučajevima);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return statistika;	
	}
	
	public static boolean dodajNoviDogadaj(Dogadaj dogadaj) {
		List<String> a=new ArrayList<>();
		List<String> v=new ArrayList<>();
		String upit="";
		a.add("događajID");
		a.add("nazivDogađaja");
		a.add("pbrMjesto");
		a.add("brojSlučaja");
		a.add("adresa");
		a.add("vrijeme");
		v.add("NULL");
		v.add(dogadaj.getNaziv());
		v.add(dogadaj.getPbrMjesto().toString());
		v.add(dogadaj.getBrojSlucaja().toString());
		v.add(dogadaj.getAdresa());
		v.add(dogadaj.getVrijeme().toString());
		upit=StrategijaUpit.upitUnos("ListaDogađaja", a, v);		
			try {
			dbConnection = getDBConnection();
			statement=dbConnection.prepareStatement(upit);
			statement.executeUpdate(upit);
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		return true;
	}

	public static boolean dodajNovogKriminalca(Osumnjiceni osumnjiceni) {
		
		return false;
	}
}
