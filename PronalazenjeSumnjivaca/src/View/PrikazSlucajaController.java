package View;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

import Controller.ViewDelegate;
import Model.Dogadaj;
import Model.Dokaz;
import Model.GenerirajPDF;
import Model.Osoba;
import Model.Slucaj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
    
    @FXML ListView<String> popisOsumnjicenih;
    @FXML ListView<String> popisSvjedoka;
    @FXML ListView<String> popisDokaza;
    @FXML ListView<String> popisPolicajaca;
    @FXML ListView<String> popisDogadaja;
    
    @FXML Button ispis;
    
    Slucaj slucaj;
    int index;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void prikaziPodatke(Slucaj slucaj) {
    	index=0;
		this.slucaj = slucaj;

		if (!slucaj.getFotografijeSlučaja().isEmpty() && slucaj.getFotografijeSlučaja() != null) {

			ArrayList<String> lista = new ArrayList<>();
			lista.addAll(slucaj.getFotografijeSlučaja());
			Image img = new Image(lista.get(0));
			fotografija.setImage(img);
			fotografija.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
						if (lista.size() > 1) {
							index++;
							Image img2 = new Image(lista.get(index % lista.size()));
							fotografija.setImage(img2);
						}
					}
				}

			});
		}

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
        
        List<Osoba> listaOsumnjicenih = new ArrayList<>();
        ObservableList<String> observableOsumnjiceni = FXCollections.observableArrayList();
        if (!slucaj.getPopisOsumnjicenih().isEmpty()) {
            listaOsumnjicenih.addAll(slucaj.getPopisOsumnjicenih());
            for (Osoba krimi : listaOsumnjicenih) {
                if (krimi.getIme() != null)
                    observableOsumnjiceni.add(krimi.getIme() + " " + krimi.getPrezime() + " " + krimi.getOib());
            }
            popisOsumnjicenih.setItems(observableOsumnjiceni);   
        }
        
        ObservableList<String> observableSvjedoci = FXCollections.observableArrayList();
        List<Osoba> listaSvjedoka = new ArrayList<>();
        if (!slucaj.getPopisSvjedoka().isEmpty()) {
            listaSvjedoka.addAll(slucaj.getPopisSvjedoka());
            for (Osoba svjedok : listaSvjedoka) {
                if (svjedok.getIme() != null)
                    observableSvjedoci.add(svjedok.getIme() + " " + svjedok.getPrezime() + " " + svjedok.getOib());
            }
            popisSvjedoka.setItems(observableSvjedoci);
        }
        
        ObservableList<String> observableDokazi = FXCollections.observableArrayList();
        List<Dokaz> listaDokaza = new ArrayList<>();
        if (!slucaj.getPopisDokaza().isEmpty()) {
            listaDokaza.addAll(slucaj.getPopisDokaza());
            for (Dokaz dokaz : listaDokaza) {
                if (dokaz.getID() != null)
                    observableDokazi.add(dokaz.getID() + " " + dokaz.getNaziv());
            }
            popisDokaza.setItems(observableDokazi);
        }
        
        ObservableList<String> observablePolicajci = FXCollections.observableArrayList();
        List<Osoba> listaPolicajaca = new ArrayList<>();
        if (!slucaj.getPopisPolicajaca().isEmpty()) {
            listaPolicajaca.addAll(slucaj.getPopisPolicajaca());
            for (Osoba policajac : listaPolicajaca) {
                if (policajac.getIme() != null)
                    observablePolicajci.add(policajac.getIme() + " " + policajac.getPrezime() + " " + policajac.getOib());
            }
            popisPolicajaca.setItems(observablePolicajci);
        }
        
        ObservableList<String> observableDogadaji = FXCollections.observableArrayList();
        List<Dogadaj> listaDogadaja = new ArrayList<>();
        if (!slucaj.getPopisDogadaja().isEmpty()) {
            listaDogadaja.addAll(slucaj.getPopisDogadaja());
            for (Dogadaj dogadaj : listaDogadaja) {
                if (dogadaj.getDogadajID() != null)
                    observableDogadaji.add(dogadaj.getDogadajID() + " " + dogadaj.getNaziv());
            }
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
