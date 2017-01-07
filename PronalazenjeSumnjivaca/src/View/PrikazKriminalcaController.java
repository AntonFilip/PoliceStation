package View;

import Controller.ViewDelegate;
import Model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 */
public class PrikazKriminalcaController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;   
    
    @FXML Button ispisPDF;
    @FXML ImageView fotografija;
    @FXML Label ime;
    @FXML Label prezime;
    @FXML Label oib;
    @FXML Label adresa;
    @FXML Label brojTelefona;
    @FXML Label status;
    @FXML ListView<String> opisKriminalnihDjelatnosti;
    @FXML ListView popisAliasa;
    @FXML ListView poznateAdrese;
    @FXML ListView popisPovezanihSlucajeva;
    @FXML ListView popisPovezanihKriminalaca;

    @FXML Label spol;
    @FXML Label rasa;
    @FXML Label visina;
    @FXML Label tezina;
    @FXML Label godine;
    @FXML Label bojaKose;
    @FXML Label oblikFrizure;
    @FXML Label oblikGlave;
    @FXML Label bojaOciju;
    @FXML Label gradaTijela;
    @FXML ListView tetovaze;
    @FXML ListView fizickiNedostatci;
    @FXML ListView bolesti;
    @FXML ListView ostaleFizickeOsobine;

    @FXML Label nacinGovora;
    @FXML Label razinaInteligencije;
    @FXML ListView psihickiProblemi;
    @FXML ListView ostaleKarakterneOsobine;

    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void prikaziPodatke(Osumnjiceni osumnjiceni) {
        
        //fotografija.setImage((Image) osumnjiceni.getFotografijeURL());
        if(osumnjiceni.getIme() != null)
        	ime.setText(osumnjiceni.getIme());
        if(osumnjiceni.getPrezime() != null)
        	prezime.setText(osumnjiceni.getPrezime());
        if(osumnjiceni.getOib() != null)
        	oib.setText(Long.toString(osumnjiceni.getOib()));
        if(osumnjiceni.getAdresaPrebivalista().getAdresa() != null)
        	adresa.setText(osumnjiceni.getAdresaPrebivalista().getAdresa());
        if(osumnjiceni.getBrojTelefona() != null)
        	brojTelefona.setText(osumnjiceni.getBrojTelefona());
        if (osumnjiceni.getStatus() != null) {
            status.setText(osumnjiceni.getStatus().toString());
        }
        
        List<String> list1 = new ArrayList<>();
        ObservableList<String> observableOpisKriminalnihDjelatnosti = FXCollections.emptyObservableList();
        if(osumnjiceni.getOpisKriminalnihDjelatnosti() != null){
            list1.add(osumnjiceni.getOpisKriminalnihDjelatnosti());
            observableOpisKriminalnihDjelatnosti = FXCollections.observableList(list1);
            opisKriminalnihDjelatnosti.setItems(observableOpisKriminalnihDjelatnosti);
        }
        
        List<String> list2 = new ArrayList<>();
        ObservableList<String> observablePopisAliasa = FXCollections.emptyObservableList();
        if(!osumnjiceni.getPopisAliasa().isEmpty()){
            for (String alias : osumnjiceni.getPopisAliasa()) {
                list2.add(alias);
            } 
            observablePopisAliasa = FXCollections.observableList(list2);
            popisAliasa.setItems(observablePopisAliasa);
        }
        
        List<String> list3 = new ArrayList<>();
        ObservableList<String> observableAdrese = FXCollections.emptyObservableList();
        if(!osumnjiceni.getPoznateAdrese().isEmpty()){
            for (AdresaIMjestoStanovanja adresa : osumnjiceni.getPoznateAdrese()) {
                list3.add(adresa.getAdresa() + ", " + adresa.getNazivMjesta());
            }
            observableAdrese = FXCollections.observableList(list3);
            poznateAdrese.setItems(observableAdrese);
        }
        
        List<String> list4 = new ArrayList<>();
        ObservableList<String> observableSlucajevi = FXCollections.emptyObservableList();
        if(osumnjiceni.getPovezaniSlucajevi() != null){
            for (Slucaj slucaj : osumnjiceni.getPovezaniSlucajevi()) {
        	if(slucaj.getBrojSlucaja() != null)
                list4.add(Integer.toString(slucaj.getBrojSlucaja()));
            }
            observableSlucajevi = FXCollections.observableList(list4);
            popisPovezanihSlucajeva.setItems(observableSlucajevi);
        }
        
        List<String> list5 = new ArrayList<>();
        ObservableList<String> observableKriminalci = FXCollections.emptyObservableList();
        if (!osumnjiceni.getPopisPovezanihKriminalaca().isEmpty()) {
            for (Osumnjiceni krimi : osumnjiceni.getPopisPovezanihKriminalaca()) {
                list5.add(Long.toString(krimi.getOib()));
            }
            observableKriminalci = FXCollections.observableList(list5);
            popisPovezanihKriminalaca.setItems(observableKriminalci);
        }
        if(osumnjiceni.getFizickeOsobine() != null){
        if(osumnjiceni.getFizickeOsobine().getSpol() != null)
        	spol.setText(osumnjiceni.getFizickeOsobine().getSpol().toString());
        if(osumnjiceni.getFizickeOsobine().getRasa() != null)
        	rasa.setText(osumnjiceni.getFizickeOsobine().getRasa());
        if(osumnjiceni.getFizickeOsobine().getVisina() != null)
        	visina.setText(Float.toString(osumnjiceni.getFizickeOsobine().getVisina()));
        if(osumnjiceni.getFizickeOsobine().getTezina() != null)
        	tezina.setText(Float.toString(osumnjiceni.getFizickeOsobine().getTezina()));
        if(osumnjiceni.getFizickeOsobine().getGodine() != null)
        	godine.setText(Integer.toString(osumnjiceni.getFizickeOsobine().getGodine()));
        if(osumnjiceni.getFizickeOsobine().getBojaKose() != null)
        	bojaKose.setText(osumnjiceni.getFizickeOsobine().getBojaKose());
        if(osumnjiceni.getFizickeOsobine().getOblikFrizure() != null)
        	oblikFrizure.setText(osumnjiceni.getFizickeOsobine().getOblikFrizure());
        if(osumnjiceni.getFizickeOsobine().getOblikGlave() != null)
        	oblikGlave.setText(osumnjiceni.getFizickeOsobine().getOblikGlave());
        if(osumnjiceni.getFizickeOsobine().getBojaOciju() != null)
        	bojaOciju.setText(osumnjiceni.getFizickeOsobine().getBojaOciju());
        if(osumnjiceni.getFizickeOsobine().getGradaTijela() != null)
        	gradaTijela.setText(osumnjiceni.getFizickeOsobine().getGradaTijela().toString());
        
        List<String> list6 = new ArrayList<>();
        ObservableList<String> observableTetovaze = FXCollections.emptyObservableList();
        if(osumnjiceni.getFizickeOsobine().getTetovaze() != null){
        list6.addAll(osumnjiceni.getFizickeOsobine().getTetovaze());
        observableTetovaze = FXCollections.observableList(list6);
        tetovaze.setItems(observableTetovaze);}
        
        List<String> list7 = new ArrayList<>();
        ObservableList<String> observableNedostatci = FXCollections.emptyObservableList();
        if(osumnjiceni.getFizickeOsobine().getFizickiNedostatci() != null){
        list7.addAll(osumnjiceni.getFizickeOsobine().getFizickiNedostatci());
        observableNedostatci = FXCollections.observableList(list7);
        fizickiNedostatci.setItems(observableNedostatci);}
        
        List<String> list8 = new ArrayList<>();
        ObservableList<String> observableBolesti = FXCollections.emptyObservableList();
        if(osumnjiceni.getFizickeOsobine().getBolesti() != null){
        list8.addAll(osumnjiceni.getFizickeOsobine().getBolesti());
        observableBolesti = FXCollections.observableList(list8);
        bolesti.setItems(observableBolesti);}
        
        List<String> list9 = new ArrayList<>();
        ObservableList<String> observableOstaleFizicke = FXCollections.emptyObservableList();
        if(osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine() != null) {
        list9.addAll(osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine());
        observableOstaleFizicke = FXCollections.observableList(list9);
        ostaleFizickeOsobine.setItems(observableOstaleFizicke);}
        }
        
        if(osumnjiceni.getKarakterneOsobine() != null){
        if(osumnjiceni.getKarakterneOsobine().getNacinGovora() != null)
        	nacinGovora.setText(osumnjiceni.getKarakterneOsobine().getNacinGovora());
        if(osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije() != null)
        	razinaInteligencije.setText(osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije().toString());
        
        List<String> list10 = new ArrayList<>();
        ObservableList<String> observablePsiholoski = FXCollections.emptyObservableList();
        if(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi() != null){
        list10.addAll(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi());
        observablePsiholoski = FXCollections.observableList(list10);
        psihickiProblemi.setItems(observablePsiholoski);}
        
        List<String> list11 = new ArrayList<>();
        ObservableList<String> observableOstaleKarakterne = FXCollections.emptyObservableList();
        if(osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine() != null){
        list11.addAll(osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine());
        observableOstaleKarakterne = FXCollections.observableList(list11);
        ostaleKarakterneOsobine.setItems(observableOstaleKarakterne);}}
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
}
