/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices.impl;

import DomainModels.CuaHangDM;
import java.util.ArrayList;
import DomainModels.NhanVienDM;
import Reponsitories.impl.NhanVienRepon;
import Sevices.NhanVienISevice;
/**
 *
 * @author hiep nguyen
 */
public class NhanVienSevice implements NhanVienISevice{
  private NhanVienRepon nhanvienRepos = new NhanVienRepon();
    
    @Override
    public ArrayList<NhanVienDM> getAllNhanVien(){
        try {
            return nhanvienRepos.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    //C_R_U_D
    @Override
    public boolean addNhanVien(NhanVienDM nv){
        try {
            return nhanvienRepos.addNV(nv);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean suaNhanVien(NhanVienDM nv){
        try {
            return nhanvienRepos.suaNV(nv);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean xoaNhanVien(String ma){
        try {
            return nhanvienRepos.xoaNV(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<CuaHangDM> getAllCuaHang() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CuaHangDM getIdCh(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
