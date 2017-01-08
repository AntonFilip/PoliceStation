package View;

import Controller.ViewDelegate;
import Model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 */
public class DodajKriminalacController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML Button dodaj;

    @FXML TextField ime;
    @FXML TextField prezime;
    @FXML TextField oib;
    @FXML TextField adresa;
    @FXML TextField brojTelefona;
    @FXML ComboBox status;
    @FXML TextArea opisKriminalnihDjelatnosti;
    @FXML TextArea popisAliasa;
    @FXML TextArea poznateAdrese;
    @FXML TextArea popisPovezanihSlucajeva;
    @FXML TextArea popisPovezanihKriminalaca;

    @FXML ComboBox spol;
    @FXML TextField rasa;
    @FXML TextField visina;
    @FXML TextField tezina;
    @FXML TextField godine;
    @FXML TextField bojaKose;
    @FXML TextField oblikFrizure;
    @FXML TextField oblikGlave;
    @FXML TextField bojaOciju;
    @FXML ComboBox gradaTijela;
    @FXML TextArea tetovaze;
    @FXML TextArea fizickiNedostatci;
    @FXML TextArea bolesti;
    @FXML TextArea ostaleFizickeOsobine;

    @FXML TextField nacinGovora;
    @FXML ComboBox razinaApstraktneInteligencije;
    @FXML TextArea psiholoskiProblemi;
    @FXML TextArea ostaleKarakterneOsobine;  
    
    @FXML ListView fotografije;
    @FXML TextField upisaniURL;
    @FXML Button dodajURL;
    @FXML Button obrisiURL;
    @FXML TextField otisakPrsta;
    
    ObservableList<String> observableFotografije = FXCollections.observableArrayList();

    @Override
    public void init(ViewDelegate delegate) {
            this.delegate = delegate;	
            fotografije.setItems(observableFotografije);
    }

    /**
     * Izvlacimo upisane podatke iz View-a i saljemo ih u Controller 
     */
    @FXML private void dodaj(ActionEvent event) {
        Osumnjiceni osumnjiceni = new Osumnjiceni();

        osumnjiceni.setIme(ime.getText());
        osumnjiceni.setPrezime(prezime.getText());
        osumnjiceni.setOib(Long.parseLong(oib.getText()));
        
        if (!adresa.getText().isEmpty()) {
            AdresaIMjestoStanovanja adr = new AdresaIMjestoStanovanja();
            String[] adresaIMjesto = adresa.getText().split(",");
            String adress = adresaIMjesto[0];
            String mjesto = adresaIMjesto[1];
            adr.setAdresa(adress);
            adr.setNazivMjesta(mjesto);
            osumnjiceni.setAdresaPrebivalista(adr);
        }
        osumnjiceni.setBrojTelefona(brojTelefona.getText());

        if (status.getValue().equals("Na slobodi")) {
            osumnjiceni.setStatus(TrenutniStatusKriminalca.sloboda);
        } else if (status.getValue().equals("U pritvoru")) {
            osumnjiceni.setStatus(TrenutniStatusKriminalca.u_pritvoru);
        } else if (status.getValue().equals("U zatvoru")) {
            osumnjiceni.setStatus(TrenutniStatusKriminalca.u_zatvoru);
        }

        osumnjiceni.setOpisKriminalnihDjelatnosti(opisKriminalnihDjelatnosti.getText());

        osumnjiceni.setPopisAliasa(popis(popisAliasa.getText().split(";")));
        
        if (!poznateAdrese.getText().isEmpty()) {
            HashSet<AdresaIMjestoStanovanja> poznateAdr = new HashSet<>();
            String[] adrese = poznateAdrese.getText().split(";");
            for (String adresa : adrese) {
                AdresaIMjestoStanovanja a = new AdresaIMjestoStanovanja();
                String[] temp = adresa.split(",");
                a.setAdresa(temp[0].trim());
                a.setNazivMjesta(temp[1].trim());
                poznateAdr.add(a);
            }
            osumnjiceni.setPoznateAdrese(poznateAdr);
        }
        
        if(!popisPovezanihSlucajeva.getText().isEmpty()) {
            String[] slucajevi = popisPovezanihSlucajeva.getText().split(";");
            HashSet<Slucaj> popisSlucajeva = new HashSet<>();
            for (String slucaj : slucajevi) {
                Slucaj novi = new Slucaj();
                novi.setBrojSlucaja(Integer.parseInt(slucaj));
                popisSlucajeva.add(novi);
            }
            osumnjiceni.setPovezaniSlucajevi(popisSlucajeva);
        }
        
        if(!popisPovezanihKriminalaca.getText().isEmpty()) {
            String[] kriminalci = popisPovezanihKriminalaca.getText().split(";");
            HashSet<Osumnjiceni> popisKriminalaca = new HashSet<>();
            for (String kriminalac : kriminalci) {
                Osumnjiceni novi = new Osumnjiceni();
                novi.setOib(Integer.parseInt(kriminalac)); 
                popisKriminalaca.add(novi);
            }
            osumnjiceni.setPopisPovezanihKriminalaca(popisKriminalaca);
        }
        
        FizickeOsobine fizickeOsobine = new FizickeOsobine();
        
        if (spol.getValue() != null) {
            if (spol.getValue().equals("M")) {
                fizickeOsobine.setSpol(Spol.M);
            } else if (spol.getValue().equals("Ž")) {
                fizickeOsobine.setSpol(Spol.Ž);
            }
        }
        if (rasa.getText() != null && !rasa.getText().equals("")) fizickeOsobine.setRasa(rasa.getText());
        if (visina.getText() != null && !visina.getText().equals("")) fizickeOsobine.setVisina(Float.parseFloat(visina.getText()));
        if (tezina.getText() != null && !tezina.getText().equals("")) fizickeOsobine.setTezina(Float.parseFloat(tezina.getText()));
        if (godine.getText() != null && !godine.getText().equals("")) fizickeOsobine.setGodine(Integer.parseInt(godine.getText()));
        if (bojaKose.getText() != null && !bojaKose.getText().equals("")) fizickeOsobine.setBojaKose(bojaKose.getText());
        if (oblikGlave.getText() != null && !oblikGlave.getText().equals("")) fizickeOsobine.setOblikGlave(oblikGlave.getText());
        if (oblikFrizure.getText() != null && !oblikFrizure.getText().equals("")) fizickeOsobine.setOblikFrizure(oblikFrizure.getText());
        if (bojaOciju.getText() != null && !bojaOciju.getText().equals("")) fizickeOsobine.setBojaOciju(bojaOciju.getText());
        
        if (gradaTijela.getValue() != null) {
            if (gradaTijela.getValue().equals("Slabija")) {
                fizickeOsobine.setGradaTijela(GradaTijela.slabija);
            } else if (gradaTijela.getValue().equals("Srednja")) {
                fizickeOsobine.setGradaTijela(GradaTijela.srednja);
            } else if (gradaTijela.getValue().equals("Jača")) {
                fizickeOsobine.setGradaTijela(GradaTijela.jaca);
            }
        }
        
        if(tetovaze.getText() != null) fizickeOsobine.setTetovaze(popis(tetovaze.getText().split(";")));
        if (fizickiNedostatci.getText() != null) fizickeOsobine.setFizickiNedostatci(popis(fizickiNedostatci.getText().split(";")));
        if (bolesti.getText() != null) fizickeOsobine.setBolesti(popis(bolesti.getText().split(";")));
        if (ostaleFizickeOsobine.getText() != null) fizickeOsobine.setOstaleFizickeOsobine(popis(ostaleFizickeOsobine.getText().split(";")));

        osumnjiceni.setFizickeOsobine(fizickeOsobine);

        KarakterneOsobine karakterneOsobine = new KarakterneOsobine();
        
        if (nacinGovora.getText() != null) karakterneOsobine.setNacinGovora(nacinGovora.getText());
        
        if (razinaApstraktneInteligencije.getValue() != null) {
            if (razinaApstraktneInteligencije.getValue().equals("Niska")) {
                karakterneOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.niska);
            } else if (razinaApstraktneInteligencije.getValue().equals("Srednja")) {
                karakterneOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.srednja);
            } else if (razinaApstraktneInteligencije.getValue().equals("Visoka")) {
                karakterneOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.visoka);
            }  
        }
        
        if (psiholoskiProblemi.getText() != null) karakterneOsobine.setPsiholoskiProblemi(popis(psiholoskiProblemi.getText().split(";")));
        if (ostaleKarakterneOsobine.getText() != null) karakterneOsobine.setOstaleKarakterneOsobine(popis(ostaleKarakterneOsobine.getText().split(";")));

        osumnjiceni.setKarakterneOsobine(karakterneOsobine);
        
        if (!observableFotografije.isEmpty()) {
            Set<String> fotkeURL = new HashSet<>();
            fotkeURL.addAll(observableFotografije);
            osumnjiceni.setFotografijeURL(fotkeURL);
        }
        
        if (otisakPrsta.getText() != null && !otisakPrsta.getText().equals("")) 
            osumnjiceni.setOtisakPrstaURL(otisakPrsta.getText());

        delegate.dodajKriminalca(osumnjiceni);
    }
    
    @FXML private void dodajURL(ActionEvent event) {
        observableFotografije.add(upisaniURL.getText());
        upisaniURL.clear();
    }
    
    @FXML private void obrisiURL(ActionEvent event) {
        observableFotografije.remove(fotografije.getSelectionModel().getSelectedIndex());
    }

    public static HashSet<String> popis(String[] ulaz) {
        HashSet<String> popis = new HashSet<>();
        popis.addAll(Arrays.asList(ulaz));
        return popis;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}
