/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices.impl;

import DomainModels.DanhSachHoaDonDM;
import Reponsitories.DanhSachHoaDonIRepon;
import Reponsitories.impl.DanhSachHoaDonRepon;
import Sevices.DanhSachHoaDonIService;
import ViewModels.DanhSachHoaDonVM;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author longd
 */
public class DanhSachHoaDonService implements DanhSachHoaDonIService{

    DanhSachHoaDonIRepon dshd = new DanhSachHoaDonRepon();
    
    @Override
    public List<DanhSachHoaDonVM> fillAll() {
        List<DanhSachHoaDonVM> listVM = new ArrayList<>();
        List<DanhSachHoaDonDM> listDM = dshd.fillAll();
        try {
            for (DanhSachHoaDonDM dSHDDM : listDM) {
                listVM.add(new DanhSachHoaDonVM(dSHDDM.getMaHD(), dSHDDM.getMaNV(), dSHDDM.getTenNV(), dSHDDM.getTenKH(), dSHDDM.getNgayTao(), dSHDDM.getTrangThai(), dSHDDM.getSdtKH(), dSHDDM.getDiaChi(), dSHDDM.getThanhTien(), dSHDDM.getLyDoHuy()));
            }
            return  listVM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
}
