package View;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import Controller.ViewDelegate;
import Model.AdresaIMjestoStanovanja;
import Model.FizickeOsobine;
import Model.GradaTijela;
import Model.KarakterneOsobine;
import Model.Osumnjiceni;
import Model.RazinaApstraktneInteligencije;
import Model.Slucaj;
import Model.Spol;
import Model.TrenutniStatusKriminalca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Klasa koja upravlja sučeljem za postavljanje upita o kriminalcu
 */
public class UpitKriminalacController implements Initializable, ControlledScreen {

    ViewDelegate delegate;
    
    @FXML Button posalji;

    @FXML TextField ime;
    @FXML TextField prezime;
    @FXML TextField adresa;
    @FXML TextField brojTelefona;
    @FXML ComboBox<?> status;
    @FXML TextArea popisAliasa;
    @FXML TextArea poznateAdrese;
    @FXML TextArea popisPovezanihSlucajeva;
    @FXML TextArea popisPovezanihKriminalaca;

    @FXML ComboBox<?> spol;
    @FXML TextField rasa;
    @FXML TextField visina;
    @FXML TextField tezina;
    @FXML TextField godine;
    @FXML TextField bojaKose;
    @FXML TextField oblikFrizure;
    @FXML TextField oblikGlave;
    @FXML TextField bojaOciju;
    @FXML ComboBox<?> gradaTijela;
    @FXML TextArea tetovaze;
    @FXML TextArea fizickiNedostatci;
    @FXML TextArea bolesti;
    @FXML TextArea ostaleFizickeOsobine;

    @FXML TextField nacinGovora;
    @FXML ComboBox<?> razinaApstraktneInteligencije;
    @FXML TextArea psiholoskiProblemi;
    @FXML TextArea ostaleKarakterneOsobine;      

    @Override
    public void init(ViewDelegate delegate) {
            this.delegate = delegate;		
    }

