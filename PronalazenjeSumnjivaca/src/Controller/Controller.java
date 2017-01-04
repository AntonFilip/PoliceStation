package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Model.Dokaz;
import Model.Osumnjiceni;
import Model.Pozornik;
import Model.PristupBaziPodataka;
import Model.Slucaj;
import View.ControlledScreen;
import View.GlavniIzbornikController;
import View.PostaviUpitController;
import View.PrijavaController;
import View.PrikaziDnevnikController;
import View.PrikaziStatistikuController;
import View.UpitDokazController;
import View.UpitKriminalacController;
import View.UpitSlucajController;

public class Controller extends Application implements ViewDelegate {

	private Stage stage;

	Pozornik policajac = null;
	Slucaj slucaj;
	Dokaz dokaz;
	Osumnjiceni osumjiceni;

	Map<Osumnjiceni, Float> mapaOsumnjiceni;
	Map<Dokaz, Float> mapaDokaz;
	Map<Slucaj, Float> mapaSlucaj;

	private Pane pane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setResizable(false);
		stage.setTitle("Pronalaženje Sumnjivaca v1.0");
		postaviScenuPrijava();

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void postaviScenuPrijava() {
		Loader loader = new Loader("FXMLPrijava");
		Parent loadScreen = loader.getLoadScreen();
		ControlledScreen controller = (PrijavaController) loader.getMyLoader()
				.getController();
		controller.init(this);
		Scene scene = new Scene(loadScreen);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void prijava(String username, String password) throws IOException {
		try {
			policajac = PristupBaziPodataka.prijava(username, password);
		} catch (SQLException e) {
			// TODO pogledat kaj radi exception i napravit dobar odgovor

			e.printStackTrace();
			return;
		}

		if (policajac == null) {
			System.out.println("Korisni�ko ime i/ili lozinka nisu ipravni!");
			return;
		} else {
			System.out.println("Uspje�na prijava!");
			prikaziGlavniIzbornik(policajac);
		}
	}

	@Override
	public void prikaziGlavniIzbornik(Pozornik pozornik) {
		Loader loader = new Loader("FXMLGlavniIzbornik");
		Parent loadScreen = loader.getLoadScreen();
		GlavniIzbornikController controller = (GlavniIzbornikController) loader
				.getMyLoader().getController();
		pane = controller.init(this);
		controller.setIme(policajac.getIme());
		Scene scene = new Scene(loadScreen);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void postaviScenuUpit() {
		Loader loader = new Loader("PostaviUpit");
		Parent loadScreen = loader.getLoadScreen();

		ControlledScreen controller = (PostaviUpitController) loader
				.getMyLoader().getController();
		controller.init(this);
		Scene scene = new Scene(loadScreen);
		stage.setScene(scene);
		stage.show();
		pane.getChildren().setAll(loadScreen);

	}

	@Override
	public void postaviScenuStatistika() {
		Loader loader = new Loader("PrikaziStatistiku");
		Parent loadScreen = loader.getLoadScreen();

		PrikaziStatistikuController controller = (PrikaziStatistikuController) loader
				.getMyLoader().getController();
		controller.init(this);
		controller.postaviPodatke("proba1", "Proba2", "proba333");
		// Scene scene = new Scene(loadScreen);
		// stage.setScene(scene);
		// stage.show();
		pane.getChildren().setAll(loadScreen);
	}

	@Override
	public void postaviScenuDnevnikPretrazivanja() {
		Loader loader = new Loader("PrikaziDnevnik");
		Parent loadScreen = loader.getLoadScreen();

		PrikaziDnevnikController controller = (PrikaziDnevnikController) loader
				.getMyLoader().getController();
		controller.init(this);

		ObservableList<String> data = FXCollections.observableArrayList(
				"chocolate", "salmon", "gold", "coral", "darkorchid",
				"darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
				"blueviolet", "brown", "karmela1", "karmela2", "karmela3",
				"karmela4", "karmela5");
		controller.postaviPodatke(data);

		// Scene scene = new Scene(loadScreen);
		// stage.setScene(scene);
		// stage.show();
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
		mapaOsumnjiceni = policajac.posaljiUpit(kriminalac);

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
		try {
			mapaDokaz = policajac.posaljiUpit(dokaz);
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
		policajac = null;
		slucaj = null;
		dokaz = null;
		osumjiceni = null;
		postaviScenuPrijava();
	}

	@Override
	public void postaviScenuUpitKriminalac() {
		Loader loader = new Loader("UpitKriminalac");
		Parent loadScreen = loader.getLoadScreen();

		ControlledScreen controller = (UpitKriminalacController) loader
				.getMyLoader().getController();
		controller.init(this);
		pane.getChildren().setAll(loadScreen);
	}

	@Override
	public void postaviScenuUpitSlucaj() {
		Loader loader = new Loader("UpitSlucaj");
		Parent loadScreen = loader.getLoadScreen();

		ControlledScreen controller = (UpitSlucajController) loader
				.getMyLoader().getController();
		controller.init(this);
		pane.getChildren().setAll(loadScreen);
	}

	@Override
	public void postaviScenuUpitDokaz() {
		Loader loader = new Loader("UpitDokaz");
		Parent loadScreen = loader.getLoadScreen();

		ControlledScreen controller = (UpitDokazController) loader
				.getMyLoader().getController();
		controller.init(this);
		pane.getChildren().setAll(loadScreen);
	}

	@Override
	public void postaviScenuPopis(String predmet, Map<String, Integer> popis) {
		
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

	private class Loader {
		private FXMLLoader myLoader;
		private Parent loadScreen = null;

		/**
		 * @return the loadScreen
		 */
		public Parent getLoadScreen() {
			return loadScreen;
		}

		/**
		 * @return the myLoader
		 */
		public FXMLLoader getMyLoader() {
			return myLoader;
		}

		public Loader(String fxmlFile) {
			myLoader = new FXMLLoader(getClass().getResource(
					"/View/" + fxmlFile + ".fxml"));
			try {
				loadScreen = (Parent) myLoader.load();
			} catch (IOException ex) {
				Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}
}
