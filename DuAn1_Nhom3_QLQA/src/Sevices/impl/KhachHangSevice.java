/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices.impl;

import DomainModels.KhacHangDM;
import Reponsitories.KhachHangIRepon;
import Reponsitories.impl.KhachHangRePon;
import Sevices.KhachHangISevice;
import ViewModels.KhachHangVM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class KhachHangSevice implements KhachHangISevice {

    KhachHangIRepon khrp = new KhachHangRePon();

    @Override
    public List<KhachHangVM> getAll() {
        List<KhachHangVM> _listKH = new ArrayList<>();
        try {
            List<KhacHangDM> kh = khrp.getAll();
            for (KhacHangDM x : kh) {
                  _listKH.add(new KhachHangVM(x.getMaKH(), x.getHoKH(), x.getTenKH(), x.getSDT(), x.getDiaChi(), x.getTrangThai()));
            }
            return _listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<KhachHangVM> getKhachHangByID(String Ma) {
        List<KhachHangVM> _listKH = new ArrayList<>();
        List<KhacHangDM> kh = khrp.getKhachHangByID(Ma);
        for (KhacHangDM x : kh) {
            _listKH.add(new KhachHangVM(x.getMaKH(), x.getHoKH(), x.getTenKH(), x.getSDT(), x.getDiaChi(), x.getTrangThai()));
        }
        return _listKH;
    }

    @Override
    public boolean createNewKhachHang(KhachHangVM KhachHang) {
        KhacHangDM viewCv = new KhacHangDM(KhachHang.getMaKH(), KhachHang.getHoKH(), KhachHang.getTenKH(), KhachHang.getSDT(), KhachHang.getDiaChi(), KhachHang.getTrangThai());
        return khrp.createNewKhachHang(viewCv);
    }

    @Override
    public boolean UpdateKhachHangById(KhachHangVM KhachHang) {
        KhacHangDM viewCv = new KhacHangDM(KhachHang.getMaKH(), KhachHang.getHoKH(), KhachHang.getTenKH(), KhachHang.getSDT(), KhachHang.getDiaChi(), KhachHang.getTrangThai());
        return khrp.UpdateKhachHangById(viewCv);
    }

    @Override
    public boolean DeleteKhachHangById(String Ma) {
        return khrp.DeleteKhachHangById(Ma);
    }

}
