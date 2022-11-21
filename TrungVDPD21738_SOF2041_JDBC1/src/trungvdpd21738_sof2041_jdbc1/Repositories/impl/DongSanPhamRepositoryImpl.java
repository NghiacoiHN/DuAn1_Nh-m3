/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.lang.constant.Constable;
import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.DongSanPham;
import trungvdpd21738_sof2041_jdbc1.Repositories.DongSanPhamRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;
/**
 *
 * @author TrungVD
 */
public class DongSanPhamRepositoryImpl implements DongSanPhamRepository{
    
    private DBConnection connection;
    
    @Override
    public ArrayList<DongSanPham> getListDSPDB() {
        ArrayList<DongSanPham> listDSP = new ArrayList<>();
        String query = "SELECT * FROM view_xemThongTinDSP";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DongSanPham dongSanPham = new DongSanPham();
                dongSanPham.setiD(rs.getString(1));
                dongSanPham.setMaDSP(rs.getString(2));
                dongSanPham.setTenDSP(rs.getString(3));
                listDSP.add(dongSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDSP;
    }

    @Override
    public Boolean themDSP(DongSanPham dongSanPham) {
        String query = "INSERT INTO DongSP(Ma,Ten)\n"
                + "VALUES (?,?)";
        int checkThem = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, dongSanPham.getMaDSP());
            ps.setString(2, dongSanPham.getTenDSP());
            checkThem = ps.executeUpdate();
            return checkThem > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaDSP(DongSanPham dongSanPham, String maDSP) {
        int checkUpdate = 0;
        String query = "UPDATE DongSP\n"
                + "SET Ma = ?, Ten = ?\n"
                + "WHERE Ma = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, dongSanPham.getMaDSP());
            ps.setString(2, dongSanPham.getTenDSP());
            ps.setString(3, maDSP);
            checkUpdate = ps.executeUpdate();
            return checkUpdate > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean xoaDSP(String maDSPXoa) {
String query = "DELETE DongSP WHERE Ma = ?";
        int checkXoa = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maDSPXoa);
            checkXoa = ps.executeUpdate();
            return checkXoa > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<DongSanPham> timKiemDSP(String tenDSPTim) {
        ArrayList<DongSanPham> listDSPTim = new ArrayList<>();
        String query = "SELECT * FROM DongSP WHERE Ten like '%" + tenDSPTim + "%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DongSanPham dongSanPham = new DongSanPham();
                dongSanPham.setiD(rs.getString(1));
                dongSanPham.setMaDSP(rs.getString(2));
                dongSanPham.setTenDSP(rs.getString(3));
                listDSPTim.add(dongSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDSPTim;
    }

}
