/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories.impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChiTietSanPham;
import trungvdpd21738_sof2041_jdbc1.Repositories.ChiTietSanPhamRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import trungvdpd21738_sof2041_jdbc1.DomainModels.DongSanPham;
import trungvdpd21738_sof2041_jdbc1.DomainModels.MauSac;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhaSanXuat;
import trungvdpd21738_sof2041_jdbc1.DomainModels.SanPham;
import trungvdpd21738_sof2041_jdbc1.Utilties.DBConnection;
import trungvdpd21738_sof2041_jdbc1.ViewModels.SPBanHangViewModel;
import trungvdpd21738_sof2041_jdbc1.ViewModels.SanPhamTest;

/**
 *
 * @author TrungVD
 */
public class ChiTietSanPhamRepositoryImpl implements ChiTietSanPhamRepository {

    private DBConnection connection;

    @Override
    public ArrayList<ChiTietSanPham> getListCTSPDB() {
        ArrayList<ChiTietSanPham> listCTSPDB = new ArrayList<>();
        String query = "SELECT * FROM view_xemCTSP";
//          String query = "SELECT * FROM ChiTietSP";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

                SanPham sanPham = new SanPham();
                sanPham.setiD(rs.getString(2));
                sanPham.setMaSP(rs.getString(3));
                sanPham.setTenSP(rs.getString(4));
//                System.out.println(sanPham.getMaSP());
//                System.out.println(sanPham.getTenSP());
                NhaSanXuat nhaSanXuat = new NhaSanXuat(rs.getString(5), rs.getString(6), rs.getString(7));
                MauSac mauSac = new MauSac(rs.getString(8), rs.getString(9), rs.getString(10));
                DongSanPham dongSanPham = new DongSanPham(rs.getString(11), rs.getString(12), rs.getString(13));

                chiTietSanPham.setiD(rs.getString(1));
                chiTietSanPham.setSanPham(sanPham);
                chiTietSanPham.setNhaSanXuat(nhaSanXuat);
                chiTietSanPham.setMauSac(mauSac);
                chiTietSanPham.setDongSanPham(dongSanPham);
                chiTietSanPham.setNamBH(rs.getInt(14));
                chiTietSanPham.setMoTa(rs.getString(15));
                chiTietSanPham.setSoLuongTon(rs.getInt(16));
                chiTietSanPham.setGiaNhap(rs.getDouble(17));
                chiTietSanPham.setGiaBan(rs.getDouble(18));
                listCTSPDB.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTSPDB;
    }

