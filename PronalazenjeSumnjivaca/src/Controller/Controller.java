package Controller;

import javax.swing.SwingUtilities;

import Model.*;
import View.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Controller implements ViewDelegate {

	MainWindow mW;
	Pozornik policajac;

	Slucaj slucaj;
	Dokaz dokaz;
	Osumnjiceni osumjiceni;

	public static void main(String[] args) throws SQLException {
		/*Controller c = new Controller();
		SwingUtilities.invokeLater(() -> {
			c.mW = new MainWindow();
			c.mW.add(new JPrijava(c));
		});*/
		/*Pozornik novi=new Pozornik();
        Dokaz dokaz=new Dokaz();
        dokaz.setDNASekvenca("aa");
        dokaz.setNazivSlucaja("sadada");
        dokaz.setTipOruzja("ma�");
        dokaz.setKrvnaGrupa("ss#");
        novi.posaljiUpit(dokaz);*/
        
        Pozornik novi=new Pozornik();
        Dokaz dokaz=new Dokaz();
        dokaz.setTipOruzja("Glock");
        dokaz.setKrvnaGrupa("A");
        List<Map<Dokaz, Integer>> rezultat = novi.posaljiUpit(dokaz);
	}

	@Override
	public void prijava(String username, String password) throws SQLException {
		System.out.println(username + " " + password);
		policajac = PristupBaziPodataka.prijava(username, password);
		 
		if (policajac == null) {
			return;
		} else {
			SwingUtilities
					.invokeLater(() -> {
						mW.getContentPane().removeAll();
						System.out.println("Ime: " + policajac.getIme());
						System.out.println("Razina pristupa: "
								+ policajac.getAccess());
						// System.out.println("tu sam");
						mW.getContentPane().add(new JGlavniIzbornik(policajac));
						mW.getContentPane().validate();
						mW.getContentPane().repaint();
					});

		}
		
	}// Otprilike

    @Override
    public void postaviScenuPrijava(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prikaziGlavniIzbornik(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuUpitKriminalac(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuUpitSlucaj(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuUpitDokaz(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posaljiUpitKriminalac(String ime, String prezime, int oib, String popisAliasa, String adresa, String poznateAdrese, String brojTelefona, String povezaniSlucajevi, String spol, String rasa, int visina, int tezina, int godine, String bojaKose, String oblikGlave, String oblikFrizure, String bojaOciju, String gradaTijela, String tetovaze, String fizickiNedostatci, String bolesti, String ostaleFiz, String nacinGovora, String razinaApstrIntel, String psiholoskiProblemi, String ostaleKar, String opisKrimDjelatnosti, String popisPovezanihKrim, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posaljiUpitSlucaj(int ID, String naziv, String opis, String glavniOsumnjiceni, String popisOsumnjicenih, String popisSvjedoka, String popisAdresa, String popisVremena, String popisDokaza, String popisPolicajaca, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posaljiUpitDokaz(int ID, int idSlucaja, String naziv, String krvnaGrupa, String DNASekvenca, String tipOruzja) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuPopis(ViewDelegate vd, String predmet, Map<String, Integer> popis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void odaberiStavku(String odabir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prikaziPodatkeKriminalca(ViewDelegate vd, String ime, String prezime, int oib, String popisAliasa, String adresa, String poznateAdrese, String brojTelefona, String povezaniSlucajevi, String spol, String rasa, int visina, int tezina, int godine, String bojaKose, String oblikGlave, String oblikFrizure, String bojaOciju, String gradaTijela, String tetovaze, String fizickiNedostatci, String bolesti, String ostaleFiz, String nacinGovora, String razinaApstrIntel, String psiholoskiProblemi, String ostaleKar, String opisKrimDjelatnosti, String popisPovezanihKrim, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prikaziPodatkeSlucaja(ViewDelegate vd, int ID, String naziv, String opis, String glavniOsumnjiceni, String popisOsumnjicenih, String popisSvjedoka, String popisAdresa, String popisVremena, String popisDokaza, String popisPolicajaca, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prikaziPodatkeDokaza(ViewDelegate vd, int ID, int idSlucaja, String naziv, String krvnaGrupa, String DNASekvenca, String tipOruzja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ispisPDF() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuIzmjeneKriminalca(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuIzmjeneSlucaja(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuIzmjeneDokaza(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void spremiIzmjeneKriminalca(String ime, String prezime, int oib, String popisAliasa, String adresa, String poznateAdrese, String brojTelefona, String povezaniSlucajevi, String spol, String rasa, int visina, int tezina, int godine, String bojaKose, String oblikGlave, String oblikFrizure, String bojaOciju, String gradaTijela, String tetovaze, String fizickiNedostatci, String bolesti, String ostaleFiz, String nacinGovora, String razinaApstrIntel, String psiholoskiProblemi, String ostaleKar, String opisKrimDjelatnosti, String popisPovezanihKrim, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void spremiIzmjeneSlucaja(int ID, String naziv, String opis, String glavniOsumnjiceni, String popisOsumnjicenih, String popisSvjedoka, String popisAdresa, String popisVremena, String popisDokaza, String popisPolicajaca, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void spremiIzmjeneDokaza(int ID, int idSlucaja, String naziv, String krvnaGrupa, String DNASekvenca, String tipOruzja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuDodajKriminalca(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuDodajSlucaj(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuDodajDokaz(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dodajKriminalca(String ime, String prezime, int oib, String popisAliasa, String adresa, String poznateAdrese, String brojTelefona, String povezaniSlucajevi, String spol, String rasa, int visina, int tezina, int godine, String bojaKose, String oblikGlave, String oblikFrizure, String bojaOciju, String gradaTijela, String tetovaze, String fizickiNedostatci, String bolesti, String ostaleFiz, String nacinGovora, String razinaApstrIntel, String psiholoskiProblemi, String ostaleKar, String opisKrimDjelatnosti, String popisPovezanihKrim, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dodajSlucaj(int ID, String naziv, String opis, String glavniOsumnjiceni, String popisOsumnjicenih, String popisSvjedoka, String popisAdresa, String popisVremena, String popisDokaza, String popisPolicajaca, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dodajDokaz(int ID, int idSlucaja, String naziv, String krvnaGrupa, String DNASekvenca, String tipOruzja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pristupiStatistici(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pristupiDnevniku(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void odjava(ViewDelegate vd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
