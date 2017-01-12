package View;

import Controller.ViewDelegate;
import Model.*;
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
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Karmela
 */
public class IzmjenaKriminalacController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML Button spremi;
    @FXML Label info;
    
    @FXML ListView<String> listaURL;
    @FXML Button obrisiURL;
    @FXML Button dodajURL;
    @FXML TextField upisaniURL;

    @FXML TextField adresa;
    @FXML TextField mjesto;
    @FXML TextField pbrMjesto;
    @FXML TextField brojTelefona;
    @FXML ComboBox<String> status;
    @FXML TextArea opisKriminalnihDjelatnosti;
    
    @FXML ListView<String> popisAliasa;
    @FXML Button obrisiAlias;
    @FXML Button dodajAlias;
    @FXML TextField upisaniAlias;
    
    @FXML ListView<String> poznateAdrese;
    @FXML Button obrisiAdresu;
    @FXML Button dodajAdresu;
    @FXML TextField upisanaAdresa;
    
    @FXML ListView<String> popisPovezanihSlucajeva;
    @FXML Button obrisiSlucaj;
    @FXML Button dodajSlucaj;
    @FXML TextField upisaniSlucaj;
    
    @FXML ListView<String> popisPovezanihKriminalaca;
    @FXML Button obrisiKriminalca;
    @FXML Button dodajKriminalca;
    @FXML TextField upisaniKriminalac;

    @FXML ComboBox<String> spol;
    @FXML TextField visina;
    @FXML TextField tezina;
    @FXML TextField bojaKose;
    @FXML TextField oblikFrizure;
    @FXML ComboBox<String> gradaTijela;
    
    @FXML ListView<String> tetovaze;
    @FXML Button obrisiTetovazu;
    @FXML Button dodajTetovazu;
    @FXML TextField upisanaTetovaza;
    
    @FXML ListView<String> fizickiNedostatci;
    @FXML Button obrisiNedostatak;
    @FXML Button dodajNedostatak;
    @FXML TextField upisaniNedostatak;
    
    @FXML ListView<String> bolesti;
    @FXML Button obrisiBolest;
    @FXML Button dodajBolest;
    @FXML TextField upisanaBolest;
    
    @FXML ListView<String> ostaleFizickeOsobine;
    @FXML Button obrisiFizickuOsobinu;
    @FXML Button dodajFizickuOsobinu;
    @FXML TextField upisanaFizickaOsobina;
    
    @FXML ListView<String> psiholoskiProblemi;
    @FXML Button obrisiPsiholoski;
    @FXML Button dodajPsiholoski;
    @FXML TextField upisaniPsiholoski;
    
    @FXML ListView<String> ostaleKarakterneOsobine; 
    @FXML Button obrisiKarakternuOsobinu;
    @FXML Button dodajKarakternuOsobinu;
    @FXML TextField upisanaKarakternaOsobina;
    
    ObservableList<String> observableListaURL = FXCollections.observableArrayList();
    ObservableList<String> observablePopisAliasa = FXCollections.observableArrayList();
    ObservableList<String> observableTetovaze = FXCollections.observableArrayList();
    ObservableList<String> observableBolesti = FXCollections.observableArrayList();
    ObservableList<String> observableFizickiNedostatci = FXCollections.observableArrayList();
    ObservableList<String> observableOstaleFizicke = FXCollections.observableArrayList();
    ObservableList<String> observablePsiholoski = FXCollections.observableArrayList();
    ObservableList<String> observableOstaleKarakterne = FXCollections.observableArrayList();
    ObservableList<String> observableAdrese = FXCollections.observableArrayList();
    ObservableList<String> observableSlucajevi = FXCollections.observableArrayList();
    ObservableList<String> observableKriminalci = FXCollections.observableArrayList();
    
    Osumnjiceni izmijenjeniKriminalac;
    Osumnjiceni stariOsumnjiceni;
    
    Set<String> dodaniAtributi = new HashSet<>();
    Set<String> obrisaniAtributi = new HashSet<>();
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void prikaziTrenutnePodatke(Osumnjiceni osumnjiceni) {
        stariOsumnjiceni = osumnjiceni;
        
        if (osumnjiceni.getAdresaPrebivalista().getAdresa() != null)
            adresa.setText(osumnjiceni.getAdresaPrebivalista().getAdresa());
        if (osumnjiceni.getAdresaPrebivalista().getNazivMjesta() != null)
            mjesto.setText(osumnjiceni.getAdresaPrebivalista().getNazivMjesta());
        if (osumnjiceni.getAdresaPrebivalista().getPbrMjesto() != null)
            pbrMjesto.setText(osumnjiceni.getAdresaPrebivalista().getPbrMjesto().toString());
        if (osumnjiceni.getBrojTelefona() != null)
            brojTelefona.setText(osumnjiceni.getBrojTelefona());       
        if (osumnjiceni.getStatus() != null)
            status.setValue(osumnjiceni.getStatus().toString());
        if (osumnjiceni.getOpisKriminalnihDjelatnosti() != null)
            opisKriminalnihDjelatnosti.setText(osumnjiceni.getOpisKriminalnihDjelatnosti());
        if (osumnjiceni.getFizickeOsobine().getSpol() != null)
            spol.setValue(osumnjiceni.getFizickeOsobine().getSpol().toString());
        if (osumnjiceni.getFizickeOsobine().getVisina() != null)
            visina.setText(osumnjiceni.getFizickeOsobine().getVisina().toString());
        if (osumnjiceni.getFizickeOsobine().getTezina() != null)
            tezina.setText(osumnjiceni.getFizickeOsobine().getTezina().toString());
        if (osumnjiceni.getFizickeOsobine().getBojaKose() != null)
            bojaKose.setText(osumnjiceni.getFizickeOsobine().getBojaKose());
        if (osumnjiceni.getFizickeOsobine().getOblikFrizure() != null)
            oblikFrizure.setText(osumnjiceni.getFizickeOsobine().getOblikFrizure());
        if (osumnjiceni.getFizickeOsobine().getGradaTijela() != null)
            gradaTijela.setValue(osumnjiceni.getFizickeOsobine().getGradaTijela().toString());
        
        if (!osumnjiceni.getPoznateAdrese().isEmpty()) {
            List<AdresaIMjestoStanovanja> listaAdresa = new ArrayList<>();
            listaAdresa.addAll(osumnjiceni.getPoznateAdrese());
            for (AdresaIMjestoStanovanja adresa : listaAdresa) {
                observableAdrese.add(adresa.getAdresa() + ", " + adresa.getNazivMjesta());
            }
        }
        poznateAdrese.setItems(observableAdrese);
        
        if (!osumnjiceni.getPovezaniSlucajevi().isEmpty()) {
            List<Slucaj> listaSlucajeva = new ArrayList<>();
            listaSlucajeva.addAll(osumnjiceni.getPovezaniSlucajevi());
            for (Slucaj slucaj : listaSlucajeva) {
                observableSlucajevi.add(slucaj.getBrojSlucaja() + " - " + slucaj.getNazivSlucaja());
            }
        }
        popisPovezanihSlucajeva.setItems(observableSlucajevi);
        
        if (!osumnjiceni.getPopisPovezanihKriminalaca().isEmpty()) {
            List<Osumnjiceni> listaKriminalaca = new ArrayList<>();
            listaKriminalaca.addAll(osumnjiceni.getPopisPovezanihKriminalaca());
            for (Osumnjiceni krimi : listaKriminalaca) {
                observableKriminalci.add(krimi.getIme() + " " + krimi.getPrezime() + " " + krimi.getOib().toString());
            }
        }
        popisPovezanihKriminalaca.setItems(observableKriminalci);
        
        if (!osumnjiceni.getFotografijeURL().isEmpty()) {
            observableListaURL.addAll(osumnjiceni.getFotografijeURL());
        }
        listaURL.setItems(observableListaURL);

        if (!osumnjiceni.getPopisAliasa().isEmpty()) {
            observablePopisAliasa.addAll(osumnjiceni.getPopisAliasa());
        }
        popisAliasa.setItems(observablePopisAliasa);
        
        if (!osumnjiceni.getFizickeOsobine().getTetovaze().isEmpty()) {
            observableTetovaze.addAll(osumnjiceni.getFizickeOsobine().getTetovaze());
        }
        tetovaze.setItems(observableTetovaze);
        
        if (!osumnjiceni.getFizickeOsobine().getBolesti().isEmpty()) {
            observableBolesti.addAll(osumnjiceni.getFizickeOsobine().getBolesti());    
        }
        bolesti.setItems(observableBolesti);
        
        if (!osumnjiceni.getFizickeOsobine().getFizickiNedostatci().isEmpty()) {
            observableFizickiNedostatci.addAll(osumnjiceni.getFizickeOsobine().getFizickiNedostatci());    
        }
        fizickiNedostatci.setItems(observableFizickiNedostatci);
        
        if (!osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine().isEmpty()) {
            observableOstaleFizicke.addAll(osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine());
        }
        ostaleFizickeOsobine.setItems(observableOstaleFizicke);
        
        if (!osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi().isEmpty()) {
            observablePsiholoski.addAll(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi());
        }
        psiholoskiProblemi.setItems(observablePsiholoski);
        
        if (!osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine().isEmpty()) {
            observableOstaleKarakterne.addAll(osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine());
        }
        ostaleKarakterneOsobine.setItems(observableOstaleKarakterne);
    }
    
    @FXML private void dodajURL(ActionEvent event) {
        observableListaURL.add(upisaniURL.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaFotografijeKriminalca(upisaniURL.getText()));
        upisaniURL.clear();  
    }
    
    @FXML private void obrisiURL(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaFotografijeKriminalca(listaURL.getSelectionModel().getSelectedItem().toString()));
        observableListaURL.remove(listaURL.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajAlias(ActionEvent event) {
        observablePopisAliasa.add(upisaniAlias.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaListaAliasa(upisaniAlias.getText()));
        upisaniAlias.clear();
    }
    
    @FXML private void obrisiAlias(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaListaAliasa(popisAliasa.getSelectionModel().getSelectedItem().toString()));
        observablePopisAliasa.remove(popisAliasa.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajAdresu(ActionEvent event) {
        observableAdrese.add(upisanaAdresa.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaPoznateAdrese(upisanaAdresa.getText()));
        upisanaAdresa.clear();
    }
    
    @FXML private void obrisiAdresu(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaPoznateAdrese(poznateAdrese.getSelectionModel().getSelectedItem().toString()));
        observableAdrese.remove(poznateAdrese.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajSlucaj(ActionEvent event) {
        observableSlucajevi.add(upisaniSlucaj.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaPovezaniSlucajevi(upisaniSlucaj.getText()));
        upisaniSlucaj.clear();
    }
    
    @FXML private void obrisiSlucaj(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaPovezaniSlucajevi(popisPovezanihSlucajeva.getSelectionModel().getSelectedItem().toString()));
        observableSlucajevi.remove(popisPovezanihSlucajeva.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajKriminalca(ActionEvent event) {
        observableKriminalci.add(upisaniKriminalac.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaPovezaniKriminalci(upisaniKriminalac.getText()));
        upisaniKriminalac.clear();
    }
    
    @FXML private void obrisiKriminalca(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaPovezaniKriminalci(popisPovezanihKriminalaca.getSelectionModel().getSelectedItem().toString()));
        observableKriminalci.remove(popisPovezanihKriminalaca.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajTetovazu(ActionEvent event) {
        observableTetovaze.add(upisanaTetovaza.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaTetovaža(upisanaTetovaza.getText()));
        upisanaTetovaza.clear();
    }
    
    @FXML private void obrisiTetovazu(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaTetovaža(tetovaze.getSelectionModel().getSelectedItem().toString()));
        observableTetovaze.remove(tetovaze.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajBolest(ActionEvent event) {
        observableBolesti.add(upisanaBolest.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaBolest(upisanaBolest.getText()));
        upisanaBolest.clear();
    }
    
    @FXML private void obrisiBolest(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaBolest(bolesti.getSelectionModel().getSelectedItem().toString()));
        observableBolesti.remove(bolesti.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajFizickiNedostatak(ActionEvent event) {
        observableFizickiNedostatci.add(upisaniNedostatak.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaFizičkiNedostatak(upisaniNedostatak.getText()));
        upisaniNedostatak.clear();
    }
    
    @FXML private void obrisiFizickiNedostatak(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaFizičkiNedostatak(fizickiNedostatci.getSelectionModel().getSelectedItem().toString()));
        observableFizickiNedostatci.remove(fizickiNedostatci.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajFizickuOsobinu(ActionEvent event) {
        observableOstaleFizicke.add(upisanaFizickaOsobina.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaFizickaOsobina(upisanaFizickaOsobina.getText()));
        upisanaFizickaOsobina.clear();
    }
    
    @FXML private void obrisiFizickuOsobinu(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaFizickaOsobina(ostaleFizickeOsobine.getSelectionModel().getSelectedItem().toString()));
        observableOstaleFizicke.remove(ostaleFizickeOsobine.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajPsiholoskiProblem(ActionEvent event) {
        observablePsiholoski.add(upisaniPsiholoski.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaPsihološkiProblem(upisaniPsiholoski.getText()));
        upisaniPsiholoski.clear();
    }
    
    @FXML private void obrisiPsiholoskiProblem(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaPsihološkiProblem(psiholoskiProblemi.getSelectionModel().getSelectedItem().toString()));
        observablePsiholoski.remove(psiholoskiProblemi.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajKarakternuOsobinu(ActionEvent event) {
        observableOstaleKarakterne.add(upisanaKarakternaOsobina.getText());
        dodaniAtributi.add(Osumnjiceni.izmjenaKarakternaOsobina(upisanaKarakternaOsobina.getText()));
        upisanaKarakternaOsobina.clear();
    }
    
    @FXML private void obrisiKarakternuOsobinu(ActionEvent event) {
        obrisaniAtributi.add(Osumnjiceni.izmjenaKarakternaOsobina(ostaleKarakterneOsobine.getSelectionModel().getSelectedItem().toString()));
        observableOstaleKarakterne.remove(ostaleKarakterneOsobine.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void spremiIzmjene(ActionEvent event) {
        
        String poruka = "Unesite: ";
        
        izmijenjeniKriminalac = new Osumnjiceni();
        izmijenjeniKriminalac.setOib(stariOsumnjiceni.getOib());
        
        AdresaIMjestoStanovanja aims = new AdresaIMjestoStanovanja();
        if (adresa.getText() != null)
            if (!adresa.getText().isEmpty())
                aims.setAdresa(adresa.getText());
       
        if (mjesto.getText() != null)
            if (!mjesto.getText().isEmpty())
                aims.setNazivMjesta(mjesto.getText());
        
        if (pbrMjesto.getText() != null)
            if (!pbrMjesto.getText().isEmpty())
                aims.setPbrMjesto(Integer.parseInt(pbrMjesto.getText()));
        if(aims != null)
        	izmijenjeniKriminalac.setAdresaPrebivalista(aims);
        
        if (brojTelefona.getText() != null)
            if (!brojTelefona.getText().isEmpty())
                izmijenjeniKriminalac.setBrojTelefona(brojTelefona.getText());
        
        if (opisKriminalnihDjelatnosti.getText() != null)
            if (!opisKriminalnihDjelatnosti.getText().isEmpty())
                izmijenjeniKriminalac.setOpisKriminalnihDjelatnosti(opisKriminalnihDjelatnosti.getText());
        
        if (status.getValue() != null) {
            if (status.getValue().equals("Na slobodi")) {
                izmijenjeniKriminalac.setStatus(TrenutniStatusKriminalca.sloboda);
            } else if (status.getValue().equals("U pritvoru")) {
                izmijenjeniKriminalac.setStatus(TrenutniStatusKriminalca.u_pritvoru);
            } else if (status.getValue().equals("U zatvoru")) {
                izmijenjeniKriminalac.setStatus(TrenutniStatusKriminalca.u_zatvoru);
            }
        } else {
            poruka = poruka.concat("status; ");
        }
        
        FizickeOsobine fizickeOsobine = new FizickeOsobine();
        
        if (bojaKose.getText() != null)
            if (!bojaKose.getText().isEmpty())
                fizickeOsobine.setBojaKose(bojaKose.getText());
        
        if (gradaTijela.getValue() != null) {
            if (gradaTijela.getValue().equals("Slabija")) {
                fizickeOsobine.setGradaTijela(GradaTijela.slabija);
            } else if (gradaTijela.getValue().equals("Srednja")) {
                fizickeOsobine.setGradaTijela(GradaTijela.srednja);
            } else if (gradaTijela.getValue().equals("Jača")) {
                fizickeOsobine.setGradaTijela(GradaTijela.jaca);
            }
        }
        
        if (oblikFrizure.getText() != null)
            if (!oblikFrizure.getText().isEmpty())
                fizickeOsobine.setOblikFrizure(oblikFrizure.getText());
        
        if (spol.getValue() != null) {
            if (spol.getValue().equals("M")) {
                fizickeOsobine.setSpol(Spol.M);
            } else if (spol.getValue().equals("�")) {
                fizickeOsobine.setSpol(Spol.Ž);
            }
        } else poruka = poruka.concat("spol; ");
        
        if (tezina.getText() != null)
            if (!tezina.getText().isEmpty())
                fizickeOsobine.setTezina(Float.parseFloat(tezina.getText()));
        
        if (visina.getText() != null)
            if (!visina.getText().isEmpty())
                fizickeOsobine.setVisina(Float.parseFloat(visina.getText()));
        
        izmijenjeniKriminalac.setFizickeOsobine(fizickeOsobine);
        
        if (poruka.equals("Unesite: "))       
            delegate.spremiIzmjeneKriminalca(stariOsumnjiceni, izmijenjeniKriminalac, dodaniAtributi, obrisaniAtributi);
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
