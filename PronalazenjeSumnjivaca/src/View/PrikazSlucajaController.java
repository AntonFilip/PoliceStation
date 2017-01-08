package View;

import Controller.ViewDelegate;
import Model.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.MalformedURLException;
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
 *
 * @author Karmela
 */
public class PrikazSlucajaController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML ImageView fotografija;
    @FXML Label broj;
    @FXML Label naziv;
    @FXML Label opis;
    @FXML Label glavniOsumnjiceni;
    @FXML Label status;
    
    @FXML ListView popisOsumnjicenih;
    @FXML ListView popisSvjedoka;
    @FXML ListView popisDokaza;
    @FXML ListView popisPolicajaca;
    @FXML ListView popisDogadaja;
    
    @FXML Button ispis;
    
    Slucaj slucaj;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void prikaziPodatke(Slucaj slucaj) {
        
        this.slucaj = slucaj;
        
        //fotografija.setImage((Image) slucaj.getFotografijeSlučaja());
        
    	if(slucaj.getBrojSlucaja() != null)
    		broj.setText(Integer.toString(slucaj.getBrojSlucaja()));
        if(slucaj.getNazivSlucaja() != null)
        	naziv.setText(slucaj.getNazivSlucaja());
        if(slucaj.getOpis() != null)
        	opis.setText(slucaj.getOpis());
        if(slucaj.getGlavniOsumnjiceni() != null)
        	glavniOsumnjiceni.setText(Long.toString(slucaj.getGlavniOsumnjiceni().getOib()));
        if(slucaj.getStatus() != null)
        	status.setText(slucaj.getStatus().toString());
        
        List<String> list1 = new ArrayList<>();
        List<Osoba> listaOsumnjicenih = new ArrayList<>();
        ObservableList<String> observableOsumnjiceni = FXCollections.emptyObservableList();
        if (!slucaj.getPopisOsumnjicenih().isEmpty()) {
            listaOsumnjicenih.addAll(slucaj.getPopisOsumnjicenih());
            for (Osoba krimi : listaOsumnjicenih) {
                list1.add(krimi.getIme() + " " + krimi.getPrezime() + " " + krimi.getOib());
            }
            observableOsumnjiceni = FXCollections.observableList(list1);
            popisOsumnjicenih.setItems(observableOsumnjiceni);   
        }
        
        List<String> list2 = new ArrayList<>();
        ObservableList<String> observableSvjedoci = FXCollections.emptyObservableList();
        List<Osoba> listaSvjedoka = new ArrayList<>();
        if (!slucaj.getPopisSvjedoka().isEmpty()) {
            listaSvjedoka.addAll(slucaj.getPopisSvjedoka());
            for (Osoba svjedok : listaSvjedoka) {
                list2.add(svjedok.getIme() + " " + svjedok.getPrezime() + " " + svjedok.getOib());
            }
            observableSvjedoci = FXCollections.observableList(list2);
            popisSvjedoka.setItems(observableSvjedoci);
        }
        
        List<String> list3 = new ArrayList<>();
        ObservableList<String> observableDokazi = FXCollections.emptyObservableList();
        List<Dokaz> listaDokaza = new ArrayList<>();
        if (!slucaj.getPopisDokaza().isEmpty()) {
            listaDokaza.addAll(slucaj.getPopisDokaza());
            for (Dokaz dokaz : listaDokaza) {
                list3.add(dokaz.getID() + " " + dokaz.getNaziv());
            }
            observableDokazi = FXCollections.observableList(list3);
            popisDokaza.setItems(observableDokazi);
        }
        
        List<String> list4 = new ArrayList<>();
        ObservableList<String> observablePolicajci = FXCollections.emptyObservableList();
        List<Osoba> listaPolicajaca = new ArrayList<>();
        if (!slucaj.getPopisPolicajaca().isEmpty()) {
            listaPolicajaca.addAll(slucaj.getPopisPolicajaca());
            for (Osoba policajac : listaPolicajaca) {
                list4.add(policajac.getIme() + " " + policajac.getPrezime() + " " + policajac.getOib());
            }
            observablePolicajci = FXCollections.observableList(list4);
            popisPolicajaca.setItems(observablePolicajci);
        }
        
        List<String> list5 = new ArrayList<>();
        ObservableList<String> observableDogadaji = FXCollections.emptyObservableList();
        List<Dogadaj> listaDogadaja = new ArrayList<>();
        if (!slucaj.getPopisDogadaja().isEmpty()) {
            listaDogadaja.addAll(slucaj.getPopisDogadaja());
            for (Dogadaj dogadaj : listaDogadaja) {
                list5.add(dogadaj.getDogadajID() + " " + dogadaj.getNaziv());
            }
            observableDogadaji = FXCollections.observableList(list5);
            popisDogadaja.setItems(observableDogadaji);
        }
        
    }
    
    @FXML private void generirajPDF(ActionEvent event) throws DocumentException, MalformedURLException, BadElementException, IOException {
        GenerirajPDF.generiraj(slucaj);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}
