package View;

import java.net.URL;
import java.util.ResourceBundle;

import Model.AdresaIMjestoStanovanja;
import Model.Osoba;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Karmela
 */
public class DodajOsobuController implements Initializable {

    DialogManager delegate;
    String tipOsobe;
    
    @FXML TextField ime;
    @FXML TextField prezime;
    @FXML TextField oib;
    @FXML TextField ulica;
    @FXML TextField mjesto;
    @FXML TextField pbr;
    
    @FXML Button dodajOsobu;
    @FXML Label info;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML private void dodajOsobu() {
        
        Osoba osoba = new Osoba();
        
        String poruka = "Unesite: ";
        
        if (ime.getText() != null) {
            if (!ime.getText().isEmpty()) {
                osoba.setIme(ime.getText());
            } else {
                poruka = poruka.concat("ime; ");
            }
        }
        
        if (prezime.getText() != null) {
            if (!prezime.getText().isEmpty()) {
                osoba.setPrezime(prezime.getText());
            } else {
                poruka = poruka.concat("prezime; ");
            }
        }
        
        if (oib.getText() != null) {
            if (!oib.getText().isEmpty()) {
                osoba.setOib(Long.parseLong(oib.getText()));
            } else {
                poruka = poruka.concat("oib; ");
            }
        }
        
        AdresaIMjestoStanovanja adresa = new AdresaIMjestoStanovanja();
        
        if (ulica.getText() != null) {
            if (!ulica.getText().isEmpty()) {
                adresa.setAdresa(ulica.getText());
            } else {
                poruka = poruka.concat("ulica; ");
            }
        } else {
                poruka = poruka.concat("ulica; ");
            }
        
        if (mjesto.getText() != null) {
            if (!mjesto.getText().isEmpty()) {
                adresa.setNazivMjesta(mjesto.getText());
            } else {
                poruka = poruka.concat("mjesto; ");
            }
        }
        
        if (pbr.getText() != null) {
            if (!pbr.getText().isEmpty()) {
                adresa.setPbrMjesto(Integer.parseInt(pbr.getText()));
            } else {
                poruka = poruka.concat("poštanski broj; ");
            }
        }
            
        if (poruka.equals("Unesite: ")) {
            if (tipOsobe.equals("Osumnjiceni")) {
                delegate.dodajOsumnjicenog(osoba);
            } else if (tipOsobe.equals("Svjedok")) {
                delegate.dodajSvjedoka(osoba);
            }
        } else {
            info.setText(poruka);
        }
    }
    
    public void init(DialogManager delegate, String tipOsobe) {
        this.tipOsobe = tipOsobe;
        this.delegate = delegate;
    }
    
}
