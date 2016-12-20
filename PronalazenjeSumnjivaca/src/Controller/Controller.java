package Controller;

import Model.*;
import View.*;
import java.io.IOException;
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
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/View/FXMLPrijava.fxml"));
            Parent loadScreen = null;
            try {
                loadScreen = (Parent) myLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            ControlledScreen prijavaController = (PrijavaController) myLoader.getController();
            prijavaController.init(this);
            Scene scene = new Scene(loadScreen);
            stage.setScene(scene);
            stage.show();
        }
        
	@Override
	public void prijava(String username, String password) throws IOException {
		System.out.println(username + " " + password);
		policajac = new Pozornik();
                        /*PristupBaziPodataka.prijava(username, password);*/
		if (policajac == null) {
			return;
		} else {
                    prikaziGlavniIzbornik("Mirko", "Glupan", RazinaPristupa.NISKA);   
                }
	}
        
    @Override
    public void prikaziGlavniIzbornik(String ime, String prezime, RazinaPristupa razina) {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/View/FXMLGlavniIzbornik.fxml"));
        Parent loadScreen = null;
            try {
                loadScreen = (Parent) myLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        GlavniIzbornikController glavniIzbornikController = (GlavniIzbornikController) myLoader.getController();
        glavniIzbornikController.init(this);
        glavniIzbornikController.setIme(ime); //ovdje možemo postavljati neke podatke na scenu
        Scene scene = new Scene(loadScreen);         
        stage.setScene(scene);
        stage.show();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void odjava() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    
}
