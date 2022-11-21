/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhanVien;
import trungvdpd21738_sof2041_jdbc1.Repositories.NhanVienRepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.UUID;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChucVu;
import trungvdpd21738_sof2041_jdbc1.DomainModels.CuaHang;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;
import trungvdpd21738_sof2041_jdbc1.ViewModels.NhanVienViewModel;

/**
 *
 * @author TrungVD
 */
public class NhanVienRepositoryImpl implements NhanVienRepository {

    private DBConnection connection;

    private UUID UUID;

    @Override
    public ArrayList<NhanVien> getListNSXDB() {
        ArrayList<NhanVien> listNVDB = new ArrayList<>();
        String query = "SELECT * FROM view_xemThongTinNhanVien";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setiD(rs.getString(1));
                nhanVien.setMa(rs.getString(2));
                nhanVien.setTen(rs.getString(3));
                nhanVien.setTenDem(rs.getString(4));
                nhanVien.setHo(rs.getString(5));
                nhanVien.setGioiTinh(rs.getString(6));
                nhanVien.setNgaySinh(rs.getString(7));
                nhanVien.setDiaChi(rs.getString(8));
                nhanVien.setsDT(rs.getString(9));
                nhanVien.setMatKhau(rs.getString(10));
                CuaHang cuaHang = new CuaHang(rs.getString(11));
                nhanVien.setCuaHang(cuaHang);
                ChucVu chucVu = new ChucVu(rs.getString(12));
                nhanVien.setChucVu(chucVu);
                nhanVien.setiDGuiBC(rs.getString(13));
                nhanVien.setTrangThai(rs.getInt(14));
                listNVDB.add(nhanVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNVDB;
    }

    @Override
    public Boolean themNhanVien(NhanVien nhanVien) {
        String query = "INSERT INTO NhanVien(Ma,Ten,TenDem,Ho,GioiTinh,NgaySinh,"
                + "DiaChi,Sdt,MatKhau,IdCH,IdCV,TrangThai)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        int checkThem = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nhanVien.getMa());
            ps.setString(2, nhanVien.getTen());
            ps.setString(3, nhanVien.getTenDem());
            ps.setString(4, nhanVien.getHo());
            ps.setString(5, nhanVien.getGioiTinh());
            ps.setString(6, nhanVien.getNgaySinh());
            ps.setString(7, nhanVien.getDiaChi());
            ps.setString(8, nhanVien.getsDT());
            ps.setString(9, nhanVien.getMatKhau());
            ps.setString(10, nhanVien.getCuaHang().getiD());
            ps.setString(11, nhanVien.getChucVu().getiD());
//            ps.setObject(12, UUID.fromString(nhanVien.getiD()));
            ps.setInt(12, nhanVien.getTrangThai());
            checkThem = ps.executeUpdate();
            return checkThem > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaNhanVien(NhanVien nhanVien, String maNVSua) {
        String query = "UPDATE NhanVien SET Ma = ?, Ten = ?, TenDem = ?, Ho = ?, GioiTinh = ?,NgaySinh = ?,\n"
                + "DiaChi = ?, Sdt = ?, MatKhau = ?, IdCH = ?,\n"
                + "IdCV = ?, TrangThai = ?\n"
                + "WHERE Ma = ?";
        int checkSua = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nhanVien.getMa());
            ps.setString(2, nhanVien.getTen());
            ps.setString(3, nhanVien.getTenDem());
            ps.setString(4, nhanVien.getHo());
            ps.setString(5, nhanVien.getGioiTinh());
            ps.setString(6, nhanVien.getNgaySinh());
            ps.setString(7, nhanVien.getDiaChi());
            ps.setString(8, nhanVien.getsDT());
            ps.setString(9, nhanVien.getMatKhau());
            ps.setString(10, nhanVien.getCuaHang().getiD());
            ps.setString(11, nhanVien.getChucVu().getiD());
//            ps.setObject(12, UUID.fromString(nhanVien.getiD()));
            ps.setInt(12, nhanVien.getTrangThai());
            ps.setString(13, maNVSua);
            checkSua = ps.executeUpdate();
            return checkSua > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean xoaNhanVien(String maNVXoa) {
        String query = "DELETE NhanVien WHERE Ma = ?";
        int checkXoaNV = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maNVXoa);
            checkXoaNV = ps.executeUpdate();
            return checkXoaNV > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<NhanVien> timKiemNV(String tenNV) {
        ArrayList<NhanVien> listNVTim = new ArrayList<>();
        String query = "SELECT * FROM view_xemThongTinNhanVien\n"
                + "WHERE Ten like N'%" + tenNV + "%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setiD(rs.getString(1));
                nhanVien.setMa(rs.getString(2));
                nhanVien.setTen(rs.getString(3));
                nhanVien.setTenDem(rs.getString(4));
                nhanVien.setHo(rs.getString(5));
                nhanVien.setGioiTinh(rs.getString(6));
                nhanVien.setNgaySinh(rs.getString(7));
                nhanVien.setDiaChi(rs.getString(8));
                nhanVien.setsDT(rs.getString(9));
                nhanVien.setMatKhau(rs.getString(10));
                CuaHang cuaHang = new CuaHang(rs.getString(11));
                nhanVien.setCuaHang(cuaHang);
                ChucVu chucVu = new ChucVu(rs.getString(12));
                nhanVien.setChucVu(chucVu);
                nhanVien.setiDGuiBC(rs.getString(13));
                nhanVien.setTrangThai(rs.getInt(14));
                listNVTim.add(nhanVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNVTim;
    }

    @Override
    public ArrayList<NhanVienViewModel> listNVViewModel() {
        ArrayList<NhanVienViewModel> listNVViewModel = new ArrayList<>();
        String query = "SELECT Ma,Ho + ' ' + TenDem + ' '+ Ten'ho_ten',GioiTinh,NgaySinh,Sdt,DiaChi,CAST(SUBSTRING(Ma,3,5) as int)'STT'\n"
                + "FROM NhanVien\n"
                + "ORDER BY STT";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienViewModel nhanVien = new NhanVienViewModel();
                nhanVien.setMa(rs.getString(1));
                nhanVien.setHoVaTen(rs.getString(2));
                nhanVien.setGioiTinh(rs.getString(3));
                nhanVien.setNgaySinh(rs.getDate(4));
                nhanVien.setsDT(rs.getString(5));
                nhanVien.setDiaChi(rs.getString(6));
                listNVViewModel.add(nhanVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNVViewModel;
    }

    @Override
    public Boolean themNhanVien2(NhanVien nhanVien) {
        String query = "INSERT INTO NhanVien(Ma,Ten,TenDem,Ho,GioiTinh,NgaySinh,"
                + "DiaChi,Sdt)\n"
                + "VALUES(?,?,?,?,?,?,?,?)";
        int checkThem = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nhanVien.getMa());
            ps.setString(2, nhanVien.getTen());
            ps.setString(3, nhanVien.getTenDem());
            ps.setString(4, nhanVien.getHo());
            ps.setString(5, nhanVien.getGioiTinh());
            ps.setString(6, nhanVien.getNgaySinh());
            ps.setString(7, nhanVien.getDiaChi());
            ps.setString(8, nhanVien.getsDT());
            checkThem = ps.executeUpdate();
            return checkThem > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaNhanVien2(NhanVien nhanVien, String maNVSua) {
        String query = "UPDATE NhanVien SET Ma = ?, Ten = ?, TenDem = ?, Ho = ?, GioiTinh = ?,NgaySinh = ?,\n"
                + "DiaChi = ?, Sdt = ?, MatKhau = ?, IdCH = ?,\n"
                + "IdCV = ?, TrangThai = ?\n"
                + "WHERE Ma = ?";
        int checkSua = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nhanVien.getMa());
            ps.setString(2, nhanVien.getTen());
            ps.setString(3, nhanVien.getTenDem());
            ps.setString(4, nhanVien.getHo());
            ps.setString(5, nhanVien.getGioiTinh());
            ps.setString(6, nhanVien.getNgaySinh());
            ps.setString(7, nhanVien.getDiaChi());
            ps.setString(8, nhanVien.getsDT());
            ps.setString(9, nhanVien.getMatKhau());
            ps.setString(10, nhanVien.getCuaHang().getiD());
            ps.setString(11, nhanVien.getChucVu().getiD());
//            ps.setObject(12, UUID.fromString(nhanVien.getiD()));
            ps.setInt(12, nhanVien.getTrangThai());
            ps.setString(13, maNVSua);
            checkSua = ps.executeUpdate();
            return checkSua > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
