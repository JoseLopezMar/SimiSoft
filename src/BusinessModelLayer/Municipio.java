/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModelLayer;

import DataAccessLayer.DataAccess;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author INFO-JOSEASUNCIONLOP
 */
public class Municipio {
    private DataAccess dataAccess = DataAccess.Instance();
    private int idMunicipio;
    private int idEstado;
    private String municipio;

    public Municipio() {
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM Municipios";
        return dataAccess.Query(query);
    }
    
    public void GetById() {
        String query = "SELECT * FROM Municipios WHERE idMunicipio = " + idMunicipio;
        DefaultTableModel res = dataAccess.Query(query);
        idMunicipio = (int) res.getValueAt(0, 0);
        idEstado = (int) res.getValueAt(0, 1);
        municipio = (String) res.getValueAt(0, 2);
    }
    
    public boolean Add() {
        String query = "INSERT INTO Municipios(idEstado, municipio) "
                + "VALUES(" + idEstado + ",'" + municipio + "')";
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Delete() {
        String query = "DELETE FROM Municipios WHERE idMunicipio = " + idMunicipio;
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Update() {
        String query = "UPDATE Municipios set "
                + "idEstado = " + idEstado + ", "
                + "municipio = '" + municipio + "' "
                + "WHERE idMunicipio = " + idMunicipio;
        return dataAccess.Execute(query) >= 1;
    }
    
    public DefaultTableModel GetAllOrdered() {
        String query = "SELECT * FROM Municipios ORDER BY municipio";
        return dataAccess.Query(query);
    }
    
    public DefaultTableModel GetAllSearch(String caracteres) {
        String query = "SELECT * FROM Municipios WHERE municipio LIKE '" + caracteres + "%'";
        return dataAccess.Query(query);
    }
    
    public DefaultComboBoxModel GetEstados(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Estados ORDER BY estado";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> estados = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            estados.add((String) res.getValueAt(i, 1));
        }
        return new DefaultComboBoxModel(estados);
    }
    
    public void GetIdByEstado(String estado){ //Guarda el ID en base al nombre seleccionado
        String query = "SELECT * FROM Estados WHERE estado = '" + estado + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idEstado = (int) res.getValueAt(0, 0);
    }
    
    public String estado(){ //Selecciona o manda el foco en el combo box del nombre que se tiene registrado
        String query = "SELECT * FROM Estados WHERE idEstado = " + idEstado;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 1);
    }
}
