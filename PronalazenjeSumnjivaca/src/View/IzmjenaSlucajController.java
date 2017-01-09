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
 *
 * @author Karmela
 */
public class IzmjenaSlucajController implements Initializable, ControlledScreen, DialogManager {

    ViewDelegate delegate;
    
    @FXML Label info;
    @FXML Button spremi;
    
    @FXML TextField naziv;
    @FXML TextArea opis;
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
    
    @FXML ListView popisDokaza;
    @FXML TextField upisaniDokaz;
    @FXML Button dodajDokaz;
    @FXML Button obrisiDokaz;
    
    @FXML ListView popisPolicajaca; 
    @FXML TextField upisaniPolicajac;
    @FXML Button dodajPolicajca;
    @FXML Button obrisiPolicajca;
    
    @FXML ListView popisDogadaja;
    @FXML TextField upisaniDogadaj;
    @FXML Button dodajDogadaj;
    @FXML Button obrisiDogadaj;
    
    @FXML ListView fotografijeSlucaja;
    @FXML TextField upisaniURL;
    @FXML Button dodajURL;
    @FXML Button obrisiURL;
    
    Stage dialog;
    
    ObservableList<String> observableOsumnjiceni = FXCollections.observableArrayList();
    ObservableList<String> observableSvjedoci = FXCollections.observableArrayList();
    ObservableList<String> observableDokazi = FXCollections.observableArrayList();
    ObservableList<String> observablePolicajci = FXCollections.observableArrayList();
    ObservableList<String> observableDogadaji = FXCollections.observableArrayList();
    ObservableList<String> observableFotografije = FXCollections.observableArrayList();
    
    Slucaj izmijenjeniSlucaj;
    
    Set<String> dodaniAtributi = new HashSet<>();
    Set<String> obrisaniAtributi = new HashSet<>();
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void postaviTrenutnePodatke(Slucaj slucaj) {
        
        if (slucaj.getNazivSlucaja() != null) 
            naziv.setText(slucaj.getNazivSlucaja());
        if (slucaj.getOpis() != null)
            opis.setText(slucaj.getOpis());
        if (slucaj.getGlavniOsumnjiceni() != null)
            glavniOsumnjiceni.setText(slucaj.getGlavniOsumnjiceni().getIme() + " " 
                + slucaj.getGlavniOsumnjiceni().getPrezime() + " " + slucaj.getGlavniOsumnjiceni().getOib().toString());
        
        if (statusSlucaja.getValue() != null) {
            if (statusSlucaja.getValue().equals("Riješen")) {
                slucaj.setStatus(TrenutniStatusSlucaja.riješen);
            } else if (statusSlucaja.getValue().equals("Otvoren")) {
                slucaj.setStatus(TrenutniStatusSlucaja.otvoren);
            } else if (statusSlucaja.getValue().equals("Zatvoren")) {
                slucaj.setStatus(TrenutniStatusSlucaja.zatvoren);
            }
        }
        
        List<Osoba> listaOsumnjicenih = new ArrayList<>();
        listaOsumnjicenih.addAll(slucaj.getPopisOsumnjicenih());
        for (Osoba krimi : listaOsumnjicenih) {
            observableOsumnjiceni.add(krimi.getIme() + " " + krimi.getPrezime() + " " + krimi.getOib());
        }
        popisOsumnjicenih.setItems(observableOsumnjiceni);
        
        
        List<Osoba> listaSvjedoka = new ArrayList<>();
        listaSvjedoka.addAll(slucaj.getPopisSvjedoka());
        for (Osoba svjedok : listaSvjedoka) {
            observableSvjedoci.add(svjedok.getIme() + " " + svjedok.getPrezime() + " " + svjedok.getOib());
        }
        popisSvjedoka.setItems(observableSvjedoci);
        
        List<Dokaz> listaDokaza = new ArrayList<>();
        listaDokaza.addAll(slucaj.getPopisDokaza());
        for (Dokaz dokaz : listaDokaza) {
            observableDokazi.add(dokaz.getID() + " " + dokaz.getNaziv());
        }
        popisDokaza.setItems(observableDokazi);
        
        List<Osoba> listaPolicajaca = new ArrayList<>();
        listaPolicajaca.addAll(slucaj.getPopisPolicajaca());
        for (Osoba policajac : listaPolicajaca) {
            observablePolicajci.add(policajac.getIme() + " " + policajac.getPrezime() + " " + policajac.getOib());
        }
        popisPolicajaca.setItems(observablePolicajci); 
        
        List<Dogadaj> listaDogadaja = new ArrayList<>();
        listaDogadaja.addAll(slucaj.getPopisDogadaja());
        for (Dogadaj dogadaj : listaDogadaja) {
            observableDogadaji.add(dogadaj.getDogadajID() + " " + dogadaj.getNaziv());
        }
        popisDogadaja.setItems(observableDogadaji);
        
        observableFotografije.addAll(slucaj.getFotografijeSlučaja());
        fotografijeSlucaja.setItems(observableFotografije);
    }
    
