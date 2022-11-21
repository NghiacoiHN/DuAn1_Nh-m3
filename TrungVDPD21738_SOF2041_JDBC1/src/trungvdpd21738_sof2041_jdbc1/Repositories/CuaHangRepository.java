/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.CuaHang;

/**
 *
 * @author TrungVD
 */
public interface CuaHangRepository {
    public ArrayList<CuaHang> getListCuaHangDB();
    
    public Boolean themCuaHang(CuaHang cuaHang);
    
    public Boolean suaCuaHang(CuaHang cuaHang, String maCHSua);
    
    public Boolean xoaCuaHang(String maCHXoa);
    
    public ArrayList<CuaHang> timCuaHang(String diaChiCHTim);
}
