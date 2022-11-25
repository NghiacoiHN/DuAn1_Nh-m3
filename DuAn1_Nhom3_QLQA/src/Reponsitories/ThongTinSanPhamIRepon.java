/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitories;

import DomainModels.ThongTinSanPhamDM;
import java.util.List;

/**
 *
 * @author longd
 */
public interface ThongTinSanPhamIRepon {
    
    List<ThongTinSanPhamDM> fillByMaHD(String mahd);
    
}
