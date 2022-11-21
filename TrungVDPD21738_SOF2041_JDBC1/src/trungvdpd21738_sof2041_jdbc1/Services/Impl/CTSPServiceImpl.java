/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChiTietSanPham;
import trungvdpd21738_sof2041_jdbc1.Repositories.ChiTietSanPhamRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.ChiTietSanPhamRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.CTSPService;
import trungvdpd21738_sof2041_jdbc1.ViewModels.SPBanHangViewModel;
import trungvdpd21738_sof2041_jdbc1.ViewModels.SanPhamTest;

/**
 *
 * @author TrungVD
 */
public class CTSPServiceImpl implements CTSPService{

    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepositoryImpl();
    
    private int valiDate(ChiTietSanPham chiTietSanPham){
        return 0;
    }
    
    @Override
    public ArrayList<ChiTietSanPham> getListCTSPDB() {
        return chiTietSanPhamRepository.getListCTSPDB();
    }

    @Override
    public String themCTSP(ChiTietSanPham chiTietSanPham) {
        boolean themCTSP = chiTietSanPhamRepository.themCTSP(chiTietSanPham);
        if(themCTSP){
            return "Thêm thành công!";
        }else{
            return "Thêm thất bại";
        }
    }

    @Override
    public String suaCTSP(ChiTietSanPham chiTietSanPham, String iDCTSP) {
        boolean suaCTSP = chiTietSanPhamRepository.suaCTSP(chiTietSanPham, iDCTSP);
        if(suaCTSP){
            return "Sửa thành công!";
        }else{
            return "Sửa thất bại";
        }
    }

    @Override
    public String xoaCTSP(String maCTSPX) {
        boolean xoaCTSP = chiTietSanPhamRepository.xoaCTSP(maCTSPX);
        if(xoaCTSP){
            return "Xóa thành công!";
        }else{
            return "Xóa thất bại";
        }
    }

    @Override
    public ArrayList<ChiTietSanPham> timCTSP(String tenCTSP) {
        return chiTietSanPhamRepository.timCTSP(tenCTSP);
    }

    @Override
    public ArrayList<SPBanHangViewModel> getListSP() {
        return chiTietSanPhamRepository.getListSP();
    }

    @Override
    public ArrayList<SPBanHangViewModel> timSPBanHang(String tenTimKiem) {
        return chiTietSanPhamRepository.timSPBanHang(tenTimKiem);
    }

    @Override
    public ArrayList<SanPhamTest> getListSPTest() {
        return chiTietSanPhamRepository.getListSPTest();
    }

    @Override
    public String updateSPTest(SanPhamTest sanPham, String iDSP) {
        boolean update = chiTietSanPhamRepository.updateSPTest(sanPham, iDSP);
        if(update){
            return "Update thành công!";
        }else{
            return "Update thất bại!";
        }
    }

    @Override
    public String themSanPham(SanPhamTest sanPham) {
        boolean them = chiTietSanPhamRepository.themSanPham(sanPham);
        if(them){
            return "Update thành công!";
        }else{
            return "Update thất bại!";
        }
    }
    
    
    
}
