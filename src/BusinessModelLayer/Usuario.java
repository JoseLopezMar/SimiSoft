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
public class Usuario {
    private DataAccess dataAccess = DataAccess.Instance();
    private int idUsuario;
    private int idSucursal;
    private String tipoUsuario;
    private String nombre;
    private int idContacto;
    private String usuario;
    private String password;
    private int activo;

    public Usuario() {
    }

    public Usuario(int idSucursal, String tipoUsuario, String nombre, int idContacto, String usuario, String password, int activo) {
        this.idSucursal = idSucursal;
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.idContacto = idContacto;
        this.usuario = usuario;
        this.password = password;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM Usuarios";
        return dataAccess.Query(query);
    }
    
    public void GetById() {
        String query = "SELECT * FROM Usuarios WHERE idUsuario = " + idUsuario;
        DefaultTableModel res = dataAccess.Query(query);
        idUsuario = (int) res.getValueAt(0, 0);
        idSucursal = (int) res.getValueAt(0, 1);
        tipoUsuario = (String) res.getValueAt(0, 2);
        nombre = (String) res.getValueAt(0, 3);
        idContacto = (int) res.getValueAt(0, 4);
        usuario = (String) res.getValueAt(0, 5);
        password = (String) res.getValueAt(0, 6);
        activo = (boolean) res.getValueAt(0, 7) ? 1 : 0;
    }
    
    public boolean Add() {
        String query = "INSERT INTO Usuarios(idSucursal, tipoUsuario, nombre, idContacto, usuario, password, activo) "
                + "VALUES(" + idSucursal + ",'" + tipoUsuario + "','" + nombre + "'," + idContacto + ",'" + usuario 
                + "','" + password + "'," + activo + ")";
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Delete() {
        String query = "DELETE FROM Usuarios WHERE idUsuario = " + idUsuario;
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Update() {
        String query = "UPDATE Usuarios set "
                + "idSucursal = " + idSucursal + ", "
                + "tipoUsuario = '" + tipoUsuario + "', "
                + "nombre = '" + nombre + "', "
                + "idContacto = " + idContacto + ", "
                + "usuario = '" + usuario + "', "
                + "password = '" + password + "', "
                + "activo = " + activo + " "
                + "WHERE idUsuario = " + idUsuario;
        return dataAccess.Execute(query) >= 1;
    }
    
    public DefaultTableModel GetAllOrdered() {
        String query = "SELECT * FROM Usuarios ORDER BY nombre";
        return dataAccess.Query(query);
    }
    
    public DefaultTableModel GetAllSearch(String caracteres) {
        String query = "SELECT * FROM Usuarios WHERE nombre LIKE '" + caracteres + "%'";
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
    
    public DefaultComboBoxModel GetSucursales(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Sucursales ORDER BY nombre";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> sucursales = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            sucursales.add((String) res.getValueAt(i, 1));
        }
        return new DefaultComboBoxModel(sucursales);
    }
    
    public void GetIdBySucursal(String sucursal){ //Guarda el ID en base al nombre seleccionado
        String query = "SELECT * FROM Sucursales WHERE nombre = '" + sucursal + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idSucursal = (int) res.getValueAt(0, 0);
    }
    
    public String sucursal(){ //Selecciona o manda el foco en el combo box del nombre que se tiene registrada
        String query = "SELECT * FROM Sucursales WHERE idSucursal = " + idSucursal;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 1);
    }
}
