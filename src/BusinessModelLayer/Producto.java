/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModelLayer;

import DataAccessLayer.DataAccess;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author INFO-JOSEASUNCIONLOP
 */
//POJO
public class Producto {

    private DataAccess dataAccess = DataAccess.Instance();
    private int idProducto;
    private String nombre;
    private Date caducidad;
    private int stock;
    private int idFarmacia;
    private int activo;

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM productos";
        return dataAccess.Query(query);
    }

    public void GetById() {
        String query = "SELECT * FROM productos WHERE idProducto =  " + idProducto;
        DefaultTableModel res = dataAccess.Query(query);
        idProducto = (int) res.getValueAt(0, 0);
        nombre = (String) res.getValueAt(0, 1);
        String fecha = String.valueOf(res.getValueAt(0, 2));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(fecha);
            long d = date.getTime();
            caducidad = new java.sql.Date(d);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha ingresado no válido: "+e.getMessage(),
                    "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        stock = (int) res.getValueAt(0, 3);
        idFarmacia = (int) res.getValueAt(0, 4);
        activo = (int) res.getValueAt(0, 5);
    }

    public boolean Add() {
        String query = "INSERT INTO productos(nombre, caducidad, stock, idFarmacia, activo) "
                + "VALUES('" + nombre + "','" + caducidad + "'," + stock + "," + idFarmacia + "," + activo + ")";
        return dataAccess.Execute(query) >= 1;
    }

    public boolean Delete() {
        String query = "DELETE FROM productos WHERE idProducto = " + idProducto;
        return dataAccess.Execute(query) >= 1;
    }

    public boolean Update() {
        String query = "UPDATE productos set "
                + "nombre = '" + nombre + "', "
                + "caducidad = '" + caducidad + "', "
                + "stock = " + stock + ", "
                + "idFarmacia = " + idFarmacia + ", "
                + "activo = " + activo + " "
                + "WHERE idProducto = " + idProducto;
        return dataAccess.Execute(query) >= 1;
    }

    public DefaultComboBoxModel GetNamesPharmacy(){
        String query = "SELECT * FROM farmacias";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> namesPharmacy = new Vector<String>();  
        for (int i = 0; i < res.getRowCount(); i++) {
            namesPharmacy.add((String) res.getValueAt(i, 1));
        }
        return new DefaultComboBoxModel(namesPharmacy);
    }
    
    public void GetIdByName(String namePharmacy){
        String query = "SELECT * FROM farmacias WHERE nombre = '" + namePharmacy + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idFarmacia = (int) res.getValueAt(0, 0);
    }
    
    public String NamePharmacy(){
        String query = "SELECT * FROM farmacias WHERE idFarmacia = " + idFarmacia;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 1);
    }
    
    public boolean Delete(int idFarmacia) {
        String query = "DELETE FROM productos WHERE idFarmacia = " + idFarmacia;
        return dataAccess.Execute(query) >= 1;
    }
}
