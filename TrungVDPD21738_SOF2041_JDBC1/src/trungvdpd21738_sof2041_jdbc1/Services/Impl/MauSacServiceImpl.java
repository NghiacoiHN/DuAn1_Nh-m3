/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services.Impl;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.MauSac;
import trungvdpd21738_sof2041_jdbc1.Repositories.MauSacRepository;
import trungvdpd21738_sof2041_jdbc1.Repositories.impl.MauSacRepositoryImpl;
import trungvdpd21738_sof2041_jdbc1.Services.MauSacService;

/**
 *
 * @author TrungVD
 */
public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository mauSacRepository = new MauSacRepositoryImpl();

    private int valiDate(MauSac mauSac) {
        if (mauSac.getMaMauSac().trim().length() == 0
                || mauSac.getTenMauSac().trim().length() == 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public ArrayList<MauSac> getListMSDB() {
        return mauSacRepository.getListMSDB();
    }

    @Override
    public String themMauSac(MauSac mauSac) {
        if (valiDate(mauSac) == 1) {
            return "Không để trống thông tin sản phẩm";
        }
        boolean themMS = mauSacRepository.themMauSac(mauSac);
        if (themMS) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String suaMauSac(MauSac mauSac, String maMSSua) {
        if (valiDate(mauSac) == 1) {
            return "Không để trống thông tin sản phẩm";
        }
        boolean suaMS = mauSacRepository.suaMauSac(mauSac, maMSSua);
        if (suaMS) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String xoaMauSac(String maMSXoa) {
        Boolean xoaMS = mauSacRepository.xoaMauSac(maMSXoa);
        if (xoaMS) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public ArrayList<MauSac> timKiemMauSac(String tenMauSac) {
        return mauSacRepository.timKiemMauSac(tenMauSac);
    }
//    . : đại diện cho 1 ký tự bất kỳ trừ ký tự xuống dòng \n.
//\d : ký tự chữ số tương đương [0-9]
//\D : ký tự ko phải chữ số
//\s : ký tự khoảng trắng tương đương [ \f\n\r\t\v]
//\S : ký tự không phải khoảng trắng tương đương [ ^\f\n\r\t\v]
//\w : ký tự word (gồm chữ cái và chữ số, dấu gạch dưới _ ) tương đương [a-zA-Z_0-9]
//\W : ký tự không phải ký tự word tương đương [^a-zA-Z_0-9]
//^ : bắt đầu 1 chuỗi hay 1 dòng
//$ : kết thúc 1 chuỗi hay 1 dòng
//\A : bắt đầu 1 chuỗi
//\z : kết thúc 1 chuỗi
//| : ký tự ngăn cách so trùng tương đương với phép or (lưu ý cái này nếu muốn kết hợp nhiều điều kiện)
//[abc] : khớp với 1 ký tự nằm trong nhóm là a hay b hay c.
//[a-z] so trùng với 1 ký tự nằm trong phạm vi a-z, dùng dấu - làm dấu ngăn cách.
//[^abc] sẽ không so trùng với 1 ký tự nằm trong nhóm, ví dụ không so trùng với a hay b hay c.
//() : Xác định 1 group (biểu thức con) xem như nó là một yếu tố đơn lẻ trong pattern .ví dụ ((a(b))c) sẽ khớp với b, ab, abc.
//? : khớp với đứng trước từ 0 hay 1 lần. Ví dụ A?B sẽ khớp với B hay AB.
//: khớp với đứng trước từ 0 lần trở lên . A*B khớp với B, AB, AAB
//: khớp với đứng trước từ 1 lần trở lên. A+B khớp với AB, AAB.
//{n} : n là con số, Khớp đúng với n ký tự đúng trước nó . Ví dụ A{2}) khớp đúng với 2 chữ A.
//{n, } : khớp đúng với n ký tự trở lên đứng trước nó , A{2,} khớp vói AA, AAA ...
//{m,n} : khớp đùng với từ m->n ký tự đứng trước nó, A{2,4} khớp vói AA,AAA,AAAA
}
