/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.CuaHang;

/**
 *
 * @author TrungVD
 */
public interface CuaHangService {
    public ArrayList<CuaHang> getListCuaHangDB();
    
    public String themCuaHang(CuaHang cuaHang);
    
    public String suaCuaHang(CuaHang cuaHang, String maCHSua);
    
    public String xoaCuaHang(String maCHXoa);
    
    public ArrayList<CuaHang> timCuaHang(String diaChiCHTim);
}
