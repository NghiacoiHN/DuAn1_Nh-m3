/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhanVien;
import trungvdpd21738_sof2041_jdbc1.ViewModels.NhanVienViewModel;

/**
 *
 * @author TrungVD
 */
public interface NhanVienService {
    
    public ArrayList<NhanVien> getListNSXDB();
    
    public String themNhanVien(NhanVien nhanVien);
    
    public String suaNhanVien(NhanVien nhanVien, String maNVSua);
    
    public String xoaNhanVien(String maNVXoa);
    
    public ArrayList<NhanVien> timKiemNV(String tenNV);
    
    public ArrayList<NhanVienViewModel> listNVViewModel();
    
    public String themNhanVien2(NhanVien nhanVien);
}
