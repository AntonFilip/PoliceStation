package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.*;
import View.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller extends Application implements ViewDelegate {

	private Stage stage;

	Pozornik policajac;
	Slucaj slucaj;
	Dokaz dokaz;
	Osumnjiceni osumjiceni;
	
	private Pane pane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Pronalaženje Sumnjivaca v1.0");
		postaviScenuPrijava();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
private FXMLLoader postaviResurs(String s){
	return new FXMLLoader(getClass().getResource("/View/"+s+".fxml"));
}

	@Override
	public void postaviScenuPrijava() {
		FXMLLoader myLoader = postaviResurs("FXMLPrijava");
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
		try {
			policajac = PristupBaziPodataka.prijava(username, password);
		} catch (SQLException e) {
			//TODO pogledat kaj radi exception  i napravit dobar odgovor
			e.printStackTrace();
			return;
		}
		
		if (policajac == null) {
			return;
		} else {
			prikaziGlavniIzbornik(policajac);
		}
	}

	@Override
	public void prikaziGlavniIzbornik(Pozornik pozornik) {
		FXMLLoader myLoader = postaviResurs("FXMLGlavniIzbornik");
		Parent loadScreen = null;
		try {
			loadScreen = (Parent) myLoader.load();
		} catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
		GlavniIzbornikController glavniIzbornikController = (GlavniIzbornikController) myLoader.getController();
		pane=glavniIzbornikController.init(this);
		glavniIzbornikController.setIme(policajac.getIme());
		Scene scene = new Scene(loadScreen);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void postaviScenuUpit() {
		FXMLLoader myLoader = postaviResurs("PostaviUpit");
		Parent loadScreen = null;
		try {
			loadScreen = (Parent) myLoader.load();
		} catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
		ControlledScreen prijavaController = (PostaviUpitController) myLoader.getController();
		prijavaController.init(this);
//		Scene scene = new Scene(loadScreen);
//		stage.setScene(scene);
//		stage.show();
		pane.getChildren().setAll(loadScreen);

	}

	@Override
	public void postaviScenuStatistika() {
		FXMLLoader myLoader = postaviResurs("PrikaziStatistiku");
		Parent loadScreen = null;
		try {
			loadScreen = (Parent) myLoader.load();
		} catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
		PrikaziStatistikuController prijavaController = (PrikaziStatistikuController) myLoader.getController();
		prijavaController.init(this);
		prijavaController.postaviPodatke("proba1", "Proba2", "proba333");
//		Scene scene = new Scene(loadScreen);
//		stage.setScene(scene);
//		stage.show();
		pane.getChildren().setAll(loadScreen);
	}

	@Override
	public void postaviScenuDnevnikPretrazivanja() {
		FXMLLoader myLoader = postaviResurs("PrikaziDnevnik");
		Parent loadScreen = null;
		try {
			loadScreen = (Parent) myLoader.load();
		} catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
		PrikaziDnevnikController prijavaController = (PrikaziDnevnikController) myLoader.getController();
		prijavaController.init(this);
		
		ObservableList<String> data = FXCollections.observableArrayList(
	            "chocolate", "salmon", "gold", "coral", "darkorchid",
	            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
	            "blueviolet", "brown", "karmela1", "karmela2", "karmela3", "karmela4", "karmela5");
		prijavaController.postaviPodatke(data);
		
//		Scene scene = new Scene(loadScreen);
//		stage.setScene(scene);
//		stage.show();
		pane.getChildren().setAll(loadScreen);

	}

	@Override
	public void odaberiStavku(String odabir) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void ispisPDF() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void posaljiUpitKriminalac(Osumnjiceni kriminalac) {
		FXMLLoader myLoader = postaviResurs("UpitKriminalac");
		Parent loadScreen = null;
		try {
			loadScreen = (Parent) myLoader.load();
		} catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
		PrikaziDnevnikController prijavaController = (PrikaziDnevnikController) myLoader.getController();
		prijavaController.init(this);
		
		policajac.posaljiUpitZaOsumnjicenog("");//TODO pricekat cure da naprave kak to treba biti u modelu
		
		
//		ObservableList<String> data = FXCollections.observableArrayList(
//	            "chocolate", "salmon", "gold", "coral", "darkorchid",
//	            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
//	            "blueviolet", "brown", "karmela1", "karmela2", "karmela3", "karmela4", "karmela5");
//		prijavaController.postaviPodatke(data);
		
//		Scene scene = new Scene(loadScreen);
//		stage.setScene(scene);
//		stage.show();
		pane.getChildren().setAll(loadScreen);

	}

	@Override
	public void posaljiUpitSlucaj(Slucaj slucaj) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void posaljiUpitDokaz(Dokaz dokaz) {
		FXMLLoader myLoader = postaviResurs("UpitDokaz");
		Parent loadScreen = null;
		try {
			loadScreen = (Parent) myLoader.load();
		} catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
		PrikaziDnevnikController prijavaController = (PrikaziDnevnikController) myLoader.getController();
		prijavaController.init(this);
		
		try {
			policajac.posaljiUpit(dokaz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO provjeriti s tomislavom il karmelom kak im poslati mapu te isto i napraviti sa sljedecim kodom
		ObservableList<String> data = FXCollections.observableArrayList(
	            "chocolate", "salmon", "gold", "coral", "darkorchid",
	            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
	            "blueviolet", "brown", "karmela1", "karmela2", "karmela3", "karmela4", "karmela5");
		prijavaController.postaviPodatke(data);
		
//		Scene scene = new Scene(loadScreen);
//		stage.setScene(scene);
//		stage.show();
		pane.getChildren().setAll(loadScreen);

	}

	@Override
	public void spremiIzmjeneKriminalca(Osumnjiceni kriminalac) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void spremiIzmjeneSlucaja(Slucaj slucaj) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void spremiIzmjeneDokaza(Dokaz dokaz) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void dodajKriminalca(Osumnjiceni kriminalac) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void dodajSlucaj(Slucaj slucaj) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void dodajDokaz(Dokaz dokaz) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void prikaziPodatkeKriminalca(Osumnjiceni kriminalac) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void prikaziPodatkeSlucaja(Slucaj slucaj) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void prikaziPodatkeDokaza(Dokaz dokaz) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void pristupiStatistici() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void pristupiDnevniku() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void odjava() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuUpitKriminalac() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuUpitSlucaj() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuUpitDokaz() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuPopis(String predmet, Map<String, Integer> popis) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuIzmjeneKriminalca() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuIzmjeneSlucaja() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuIzmjeneDokaza() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuDodajKriminalca() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuDodajSlucaj() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void postaviScenuDodajDokaz() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}
