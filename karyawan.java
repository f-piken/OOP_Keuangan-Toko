/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_akhir_prak;

/**
 *
 * @author ASUS
 */
public interface karyawan {
    int gaji1=1500000;
    int gaji2=1200000;
    int lembur=10000;
    
    abstract int gaji(int tipe,int Jlmbr);
    abstract String Tgl(String tgl);
}
