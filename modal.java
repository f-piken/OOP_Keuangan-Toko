/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_akhir_prak;

/**
 *
 * @author ASUS
 */
public abstract class modal {
    //atribut
    private int Modal;
    int harga,jumlah;
    private String jenis;
    String nama;
    
    //konstrukotor
    public modal(){
        Modal=10000000;
    }
    
    //method
    int setmodal(){
        return Modal;
    }
    void getnama(String nama){
        this.nama = nama;
    }
    String setnama(){
        return nama;
    }
    void getjenis(String jenis){
        this.jenis = jenis;
    }
    String setjenis(){
        return jenis;
    }
    void getharga(int harga){
        this.harga = harga;
    }
    int setharga(){
        return harga;
    }
    void getjumlah(int jumlah){
        this.jumlah = jumlah;
    }
    int setjumlah(){
        return jumlah;
    }
    
    abstract int hittod();
    abstract int hittod(int jum);
    abstract int hittod(int i,int jum,int val);
}
