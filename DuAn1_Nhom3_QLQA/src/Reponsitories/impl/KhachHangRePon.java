/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitories.impl;

import DomainModels.KhacHangDM;
import Reponsitories.KhachHangIRepon;
import java.util.List;
import java.util.List;
import java.sql.ResultSet;
import Utilities.DBConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author HP
 */
public class KhachHangRePon implements KhachHangIRepon {

    final String INSERT_SQL = "INSERT INTO KhachHang(MaKH,HoKH,TenKH,SDT,DiaChi,TrangThai) values (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhachHang SET HoKH= ? , TenKH=?, SDT =?, DiaChi=? , TrangThai= ? WHERE  MaKH= ?";
    final String DELETE_SQL = "DELETE FROM KhachHang WHERE MaKH = ?";
    final String SELECT_BY_SQL = "SELECT * FROM KhachHang WHERE MaKH = ?";
    final String SELECT_ALL_SQL = "SELECT MaKH, HoKH,TenKH, SDT, DiaChi, TrangThai FROM KhachHang";

    @Override
    public List<KhacHangDM> getAll() {
        List<KhacHangDM> listKH = new ArrayList<>();
        try {

            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(SELECT_ALL_SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhacHangDM kh = new KhacHangDM();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setHoKH(rs.getString("HoKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSDT(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                
                listKH.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }

    @Override
    public List<KhacHangDM> getKhachHangByID(String Ma) {
        List<KhacHangDM> listKH = new ArrayList<>();
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(SELECT_BY_SQL);
            ps.setString(1, Ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhacHangDM kh = new KhacHangDM();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setHoKH(rs.getString("HoKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSDT(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                listKH.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }

    @Override
    public boolean createNewKhachHang(KhacHangDM KhachHang) {

        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(INSERT_SQL);
            ps.setString(1, KhachHang.getMaKH());
            ps.setString(2, KhachHang.getHoKH());
            ps.setString(3, KhachHang.getTenKH());
            ps.setString(4, KhachHang.getSDT());
            ps.setString(5, KhachHang.getDiaChi());
            ps.setString(6, String.valueOf(KhachHang.getTrangThai()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean UpdateKhachHangById(KhacHangDM KhachHang) {
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(UPDATE_SQL);
            ps.setString(6, KhachHang.getMaKH());
            ps.setString(1, KhachHang.getHoKH());
            ps.setString(2, KhachHang.getTenKH());
            ps.setString(3, KhachHang.getSDT());
            ps.setString(4, KhachHang.getDiaChi());
            ps.setString(5, String.valueOf(KhachHang.getTrangThai()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean DeleteKhachHangById(String Ma) {
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(DELETE_SQL);
            ps.setString(1, Ma);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
