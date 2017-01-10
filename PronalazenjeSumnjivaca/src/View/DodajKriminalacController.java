package View;

import Controller.ViewDelegate;
import Model.*;
import java.net.URL;
import java.time.LocalDate;
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
    @FXML ComboBox<?> status;
    @FXML TextField datumRodenja;
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

    @FXML TextField nacinGovora;
    @FXML ComboBox<?> razinaApstraktneInteligencije; 
    
    @FXML ListView<String> fotografije;
    @FXML TextField upisaniURL;
    @FXML Button dodajURL;
    @FXML Button obrisiURL;
    @FXML TextField otisakPrsta;
    
    @FXML Label info;  
    
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
    ObservableList<String> observableFotografije = FXCollections.observableArrayList();

    @Override
    public void init(ViewDelegate delegate) {
            this.delegate = delegate;	
            fotografije.setItems(observableFotografije);
            popisAliasa.setItems(observablePopisAliasa);
            tetovaze.setItems(observableTetovaze);
            bolesti.setItems(observableBolesti);
            fizickiNedostatci.setItems(observableFizickiNedostatci);
            ostaleFizickeOsobine.setItems(observableOstaleFizicke);
            psiholoskiProblemi.setItems(observablePsiholoski);
            popisPovezanihKriminalaca.setItems(observableKriminalci);
            poznateAdrese.setItems(observableAdrese);
            popisPovezanihSlucajeva.setItems(observableSlucajevi);
            ostaleKarakterneOsobine.setItems(observableOstaleKarakterne);         
    }

    /**
     * Izvlacimo upisane podatke iz View-a i saljemo ih u Controller 
     */
    @FXML private void dodaj(ActionEvent event) {
        
        Osumnjiceni osumnjiceni = new Osumnjiceni();
        
        String poruka = "Unesite: ";

        if (ime.getText() != null) {
            if (!ime.getText().isEmpty()) {
                osumnjiceni.setIme(ime.getText());
            } else {
                poruka = poruka.concat("ime; ");
            }
        }
        
        if (prezime.getText() != null) {
            if (!prezime.getText().isEmpty()) {
                osumnjiceni.setPrezime(prezime.getText());
            } else {
                poruka = poruka.concat("prezime; ");
            }
        }
        
        if (oib.getText() != null) {
            if (!oib.getText().isEmpty()) {
                osumnjiceni.setOib(Long.parseLong(oib.getText()));
            } else {
                poruka = poruka.concat("OIB; ");
            }
        }  
        
        AdresaIMjestoStanovanja adr = new AdresaIMjestoStanovanja();
        if (adresa.getText() != null) {
            if (!adresa.getText().isEmpty()) {
                adr.setAdresa(adresa.getText());
            } else {
                poruka = poruka.concat("adresa; ");
            }
        }
        if (mjesto.getText() != null) {
            if (!adresa.getText().isEmpty()) {
                adr.setNazivMjesta(mjesto.getText());
            } else {
                poruka = poruka.concat("mjesto; ");
            }
        }
        if (pbrMjesto.getText() != null) {
            if (!pbrMjesto.getText().isEmpty()) {
                adr.setPbrMjesto(Integer.parseInt(pbrMjesto.getText()));
            } else {
                poruka = poruka.concat("poštanski broj; ");
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
        } else poruka = poruka.concat("status; ");
        
        if (datumRodenja.getText() != null) 
            if (!datumRodenja.getText().isEmpty())
                osumnjiceni.setDatumRodjenja(LocalDate.parse(datumRodenja.getText()));
            else poruka = poruka.concat("datum rođenja; ");
        
        if (opisKriminalnihDjelatnosti.getText() != null) {
            if (!opisKriminalnihDjelatnosti.getText().isEmpty()) {
                osumnjiceni.setOpisKriminalnihDjelatnosti(opisKriminalnihDjelatnosti.getText());
            }
        }
              
        if (!observablePopisAliasa.isEmpty()) {
            Set<String> aliasi = new HashSet<>();
            aliasi.addAll(observablePopisAliasa);
            osumnjiceni.setPopisAliasa(aliasi);
        }    
                   
        if (!observableAdrese.isEmpty()) {
            HashSet<AdresaIMjestoStanovanja> poznateAdr = new HashSet<>();
            for (String adresa : observableAdrese) {
                AdresaIMjestoStanovanja a = new AdresaIMjestoStanovanja();
                String[] temp = adresa.split(",");
                a.setAdresa(temp[0].trim());
                a.setNazivMjesta(temp[1].trim());
                a.setPbrMjesto(Integer.parseInt(temp[2].trim()));
                poznateAdr.add(a);
            }
            osumnjiceni.setPoznateAdrese(poznateAdr);
        }
        
        
        if (!observableSlucajevi.isEmpty()) {
            HashSet<Slucaj> popisSlucajeva = new HashSet<>();
            for (String slucaj : observableSlucajevi) {
                Slucaj novi = new Slucaj();
                novi.setBrojSlucaja(Integer.parseInt(slucaj));
                popisSlucajeva.add(novi);
            }
            osumnjiceni.setPovezaniSlucajevi(popisSlucajeva);
        }
        
        if (!observableKriminalci.isEmpty()) {
                HashSet<Osumnjiceni> popisKriminalaca = new HashSet<>();
                for (String kriminalac : observableKriminalci) {
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
        } else poruka = poruka.concat("spol; ");
        
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
        
        if(!observableTetovaze.isEmpty()) {
            HashSet<String> popisTetovaza = new HashSet<>();
            popisTetovaza.addAll(observableTetovaze);
            fizickeOsobine.setTetovaze(popisTetovaza);
        }
        
        if (!observableFizickiNedostatci.isEmpty()) {
            Set<String> nedostatci = new HashSet<>();
            nedostatci.addAll(observableFizickiNedostatci);
            fizickeOsobine.setFizickiNedostatci(nedostatci);
        }
        
        if (!observableBolesti.isEmpty()) {
            Set<String> popisBolesti = new HashSet<>();
            popisBolesti.addAll(observableBolesti);
            fizickeOsobine.setBolesti(popisBolesti);
        }
        
        if (!observableOstaleFizicke.isEmpty()) {
            Set<String> ostaleFizicke = new HashSet<>();
            ostaleFizicke.addAll(observableOstaleFizicke);
            fizickeOsobine.setOstaleFizickeOsobine(ostaleFizicke);
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
        
        if (!observablePsiholoski.isEmpty()) {
            Set<String> psiholoski = new HashSet<>();
            psiholoski.addAll(observablePsiholoski);
            karakterneOsobine.setPsiholoskiProblemi(psiholoski);
        }
        
        if (!observableOstaleKarakterne.isEmpty()) {
            Set<String> karakterne = new HashSet<>();
            karakterne.addAll(observableOstaleKarakterne);
            karakterneOsobine.setOstaleKarakterneOsobine(karakterne);
        }             

        osumnjiceni.setKarakterneOsobine(karakterneOsobine);
        
        if (!observableFotografije.isEmpty()) {
            Set<String> fotkeURL = new HashSet<>();
            fotkeURL.addAll(observableFotografije);
            osumnjiceni.setFotografijeURL(fotkeURL);
        }
        
        if (otisakPrsta.getText() != null) {
            if (!otisakPrsta.getText().equals("")) 
                osumnjiceni.setOtisakPrstaURL(otisakPrsta.getText());
        } else poruka = poruka.concat("otisak prsta; ");
        
        if (poruka.equals("Unesite: ")) 
            delegate.dodajKriminalca(osumnjiceni);
        else info.setText(poruka);
    }
    
    @FXML private void dodajURL(ActionEvent event) {
        observableFotografije.add(upisaniURL.getText());
        upisaniURL.clear();
    }
    
    @FXML private void obrisiURL(ActionEvent event) {
        observableFotografije.remove(fotografije.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajAlias(ActionEvent event) {
        observablePopisAliasa.add(upisaniAlias.getText());
        upisaniAlias.clear();
    }
    
    @FXML private void obrisiAlias(ActionEvent event) {
        observablePopisAliasa.remove(popisAliasa.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajAdresu(ActionEvent event) {
        observableAdrese.add(upisanaAdresa.getText());
        upisanaAdresa.clear();
    }
    
    @FXML private void obrisiAdresu(ActionEvent event) {
        observableAdrese.remove(poznateAdrese.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajSlucaj(ActionEvent event) {
        observableSlucajevi.add(upisaniSlucaj.getText());
        upisaniSlucaj.clear();
    }
    
    @FXML private void obrisiSlucaj(ActionEvent event) {
        observableSlucajevi.remove(popisPovezanihSlucajeva.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajKriminalca(ActionEvent event) {
        observableKriminalci.add(upisaniKriminalac.getText());
        upisaniKriminalac.clear();
    }
    
    @FXML private void obrisiKriminalca(ActionEvent event) {
        observableKriminalci.remove(popisPovezanihKriminalaca.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajTetovazu(ActionEvent event) {
        observableTetovaze.add(upisanaTetovaza.getText());
        upisanaTetovaza.clear();
    }
    
    @FXML private void obrisiTetovazu(ActionEvent event) {
        observableTetovaze.remove(tetovaze.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajBolest(ActionEvent event) {
        observableBolesti.add(upisanaBolest.getText());
        upisanaBolest.clear();
    }
    
    @FXML private void obrisiBolest(ActionEvent event) {
        observableBolesti.remove(bolesti.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajFizickiNedostatak(ActionEvent event) {
        observableFizickiNedostatci.add(upisaniNedostatak.getText());
        upisaniNedostatak.clear();
    }
    
    @FXML private void obrisiFizickiNedostatak(ActionEvent event) {
        observableFizickiNedostatci.remove(fizickiNedostatci.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajFizickuOsobinu(ActionEvent event) {
        observableOstaleFizicke.add(upisanaFizickaOsobina.getText());
        upisanaFizickaOsobina.clear();
    }
    
    @FXML private void obrisiFizickuOsobinu(ActionEvent event) {
        observableOstaleFizicke.remove(ostaleFizickeOsobine.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajPsiholoskiProblem(ActionEvent event) {
        observablePsiholoski.add(upisaniPsiholoski.getText());
        upisaniPsiholoski.clear();
    }
    
    @FXML private void obrisiPsiholoskiProblem(ActionEvent event) {
        observablePsiholoski.remove(psiholoskiProblemi.getSelectionModel().getSelectedIndex());
    }
    
    @FXML private void dodajKarakternuOsobinu(ActionEvent event) {
        observableOstaleKarakterne.add(upisanaKarakternaOsobina.getText());
        upisanaKarakternaOsobina.clear();
    }
    
    @FXML private void obrisiKarakternuOsobinu(ActionEvent event) {
        observableOstaleKarakterne.remove(ostaleKarakterneOsobine.getSelectionModel().getSelectedIndex());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}
