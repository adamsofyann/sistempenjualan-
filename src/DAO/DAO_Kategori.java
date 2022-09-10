/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import koneksi.Database;
import java.util.List;
import model.kategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB 2 PC 04
 */
public class DAO_Kategori implements Model_DAO<kategori>{

    public DAO_Kategori(){
        //konstruktor
        connection = Database.KoneksiDB();
    }
    
    Connection connection;
    String INSERT = "INSERT INTO kategori(kdkategori,nmkategori) values(?,?)";
    String UPDATE = "UPDATE kategori SET nmkategori=? WHERE kdkategori=?";
    String DELETE = "DELETE FROM kategori WHERE kdkategori=?";
    String SELECT = "SELECT * FROM kategori";
    String CARI = "SELECT * FROM kategori WHERE kdkategori LIKE ?";
    String COUNTER = "SELECT max(kdkategori) as koden FROM kategori";
    
    @Override
    public int autonumber(kategori object) {
        
    }

    @Override
    public void insert(kategori object) {
        PreparedStatement statement = null;
try {
    statement = connection.prepareStatement(CARI);
    statement.setInt(1, object.getKode());
    ResultSet rs = statement.executeQuery();
    if (rs.next()) //jika data sudah pernah disimpan
        JOptionPane.showMessageDialog(null,"Data sudah pernah di simpan!");
    else{ //jika data belum pernah disimpan
        PreparedStatement statement2 = null;
        statement2 = connection.prepareStatement(INSERT);
        statement2.setString(1, object.getNama());
        statement2.setInt(2, object.getKode());
        statement2.executeUpdate();
    }
} catch (Exception e) {
    e.printStackTrace();
}finally{
    try {
        statement.close();
    } catch (SQLException ex) {
        Logger.getLogger(DAO_Kategori.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    }

    @Override
    public void update(kategori object) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, object.getNama());
            statement.setInt(2, object.getKode());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data berhasil di ubah!");
        } catch (Exception e) {
   }finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void delete(Integer id) {
    PreparedStatement statement = null;
    try {
        statement = connection.prepareStatement(DELETE);
        statement.setInt(1,id);
        statement.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }finally{
        try {
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Kategori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   }

    @Override
    public List<kategori> getAll() {
        
   }

    @Override
    public List<kategori> getCari(String key) {
        
   }
    
}
