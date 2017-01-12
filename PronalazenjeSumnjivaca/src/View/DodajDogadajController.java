package View;

import Model.Dogadaj;
import Model.PristupBaziPodataka;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Karmela
 */
public class DodajDogadajController implements Initializable {
    
    DialogManager delegate;
    
    @FXML TextField nazivDogadaja;
    @FXML TextField pbrMjestaDogadaja;
    @FXML TextField adresaMjestaDogadaja;
    @FXML TextField nazivMjestaDogadaja;
    @FXML TextField vrijemeDogadaja;
    @FXML Button dodaj;
    @FXML Label info;
    
    public void init(DialogManager delegate) {
        this.delegate = delegate;
    }    
    
    @FXML private void dodajDogadaj (ActionEvent event) {
        
        Dogadaj dogadaj = new Dogadaj();
        
        String poruka = "Unesite: ";
        Boolean pogresanFormat=false;
        Boolean pogresanPbr=false;
        Boolean loseMjesto=false;
        String IspisGreske="";
        
        if (nazivDogadaja.getText() != null)
            if (!nazivDogadaja.getText().isEmpty()) 
                dogadaj.setNaziv(nazivDogadaja.getText());
            else
                poruka = poruka.concat("naziv; ");
        
        if (pbrMjestaDogadaja.getText() != null)
            if (!pbrMjestaDogadaja.getText().isEmpty()) 
                try {
                	dogadaj.setPbrMjesto(Integer.parseInt(pbrMjestaDogadaja.getText()));
                }catch (Exception e) {
					pogresanFormat=true;
				}
            else 
                poruka = poruka.concat("poštanski broj; ");
        
        if (adresaMjestaDogadaja.getText() != null)
            if (!adresaMjestaDogadaja.getText().isEmpty())
                dogadaj.setAdresa(adresaMjestaDogadaja.getText());
            else 
                poruka = poruka.concat("adresa; ");
        
        if (nazivMjestaDogadaja.getText() != null)
            if (!nazivMjestaDogadaja.getText().isEmpty())
                dogadaj.setNazivMjesto(nazivMjestaDogadaja.getText());
            else poruka = poruka.concat("naziv mjesta; ");
        
        if (vrijemeDogadaja.getText() != null)
            if (!vrijemeDogadaja.getText().isEmpty())
            	try{
            		dogadaj.setVrijeme(LocalDateTime.parse(vrijemeDogadaja.getText()));
            	}
            	catch (DateTimeParseException e) {
            		pogresanFormat=true;
				}
            else 
                poruka = poruka.concat("vrijeme; ");
        if(!pogresanPbr && !PristupBaziPodataka.provjeriNazivMjesta(pbrMjestaDogadaja.getText(), nazivMjestaDogadaja.getText())){
        	loseMjesto=true;
        }
        
        if (poruka.equals("Unesite: ") && !pogresanFormat && !pogresanPbr && !loseMjesto )
            delegate.dodajDogadaj(dogadaj);
        else {
        	if(!poruka.equals("Unesite: ")) IspisGreske+=poruka;
        	if(pogresanFormat) {
        		if(!IspisGreske.equals("")) IspisGreske+="\n";
        		IspisGreske+="Unijeli ste pogrešan format datuma.";
        	}
        	if(pogresanPbr){
        		if(!IspisGreske.equals("")) IspisGreske+="\n";
        		IspisGreske+="Unijeli ste pogrešan poštanski broj mjesta.";
        	}
        	if(loseMjesto){
        		if(!IspisGreske.equals("")) IspisGreske+="\n";
        		IspisGreske+="Unjeli ste pogrešne podatke za mjesto događaja.";
        	}
        	info.setText(IspisGreske);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo
    }

    
    
}
