--Chất liệu
INSERT INTO ChatLieu
                  (MaCL, TenCL, TinhTrang)
VALUES ('CL01',N'Nỉ',1), ('CL02',N'Bông',1)
SELECT*FROM ChatLieu
--Size
INSERT INTO Size
                  (MaSize, TenSize, TinhTrang)
VALUES ('SZ01',N'L',1), ('SZ02',N'M',1)
SELECT*FROM Size
--Màu sắc
INSERT INTO MauSac
                  (MaMS, TenMS, TinhTrang)
VALUES ('MS01',N'Màu Hồng',1),('MS02',N'Màu Xanh',1)
SELECT*FROM MauSac
--Sản phẩm
INSERT INTO SanPham
                  (MaSP, TenSp, TinhTrang)
VALUES ('SP01',N'Quần',1),('SP02',N'Áo',1)
SELECT*FROM SanPham
--Loại sản phẩm
INSERT INTO LoaiSP
                  (MaLSP, TenLSP, TinhTrang)
VALUES ('LSP01',N'Áo Blaze',1), ('LSP02',N'Quần Blaze',1)
SELECT*FROM LoaiSP
--XuatXu
INSERT INTO XuatXu
                  (MaXX, TenNuoc, TinhTrang)
VALUES ('XX01',N'Mỹ',1), ('XX02',N'Việt Nam',1)
SELECT*FROM XuatXu

--Cửa hàng
INSERT INTO CuaHang
                  (MaCH, TenCH, DiaChi, TrangThai)
VALUES ('CH01',N'Cửa Hàng Vui Vẻ',N'Hà Nội',1),('CH02',N'Cửa Hàng Vẻ Vui',N'Hà Nội',1)
SELECT*FROM CuaHang
--Nhân viên
INSERT INTO NhanVien
                  (MaNV, HoNV, TenNV, SDT, MatKhau, ChucVu, DiaChi, TrangThai, IDCH)
VALUES ('NV01',N'Nguyễn',N'Nghĩa','0337842655','123456',1,N'Hà Nội',1,'FDCB75A2-AC1D-4683-9321-84594E74812D'),
('NV02',N'Nguyễn',N'Hoàng','0987654321','123456',2,N'Hà Nội',1,'B23CBF2F-C5D7-425D-9B3C-FC4098F74F5F')
SELECT*FROM NhanVien WHERE SDT = 0987654321 and MatKhau = 123456 and ChucVu = 2
--Hóa đơn
INSERT INTO HoaDon
                  (ThanhTien, TienDua, TienThua, HinhThucThanhToan)
VALUES (15000,20000,5000,N'Bằng thẻ')
Select*from HoaDon
--Chi tiết sản phẩm
INSERT INTO ChiTietSanPham
                  (MaCTSP, IDCL, IDMS, IDSize, IDSP, IDLSP, IDXX, MoTa, SoLuongTon, GiaNhap, GiaBan, TrangThai)
VALUES ('CTSP01','2184744D-C9E4-41DF-9C8F-62AC19AB3E80','C38BB44A-7FEF-486A-AB70-B26BD7A7B16A','819DAF3E-63B0-4082-9A92-A2B2E2043696',
'5AB63082-B89B-4AF7-86A7-CE707CA3CE1D','5DECFA5B-6062-44C3-8C63-DA5F811F4F90','902D0A3B-9DA4-4565-84A1-4EB861680298',
N'Đẹp',10,1000,1500,1),
('CTSP02','0A3406EC-A532-447F-AFB8-8AFD956871BE','C38BB44A-7FEF-486A-AB70-B26BD7A7B16A','819DAF3E-63B0-4082-9A92-A2B2E2043696',
'DC3CD743-A3D2-4F6D-AF8A-63159A810A3C','5DECFA5B-6062-44C3-8C63-DA5F811F4F90','1F1FE82C-6664-4F0E-BD41-DA6852ABB603',
N'Đẹp',16,2000,2500,1)
Select*from ChiTietSanPham


--Hóa đơn chi tiết
INSERT INTO HoaDonChiTiet
                  (IDHD, IDCTSP, SoLuong, DonGia, TrangThai)
VALUES ('53502DF4-492B-4A2D-9455-16D444C7B8E8','DC22CA4F-3AAE-4FF6-AACA-7BBD060B1FF2',2,5000,1),
('53502DF4-492B-4A2D-9455-16D444C7B8E8','806F9689-7C78-4504-9FF8-F4030D752B0A',1,1500,1)
Select*from HoaDonChiTiet
Select*from HoaDon

SELECT HoaDon.MaHD, SanPham.TenSp, LoaiSP.TenLSP, ChatLieu.TenCL, MauSac.TenMS, Size.TenSize, XuatXu.TenNuoc, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia
FROM     HoaDon INNER JOIN
                  HoaDonChiTiet ON HoaDon.IDHD = HoaDonChiTiet.IDHD INNER JOIN
                  ChiTietSanPham ON HoaDonChiTiet.IDCTSP = ChiTietSanPham.IDCTSP INNER JOIN
                  LoaiSP ON ChiTietSanPham.IDLSP = LoaiSP.IDLSP INNER JOIN
                  ChatLieu ON ChiTietSanPham.IDCL = ChatLieu.IDCL INNER JOIN
                  MauSac ON ChiTietSanPham.IDMS = MauSac.IDMS INNER JOIN
                  Size ON ChiTietSanPham.IDSize = Size.IDSize INNER JOIN
                  XuatXu ON ChiTietSanPham.IDXX = XuatXu.IDXX INNER JOIN
                  SanPham ON ChiTietSanPham.IDSP = SanPham.IDSP
WHERE HoaDon.MaHD = ?

