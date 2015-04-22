/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogobicho.views;

import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import domain.JogoBicho;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class PDFJogos implements PDF{
    private Document document;
    private final int jogosPorColuna = 45;
    private final int colunasDeJogos = 3;
    DecimalFormat df = new DecimalFormat("#,##0.00");

    @Override
    public Document generate(ArrayList<JogoBicho> jogos, Document document) {
        this.document = document;
        try {
            generatePdf(jogos);
        } catch (Exception ex) {
        }
        return document;
    }

    public void generatePdf(ArrayList<JogoBicho> jogos) throws DocumentException {
       
        while(jogos.size()%(jogosPorColuna*colunasDeJogos) != 0) {
            jogos.add(new JogoBicho(0, 0, null));
        }

        List<List<JogoBicho>> separatedList = Lists.partition(jogos, jogosPorColuna);
        
        List<List<List<JogoBicho>>> chunkedList = Lists.partition(separatedList, colunasDeJogos);
        
        float valorAcumulado = 0;
        
        for (List<List<JogoBicho>> chunkedList1 : chunkedList) {
            document.newPage();
            PdfPTable mainTable = new PdfPTable(colunasDeJogos);
            mainTable.setWidthPercentage(100.0f);
            
            for (List<JogoBicho> columnList : chunkedList1) {
                PdfPCell columnTableCell = new PdfPCell();
                columnTableCell.setBorder(PdfPCell.NO_BORDER);
                
                PdfPTable columnTable = new PdfPTable(2);
                columnTable.setWidthPercentage(98.0f);
                
                for (JogoBicho jogo: columnList) {
                    if (jogo.getDataAposta() == null) {
                        PdfPCell cell = new PdfPCell();
                        cell.setBorder(PdfPCell.NO_BORDER);
                        columnTable.addCell(cell);
                        columnTable.addCell(cell);
                    } else {
                        columnTable.addCell(jogo.getNumeroApostado() + "");
                        columnTable.addCell(moneyFormat(jogo.getValorApostado()));
                        valorAcumulado+= jogo.getValorApostado();
                    }
                }
                columnTableCell.addElement(columnTable);
                mainTable.addCell(columnTableCell);
            }
            document.add(mainTable);
        }
        
        Paragraph totalApostado = new Paragraph("Total apostado: " + moneyFormat(valorAcumulado));
        totalApostado.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(totalApostado);
    }
    
    private String moneyFormat(float value) {
        return "R$ " + df.format(value);
    }
}
