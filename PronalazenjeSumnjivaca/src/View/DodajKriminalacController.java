package View;

import Controller.ViewDelegate;
import Model.FizickeOsobine;
import Model.GradaTijela;
import Model.KarakterneOsobine;
import Model.Osumnjiceni;
import Model.Slucaj;
import Model.TrenutniStatusKriminalca;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML TextField razinaApstraktneInteligencije;
    @FXML TextArea psiholoskiProblemi;
    @FXML TextArea ostaleKarakterneOsobine;      

    @Override
    public void init(ViewDelegate delegate) {
            this.delegate = delegate;		
    }

    /**
     * Izvlacimo upisane podatke iz View-a i saljemo ih u Controller 
     */
    @FXML private void dodaj(ActionEvent event) {
        Osumnjiceni osumnjiceni = new Osumnjiceni();

        osumnjiceni.setIme(ime.getText());
        osumnjiceni.setPrezime(prezime.getText());
        osumnjiceni.setOib(Integer.parseInt(oib.getText()));
        osumnjiceni.setAdresa(adresa.getText());
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

        osumnjiceni.setPoznateAdrese(popis(poznateAdrese.getText().split(";")));

        String[] slucajevi = popisPovezanihSlucajeva.getText().split(";");
        HashSet<Slucaj> popisSlucajeva = new HashSet<>();
        for (String slucaj : slucajevi) {
            Slucaj novi = new Slucaj();
            novi.setBrojSlucaja(Integer.parseInt(slucaj));
            popisSlucajeva.add(novi);
        }
        osumnjiceni.setPovezaniSlucajevi(popisSlucajeva);

        String[] kriminalci = popisPovezanihKriminalaca.getText().split(";");
        HashSet<Osumnjiceni> popisKriminalaca = new HashSet<>();
        for (String kriminalac : kriminalci) {
            Osumnjiceni novi = new Osumnjiceni();
            novi.setOib(Integer.parseInt(kriminalac)); 
            popisKriminalaca.add(novi);
        }
        osumnjiceni.setPopisPovezanihKriminalaca(popisKriminalaca);

        FizickeOsobine fizickeOsobine = new FizickeOsobine();
        fizickeOsobine.setSpol(spol.getValue().toString());
        fizickeOsobine.setRasa(rasa.getText());
        fizickeOsobine.setVisina(Integer.parseInt(visina.getText()));
        fizickeOsobine.setTezina(Integer.parseInt(tezina.getText()));
        fizickeOsobine.setGodine(Integer.parseInt(godine.getText()));
        fizickeOsobine.setBojaKose(bojaKose.getText());
        fizickeOsobine.setOblikGlave(oblikGlave.getText());
        fizickeOsobine.setOblikFrizure(oblikFrizure.getText());
        fizickeOsobine.setBojaOciju(bojaOciju.getText());

        if (gradaTijela.getValue().equals("Slabija")) {
            fizickeOsobine.setGradaTijela(GradaTijela.slabija);
        } else if (gradaTijela.getValue().equals("Srednja")) {
            fizickeOsobine.setGradaTijela(GradaTijela.srednja);
        } else if (gradaTijela.getValue().equals("Jača")) {
            fizickeOsobine.setGradaTijela(GradaTijela.jaca);
        }

        fizickeOsobine.setTetovaze(tetovaze.getText());
        fizickeOsobine.setFizickiNedostatci(fizickiNedostatci.getText());
        fizickeOsobine.setBolesti(bolesti.getText());
        fizickeOsobine.setOstalo(ostaleFizickeOsobine.getText());

        osumnjiceni.setFizickeOsobine(fizickeOsobine);

        KarakterneOsobine karakterneOsobine = new KarakterneOsobine();
        karakterneOsobine.setNacinGovora(nacinGovora.getText());
        karakterneOsobine.setRazinaApstraktneInteligencije(razinaApstraktneInteligencije.getText());
        karakterneOsobine.setPsiholoskiProblemi(psiholoskiProblemi.getText());
        karakterneOsobine.setOstalo(ostaleKarakterneOsobine.getText());

        osumnjiceni.setKarakterneOsobine(karakterneOsobine);

        delegate.dodajKriminalca(osumnjiceni);
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
