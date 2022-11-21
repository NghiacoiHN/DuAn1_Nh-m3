/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChucVu;
import trungvdpd21738_sof2041_jdbc1.Repositories.ChucVuRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.ChucVuRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.ChucVuService;

/**
 *
 * @author TrungVD
 */
public class ChucVuServiceImpl implements ChucVuService{

    private ChucVuRepository chucVuRepository = new ChucVuRepositoryImpl();
    
    private int valiDate(ChucVu chucVu){
        if(chucVu.getMaCV().trim().length() == 0 ||
                chucVu.getTenCV().trim().length() == 0){
            return 1;
        }
        return 0;
    }
    @Override
    public ArrayList<ChucVu> getListChucVuDB() {
        return chucVuRepository.getListChucVuDB();
    }

    @Override
    public String themChucVu(ChucVu chucVu) {
        if(valiDate(chucVu) == 1){
            return "Không để trống thông tin";
        }
        boolean themCV = chucVuRepository.themChucVu(chucVu);
        if(themCV){
            return "Thêm thành công!";
        }else{
            return "Thêm thất bại!";
        }
    }

    @Override
    public String suaChucVu(ChucVu chucVu, String maCVSua) {
        if(valiDate(chucVu) == 1){
            return "Không để trống thông tin";
        }
        boolean themCV = chucVuRepository.suaChucVu(chucVu, maCVSua);
        if(themCV){
            return "Sửa thành công!";
        }else{
            return "Sửa thất bại!";
        }
    }

    @Override
    public String xoaChucVu(String maCVXoa) {
        boolean themCV = chucVuRepository.xoaChucVu(maCVXoa);
        if(themCV){
            return "Xóa thành công!";
        }else{
            return "Xóa thất bại!";
        }
    }

    @Override
    public ArrayList<ChucVu> timChucVu(String tenCVTim) {
        return chucVuRepository.timChucVu(tenCVTim);
    }
    
}
