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
public class ProductoProveedores {
    private DataAccess dataAccess = DataAccess.Instance();
    private int idProductoProveedor;
    private int idProveedor;
    private int idProducto;

    public ProductoProveedores() {
    }

    public ProductoProveedores(int idProveedor, int idProducto) {
        this.idProveedor = idProveedor;
        this.idProducto = idProducto;
    }

    public int getIdProductoProveedor() {
        return idProductoProveedor;
    }

    public void setIdProductoProveedor(int idProductoProveedor) {
        this.idProductoProveedor = idProductoProveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM ProductoProvedores";
        return dataAccess.Query(query);
    }
    
    public void GetById() {
        String query = "SELECT * FROM ProductoProvedores WHERE idProductoProveedor = " + idProductoProveedor;
        DefaultTableModel res = dataAccess.Query(query);
        idProductoProveedor = (int) res.getValueAt(0, 0);
        idProveedor = (int) res.getValueAt(0, 1);
        idProducto = (int) res.getValueAt(0, 2);
    }
    
    public boolean Add() {
        String query = "INSERT INTO ProductoProvedores(idProveedor, idProducto) "
                + "VALUES(" + idProveedor + "," + idProducto + ")";
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Delete() {
        String query = "DELETE FROM ProductoProvedores WHERE idProductoProveedor = " + idProductoProveedor;
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Update() {
        String query = "UPDATE ProductoProvedores set "
                + "idProveedor = " + idProveedor + ", "
                + "idProducto = " + idProducto + " "
                + "WHERE idProductoProveedor = " + idProductoProveedor;
        return dataAccess.Execute(query) >= 1;
    }
    
    public DefaultTableModel GetAllOrdered() {
        String query = "SELECT * FROM ProductoProvedores ORDER BY idProveedor, idProducto";
        return dataAccess.Query(query);
    }
    
    public DefaultTableModel GetAllSearch(String caracteres) {
        String query = "SELECT * FROM ProductoProvedores WHERE idProveedor LIKE '" + caracteres + "%'";
        return dataAccess.Query(query);
    }
    
    public DefaultComboBoxModel GetProveedores(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Proveedores ORDER BY nombre";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> proveedores = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            proveedores.add((String) res.getValueAt(i, 1));
        }
        return new DefaultComboBoxModel(proveedores);
    }
    
    public void GetIdByProveedor(String proveedor){ //Guarda el ID en base al nombre seleccionado
        String query = "SELECT * FROM Proveedores WHERE nombre = '" + proveedor + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idProveedor = (int) res.getValueAt(0, 0);
    }
    
    public String proveedor(){ //Selecciona o manda el foco en el combo box del nombre que se tiene registrada
        String query = "SELECT * FROM Proveedores WHERE idProveedor = " + idProveedor;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 1);
    }
    
    public DefaultComboBoxModel GetProductos(){ //Obtiene los nombres para ponerlos en el combo box
        String query = "SELECT * FROM Productos ORDER BY nombre";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> productos = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            productos.add((String) res.getValueAt(i, 2));
        }
        return new DefaultComboBoxModel(productos);
    }
    
    public void GetIdByProducto(String producto){ //Guarda el ID en base al nombre seleccionado
        String query = "SELECT * FROM Productos WHERE nombre = '" + producto + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idProducto = (int) res.getValueAt(0, 0);
    }
    
    public String producto(){ //Selecciona o manda el foco en el combo box del nombre que se tiene registrada
        String query = "SELECT * FROM Productos WHERE idProducto = " + idProducto;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 2);
    }
}
