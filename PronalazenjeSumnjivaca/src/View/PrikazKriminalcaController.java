package View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import Controller.ViewDelegate;
import Model.AdresaIMjestoStanovanja;
import Model.GenerirajPDF;
import Model.Osumnjiceni;
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
 */
public class PrikazKriminalcaController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;   
    
    @FXML Button ispisPDF;
    @FXML ImageView fotografija;
    @FXML Label ime;
    @FXML Label prezime;
    @FXML Label oib;
    @FXML Label adresa;
    @FXML Label brojTelefona;
    @FXML Label status;
    @FXML ListView<String> opisKriminalnihDjelatnosti;
    @FXML ListView<String> popisAliasa;
    @FXML ListView<String> poznateAdrese;
    @FXML ListView<String> popisPovezanihSlucajeva;
    @FXML ListView<String> popisPovezanihKriminalaca;

    @FXML Label spol;
    @FXML Label rasa;
    @FXML Label visina;
    @FXML Label tezina;
    @FXML Label godine;
    @FXML Label bojaKose;
    @FXML Label oblikFrizure;
    @FXML Label oblikGlave;
    @FXML Label bojaOciju;
    @FXML Label gradaTijela;
    @FXML ListView<String> tetovaze;
    @FXML ListView<String> fizickiNedostatci;
    @FXML ListView<String> bolesti;
    @FXML ListView<String> ostaleFizickeOsobine;

    @FXML Label nacinGovora;
    @FXML Label razinaInteligencije;
    @FXML ListView<String> psihickiProblemi;
    @FXML ListView<String> ostaleKarakterneOsobine;
    
    Osumnjiceni osumnjiceni;
    int index;

    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
        index = 0;       
    }

    public void prikaziPodatke(Osumnjiceni osumnjiceni) {
        this.osumnjiceni = osumnjiceni;
        if (!osumnjiceni.getFotografijeURL().isEmpty()) {
            ArrayList<String> lista = new ArrayList<>();
            lista.addAll(osumnjiceni.getFotografijeURL());
            System.out.println(lista);
            Image img = new Image(lista.get(index));
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

        if (osumnjiceni.getIme() != null) {
            ime.setText(osumnjiceni.getIme());
        }
        if (osumnjiceni.getPrezime() != null) {
            prezime.setText(osumnjiceni.getPrezime());
        }
        if (osumnjiceni.getOib() != null) {
            oib.setText(Long.toString(osumnjiceni.getOib()));
        }
        if (osumnjiceni.getAdresaPrebivalista().getAdresa() != null) {
            adresa.setText(osumnjiceni.getAdresaPrebivalista().getAdresa());
        }
        if (osumnjiceni.getBrojTelefona() != null) {
            brojTelefona.setText(osumnjiceni.getBrojTelefona());
        }
        if (osumnjiceni.getStatus() != null) {
            status.setText(osumnjiceni.getStatus().toString());
        }

        ObservableList<String> observableOpisKriminalnihDjelatnosti = FXCollections.observableArrayList();
        if (osumnjiceni.getOpisKriminalnihDjelatnosti() != null) {
            observableOpisKriminalnihDjelatnosti.add(osumnjiceni.getOpisKriminalnihDjelatnosti());
            opisKriminalnihDjelatnosti.setItems(observableOpisKriminalnihDjelatnosti);
        }

        ObservableList<String> observablePopisAliasa = FXCollections.observableArrayList();
        if (!osumnjiceni.getPopisAliasa().isEmpty()) {
            observablePopisAliasa.addAll(osumnjiceni.getPopisAliasa());
            popisAliasa.setItems(observablePopisAliasa);
        }

        ObservableList<String> observableAdrese = FXCollections.observableArrayList();
        if (!osumnjiceni.getPoznateAdrese().isEmpty()) {
            for (AdresaIMjestoStanovanja adresa : osumnjiceni.getPoznateAdrese()) {
                observableAdrese.add(adresa.getAdresa() + ", " + adresa.getNazivMjesta() + ", " + adresa.getPbrMjesto().toString());
            }
            poznateAdrese.setItems(observableAdrese);
        }

        ObservableList<String> observableSlucajevi = FXCollections.observableArrayList();
        if (osumnjiceni.getPovezaniSlucajevi() != null) {
            for (Slucaj slucaj : osumnjiceni.getPovezaniSlucajevi()) {
                if (slucaj.getBrojSlucaja() != null) {
                    observableSlucajevi.add(Integer.toString(slucaj.getBrojSlucaja()));
                }
            }
            popisPovezanihSlucajeva.setItems(observableSlucajevi);
        }

        ObservableList<String> observableKriminalci = FXCollections.observableArrayList();
        if (!osumnjiceni.getPopisPovezanihKriminalaca().isEmpty()) {
            for (Osumnjiceni krimi : osumnjiceni.getPopisPovezanihKriminalaca()) {
                observableKriminalci.add(Long.toString(krimi.getOib()));
            }
            popisPovezanihKriminalaca.setItems(observableKriminalci);
        }

        if (osumnjiceni.getFizickeOsobine() != null) {
            if (osumnjiceni.getFizickeOsobine().getSpol() != null) {
                spol.setText(osumnjiceni.getFizickeOsobine().getSpol().toString());
            }
            if (osumnjiceni.getFizickeOsobine().getRasa() != null) {
                rasa.setText(osumnjiceni.getFizickeOsobine().getRasa());
            }
            if (osumnjiceni.getFizickeOsobine().getVisina() != null) {
                visina.setText(Float.toString(osumnjiceni.getFizickeOsobine().getVisina()));
            }
            if (osumnjiceni.getFizickeOsobine().getTezina() != null) {
                tezina.setText(Float.toString(osumnjiceni.getFizickeOsobine().getTezina()));
            }
            if (osumnjiceni.getFizickeOsobine().getGodine() != null) {
                godine.setText(Integer.toString(osumnjiceni.getFizickeOsobine().getGodine()));
            }
            if (osumnjiceni.getFizickeOsobine().getBojaKose() != null) {
                bojaKose.setText(osumnjiceni.getFizickeOsobine().getBojaKose());
            }
            if (osumnjiceni.getFizickeOsobine().getOblikFrizure() != null) {
                oblikFrizure.setText(osumnjiceni.getFizickeOsobine().getOblikFrizure());
            }
            if (osumnjiceni.getFizickeOsobine().getOblikGlave() != null) {
                oblikGlave.setText(osumnjiceni.getFizickeOsobine().getOblikGlave());
            }
            if (osumnjiceni.getFizickeOsobine().getBojaOciju() != null) {
                bojaOciju.setText(osumnjiceni.getFizickeOsobine().getBojaOciju());
            }
            if (osumnjiceni.getFizickeOsobine().getGradaTijela() != null) {
                gradaTijela.setText(osumnjiceni.getFizickeOsobine().getGradaTijela().toString());
            }

            ObservableList<String> observableTetovaze = FXCollections.observableArrayList();
            if (osumnjiceni.getFizickeOsobine().getTetovaze() != null) {
                observableTetovaze.addAll(osumnjiceni.getFizickeOsobine().getTetovaze());
                tetovaze.setItems(observableTetovaze);
            }

            ObservableList<String> observableNedostatci = FXCollections.observableArrayList();
            if (osumnjiceni.getFizickeOsobine().getFizickiNedostatci() != null) {
                observableNedostatci.addAll(osumnjiceni.getFizickeOsobine().getFizickiNedostatci());
                fizickiNedostatci.setItems(observableNedostatci);
            }

            ObservableList<String> observableBolesti = FXCollections.observableArrayList();
            if (osumnjiceni.getFizickeOsobine().getBolesti() != null) {
                observableBolesti.addAll(osumnjiceni.getFizickeOsobine().getBolesti());
                bolesti.setItems(observableBolesti);
            }

            ObservableList<String> observableOstaleFizicke = FXCollections.observableArrayList();
            if (osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine() != null) {
                observableOstaleFizicke.addAll(osumnjiceni.getFizickeOsobine().getOstaleFizickeOsobine());
                ostaleFizickeOsobine.setItems(observableOstaleFizicke);
            }
        }

        if (osumnjiceni.getKarakterneOsobine() != null) {
            if (osumnjiceni.getKarakterneOsobine().getNacinGovora() != null) {
                nacinGovora.setText(osumnjiceni.getKarakterneOsobine().getNacinGovora());
            }
            if (osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije() != null) {
                razinaInteligencije.setText(osumnjiceni.getKarakterneOsobine().getRazinaApstraktneInteligencije().toString());
            }

            ObservableList<String> observablePsiholoski = FXCollections.observableArrayList();
            if (osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi() != null) {
                observablePsiholoski.addAll(osumnjiceni.getKarakterneOsobine().getPsiholoskiProblemi());
                psihickiProblemi.setItems(observablePsiholoski);
            }

            ObservableList<String> observableOstaleKarakterne = FXCollections.observableArrayList();
            if (osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine() != null) {
                observableOstaleKarakterne.addAll(osumnjiceni.getKarakterneOsobine().getOstaleKarakterneOsobine());
                ostaleKarakterneOsobine.setItems(observableOstaleKarakterne);
            }
        }
    }

    @FXML
    private void generirajPDF(ActionEvent event) throws DocumentException, IOException {
        GenerirajPDF.generiraj(osumnjiceni);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      

	


	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		//String image = "http://www.avajava.com/images/avajavalogo.jpg";
		//String destination = "image.jpg";
		saveImage(imageUrl, destinationFile);
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}
	
	
}
