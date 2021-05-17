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
public class Proveedor {
    private DataAccess dataAccess = DataAccess.Instance();
    private int idProveedor;
    private String nombre;
    private int idContacto;
    private int activo;

    public Proveedor() {
    }

    public Proveedor(String nombre, int idContacto, int activo) {
        this.nombre = nombre;
        this.idContacto = idContacto;
        this.activo = activo;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM Proveedores";
        return dataAccess.Query(query);
    }
    
    public void GetById() {
        String query = "SELECT * FROM Proveedores WHERE idProveedor = " + idProveedor;
        DefaultTableModel res = dataAccess.Query(query);
        idProveedor = (int) res.getValueAt(0, 0);
        nombre = (String) res.getValueAt(0, 1);
        idContacto = (int) res.getValueAt(0, 2);
        activo = (boolean) res.getValueAt(0, 3) ? 1 : 0;
    }
    
    public boolean Add() {
        String query = "INSERT INTO Proveedores(nombre, idContacto, activo) "
                + "VALUES('" + nombre + "'," + idContacto + "," + activo + ")";
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Delete() {
        String query = "DELETE FROM Proveedores WHERE idProveedor = " + idProveedor;
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Update() {
        String query = "UPDATE Proveedores set "
                + "nombre = '" + nombre + "', "
                + "idContacto = " + idContacto + ", "
                + "activo = " + activo + " "
                + "WHERE idProveedor = " + idProveedor;
        return dataAccess.Execute(query) >= 1;
    }
    
    public DefaultTableModel GetAllOrdered() {
        String query = "SELECT * FROM Proveedores ORDER BY nombre";
        return dataAccess.Query(query);
    }
    
    public DefaultTableModel GetAllSearch(String caracteres) {
        String query = "SELECT * FROM Proveedores WHERE nombre LIKE '" + caracteres + "%'";
        return dataAccess.Query(query);
    }
    
    public DefaultComboBoxModel GetContactos(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Contactos ORDER BY codigoPostal";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> contactos = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            contactos.add(String.valueOf(res.getValueAt(i, 1))  + "," + (String) res.getValueAt(i, 2)
            + "," + (String) res.getValueAt(i, 3) + "," + (String) res.getValueAt(i, 4));
        }
        return new DefaultComboBoxModel(contactos);
    }
    
    public void GetIdByContacto(String contacto){ //Guarda el ID en base al campo seleccionado
        String datosContacto [] = contacto.split(",");
        String query = "SELECT * FROM Contactos WHERE codigoPostal = " + Integer.parseInt(datosContacto[0]) + " AND "
        + "comuna = '" + datosContacto[1] + "' AND " + "calle = '" + datosContacto[2] + "' AND " + "telefono = '"
        + datosContacto[3] + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idContacto = (int) res.getValueAt(0, 0);
    }
    
    public String contacto(){ //Selecciona o manda el foco en el combo box del contacto que se tiene registrado
        String query = "SELECT * FROM Contactos WHERE idContacto = " + idContacto;
        DefaultTableModel res = dataAccess.Query(query);
        return String.valueOf(res.getValueAt(0, 1)) + "," + (String) res.getValueAt(0, 2)
            + "," + (String) res.getValueAt(0, 3) + "," + (String) res.getValueAt(0, 4);
    }
}