    /**
     * Izvlacimo upisane podatke iz View-a i saljemo ih u Controller 
     */
    @FXML private void postaviUpit(ActionEvent event) {
        Osumnjiceni osumnjiceni = new Osumnjiceni();

        osumnjiceni.setIme(ime.getText());
        osumnjiceni.setPrezime(prezime.getText());
        
        AdresaIMjestoStanovanja adr = new AdresaIMjestoStanovanja();

        if (!adresa.getText().isEmpty()) {

            String[] adresaIMjesto = adresa.getText().split(",");
            String adress = adresaIMjesto[0];
            String mjesto = adresaIMjesto[1];
            adr.setAdresa(adress);
            adr.setNazivMjesta(mjesto);

        }
        osumnjiceni.setAdresaPrebivalista(adr);
        
        osumnjiceni.setBrojTelefona(brojTelefona.getText());
        
        if (status.getValue() != null) {
            if (status.getValue().equals("Na slobodi")) {
                osumnjiceni.setStatus(TrenutniStatusKriminalca.sloboda);
            } else if (status.getValue().equals("U pritvoru")) {
                osumnjiceni.setStatus(TrenutniStatusKriminalca.u_pritvoru);
            } else if (status.getValue().equals("U zatvoru")) {
                osumnjiceni.setStatus(TrenutniStatusKriminalca.u_zatvoru);
            }
        }

        osumnjiceni.setPopisAliasa(popis(popisAliasa.getText().split(";")));
        
        HashSet<AdresaIMjestoStanovanja> poznateAdr = null;
	if (!poznateAdrese.getText().isEmpty()) {
            poznateAdr = new HashSet<>();
            String[] adrese = poznateAdrese.getText().split(";");
            for (String adresa : adrese) {
                AdresaIMjestoStanovanja a = new AdresaIMjestoStanovanja();
                String[] temp = adresa.split(",");
                a.setAdresa(temp[0].trim());
                a.setNazivMjesta(temp[1].trim());
                poznateAdr.add(a);
            }
        }
        osumnjiceni.setPoznateAdrese(poznateAdr);

        String[] slucajevi = popisPovezanihSlucajeva.getText().split(";");
        HashSet<Slucaj> popisSlucajeva = new HashSet<>();
        for (String slucaj : slucajevi) {
            Slucaj novi = new Slucaj();
            if (!slucaj.isEmpty()) {
                novi.setBrojSlucaja(Integer.parseInt(slucaj));
                popisSlucajeva.add(novi);
            }
        }
        osumnjiceni.setPovezaniSlucajevi(popisSlucajeva);

        String[] kriminalci = popisPovezanihKriminalaca.getText().split(";");
        HashSet<Osumnjiceni> popisKriminalaca = new HashSet<>();
        for (String kriminalac : kriminalci) {
            Osumnjiceni novi = new Osumnjiceni();
            if (!kriminalac.isEmpty()) {
                novi.setOib(Long.parseLong(kriminalac)); 
                popisKriminalaca.add(novi);
            }
        }
        osumnjiceni.setPopisPovezanihKriminalaca(popisKriminalaca);

        FizickeOsobine fizickeOsobine = new FizickeOsobine();
        
        if (spol.getValue() != null) {
            if (spol.getValue().equals("M")) {
                fizickeOsobine.setSpol(Spol.M);
            } else  {
                fizickeOsobine.setSpol(Spol.Ž);
            }
        }
        
        fizickeOsobine.setRasa(rasa.getText());
        
        if (!visina.getText().isEmpty()) {
        	String[] visinamm = visina.getText().split("-");
        	fizickeOsobine.setVisinaMin(Float.parseFloat(visinamm[0]));
        	fizickeOsobine.setVisinaMax(Float.parseFloat(visinamm[1]));
        }
        
        if (!tezina.getText().isEmpty()) {
        	String[] tezinamm = tezina.getText().split("-");
        	fizickeOsobine.setTezinaMin(Float.parseFloat(tezinamm[0]));
        	fizickeOsobine.setTezinaMax(Float.parseFloat(tezinamm[1]));
        }
        
        if (!godine.getText().isEmpty()) {
        	String[] godinemm = godine.getText().split("-");
        	fizickeOsobine.setGodineMin(Integer.parseInt(godinemm[0]));
        	fizickeOsobine.setGodineMax(Integer.parseInt(godinemm[1]));
        }
        
        fizickeOsobine.setBojaKose(bojaKose.getText());
        fizickeOsobine.setOblikGlave(oblikGlave.getText());
        fizickeOsobine.setOblikFrizure(oblikFrizure.getText());
        fizickeOsobine.setBojaOciju(bojaOciju.getText());
        
        if (gradaTijela.getValue() != null) {
            if (gradaTijela.getValue().equals("Slabija")) {
                fizickeOsobine.setGradaTijela(GradaTijela.slabija);
            } else if (gradaTijela.getValue().equals("Srednja")) {
                fizickeOsobine.setGradaTijela(GradaTijela.srednja);
            } else if (gradaTijela.getValue().equals("Jača")) {
                fizickeOsobine.setGradaTijela(GradaTijela.jaca);
            }
        }

        fizickeOsobine.setTetovaze(popis(tetovaze.getText().split(";")));
        fizickeOsobine.setFizickiNedostatci(popis(fizickiNedostatci.getText().split(";")));
        fizickeOsobine.setBolesti(popis(bolesti.getText().split(";")));
        fizickeOsobine.setOstaleFizickeOsobine(popis(ostaleFizickeOsobine.getText().split(";")));

        osumnjiceni.setFizickeOsobine(fizickeOsobine);

        KarakterneOsobine karakterneOsobine = new KarakterneOsobine();
        karakterneOsobine.setNacinGovora(nacinGovora.getText());
        if (razinaApstraktneInteligencije.getValue() != null) {
            if (razinaApstraktneInteligencije.getValue().equals("Niska")) {
                karakterneOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.niska);
            } else if (razinaApstraktneInteligencije.getValue().equals("Srednja")) {
                karakterneOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.srednja);
            } else if (razinaApstraktneInteligencije.getValue().equals("Visoka")) {
                karakterneOsobine.setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije.visoka);
            }  
        }
        karakterneOsobine.setPsiholoskiProblemi(popis(psiholoskiProblemi.getText().split(";")));
        karakterneOsobine.setOstaleKarakterneOsobine(popis(ostaleKarakterneOsobine.getText().split(";")));

        osumnjiceni.setKarakterneOsobine(karakterneOsobine);

        delegate.posaljiUpitKriminalac(osumnjiceni);
    }

    public static HashSet<String> popis(String[] ulaz) {
        HashSet<String> popis = new HashSet<>();
        popis.addAll(Arrays.asList(ulaz));
        return popis;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            // TODO Auto-generated method stub

    }

}
