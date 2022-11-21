/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.MauSac;
import trungvdpd21738_sof2041_jdbc1.Repositories.MauSacRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;

/**
 *
 * @author TrungVD
 */
public class MauSacRepositoryImpl implements MauSacRepository {

    private DBConnection connection;

    @Override
    public ArrayList<MauSac> getListMSDB() {
        ArrayList<MauSac> listMSDB = new ArrayList<>();
        String query = "SELECT * FROM view_viewXemThongTinMauSac";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac mauSac = new MauSac();
                mauSac.setiD(rs.getString(1));
                mauSac.setMaMauSac(rs.getString(2));
                mauSac.setTenMauSac(rs.getString(3));
                listMSDB.add(mauSac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMSDB;
    }

    @Override
    public Boolean themMauSac(MauSac mauSac) {
        String query = "INSERT INTO MauSac(Ma,Ten)\n"
                + "VALUES (?,?)";
        int checkThem = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, mauSac.getMaMauSac());
            ps.setString(2, mauSac.getTenMauSac());
            checkThem = ps.executeUpdate();
            return checkThem > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaMauSac(MauSac mauSac, String maMSSua) {
        int checkUpdate = 0;
        String query = "UPDATE MauSac\n"
                + "SET Ma = ?, Ten = ?\n"
                + "WHERE Ma = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, mauSac.getMaMauSac());
            ps.setString(2, mauSac.getTenMauSac());
            ps.setString(3, maMSSua);
            checkUpdate = ps.executeUpdate();
            return checkUpdate > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean xoaMauSac(String maMSXoa) {
        String query = "DELETE MauSac WHERE Ma = ?";
        int checkXoa = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maMSXoa);
            checkXoa = ps.executeUpdate();
            return checkXoa > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<MauSac> timKiemMauSac(String tenMauSac) {
        ArrayList<MauSac> listMSTim = new ArrayList<>();
        String query = "SELECT * FROM MauSac WHERE Ten like '%" + tenMauSac + "%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac mauSac = new MauSac();
                mauSac.setiD(rs.getString(1));
                mauSac.setMaMauSac(rs.getString(2));
                mauSac.setTenMauSac(rs.getString(3));
                listMSTim.add(mauSac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMSTim;
    }

}
