package View;

import Controller.RazinaPristupa;
import java.util.Map;

/**
 *
 * @author Karmela
 */
public interface ShowScene {
    public void postaviScenuPrijava(); // pocetak
    public void prikaziGlavniIzbornik(String ime, String prezime, RazinaPristupa razina); // uspjesna prijava -> ime, prezime, razina trenutnog korisnika (atribut u controlleru)
    public void postaviScenuUpitKriminalac(); // klik na postavi upit o kriminalcu
    public void postaviScenuUpitSlucaj(); // klik na postavi upit o slucaju
    public void postaviScenuUpitDokaz(); // klik na postavi upit o dokazu
    public void postaviScenuPopis(String predmet, Map<String,Integer> popis); // predmet: kriminalac, slucaj ili dokaz
    public void postaviScenuIzmjeneKriminalca(); // klik na izmjenu kriminalca
    public void postaviScenuIzmjeneSlucaja(); // klik na izmjenu slucaja
    public void postaviScenuIzmjeneDokaza(); // klik na izmjenu dokaza
    public void postaviScenuDodajKriminalca(); // klik na dodaj krimija
    public void postaviScenuDodajSlucaj(); // klik na dodaj slucaj
    public void postaviScenuDodajDokaz(); //klik na dodaj dokaz iz scene za dodavanje slucaja
}
