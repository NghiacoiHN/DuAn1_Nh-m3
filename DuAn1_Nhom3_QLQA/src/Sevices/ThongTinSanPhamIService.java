/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sevices;

import ViewModels.ThongTinSanPhamVM;
import java.util.List;

/**
 *
 * @author longd
 */
public interface ThongTinSanPhamIService {
    
    List<ThongTinSanPhamVM> fillByMaHD(String mahd);
    
}
