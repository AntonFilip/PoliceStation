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
    @FXML ListView opisKriminalnihDjelatnosti;
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
        
        fotografija.setImage((Image) osumnjiceni.getFotografije());
        
        ime.setText(osumnjiceni.getIme());
        prezime.setText(osumnjiceni.getPrezime());
        oib.setText(Integer.toString(osumnjiceni.getOib()));
        adresa.setText(osumnjiceni.getAdresa());
        brojTelefona.setText(osumnjiceni.getBrojTelefona());
        if (osumnjiceni.getStatus() != null) {
            status.setText(osumnjiceni.getStatus().toString());
        }
        
        List<String> list = new ArrayList<>();
        list.add(osumnjiceni.getOpisKriminalnihDjelatnosti());
        ObservableList<String> observableList = FXCollections.observableList(list);
        if (!observableList.get(0).equals("")) {
            opisKriminalnihDjelatnosti.setItems(observableList);
        }
        
        list.clear();
        for (String alias : osumnjiceni.getPopisAliasa()) {
            list.add(alias);
        }
        observableList.clear();
        observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
            popisAliasa.setItems(observableList);
        }
        
        list.clear();
        observableList.clear();
        for (String adresa : osumnjiceni.getPoznateAdrese()) {
            list.add(adresa);
        }
        observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
        poznateAdrese.setItems(observableList);
        }
        
        list.clear();
        observableList.clear();
        for (Slucaj slucaj : osumnjiceni.getPovezaniSlucajevi()) {
            list.add(Integer.toString(slucaj.getBrojSlucaja()));
        }
        observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
        popisPovezanihSlucajeva.setItems(observableList);
        }
        
        list.clear();
        observableList.clear();
        for (Osumnjiceni krimi : osumnjiceni.getPopisPovezanihKriminalaca()) {
            list.add(Integer.toString(krimi.getOib()));
        }
        observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
        popisPovezanihKriminalaca.setItems(observableList);
        }
        
        spol.setText(osumnjiceni.getFizickeOsobine().getSpol());
        rasa.setText(osumnjiceni.getFizickeOsobine().getRasa());
        visina.setText(Integer.toString(osumnjiceni.getFizickeOsobine().getVisina()));
        tezina.setText(Integer.toString(osumnjiceni.getFizickeOsobine().getTezina()));
        godine.setText(Integer.toString(osumnjiceni.getFizickeOsobine().getGodine()));
        bojaKose.setText(osumnjiceni.getFizickeOsobine().getBojaKose());
        oblikFrizure.setText(osumnjiceni.getFizickeOsobine().getOblikFrizure());
        oblikGlave.setText(osumnjiceni.getFizickeOsobine().getOblikGlave());
        bojaOciju.setText(osumnjiceni.getFizickeOsobine().getBojaOciju());
        gradaTijela.setText(osumnjiceni.getFizickeOsobine().getGradaTijela().toString());
        
        list.clear();
        observableList.clear();
        list.add(osumnjiceni.getFizickeOsobine().getTetovaze());
        observableList = FXCollections.observableList(list);
        tetovaze.setItems(observableList);
        
        list.clear();
        observableList.clear();
        list.add(osumnjiceni.getFizickeOsobine().getFizickiNedostatci());
        observableList = FXCollections.observableList(list);
        fizickiNedostatci.setItems(observableList);
        
        list.clear();
        observableList.clear();
        list.add(osumnjiceni.getFizickeOsobine().getBolesti());
        observableList = FXCollections.observableList(list);
        bolesti.setItems(observableList);
        
        list.clear();
        observableList.clear();
        list.add(osumnjiceni.getFizickeOsobine().getOstalo());
        observableList = FXCollections.observableList(list);
        ostaleFizickeOsobine.setItems(observableList);
        
        nacinGovora.setText(osumnjiceni.getKarakterneOsobine().getNacinGovora());
        razinaInteligencije.setText(osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije());
        
        list.clear();
        observableList.clear();
        list.add(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi());
        observableList = FXCollections.observableList(list);
        psihickiProblemi.setItems(observableList);
        
        list.clear();
        observableList.clear();
        list.add(osumnjiceni.getKarakterneOsobine().getOstalo());
        observableList = FXCollections.observableList(list);
        ostaleKarakterneOsobine.setItems(observableList);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
}
