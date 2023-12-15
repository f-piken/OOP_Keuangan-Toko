/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_akhir_prak;

/**
 *
 * @author ASUS
 */
public class grosir extends toko{
    int diskon,total,min_or;
    
    public grosir() {
        getjenis("grosir");
        min_or=12;
    }
    
//    @Override
    public int diskon(int dis){
        diskon=(harga*jumlah*dis)/100;
        return diskon;
    }
    
    @Override
    public int hittod(int dit){
        total = harga*jumlah - diskon(dit);
        return total;
    }
}
