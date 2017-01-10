package View;

import Controller.ViewDelegate;
import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
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
    
    @FXML TextField nazivSlucaja;
    @FXML TextArea opisSlucaja;
    @FXML TextField glavniOsumnjiceni;
    @FXML ComboBox statusSlucaja;
    
    @FXML ListView popisOsumnjicenih;
    @FXML Button dodajOsumnjicenog;
    @FXML Button obrisiOsumnjicenog;
    
    @FXML ListView popisSvjedoka;
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
    @FXML Label info;
    
    Stage dialogDogadaj;
    Stage dialogOsoba;
    
    Set<Osoba> setOsumnjiceni = new HashSet<>();
    Set<Osoba> setSvjedoci = new HashSet<>();
    Set<Dogadaj> setDogadaji = new HashSet<>();
   
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
    
    @FXML private void dodajOsobuOsumnjiceni() throws IOException {
        dialogOsoba = new Stage();
        dialogOsoba.initModality(Modality.APPLICATION_MODAL);
        dialogOsoba.initOwner(dodaj.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajOsobu.fxml"));
        Parent loadScreen = loader.load();
        DodajOsobuController controller = (DodajOsobuController) loader.getController();
	controller.init(this, "Osumnjiceni");
        Scene scene = new Scene(loadScreen);
        dialogOsoba.setScene(scene);
        dialogOsoba.show();
    }
    
    @Override
    public void dodajOsumnjicenog(Osoba osumnjiceni) {
        dialogOsoba.close();
        setOsumnjiceni.add(osumnjiceni);
        observableOsumnjiceni.add(osumnjiceni.getIme() + " " + osumnjiceni.getPrezime() + " " + osumnjiceni.getOib().toString());
    }
    
    @FXML private void obrisiOsumnjicenog(ActionEvent event) {
        observableOsumnjiceni.remove(popisOsumnjicenih.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajOsobuSvjedok() throws IOException {
        dialogOsoba = new Stage();
        dialogOsoba.initModality(Modality.APPLICATION_MODAL);
        dialogOsoba.initOwner(dodaj.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajOsobu.fxml"));
        Parent loadScreen = loader.load();
        DodajOsobuController controller = (DodajOsobuController) loader.getController();
	controller.init(this, "Svjedok");
        Scene scene = new Scene(loadScreen);
        dialogOsoba.setScene(scene);
        dialogOsoba.show();
    }
    
    @Override
    public void dodajSvjedoka(Osoba svjedok) {
        dialogOsoba.close();
        setSvjedoci.add(svjedok);
        observableSvjedoci.add(svjedok.getIme() + " " + svjedok.getPrezime() + " " + svjedok.getOib().toString());
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
        dialogDogadaj = new Stage();
        dialogDogadaj.initModality(Modality.APPLICATION_MODAL);
        dialogDogadaj.initOwner(dodaj.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajDogadaj.fxml"));
        Parent loadScreen = loader.load();
        DodajDogadajController controller = (DodajDogadajController) loader.getController();
	controller.init(this);
        Scene scene = new Scene(loadScreen);
        dialogDogadaj.setScene(scene);
        dialogDogadaj.show();
    }
    
    @Override
    public void dodajDogadaj(Dogadaj dogadaj) {
        dialogDogadaj.close();
        setDogadaji.add(dogadaj);
        observableDogadaji.add(dogadaj.getNaziv()+","+dogadaj.getVrijeme().toString()+","+dogadaj.getAdresa()+","+dogadaj.getPbrMjesto());
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
        
        String poruka = "Unesite: ";
        
        if (nazivSlucaja.getText() != null) {
            if (!nazivSlucaja.getText().isEmpty()) {
                slucaj.setNazivSlucaja(nazivSlucaja.getText());
            } else {
                poruka = poruka.concat("naziv slučaja; ");
            }
        }
        
        if (opisSlucaja.getText() != null) {
            if (!opisSlucaja.getText().isEmpty()) {
                slucaj.setOpis(opisSlucaja.getText());
            } else poruka = poruka.concat("opis slučaja; ");
        }
        
        if (glavniOsumnjiceni.getText() != null) {
            if (!glavniOsumnjiceni.getText().isEmpty()) {
                Osumnjiceni osumnjiceni = new Osumnjiceni();
                osumnjiceni.setOib(Integer.parseInt(glavniOsumnjiceni.getText()));
                slucaj.setGlavniOsumnjiceni(osumnjiceni);
            }
        }
        
        if (!observableFotografije.isEmpty()) {
            Set<String> fotke = new HashSet<>();
            fotke.addAll(observableFotografije);
            slucaj.setFotografijeSlučaja(fotke);
        }
        
        if (!setOsumnjiceni.isEmpty()) {
            slucaj.setPopisOsumnjicenih(setOsumnjiceni);
        }
        
        if (!setSvjedoci.isEmpty()) 
            slucaj.setPopisSvjedoka(setSvjedoci);
        
        if (!observablePolicajci.isEmpty()) {
            Set<Pozornik> policajci = new HashSet<>();
            for (String polis : observablePolicajci) {
                String[] pol = polis.split(" ");
                Pozornik polisman = new Pozornik();
                polisman.setOib(Long.parseLong(pol[2].trim()));
            }
            slucaj.setPopisPolicajaca(policajci);
        }
        
        if (statusSlucaja.getValue() != null) {
            if (statusSlucaja.getValue().equals("Riješen")) {
                slucaj.setStatus(TrenutniStatusSlucaja.riješen);
            } else if (statusSlucaja.getValue().equals("Otvoren")) {
                slucaj.setStatus(TrenutniStatusSlucaja.otvoren);
            } else if (statusSlucaja.getValue().equals("Zatvoren")) {
                slucaj.setStatus(TrenutniStatusSlucaja.zatvoren);
            }
        } else poruka = poruka.concat("status; ");
        
        if (!setDogadaji.isEmpty()) 
            slucaj.setPopisDogadaja(setDogadaji);        
        
        if (poruka.equals("Unesite: "))
            delegate.dodajSlucaj(slucaj);
        else info.setText(poruka);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      

    
}
