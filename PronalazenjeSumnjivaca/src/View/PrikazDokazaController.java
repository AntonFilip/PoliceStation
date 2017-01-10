package View;

import Controller.ViewDelegate;
import Model.Dokaz;
import Model.GenerirajPDF;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Karmela
 */
public class PrikazDokazaController implements Initializable, ControlledScreen {

    ViewDelegate delegate;
    
    @FXML ImageView fotografija;
    @FXML ImageView otisakPrsta;
    @FXML Label id;
    @FXML Label naziv;
    @FXML Label nazivSlucaja;
    @FXML Label krvnaGrupa;
    @FXML Label dnaSekvenca;
    @FXML Label tipOruzja;
    @FXML Button ispis;
    
    Dokaz dokaz;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
        
    }
    
    public void prikaziPodatke(Dokaz dokaz) {
        
        this.dokaz = dokaz;
        
        if (!dokaz.getFotografija().isEmpty() && dokaz.getFotografija() != null) {
			Image img = new Image(dokaz.getFotografija());
			fotografija.setImage(img);
		}
        
        if (!dokaz.getOtisakPrsta().isEmpty() && dokaz.getOtisakPrsta() != null) {

			ArrayList<String> lista = new ArrayList<>();
			lista.addAll(dokaz.getOtisakPrsta());
			Image img = new Image(lista.get(0));
			fotografija.setImage(img);
		}
        
        if (dokaz.getID() != null)
            id.setText(Integer.toString(dokaz.getID()));
        if (dokaz.getNaziv() != null)
            naziv.setText(dokaz.getNaziv());
        if (dokaz.getNazivSlucaja() != null)
            nazivSlucaja.setText(dokaz.getNazivSlucaja());
        if (dokaz.getKrvnaGrupa() != null)
            krvnaGrupa.setText(dokaz.getKrvnaGrupa().toString());
        if (dokaz.getDNASekvenca() != null)
            dnaSekvenca.setText(dokaz.getDNASekvenca().toString());
        if (dokaz.getTipOruzja() != null)
            tipOruzja.setText(dokaz.getTipOruzja().toString());
        
    }
    
    @FXML private void generirajPDF() throws DocumentException, IOException {
        GenerirajPDF.generiraj(dokaz);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
