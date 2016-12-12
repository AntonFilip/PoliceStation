package Controller;

import Model.*;
import java.util.Map;

public interface ViewDelegate {

	public void postaviScenuPrijava(ViewDelegate vd); // pocetak
        public void prijava(String korisnickoIme, String lozinka);// klik na prijava
        public void prikaziGlavniIzbornik(ViewDelegate vd); // uspjesna prijava -> ime, prezime, razina trenutnog korisnika (atribut u controlleru)
	public void postaviScenuUpitKriminalac(ViewDelegate vd); // klik na postavi upit o kriminalcu
	public void postaviScenuUpitSlucaj(ViewDelegate vd); // klik na postavi upit o slucaju
        public void postaviScenuUpitDokaz(ViewDelegate vd); // klik na postavi upit o dokazu
        public void posaljiUpitKriminalac(String ime, String prezime, int oib, String popisAliasa, 
                String adresa, String poznateAdrese, String brojTelefona, String povezaniSlucajevi, 
                String spol, String rasa, int visina, int tezina, int godine, String bojaKose, 
                String oblikGlave, String oblikFrizure, String bojaOciju,String gradaTijela, 
                String tetovaze, String fizickiNedostatci, String bolesti, String ostaleFiz,
                String nacinGovora, String razinaApstrIntel, String psiholoskiProblemi, String ostaleKar,
                String opisKrimDjelatnosti, String popisPovezanihKrim, String status); // upisani podaci -> klik na posalji upit
	public void posaljiUpitKriminalac(Osumnjiceni kriminalac);
        public void posaljiUpitSlucaj(int ID, String naziv, String opis, String glavniOsumnjiceni, 
                String popisOsumnjicenih, String popisSvjedoka, String popisAdresa, String popisVremena, 
                String popisDokaza, String popisPolicajaca, String status); // upisani podaci -> klik na posalji upit
        public void posaljiUpitSlucaj(Slucaj slucaj);
        public void posaljiUpitDokaz(int ID, int idSlucaja, String naziv, String krvnaGrupa, String DNASekvenca,
                String tipOruzja); // upisani podaci -> klik na posalji upit
        public void posaljiUpitDokaz(Dokaz dokaz);
        public void postaviScenuPopis(ViewDelegate vd, String predmet, Map<String,Integer> popis); // predmet: kriminalac, slucaj ili dokaz
        public void odaberiStavku(String odabir); // popis -> klik na kriminalca/slucaj/dokaz
        public void prikaziPodatkeKriminalca(ViewDelegate vd, String ime, String prezime, int oib, String popisAliasa, 
                String adresa, String poznateAdrese, String brojTelefona, String povezaniSlucajevi, 
                String spol, String rasa, int visina, int tezina, int godine, String bojaKose, 
                String oblikGlave, String oblikFrizure, String bojaOciju,String gradaTijela, 
                String tetovaze, String fizickiNedostatci, String bolesti, String ostaleFiz,
                String nacinGovora, String razinaApstrIntel, String psiholoskiProblemi, String ostaleKar,
                String opisKrimDjelatnosti, String popisPovezanihKrim, String status);
        public void prikaziPodatkeKriminalca(ViewDelegate vd, Osumnjiceni kriminalac);
        public void prikaziPodatkeSlucaja(ViewDelegate vd, int ID, String naziv, String opis, String glavniOsumnjiceni, 
                String popisOsumnjicenih, String popisSvjedoka, String popisAdresa, String popisVremena, 
                String popisDokaza, String popisPolicajaca, String status);
        public void prikaziPodatkeSlucaja(ViewDelegate vd, Slucaj slucaj);
        public void prikaziPodatkeDokaza(ViewDelegate vd, int ID, int idSlucaja, String naziv, String krvnaGrupa, 
                String DNASekvenca, String tipOruzja);    
        public void prikaziPodatkeDokaza(ViewDelegate vd, Dokaz dokaz);
        public void ispisPDF(); // prikaz podataka -> klik na ispis -> generiranje PDF-a
        public void postaviScenuIzmjeneKriminalca(ViewDelegate vd); // klik na izmjenu kriminalca
        public void postaviScenuIzmjeneSlucaja(ViewDelegate vd); // klik na izmjenu slucaja
        public void postaviScenuIzmjeneDokaza(ViewDelegate vd); // klik na izmjenu dokaza
        public void spremiIzmjeneKriminalca(String ime, String prezime, int oib, String popisAliasa, 
                String adresa, String poznateAdrese, String brojTelefona, String povezaniSlucajevi, 
                String spol, String rasa, int visina, int tezina, int godine, String bojaKose, 
                String oblikGlave, String oblikFrizure, String bojaOciju,String gradaTijela, 
                String tetovaze, String fizickiNedostatci, String bolesti, String ostaleFiz,
                String nacinGovora, String razinaApstrIntel, String psiholoskiProblemi, String ostaleKar,
                String opisKrimDjelatnosti, String popisPovezanihKrim, String status); // upisani podaci -> klik na spremi izmjene
        public void spremiIzmjeneKriminalca(Osumnjiceni kriminalac);
        public void spremiIzmjeneSlucaja(int ID, String naziv, String opis, String glavniOsumnjiceni, 
                String popisOsumnjicenih, String popisSvjedoka, String popisAdresa, String popisVremena, 
                String popisDokaza, String popisPolicajaca, String status); // upisani podaci -> klik na spremi izmjene
        public void spremiIzmjeneSlucaja(Slucaj slucaj);
        public void spremiIzmjeneDokaza(int ID, int idSlucaja, String naziv, String krvnaGrupa, 
                String DNASekvenca, String tipOruzja); // upisani podaci -> klik na spremi izmjene
        public void spremiIzmjeneDokaza(Dokaz dokaz);
        public void postaviScenuDodajKriminalca(ViewDelegate vd); // klik na dodaj krimija
        public void postaviScenuDodajSlucaj(ViewDelegate vd); // klik na dodaj slucaj
        public void postaviScenuDodajDokaz(ViewDelegate vd); //klik na dodaj dokaz iz scene za dodavanje slucaja
        public void dodajKriminalca(String ime, String prezime, int oib, String popisAliasa, 
                String adresa, String poznateAdrese, String brojTelefona, String povezaniSlucajevi, 
                String spol, String rasa, int visina, int tezina, int godine, String bojaKose, 
                String oblikGlave, String oblikFrizure, String bojaOciju,String gradaTijela, 
                String tetovaze, String fizickiNedostatci, String bolesti, String ostaleFiz,
                String nacinGovora, String razinaApstrIntel, String psiholoskiProblemi, String ostaleKar,
                String opisKrimDjelatnosti, String popisPovezanihKrim, String status); // upisani podaci -> klik na spremi
        public void dodajKriminalca(Osumnjiceni kriminalac);
        public void dodajSlucaj(int ID, String naziv, String opis, String glavniOsumnjiceni, 
                String popisOsumnjicenih, String popisSvjedoka, String popisAdresa, String popisVremena, 
                String popisDokaza, String popisPolicajaca, String status); // upisani podaci -> klik na spremi
        public void dodajSlucaj(Slucaj slucaj);
        public void dodajDokaz(int ID, int idSlucaja, String naziv, String krvnaGrupa, 
                String DNASekvenca, String tipOruzja); // upisani podaci -> klik na spremi 
        public void dodajDokaz(Dokaz dokaz);
        public void pristupiStatistici(ViewDelegate vd); // klik na statistiku
        public void pristupiDnevniku(ViewDelegate vd); // klik na dnevnik
        public void odjava(ViewDelegate vd); // klik na odjava -> natrag na prijavu
}