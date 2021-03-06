package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import Controller.ViewDelegate;
import Model.Dogadaj;
import Model.Dokaz;
import Model.Osoba;
import Model.Osumnjiceni;
import Model.Pozornik;
import Model.PristupBaziPodataka;
import Model.Slucaj;
import Model.TrenutniStatusSlucaja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    @FXML ComboBox<String> statusSlucaja;

    @FXML ListView<String> popisOsumnjicenih;
    @FXML Button dodajOsumnjicenog;
    @FXML Button obrisiOsumnjicenog;

    @FXML ListView<String> popisSvjedoka;
    @FXML Button dodajSvjedoka;
    @FXML Button obrisiSvjedoka;

    @FXML ListView<String> popisDokaza;
    @FXML TextField upisaniDokaz;
    @FXML Button dodajDokaz;
    @FXML Button obrisiDokaz;

    @FXML ListView<String> popisPolicajaca;
    @FXML TextField upisaniPolicajac;
    @FXML Button dodajPolicajca;
    @FXML Button obrisiPolicajca;
    @FXML Label labelaPolicajci;

    @FXML ListView<String> popisDogadaja;
    @FXML Button dodajDogadaj;
    @FXML Button obrisiDogadaj;

    @FXML ListView<String> fotografijeSlucaja;
    @FXML TextField upisaniURL;
    @FXML Button dodajURL;
    @FXML Button obrisiURL;

    Stage dialogDogadaj;
    Stage dialogOsoba;

    ObservableList<String> observableOsumnjiceni = FXCollections.observableArrayList();
    ObservableList<String> observableSvjedoci = FXCollections.observableArrayList();
    ObservableList<String> observableDokazi = FXCollections.observableArrayList();
    ObservableList<String> observablePolicajci = FXCollections.observableArrayList();
    ObservableList<String> observableDogadaji = FXCollections.observableArrayList();
    ObservableList<String> observableFotografije = FXCollections.observableArrayList();

    Slucaj izmijenjeniSlucaj;
    Slucaj stariSlucaj;

    Set<String> dodaniAtributi = new HashSet<>();
    Set<String> obrisaniAtributi = new HashSet<>();

    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void postaviTrenutnePodatke(Slucaj slucaj) {
        
        stariSlucaj = slucaj;
        
        if (slucaj.getNazivSlucaja() != null) {
            naziv.setText(slucaj.getNazivSlucaja());
        }
        if (slucaj.getOpis() != null) {
            opis.setText(slucaj.getOpis());
        }
        if (slucaj.getGlavniOsumnjiceni() != null) {
            if (slucaj.getGlavniOsumnjiceni().getOib() != null) {
                glavniOsumnjiceni.setText(slucaj.getGlavniOsumnjiceni().getOib().toString());
            }
        }
        if (slucaj.getStatus() != null) {
            statusSlucaja.setValue(slucaj.getStatus().toString().substring(0, 1).toUpperCase() + slucaj.getStatus().toString().substring(1));
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

        List<Pozornik> listaPolicajaca = new ArrayList<>();
        listaPolicajaca.addAll(slucaj.getPopisPolicajaca());
        if (!listaPolicajaca.isEmpty()) {
            for (Pozornik policajac : listaPolicajaca) {
                System.out.println(policajac);
                try {
                    if (policajac != null) {
                        observablePolicajci.add(policajac.getIme() + " " + policajac.getPrezime() + " " + policajac.getJedinstveniBroj().toString());
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
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

    @FXML
    private void dodajOsobuOsumnjiceni() throws IOException {
        info.setText("");
        dialogOsoba = new Stage();
        dialogOsoba.initModality(Modality.APPLICATION_MODAL);
        dialogOsoba.initOwner(spremi.getScene().getWindow());
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
        String noviOsumnjiceni = osumnjiceni.getIme() + " " + osumnjiceni.getPrezime() + " " + osumnjiceni.getOib().toString();
        if (popisOsumnjicenih.getItems().contains(noviOsumnjiceni)) {
            info.setText("Unijeli ste osobu koja je već osumnjičena u ovom slučaju.");
        } else {
            info.setText("");
            observableOsumnjiceni.add(noviOsumnjiceni);
            dodaniAtributi.add(Slucaj.izmjenaOsumnjicenih(osumnjiceni.getOib().toString()));
        }
    }

    @FXML
    private void obrisiOsumnjicenog(ActionEvent event) {
        info.setText("");
        obrisaniAtributi.add(Slucaj.izmjenaOsumnjicenih(popisOsumnjicenih.getSelectionModel().getSelectedItem()));
        observableOsumnjiceni.remove(popisOsumnjicenih.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void dodajOsobuSvjedok() throws IOException {
        info.setText("");
        dialogOsoba = new Stage();
        dialogOsoba.initModality(Modality.APPLICATION_MODAL);
        dialogOsoba.initOwner(spremi.getScene().getWindow());
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
        String noviSvjedok = svjedok.getIme() + " " + svjedok.getPrezime() + " " + svjedok.getOib().toString();
        if (popisSvjedoka.getItems().contains(noviSvjedok)) {
            info.setText("Unijeli ste svjedoka koji je već pridružen ovom slučaju.");
        } else {
            info.setText("");
            observableSvjedoci.add(noviSvjedok);
            dodaniAtributi.add(Slucaj.izmjenaSvjedoka(svjedok.getOib().toString()));
        }
    }

    @FXML
    private void obrisiSvjedoka() {
        info.setText("");
        obrisaniAtributi.add(Slucaj.izmjenaSvjedoka(popisSvjedoka.getSelectionModel().getSelectedItem()));
        observableSvjedoci.remove(popisSvjedoka.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void dodajDokaz(ActionEvent event) {
        info.setText("");
        observableDokazi.add(upisaniDokaz.getText());
        dodaniAtributi.add(Slucaj.izmjenaDokaza(upisaniDokaz.getText()));
        upisaniDokaz.clear();
        info.setText("");
    }

    @FXML
    private void obrisiDokaz(ActionEvent event) {
        info.setText("");
        obrisaniAtributi.add(Slucaj.izmjenaDokaza(popisDokaza.getSelectionModel().getSelectedItem()));
        observableDokazi.remove(popisDokaza.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void dodajPolicajca(ActionEvent event) {
        String brPol = upisaniPolicajac.getText();
        String provjera = PristupBaziPodataka.provjeriPolicajca(brPol);
        if (!provjera.equals("nema")) {
            if (observablePolicajci.contains(provjera)) {
                info.setText("Unijeli ste policajca koji već radi na ovom slučaju.");
            } else {
                info.setText("");
                upisaniPolicajac.clear();
                observablePolicajci.add(provjera);
                dodaniAtributi.add(Slucaj.izmjenaPolicajca(brPol));
            }
        } else {
            info.setText("Ne postoji policajac s jedinstvenim brojem: " + brPol + ".");
        }
    }

    @FXML
    private void obrisiPolicajca(ActionEvent event) {
        info.setText("");
        String[] parts = popisPolicajaca.getSelectionModel().getSelectedItem().split(" ");
        obrisaniAtributi.add(Slucaj.izmjenaPolicajca(parts[2]));
        observablePolicajci.remove(popisPolicajaca.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void postaviDialogDogadaj(ActionEvent event) throws IOException {
        info.setText("");
        dialogDogadaj = new Stage();
        dialogDogadaj.initModality(Modality.APPLICATION_MODAL);
        dialogDogadaj.initOwner(dodajDogadaj.getScene().getWindow());
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
        info.setText("");
        dialogDogadaj.close();
        observableDogadaji.add(dogadaj.getNaziv() + "," + dogadaj.getVrijeme().toString() + "," + dogadaj.getAdresa() + "," + dogadaj.getPbrMjesto());
        dodaniAtributi.add(Slucaj.izmjenaDogađaja(dogadaj.getNaziv() + "," + dogadaj.getVrijeme().toString() + "," + dogadaj.getAdresa() + "," + dogadaj.getPbrMjesto()));
    }

    @FXML
    private void obrisiDogadaj(ActionEvent event) {
        info.setText("");
        obrisaniAtributi.add(Slucaj.izmjenaDogađaja(popisDogadaja.getSelectionModel().getSelectedItem()));
        observableDogadaji.remove(popisDogadaja.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void dodajFotografiju(ActionEvent event) {
        info.setText("");
        observableFotografije.add(upisaniURL.getText());
        dodaniAtributi.add(Slucaj.izmjenaFotografija(upisaniURL.getText()));
        upisaniURL.clear();
    }

    @FXML
    private void obrisiFotografiju(ActionEvent event) {
        info.setText("");
        obrisaniAtributi.add(Slucaj.izmjenaFotografija(fotografijeSlucaja.getSelectionModel().getSelectedItem()));
        observableFotografije.remove(fotografijeSlucaja.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void spremiIzmjene(ActionEvent event) {

        String poruka = "Unesite: ";
        izmijenjeniSlucaj = new Slucaj();
        izmijenjeniSlucaj.setBrojSlucaja(stariSlucaj.getBrojSlucaja());
        Boolean glavniok = true;

        if (naziv.getText() != null) {
            if (!naziv.getText().isEmpty()) {
                izmijenjeniSlucaj.setNazivSlucaja(naziv.getText());
            } else {
                poruka = poruka.concat("naziv; ");
            }
        }

        if (opis.getText() != null) {
            izmijenjeniSlucaj.setOpis(opis.getText());
        } else {
            poruka = poruka.concat("opis; ");
        }

        if (glavniOsumnjiceni.getText() != null) {
            if (!glavniOsumnjiceni.getText().isEmpty()) {
                Osumnjiceni novi = new Osumnjiceni();
                try {
                    novi.setOib(Long.parseLong(glavniOsumnjiceni.getText()));
                    if (PristupBaziPodataka.provjeriOibOsobe(glavniOsumnjiceni.getText())) {
                        izmijenjeniSlucaj.setGlavniOsumnjiceni(novi);
                        info.setText("");
                    } else {
                        info.setText("U bazi ne postoji osoba s oibom: " + glavniOsumnjiceni.getText() + ".");
                        glavniok = false;
                    }
                } catch (Exception e) {
                    glavniok = false;
                }
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
        } else {
            poruka = poruka.concat("status; ");
        }

        if (poruka.equals("Unesite: ") && glavniok) {
            delegate.spremiIzmjeneSlucaja(stariSlucaj, izmijenjeniSlucaj, dodaniAtributi, obrisaniAtributi);
        } else {
            String ispisGreske = "";
            if (!poruka.equals("Unesite: ")) {
                ispisGreske += poruka;
            }
            if (!glavniok) {
                if (!ispisGreske.equals("")) {
                    ispisGreske += "\n";
                }
                ispisGreske += "U bazi ne postoji osoba s oibom: " + glavniOsumnjiceni.getText() + ".";
            }
            info.setText(ispisGreske);
        }

    }
    
    public void disablePopisPolicajaca() {
        popisPolicajaca.setVisible(false);
        upisaniPolicajac.setVisible(false);
        dodajPolicajca.setVisible(false);
        obrisiPolicajca.setVisible(false);
        labelaPolicajci.setVisible(false);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
