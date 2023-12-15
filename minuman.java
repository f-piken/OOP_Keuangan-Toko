/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_akhir_prak;

/**
 *
 * @author ASUS
 */
public class minuman extends toko{
    String merk;
    int diskon,total;
    
    public minuman(){
        diskon = 3000;
        getjenis("minuman");
    }
    
    @Override
    public int hittod(){
        total = (harga*jumlah) - diskon;
        return total;
    }
}
