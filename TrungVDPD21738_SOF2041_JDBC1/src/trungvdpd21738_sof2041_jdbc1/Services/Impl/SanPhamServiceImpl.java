/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.SanPham;
import trungvdpd21738_sof2041_jdbc1.Repositories.SanPhamRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.SanPhamRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.SanPhamService;

/**
 *
 * @author TrungVD
 */
public class SanPhamServiceImpl implements SanPhamService {

    private SanPhamRepository sanPhamRepository = new SanPhamRepositoryImpl();

    private int valiDate(SanPham sanPham) {
        if (sanPham.getTenSP().trim().length() == 0 || sanPham.getMaSP().trim().length() == 0) {
            return 1;
        }
        
        return 0;
    }

    @Override
    public ArrayList<SanPham> getListSanPhamDB() {
        return sanPhamRepository.getListSanPhamDB();
    }

    @Override
    public String themSanPham(SanPham sanPham) {
        if (valiDate(sanPham) == 1) {
            return "Không để trống thông tin sản phẩm";
        }
        boolean themSP = sanPhamRepository.themSanPham(sanPham);
        if (themSP) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String suaSanPham(SanPham sanPham, String maSPSua) {
        if (valiDate(sanPham) == 1) {
            return "Không để trống thông tin sản phẩm";
        }
        boolean suaSP = sanPhamRepository.suaSanPham(sanPham, maSPSua);
        if (suaSP) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public ArrayList<SanPham> timKiemSanPham(String tenSPSua) {
        return sanPhamRepository.timKiemSanPham(tenSPSua);
    }

    @Override
    public String xoaSanPham(String maSPXoa) {

        Boolean xoaSP = sanPhamRepository.xoaSanPham(maSPXoa);
        System.out.println(sanPhamRepository.xoaSanPham(maSPXoa));
        if (xoaSP) {
            return "Xóa thành công";
        } else {
            return "Xóa không thành công!";
        }
    }
}
