/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChucVu;

/**
 *
 * @author TrungVD
 */
public interface ChucVuService {
    public ArrayList<ChucVu> getListChucVuDB();
    
    public String themChucVu(ChucVu chucVu);
    
    public String suaChucVu(ChucVu chucVu, String maCVSua);
    
    public String xoaChucVu(String maCVXoa);
    
    public ArrayList<ChucVu> timChucVu(String tenCVTim);
}
