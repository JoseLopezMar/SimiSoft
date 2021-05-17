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
public class Contacto {
    private DataAccess dataAccess = DataAccess.Instance();
    private int idContacto;
    private int codigoPostal;
    private String comuna;
    private String calle;
    private String telefono;
    private int idEstado;
    private int idMunicipio;
    private int idLocalidad;

    public Contacto() {
    }

    public Contacto(int codigoPostal, String comuna, String calle, String telefono, int idEstado, int idMunicipio, int idLocalidad) {
        this.codigoPostal = codigoPostal;
        this.comuna = comuna;
        this.calle = calle;
        this.telefono = telefono;
        this.idEstado = idEstado;
        this.idMunicipio = idMunicipio;
        this.idLocalidad = idLocalidad;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM Contactos";
        return dataAccess.Query(query);
    }
    
    public void GetById() {
        String query = "SELECT * FROM Contactos WHERE idContacto = " + idContacto;
        DefaultTableModel res = dataAccess.Query(query);
        idContacto = (int) res.getValueAt(0, 0);
        codigoPostal = (int) res.getValueAt(0, 1);
        comuna = (String) res.getValueAt(0, 2);
        calle = (String) res.getValueAt(0, 3);
        telefono = (String) res.getValueAt(0, 4);
        idEstado = (int) res.getValueAt(0, 5);
        idMunicipio = (int) res.getValueAt(0, 6);
        idLocalidad = (int) res.getValueAt(0, 7);
    }
    
    public boolean Add() {
        String query = "INSERT INTO Contactos(codigoPostal, comuna, calle, telefono, idEstado, idMunicipio, idLocalidad) "
                + "VALUES(" + codigoPostal + ",'" + comuna + "','" + calle + "','" + telefono + "'," + idEstado + "," 
                + idMunicipio + "," + idLocalidad + ")";
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Delete() {
        String query = "DELETE FROM Contactos WHERE idContacto = " + idContacto;
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Update() {
        String query = "UPDATE Contactos set "
                + "codigoPostal = " + codigoPostal + ", "
                + "comuna = '" + comuna + "', "
                + "calle = '" + calle + "', "
                + "telefono = '" + telefono + "', "
                + "idEstado = " + idEstado + ", "
                + "idMunicipio = " + idMunicipio + ", "
                + "idLocalidad = " + idLocalidad + " "
                + "WHERE idContacto = " + idContacto;
        return dataAccess.Execute(query) >= 1;
    }
    
    public DefaultTableModel GetAllOrdered() {
        String query = "SELECT * FROM Contactos ORDER BY comuna, calle";
        return dataAccess.Query(query);
    }
    
    public DefaultTableModel GetAllSearch(String caracteres) {
        String query = "SELECT * FROM Contactos WHERE comuna LIKE '" + caracteres + "%'";
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
    
    public String estado(){ //Selecciona o manda el foco en el combo box del nombre que se tiene registrada
        String query = "SELECT * FROM Estados WHERE idEstado = " + idEstado;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 1);
    }
    
    public DefaultComboBoxModel GetMunicipios(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Municipios ORDER BY municipio";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> municipios = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            municipios.add((String) res.getValueAt(i, 2));
        }
        return new DefaultComboBoxModel(municipios);
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
    
    public DefaultComboBoxModel GetLocalidades(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Localidades ORDER BY localidad";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> localidades = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            localidades.add((String) res.getValueAt(i, 2));
        }
        return new DefaultComboBoxModel(localidades);
    }
    
    public void GetIdByLocalidad(String localidad){ //Guarda el ID en base al nombre seleccionado
        String query = "SELECT * FROM Localidades WHERE localidad = '" + localidad + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idLocalidad = (int) res.getValueAt(0, 0);
    }
    
    public String localidad(){ //Selecciona o manda el foco en el combo box del nombre que se tiene registrada
        String query = "SELECT * FROM Localidades WHERE idLocalidad = " + idLocalidad;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 2);
    }
}
