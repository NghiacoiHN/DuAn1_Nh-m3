/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitories;

import DomainModels.BHHoaDonDM;
import ViewModels.BHHoaDonVM;
import java.util.List;

/**
 *
 * @author TRONG NGHIA
 */
public interface BHHoaDonIRepon {

    List<BHHoaDonVM> findAll();

    List<BHHoaDonVM> findByTT(Integer trangThai);

    boolean add(BHHoaDonDM a);

    boolean delete(String ma);

    boolean update(BHHoaDonDM a, String ma);

    List<BHHoaDonDM> getIDHD(String ma);

    boolean addGiaoHang(BHHoaDonDM a);
    
    boolean updateGiaoHang(BHHoaDonDM a, String ma);

}
