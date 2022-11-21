/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhaSanXuat;
import trungvdpd21738_sof2041_jdbc1.Repositories.NhaSanXuatRepository;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TrungVD
 */
public class NhaSanXuatRepositoryImpl implements NhaSanXuatRepository {

    private DBConnection connection;

    @Override
    public ArrayList<NhaSanXuat> getListNSXDB() {
        ArrayList<NhaSanXuat> listNSXDB = new ArrayList<>();
        String query = "SELECT * From NSX\n"
                +      "ORDER BY MA ";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaSanXuat nhaSanXuat = new NhaSanXuat();
                nhaSanXuat.setiD(rs.getString(1));
                nhaSanXuat.setMaNSX(rs.getString(2));
                nhaSanXuat.setTenNSX(rs.getString(3));
                listNSXDB.add(nhaSanXuat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNSXDB;
    }

    @Override
    public Boolean themNSX(NhaSanXuat nhaSanXuat) {
        String query = "INSERT INTO NSX(Ma,Ten)\n"
                + "VALUES (?,?)";
        int checkThem = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nhaSanXuat.getMaNSX());
            ps.setString(2, nhaSanXuat.getTenNSX());
            checkThem = ps.executeUpdate();
            return checkThem > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaNSX(NhaSanXuat nhaSanXuat, String maNSXSua) {
        int checkUpdate = 0;
        String query = "UPDATE NSX\n"
                + "SET Ma = ?, Ten = ?\n"
                + "WHERE Ma = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nhaSanXuat.getMaNSX());
            ps.setString(2, nhaSanXuat.getTenNSX());
            ps.setString(3, maNSXSua);
            checkUpdate = ps.executeUpdate();
            return checkUpdate > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean xoaNSX(String maNSXXoa) {
        String query = "DELETE NSX WHERE Ma = ?";
        int checkXoa = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maNSXXoa);
            checkXoa = ps.executeUpdate();
            return checkXoa > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<NhaSanXuat> timKiemNSX(String tenNSX) {
        ArrayList<NhaSanXuat> listMSTim = new ArrayList<>();
        String query = "SELECT * FROM NSX WHERE Ten like '%" + tenNSX + "%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaSanXuat nhaSanXuat = new NhaSanXuat();
                nhaSanXuat.setiD(rs.getString(1));
                nhaSanXuat.setMaNSX(rs.getString(2));
                nhaSanXuat.setTenNSX(rs.getString(3));
                listMSTim.add(nhaSanXuat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMSTim;
    }

}
