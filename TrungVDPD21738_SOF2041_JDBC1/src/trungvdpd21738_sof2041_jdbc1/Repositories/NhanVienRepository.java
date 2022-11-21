/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhanVien;
import trungvdpd21738_sof2041_jdbc1.ViewModels.NhanVienViewModel;

/**
 *
 * @author TrungVD
 */
public interface NhanVienRepository {
    public ArrayList<NhanVien> getListNSXDB();
    
    public Boolean themNhanVien(NhanVien nhanVien);
    
    public Boolean suaNhanVien(NhanVien nhanVien, String maNVSua);
    
    public Boolean xoaNhanVien(String maNVXoa);
    
    public ArrayList<NhanVien> timKiemNV(String tenNV);
    
    public ArrayList<NhanVienViewModel> listNVViewModel();
    
    public Boolean themNhanVien2(NhanVien nhanVien);
    
    public Boolean suaNhanVien2(NhanVien nhanVien, String maNVSua);
}