    @FXML private void dodajOsumnjicenog(ActionEvent event) {
        observableOsumnjiceni.add(upisaniOsumnjiceni.getText());
        dodaniAtributi.add(Slucaj.izmjenaOsumnjicenih(upisaniOsumnjiceni.getText()));
        upisaniOsumnjiceni.clear();    
    }
    
    @FXML private void obrisiOsumnjicenog(ActionEvent event) {
        obrisaniAtributi.add(Slucaj.izmjenaOsumnjicenih(popisOsumnjicenih.getSelectionModel().getSelectedItem().toString()));
        observableOsumnjiceni.remove(popisOsumnjicenih.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajSvjedoka(ActionEvent event) {
        observableSvjedoci.add(upisaniSvjedok.getText());
        dodaniAtributi.add(Slucaj.izmjenaSvjedoka(upisaniSvjedok.getText()));
        upisaniSvjedok.clear();
    }
    
    @FXML private void obrisiSvjedoka(ActionEvent event) {
        obrisaniAtributi.add(Slucaj.izmjenaSvjedoka(popisSvjedoka.getSelectionModel().getSelectedItem().toString()));
        observableSvjedoci.remove(popisSvjedoka.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajDokaz(ActionEvent event) {
        observableDokazi.add(upisaniDokaz.getText());
        dodaniAtributi.add(Slucaj.izmjenaDokaza(upisaniDokaz.getText()));
        upisaniDokaz.clear();
    }
    
    @FXML private void obrisiDokaz(ActionEvent event) {
        obrisaniAtributi.add(Slucaj.izmjenaDokaza(popisDokaza.getSelectionModel().getSelectedItem().toString()));
        observableDokazi.remove(popisDokaza.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajPolicajca(ActionEvent event) {
        observablePolicajci.add(upisaniPolicajac.getText());
        dodaniAtributi.add(Slucaj.izmjenaPolicajca(upisaniPolicajac.getText()));
        upisaniPolicajac.clear();
    }
    
    @FXML private void obrisiPolicajca(ActionEvent event) {
        obrisaniAtributi.add(Slucaj.izmjenaPolicajca(popisPolicajaca.getSelectionModel().getSelectedItem().toString()));
        observablePolicajci.remove(popisPolicajaca.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void postaviDialogDogadaj(ActionEvent event) throws IOException {
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(dodajDogadaj.getScene().getWindow());
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
        dialog.close();
        Model.PristupBaziPodataka.dodajNoviDogadaj(dogadaj);
        observableDogadaji.add(dogadaj.getNaziv()+","+dogadaj.getVrijeme().toString()+","+dogadaj.getAdresa()+","+dogadaj.getPbrMjesto());
        dodaniAtributi.add(Slucaj.izmjenaDogađaja(dogadaj.getNaziv()+","+dogadaj.getVrijeme().toString()+","+dogadaj.getAdresa()+","+dogadaj.getPbrMjesto()));
    }
    
    @FXML private void obrisiDogadaj(ActionEvent event) {
        obrisaniAtributi.add(Slucaj.izmjenaDogađaja(popisDogadaja.getSelectionModel().getSelectedItem().toString()));
        observableDogadaji.remove(popisDogadaja.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajFotografiju(ActionEvent event) {
        observableFotografije.add(upisaniURL.getText());
        dodaniAtributi.add(Slucaj.izmjenaFotografija(upisaniURL.getText()));
        upisaniURL.clear();
    }
    
    @FXML private void obrisiFotografiju(ActionEvent event) {
        obrisaniAtributi.add(Slucaj.izmjenaFotografija(fotografijeSlucaja.getSelectionModel().getSelectedItem().toString()));
        observableFotografije.remove(fotografijeSlucaja.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void spremiIzmjene(ActionEvent event) {
        
        String poruka = "Unesite: ";
        
        if (naziv.getText() != null) 
            if (!naziv.getText().isEmpty())
                izmijenjeniSlucaj.setNazivSlucaja(naziv.getText());
            else 
                poruka = poruka.concat("naziv; ");
        
        if (opis.getText() != null) 
            izmijenjeniSlucaj.setOpis(opis.getText());
        
        if (glavniOsumnjiceni.getText() != null) {
            if (!glavniOsumnjiceni.getText().isEmpty()) {
                String[] zapis = glavniOsumnjiceni.getText().split(" ");
                Osumnjiceni novi = new Osumnjiceni();
                novi.setOib(Long.parseLong(zapis[2].trim()));
                izmijenjeniSlucaj.setGlavniOsumnjiceni(novi);
            }
        }
        
        if (statusSlucaja.getValue() != null) {
            if (statusSlucaja.getValue().equals("Riješen")) {
                izmijenjeniSlucaj.setStatus(TrenutniStatusSlucaja.riješen);
            } else if (statusSlucaja.getValue().equals("Otvoren")) {
                izmijenjeniSlucaj.setStatus(TrenutniStatusSlucaja.otvoren);
            } else if (statusSlucaja.getValue().equals("Zatvoren")) {
                izmijenjeniSlucaj.setStatus(TrenutniStatusSlucaja.zatvoren);
            }
        }
//        Set<String> fotografije = new HashSet<>();
//        fotografije.addAll(observableFotografije);
//        slucaj.setFotografijeSlučaja(fotografije);
//        
//        Set<Dogadaj> dogadaji = new HashSet<>();
//        for (String dogadaj : observableDogadaji) {
//            String[] dog = dogadaj.split(" ");
//            Dogadaj noviDogadaj = new Dogadaj();
//            noviDogadaj.setDogadajID(Integer.parseInt(dog[0].trim()));
//        }
//        slucaj.setPopisDogadaja(dogadaji);
//        
//        Set<Dokaz> dokazi = new HashSet<>();
//        for (String dokaz : observableDokazi) {
//            String[] dok = dokaz.split(" ");
//            Dokaz noviDokaz = new Dokaz();
//            noviDokaz.setID(Integer.parseInt(dok[0].trim()));
//        }
//        slucaj.setPopisDokaza(dokazi);
//        
//        Set<Osoba> osumnjiceni = new HashSet<>();
//        for (String krimi : observableOsumnjiceni) {
//            String[] krim = krimi.split(" ");
//            Osoba kriminalac = new Osoba();
//            kriminalac.setOib(Long.parseLong(krim[2].trim()));
//        }
//        slucaj.setPopisOsumnjicenih(osumnjiceni);
//        
//        Set<Pozornik> policajci = new HashSet<>();
//        for (String polis : observablePolicajci) {
//            String[] pol = polis.split(" ");
//            Pozornik polisman = new Pozornik();
//            polisman.setOib(Long.parseLong(pol[2].trim()));
//        }
//        slucaj.setPopisPolicajaca(policajci);
//        
//        Set<Osoba> svjedoci = new HashSet<>();
//        for (String svjedok : observableSvjedoci) {
//            String[] svj = svjedok.split(" ");
//            Osoba jadniSvjedok = new Osoba();
//            jadniSvjedok.setOib(Long.parseLong(svj[2].trim()));
//        }
//        slucaj.setPopisSvjedoka(svjedoci);
        if (poruka.equals("Unesite: ")) 
            delegate.spremiIzmjeneSlucaja(izmijenjeniSlucaj, dodaniAtributi, obrisaniAtributi);
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
