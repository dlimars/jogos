/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import domain.JogoBicho;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class JogoBichoDatabaseRepository implements JogoBichoRepository{

    public JogoBichoDatabaseRepository() {
        createTable();
    }
    
    @Override
    public boolean add(JogoBicho jogo) {
        
        JogoBicho storedJogo = get(jogo);
        
        if (storedJogo == null) {
            String sql = "INSERT INTO jogos (numeroApostado, valorApostado, dataAposta) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
                stmt.setInt(1, jogo.getNumeroApostado());
                stmt.setFloat(2, jogo.getValorApostado());
                stmt.setString(3, jogo.getDataAposta());
                stmt.execute();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            storedJogo.setValorApostado(storedJogo.getValorApostado() + jogo.getValorApostado());
            update(storedJogo);
        }
        return false;
    }
    
    @Override
    public boolean remove(JogoBicho jogo) {
        String sql = "DELETE FROM jogos where numeroApostado=? and dataAposta=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, jogo.getNumeroApostado());
            stmt.setString(2, jogo.getDataAposta());
            stmt.execute();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean update(JogoBicho jogo) {
        String sql = "UPDATE jogos set valorApostado = ? WHERE numeroApostado=? and dataAposta=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setFloat(1, jogo.getValorApostado());
            stmt.setInt(2, jogo.getNumeroApostado());
            stmt.setString(3, jogo.getDataAposta());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public ArrayList<JogoBicho> getAll(String date) {
        String sql = "SELECT * FROM jogos WHERE dataAposta=? ORDER BY numeroApostado";
        ArrayList<JogoBicho> jogos = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, date);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                jogos.add(new JogoBicho(result.getInt("numeroApostado"),
                                        result.getFloat("valorApostado"),
                                        result.getString("dataAposta")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return jogos;
    }
    
    private JogoBicho get(JogoBicho jogo) {
        String sql = "SELECT * FROM jogos WHERE numeroApostado=? and dataAposta=? limit 1";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, jogo.getNumeroApostado());
            stmt.setString(2, jogo.getDataAposta());
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                return new JogoBicho(result.getInt("numeroApostado"),
                                        result.getFloat("valorApostado"),
                                        result.getString("dataAposta"));
            }
            
        } catch (Exception e) {
        }
        return null;
    }
    
    private Connection getConnection(){
        try {
          Class.forName("org.sqlite.JDBC");
          return DriverManager.getConnection("jdbc:sqlite:database.sqlite");
        } catch ( ClassNotFoundException | SQLException e ) {
          JOptionPane.showMessageDialog(null, "Falha ao abrir arquivo do banco de dados.");
        }
        return null;
    }
    
    private void createTable(){
        try (Statement stmt = getConnection().createStatement()) {
            String sql = "CREATE TABLE jogos " +
                    "(numeroApostado INT NOT NULL," +
                    " valorApostado DECIMAL(10,2) NOT NULL, " +
                    " dataAposta varchar(10) NOT NULL)";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
        }
    }
}
