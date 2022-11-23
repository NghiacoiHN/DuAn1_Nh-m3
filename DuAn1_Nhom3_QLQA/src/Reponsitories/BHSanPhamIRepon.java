/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitories;

import DomainModels.BHSanPhamDM;
import ViewModels.BHSanPhamVM;
import java.util.List;

/**
 *
 * @author TRONG NGHIA
 */
public interface BHSanPhamIRepon {

    List<BHSanPhamVM> findAll();

    BHSanPhamVM getOne(Integer trangThai);

    boolean add(BHSanPhamDM a);

    boolean delete(String ma);

    boolean updateSL(BHSanPhamDM a, String ma);
}
