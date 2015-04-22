/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogobicho.views;

import com.itextpdf.text.Document;
import domain.JogoBicho;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public interface PDF {
    public Document generate(ArrayList<JogoBicho> jogos, Document document);
}
