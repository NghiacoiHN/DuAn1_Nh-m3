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
                  (ThanhTien, TienDua, TienThua, HinhThucThanhToan)
VALUES (15000,20000,5000,N'Bằng thẻ')
Select*from HoaDon
--Chi tiết sản phẩm
INSERT INTO ChiTietSanPham
                  (MaCTSP, IDCL, IDMS, IDSize, IDSP, IDLSP, IDXX, MoTa, SoLuongTon, GiaNhap, GiaBan, TrangThai)
VALUES ('CTSP01','326CFC39-1A84-43CC-90F6-4EEBE75271C6','257C2048-771A-4B10-A665-41988346F884','EA49C9C9-969F-4D2C-B7EB-170279F09F70',
'178DA487-20DC-45FB-A46B-D4A6C25DC46D','E4FDD482-6114-4FB4-AA5A-3DFD1F14B133','B40AAA17-CD31-4125-BC4D-DBDE555C485B',
N'Đẹp',1015,1000,1500,1),
('CTSP02','E72B7845-E3B0-4A1E-ADBA-2F499894E843','763B2F7B-E005-46C2-99CE-3AFE89A7A65E','EA49C9C9-969F-4D2C-B7EB-170279F09F70',
'430611E7-D872-484A-94CA-E2B4E3A22236','FDD14731-1C96-4C12-913B-6C1AEF2011CB','B40AAA17-CD31-4125-BC4D-DBDE555C485B',
N'Đẹp',160,2000,2500,1)
Select*from ChiTietSanPham
Select*from XuatXu

--Hóa đơn chi tiết
INSERT INTO HoaDonChiTiet
                  (IDHD, IDCTSP, SoLuong, DonGia, TrangThai)
VALUES ('53502DF4-492B-4A2D-9455-16D444C7B8E8','DC22CA4F-3AAE-4FF6-AACA-7BBD060B1FF2',2,5000,1),
('53502DF4-492B-4A2D-9455-16D444C7B8E8','806F9689-7C78-4504-9FF8-F4030D752B0A',1,1500,1)
Select*from HoaDonChiTiet
Select*from HoaDon


UPDATE ChiTietSanPham
SET          SoLuongTon = 256
WHERE MaCTSP = 'CTSP02'

Select * from ChiTietSanPham

INSERT INTO HoaDon
                  (TenKH, SDTKH, TenShip, SDTShip, DiaChi, NgayMuonNhan, NgayTao, TrangThai)
VALUES (?,?,?,?,?,?,?,2)