package Model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


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

	public static Pozornik prijava (String username, String password){
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
		return null;
	}

	private static Set<String> vratiID(String query) {
		Set<String> listaIDa=new LinkedHashSet<>();
		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}

			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				listaIDa.add(rs.getString(1));
			}

			return listaIDa;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
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
		return null;
	}
	
	public static Set<Slucaj> vratiSlucajeve(String query) {
		Set<Slucaj> listaSlucajeva=new LinkedHashSet<>();
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
				listaSlucajeva.add(slucaj);
			}

			return listaSlucajeva;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
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
		return null;
	}

	public static Set<Dokaz> vratiDokaze (String query)  {
		Set<Dokaz> listaDokaza=new LinkedHashSet<>();

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
				dokaz.setNazivSlucaja(rs.getString(3));
				listaDokaza.add(dokaz);
			}
			return listaDokaza;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
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
		return null;
	}

	public static Set<Osumnjiceni> vratiOsumnjicene (String query)  {
		Set<Osumnjiceni> listaOsumnjicenih=new LinkedHashSet<>();

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
		return null;
	}

	public static Dokaz dohvatiPodatkeDokaz(String id) {
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
		return null;
	}

	public static Slucaj dohvatiPodatkeSlucaj(String id)  {
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
						slucaj.addPolicajac(vratiPodatke("SELECT * FROM Policajac JOIN Osoba ON Policajac.osobaOib = Osoba.oib WHERE Policajac.jedinstveniBrojPolicajca ="+s));
					}
					vrati.removeAll(vrati);

				}



				if(!(vratiListu(Long.parseLong(id),"Osoba.oib","PolicijskiSlučaj","ListaOsumnjicenihOsoba", "ListaOsumnjicenihOsoba.brojSlučaja","PolicijskiSlučaj.brojSlučaja","Osoba", "Osoba.oib", "ListaOsumnjicenihOsoba.osobaOib").isEmpty())) {
					for(String s:vrati) {
						if(s != null) {
							slucaj.addOsumnjiceni(vratiPodatkeO("select * from Osoba WHERE oib="+s));
						}
					}
					vrati.removeAll(vrati);

				}



				if(!(vratiListu(Long.parseLong(id),"Osoba.oib","PolicijskiSlučaj","ListaSvjedoka", "ListaSvjedoka.brojSlučaja","PolicijskiSlučaj.brojSlučaja","Osoba", "Osoba.oib", "ListaSvjedoka.osobaOib").isEmpty())) {
					for(String s:vrati) {
						if(s != null) {
							slucaj.addSvjedok(vratiPodatkeO("select * from Osoba WHERE oib="+s));
						}
					}
					vrati.removeAll(vrati);
				}
				if(!vratiListu(Long.parseLong(id), "događajID", "ListaDogađaja", "PolicijskiSlučaj", "ListaDogađaja.brojSlučaja", "PolicijskiSlučaj.brojSlučaja", "PolicijskiSlučaj.brojSlučaja").isEmpty()) {
					for(String s:vrati) {
						String select="select * from ListaDogađaja where događajID='"+s+"'";
						try {
							statement=dbConnection.prepareStatement(select);
							ResultSet rSet=statement.executeQuery(select);
							while(rSet.next()) {
								Dogadaj dogadaj=new Dogadaj();
								dogadaj.setAdresa(rSet.getString(5));
								dogadaj.setNaziv(rSet.getString(2));
								dogadaj.setPbrMjesto(rSet.getInt(3));
								System.out.println("tuuuuu");
								String[] vrijemeDatum=rSet.getString(6).split(" ");
								Integer godina=Integer.parseInt(vrijemeDatum[0].split("-")[0]);
								Integer mjesec=Integer.parseInt(vrijemeDatum[0].split("-")[1]);
								Integer dan=Integer.parseInt(vrijemeDatum[0].split("-")[2]);
								Integer sat=Integer.parseInt(vrijemeDatum[1].split(":")[0]);
								Integer minute=Integer.parseInt(vrijemeDatum[1].split(":")[1]);
								//Integer sekunde=Integer.parseInt(vrijemeDatum[1].split(":")[2]);
								dogadaj.setVrijeme(LocalDateTime.of(godina, mjesec, dan, sat, minute));
								slucaj.addDogadaj(dogadaj);
							}
						}
						catch(Exception ex) {
							System.out.println(ex);
						}
					}
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
		return null;
	}

	public static Osumnjiceni dohvatiPodatkeOsumnjiceni(String oib) {
		String query="select Kriminalac.*, Osoba.imeOsobe, Osoba.prezimeOsobe,Osoba.adresaPrebivalista,Mjesto.pbrMjesto,Mjesto.nazivMjesto from Kriminalac join Osoba on Kriminalac.oib=Osoba.oib join Mjesto on Osoba.Mjesto_pbrMjesto=Mjesto.pbrMjesto where Osoba.oib='"+oib+"'";
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

				osumnjiceni.setIme(rs.getString(18));
				osumnjiceni.setPrezime(rs.getString(19));
				AdresaIMjestoStanovanja adrPreb=new AdresaIMjestoStanovanja();
				adrPreb.setAdresa(rs.getString(20));
				adrPreb.setNazivMjesta(rs.getString(22));
				adrPreb.setPbrMjesto(rs.getInt(21));
				osumnjiceni.setAdresaPrebivalista(adrPreb);

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
				if(gradaTijela!=null) {
					switch (gradaTijela) {
					case "slabija": fOsobina.setGradaTijela(GradaTijela.slabija);;break;
					case "srednja": fOsobina.setGradaTijela(GradaTijela.srednja);;break;
					case "jača": fOsobina.setGradaTijela(GradaTijela.jaca);; break;
					}
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
				System.out.println(razinaInt);
				if(razinaInt!=null) {

					switch (razinaInt) {
					case "visoka": kOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.visoka);break;
					case "srednja": kOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.srednja);break;
					case "niska": kOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.niska); break;
					}
				}
				if(!(vratiListu(Long.parseLong(oib),"Bolest.nazivBolesti","Kriminalac","ListaBolestiKriminalca","ListaBolestiKriminalca.kriminalacOib","Kriminalac.oib","Bolest","Bolest.bolestID" , "ListaBolestiKriminalca.bolestID").isEmpty())) {
					for(String s:vrati) {
						fOsobina.addBolest(s);

					}

					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"Tetovaža.opisTetovaže","Kriminalac","TetovažeKriminalca","TetovažeKriminalca.kriminalacOib","Kriminalac.oib","Tetovaža","Tetovaža.tetovažaID" , "TetovažeKriminalca.tetovažaID").isEmpty())) {

					for(String s:vrati) {
						fOsobina.addTetovaza(s);
					}

					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"FizičkiNedostatak.fizičkiNedostatakOpis","Kriminalac","FizičkiNedostaciKriminalca","FizičkiNedostaciKriminalca.kriminalacOib","Kriminalac.oib","FizičkiNedostatak","FizičkiNedostatak.fizičkiNedostatakID" , "FizičkiNedostaciKriminalca.fizičkiNedostatakID").isEmpty())) {
					for(String s:vrati) {
						fOsobina.addFizickiNedostatak(s);
					}

					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"KarakternaOsobina.karakternaOsobinaOpis","Kriminalac","OstaleKarakterneOsobineKriminalca","OstaleKarakterneOsobineKriminalca.kriminalacOib","Kriminalac.oib","KarakternaOsobina","KarakternaOsobina.karakternaOsobinaID" , "OstaleKarakterneOsobineKriminalca.karakternaOsobinaID").isEmpty())) {
					for(String s:vrati) {
						kOsobine.addOstalo(s);

					}
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"PsihološkiProblem.psihološkiProblemOpis","Kriminalac","PsihološkiProblemiKriminalca","PsihološkiProblemiKriminalca.kriminalacOib","Kriminalac.oib","PsihološkiProblem","PsihološkiProblem.psihološkiProblemID" , "PsihološkiProblemiKriminalca.psihološkiProblemID").isEmpty())) {

					for(String s:vrati) {
						kOsobine.addPsiholoskiProblem(s);

					}
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib),"FizičkaOsobina.fizičkaOsobinaOpis","Kriminalac","OstaleFizičkeOsobineKriminalca","OstaleFizičkeOsobineKriminalca.kriminalacOib","Kriminalac.oib","FizičkaOsobina","FizičkaOsobina.fizičkaOsobina" , "OstaleFizičkeOsobineKriminalca.fizičkaOsobinaID").isEmpty())) {

					for(String s:vrati) {

						fOsobina.addOstalo(s);}
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "fotografijaURL", "OtisakPrsta", "Kriminalac", "OtisakPrsta.otisakPrstaID", "Kriminalac.otisakPrstaID","Kriminalac.oib").isEmpty())) {
					System.out.println(vrati.get(0));
					osumnjiceni.setOtisakPrstaURL(vrati.get(0));
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "FotografijaURL", "FotografijeKriminalca", "Kriminalac", "FotografijeKriminalca.OibKriminalac", "Kriminalac.oib","Kriminalac.oib").isEmpty())) {
					for(String s:vrati) {
						osumnjiceni.addFotografijaURL(s);
					}

					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "alias","ListaAliasa", "Kriminalac", "ListaAliasa.kriminalacOib", "Kriminalac.oib","Kriminalac.oib").isEmpty())) {
					for(String s:vrati) {
						osumnjiceni.addAlias(s);
					}
				}
				if(!(vratiListu(Long.parseLong(oib), "nazivSlučaja", "ListaOsumnjicenihOsoba", "PolicijskiSlučaj", "ListaOsumnjicenihOsoba.brojSlučaja", "PolicijskiSlučaj.brojSlučaja","ListaOsumnjicenihOsoba.osobaOib").isEmpty())) {
					Set<Slucaj> slucaji=new HashSet<>();
					for(String s:vrati) {
						Slucaj slucaj=new Slucaj();
						slucaj.setNazivSlucaja(s);
						slucaji.add(slucaj);
					}
					osumnjiceni.setPovezaniSlucajevi(slucaji);
					vrati.removeAll(vrati);
				}
				if(!(vratiListu(Long.parseLong(oib), "adresaStanovanja", "PoznateAdreseStanovanjaKriminalca", "Kriminalac", "PoznateAdreseStanovanjaKriminalca.kriminalacOib", "Kriminalac.oib","Kriminalac.oib").isEmpty())) {
					AdresaIMjestoStanovanja adr=new AdresaIMjestoStanovanja();
					String select="select * from PoznateAdreseStanovanjaKriminalca where kriminalacOib='"+oib+"'";
					vrati.removeAll(vrati);
					try {
						System.out.println(select);
						statement=dbConnection.prepareStatement(select);
						ResultSet r = statement.executeQuery(select);
						while(r.next()) {
							adr.setPbrMjesto(r.getInt(3));
							adr.setAdresa(r.getString(4));
							vratiListu(r.getLong(3), "nazivMjesto","Mjesto","PoznateAdreseStanovanjaKriminalca" , "Mjesto.pbrMjesto", "PoznateAdreseStanovanjaKriminalca.pbrMjesto", "Mjesto.pbrMjesto");
							adr.setNazivMjesta(vrati.get(0));
							osumnjiceni.addPoznataAdresa(adr);
						}
					}

					catch (Exception ex) {
						System.out.println(ex);
					}


					vrati.removeAll(vrati);
				}

				if(!(vratiListu(Long.parseLong(oib), "povezanSaKriminalacOib", "ListaPovezanihKriminalaca", "Kriminalac", "ListaPovezanihKriminalaca.kriminalacOib", "Kriminalac.oib","Kriminalac.oib").isEmpty())) {

					for(String s:vrati) {
						Osumnjiceni osumnjiceni2=new Osumnjiceni();
						System.out.println(s);
						osumnjiceni2.setOib(Long.parseLong(s));
						Osoba osoba=vratiPodatkeO("select * from Osoba WHERE oib="+s);
						osumnjiceni2.setIme(osoba.getIme());
						osumnjiceni2.setPrezime(osoba.getPrezime());
						osumnjiceni.addPovezanKriminalac(osumnjiceni2);

					}
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
		String brDokaza;
		Set<String> krvnaGrupa=dokaz.getKrvnaGrupa();
		Set<String> dnaSekvenca=dokaz.getDNASekvenca();
		Set<String> tipOružja=dokaz.getTipOruzja();
		Set<String> otisakPrsta=dokaz.getOtisakPrsta();

		atr.addAll(Arrays.asList("brojDokaznogMaterijala","nazivDokaznogMaterijala","brojSlučaja","fotografijaDokaznogMaterijalaURL"));
		vrij.addAll(Arrays.asList("NULL",naziv,brSlucaja,foto));
		brDokaza=izvrsiUpit(StrategijaUpit.upitUnos("DokazniMaterijal", atr, vrij));
		System.out.println(query);
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
	}
	private static void upišiUBazu(String ime,Set<String> atribut,String brDokaza) {
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
		List<String> atr=new ArrayList<>();
		List<String> vrij=new ArrayList<>();
		String query="";
		atr.addAll(Arrays.asList("brojSlučaja","nazivSlučaja","opis","trenutniStatus","glavnaOsumljicenaOsobaOib"));
		if(slucaj.getGlavniOsumnjiceni()==null) {
			vrij.addAll(Arrays.asList("NULL",slucaj.getNazivSlucaja(),slucaj.getOpis(),slucaj.getStatus().toString(),"NULL"));
		}
		else {
			
		vrij.addAll(Arrays.asList("NULL",slucaj.getNazivSlucaja(),slucaj.getOpis(),slucaj.getStatus().toString(),slucaj.getGlavniOsumnjiceni().getOib().toString()));
		}
		String idSlucaja=izvrsiUpit(StrategijaUpit.upitUnos("PolicijskiSlučaj", atr, vrij));
		System.out.println(query);	
		Set<String> popisOsumnjicenih = new HashSet<String>();
		atr.removeAll(atr);
		vrij.removeAll(vrij);
		
		for(Osoba o:slucaj.getPopisOsumnjicenih()) {
			if(provjeriUnos("Osoba.oib", o.getOib().toString(), "Osoba", "Osoba.oib")=="nema") {
				if(provjeriUnos("pbrMjesto", o.getAdresaPrebivalista().getPbrMjesto().toString(), "Mjesto", "Mjesto.pbrMjesto")=="nema") {
					izvrsiUnos(StrategijaUpit.upitUnos("Mjesto", "pbrMjesto", "nazivMjesto", o.getAdresaPrebivalista().getPbrMjesto().toString(), o.getAdresaPrebivalista().getNazivMjesta()));
				}
				atr.addAll(Arrays.asList("oib","imeOsobe","prezimeOsobe","adresaPrebivalista","Mjesto_pbrMjesto"));
				vrij.addAll(Arrays.asList(o.getOib().toString(),o.getIme(),o.getPrezime(),o.getAdresaPrebivalista().getAdresa(),o.getAdresaPrebivalista().getPbrMjesto().toString()));
				izvrsiUnos(StrategijaUpit.upitUnos("Osoba", atr, vrij));
			}
			popisOsumnjicenih.add(o.getOib().toString());
		}
		atr.removeAll(atr);
		vrij.removeAll(vrij);
		Set<String> popisSvjedoka = new HashSet<String>();
		for(Osoba o:slucaj.getPopisSvjedoka()) {
			if(provjeriUnos("Osoba.oib", o.getOib().toString(), "Osoba", "Osoba.oib")=="nema") {
				if(provjeriUnos("pbrMjesto", o.getAdresaPrebivalista().getPbrMjesto().toString(), "Mjesto", "Mjesto.pbrMjesto")=="nema") {
					izvrsiUnos(StrategijaUpit.upitUnos("Mjesto", "pbrMjesto", "nazivMjesto", o.getAdresaPrebivalista().getPbrMjesto().toString(), o.getAdresaPrebivalista().getNazivMjesta()));
				}
				atr.addAll(Arrays.asList("oib","imeOsobe","prezimeOsobe","adresaPrebivalista","Mjesto_pbrMjesto"));
				vrij.addAll(Arrays.asList(o.getOib().toString(),o.getIme(),o.getPrezime(),o.getAdresaPrebivalista().getAdresa(),o.getAdresaPrebivalista().getPbrMjesto().toString()));
				izvrsiUnos(StrategijaUpit.upitUnos("Osoba", atr, vrij));
			}
			popisSvjedoka.add(o.getOib().toString());
		}
		atr.removeAll(atr);
		vrij.removeAll(vrij);
		
		Set<String> popisPolicajaca = new HashSet<String>();
		for(Osoba o:slucaj.getPopisPolicajaca()) {
			popisPolicajaca.add(o.getOib().toString());
		}
		
		Set<String> fotografijeSlučaja=slucaj.getFotografijeSlučaja();
		if(!popisOsumnjicenih.isEmpty()) {
			for(String s:popisOsumnjicenih) {
			izvrsiUnos(StrategijaUpit.upitUnos("ListaOsumnjicenihOsoba", "osobaOib", "brojSlučaja", s, idSlucaja));
			}	
		}
		if(!popisPolicajaca.isEmpty()) {
			for(String s:popisPolicajaca) {// s=OIB
				String id=provjeriUnos("jedinstveniBrojPolicajca", s, "Policajac","osobaOib" );
				System.out.println(id);
					izvrsiUnos(StrategijaUpit.upitUnos("PolicajciDodijeljeniSlučaju", "brojSlučaja", "jedinstveniBrojPolicajca", idSlucaja, id));
			}
		}
			if(!popisSvjedoka.isEmpty()) {
				for(String s:popisSvjedoka) {
					izvrsiUnos(StrategijaUpit.upitUnos("ListaSvjedoka", "brojSlučaja", "osobaOib", idSlucaja, s));
				}
			}
			if(!fotografijeSlučaja.isEmpty()) {
				for(String s:fotografijeSlučaja) {
					atr.addAll(Arrays.asList("fotografijaID","fotografijaURL","brojSlučaja"));
					vrij.addAll(Arrays.asList("NULL",s,idSlucaja));
					izvrsiUnos(StrategijaUpit.upitUnos("FotografijaPolicijskogSlučaja", atr, vrij));
				
				}
			}
			Set<Dogadaj> popisDogadaja=slucaj.getPopisDogadaja();
			if(!popisDogadaja.isEmpty()) {
				for(Dogadaj dogadaj: popisDogadaja) {
					dogadaj.setBrojSlucaja(Integer.parseInt(idSlucaja));
					dodajNoviDogadaj(dogadaj);
				}
			}
			
		
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

	public static List<DnevnikPretrazivanja> dohvatiZapiseDnevnika(){
		Connection dbConnection = null;
		Statement statement = null;
		List<DnevnikPretrazivanja> listaZapisa=new LinkedList<>();
		String query="Select dnevnikPretraživanjaID, ipAdresa,vrijemeUpita, jedinstveniBrojPolicajca from DnevnikPretraživanja";
		try {
			dbConnection = getDBConnection();
			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				DnevnikPretrazivanja zapis=new DnevnikPretrazivanja();
				zapis.setID(rs.getInt(1));
				zapis.setIpAdresa(rs.getString(2));
				zapis.setVrijemeUpita(rs.getTimestamp(3).toString());
				zapis.setBrojPolicajca(rs.getInt(4));
				listaZapisa.add(zapis);
			}
			return listaZapisa;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
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
		return null;
	}

	public static DnevnikPretrazivanja izaberiZapisUDnevniku(Integer brojZapisa){
		Connection dbConnection = null;
		Statement statement = null;
		DnevnikPretrazivanja zapis=new DnevnikPretrazivanja();
		String query="Select * from DnevnikPretraživanja where dnevnikPretraživanjaID='"+brojZapisa+"'";

		try {
			dbConnection = getDBConnection();
			statement=dbConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				zapis=new DnevnikPretrazivanja();
				zapis.setID(rs.getInt(1));
				zapis.setIpAdresa(rs.getString(2));
				zapis.setVrijemeUpita(rs.getTimestamp(5).toString());
				zapis.setBrojPolicajca(rs.getInt(6));
				zapis.setSqlUpit(rs.getString(4));
				zapis.setTextUpita(rs.getString(3));

			}
			return zapis;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
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
		return null;
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
				statistika.setPostotakRiješenihSlučajeva((rs2.getFloat(1)/rs2.getFloat(2))*100);
			}
			Float ukBrojOružjaUSlučajevima=0F;
			while (rs3.next()) ukBrojOružjaUSlučajevima=rs3.getFloat(1);
			while (rs4.next()){
				String naziv=rs4.getString(1);
				Float brojOdređenogOružja=rs4.getFloat(2);
				statistika.addUdioTipOružja(naziv, (brojOdređenogOružja/ukBrojOružjaUSlučajevima)*100);
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
		if(provjeriUnos("pbrMjesto", dogadaj.getPbrMjesto().toString(), "Mjesto", "Mjesto.pbrMjesto")=="nema") {
			izvrsiUnos(StrategijaUpit.upitUnos("Mjesto", "pbrMjesto", "nazivMjesto", dogadaj.getPbrMjesto().toString(), dogadaj.getNazivMjesto()));
		}
		a.addAll(Arrays.asList("događajID","nazivDogađaja","pbrMjesto","brojSlučaja","adresa","vrijeme"));
		v.addAll(Arrays.asList("NULL",dogadaj.getNaziv(),dogadaj.getPbrMjesto().toString(),dogadaj.getBrojSlucaja().toString(),dogadaj.getAdresa(),dogadaj.getVrijeme().toString()));
		izvrsiUnos(StrategijaUpit.upitUnos("ListaDogađaja", a, v));		
		return true;
	}

	public static boolean dodajNovogKriminalca(Osumnjiceni osumnjiceni) {
		Set<String> listaAtributa= osumnjiceni.generirajListuAtributaPretrage();	
		List<String> atributi=new ArrayList<>();
		List<String> aList=new ArrayList<>();
		List<String> vrijednosti = new ArrayList<>();
		List<String> vList=new ArrayList<>();
		String upit="";
		String rez="";
		@SuppressWarnings("unused")
		String prezime="",ime="",nazivMjesto="",adresa,pbr = null,oib="";
		
		for (String s:listaAtributa) {
			System.out.println(s);
			String [] parts=s.split("\\*");
			System.out.println(parts);
			String obiljezje=parts[1];
			System.out.println(obiljezje);
			String vrijednost=parts[0];
			
			if(obiljezje.startsWith("Osoba.ime")) {
				String [] parts2=obiljezje.split("\\.");
				String atribut=parts2[1];
				ime=vrijednost;		
				aList.add(atribut);
				vList.add(vrijednost);
				
			}
			if(obiljezje.startsWith("Osoba.prezime")) {
				String [] parts2=obiljezje.split("\\.");
				String atribut=parts2[1];
				prezime=vrijednost;
				aList.add(atribut);
				vList.add(vrijednost);
			}
			if(obiljezje.startsWith("Kriminalac.oib")) {
				String [] parts2=obiljezje.split("\\.");
				String atribut=parts2[1];
				oib=vrijednost;
				aList.add(atribut);
				vList.add(vrijednost);
			}
			if(obiljezje.startsWith("Kriminalac.")) {	
				String [] parts2=obiljezje.split("\\.");
				String atribut=parts2[1];
				atributi.add(atribut);
				vrijednosti.add(vrijednost);
			}
			if(obiljezje.startsWith("Osoba.adresa")) {
				String [] parts2=obiljezje.split("\\.");
				String atribut=parts2[1];
				pbr=osumnjiceni.getAdresaPrebivalista().getPbrMjesto().toString();
				adresa=vrijednost.split("#")[0];
				nazivMjesto=vrijednost.split("#")[1];
				aList.add(atribut);
				vList.add(adresa);
				aList.add("Mjesto_pbrMjesto");
				vList.add(pbr);
			}
		}
		if(provjeriUnos("Osoba.oib", oib, "Osoba", "Osoba.oib")=="nema") { //ako nema te osobe
			if(provjeriUnos("pbrMjesto", pbr, "Mjesto", "Mjesto.pbrMjesto")=="nema") {
				izvrsiUnos(StrategijaUpit.upitUnos("Mjesto", "pbrMjesto", "nazivMjesto", pbr, nazivMjesto));
			}
			izvrsiUnos(StrategijaUpit.upitUnos("Osoba", aList, vList));
			
		}
		String otisakUrl=osumnjiceni.getOtisakPrstaURL();
		String idOtisak=provjeriUnos("otisakPrstaID", otisakUrl, "OtisakPrsta", "fotografija");
				if(idOtisak!="nema") {// ako vec postoji
					izvrsiUnos(StrategijaUpit.upitUnos("OtisakPrsta","otisakPrstaID","fotografijaURL" ,idOtisak, otisakUrl));
				}
				else { //ako ne postoji
					idOtisak=izvrsiUpit(StrategijaUpit.upitUnos("OtisakPrsta","otisakPrstaID","fotografijaURL" ,"NULL", otisakUrl));
				}
		
		atributi.add("otisakPrstaID");
		vrijednosti.add(idOtisak);
		atributi.add("datumRođenja");
		vrijednosti.add(osumnjiceni.getDatumRodjenja().toString());
		izvrsiUnos(StrategijaUpit.upitUnos("Kriminalac", atributi, vrijednosti));
		
		for (String s: listaAtributa){
			String [] parts=s.split("\\*");
			String relacija="";
			String atribut="";
			String obiljezje=parts[1];
			String vrijednost=parts[0];
			if(!obiljezje.startsWith("Poznate")) {
				String [] parts2=obiljezje.split("\\.");
				relacija=parts2[0];
				atribut=parts2[1];
				System.out.println(obiljezje);
			}



			switch (obiljezje){
			case "Tetovaža.opisTetovaže": 
				System.out.println(parts);
				System.out.println(relacija);
				System.out.println(vrijednost);
				System.out.println(atribut);
				rez=provjeriUnos("tetovažaID",vrijednost,relacija,atribut);
				if(rez=="nema") {
					upit=StrategijaUpit.upitUnos(relacija,"tetovažaID", atribut,"NULL", vrijednost);
					System.out.println(upit);
					String id=izvrsiUpit(upit);
					rez=id;

				}

				upit=StrategijaUpit.upitUnos("TetovažeKriminalca","kriminalacOib","tetovažaID", oib, rez);
				System.out.println(upit);
				izvrsiUnos(upit);

				break;

			case "FizičkiNedostatak.fizičkiNedostatakOpis": 
				rez=provjeriUnos("fizičkiNedostatakID",vrijednost,relacija,atribut);
				if(rez=="nema") {
					upit=StrategijaUpit.upitUnos(relacija,"fizičkiNedostatakID", atribut,"NULL", vrijednost);
					String id=izvrsiUpit(upit);
					rez=id;

				}

				upit=StrategijaUpit.upitUnos("FizičkiNedostaciKriminalca","kriminalacOib","fizičkiNedostatakID", oib, rez);
				izvrsiUnos(upit);

				break;

			case "Bolest.nazivBolesti": 
				rez=provjeriUnos("bolestID",vrijednost,relacija,atribut);
				if(rez=="nema") {
					upit=StrategijaUpit.upitUnos(relacija,"bolestID", atribut,"NULL", vrijednost);
					System.out.println(upit);
					String id=izvrsiUpit(upit);
					rez=id;

				}

				upit=StrategijaUpit.upitUnos("ListaBolestiKriminalca","kriminalacOib","bolestID",oib, rez);
				izvrsiUnos(upit);

				break;

			case "FizičkaOsobina.fizičkaOsobinaOpis": 
				rez=provjeriUnos("fizičkaOsobina",vrijednost,relacija,atribut);
				if(rez=="nema") {
					upit=StrategijaUpit.upitUnos("FizičkaOsobina","fizičkaOsobina", atribut,"NULL",vrijednost);
					String id=izvrsiUpit(upit);
					rez=id;
					System.out.println(rez);

				}

				upit=StrategijaUpit.upitUnos("OstaleFizičkeOsobineKriminalca","kriminalacOib","fizičkaOsobinaID", oib, rez);
				izvrsiUnos(upit);

				break;

			case "PsihološkiProblem.psihološkiProblemOpis": 
				rez=provjeriUnos("psihološkiProblemID",vrijednost,relacija,atribut);
				if(rez=="nema") {
					upit=StrategijaUpit.upitUnos(relacija,"psihološkiProblemID", atribut, "NULL",vrijednost);
					String id=izvrsiUpit(upit);
					rez=id;	
				}
				upit=StrategijaUpit.upitUnos("PsihološkiProblemiKriminalca","kriminalacOib","psihološkiProblemID", oib, rez);
				izvrsiUnos(upit);

				break;

			case "KarakternaOsobina.karakternaOsobinaOpis": 
				rez=provjeriUnos("karakternaOsobinaID",vrijednost,relacija,atribut);
				if(rez=="nema") {
					upit=StrategijaUpit.upitUnos(relacija,"karakternaOsobinaID", atribut,"NULL", vrijednost);
					String id=izvrsiUpit(upit);
					rez=id;

				}

				upit=StrategijaUpit.upitUnos("OstaleKarakterneOsobineKriminalca","kriminalacOib","karakternaOsobinaID", oib, rez);
				izvrsiUnos(upit);

				break;

			case "ListaAliasa.alias": 

				upit="INSERT INTO `ListaAliasa`(`aliasID`, `alias`, `kriminalacOib`) VALUES (NULL,'"+vrijednost+"','"+oib+"')";
				izvrsiUnos(upit);

				break;

			

			case "ListaPovezanihKriminalaca.povezanSaKriminalacOib": 

				upit=StrategijaUpit.upitUnos(relacija, "kriminalacOib",atribut,oib, vrijednost);
				izvrsiUnos(upit);
				upit=StrategijaUpit.upitUnos(relacija, "kriminalacOib",atribut,vrijednost, oib);
				izvrsiUnos(upit);
				break;

			}

		}
		if(!osumnjiceni.getFotografijeURL().isEmpty()) {
			Set<String> fotoUrl=osumnjiceni.getFotografijeURL();
			List<String> a=new ArrayList<>();
			List<String> v=new ArrayList<>();
			a.add("IDFotografija");
			a.add("FotografijaURL");
			a.add("OibKriminalac");
			v.add("NULL");
			for(String url:fotoUrl) {
				vList.add(url);
				vList.add(oib);
				izvrsiUnos(StrategijaUpit.upitUnos("FotografijeKriminalca", a, v));

			}
		}
		if(!osumnjiceni.getPoznateAdrese().isEmpty()) {
			Set<AdresaIMjestoStanovanja> poznateadrese=osumnjiceni.getPoznateAdrese();
			for(AdresaIMjestoStanovanja adresaIMjestoStanovanja : poznateadrese) {
				String adresaNaziv=adresaIMjestoStanovanja.getAdresa();
				String mjestoNaziv=adresaIMjestoStanovanja.getNazivMjesta();
				String pbrMjesto=adresaIMjestoStanovanja.getPbrMjesto().toString();
				List<String> aLista=new ArrayList<>();
				List<String> vLista=new ArrayList<>();
				if(provjeriUnos("pbrMjesto", pbrMjesto, "Mjesto", "pbrMjesto")=="nema") {
					izvrsiUnos(StrategijaUpit.upitUnos("Mjesto", "pbrMjesto", "nazivMjesto", pbrMjesto, mjestoNaziv));
				}
						
				aLista.addAll(Arrays.asList("adresaStanovanjaID","kriminalacOib","pbrMjesto","adresaStanovanja"));
				vLista.addAll(Arrays.asList("NULL",oib,pbr,adresaNaziv));
				upit=StrategijaUpit.upitUnos("PoznateAdreseStanovanjaKriminalca", aLista, vLista);
				System.out.println(upit);
				izvrsiUnos(upit);
				
			}
		}
		return true;
	}
	
	public static String provjeriUnos(String select,String vrijednost,String relacija,String atribut){
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String query="select "+select+" from "+relacija +" where "+atribut+"='"+vrijednost+"'";
		System.out.println(query);
		try {
			dbConnection = getDBConnection();
			preparedStatement=dbConnection.prepareStatement(query);
			ResultSet rSet = preparedStatement.executeQuery();
			if(rSet.next()) return rSet.getString(1);
		}

		catch (Exception e) {
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
		return "nema";
	}
	
	public static String izvrsiUpit(String upit) {
		String id="";
		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) {System.out.println("fail");	
			}
			statement = dbConnection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);  
			statement.executeUpdate(upit,Statement.RETURN_GENERATED_KEYS);  
			ResultSet keys = statement.getGeneratedKeys();    
			keys.next();  
			id=keys.getString(1);
			System.out.println(id);
			return id;
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		return id;
	}
	
	public static boolean izvrsiUnos(String upit) {
		try {
			dbConnection = getDBConnection();
			if (dbConnection==null) return false;	
			statement = dbConnection.prepareStatement(upit);  
			statement.executeUpdate(upit);  

		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}
	
	public static String provjeriUnos(String select,String vrijednost,String vrijednost2,String relacija,String atribut,String atribut2){
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String query="select "+select+" from "+relacija +" where "+atribut+"='"+vrijednost+"' AND "+atribut2+"='"+vrijednost2;
		System.out.println(query);
		try {
			dbConnection = getDBConnection();
			preparedStatement=dbConnection.prepareStatement(query);
			ResultSet rSet = preparedStatement.executeQuery();
			if(rSet.next()) return rSet.getString(1);
		}

		catch (Exception e) {
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
		return "nema";
	}
	
	public static boolean izmjenaContexta(String updateSQL,String whereSQL,Set<String> atributi){
		for(String s: atributi){
			System.out.println(s);
			String [] parts=s.split("\\*");
			String vrijednost=parts[0];
			String atribut=parts[1];
			updateSQL +=StrategijaUpit.generirajUpdate( atribut,vrijednost)+",";
		}
		updateSQL=updateSQL.substring(0, updateSQL.lastIndexOf(","));
		updateSQL+=whereSQL;
		System.out.println("Update: "+updateSQL);
		return izvrsiUnos(updateSQL);
	}
	
	public static Set<String> dohvatiOsobe(){
		String query="SELECT Osoba.oib FROM Osoba";
		return vratiID(query);
	}
	public static Set<String> dohvatiPolicajce(){
		String query="SELECT Policajac.jedinstveniBrojPolicajca FROM Policajac";
		return vratiID(query);
	}
	public static Set<String> dohvatiKriminalce(){
		String query="SELECT Kriminalac.oib FROM Kriminalac";
		return vratiID(query);
	}
	public static Pozornik vratiPodatke(String upit) {
		Pozornik pozornik=new Pozornik();
		try {
			dbConnection = getDBConnection();
			statement=dbConnection.prepareStatement(upit);
			ResultSet rSet = statement.executeQuery(upit);
			if(rSet.next()) {
				pozornik.setIme(rSet.getString(7));
				pozornik.setPrezime(rSet.getString(8));
				pozornik.setJedinstveniBroj(rSet.getInt(1));
				pozornik.setOib(rSet.getLong(2));
				return pozornik;
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return pozornik;
	}
	public static Osoba vratiPodatkeO(String upit) {
		Osoba osoba=new Osoba();
		try {
			dbConnection = getDBConnection();
			statement=dbConnection.prepareStatement(upit);
			ResultSet rSet = statement.executeQuery(upit);
			if(rSet.next()) {
				osoba.setIme(rSet.getString(2));
				osoba.setOib(rSet.getLong(1));
				osoba.setPrezime(rSet.getString(3));
				return osoba;
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return osoba;
	}
}
