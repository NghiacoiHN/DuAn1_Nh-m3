/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitories.impl;

import DomainModels.DanhSachHoaDonDM;
import Reponsitories.DanhSachHoaDonIRepon;
import Utilities.DBConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author longd
 */
public class DanhSachHoaDonRepon implements DanhSachHoaDonIRepon{

    @Override
    public List<DanhSachHoaDonDM> fillAll() {
        
        List<DanhSachHoaDonDM> list = new ArrayList<>();
        // DISTINCT
        String query = "SELECT DISTINCT HoaDon.MaHD, NhanVien.MaNV, NhanVien.TenNV, KhachHang.TenKH, HoaDon.NgayTao, HoaDon.TrangThai, KhachHang.SDT, KhachHang.DiaChi, HoaDon.ThanhTien, HoaDonChiTiet.LyDoHuy \n" +
"					 FROM HoaDon JOIN KhachHang ON HoaDon.IDKH = KhachHang.IDKH\n" +
"					 JOIN HoaDonChiTiet ON HoaDon.IDHD = HoaDonChiTiet.IDHD\n" +
"					 JOIN NhanVien ON HoaDon.IDNV = NhanVien.IDNV";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                DanhSachHoaDonDM danhSachHoaDonDM = new DanhSachHoaDonDM();
                danhSachHoaDonDM.setMaHD(rs.getString("MaHD"));
                danhSachHoaDonDM.setTenNV(rs.getString("TenNV"));
                danhSachHoaDonDM.setTenKH(rs.getString("TenKH"));
                danhSachHoaDonDM.setNgayTao(rs.getString("NgayTao"));
                danhSachHoaDonDM.setTrangThai(rs.getInt("TrangThai"));
                danhSachHoaDonDM.setSdtKH(rs.getString("SDT"));
                danhSachHoaDonDM.setDiaChi(rs.getString("DiaChi"));
                danhSachHoaDonDM.setThanhTien(rs.getBigDecimal("ThanhTien"));
                danhSachHoaDonDM.setLyDoHuy(rs.getString("LyDoHuy"));
                danhSachHoaDonDM.setMaNV(rs.getString("MaNV"));
                
                list.add(danhSachHoaDonDM);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    
    
}
