package View;

import Controller.ViewDelegate;
import Model.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Klasa koja upravlja sučeljem za postavljanje upita o dokaznom materijalu.
 */
public class UpitDokazController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML TextField naziv;
    @FXML TextField nazivSlucaja;
    @FXML CheckBox A;
    @FXML CheckBox B;
    @FXML CheckBox AB;
    @FXML CheckBox nula;
    @FXML TextArea DNAsekvenca;
    @FXML TextArea tipOruzja;
    @FXML Button posalji;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    @FXML private void postaviUpit(ActionEvent event) {
        Dokaz dokaz = new Dokaz();
        
        dokaz.setNaziv(naziv.getText());
        dokaz.setNazivSlucaja(nazivSlucaja.getText());
        
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
        
        Set<String> sekvence = new HashSet<>();
        String[] popis = DNAsekvenca.getText().split(";");
        sekvence.addAll(Arrays.asList(popis));
        dokaz.addAllDNASekvenca(sekvence);
        
        Set<String> oruzja = new HashSet<>();
        popis = tipOruzja.getText().split(";");
        oruzja.addAll(Arrays.asList(popis));
        dokaz.addAllTipOruzja(oruzja);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
