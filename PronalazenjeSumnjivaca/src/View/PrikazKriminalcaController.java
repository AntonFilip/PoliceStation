package View;

import Controller.ViewDelegate;
import Model.*;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    
    Osumnjiceni osumnjiceni;

    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void prikaziPodatke(Osumnjiceni osumnjiceni) {
        this.osumnjiceni = osumnjiceni;
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
        
        ObservableList<String> observableOpisKriminalnihDjelatnosti = FXCollections.observableArrayList();
        if(osumnjiceni.getOpisKriminalnihDjelatnosti() != null){
            observableOpisKriminalnihDjelatnosti.add(osumnjiceni.getOpisKriminalnihDjelatnosti());
            opisKriminalnihDjelatnosti.setItems(observableOpisKriminalnihDjelatnosti);
        }
        
        ObservableList<String> observablePopisAliasa = FXCollections.observableArrayList();
        if(!osumnjiceni.getPopisAliasa().isEmpty()){           
            observablePopisAliasa.addAll(osumnjiceni.getPopisAliasa());
            popisAliasa.setItems(observablePopisAliasa);
        }
        
        ObservableList<String> observableAdrese = FXCollections.observableArrayList();
        if(!osumnjiceni.getPoznateAdrese().isEmpty()){
            for (AdresaIMjestoStanovanja adresa : osumnjiceni.getPoznateAdrese()) {
                observableAdrese.add(adresa.getAdresa() + ", " + adresa.getNazivMjesta() + ", " + adresa.getPbrMjesto().toString());
            }
            poznateAdrese.setItems(observableAdrese);
        }
        
        ObservableList<String> observableSlucajevi = FXCollections.observableArrayList();
        if(osumnjiceni.getPovezaniSlucajevi() != null){
            for (Slucaj slucaj : osumnjiceni.getPovezaniSlucajevi()) {
        	if(slucaj.getBrojSlucaja() != null)
                observableSlucajevi.add(Integer.toString(slucaj.getBrojSlucaja()));
            }
            popisPovezanihSlucajeva.setItems(observableSlucajevi);
        }
        
        ObservableList<String> observableKriminalci = FXCollections.observableArrayList();
        if (!osumnjiceni.getPopisPovezanihKriminalaca().isEmpty()) {
            for (Osumnjiceni krimi : osumnjiceni.getPopisPovezanihKriminalaca()) {
                observableKriminalci.add(Long.toString(krimi.getOib()));
            }
            popisPovezanihKriminalaca.setItems(observableKriminalci);
        }
        
        if (osumnjiceni.getFizickeOsobine() != null) {
            if (osumnjiceni.getFizickeOsobine().getSpol() != null) {
                spol.setText(osumnjiceni.getFizickeOsobine().getSpol().toString());
            }
            if (osumnjiceni.getFizickeOsobine().getRasa() != null) {
                rasa.setText(osumnjiceni.getFizickeOsobine().getRasa());
            }
            if (osumnjiceni.getFizickeOsobine().getVisina() != null) {
                visina.setText(Float.toString(osumnjiceni.getFizickeOsobine().getVisina()));
            }
            if (osumnjiceni.getFizickeOsobine().getTezina() != null) {
                tezina.setText(Float.toString(osumnjiceni.getFizickeOsobine().getTezina()));
            }
            if (osumnjiceni.getFizickeOsobine().getGodine() != null) {
                godine.setText(Integer.toString(osumnjiceni.getFizickeOsobine().getGodine()));
            }
            if (osumnjiceni.getFizickeOsobine().getBojaKose() != null) {
                bojaKose.setText(osumnjiceni.getFizickeOsobine().getBojaKose());
            }
            if (osumnjiceni.getFizickeOsobine().getOblikFrizure() != null) {
                oblikFrizure.setText(osumnjiceni.getFizickeOsobine().getOblikFrizure());
            }
            if (osumnjiceni.getFizickeOsobine().getOblikGlave() != null) {
                oblikGlave.setText(osumnjiceni.getFizickeOsobine().getOblikGlave());
            }
            if (osumnjiceni.getFizickeOsobine().getBojaOciju() != null) {
                bojaOciju.setText(osumnjiceni.getFizickeOsobine().getBojaOciju());
            }
            if (osumnjiceni.getFizickeOsobine().getGradaTijela() != null) {
                gradaTijela.setText(osumnjiceni.getFizickeOsobine().getGradaTijela().toString());
            }

            ObservableList<String> observableTetovaze = FXCollections.observableArrayList();
            if (osumnjiceni.getFizickeOsobine().getTetovaze() != null) {
                observableTetovaze.addAll(osumnjiceni.getFizickeOsobine().getTetovaze());
                tetovaze.setItems(observableTetovaze);
            }

            ObservableList<String> observableNedostatci = FXCollections.observableArrayList();
            if (osumnjiceni.getFizickeOsobine().getFizickiNedostatci() != null) {
                observableNedostatci.addAll(osumnjiceni.getFizickeOsobine().getFizickiNedostatci());
                fizickiNedostatci.setItems(observableNedostatci);
            }

            ObservableList<String> observableBolesti = FXCollections.observableArrayList();
            if (osumnjiceni.getFizickeOsobine().getBolesti() != null) {
                observableBolesti.addAll(osumnjiceni.getFizickeOsobine().getBolesti());
                bolesti.setItems(observableBolesti);
            }

            ObservableList<String> observableOstaleFizicke = FXCollections.observableArrayList();
            if (osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine() != null) {
                observableOstaleFizicke.addAll(osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine());
                ostaleFizickeOsobine.setItems(observableOstaleFizicke);
            }
        }
        
        if (osumnjiceni.getKarakterneOsobine() != null) {
            if (osumnjiceni.getKarakterneOsobine().getNacinGovora() != null) {
                nacinGovora.setText(osumnjiceni.getKarakterneOsobine().getNacinGovora());
            }
            if (osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije() != null) {
                razinaInteligencije.setText(osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije().toString());
            }

            ObservableList<String> observablePsiholoski = FXCollections.observableArrayList();
            if (osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi() != null) {
                observablePsiholoski.addAll(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi());
                psihickiProblemi.setItems(observablePsiholoski);
            }

            ObservableList<String> observableOstaleKarakterne = FXCollections.observableArrayList();
            if (osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine() != null) {
                observableOstaleKarakterne.addAll(osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine());
                ostaleKarakterneOsobine.setItems(observableOstaleKarakterne);
            }
        }
    }
    
    @FXML private void generirajPDF(ActionEvent event) throws DocumentException, IOException {
        GenerirajPDF.generiraj(osumnjiceni);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
}
