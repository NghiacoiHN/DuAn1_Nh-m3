/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitories;

import DomainModels.KhacHangDM;
import java.util.List;

/**
 *
 * @author HP
 */
public interface KhachHangIRepon {
   
     List<KhacHangDM> getAll();

    List<KhacHangDM> getKhachHangByID(String Ma);

    boolean createNewKhachHang(KhacHangDM KhachHang);

    boolean UpdateKhachHangById(KhacHangDM KhachHang);

    boolean DeleteKhachHangById(String Ma);
}
