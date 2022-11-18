/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitories.impl;

import DomainModels.BHHoaDonDM;
import Utilities.DBConnection;
import ViewModels.BhHoaDonVM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Reponsitories.BHHoaDonIRepon;

/**
 *
 * @author TRONG NGHIA
 */
public class BHHoaDonRepon implements BHHoaDonIRepon {

    @Override
    public List<BhHoaDonVM> findAll() {
        List<BhHoaDonVM> products = new ArrayList<>();
        String sql = "SELECT MaHD, NgayTao, TenKH, TrangThai\n"
                + "FROM     HoaDon";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BhHoaDonVM hoaDon1 = new BhHoaDonVM();
                hoaDon1.setMaHD(rs.getString("MaHD"));
                hoaDon1.setNgayTao(rs.getString("NgayTao"));
                hoaDon1.setTenKH(rs.getString("TenKH"));
                hoaDon1.setTrangThai(rs.getInt("TrangThai"));
                products.add(hoaDon1);
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
    public List<BhHoaDonVM> findByTT(Integer trangThai) {
        List<BhHoaDonVM> products = new ArrayList<>();
        String sql = "SELECT MaHD, NgayTao, TenKH, TrangThai\n"
                + "FROM     HoaDon\n"
                + "WHERE TrangThai = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BhHoaDonVM hoaDon1 = new BhHoaDonVM();
                hoaDon1.setMaHD(rs.getString("MaHD"));
                hoaDon1.setNgayTao(rs.getString("NgayTao"));
                hoaDon1.setTenKH(rs.getString("TenKH"));
                hoaDon1.setTrangThai(rs.getInt("TrangThai"));
                products.add(hoaDon1);
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
    public boolean add(BHHoaDonDM a) {
        String sql = "INSERT INTO HoaDon\n"
                + "                  (NgayTao, TenKH, SDTKH, TrangThai)\n"
                + "VALUES (?,?,?,2)";
        int check = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, a.getNgayTao());
            ps.setObject(2, a.getTenKH());
            ps.setObject(3, a.getSDTKH());
            check = ps.executeUpdate();

            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    @Override
    public boolean delete(String ma) {
        String sql = "UPDATE HoaDon\n"
                + "SET          TrangThai = 10"
                + "WHERE MaHD= ?";
        int check = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, ma);
            check = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    @Override
    public boolean update(BHHoaDonDM a, String ma) {
        String sql = "UPDATE HoaDon\n"
                + "SET          NgayThanhToan =?, ThanhTien =?, TienDua =?, TienThua =?, HinhThucThanhToan =N'?', SoTienGiamGia =?, TrangThai = 1\n"
                + "WHERE MaHD= ?";
        int check = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, a.getNgayThanhToan());
            ps.setObject(2, a.getThanhTien());
            ps.setObject(3, a.getTienDua());
            ps.setObject(4, a.getTienThua());
            ps.setObject(5, a.getHinhThucThanhToan());
            ps.setObject(6, a.getSoTienGiam());
            ps.setObject(7, ma);
            check = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

}
