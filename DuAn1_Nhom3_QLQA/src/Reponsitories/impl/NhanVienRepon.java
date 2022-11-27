/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitories.impl;


import DomainModels.CuaHangDM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DomainModels.NhanVienDM;
import Utilities.DBConnection;
/**
 *
 * @author hiep nguyen
 */
public class NhanVienRepon {
    private DBConnection dBcontext;
    public ArrayList<DomainModels.NhanVienDM> getAll() throws SQLException {
        ArrayList<DomainModels.NhanVienDM> list = new ArrayList<>();
        String sql = "SELECT [IDCH]\n" +
"      ,[MaNV]\n" +
"      ,[HoNV]\n" +
"      ,[TenNV]\n" +
"      ,[SDT]\n" +
"      ,[MatKhau]\n" +
"      ,[ChucVu]\n" +
"      ,[DiaChi]\n" +
"      ,[TrangThai]\n" +
"  FROM [dbo].[NhanVien]";
        Connection connection = dBcontext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DomainModels.NhanVienDM nv = new NhanVienDM();
            nv.setIdCH(rs.getString(1));
            nv.setMaNv(rs.getString(2));
            nv.setHoNV(rs.getString(3));
            nv.setTenNv(rs.getString(4));
            nv.setSdt(rs.getString(5));
            nv.setMatKhau(rs.getString(6));
            nv.setIdCV(rs.getString(7));
            nv.setDiaChi(rs.getString(8));
            nv.setTrangThai(rs.getInt(9));

            list.add(nv);
        }
        return list;
    }

    public ArrayList<CuaHangDM> getAllCuaHang() throws SQLException {
        ArrayList<CuaHangDM> list = new ArrayList<>();
        String sql = "SELECT [TenCH]\n" +
"  FROM [dbo].[CuaHang]";
        Connection connection = dBcontext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            CuaHangDM ch = new CuaHangDM();
           
            ch.setTenCH(rs.getString(1));
            
            list.add(ch);
        }
        return  list;
    }
    public CuaHangDM getIdCH(String ma) throws SQLException{
        String sql = "SELECT [IDCH]\n" +
"  FROM [dbo].[CuaHang]";
        Connection connection = dBcontext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            CuaHangDM ch = new CuaHangDM();
            ch.setId(rs.getString(1));
            return ch;
        }
        return  null;
    }
    
    

    public boolean addNV(NhanVienDM nv) throws SQLException {
        String sql = "INSERT INTO [dbo].[NhanVien]\n" +
"           ,[IDCH]\n" +
"           ,[MaNV]\n" +
"           ,[HoNV]\n" +
"           ,[TenNV]\n" +
"           ,[SDT]\n" +
"           ,[MatKhau]\n" +
"           ,[ChucVu]\n" +
"           ,[DiaChi]\n" +
"           ,[TrangThai])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)";
        Connection connection = dBcontext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(10, nv.getIdCH());
        ps.setString(1, nv.getMaNv());
        ps.setString(4, nv.getHoNV());
        ps.setString(2, nv.getTenNv());
        
        ps.setString(7, nv.getDiaChi());
        ps.setString(8, nv.getSdt());
        ps.setString(9, nv.getMatKhau());
      
        ps.setString(11, nv.getIdCV());
        ps.setInt(12, nv.getTrangThai());

        return ps.executeUpdate() > 0;
    }

    public boolean suaNV(NhanVienDM nv) throws SQLException {
        String sql = "UPDATE [dbo].[NhanVien]\n" +
"   SET [IDNV] = ?\n" +
"      ,[IDCH] = ?\n" +
"      ,[MaNV] = ?\n" +
"      ,[HoNV] = ?\n" +
"      ,[TenNV] =?\n" +
"      ,[SDT] =?\n" +
"      ,[MatKhau] = ?\n" +
"      ,[ChucVu] = ?\n" +
"      ,[DiaChi] = ?\n" +
"      ,[TrangThai] = ?\n" +
" WHERE <Search Conditions,,>";
        Connection connection = dBcontext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, nv.getTenNv());

        ps.setString(3, nv.getHoNV());

        ps.setString(6, nv.getDiaChi());
        ps.setString(7, nv.getSdt());
        ps.setString(8, nv.getMatKhau());
        ps.setString(9, nv.getIdCH());
        ps.setString(10, nv.getIdCV());
        ps.setInt(11, nv.getTrangThai());
        ps.setString(12, nv.getMaNv());

        return ps.executeUpdate() > 0;
    }
    
    public boolean xoaNV(String ma) throws SQLException {
        String sql = "DELETE FROM [dbo].[NhanVien]\n" +
"      WHERE <Search Conditions,,>";
        Connection connection = dBcontext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ma);
        
        return ps.executeUpdate()>0;
    }
    
}
