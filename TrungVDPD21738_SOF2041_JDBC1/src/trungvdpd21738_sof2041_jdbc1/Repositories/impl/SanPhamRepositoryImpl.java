/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.SanPham;
import trungvdpd21738_sof2041_jdbc1.Repositories.SanPhamRepository;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TrungVD
 */
public class SanPhamRepositoryImpl implements SanPhamRepository {

    private DBConnection connection;

    @Override
    public ArrayList<SanPham> getListSanPhamDB() {
        ArrayList<SanPham> listSP = new ArrayList<>();
        String query = "SELECT * FROM view_viewXemThongTinSP";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setiD(rs.getString(1));
                sanPham.setMaSP(rs.getString(2));
                sanPham.setTenSP(rs.getString(3));
                listSP.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    @Override
    public Boolean themSanPham(SanPham sanPham) {
        int checkInsert = 0;
        String query = "INSERT INTO SanPham(Ma,Ten)\n"
                + "VALUES (?,?)";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, sanPham.getMaSP());
            ps.setString(2, sanPham.getTenSP());
            checkInsert = ps.executeUpdate();
            return checkInsert > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Boolean suaSanPham(SanPham sanPham, String maSPSua) {
        int checkUpdate = 0;
        String query = "UPDATE SanPham\n"
                + "SET Ma = ?, Ten = ?\n"
                + "WHERE Ma = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, sanPham.getMaSP());
            ps.setString(2, sanPham.getTenSP());
            ps.setString(3, maSPSua);
            checkUpdate = ps.executeUpdate();
            return checkUpdate > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<SanPham> timKiemSanPham(String tenSPTim) {
        ArrayList<SanPham> listTimSP = new ArrayList<>();
        String query = "SELECT * FROM SanPham WHERE Ten like '%" + tenSPTim + "%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setiD(rs.getString(1));
                sanPham.setMaSP(rs.getString(2));
                sanPham.setTenSP(rs.getString(3));
                listTimSP.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTimSP;
    }

    

    @Override
    public Boolean xoaSanPham(String maSPXoa) {
        String query = "DELETE SanPham WHERE Ma = ?";
        int checkXoa = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maSPXoa);
            checkXoa = ps.executeUpdate();
            return checkXoa > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean kiemTraTenSP(String tenSanPham) {
        String query = "SELECT * FROM SanPham WHERE Ten = ?";
        int checkTen = 0;
        try ( Connection con = connection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, tenSanPham);
            checkTen = ps.executeUpdate();
            return checkTen > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
