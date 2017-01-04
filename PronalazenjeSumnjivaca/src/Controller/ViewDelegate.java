package Controller;

import Model.*;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;

public interface ViewDelegate {
	
        public void prijava(String korisnickoIme, String lozinka) throws IOException;// klik na prijava 
        public void posaljiUpitKriminalac(Osumnjiceni kriminalac); // upisani podaci -> klik na posalji upit      
        public void posaljiUpitSlucaj(Slucaj slucaj); // upisani podaci -> klik na posalji upit     
        public void posaljiUpitDokaz(Dokaz dokaz); // upisani podaci -> klik na posalji upit     
        public void odaberiStavku(String odabir); // popis -> klik na kriminalca/slucaj/dokaz
        public void prikaziPodatkeKriminalca(Osumnjiceni kriminalac);
        public void prikaziPodatkeSlucaja(Slucaj slucaj);  
        public void prikaziPodatkeDokaza(Dokaz dokaz);
        public void ispisPDF(); // prikaz podataka -> klik na ispis -> generiranje PDF-a      
        public void spremiIzmjeneKriminalca(Osumnjiceni kriminalac); // upisani podaci -> klik na spremi izmjene   
        public void spremiIzmjeneSlucaja(Slucaj slucaj); // upisani podaci -> klik na spremi izmjene
        public void spremiIzmjeneDokaza(Dokaz dokaz); // upisani podaci -> klik na spremi izmjene
        public void dodajKriminalca(Osumnjiceni kriminalac); // upisani podaci -> klik na spremi
        public void dodajSlucaj(Slucaj slucaj); // upisani podaci -> klik na spremi
        public void dodajDokaz(Dokaz dokaz); // upisani podaci -> klik na spremi 
        public void pristupiStatistici(); // klik na statistiku
        public void pristupiDnevniku(); // klik na dnevnik
        public void odjava(); // klik na odjava -> natrag na prijavu

        
        public void postaviScenuPrijava(); // pocetak
        public void prikaziGlavniIzbornik(Pozornik pozornik); // uspjesna prijava -> ime, prezime, razina trenutnog korisnika (atribut u controlleru)
        public void postaviScenuUpit(); //klik na postavi upit (glavni izbornik)
        public void postaviScenuUpitKriminalac(); // klik na postavi upit o kriminalcu
        public void postaviScenuUpitSlucaj(); // klik na postavi upit o slucaju
        public void postaviScenuUpitDokaz(); // klik na postavi upit o dokazu
        public void postaviScenuPopis(String predmet, Map<Dokaz,Float> popis); // predmet: kriminalac, slucaj ili dokaz
        public void postaviScenuIzmjeneKriminalca(); // klik na izmjenu kriminalca
        public void postaviScenuIzmjeneSlucaja(); // klik na izmjenu slucaja
        public void postaviScenuIzmjeneDokaza(); // klik na izmjenu dokaza
        public void postaviScenuDodajKriminalca(); // klik na dodaj krimija
        public void postaviScenuDodajSlucaj(); // klik na dodaj slucaj
        public void postaviScenuDodajDokaz(); //klik na dodaj dokaz iz scene za dodavanje slucaja
        public void postaviScenuStatistika(); //klik na gumbic za statistiku
        public void postaviScenuDnevnikPretrazivanja(); // klik na gumbic za dnevnik
		
        
}