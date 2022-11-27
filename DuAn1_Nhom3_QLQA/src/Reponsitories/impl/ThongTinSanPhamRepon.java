/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitories.impl;

import DomainModels.ThongTinSanPhamDM;
import Reponsitories.ThongTinSanPhamIRepon;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author longd
 */
public class ThongTinSanPhamRepon implements ThongTinSanPhamIRepon{

    @Override
    public List<ThongTinSanPhamDM> fillByMaHD(String mahd) {
        String query = "SELECT SanPham.MaSP, SanPham.TenSp, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, HoaDon.SoTienGiamGia, HoaDon.ThanhTien, ChiTietSanPham.TrangThai, HoaDonChiTiet.LyDoHuy\n" +
"					 FROM HoaDon JOIN HoaDonChiTiet ON HoaDon.IDHD = HoaDonChiTiet.IDHD\n" +
"					 JOIN ChiTietSanPham ON HoaDonChiTiet.IDCTSP = ChiTietSanPham.IDCTSP\n" +
"					 JOIN SanPham ON ChiTietSanPham.IDSP = SanPham.IDSP\n" +
"					 WHERE HoaDon.MaHD = ?";
        List<ThongTinSanPhamDM> list = new ArrayList<>();
        
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, mahd);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                ThongTinSanPhamDM ttspDM = new ThongTinSanPhamDM();
                ttspDM.setMaSP(rs.getString("MaSP"));
                ttspDM.setTenSP(rs.getString("TenSp"));
                ttspDM.setSoLuong(rs.getInt("SoLuong"));
                ttspDM.setDonGia(rs.getBigDecimal("DonGia"));
                ttspDM.setSoTienGiamGia(rs.getBigDecimal("SoTienGiamGia"));
                ttspDM.setThanhTien(rs.getBigDecimal("ThanhTien"));
                ttspDM.setTrangThai(rs.getInt("TrangThai"));
                ttspDM.setLyDoHuy(rs.getString("LyDoHuy"));
                
                list.add(ttspDM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
}
