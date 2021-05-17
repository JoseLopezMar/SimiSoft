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
public class Localidad {
    private DataAccess dataAccess = DataAccess.Instance();
    private int idLocalidad;
    private int idMunicipio;
    private String localidad;

    public Localidad() {
    }

    public Localidad(int idMunicipio, String localidad) {
        this.idMunicipio = idMunicipio;
        this.localidad = localidad;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM Localidades";
        return dataAccess.Query(query);
    }
    
    public void GetById() {
        String query = "SELECT * FROM Localidades WHERE idLocalidad = " + idLocalidad;
        DefaultTableModel res = dataAccess.Query(query);
        idLocalidad = (int) res.getValueAt(0, 0);
        idMunicipio = (int) res.getValueAt(0, 1);
        localidad = (String) res.getValueAt(0, 2);
    }
    
    public boolean Add() {
        String query = "INSERT INTO Localidades(idMunicipio, localidad) "
                + "VALUES(" + idMunicipio + ",'" + localidad + "')";
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Delete() {
        String query = "DELETE FROM Localidades WHERE idLocalidad = " + idLocalidad;
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Update() {
        String query = "UPDATE Localidades set "
                + "idMunicipio = " + idMunicipio + ", "
                + "localidad = '" + localidad + "' "
                + "WHERE idLocalidad = " + idLocalidad;
        return dataAccess.Execute(query) >= 1;
    }
    
    public DefaultTableModel GetAllOrdered() {
        String query = "SELECT * FROM Localidades ORDER BY localidad";
        return dataAccess.Query(query);
    }
    
    public DefaultTableModel GetAllSearch(String caracteres) {
        String query = "SELECT * FROM Localidades WHERE localidad LIKE '" + caracteres + "%'";
        return dataAccess.Query(query);
    }
    
    public DefaultComboBoxModel GetMunicipios(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Municipios ORDER BY municipio";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> municipio = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            municipio.add((String) res.getValueAt(i, 2));
        }
        return new DefaultComboBoxModel(municipio);
    }
    
    public void GetIdByMunicipio(String municipio){ //Guarda el ID en base al nombre seleccionado
        String query = "SELECT * FROM Municipios WHERE municipio = '" + municipio + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idMunicipio = (int) res.getValueAt(0, 0);
    }
    
    public String municipio(){ //Selecciona o manda el foco en el combo box del nombre que se tiene registrada
        String query = "SELECT * FROM Municipios WHERE idMunicipio = " + idMunicipio;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 2);
    }
}
