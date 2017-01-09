package View;

import Controller.ViewDelegate;
import Model.Dokaz;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 */
public class DodajDokazController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML Label info;
    
    @FXML TextField naziv;
    @FXML TextField brojSlucaja;
    @FXML CheckBox A;
    @FXML CheckBox B;
    @FXML CheckBox AB;
    @FXML CheckBox nula;
    @FXML TextArea DNAsekvenca;
    @FXML TextArea tipOruzja;
    @FXML Button dodaj;
    
    @FXML ListView listaOtisaka;
    @FXML TextField upisaniOtisak;
    @FXML Button dodajOtisak;
    @FXML Button obrisiOtisak;

    @FXML TextField fotografijaURL;
    
    ObservableList<String> observableOtisci = FXCollections.observableArrayList();
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
        listaOtisaka.setItems(observableOtisci);
    }
    
    @FXML private void dodaj(ActionEvent event) {
        Dokaz dokaz = new Dokaz();
        
        String poruka = "Unesite: ";
        
        if (naziv.getText() != null) {
            if (!naziv.getText().equals("")) {
                dokaz.setNaziv(naziv.getText());
            } else {
                poruka.concat("naziv; ");
            }
        }
        if (brojSlucaja.getText() != null) {
            if(!brojSlucaja.getText().equals("")) {
                dokaz.setBrojSlucaja(Integer.parseInt(brojSlucaja.getText()));
            } else {
                poruka.concat("broj slučaja; ");
            }
        }
        
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
        
        if (!observableOtisci.isEmpty()) {
            Set<String> otisci = new HashSet<>();
            otisci.addAll(observableOtisci);
            dokaz.setOtisakPrsta(otisci);
        }
        if (fotografijaURL.getText() != null) {
            if (!fotografijaURL.getText().isEmpty()) {
                dokaz.setFotografija(fotografijaURL.getText());
            }
        }
        
        if (poruka.equals("Unesite: ")) 
            delegate.dodajDokaz(dokaz);      
        else info.setText(poruka);
       
    }
    
    @FXML private void dodajOtisakPrsta(ActionEvent event) {
        observableOtisci.add(upisaniOtisak.getText());
        upisaniOtisak.clear();
    }
    
    @FXML private void obrisiOtisakPrsta(ActionEvent event) {
        observableOtisci.remove(listaOtisaka.getSelectionModel().getSelectedIndex());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
}
