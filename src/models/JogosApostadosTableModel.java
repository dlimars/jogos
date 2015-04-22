/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.JogoBicho;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import repositories.JogoBichoRepository;

/**
 *
 * @author Daniel
 */
public class JogosApostadosTableModel extends AbstractTableModel{

    private final JogoBichoRepository repository;
    private final String columns[] = new String[]{"Número Apostado", "Valor Acumulado", "Data da Aposta"};
    private ArrayList<JogoBicho> jogos;
    private final DecimalFormat formatter = new DecimalFormat("##0.00");
    
    public JogosApostadosTableModel (JogoBichoRepository repository) {
        this.jogos = new ArrayList<>();
        this.repository = repository;
        refreshTable();
    }
    
    private void refreshTable() {
        jogos = repository.getAll(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        System.out.println(jogos.size());
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return jogos.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return jogos.get(rowIndex).getNumeroApostado();
            case 1:
                return formatter.format(jogos.get(rowIndex).getValorApostado());
            case 2:
                return jogos.get(rowIndex).getDataAposta();
            default:
                return "";
        }
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        try {
            float valor = Float.parseFloat(value.toString().replace(",", "."));
            JogoBicho jogo = get(rowIndex);
            jogo.setValorApostado(valor);
            repository.update(jogo);
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "O Valor informado é inválido");
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1;
    }
    
    public boolean add(JogoBicho jogo) {
        boolean status = repository.add(jogo);
        refreshTable();
        return status;
    }

    public JogoBicho get(int selectedRow) {
        return jogos.get(selectedRow);
    }

    public void remove(JogoBicho jogo) {
        repository.remove(jogo);
        refreshTable();
    }

    public ArrayList<JogoBicho> getList() {
        return jogos;
    }
}
