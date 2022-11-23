/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitories.impl;

import DomainModels.BHHoaDonDM;
import DomainModels.BHSanPhamDM;
import Utilities.DBConnection;
import ViewModels.BHHoaDonVM;
import ViewModels.BHSanPhamVM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Reponsitories.BHSanPhamIRepon;

/**
 *
 * @author TRONG NGHIA
 */
public class BHSanPhamRepon implements BHSanPhamIRepon {

    @Override
    public List<BHSanPhamVM> findAll() {
        List<BHSanPhamVM> products = new ArrayList<>();
        String sql = "SELECT ChiTietSanPham.IDCTSP, ChiTietSanPham.MaCTSP, SanPham.TenSp, LoaiSP.TenLSP, ChatLieu.TenCL, MauSac.TenMS, Size.TenSize, XuatXu.TenNuoc, ChiTietSanPham.SoLuongTon, ChiTietSanPham.GiaBan\n"
                + "FROM     ChiTietSanPham INNER JOIN\n"
                + "                  SanPham ON ChiTietSanPham.IDSP = SanPham.IDSP INNER JOIN\n"
                + "                  LoaiSP ON ChiTietSanPham.IDLSP = LoaiSP.IDLSP INNER JOIN\n"
                + "                  ChatLieu ON ChiTietSanPham.IDCL = ChatLieu.IDCL INNER JOIN\n"
                + "                  MauSac ON ChiTietSanPham.IDMS = MauSac.IDMS INNER JOIN\n"
                + "                  Size ON ChiTietSanPham.IDSize = Size.IDSize INNER JOIN\n"
                + "                  XuatXu ON ChiTietSanPham.IDXX = XuatXu.IDXX";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BHSanPhamVM sanPham1 = new BHSanPhamVM();
                sanPham1.setIDCTSP(rs.getString("IDCTSP"));
                sanPham1.setMaCTSP(rs.getString("MaCTSP"));
                sanPham1.setTenSP(rs.getString("TenSp"));
                sanPham1.setTenLoaiSP(rs.getString("TenLSP"));
                sanPham1.setTenCL(rs.getString("TenCL"));
                sanPham1.setTenMS(rs.getString("TenMS"));
                sanPham1.setTenSize(rs.getString("TenSize"));
                sanPham1.setTenXuatXu(rs.getString("TenNuoc"));
                sanPham1.setGiaBan(rs.getBigDecimal("GiaBan"));
                sanPham1.setSoLuongTon(rs.getInt("SoLuongTon"));
//                sanPham1.setTrangThai(rs.getInt("TrangThai"));
                products.add(sanPham1);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
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
        String sql = "UPDATE ChiTietSanPham\n"
                + "SET          SoLuongTon = ?\n"
                + "WHERE MaCTSP = ?";
        int check = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, a.getSoLuongTon());
            ps.setObject(2, ma);
            check = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

}
