/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.DongSanPham;
import trungvdpd21738_sof2041_jdbc1.Repositories.DongSanPhamRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.DongSanPhamRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.DongSPService;

/**
 *
 * @author TrungVD
 */
public class DongSPServiceImpl implements DongSPService{
    
    private DongSanPhamRepository dongSPRepository = new DongSanPhamRepositoryImpl();
    
    private int valiDate(DongSanPham dongSanPham){
        if(dongSanPham.getMaDSP().trim().length()==0 ||
                dongSanPham.getTenDSP().trim().length() == 0){
            return 1;
        }
        return 0;
    }
    @Override
    public ArrayList<DongSanPham> getListDSPDB() {
        return dongSPRepository.getListDSPDB();
    }

    @Override
    public String themDSP(DongSanPham dongSanPham) {
        if(valiDate(dongSanPham) == 1){
            return "Không để trống thông tin sản phẩm";
        }
        boolean themDSP = dongSPRepository.themDSP(dongSanPham);
        if(themDSP){
            return "Thêm thành công!";
        }else{
            return "Thêm thất bại!";
        }
    }

    @Override
    public String suaDSP(DongSanPham dongSanPham, String maDSP) {
        boolean suaDSP = dongSPRepository.suaDSP(dongSanPham, maDSP);
        if(suaDSP){
            return "Sửa thành công!";
        }else{
            return "Sửa thất bại!";
        }
    }

    @Override
    public String xoaDSP(String maDSPXoa) {
        boolean xoaDSP = dongSPRepository.xoaDSP(maDSPXoa);
        if(xoaDSP){
            return "Xóa thành công!";
        }else{
            return "Xóa thất bại!";
        }
    }

    @Override
    public ArrayList<DongSanPham> timKiemDSP(String tenDSPTim) {
        return dongSPRepository.timKiemDSP(tenDSPTim);
    }
    
}
