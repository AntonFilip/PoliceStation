package View;

import Controller.ViewDelegate;
import Model.*;
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
public class DodajKriminalacController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML Button dodaj;

    @FXML TextField ime;
    @FXML TextField prezime;
    @FXML TextField oib;
    @FXML TextField adresa;
    @FXML TextField mjesto;
    @FXML TextField pbrMjesto;
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
    
    @FXML Label info;
    
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
        
        String poruka = "Neispravno: ";

        if (ime.getText() != null) {
            if (!ime.getText().isEmpty()) {
                osumnjiceni.setIme(ime.getText());
            } else {
                poruka.concat("ime; ");
            }
        }
        
        if (prezime.getText() != null) {
            if (!prezime.getText().isEmpty()) {
                osumnjiceni.setPrezime(prezime.getText());
            } else {
                poruka.concat("prezime; ");
            }
        }
        
        if (oib.getText() != null) {
            if (!oib.getText().isEmpty()) {
                osumnjiceni.setOib(Long.parseLong(oib.getText()));
            } else {
                poruka.concat("OIB; ");
            }
        }  
        
        AdresaIMjestoStanovanja adr = new AdresaIMjestoStanovanja();
        if (adresa.getText() != null) {
            if (!adresa.getText().isEmpty()) {
                adr.setAdresa(adresa.getText());
            } else {
                poruka.concat("adresa; ");
            }
        }
        if (mjesto.getText() != null) {
            if (!adresa.getText().isEmpty()) {
                adr.setNazivMjesta(mjesto.getText());
            } else {
                poruka.concat("mjesto; ");
            }
        }
        if (pbrMjesto.getText() != null) {
            if (!pbrMjesto.getText().isEmpty()) {
                adr.setPbrMjesto(Integer.parseInt(pbrMjesto.getText()));
            } else {
                poruka.concat("poštanski broj; ");
            }
        }
        osumnjiceni.setAdresaPrebivalista(adr);
        
        if (brojTelefona.getText() != null) {
            if (!brojTelefona.getText().isEmpty()) {
                osumnjiceni.setBrojTelefona(brojTelefona.getText());
            }
        }
        
        if (status.getValue() != null) {
            if (status.getValue().equals("Na slobodi")) {
                osumnjiceni.setStatus(TrenutniStatusKriminalca.sloboda);
            } else if (status.getValue().equals("U pritvoru")) {
                osumnjiceni.setStatus(TrenutniStatusKriminalca.u_pritvoru);
            } else if (status.getValue().equals("U zatvoru")) {
                osumnjiceni.setStatus(TrenutniStatusKriminalca.u_zatvoru);
            }
        }
        
        if (opisKriminalnihDjelatnosti.getText() != null) {
            if (!opisKriminalnihDjelatnosti.getText().isEmpty()) {
                osumnjiceni.setOpisKriminalnihDjelatnosti(opisKriminalnihDjelatnosti.getText());
            }
        }
        
        if (popisAliasa.getText() != null) {
            if (!popisAliasa.getText().isEmpty()) {
                osumnjiceni.setPopisAliasa(popis(popisAliasa.getText().split(";")));
            }
        }       
        
        if (poznateAdrese.getText() != null) {
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
        }
        
        if (popisPovezanihSlucajeva.getText() != null) {
            if (!popisPovezanihSlucajeva.getText().isEmpty()) {
                String[] slucajevi = popisPovezanihSlucajeva.getText().split(";");
                HashSet<Slucaj> popisSlucajeva = new HashSet<>();
                for (String slucaj : slucajevi) {
                    Slucaj novi = new Slucaj();
                    novi.setBrojSlucaja(Integer.parseInt(slucaj));
                    popisSlucajeva.add(novi);
                }
                osumnjiceni.setPovezaniSlucajevi(popisSlucajeva);
            }
        }
        
        if (popisPovezanihKriminalaca.getText() != null) {
            if (!popisPovezanihKriminalaca.getText().isEmpty()) {
                String[] kriminalci = popisPovezanihKriminalaca.getText().split(";");
                HashSet<Osumnjiceni> popisKriminalaca = new HashSet<>();
                for (String kriminalac : kriminalci) {
                    Osumnjiceni novi = new Osumnjiceni();
                    novi.setOib(Integer.parseInt(kriminalac));
                    popisKriminalaca.add(novi);
                }
                osumnjiceni.setPopisPovezanihKriminalaca(popisKriminalaca);
            }
        }
        
        FizickeOsobine fizickeOsobine = new FizickeOsobine();
        
        if (spol.getValue() != null) {
            if (spol.getValue().equals("M")) {
                fizickeOsobine.setSpol(Spol.M);
            } else if (spol.getValue().equals("Ž")) {
                fizickeOsobine.setSpol(Spol.Ž);
            }
        }
        
        if (rasa.getText() != null) {
            if (!rasa.getText().isEmpty()) {
                fizickeOsobine.setRasa(rasa.getText());
            }
        }
        if (visina.getText() != null) {
            if (!visina.getText().equals("")) {
                fizickeOsobine.setVisina(Float.parseFloat(visina.getText()));
            }
        }
        if (tezina.getText() != null) {
            if(!tezina.getText().equals("")) {
                fizickeOsobine.setTezina(Float.parseFloat(tezina.getText()));
            }
        } 
        if (godine.getText() != null) {
            if(!godine.getText().equals("")) {
                fizickeOsobine.setGodine(Integer.parseInt(godine.getText()));
            }
        } 
        if (bojaKose.getText() != null) {
            if (!bojaKose.getText().equals("")) {
                fizickeOsobine.setBojaKose(bojaKose.getText());
            }
        }
        if (oblikGlave.getText() != null) {
            if (!oblikGlave.getText().equals("")) {
                fizickeOsobine.setOblikGlave(oblikGlave.getText());
            }
        }
        if (oblikFrizure.getText() != null) {
            if(!oblikFrizure.getText().equals("")) {
                fizickeOsobine.setOblikFrizure(oblikFrizure.getText());
            }
        }
        if (bojaOciju.getText() != null) {
            if(!bojaOciju.getText().equals("")) {
                fizickeOsobine.setBojaOciju(bojaOciju.getText());
            }
        }
        
        if (gradaTijela.getValue() != null) {
            if (gradaTijela.getValue().equals("Slabija")) {
                fizickeOsobine.setGradaTijela(GradaTijela.slabija);
            } else if (gradaTijela.getValue().equals("Srednja")) {
                fizickeOsobine.setGradaTijela(GradaTijela.srednja);
            } else if (gradaTijela.getValue().equals("Jača")) {
                fizickeOsobine.setGradaTijela(GradaTijela.jaca);
            }
        }
        
        if(tetovaze.getText() != null) 
            if (!tetovaze.getText().isEmpty()) {
                fizickeOsobine.setTetovaze(popis(tetovaze.getText().split(";")));
            }
        if (fizickiNedostatci.getText() != null)
            if (!fizickiNedostatci.getText().isEmpty()) {
                fizickeOsobine.setFizickiNedostatci(popis(fizickiNedostatci.getText().split(";")));
            }
        if (bolesti.getText() != null) 
            if (!bolesti.getText().isEmpty()) {
                fizickeOsobine.setBolesti(popis(bolesti.getText().split(";")));
            }
        if (ostaleFizickeOsobine.getText() != null)
            if (!ostaleFizickeOsobine.getText().isEmpty()) {
                fizickeOsobine.setOstaleFizickeOsobine(popis(ostaleFizickeOsobine.getText().split(";")));
            }
        osumnjiceni.setFizickeOsobine(fizickeOsobine);

        KarakterneOsobine karakterneOsobine = new KarakterneOsobine();
        
        if (nacinGovora.getText() != null) 
            if (!nacinGovora.getText().isEmpty())
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
        
        if (psiholoskiProblemi.getText() != null) 
            if (!psiholoskiProblemi.getText().isEmpty())
                karakterneOsobine.setPsiholoskiProblemi(popis(psiholoskiProblemi.getText().split(";")));
        if (ostaleKarakterneOsobine.getText() != null)
            if (!ostaleKarakterneOsobine.getText().isEmpty())
                karakterneOsobine.setOstaleKarakterneOsobine(popis(ostaleKarakterneOsobine.getText().split(";")));

        osumnjiceni.setKarakterneOsobine(karakterneOsobine);
        
        if (!observableFotografije.isEmpty()) {
            Set<String> fotkeURL = new HashSet<>();
            fotkeURL.addAll(observableFotografije);
            osumnjiceni.setFotografijeURL(fotkeURL);
        }
        
        if (otisakPrsta.getText() != null)
            if (!otisakPrsta.getText().equals("")) 
                osumnjiceni.setOtisakPrstaURL(otisakPrsta.getText());

        if (poruka.equals("Neispravno: ")) 
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
