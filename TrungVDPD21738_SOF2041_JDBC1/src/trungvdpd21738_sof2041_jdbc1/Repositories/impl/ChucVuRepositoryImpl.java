/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChucVu;
import trungvdpd21738_sof2041_jdbc1.Repositories.ChucVuRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;

/**
 *
 * @author TrungVD
 */
public class ChucVuRepositoryImpl implements ChucVuRepository {

    private DBConnection connection;

    @Override
    public ArrayList<ChucVu> getListChucVuDB() {
        ArrayList<ChucVu> listChucVuDB = new ArrayList<>();
        String query = "SELECT * FROM view_xemThongTinChucVu";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu chucVu = new ChucVu();
                chucVu.setiD(rs.getString(1));
                chucVu.setMaCV(rs.getString(2));
                chucVu.setTenCV(rs.getString(3));
                listChucVuDB.add(chucVu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listChucVuDB;
    }

    @Override
    public Boolean themChucVu(ChucVu chucVu) {
        String query = "INSERT INTO ChucVu(Ma, Ten)"
                + "VALUES (?,?)";
        int checkThemCV = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, chucVu.getMaCV());
            ps.setString(2, chucVu.getTenCV());
            checkThemCV = ps.executeUpdate();
            return checkThemCV > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaChucVu(ChucVu chucVu, String maCVSua) {
        String query = "UPDATE ChucVu \n"
                + "SET Ma = ?, Ten = ?\n"
                + "WHERE Ma = ?";
        int checkSuaCV = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, chucVu.getMaCV());
            ps.setString(2, chucVu.getTenCV());
            ps.setString(3, maCVSua);
            checkSuaCV = ps.executeUpdate();
            return checkSuaCV > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean xoaChucVu(String maCVXoa) {
        String query = "DELETE ChucVu WHERE Ma = ?";
        int checkThemCV = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maCVXoa);
            checkThemCV = ps.executeUpdate();
            return checkThemCV > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<ChucVu> timChucVu(String tenCVTim) {
        ArrayList<ChucVu> listCVTim = new ArrayList<>();
        String query = "SELECT * FROM view_xemThongTinChucVu\n"
                +      "WHERE Ten LIKE N'%"+tenCVTim+"%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu chucVu = new ChucVu();
                chucVu.setiD(rs.getString(1));
                chucVu.setMaCV(rs.getString(2));
                chucVu.setTenCV(rs.getString(3));
                listCVTim.add(chucVu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCVTim;
    }

}
