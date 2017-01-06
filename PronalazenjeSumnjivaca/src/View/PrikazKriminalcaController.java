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
        
        List<String> list = new ArrayList<>();
        ObservableList<String> observableList = FXCollections.emptyObservableList();
        if(osumnjiceni.getOpisKriminalnihDjelatnosti() != null){
        list.add(osumnjiceni.getOpisKriminalnihDjelatnosti());
        //list.add("blablalbla");
        observableList = FXCollections.observableList(list);
        if(!observableList.isEmpty())
       // if (!observableList.get(0).equals("")) {
            opisKriminalnihDjelatnosti.setItems(observableList);
       // }
        }
        list.clear();
        if(osumnjiceni.getPopisAliasa() != null){
        for (String alias : osumnjiceni.getPopisAliasa()) {
            list.add(alias);
        }
        observableList.clear();
        observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
            popisAliasa.setItems(observableList);
        }}
        
        list.clear();
        observableList.clear();
        if(osumnjiceni.getPoznateAdrese() != null){
        for (AdresaIMjestoStanovanja adresa : osumnjiceni.getPoznateAdrese()) {
            list.add(adresa.getAdresa() + ", " + adresa.getNazivMjesta());
        }
        observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
            poznateAdrese.setItems(observableList);
        }}
        
        list.clear();
        observableList.clear();
        if(osumnjiceni.getPovezaniSlucajevi() != null){
        for (Slucaj slucaj : osumnjiceni.getPovezaniSlucajevi()) {
        	if(slucaj.getBrojSlucaja() != null)
            list.add(Integer.toString(slucaj.getBrojSlucaja()));
        }
        observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
        popisPovezanihSlucajeva.setItems(observableList);
        }
        }
        list.clear();
        observableList.clear();
        for (Osumnjiceni krimi : osumnjiceni.getPopisPovezanihKriminalaca()) {
            list.add(Long.toString(krimi.getOib()));
        }
        observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
        popisPovezanihKriminalaca.setItems(observableList);
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
        
        list.clear();
        observableList.clear();
        if(osumnjiceni.getFizickeOsobine().getTetovaze() != null){
        list.addAll(osumnjiceni.getFizickeOsobine().getTetovaze());
        observableList = FXCollections.observableList(list);
        tetovaze.setItems(observableList);}
        
        list.clear();
        observableList.clear();
        if(osumnjiceni.getFizickeOsobine().getFizickiNedostatci() != null){
        list.addAll(osumnjiceni.getFizickeOsobine().getFizickiNedostatci());
        observableList = FXCollections.observableList(list);
        fizickiNedostatci.setItems(observableList);}
        
        list.clear();
        observableList.clear();
        if(osumnjiceni.getFizickeOsobine().getBolesti() != null){
        list.addAll(osumnjiceni.getFizickeOsobine().getBolesti());
        observableList = FXCollections.observableList(list);
        bolesti.setItems(observableList);}
        
        list.clear();
        observableList.clear();
        if(osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine() != null) {
        list.addAll(osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine());
        observableList = FXCollections.observableList(list);
        ostaleFizickeOsobine.setItems(observableList);}}
        if(osumnjiceni.getKarakterneOsobine() != null){
        if(osumnjiceni.getKarakterneOsobine().getNacinGovora() != null)
        	nacinGovora.setText(osumnjiceni.getKarakterneOsobine().getNacinGovora());
        if(osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije() != null)
        	razinaInteligencije.setText(osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije().toString());
        
        list.clear();
        observableList.clear();
        if(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi() != null){
        list.addAll(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi());
        observableList = FXCollections.observableList(list);
        psihickiProblemi.setItems(observableList);}
        
        list.clear();
        observableList.clear();
        if(osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine() != null){
        list.addAll(osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine());
        observableList = FXCollections.observableList(list);
        ostaleKarakterneOsobine.setItems(observableList);}}
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
}
