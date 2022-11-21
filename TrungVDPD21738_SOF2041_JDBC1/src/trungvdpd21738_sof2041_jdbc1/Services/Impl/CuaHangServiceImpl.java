/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.CuaHang;
import trungvdpd21738_sof2041_jdbc1.Repositories.CuaHangRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.CuaHangRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.CuaHangService;

/**
 *
 * @author TrungVD
 */
public class CuaHangServiceImpl implements CuaHangService{

    private CuaHangRepository cuaHangRepository = new CuaHangRepositoryImpl();
    
    private int valiDate(CuaHang cuaHang){
        if(cuaHang.getMa().trim().length() == 0 ||
                cuaHang.getTenCH().trim().length() == 0){
            return 1;
        }
        return 0;
    }
    @Override
    public ArrayList<CuaHang> getListCuaHangDB() {
        return cuaHangRepository.getListCuaHangDB();
    }

    @Override
    public String themCuaHang(CuaHang cuaHang) {
        if(valiDate(cuaHang) == 1){
            return "Không để trống thông tin";
        }
        boolean themCH = cuaHangRepository.themCuaHang(cuaHang);
        if(themCH){
            return "Thêm thành công!";
        }else{
            return "Thêm thất bại!";
        }
    }

    @Override
    public String suaCuaHang(CuaHang cuaHang, String maCHSua) {
        if(valiDate(cuaHang) == 1){
            return "Không để trống thông tin";
        }
        boolean suaCH = cuaHangRepository.suaCuaHang(cuaHang, maCHSua);
        if(suaCH){
            return "Sửa thành công!";
        }else{
            return "Sửa thất bại!";
        }
    }

    @Override
    public String xoaCuaHang(String maCHXoa) {
        boolean xoaCH = cuaHangRepository.xoaCuaHang(maCHXoa);
        if(xoaCH){
            return "Xóa thành công!";
        }else{
            return "Xóa thất bại!";
        }
    }

    @Override
    public ArrayList<CuaHang> timCuaHang(String diaChiCHTim) {
        return cuaHangRepository.timCuaHang(diaChiCHTim);
    }
    
}
