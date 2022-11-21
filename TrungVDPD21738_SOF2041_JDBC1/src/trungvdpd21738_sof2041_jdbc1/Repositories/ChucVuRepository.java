/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChucVu;

/**
 *
 * @author TrungVD
 */
public interface ChucVuRepository {
    public ArrayList<ChucVu> getListChucVuDB();
    
    public Boolean themChucVu(ChucVu chucVu);
    
    public Boolean suaChucVu(ChucVu chucVu, String maCVSua);
    
    public Boolean xoaChucVu(String maCVXoa);
    
    public ArrayList<ChucVu> timChucVu(String tenCVTim);
}
