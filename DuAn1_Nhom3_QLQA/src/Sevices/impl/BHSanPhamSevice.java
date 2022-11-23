/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices.impl;

import DomainModels.BHSanPhamDM;
import Reponsitories.impl.BHSanPhamRepon;
import ViewModels.BHSanPhamVM;
import java.util.List;
import Reponsitories.BHSanPhamIRepon;
import Sevices.BHSanPhamISevice;

/**
 *
 * @author TRONG NGHIA
 */
public class BHSanPhamSevice implements BHSanPhamISevice {

    BHSanPhamIRepon spRepon = new BHSanPhamRepon();

    @Override
    public List<BHSanPhamVM> findAll() {
        try {
            return spRepon.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BHSanPhamVM getOne(Integer trangThai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(BHSanPhamDM a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateSL(BHSanPhamDM a, String ma) {
        try {
            return spRepon.updateSL(a, ma);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    }


}
