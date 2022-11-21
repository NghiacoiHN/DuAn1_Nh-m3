/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.KhachHang;
import trungvdpd21738_sof2041_jdbc1.Repositories.KhachHangRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.KhachHangRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.KhachHangService;

/**
 *
 * @author TrungVD
 */
public class KhachHangServiceImpl implements KhachHangService{

    KhachHangRepository khachHangRepository = new KhachHangRepositoryImpl();
    @Override
    public ArrayList<KhachHang> getListKHDB() {
        return khachHangRepository.getListKHDB();
    }

    @Override
    public String themKH(KhachHang khachHang) {
        boolean themKH = khachHangRepository.themKH(khachHang);
        if(themKH){
            return "Thêm thành công!";
        }else{
            return "Thêm thất bại!";
        }
    }

    @Override
    public String suaKH(KhachHang khachHang, String iD) {
        boolean suaKH = khachHangRepository.suaKH(khachHang, iD);
        if(suaKH){
            return "Sửa thành công!";
        }else{
            return "Sửa thất bại!";
        }
    }

    @Override
    public String xoaKH(String maKH) {
        boolean xoaKH = khachHangRepository.xoaKH(maKH);
        if(xoaKH){
            return "Xóa thành công!";
        }else{
            return "Xóa thất bại!";
        }
    }

    @Override
    public ArrayList<KhachHang> timKH(String tenKH) {
        return khachHangRepository.timKH(tenKH);
    }
    
}
