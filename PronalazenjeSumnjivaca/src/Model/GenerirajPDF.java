/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Ivona
 */
public class GenerirajPDF {

    private static Font f = FontFactory.getFont(FontFactory.TIMES_ROMAN, "Cp1250", BaseFont.EMBEDDED);
    private static Font f2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, "Cp1250", BaseFont.EMBEDDED, 12, Font.BOLD, new CMYKColor(55, 0, 0, 200));
    private static Font f3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, "Cp1250", BaseFont.EMBEDDED, 16, Font.ITALIC, new CMYKColor(0, 0, 0, 255));
    public static void generiraj(Osumnjiceni osumnjiceni) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        String imeDatoteke = "ocumnjiceni" + osumnjiceni.getOib();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(imeDatoteke + ".pdf"));
        document.open();

        //naslov
        document.add(new Paragraph("Kriminalac", FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.ITALIC, new CMYKColor(0, 0, 0, 255))));

        //dodavanje fotografija
        for (String urlFoto : osumnjiceni.getFotografijeURL()) {
            Image image = Image.getInstance(new URL(urlFoto));
            image.scaleToFit(200, 200);
            document.add(image);
        }

        //dodavanje opisa
        Paragraph opis = new Paragraph("\n", FontFactory.getFont(FontFactory.COURIER, 14, Font.NORMAL, new CMYKColor(0, 0, 0, 255)));
        opis.setSpacingAfter(10);

        Chunk imeT = new Chunk("Ime:  ", f2);
        Chunk ime = new Chunk(osumnjiceni.getIme() + "\n", f);
        opis.add(imeT);
        opis.add(ime);

        Chunk prezimeT = new Chunk("Prezime:  ", f2);
        Chunk prezime = new Chunk(osumnjiceni.getPrezime() + "\n", f);
        opis.add(prezimeT);
        opis.add(prezime);

        Chunk oibT = new Chunk("OIB:  ", f2);
        Chunk oib = new Chunk(osumnjiceni.getOib() + "\n", f);
        opis.add(oibT);
        opis.add(oib);

        Chunk statusT = new Chunk("Trenutni status kriminalca:  ", f2);
        Chunk status = new Chunk(osumnjiceni.getStatus() + "\n", f);
        opis.add(statusT);
        opis.add(status);

        Chunk aliasiT = new Chunk("Popis aliasa:  ", f2);
        List aliasi = new List(List.UNORDERED);
        for (String al : osumnjiceni.getPopisAliasa()) {
            aliasi.add(new ListItem(al, f));
        }
        opis.add(aliasiT);
        opis.add(aliasi);

        Chunk telT = new Chunk("Broj telefona:  ", f2);
        Chunk tel = new Chunk(osumnjiceni.getBrojTelefona() + "\n", f);
        opis.add(telT);
        opis.add(tel);

        Chunk adresaT = new Chunk("Adresa:  ", f2);
        Chunk adresa = new Chunk(osumnjiceni.getAdresaPrebivalista().getAdresa()+", "+osumnjiceni.getAdresaPrebivalista().getNazivMjesta()+ "\n", f);
        opis.add(adresaT);
        opis.add(adresa);

        Chunk adreseT = new Chunk("Poznate adrese:  ", f2);
        List adrese = new List(List.UNORDERED);
        for (AdresaIMjestoStanovanja adr : osumnjiceni.getPoznateAdrese()) {
            adrese.add(new ListItem(adr.getAdresa() + ", " + adr.getNazivMjesta(), f));
        }
        opis.add(adreseT);
        opis.add(adrese);

        FizickeOsobine fizO = osumnjiceni.getFizickeOsobine();
        Chunk fizT = new Chunk("Fizičke osobine:  ", f2);
        List fiz = new List(List.UNORDERED);
        fiz.add(new ListItem("Spol: " + fizO.getSpol(), f));
        fiz.add(new ListItem("Rasa: " + fizO.getRasa(), f));
        fiz.add(new ListItem("Visina: " + fizO.getVisina(), f));
        fiz.add(new ListItem("Težina: " + fizO.getTezina(), f));
        fiz.add(new ListItem("Godine: " + fizO.getGodine(), f));
        fiz.add(new ListItem("Oblik glave: " + fizO.getOblikGlave(), f));
        fiz.add(new ListItem("Oblik frizure: " + fizO.getOblikFrizure(), f));
        fiz.add(new ListItem("Boja očiju: " + fizO.getBojaOciju(), f));
        fiz.add(new ListItem("Boja Kose: " + fizO.getBojaKose(), f));
        fiz.add(new ListItem("Građa tijela: " + fizO.getGradaTijela(), f));
        fiz.add(new ListItem("Tetovaže: " + fizO.getTetovaze(), f));
        fiz.add(new ListItem("Fizički nedostaci: " + fizO.getFizickiNedostatci(), f));
        fiz.add(new ListItem("Bolesti: " + fizO.getBolesti(), f));
        fiz.add(new ListItem("Ostalo: " + fizO.getOstaleFizickeOsobine(), f));
        opis.add(fizT);
        opis.add(fiz);

        KarakterneOsobine karO = osumnjiceni.getKarakterneOsobine();
        Chunk karT = new Chunk("Karakterne osobine:  ", f2);
        List kar = new List(List.UNORDERED);
        kar.add(new ListItem("Način govora: " + karO.getNacinGovora(), f));
        kar.add(new ListItem("Rzina apstraktne inteligencije: " + karO.getRazinaApstraktneInteligencije(), f));
        kar.add(new ListItem("Psihološki problemi: " + karO.getPsiholoskiProblemi(), f));
        kar.add(new ListItem("Ostalo: " + karO.getOstaleKarakterneOsobine(), f));
        opis.add(karT);
        opis.add(kar);

        Chunk djelT = new Chunk("Opis kriminalnih djelatnosti:  ", f2);
        Chunk djel = new Chunk(osumnjiceni.getOpisKriminalnihDjelatnosti() + "\n", f);
        opis.add(djelT);
        opis.add(djel);

        Chunk slucajT = new Chunk("Povezani slučajevi:  ", f2);
        List slucaj = new List(List.UNORDERED);
        for (Slucaj sl : osumnjiceni.getPovezaniSlucajevi()) {
            slucaj.add(new ListItem(sl.getNazivSlucaja() + "(br. " + sl.getBrojSlucaja() + ")", f));
        }
        opis.add(slucajT);
        opis.add(slucaj);

        Chunk povezaniT = new Chunk("Povezani kriminalci:  ", f2);
        List povezani = new List(List.UNORDERED);
        for (Osumnjiceni o : osumnjiceni.getPopisPovezanihKriminalaca()) {
            povezani.add(new ListItem(o.getPrezime() + " " + o.getIme() + " " + "(oib: " + o.getOib() + ")", f));
        }
        opis.add(povezaniT);
        opis.add(povezani);

        document.add(opis);
        document.close();
        writer.close();
    }

    public static void generiraj(Dokaz dokaz) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        String imeDatoteke = "dokaz" + dokaz.getID();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(imeDatoteke + ".pdf"));
        document.open();

        //naslov
        document.add(new Paragraph("Dokaz", FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.ITALIC, new CMYKColor(0, 0, 0, 255))));

        //dodavanje fotografije
        Image image = Image.getInstance(new URL(dokaz.getFotografija()));
        image.scaleToFit(200, 200);
        document.add(image);

        //dodavanje opisa
        Paragraph opis = new Paragraph("\n", FontFactory.getFont(FontFactory.COURIER, 14, Font.NORMAL, new CMYKColor(0, 0, 0, 255)));
        opis.setSpacingAfter(10);

        Chunk idT = new Chunk("ID:  ", f2);
        Chunk id = new Chunk(dokaz.getID() + "\n", f);
        opis.add(idT);
        opis.add(id);

        Chunk nazivT = new Chunk("Naziv:  ", f2);
        Chunk naziv = new Chunk(dokaz.getNaziv() + "\n", f);
        opis.add(nazivT);
        opis.add(naziv);

        Chunk slucajT = new Chunk("Naziv slučaja:  ", f2);
        Chunk slucaj = new Chunk(dokaz.getNazivSlucaja() + "\n", f);
        opis.add(slucajT);
        opis.add(slucaj);

        Chunk krvT = new Chunk("Krvne grupe:  ", f2);
        List krv = new List(List.UNORDERED);
        for (String k : dokaz.getKrvnaGrupa()) {
            krv.add(new ListItem(k, f));
        }
        opis.add(krvT);
        opis.add(krv);

        Chunk dnaT = new Chunk("DNA sekvence:  ", f2);
        List dna = new List(List.UNORDERED);
        for (String k : dokaz.getDNASekvenca()) {
            dna.add(new ListItem(k, f));
        }
        opis.add(dnaT);
        opis.add(dna);

        Chunk oruzjeT = new Chunk("Tip oružja:  ", f2);
        List oruzje = new List(List.UNORDERED);
        for (String k : dokaz.getTipOruzja()) {
            oruzje.add(new ListItem(k, f));
        }
        opis.add(oruzjeT);
        opis.add(oruzje);

        Chunk otisakT = new Chunk("Otisak prsta:  ", f2);
        opis.add(otisakT);
        for (String urlFoto : dokaz.getOtisakPrsta()) {
            Image ot = Image.getInstance(new URL(urlFoto));
            ot.scaleToFit(200, 200);
            opis.add(ot);
        }

        document.add(opis);
        document.close();
        writer.close();
    }

    public static void generiraj(Slucaj slucaj) throws DocumentException, FileNotFoundException, MalformedURLException, BadElementException, IOException {

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        String imeDatoteke = "slucaj" + slucaj.getBrojSlucaja();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(imeDatoteke + ".pdf"));
        document.open();

        //naslov
        document.add(new Paragraph("Slučaj", f3));

        //dodavanje opisa
        Paragraph opis = new Paragraph("\n", FontFactory.getFont(FontFactory.COURIER, 14, Font.NORMAL, new CMYKColor(0, 0, 0, 255)));
        opis.setSpacingAfter(10);

        Chunk brT = new Chunk("Broj slučaja:  ", f2);
        Chunk br = new Chunk(slucaj.getBrojSlucaja() + "\n", f);
        opis.add(brT);
        opis.add(br);

        Chunk nazT = new Chunk("Naziv:  ", f2);
        Chunk naz = new Chunk(slucaj.getNazivSlucaja() + "\n", f);
        opis.add(nazT);
        opis.add(naz);

        Chunk opisT = new Chunk("Opis:  ", f2);
        Chunk op = new Chunk(slucaj.getOpis() + "\n", f);
        opis.add(opisT);
        opis.add(op);

        Chunk statT = new Chunk("Trenutni status:  ", f2);
        Chunk stat = new Chunk(slucaj.getStatus() + "\n", f);
        opis.add(statT);
        opis.add(stat);

        Chunk goT = new Chunk("Glavni osumnjičeni:  ", f2);
        Chunk go = new Chunk(slucaj.getGlavniOsumnjiceni().toString() + "\n", f);
        opis.add(goT);
        opis.add(go);

        Chunk popoT = new Chunk("Popis osumnjičenih:  ", f2);
        List popo = new List(List.UNORDERED);
        for (Osoba o : slucaj.getPopisOsumnjicenih()) {
            popo.add(new ListItem(o.toString(), f));
        }
        opis.add(popoT);
        opis.add(popo);

        Chunk popsT = new Chunk("Popis svjedoka:  ", f2);
        List pops = new List(List.UNORDERED);
        for (Osoba o : slucaj.getPopisSvjedoka()) {
            popo.add(new ListItem(o.toString(), f));
        }
        opis.add(popsT);
        opis.add(pops);

        Chunk poplT = new Chunk("Popis policajaca:  ", f2);
        List popl = new List(List.UNORDERED);
        for (Osoba o : slucaj.getPopisPolicajaca()) {
            popo.add(new ListItem(o.toString(), f));
        }
        opis.add(poplT);
        opis.add(popl);

        Chunk popdT = new Chunk("Popis dokaza:  ", f2);
        List popd = new List(List.UNORDERED);
        for (Dokaz o : slucaj.getPopisDokaza()) {
            popo.add(new ListItem(o.getNaziv(), f));
        }
        opis.add(popdT);
        opis.add(popd);

        Chunk popgT = new Chunk("Popis događaja:  ", f2);
        List popg = new List(List.UNORDERED);
        for (Dogadaj o : slucaj.getPopisDogadaja()) {
            popo.add(new ListItem(o.getNaziv(), f));
        }
        opis.add(popgT);
        opis.add(popg);

        Chunk fotoT = new Chunk("Fotografije:  ", f2);
        opis.add(fotoT);
        for (String urlFoto : slucaj.getFotografijeSlučaja()) {
            Image ot = Image.getInstance(new URL(urlFoto));
            ot.scaleToFit(200, 200);
            opis.add(ot);
        }
        document.add(opis);
        document.close();
        writer.close();
    }
}
