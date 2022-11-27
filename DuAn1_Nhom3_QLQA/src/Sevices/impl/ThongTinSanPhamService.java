/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices.impl;

import DomainModels.ThongTinSanPhamDM;
import Reponsitories.ThongTinSanPhamIRepon;
import Reponsitories.impl.ThongTinSanPhamRepon;
import Sevices.ThongTinSanPhamIService;
import ViewModels.ThongTinSanPhamVM;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author longd
 */
public class ThongTinSanPhamService implements ThongTinSanPhamIService{

    ThongTinSanPhamIRepon ttsp = new ThongTinSanPhamRepon();
    
    @Override
    public List<ThongTinSanPhamVM> fillByMaHD(String mahd) {
        List<ThongTinSanPhamVM> listTTSPVM = new ArrayList<>();
        List<ThongTinSanPhamDM> listTTSPDM = ttsp.fillByMaHD(mahd);
        
        try {
            for (ThongTinSanPhamDM ttspDM : listTTSPDM) {
                listTTSPVM.add(new ThongTinSanPhamVM(ttspDM.getMaSP(), ttspDM.getTenSP(), ttspDM.getSoLuong(), ttspDM.getDonGia(), ttspDM.getSoTienGiamGia(), ttspDM.getThanhTien(), ttspDM.getTrangThai(), ttspDM.getLyDoHuy()));
            }
            return listTTSPVM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
