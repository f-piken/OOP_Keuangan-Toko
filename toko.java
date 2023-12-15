/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_akhir_prak;

/**
 *
 * @author ASUS
 */
public class toko extends modal implements karyawan{
    //atribut
    private String kode;
    String merk;
    
    //method
    void getkode(String kode){
        this.kode = kode;
    }
    String setkode(){
        return kode;
    }
    void getmerk(String merk){
        this.merk = merk;
    }
    String setmerk(){
        return merk;
    }
    @Override
    int hittod(){
        int total;
        total = jumlah * harga;
        return total;
    }
    @Override
    int hittod(int jum){
        int mod=setmodal();
        mod=mod-jum;
        return mod;
    }
    @Override
    int hittod(int i,int jum,int val){
        int con[] =new int[jum] ;
        con[0]=hittod();
        con[i]=con[i]+val;
        int mod=setmodal();
        mod=mod-con[i];
        return mod;
    }

    @Override
    public int gaji(int tipe,int Jlmbr) {
        int total;
        total = tipe + (lembur*Jlmbr);
        return total;
    }
    @Override
    public String Tgl(String tgl){
        return tgl;
    }

    void tgl(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
