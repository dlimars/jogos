/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Daniel
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import domain.JogoBicho;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jogobicho.views.PDF;

public class PDFGenerator {

    public void generate(ArrayList<JogoBicho> jogos, PDF pdf)
    {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("jogos.pdf"));
            document.open();
            document = pdf.generate(jogos, document);
            document.close();
            Desktop.getDesktop().open( new File( "jogos.pdf" ) );
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
