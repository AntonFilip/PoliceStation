package Controller;

import Model.*;
import java.io.IOException;

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
}