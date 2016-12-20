/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Ivona
 */
public class GenerirajPDF {

    private static int brojac = 0;

    public static void generiraj(Osumnjiceni osumnjiceni) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        String ime = "ocumnjiceni" + String.valueOf(brojac);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\PDFsumnjivci\\" + ime + ".pdf"));
        document.open();

        Anchor anchorTarget = new Anchor("Kriminalac");
        anchorTarget.setName("BackToTop");
        Paragraph paragraph1 = new Paragraph();
        paragraph1.setSpacingBefore(50);
        paragraph1.add(anchorTarget);
        document.add(paragraph1);
        
        List <String> fotografije = osumnjiceni.fotografije;

        for (String urlFoto : fotografije) {
            Image image = Image.getInstance(new URL(urlFoto));
            document.add(image);
        }

        document.add(new Paragraph("Some more text on the first page with different color and font type.", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));

    }

    public static void generiraj(Dokaz dokaz) {

    }

    public static void generiraj(Slucaj slucaj) {

    }
}
