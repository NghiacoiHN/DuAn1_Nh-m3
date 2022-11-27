/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sevices;

import ViewModels.KhachHangVM;
import java.util.List;

/**
 *
 * @author HP
 */
public interface KhachHangISevice {

    List<KhachHangVM> getAll();

    List<KhachHangVM> getKhachHangByID(String Ma);

    boolean createNewKhachHang(KhachHangVM KhachHang);

    boolean UpdateKhachHangById(KhachHangVM KhachHang);

    boolean DeleteKhachHangById(String Ma);
}
