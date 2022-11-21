/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.HoaDon;
import trungvdpd21738_sof2041_jdbc1.Repositories.HoaDonRepository;
import trungvdpd21738_sof2041_jdbc1.ViewModels.HoaDonBHModelView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;

/**
 *
 * @author TrungVD
 */
public class HoaDonRepositoryImpl implements HoaDonRepository {

    private DBConnection connection;

    @Override
    public ArrayList<HoaDonBHModelView> getListHDBH() {
        ArrayList<HoaDonBHModelView> listHDBH = new ArrayList<>();
        String query = "SELECT * FROm view_XemHoaDonBH";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonBHModelView hoaDon = new HoaDonBHModelView();
                hoaDon.setMaHD(rs.getString(1));
                hoaDon.setNgayTao(rs.getDate(2));
                hoaDon.setTenNV(rs.getString(3));
                hoaDon.setTinhTrang(rs.getInt(4));
                listHDBH.add(hoaDon);
            }
        } catch (Exception e) {
        }
        return listHDBH;
    }

    @Override
    public ArrayList<HoaDon> getListHDDB() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean themHoaDon(HoaDon hoaDon) {
        String query = "INSERT INTO HoaDon(Ma,NgayTao,IdNV,TinhTrang)\n"
                + "VALUES (?,?,'ec25e9e8-73ff-4fc0-97c0-dfedf36bb9cf',?)";
        int checkThemHD = 0;
        try(Connection con = connection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, hoaDon.getMaHD());
            ps.setObject(2, hoaDon.getNgayTao());
            ps.setInt(3, hoaDon.getTinhTrang());
            checkThemHD = ps.executeUpdate();
            return checkThemHD > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaHoaDon(HoaDon hoaDon, String maHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean xoaHoaDon(String maHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HoaDon> timKiemHD(String maHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
