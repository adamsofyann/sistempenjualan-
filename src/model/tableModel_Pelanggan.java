/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LAB 2 PC 04
 */
public class tableModel_Pelanggan extends AbstractTableModel{
    List<pelanggan>list;
    public tableModel_Pelanggan(List<pelanggan> list){
        this.list = list;
    }

    @Override
    public int getRowCount() {
       return list.size();
    }

    @Override
    public int getColumnCount() {
      return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       switch (columnIndex) {
               case 0:
               return list.get(rowIndex).getKode();
               case 1:
               return list.get(rowIndex).getNama();
               case 2:
               return list.get(rowIndex).getAlamat();
               case 3:
               return list.get(rowIndex).getTelp();
               default:
                   return null;
               
       } 
    }
}