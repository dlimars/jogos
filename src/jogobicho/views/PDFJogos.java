/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogobicho.views;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Daniel
 */
public class PDFJogos {
    private Document document;
    
    public PDFJogos () {
        try {
            document = new Document();
            openPdf();
            generatePdf();
            closePdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public boolean openPdf() {
        boolean status = false;
        try {
            File pdfFile = new File("test.pdf");
            if (pdfFile != null) {
                PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
                document.open();
                status = true;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public void closePdf() {
        document.close();
    }

    public void generatePdf() throws DocumentException {
        Paragraph paragraph = new Paragraph();
        PdfPCell cell = null;
        // Main table
        PdfPTable mainTable = new PdfPTable(3);
        mainTable.setWidthPercentage(100.0f);
        
        
        // First table
        PdfPCell firstTableCell = new PdfPCell();
        firstTableCell.setBorder(PdfPCell.NO_BORDER);
        PdfPTable firstTable = new PdfPTable(2);
        firstTable.setWidthPercentage(98.0f);
        cell = new PdfPCell(new Phrase("T1R1C1"));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T1R1C2"));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T1R2C1"));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T1R2C2"));
        firstTable.addCell(cell);
        firstTableCell.addElement(firstTable);
        mainTable.addCell(firstTableCell);
        
        // Second table
        PdfPCell secondTableCell = new PdfPCell();
        secondTableCell.setBorder(PdfPCell.NO_BORDER);
        PdfPTable secondTable = new PdfPTable(2);
        secondTable.setWidthPercentage(98.0f);
        cell = new PdfPCell(new Phrase("T2R1C1"));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T2R1C2"));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T2R2C1"));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T2R2C2"));
        secondTable.addCell(cell);
        secondTableCell.addElement(secondTable);
        mainTable.addCell(secondTableCell);
        
        //third
        PdfPCell thirdTableCell = new PdfPCell();
        thirdTableCell.setBorder(PdfPCell.NO_BORDER);
        PdfPTable thirdTable = new PdfPTable(2);
        thirdTable.setWidthPercentage(98.0f);
        cell = new PdfPCell(new Phrase("T2R1C1"));
        thirdTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T2R1C2"));
        thirdTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T2R2C1"));
        thirdTable.addCell(cell);
        cell = new PdfPCell(new Phrase("T2R2C2"));
        thirdTable.addCell(cell);
        thirdTableCell.addElement(thirdTable);
        mainTable.addCell(thirdTableCell);
        
        
        
        paragraph.add(mainTable);
        document.add(paragraph);
    }
}
