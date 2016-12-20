package Controller;

import Model.*;
import View.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends Application implements ViewDelegate, ShowScene {

	private Stage stage;

	Pozornik policajac;
	Slucaj slucaj;
	Dokaz dokaz;
	Osumnjiceni osumjiceni;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		postaviScenuPrijava();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void postaviScenuPrijava() {
		Loader loader = new Loader("/View/FXMLPrijava.fxml");
		ControlledScreen prijavaController = (PrijavaController) loader.getMyLoader().getController();
		prijavaController.init(this);
		Scene scene = new Scene(loader.getLoadScreen());
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void prijava(String username, String password) throws IOException {
		System.out.println(username + " " + password);
		policajac = new Pozornik();
		
//TODO		try {
//			policajac = PristupBaziPodataka.prijava(username, password);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (policajac == null) {
//			return;
//		} else {
//			prikaziGlavniIzbornik(policajac);
//		}

		
		if (policajac == null) {
			return;
		} else {
			prikaziGlavniIzbornik("Mirko", "Glupan", RazinaPristupa.NISKA);
		}
	}
        
    @Override
    public void prikaziGlavniIzbornik(String ime, String prezime, RazinaPristupa razina) { //TODO treba primati argument policajac
    	Loader loader = new Loader("/View/FXMLGlavniIzbornik.fxml");
        GlavniIzbornikController glavniIzbornikController = (GlavniIzbornikController) loader.getMyLoader().getController();
        glavniIzbornikController.init(this);
        glavniIzbornikController.setIme(ime); //ovdje možemo postavljati neke podatke na scenu
        Scene scene = new Scene(loader.getLoadScreen());         
        stage.setScene(scene);
        stage.show();
        
        
//        try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        odjava();
    }

    @Override
    public void odaberiStavku(String odabir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ispisPDF() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posaljiUpitKriminalac(Osumnjiceni kriminalac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posaljiUpitSlucaj(Slucaj slucaj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posaljiUpitDokaz(Dokaz dokaz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void spremiIzmjeneKriminalca(Osumnjiceni kriminalac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void spremiIzmjeneSlucaja(Slucaj slucaj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void spremiIzmjeneDokaza(Dokaz dokaz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dodajKriminalca(Osumnjiceni kriminalac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dodajSlucaj(Slucaj slucaj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dodajDokaz(Dokaz dokaz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prikaziPodatkeKriminalca(Osumnjiceni kriminalac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prikaziPodatkeSlucaja(Slucaj slucaj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prikaziPodatkeDokaza(Dokaz dokaz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pristupiStatistici() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pristupiDnevniku() {
        
    }

    @Override
    public void odjava() {
        policajac = null;
    	slucaj = null;
    	dokaz = null;
    	osumjiceni = null;
    	postaviScenuPrijava();
    }

    @Override
    public void postaviScenuUpitKriminalac() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuUpitSlucaj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuUpitDokaz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuPopis(String predmet, Map<String, Integer> popis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuIzmjeneKriminalca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuIzmjeneSlucaja() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuIzmjeneDokaza() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuDodajKriminalca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuDodajSlucaj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviScenuDodajDokaz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
	private class Loader {
		FXMLLoader myLoader;
		Parent loadScreen;
		/**
		 * @return the myLoader
		 */
		public FXMLLoader getMyLoader() {
			return myLoader;
		}

		/**
		 * @return the loadScreen
		 */
		public Parent getLoadScreen() {
			return loadScreen;
		}

		public Loader(String argument) {
			myLoader = new FXMLLoader(getClass().getResource(argument));
			loadScreen = null;
			try {
				loadScreen = (Parent) myLoader.load();
			} catch (IOException ex) {
				Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

}