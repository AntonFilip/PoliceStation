package View;

import Controller.ViewDelegate;
import Model.Dokaz;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 */
public class DodajDokazController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML TextField naziv;
    @FXML TextField brojSlucaja;
    @FXML CheckBox A;
    @FXML CheckBox B;
    @FXML CheckBox AB;
    @FXML CheckBox nula;
    @FXML TextArea DNAsekvenca;
    @FXML TextArea tipOruzja;
    @FXML Button dodaj;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    @FXML private void dodaj(ActionEvent event) {
        Dokaz dokaz = new Dokaz();
        
        if (naziv.getText() != null && !naziv.getText().equals("")) 
            dokaz.setNaziv(naziv.getText());
        if (brojSlucaja.getText() != null && !brojSlucaja.getText().equals(""))
            dokaz.setBrojSlucaja(Integer.parseInt(brojSlucaja.getText()));
        
        Set<String> krvneGrupe = new HashSet<>();
        if (A.selectedProperty().get()) {
            krvneGrupe.add("A");
        }
        if (B.selectedProperty().get()) {
            krvneGrupe.add("B");
        }
        if (AB.selectedProperty().get()) {
            krvneGrupe.add("AB");
        }
        if (nula.selectedProperty().get()) {
            krvneGrupe.add("0");
        }
        dokaz.addAllKrvnaGrupa(krvneGrupe);
        
        if (!DNAsekvenca.getText().isEmpty()) {
            Set<String> sekvence = new HashSet<>();
            String[] popis = DNAsekvenca.getText().split(";");
            sekvence.addAll(Arrays.asList(popis));
            dokaz.addAllDNASekvenca(sekvence);
        }
        if (!tipOruzja.getText().isEmpty()) {
            Set<String> oruzja = new HashSet<>();
            String[] popis = tipOruzja.getText().split(";");
            oruzja.addAll(Arrays.asList(popis));
            dokaz.addAllTipOruzja(oruzja);
        }

        delegate.dodajDokaz(dokaz);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
}
