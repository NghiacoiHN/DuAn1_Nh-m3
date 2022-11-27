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
                  (IDCH, MaNV, HoNV, TenNV, SDT, Email, MatKhau, ChucVu, DiaChi, TrangThai)
VALUES ('4BA3BD69-64A6-4678-8AB5-44B15D386740','NV01',N'Nguyễn Trọng',N'Nghĩa','0337842655','nghiantph23346@fpt.edu.vn','123456',1,N'Hà Nội',1),
('2EFEDA80-DD0D-495F-8CA3-D1596CE71F48','NV02',N'Lê Thế',N'Vinh','0345893615','vinhltph23387@fpt.edu.vn','123456',1,N'Hà Nội',1)
SELECT*FROM NhanVien 
--Hóa đơn
INSERT INTO HoaDon
                  (TongTien, TienDua, TienThua, HinhThucThanhToan)
VALUES (15000,20000,5000,N'Bằng thẻ')
Select*from HoaDon
--Chi tiết sản phẩm
INSERT INTO ChiTietSanPham
                  (MaCTSP, IDCL, IDMS, IDSize, IDSP, IDLSP, IDXX, MoTa, SoLuongTon, GiaNhap, GiaBan, TrangThai)
VALUES ('CTSP01','63ECA4A0-8E30-4399-A959-6E9DAEFCDA6A','6956DBC6-AB49-436A-A30F-78783052E5E5','75EA30D8-A42D-4B27-916A-C23B868434D2',
'90DF93EA-F951-4455-A056-5FD3DBB737FE','18CF8355-BD67-4B33-A99B-FED62BBD37A3','A971DDC3-BD67-459D-89D8-1233B72C94B2',
N'Đẹp',1015,1000,1500,1),
('CTSP02','DD91FF33-63CC-4A9F-8F73-BA9BBBF27275','6956DBC6-AB49-436A-A30F-78783052E5E5','8EACDE09-7063-4244-B18D-B36620FDBFD8',
'B9786C37-C02B-4D74-987A-9C733749CE03','B0D6BA7C-4F2F-491C-8731-97491F355D28','EFFDE34D-E9CF-40B4-9E40-17297C2D9F29',
N'Đẹp',160,2000,2500,1)
Select*from ChiTietSanPham
Select*from XuatXu

--Hóa đơn chi tiết
INSERT INTO HoaDonChiTiet
                  (IDHD, IDCTSP, SoLuong, DonGia, TrangThai)
VALUES ('53502DF4-492B-4A2D-9455-16D444C7B8E8','DC22CA4F-3AAE-4FF6-AACA-7BBD060B1FF2',2,5000,1),
('53502DF4-492B-4A2D-9455-16D444C7B8E8','806F9689-7C78-4504-9FF8-F4030D752B0A',1,1500,1)
Select HoaDonChiTiet.IDHDCT from HoaDonChiTiet
Select*from HoaDon


UPDATE ChiTietSanPham
SET          SoLuongTon = 256
WHERE MaCTSP = 'CTSP02'

Select * from ChiTietSanPham
UPDATE HoaDon
SET          ThanhTien =?, TienDua =?, TienThua =?, HinhThucThanhToan =?, SoTienGiamGia =?, NgayGiao =?, TrangThai = 4
WHERE MaHD = ?

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


