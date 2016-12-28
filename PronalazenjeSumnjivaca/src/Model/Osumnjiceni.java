package Model;

import java.util.HashSet;
import java.util.List;

public class Osumnjiceni extends Osoba {

    private HashSet<String> popisAliasa;
    private String adresa;
    private HashSet<String> poznateAdrese;
    private String brojTelefona;
    private HashSet<Slucaj> povezaniSlucajevi;
    private FizickeOsobine fizickeOsobine;
    private KarakterneOsobine karakterneOsobine;
    private String opisKriminalnihDjelatnosti;
    private HashSet<Osumnjiceni> popisPovezanihKriminalaca;
    private TrenutniStatusKriminalca status;
    private List<String> fotografije;

    public Osumnjiceni() {
    }

    public HashSet<String> getPopisAliasa() {
        return popisAliasa;
    }

    public String getAdresa() {
        return adresa;
    }

    public HashSet<String> getPoznateAdrese() {
        return poznateAdrese;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public HashSet<Slucaj> getPovezaniSlucajevi() {
        return povezaniSlucajevi;
    }

    public FizickeOsobine getFizickeOsobine() {
        return fizickeOsobine;
    }

    public KarakterneOsobine getKarakterneOsobine() {
        return karakterneOsobine;
    }

    public String getOpisKriminalnihDjelatnosti() {
        return opisKriminalnihDjelatnosti;
    }

    public HashSet<Osumnjiceni> getPopisPovezanihKriminalaca() {
        return popisPovezanihKriminalaca;
    }

    public TrenutniStatusKriminalca getStatus() {
        return status;
    }

    public List<String> getFotografije() {
        return fotografije;
    }

    public void setPopisAliasa(HashSet<String> popisAliasa) {
        this.popisAliasa = popisAliasa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setPoznateAdrese(HashSet<String> poznateAdrese) {
        this.poznateAdrese = poznateAdrese;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setPovezaniSlucajevi(HashSet<Slucaj> povezaniSlucajevi) {
        this.povezaniSlucajevi = povezaniSlucajevi;
    }

    public void setFizickeOsobine(FizickeOsobine fizickeOsobine) {
        this.fizickeOsobine = fizickeOsobine;
    }

    public void setKarakterneOsobine(KarakterneOsobine karakterneOsobine) {
        this.karakterneOsobine = karakterneOsobine;
    }

    public void setOpisKriminalnihDjelatnosti(String opisKriminalnihDjelatnosti) {
        this.opisKriminalnihDjelatnosti = opisKriminalnihDjelatnosti;
    }

    public void setPopisPovezanihKriminalaca(HashSet<Osumnjiceni> popisPovezanihKriminalaca) {
        this.popisPovezanihKriminalaca = popisPovezanihKriminalaca;
    }

    public void setStatus(TrenutniStatusKriminalca status) {
        this.status = status;
    }

    public void setFotografije(List<String> fotografije) {
        this.fotografije = fotografije;
    }

}