    @Override
    public Boolean themCTSP(ChiTietSanPham chiTietSanPham) {
        String query = "INSERT INTO ChiTietSP(IdSP, IdNsx,IdMauSac,IdDongSP,NamBH,MoTa,SoLuongTon,GiaNhap,GiaBan)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        int checkThem = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, chiTietSanPham.getSanPham().getiD());
            ps.setString(2, chiTietSanPham.getNhaSanXuat().getiD());
            ps.setString(3, chiTietSanPham.getMauSac().getiD());
            ps.setString(4, chiTietSanPham.getDongSanPham().getiD());
            ps.setInt(5, chiTietSanPham.getNamBH());
            ps.setString(6, chiTietSanPham.getMoTa());
            ps.setInt(7, chiTietSanPham.getSoLuongTon());
            ps.setDouble(8, chiTietSanPham.getGiaNhap());
            ps.setDouble(9, chiTietSanPham.getGiaBan());
            checkThem = ps.executeUpdate();
            return checkThem > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean suaCTSP(ChiTietSanPham chiTietSanPham, String iDCTSP) {
        String query = "UPDATE ChiTietSP SET\n"
                + "IdSP = ?,\n"
                + "IdNsx = ?,\n"
                + "IdMauSac = ?,\n"
                + "IdDongSP = ?,\n"
                + "NamBH = ?,\n"
                + "MoTa = ?,\n"
                + "SoLuongTon = ?,\n"
                + "GiaNhap = ?,\n"
                + "GiaBan = ?\n"
                + "WHERE Id = ?";
        int checkSua = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, chiTietSanPham.getSanPham().getiD());
            ps.setString(2, chiTietSanPham.getNhaSanXuat().getiD());
            ps.setString(3, chiTietSanPham.getMauSac().getiD());
            ps.setString(4, chiTietSanPham.getDongSanPham().getiD());
            ps.setInt(5, chiTietSanPham.getNamBH());
            ps.setString(6, chiTietSanPham.getMoTa());
            ps.setInt(7, chiTietSanPham.getSoLuongTon());
            ps.setDouble(8, chiTietSanPham.getGiaNhap());
            ps.setDouble(9, chiTietSanPham.getGiaBan());
            ps.setString(10, iDCTSP);
            checkSua = ps.executeUpdate();
            return checkSua > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean xoaCTSP(String maCTSPX) {
        String query = "DELETE ChiTietSP WHERE Id = ?";
        int checkXoa = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maCTSPX);
            checkXoa = ps.executeUpdate();
            return checkXoa > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<ChiTietSanPham> timCTSP(String tenCTSP) {
        ArrayList<ChiTietSanPham> listCTSPDBTimKiem = new ArrayList<>();
        String query = "SELECT * FROM view_xemCTSP \n"
                + "WHERE ten_sp LIKE N'%" + tenCTSP + "%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

                SanPham sanPham = new SanPham();
                sanPham.setiD(rs.getString(2));
                sanPham.setMaSP(rs.getString(3));
                sanPham.setTenSP(rs.getString(4));

                NhaSanXuat nhaSanXuat = new NhaSanXuat(rs.getString(5), rs.getString(6), rs.getString(7));
                MauSac mauSac = new MauSac(rs.getString(8), rs.getString(9), rs.getString(10));
                DongSanPham dongSanPham = new DongSanPham(rs.getString(11), rs.getString(12), rs.getString(13));

                chiTietSanPham.setiD(rs.getString(1));
                chiTietSanPham.setSanPham(sanPham);
                chiTietSanPham.setNhaSanXuat(nhaSanXuat);
                chiTietSanPham.setMauSac(mauSac);
                chiTietSanPham.setDongSanPham(dongSanPham);
                chiTietSanPham.setNamBH(rs.getInt(14));
                chiTietSanPham.setMoTa(rs.getString(15));
                chiTietSanPham.setSoLuongTon(rs.getInt(16));
                chiTietSanPham.setGiaNhap(rs.getDouble(17));
                chiTietSanPham.setGiaBan(rs.getDouble(18));
                listCTSPDBTimKiem.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTSPDBTimKiem;
    }

    @Override
    public ArrayList<SPBanHangViewModel> getListSP() {
        ArrayList<SPBanHangViewModel> listSPBH = new ArrayList<>();
        String query = "SELECT * FROM view_xemSanPhamBH";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SPBanHangViewModel sPBH = new SPBanHangViewModel();
                sPBH.setMaSP(rs.getString(1));
                sPBH.setTenSP(rs.getString(2));
                sPBH.setNamBH(rs.getInt(3));
                sPBH.setMoTa(rs.getString(4));
                sPBH.setSoLuongSP(rs.getInt(5));
                sPBH.setGiaNhap(rs.getDouble(6));
                sPBH.setGiaBan(rs.getDouble(7));
                listSPBH.add(sPBH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSPBH;
    }

    @Override
    public ArrayList<SPBanHangViewModel> timSPBanHang(String tenTimKiem) {
        ArrayList<SPBanHangViewModel> listSPBHTimKiem = new ArrayList<>();
        String query = "SELECT * FROM view_xemDSSanPhamBanHang\n"
                + "WHERE Ten LIKE '%" + tenTimKiem + "%'";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SPBanHangViewModel sPBH = new SPBanHangViewModel();
                sPBH.setMaSP(rs.getString(1));
                sPBH.setTenSP(rs.getString(2));
                sPBH.setNamBH(rs.getInt(3));
                sPBH.setMoTa(rs.getString(4));
                sPBH.setSoLuongSP(rs.getInt(5));
                sPBH.setGiaNhap(rs.getDouble(6));
                sPBH.setGiaBan(rs.getDouble(7));
                listSPBHTimKiem.add(sPBH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSPBHTimKiem;
    }

    @Override
    public ArrayList<SanPhamTest> getListSPTest() {
        ArrayList<SanPhamTest> listSP = new ArrayList<>();
        String query = "SELECT Id,NamBH,MoTa,SoLuongTon,GiaNhap FROM ChiTietSP";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamTest sanPham = new SanPhamTest();
                sanPham.setId(rs.getString(1));
                sanPham.setNamBH(rs.getInt(2));
                sanPham.setMoTa(rs.getString(3));
                sanPham.setSoLuongTon(rs.getInt(4));
                sanPham.setGiaNhap(rs.getDouble(5));
                listSP.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    @Override
    public Boolean updateSPTest(SanPhamTest sanPham, String iDSP) {
        String query = "UPDATE ChiTietSP \n"
                + "SET NamBH = ?,\n"
                + "MoTa = ?,\n"
                + "SoLuongTon = ?,\n"
                + "GiaBan = ?\n"
                + "WHERE Id = ?";
        int checkUpdate = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, sanPham.getNamBH());
            ps.setString(2, sanPham.getMoTa());
            ps.setInt(3, sanPham.getSoLuongTon());
            ps.setInt(4, (int) sanPham.getGiaNhap());
            ps.setString(5, iDSP);
            checkUpdate = ps.executeUpdate();
            return checkUpdate > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean themSanPham(SanPhamTest sanPham) {
        String query = "INSERT INTO ChiTietSP(NamBH,MoTa,SoLuongTon,GiaNhap) \n"
                + "VALUES (?,?,?,?)";
        int checkThem = 0;
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, sanPham.getNamBH());
            ps.setString(2, sanPham.getMoTa());
            ps.setInt(3, sanPham.getSoLuongTon());
            ps.setInt(4, (int) sanPham.getGiaNhap());
            checkThem = ps.executeUpdate();
            return checkThem > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
