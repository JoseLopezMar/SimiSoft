/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModelLayer;

import DataAccessLayer.DataAccess;
import java.sql.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author INFO-JOSEASUNCIONLOP
 */
//POJO
public class Producto {

    private DataAccess dataAccess = DataAccess.Instance();
    private int idProducto;
    private int idCategoria;
    private String nombre;
    private String precio;
    private Date caducidad;
    private String descuento;
    private int activo;

    public Producto() {
    }

    public Producto(int idCategoria, String nombre, String precio, Date caducidad, String descuento, int activo) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.precio = precio;
        this.caducidad = caducidad;
        this.descuento = descuento;
        this.activo = activo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM Productos";
        return dataAccess.Query(query);
    }

    public void GetById() {
        System.out.println("Estoy al principio de obtener los datos");
        String query = "SELECT * FROM Productos WHERE idProducto =  " + idProducto;
        DefaultTableModel res = dataAccess.Query(query);
        idProducto = (int) res.getValueAt(0, 0);
        idCategoria = (int) res.getValueAt(0, 1);
        nombre = (String) res.getValueAt(0, 2);
        precio = String.valueOf(res.getValueAt(0, 3));
        caducidad = Date.valueOf(String.valueOf(res.getValueAt(0, 4)));
        descuento = String.valueOf(res.getValueAt(0, 5));
        activo = (boolean) res.getValueAt(0, 6) ? 1 : 0;
    }

    public boolean Add() {
        String query = "INSERT INTO Productos(idCategoria, nombre, precio, caducidad, descuento, activo) "
                + "VALUES(" + idCategoria + ",'" + nombre + "'," + precio + ",'" + caducidad + "'," + descuento + "," + activo + ")";
        return dataAccess.Execute(query) >= 1;
    }

    public boolean Delete() {
        String query = "DELETE FROM Productos WHERE idProducto = " + idProducto;
        return dataAccess.Execute(query) >= 1;
    }

    public boolean Update() {
        String query = "UPDATE Productos set "
                + "idCategoria = " + idCategoria + ", "
                + "nombre = '" + nombre + "', "
                + "precio = " + precio + ", "
                + "caducidad = '" + caducidad + "', "
                + "descuento = " + descuento + ", "
                + "activo = " + activo + " "
                + "WHERE idProducto = " + idProducto;
        return dataAccess.Execute(query) >= 1;
    }
    
    public DefaultTableModel GetAllOrdered() {
        String query = "SELECT * FROM Productos ORDER BY nombre";
        return dataAccess.Query(query);
    }
    
    public DefaultTableModel GetAllSearch(String caracteres) {
        String query = "SELECT * FROM Productos WHERE nombre LIKE '" + caracteres + "%'";
        return dataAccess.Query(query);
    }
    
    public DefaultComboBoxModel GetCategories(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Categorias ORDER BY nombre";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> categories = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            categories.add((String) res.getValueAt(i, 1));
        }
        return new DefaultComboBoxModel(categories);
    }
    
    public void GetIdByCategory(String category){ //Guarda el ID en base al nombre seleccionado
        String query = "SELECT * FROM Categorias WHERE nombre = '" + category + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idCategoria = (int) res.getValueAt(0, 0);
    }
    
    public String category(){ //Selecciona o manda el foco en el combo box del nombre que se tiene registrada
        String query = "SELECT * FROM Categorias WHERE idCategoria = " + idCategoria;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 1);
    }
}
