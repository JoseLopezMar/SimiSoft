/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModelLayer;
import DataAccessLayer.DataAccess;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author INFO-JOSEASUNCIONLOP
 */
public class Categoria {
    private DataAccess dataAccess = DataAccess.Instance();
    private int idCategoria;
    private String nombre;
    private int activo;

    public Categoria() {
    }

    public Categoria(String nombre, int activo) {
        this.nombre = nombre;
        this.activo = activo;
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

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM Categorias";
        return dataAccess.Query(query);
    }
    
    public void GetById() {
        String query = "SELECT * FROM Categorias WHERE idCategoria = " + idCategoria;
        DefaultTableModel res = dataAccess.Query(query);
        idCategoria = (int) res.getValueAt(0, 0);
        nombre = (String) res.getValueAt(0, 1);
        activo = (boolean)res.getValueAt(0, 2) ? 1 : 0;
    }
    
    public boolean Add() {
        String query = "INSERT INTO Categorias(nombre, activo) "
                + "VALUES('" + nombre + "'," + activo + ")";
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Delete() {
        String query = "DELETE FROM Categorias WHERE idCategoria = " + idCategoria;
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Update() {
        String query = "UPDATE Categorias set "
                + "nombre = '" + nombre + "', "
                + "activo = " + activo + " "
                + "WHERE idCategoria = " + idCategoria;
        return dataAccess.Execute(query) >= 1;
    }
    
    public DefaultTableModel GetAllOrdered() {
        String query = "SELECT * FROM Categorias ORDER BY nombre";
        return dataAccess.Query(query);
    }
    
    public DefaultTableModel GetAllSearch(String caracteres) {
        String query = "SELECT * FROM Categorias WHERE nombre LIKE '" + caracteres + "%'";
        return dataAccess.Query(query);
    }
}
