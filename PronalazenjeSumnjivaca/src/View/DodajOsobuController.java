package View;

import java.net.URL;
import java.util.ResourceBundle;

import Model.AdresaIMjestoStanovanja;
import Model.Osoba;
import Model.PristupBaziPodataka;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Karmela
 */
public class DodajOsobuController implements Initializable {

	DialogManager delegate;
	String tipOsobe;

	@FXML TextField ime;
	@FXML TextField prezime;
	@FXML TextField oib;
	@FXML TextField ulica;
	@FXML TextField mjesto;
	@FXML TextField pbr;

	@FXML Button dodajOsobu;
	@FXML Label info;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}    

	@FXML private void dodajOsobu() {

		Osoba osoba = new Osoba();
		
		String ispisGreske="";
		String poruka = "Unesite: ";
		Boolean pogresanPbr=false;
		Boolean nePostojiUbazi=false;
		String nePostoji="";
		boolean pogresanOib=false;
		
		if (ime.getText() != null) {
			if (!ime.getText().isEmpty()) {
				osoba.setIme(ime.getText());
			} else {
				poruka = poruka.concat("ime; ");
			}
		}

		if (prezime.getText() != null) {
			if (!prezime.getText().isEmpty()) {
				osoba.setPrezime(prezime.getText());
			} else {
				poruka = poruka.concat("prezime; ");
			}
		}

		if (oib.getText() != null) {
			if (!oib.getText().isEmpty()) {
				try{
					osoba.setOib(Long.parseLong(oib.getText()));
				}catch (Exception e) {
					pogresanOib=true;
				}
			} else {
				poruka = poruka.concat("oib; ");
			}
		}

		AdresaIMjestoStanovanja adresa = new AdresaIMjestoStanovanja();
		osoba.setAdresaPrebivalista(adresa);
		if (ulica.getText() != null) {
			if (!ulica.getText().isEmpty()) {
				adresa.setAdresa(ulica.getText());
			} else {
				poruka = poruka.concat("ulica; ");
			}
		} else {
			poruka = poruka.concat("ulica; ");
		}

		if (mjesto.getText() != null) {
			if (!mjesto.getText().isEmpty()) {
				adresa.setNazivMjesta(mjesto.getText());
			} else {
				poruka = poruka.concat("mjesto; ");
			}
		}

		if (pbr.getText() != null) {
			if (!pbr.getText().isEmpty()) {
				try{
					adresa.setPbrMjesto(Integer.parseInt(pbr.getText()));
				}catch (Exception e) {
					pogresanPbr=true;
				}
			}
		} else {
			poruka = poruka.concat("poštanski broj; ");
		}

		if(!pogresanOib && !pogresanPbr && poruka.equals("Unesite: ")){
			if(!PristupBaziPodataka.provjeriPodatkeOOsobi(osoba)){
			nePostojiUbazi=true;
			String oibOsoba=osoba.getOib().toString();
			if(PristupBaziPodataka.provjeriOibOsobe(oibOsoba)) nePostoji+="Unijeli ste pogrešne podatke za osobu čiji je oib: "+oibOsoba+".";
        	else nePostoji+="U bazi ne postoji osoba čiji je oib: "+oibOsoba+".";
		}}
		
		if (poruka.equals("Unesite: ") && !pogresanOib && !pogresanPbr  && !nePostojiUbazi) {
			if (tipOsobe.equals("Osumnjiceni")) {
				delegate.dodajOsumnjicenog(osoba);
			} else if (tipOsobe.equals("Svjedok")) {
				delegate.dodajSvjedoka(osoba);
			}
		} else {
			if(!poruka.equals("Unesite: ")) ispisGreske+=poruka;
			if(nePostojiUbazi) {
				if(!ispisGreske.equals("")) ispisGreske+="\n";
				ispisGreske+=nePostoji;
			}
			if(pogresanPbr){
				if(!ispisGreske.equals("")) ispisGreske+="\n";
				ispisGreske+="Unijeli ste pogrešan poštanski broj mjesta.";
			}
			if(pogresanOib){
				if(!ispisGreske.equals("")) ispisGreske+="\n";
				ispisGreske+="Unijeli ste pogrešan format oib-a.";
			}
	
			info.setText(ispisGreske);
		}
	}

	public void init(DialogManager delegate, String tipOsobe) {
		this.tipOsobe = tipOsobe;
		this.delegate = delegate;
	}

}
