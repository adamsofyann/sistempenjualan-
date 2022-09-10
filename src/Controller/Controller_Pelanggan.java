/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Model_DAO;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.pelanggan;
import model.tableModel_Pelanggan;
import view.MPelanggan;

/**
 * 
 * @author LAB 2 PC 04
 */
public class Controller_Pelanggan {
    MPelanggan form;
    Model_DAO<pelanggan>model;
    List<pelanggan> list;
    String[] header;
    
    //konstruktor
    public Controller_Pelanggan(MPelanggan form){
        this.form = form;
        model = new DAO.DAO_Pelanggan();
        list = model.getAll();
        header = new String[]{"KODE","NAMA PELANGGAN","ALAMAT","TELEPON"};
        
        form.getTblplg().setShowGrid(true);
        form.getTblplg().setShowVerticalLines(true);
        form.getTblplg().setGridColor(Color.blue);
    }
    
    //method untuk menampilkan kode pelanggan berikutnya
    public void tampilurutankode(){
        pelanggan p = new pelanggan();
        model.autonumber(p);
        form.getTxtkdplg().setText(String.valueOf(model.autonumber(p)));
    }
    
    //method untuk membersihkan objek inputan yang ada pada form
    public void reset(){
        form.getTxtkdplg().setText("");
        form.getTxtnmplg().setText("");
        form.getTxtalamat().setText("");
        form.getTxttelp().setText("");
        form.getTxtnmplg().requestFocus();
        tampilurutankode();
        isiTable();
    }
    
    //method untuk menampilkan semua data kedalam Jtable
    public void isiTable(){
        list = model.getAll();
        
        //Script agar Jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},header){
         public boolean isCellEditable(int rowIndex, int columnIndex){
             return false;
         }
         
        };
        
        Object[] data = new Object[header.length];
        for (pelanggan p : list){
            data [0] = p.getKode();
            data [1] = p.getNama();
            data [2] = p.getAlamat();
            data [3] = p.getTelp();
            tblModel.addRow(data);
        }
        form.getTblplg().setModel(tblModel);
    }
    
    //method untuk meletakan data kedalam text berdasarkan data yang dipilih dari Jtable
    public void isiField(int row){
        form.getTxtkdplg().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmplg().setText(list.get(row).getNama());
        form.getTxtalamat().setText(list.get(row).getAlamat());
        form.getTxttelp().setText(list.get(row).getTelp());
    }
    //method untuk simpan data
    public void insert(){
        pelanggan p = new pelanggan();
        p.setKode(form.getTxtkdplg().getText());
        p.setNama(form.getTxtnmplg().getText());
        p.setAlamat(form.getTxtalamat().getText());
        p.setTelp(form.getTxttelp().getText());
        
      
        model.insert(p);
    }
     public void update(){
         pelanggan p = new pelanggan();
         p.setKode(form.getTxtkdplg().getText());
         p.setNama(form.getTxtnmplg().getText());
         p.setAlamat(form.getTxtalamat().getText());
         p.setTelp(form.getTxttelp().getText());
         
         model.update(p);
         
     }
     
     public void delete(){
         if(!form.getTxtkdplg().getText().trim().isEmpty()){
             int kode =Integer.parseInt(form.getTxtkdplg().getText());
             model.delete(kode);
             
         }else{
             JOptionPane.showMessageDialog(form, "pilih data yang akan dihapus");
         }
     }
     public void isiTableCari(){
         list = model.getCari(form.getTxtkdplg().getText().trim());
         DefaultTableModel tblModel = new DefaultTableModel (new Object[][]{}, header);
         Object[] data = new Object[header.length];
         for (pelanggan p : list){
             data [0] = p.getKode();
             data [1] = p.getNama();
             data [2] = p.getAlamat();
             data [3] = p.getTelp();
             tblModel.addRow(data);
         }
         
     }
    

}

