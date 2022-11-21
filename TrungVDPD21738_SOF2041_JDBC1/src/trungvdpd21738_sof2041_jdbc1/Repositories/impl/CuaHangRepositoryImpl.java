/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.CuaHang;
import trungvdpd21738_sof2041_jdbc1.Repositories.CuaHangRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;

/**
 *
 * @author TrungVD
 */
public class CuaHangRepositoryImpl implements CuaHangRepository {

    private DBConnection connection;

    @Override
    public ArrayList<CuaHang> getListCuaHangDB() {
        ArrayList<CuaHang> listCuaHang = new ArrayList<>();
        String query = "SELECT * FROM view_xemThongTinCuaHang";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CuaHang cuaHang = new CuaHang();
                cuaHang.setiD(rs.getString(1));
                cuaHang.setMa(rs.getString(2));
                cuaHang.setTenCH(rs.getString(3));
                cuaHang.setDiaChi(rs.getString(4));
                cuaHang.setThanhPho(rs.getString(5));
                cuaHang.setQuocGia(rs.getString(6));
                listCuaHang.add(cuaHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCuaHang;
    }

    @Override
    public Boolean themCuaHang(CuaHang cuaHang) {
        String query = "INSERT INTO CuaHang(Ma,Ten,DiaChi,ThanhPho,QuocGia)\n"
                + "VALUES (?,?,?,?,?)";
        int checkThemCH = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, cuaHang.getMa());
            ps.setString(2, cuaHang.getTenCH());
            ps.setString(3, cuaHang.getDiaChi());
            ps.setString(4, cuaHang.getThanhPho());
            ps.setString(5, cuaHang.getQuocGia());
            checkThemCH = ps.executeUpdate();
            return checkThemCH > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaCuaHang(CuaHang cuaHang, String maCHSua) {
        String query = "UPDATE CuaHang \n"
                + "SET Ma = ?, Ten = ?, DiaChi = ?, ThanhPho = ?, QuocGia = ?\n"
                + "WHERE Ma = ?";
        int checkSuaCH = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, cuaHang.getMa());
            ps.setString(2, cuaHang.getTenCH());
            ps.setString(3, cuaHang.getDiaChi());
            ps.setString(4, cuaHang.getThanhPho());
            ps.setString(5, cuaHang.getQuocGia());
            ps.setString(6, maCHSua);
            checkSuaCH = ps.executeUpdate();
            return checkSuaCH > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean xoaCuaHang(String maCHXoa) {
        String query = "DELETE CuaHang WHERE Ma = ?";
        int checkThemCH = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maCHXoa);
            checkThemCH = ps.executeUpdate();
            return checkThemCH > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<CuaHang> timCuaHang(String diaChiCHTim) {
        ArrayList<CuaHang> listCuaHangTim = new ArrayList<>();
        String query = "SELECT * FROM view_xemThongTinCuaHang\n"
                + "WHERE ThanhPho LIKE N'%"+diaChiCHTim+"%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CuaHang cuaHang = new CuaHang();
                cuaHang.setMa(rs.getString(1));
                cuaHang.setTenCH(rs.getString(2));
                cuaHang.setDiaChi(rs.getString(3));
                cuaHang.setThanhPho(rs.getString(4));
                cuaHang.setQuocGia(rs.getString(5));
                listCuaHangTim.add(cuaHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCuaHangTim;
    }

}
