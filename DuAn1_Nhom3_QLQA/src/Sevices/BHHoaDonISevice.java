/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sevices;

import Reponsitories.*;
import DomainModels.BHHoaDonDM;
import ViewModels.BhHoaDonVM;
import java.util.List;

/**
 *
 * @author TRONG NGHIA
 */
public interface BHHoaDonISevice {

    List<BhHoaDonVM> findAll();

    List<BhHoaDonVM> findByTT(Integer trangThai);

    boolean add(BHHoaDonDM a);

    boolean delete(String ma);

    boolean update(BHHoaDonDM a, String ma);
}
