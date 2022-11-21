/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.HoaDon;
import trungvdpd21738_sof2041_jdbc1.Repositories.HoaDonRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.HoaDonRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.HoaDonService;
import trungvdpd21738_sof2041_jdbc1.ViewModels.HoaDonBHModelView;

/**
 *
 * @author TrungVD
 */
public class HoaDonServiceImpl implements HoaDonService{

    private HoaDonRepository hoaDonRepository = new HoaDonRepositoryImpl();
    
    @Override
    public ArrayList<HoaDonBHModelView> getListHDBH() {
        return hoaDonRepository.getListHDBH();
    }

    @Override
    public String themHD(HoaDon hoaDon) {
        boolean themHD = hoaDonRepository.themHoaDon(hoaDon);
        if(themHD){
            return "Tạo hóa đơn thành công!";
        }else{
            return "Tạo hóa đơn thất bại!";
        }
    }
    
}
