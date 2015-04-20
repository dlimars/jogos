/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import domain.JogoBicho;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public interface JogoBichoRepository {
    
    public boolean add(JogoBicho jogo);
    public boolean remove(JogoBicho jogo);
    public boolean update(JogoBicho jogo);
    public ArrayList<JogoBicho> getAll(String date);
    
}
