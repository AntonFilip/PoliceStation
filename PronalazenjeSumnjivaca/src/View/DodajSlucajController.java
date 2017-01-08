package View;

import Controller.ViewDelegate;
import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class DodajSlucajController implements Initializable, ControlledScreen, DialogManager {
    
    ViewDelegate delegate;
    
    @FXML TextField brojSlucaja;
    @FXML TextField nazivSlucaja;
    @FXML TextArea opisSlucaja;
    @FXML TextField glavniOsumnjiceni;
    @FXML ComboBox statusSlucaja;
    
    @FXML ListView popisOsumnjicenih;
    @FXML TextField upisaniOsumnjiceni;
    @FXML Button dodajOsumnjicenog;
    @FXML Button obrisiOsumnjicenog;
    
    @FXML ListView popisSvjedoka;
    @FXML TextField upisaniSvjedok;
    @FXML Button dodajSvjedoka;
    @FXML Button obrisiSvjedoka;
    
    @FXML ListView popisPolicajaca;
    @FXML TextField upisaniPolicajac;
    @FXML Button dodajPolicajca;
    @FXML Button obrisiPolicajca;
    
    @FXML ListView popisDogadaja;
    @FXML Button dodajDogadaj;
    @FXML Button obrisiDogadaj;
    
    @FXML ListView fotografije;
    @FXML TextField upisaniURL;
    @FXML Button dodajURL;
    @FXML Button obrisiURL;
    
    @FXML Button dodaj;
   
    ObservableList<String> observableOsumnjiceni = FXCollections.observableArrayList();
    ObservableList<String> observableSvjedoci = FXCollections.observableArrayList();
    ObservableList<String> observablePolicajci = FXCollections.observableArrayList();
    ObservableList<String> observableDogadaji = FXCollections.observableArrayList();
    ObservableList<String> observableFotografije = FXCollections.observableArrayList();
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
        popisOsumnjicenih.setItems(observableOsumnjiceni);
        popisSvjedoka.setItems(observableSvjedoci);
        popisPolicajaca.setItems(observablePolicajci);
        popisDogadaja.setItems(observableDogadaji);
        fotografije.setItems(observableFotografije);
    }
    
    @FXML private void dodajOsumnjicenog(ActionEvent event) {
        observableOsumnjiceni.add(upisaniOsumnjiceni.getText());
        upisaniOsumnjiceni.clear();    
    }
    
    @FXML private void obrisiOsumnjicenog(ActionEvent event) {
        observableOsumnjiceni.remove(popisOsumnjicenih.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajSvjedoka(ActionEvent event) {
        observableSvjedoci.add(upisaniSvjedok.getText());
        upisaniSvjedok.clear();
    }
    
    @FXML private void obrisiSvjedoka(ActionEvent event) {
        observableSvjedoci.remove(popisSvjedoka.getSelectionModel().getSelectedIndex());
    }
    
     @FXML private void dodajPolicajca(ActionEvent event) {
        observablePolicajci.add(upisaniPolicajac.getText());
        upisaniPolicajac.clear();
    }
    
    @FXML private void obrisiPolicajca(ActionEvent event) {
        observablePolicajci.remove(popisPolicajaca.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void postaviDialogDogadaj(ActionEvent event) throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(dodaj.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajDogadaj.fxml"));
        Parent loadScreen = loader.load();
        DodajDogadajController controller = (DodajDogadajController) loader.getController();
	controller.init(this);
        Scene scene = new Scene(loadScreen);
        dialog.setScene(scene);
        dialog.show();
    }
    
    @Override
    public void dodajDogadaj(Dogadaj dogadaj) {
        observableDogadaji.add(dogadaj.getDogadajID() + " " + dogadaj.getNaziv());
    }
    
    @FXML private void obrisiDogadaj(ActionEvent event) {
        observableDogadaji.remove(popisDogadaja.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajURL(ActionEvent event) {
        observableFotografije.add(upisaniURL.getText());
        upisaniURL.clear();
    }
    
    @FXML private void obrisiURL(ActionEvent event) {
        observableFotografije.remove(fotografije.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodaj(ActionEvent event) {
        Slucaj slucaj = new Slucaj();
        
        slucaj.setBrojSlucaja(Integer.parseInt(brojSlucaja.getText()));
        slucaj.setNazivSlucaja(nazivSlucaja.getText());
        slucaj.setOpis(opisSlucaja.getText());
        
        Osumnjiceni osumnjiceni = new Osumnjiceni();
        osumnjiceni.setOib(Integer.parseInt(glavniOsumnjiceni.getText()));
        slucaj.setGlavniOsumnjiceni(osumnjiceni);
        
        Set<String> fotke = new HashSet<>();
        fotke.addAll(observableFotografije);
        slucaj.setFotografijeSlučaja(fotke);
        
        Set<Osoba> popisOsumnjiceni = new HashSet<>();
        for (String krimi : observableOsumnjiceni) {
            String[] krim = krimi.split(" ");
            Osoba kriminalac = new Osoba();
            kriminalac.setOib(Long.parseLong(krim[2].trim()));
        }
        slucaj.setPopisOsumnjicenih(popisOsumnjiceni);
        
        Set<Osoba> svjedoci = new HashSet<>();
        for (String svjedok : observableSvjedoci) {
            String[] svj = svjedok.split(" ");
            Osoba jadniSvjedok = new Osoba();
            jadniSvjedok.setOib(Long.parseLong(svj[2].trim()));
        }
        slucaj.setPopisSvjedoka(svjedoci);
        
        Set<Pozornik> policajci = new HashSet<>();
        for (String polis : observablePolicajci) {
            String[] pol = polis.split(" ");
            Pozornik polisman = new Pozornik();
            polisman.setOib(Long.parseLong(pol[2].trim()));
        }
        slucaj.setPopisPolicajaca(policajci);
        
        if (statusSlucaja.getValue().equals("Riješen")) {
            slucaj.setStatus(TrenutniStatusSlucaja.riješen);
        } else if (statusSlucaja.getValue().equals("Otvoren")) {
            slucaj.setStatus(TrenutniStatusSlucaja.otvoren);
        } else if (statusSlucaja.getValue().equals("Zatvoren")) {
            slucaj.setStatus(TrenutniStatusSlucaja.zatvoren);
        }
        
        Set<Dogadaj> dogadaji = new HashSet<>();
        for (String dogadaj : observableDogadaji) {
            String[] dog = dogadaj.split(" ");
            Dogadaj noviDogadaj = new Dogadaj();
            noviDogadaj.setDogadajID(Integer.parseInt(dog[0].trim()));
            
        }
        slucaj.setPopisDogadaja(dogadaji);
        
        delegate.dodajSlucaj(slucaj);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      

    
}
