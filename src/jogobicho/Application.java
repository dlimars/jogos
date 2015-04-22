/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogobicho;

import domain.JogoBicho;
import java.util.ArrayList;
import javax.swing.UIManager;
import jogobicho.views.PDFJogos;
import jogobicho.views.TelaDeJogos;
import services.PDFGenerator;

/**
 *
 * @author Daniel
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        decorarJanela();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaDeJogos().setVisible(true);
            }
        });
    }
    
    private static void decorarJanela()
    {
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e)
        {
        }
    }
    
}
