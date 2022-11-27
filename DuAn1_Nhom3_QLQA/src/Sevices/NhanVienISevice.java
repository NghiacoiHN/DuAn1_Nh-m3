/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sevices;

import DomainModels.CuaHangDM;
import Reponsitories.impl.NhanVienRepon;
import java.util.ArrayList;
import DomainModels.NhanVienDM;
/**
 *
 * @author hiep nguyen
 */
public interface NhanVienISevice {
    ArrayList<NhanVienDM> getAllNhanVien();
    ArrayList<CuaHangDM> getAllCuaHang();
    public CuaHangDM getIdCh(String ma);
     //crud
     boolean addNhanVien(NhanVienDM nv);
     boolean suaNhanVien(NhanVienDM nv);
     boolean xoaNhanVien(String ma);
     
}
