/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices.impl;

import DomainModels.BHHoaDonDM;
import Reponsitories.impl.BHHoaDonRepon;
import ViewModels.BHHoaDonVM;
import java.util.List;
import Reponsitories.BHHoaDonIRepon;
import Sevices.BHHoaDonISevice;

/**
 *
 * @author TRONG NGHIA
 */
public class BHHoaDonSevice implements BHHoaDonISevice {

    BHHoaDonIRepon hdRepon = new BHHoaDonRepon();

    @Override
    public List<BHHoaDonVM> findAll() {
        try {
            return hdRepon.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BHHoaDonVM> findByTT(Integer trangThai) {
        try {
            return hdRepon.findByTT(trangThai);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(BHHoaDonDM a) {
        try {
            return hdRepon.add(a);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String ma) {
        try {
            return hdRepon.delete(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(BHHoaDonDM a, String ma) {
        try {
            return hdRepon.update(a, ma);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BHHoaDonDM> getIDHD(String ma) {
        try {
            return hdRepon.getIDHD(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addGiaoHang(BHHoaDonDM a) {
        try {
            return hdRepon.addGiaoHang(a);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateGiaoHang(BHHoaDonDM a, String ma) {
        try {
            return hdRepon.updateGiaoHang(a, ma);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
