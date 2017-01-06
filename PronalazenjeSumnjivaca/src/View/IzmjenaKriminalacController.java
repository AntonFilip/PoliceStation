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
    
    @FXML ListView listaURL;
    @FXML Button obrisiURL;
    @FXML Button dodajURL;
    @FXML TextField upisaniURL;

    @FXML TextField ime;
    @FXML TextField prezime;
    @FXML TextField oib;
    @FXML TextField adresa;
    @FXML TextField brojTelefona;
    @FXML ComboBox status;
    @FXML TextArea opisKriminalnihDjelatnosti;
    
    @FXML ListView popisAliasa;
    @FXML Button obrisiAlias;
    @FXML Button dodajAlias;
    @FXML TextField upisaniAlias;
    
    @FXML ListView poznateAdrese;
    @FXML Button obrisiAdresu;
    @FXML Button dodajAdresu;
    @FXML TextField upisanaAdresa;
    
    @FXML ListView popisPovezanihSlucajeva;
    @FXML Button obrisiSlucaj;
    @FXML Button dodajSlucaj;
    @FXML TextField upisaniSlucaj;
    
    @FXML ListView popisPovezanihKriminalaca;
    @FXML Button obrisiKriminalca;
    @FXML Button dodajKriminalca;
    @FXML TextField upisaniKriminalac;

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
    
    @FXML ListView tetovaze;
    @FXML Button obrisiTetovazu;
    @FXML Button dodajTetovazu;
    @FXML TextField upisanaTetovaza;
    
    @FXML ListView fizickiNedostatci;
    @FXML Button obrisiNedostatak;
    @FXML Button dodajNedostatak;
    @FXML TextField upisaniNedostatak;
    
    @FXML ListView bolesti;
    @FXML Button obrisiBolest;
    @FXML Button dodajBolest;
    @FXML TextField upisanaBolest;
    
    @FXML ListView ostaleFizickeOsobine;
    @FXML Button obrisiFizickuOsobinu;
    @FXML Button dodajFizickuOsobinu;
    @FXML TextField upisanaFizickaOsobina;

    @FXML TextField nacinGovora;
    @FXML ComboBox razinaApstraktneInteligencije;
    
    @FXML ListView psiholoskiProblemi;
    @FXML Button obrisiPsiholoski;
    @FXML Button dodajPsiholoski;
    @FXML TextField upisaniPsiholoski;
    
    @FXML ListView ostaleKarakterneOsobine; 
    @FXML Button obrisiKarakternuOsobinu;
    @FXML Button dodajKarakternuOsobinu;
    @FXML TextField upisanaKarakternaOsobina;
    
    @FXML TextField otisakPrsta;
    
    ObservableList<String> observableListaURL;
    ObservableList<String> observablePopisAliasa;
    ObservableList<String> observableTetovaze;
    ObservableList<String> observableBolesti;
    ObservableList<String> observableFizickiNedostatci;
    ObservableList<String> observableOstaleFizicke;
    ObservableList<String> observablePsiholoski;
    ObservableList<String> observableOstaleKarakterne;
    ObservableList<String> observableAdrese;
    ObservableList<String> observableSlucajevi;
    ObservableList<String> observableKriminalci;
    
    Osumnjiceni kriminalac;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void prikaziTrenutnePodatke(Osumnjiceni osumnjiceni) {
        
        kriminalac = osumnjiceni;
        
        ime.setText(osumnjiceni.getIme());
        prezime.setText(osumnjiceni.getPrezime());
        oib.setText(Long.toString(osumnjiceni.getOib()));
        adresa.setText(osumnjiceni.getAdresaPrebivalista().getAdresa() + ", " + osumnjiceni.getAdresaPrebivalista().getNazivMjesta());
        brojTelefona.setText(osumnjiceni.getBrojTelefona());
        status.setValue(osumnjiceni.getStatus().toString());
        opisKriminalnihDjelatnosti.setText(osumnjiceni.getOpisKriminalnihDjelatnosti());
        spol.setValue(osumnjiceni.getFizickeOsobine().getSpol().toString());
        rasa.setText(osumnjiceni.getFizickeOsobine().getRasa());
        visina.setText(osumnjiceni.getFizickeOsobine().getVisina().toString());
        tezina.setText(osumnjiceni.getFizickeOsobine().getTezina().toString());
        godine.setText(osumnjiceni.getFizickeOsobine().getGodine().toString());
        bojaKose.setText(osumnjiceni.getFizickeOsobine().getBojaKose());
        bojaOciju.setText(osumnjiceni.getFizickeOsobine().getBojaOciju());
        oblikGlave.setText(osumnjiceni.getFizickeOsobine().getOblikGlave());
        oblikFrizure.setText(osumnjiceni.getFizickeOsobine().getOblikFrizure());
        gradaTijela.setValue(osumnjiceni.getFizickeOsobine().getGradaTijela().toString());
        nacinGovora.setText(osumnjiceni.getKarakterneOsobine().getNacinGovora());
        razinaApstraktneInteligencije.setValue(osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije());
        otisakPrsta.setText(osumnjiceni.getOtisakPrstaURL());
        
        List<String> lista = new ArrayList<>();
        List<AdresaIMjestoStanovanja> listaAdresa = new ArrayList<>();
        listaAdresa.addAll(osumnjiceni.getPoznateAdrese());
        for (AdresaIMjestoStanovanja adresa : listaAdresa) {
            lista.add(adresa.getAdresa() + ", " + adresa.getNazivMjesta());
        }
        observableAdrese = FXCollections.observableList(lista);
        if (!observableAdrese.get(0).equals("")) {
            poznateAdrese.setItems(observableAdrese);
        }
        
        lista.clear();
        List<Slucaj> listaSlucajeva = new ArrayList<>();
        listaSlucajeva.addAll(osumnjiceni.getPovezaniSlucajevi());
        for (Slucaj slucaj : listaSlucajeva) {
            lista.add(slucaj.getBrojSlucaja() + " - " + slucaj.getNazivSlucaja());
        }
        observableSlucajevi = FXCollections.observableList(lista);
        if (!observableSlucajevi.get(0).equals("")) {
            popisPovezanihSlucajeva.setItems(observableSlucajevi);
        }
        
        lista.clear();
        List<Osumnjiceni> listaKriminalaca = new ArrayList<>();
        listaKriminalaca.addAll(osumnjiceni.getPopisPovezanihKriminalaca());
        for (Osumnjiceni krimi : listaKriminalaca) {
            lista.add(krimi.getIme() + " " + krimi.getPrezime() + " " + krimi.getOib().toString());
        }
        observableKriminalci = FXCollections.observableList(lista);
        if (!observableKriminalci.get(0).equals("")) {
            popisPovezanihKriminalaca.setItems(observableKriminalci);
        }
        
        ispuniListView(osumnjiceni.getFotografijeURL(), listaURL, observableListaURL);
        ispuniListView(osumnjiceni.getPopisAliasa(), popisAliasa, observablePopisAliasa);
        ispuniListView(osumnjiceni.getFizickeOsobine().getTetovaze(), tetovaze, observableTetovaze);
        ispuniListView(osumnjiceni.getFizickeOsobine().getBolesti(), bolesti, observableBolesti);
        ispuniListView(osumnjiceni.getFizickeOsobine().getFizickiNedostatci(), fizickiNedostatci, observableFizickiNedostatci);
        ispuniListView(osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine(), ostaleFizickeOsobine, observableOstaleFizicke);
        ispuniListView(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi(), psiholoskiProblemi, observablePsiholoski);
        ispuniListView(osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine(), ostaleKarakterneOsobine, observableOstaleKarakterne);
        
    }
    
    private void ispuniListView(Set<String> popis, ListView listView, ObservableList<String> observableList) {
        List<String> lista = new ArrayList<>();
        lista.addAll(popis);
        observableList = FXCollections.observableList(lista);
        if (!observableList.get(0).equals("")) {
            listView.setItems(observableList);
        }
    }
    
    @FXML private void dodajURL(ActionEvent event) {
        observableListaURL.add(upisaniURL.getText());
        upisaniURL.clear();  
    }
    
    @FXML private void obrisiURL(ActionEvent event) {
        observableListaURL.remove(listaURL.getSelectionModel().getSelectedIndex());
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
    
    @FXML private void spremiIzmjene(ActionEvent event) {
        
        kriminalac.setIme(ime.getText());
        kriminalac.setPrezime(prezime.getText());
        kriminalac.setOib(Long.parseLong(oib.getText()));
        kriminalac.setOtisakPrstaURL(otisakPrsta.getText());
        
        String[] adresaStanovanja = adresa.getText().split(",");
        kriminalac.getAdresaPrebivalista().setAdresa(adresaStanovanja[0].trim());
        kriminalac.getAdresaPrebivalista().setNazivMjesta(adresaStanovanja[1].trim());
        
        kriminalac.setBrojTelefona(brojTelefona.getText());
        kriminalac.setOpisKriminalnihDjelatnosti(opisKriminalnihDjelatnosti.getText());
        
        if (status.getValue() != null) {
            if (status.getValue().equals("Na slobodi")) {
                kriminalac.setStatus(TrenutniStatusKriminalca.sloboda);
            } else if (status.getValue().equals("U pritvoru")) {
                kriminalac.setStatus(TrenutniStatusKriminalca.u_pritvoru);
            } else if (status.getValue().equals("U zatvoru")) {
                kriminalac.setStatus(TrenutniStatusKriminalca.u_zatvoru);
            }
        }
               
        Set<String> izmjene = new HashSet<>();
        izmjene.addAll(observableListaURL);
        kriminalac.setFotografijeURL(izmjene);
        
        izmjene.clear();
        izmjene.addAll(observablePopisAliasa);
        kriminalac.setPopisAliasa(izmjene);
        
        Set<AdresaIMjestoStanovanja> izmjeneAdrese = new HashSet<>();
        for (String adresa : observableAdrese) {
            String[] adresaIMjesto = adresa.split(",");
            AdresaIMjestoStanovanja adr = new AdresaIMjestoStanovanja();
            adr.setAdresa(adresaIMjesto[0].trim());
            adr.setNazivMjesta(adresaIMjesto[1].trim());
            izmjeneAdrese.add(adr);
        }
        kriminalac.setPoznateAdrese(izmjeneAdrese);
        
        Set<Slucaj> izmjeneSlucajeva = new HashSet<>();
        for (String slucaj : observableSlucajevi) {
            String[] zapis = slucaj.split("-");
            Slucaj sl = new Slucaj();
            sl.setBrojSlucaja(Integer.parseInt(zapis[0].trim()));
            sl.setNazivSlucaja(zapis[1].trim());
            izmjeneSlucajeva.add(sl);
        }
        kriminalac.setPovezaniSlucajevi(izmjeneSlucajeva);
        
        Set<Osumnjiceni> izmjeneKrimi = new HashSet<>();
        for (String krimi : observableKriminalci) {
            String[] zapis = krimi.split(" ");
            Osumnjiceni krim = new Osumnjiceni();
            krim.setIme(zapis[0]);
            krim.setPrezime(zapis[1]);
            krim.setOib(Long.parseLong(zapis[2]));
            izmjeneKrimi.add(krim);
        }
        kriminalac.setPopisPovezanihKriminalaca(izmjeneKrimi);
        
        FizickeOsobine fizickeOsobine = new FizickeOsobine();
        fizickeOsobine.setBojaKose(bojaKose.getText());
        fizickeOsobine.setBojaOciju(bojaOciju.getText());
        fizickeOsobine.setGodine(Integer.parseInt(godine.getText()));
        if (gradaTijela.getValue() != null) {
            if (gradaTijela.getValue().equals("Slabija")) {
                fizickeOsobine.setGradaTijela(GradaTijela.slabija);
            } else if (gradaTijela.getValue().equals("Srednja")) {
                fizickeOsobine.setGradaTijela(GradaTijela.srednja);
            } else if (gradaTijela.getValue().equals("Jača")) {
                fizickeOsobine.setGradaTijela(GradaTijela.jaca);
            }
        }
        fizickeOsobine.setOblikFrizure(oblikFrizure.getText());
        fizickeOsobine.setOblikGlave(oblikGlave.getText());
        fizickeOsobine.setRasa(rasa.getText());
        if (spol.getValue() != null) {
            if (spol.getValue().equals("M")) {
                fizickeOsobine.setSpol(Spol.M);
            } else if (spol.getValue().equals("�")) {
                fizickeOsobine.setSpol(Spol.Ž);
            }
        }
        fizickeOsobine.setTezina(Float.parseFloat(tezina.getText()));
        fizickeOsobine.setVisina(Float.parseFloat(visina.getText()));
        
        izmjene.clear();
        izmjene.addAll(observableBolesti);
        fizickeOsobine.setBolesti(izmjene);
        
        izmjene.clear();
        izmjene.addAll(observableFizickiNedostatci);
        fizickeOsobine.setFizickiNedostatci(izmjene);
        
        izmjene.clear();
        izmjene.addAll(observableOstaleFizicke);
        fizickeOsobine.setOstaleFizickeOsobine(izmjene);
        
        izmjene.clear();
        izmjene.addAll(observableTetovaze);
        fizickeOsobine.setTetovaze(izmjene);
        
        kriminalac.setFizickeOsobine(fizickeOsobine);
        
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
        
        izmjene.clear();
        izmjene.addAll(observablePsiholoski);
        karakterneOsobine.setPsiholoskiProblemi(izmjene);
        
        izmjene.clear();
        izmjene.addAll(observableOstaleKarakterne);
        karakterneOsobine.setOstaleKarakterneOsobine(izmjene);
        
        kriminalac.setKarakterneOsobine(karakterneOsobine);
        
        delegate.spremiIzmjeneKriminalca(kriminalac);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}
