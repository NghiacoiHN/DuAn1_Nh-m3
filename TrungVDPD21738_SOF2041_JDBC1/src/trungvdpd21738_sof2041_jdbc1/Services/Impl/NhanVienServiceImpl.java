/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhanVien;
import trungvdpd21738_sof2041_jdbc1.Repositories.NhanVienRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.NhanVienRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.NhanVienService;
import trungvdpd21738_sof2041_jdbc1.ViewModels.NhanVienViewModel;

/**
 *
 * @author TrungVD
 */
public class NhanVienServiceImpl implements NhanVienService{

    private NhanVienRepository nhanVienRepository = new NhanVienRepositoryImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private int valiDate(NhanVien nhanVien){
        if(nhanVien.getMa().trim().length() == 0 ||
                nhanVien.getTen().trim().length() == 0 ||
                nhanVien.getTenDem().trim().length() == 0 ||
                nhanVien.getNgaySinh().trim().length() == 0 ||
                nhanVien.getMatKhau().trim().length() == 0 ||
                nhanVien.getMa().trim().length() == 0 ||
                nhanVien.getDiaChi().trim().length() == 0 ||
                nhanVien.getsDT().trim().length() == 0 ||
                nhanVien.getHo().trim().length() == 0){
            return 1;
        }
        Date ngaySinh = null;
        try {
            ngaySinh = sdf.parse(nhanVien.getNgaySinh());
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
        Pattern sDT = Pattern.compile("^0[0-9]{9}$");
        if(!sDT.matcher(nhanVien.getsDT()).find()){
            return 3;
        }
        Pattern matKhau = Pattern.compile("^(?=.A-Z){8,}$");
        if(!matKhau.matcher(nhanVien.getMatKhau()).find()){
            return 4;
        }
        return 0;
    }
    @Override
    public ArrayList<NhanVien> getListNSXDB() {
        return nhanVienRepository.getListNSXDB();
    }

    @Override
    public String themNhanVien(NhanVien nhanVien) {
        if(valiDate(nhanVien) == 1){
            return "Không để trống thông tin nhân viên";
        }
        if(valiDate(nhanVien)==2){
            return "Nhập sai định dạng yyyy-MM-dd";
        }
        if(valiDate(nhanVien)==3){
            return "Số điện thoại phải có 10 số và bắt đầu từ 0";
        }
        if(valiDate(nhanVien)==4){
            return "Mật khẩu phải từ 8 kí tự và có chữ in hoa";
        }
        boolean themNV = nhanVienRepository.themNhanVien(nhanVien);
        if(themNV){
            return "Thêm thành công!";
        }else{
            return "Thêm thất bại!";
        }
    }

    @Override
    public String suaNhanVien(NhanVien nhanVien, String maNVSua) {
        if(valiDate(nhanVien) == 1){
            return "Không để trống thông tin nhân viên";
        }
        boolean suaNV = nhanVienRepository.suaNhanVien(nhanVien, maNVSua);
        if(suaNV){
            return "Sửa thành công!";
        }else{
            return "Sửa thất bại!";
        }
    }

    @Override
    public String xoaNhanVien(String maNVXoa) {
        boolean xoaNV = nhanVienRepository.xoaNhanVien(maNVXoa);
        if(xoaNV){
            return "Xóa thành công!";
        }else{
            return "Xóa thất bại!";
        }
    }

    @Override
    public ArrayList<NhanVien> timKiemNV(String tenNV) {
        return nhanVienRepository.timKiemNV(tenNV);
    }

    @Override
    public ArrayList<NhanVienViewModel> listNVViewModel() {
        return nhanVienRepository.listNVViewModel();
    }

    @Override
    public String themNhanVien2(NhanVien nhanVien) {
        boolean themNV = nhanVienRepository.themNhanVien2(nhanVien);
        if(themNV){
            return "Thêm thành công!";
        }else{
            return "Thêm thất bại!";
        }
    }
    
}
