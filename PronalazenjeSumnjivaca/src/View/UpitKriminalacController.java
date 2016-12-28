package View;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import Model.Osumnjiceni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class UpitKriminalacController implements Initializable, ControlledScreen {

	ViewDelegate delegate;
        
        @FXML TextField ime;
        @FXML TextField prezime;
        @FXML TextField oib;
        @FXML TextField adresa;
        @FXML TextField brojTelefona;
        @FXML ComboBox status;
        @FXML TextArea opisKriminalnihDjelatnosti;
        @FXML TextArea popisAliasa;
        @FXML TextArea poznateAdrese;
        @FXML TextArea popisPovezanihSlucajeva;
        @FXML TextArea popisPovezanihKriminalaca;
        
        @FXML ComboBox spol;
        @FXML TextField rasa;
        @FXML TextField visina;
        @FXML TextField tezina;
        @FXML TextField godine;
        @FXML TextField bojaKose;
        @FXML TextField oblikFrizure;
        @FXML TextField oblikGlave;
        @FXML TextField bojaOciju;
        @FXML TextField gradaTijela;
        @FXML TextArea tetovaze;
        @FXML TextArea fizickiNedostatci;
        @FXML TextArea bolesti;
        @FXML TextArea ostaleFizickeOsobine;
        
        @FXML TextField nacinGovora;
        @FXML TextField razinaApstraktneInteligencije;
        @FXML TextArea psiholoskiProblemi;
        @FXML TextArea ostaleKarakterneOsobine;
        
	
	@Override
	public void init(ViewDelegate delegate) {
		this.delegate = delegate;		
	}
        
        /**
         * 
         * Izvlacimo upisane podatke iz View-a i saljemo ih u Controller 
         */
        @FXML private void postaviUpit(ActionEvent event) {
            Osumnjiceni osumnjiceni = new Osumnjiceni();
            
            osumnjiceni.setIme(ime.getText());
            osumnjiceni.setPrezime(prezime.getText());
            osumnjiceni.setOib(Integer.parseInt(oib.getText()));
            
            delegate.posaljiUpitKriminalac(osumnjiceni);
        }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
