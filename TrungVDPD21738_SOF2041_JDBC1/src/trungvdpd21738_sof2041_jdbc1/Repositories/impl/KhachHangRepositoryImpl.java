/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.KhachHang;
import trungvdpd21738_sof2041_jdbc1.Repositories.KhachHangRepository;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TrungVD
 */
public class KhachHangRepositoryImpl implements KhachHangRepository {

    private DBConnection connection;

    @Override
    public ArrayList<KhachHang> getListKHDB() {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        String query = "SELECT *, CAST(SUBSTRING(Ma,3,5) as int)'STT' FROM KhachHang\n"
                + "ORDER BY STT";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setiD(rs.getString(1));
                khachHang.setMa(rs.getString(2));
                khachHang.setTen(rs.getString(3));
                khachHang.setTenDem(rs.getString(4));
                khachHang.setHo(rs.getString(5));
                khachHang.setNgaySinh(rs.getString(6));
                khachHang.setsDT(rs.getString(7));
                khachHang.setDiaChi(rs.getString(8));
                khachHang.setThanhPho(rs.getString(9));
                khachHang.setQuocGia(rs.getString(10));
                khachHang.setMatKhau(rs.getString(11));
                listKH.add(khachHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }

    @Override
    public boolean themKH(KhachHang khachHang) {
        String query = "INSERT INTO KhachHang(Ma,Ten,TenDem,Ho,NgaySinh,Sdt,DiaChi,ThanhPho,QuocGia,MatKhau)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        int checkThem = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, khachHang.getMa());
            ps.setString(2, khachHang.getTen());
            ps.setString(3, khachHang.getTenDem());
            ps.setString(4, khachHang.getHo());
            ps.setString(5,   khachHang.getNgaySinh());
            ps.setString(6, khachHang.getsDT());
            ps.setString(7, khachHang.getDiaChi());
            ps.setString(8, khachHang.getThanhPho());
            ps.setString(9, khachHang.getQuocGia());
            ps.setString(10, khachHang.getMatKhau());
            checkThem = ps.executeUpdate();
            return checkThem > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean suaKH(KhachHang khachHang, String iD) {
        String query = "UPDATE KhachHang SET\n"
                + "Ma = 'KH22',\n"
                + "Ten = ?,\n"
                + "TenDem = ?,\n"
                + "Ho = ?,\n"
                + "NgaySinh = ?,\n"
                + "Sdt = ?,\n"
                + "DiaChi = ?,\n"
                + "ThanhPho = ?,\n"
                + "QuocGia = ?,\n"
                + "MatKhau = ?\n"
                + "WHERE Id = ?";
        int checkSua = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, khachHang.getMa());
            ps.setString(2, khachHang.getTen());
            ps.setString(3, khachHang.getTenDem());
            ps.setString(4, khachHang.getHo());
            ps.setString(5, khachHang.getNgaySinh());
            ps.setString(6, khachHang.getsDT());
            ps.setString(7, khachHang.getDiaChi());
            ps.setString(8, khachHang.getThanhPho());
            ps.setString(9, khachHang.getQuocGia());
            ps.setString(10, khachHang.getMatKhau());
            ps.setString(11, iD);
            checkSua = ps.executeUpdate();
            return checkSua > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean xoaKH(String maKH) {
        String query = "DELETE KhachHang WHERE Ma = ?";
        int checkXoa = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maKH);
            checkXoa = ps.executeUpdate();
            return checkXoa > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<KhachHang> timKH(String tenKH) {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        String query = "SELECT *, CAST(SUBSTRING(Ma,3,5) as int)'STT' FROM KhachHang\n"
                + "WHERE Ten like N'%" + tenKH + "%'\n"
                + "ORDER BY STT";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHangTim = new KhachHang();
                khachHangTim.setiD(rs.getString(1));
                khachHangTim.setMa(rs.getString(2));
                khachHangTim.setTen(rs.getString(3));
                khachHangTim.setTenDem(rs.getString(4));
                khachHangTim.setHo(rs.getString(5));
                khachHangTim.setNgaySinh(rs.getString(6));
                khachHangTim.setsDT(rs.getString(7));
                khachHangTim.setDiaChi(rs.getString(8));
                khachHangTim.setThanhPho(rs.getString(9));
                khachHangTim.setQuocGia(rs.getString(10));
                khachHangTim.setMatKhau(rs.getString(11));
                listKH.add(khachHangTim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }
}
