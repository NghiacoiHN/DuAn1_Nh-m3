/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.com.raven.form;

import DomainModels.BHGioHangDM;
import DomainModels.BHHoaDonDM;
import DomainModels.BHSanPhamDM;
import Sevices.impl.BHGioHangSevice;
import Sevices.impl.BHHoaDonSevice;
import Sevices.impl.BHSanPhamSevice;
import ViewModels.BHGioHangVM;
import ViewModels.BHHoaDonVM;
import ViewModels.BHSanPhamVM;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Sevices.BHGioHangISevice;
import Sevices.BHHoaDonISevice;
import Sevices.BHSanPhamISevice;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAVEN
 */
public class JFormBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    /**
     * Creates new form Form_1
     */
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public JFormBanHang() {
        initComponents();
        loadTBDSSP();
        loadTBDSHD();
        loadDateNow();
        loadCB();
//        loadTongTien();
//        initWebcam();
        SetTollTipText();
    }
    BHHoaDonISevice hdsv = new BHHoaDonSevice();
    BHSanPhamISevice spsv = new BHSanPhamSevice();
    BHGioHangISevice ghsv = new BHGioHangSevice();

    //WEBCAM
    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
//        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

//        panelWC.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        panelWC.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 190));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JFormBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                Logger.getLogger(JFormBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (result != null) {
                txtSanPham.setText(result.getText());
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    private void loadTBDSHD() {
        List<BHHoaDonVM> listHD = hdsv.findAll();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "Tên khách hàng", "Trạng thái"});
        for (BHHoaDonVM x : listHD) {
            model.addRow(new Object[]{x.getMaHD(), x.getNgayTao(), x.getTenKH(), x.loadTrangThai()});
        }
        tblHoaDon.setModel(model);
    }

    private void loadTBDSGH() {
//        List<BHGioHangVM> listGH = ghsv.findAll();
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Tên sản phẩm", "Tên loại", "Chất liệu", "Màu sắc", "Size", "Xuất xứ", "Số lượng", "Đơn giá"});
//        for (BHGioHangVM x : listGH) {
//            model.addRow(new Object[]{x.getMaHD(), x.getTenSP(), x.getTenLoaiSP(),
//                x.getTenCL(), x.getTenMS(), x.getTenSize(), x.getTenSX(), x.getSoLuong(), x.getDonGia()});
//        }
//        tblGioHang.setModel(model);
        int chonCot = tblHoaDon.getSelectedRow();
        String layMa = (String) tblHoaDon.getValueAt(chonCot, 0);
        List<BHGioHangVM> listGH = ghsv.getOne(layMa);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Tên sản phẩm", "Tên loại", "Chất liệu", "Màu sắc", "Size", "Xuất xứ", "Số lượng", "Đơn giá"});
        for (BHGioHangVM x : listGH) {
            model.addRow(new Object[]{x.getMaHD(), x.getTenSP(), x.getTenLoaiSP(),
                x.getTenCL(), x.getTenMS(), x.getTenSize(), x.getTenSX(), x.getSoLuong(), x.getDonGia()});
        }
        tblGioHang.setModel(model);
    }

    private void loadTBDSSP() {
        List<BHSanPhamVM> listSP = spsv.findAll();
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã sản phâm", "Tên sản phẩm", "Tên loại", "Chất liệu", "Màu sắc", "Size", "Xuất xứ", "Giá bán", "Số lượng tồn"});
        for (BHSanPhamVM x : listSP) {
            model.addRow(new Object[]{x.getMaCTSP(), x.getTenSP(), x.getTenLoaiSP(), x.getTenCL(), x.getTenMS(), x.getTenSize(),
                x.getTenXuatXu(), x.getGiaBan(), x.getSoLuongTon()});
        }
        tblDSSP.setModel(model);
    }

    private void loadDateNow() {
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("MM/dd/yyyy");
        String format = simpleDateFormat.format(date);
        lbngayHienTai.setText(format);
        lbngayHienTai1.setText(format);
        //SetTollTipText
//        txtNgayKhachMuon.setToolTipText("Ngày Khách Muốn Nhận");
//        btnInHoaDon.setToolTipText("In Hóa Đơn");
    }

    private void SetTollTipText() {
        txtNgayKhachMuon.setToolTipText("Ngày Khách Muốn Nhận");
        btnInHoaDon.setToolTipText("In Hóa Đơn");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        popMenuGH = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Xoa.png"))); // NOI18N
        jMenuItem2.setText("Xóa");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        popMenuGH.add(jMenuItem2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1032, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void loadCB() {
        String thanhToan[] = {"Tiền Mặt", "Thẻ"};
        cbHinhThuc.setModel(new DefaultComboBoxModel(thanhToan));
    }

    private BHHoaDonDM getInputTT() {
        BHHoaDonDM a = new BHHoaDonDM();
        a.setNgayThanhToan(lbngayHienTai.getText());
        a.setThanhTien(BigDecimal.valueOf(Double.parseDouble(lbThanhTien.getText())));
        a.setTienDua(BigDecimal.valueOf(Double.parseDouble(txtTienDua.getText())));
        a.setTienThua(BigDecimal.valueOf(Double.parseDouble(lbTienThua.getText())));
        a.setHinhThucThanhToan((String) cbHinhThuc.getModel().getSelectedItem());
        a.setSoTienGiam(BigDecimal.valueOf(Double.parseDouble(txtSoTienGiam.getText())));
        return a;
    }

    //Load số tiền của Jlable
    private void loadTongTien() {
        double tongTien = 0;
        BigDecimal bgTongTien = new BigDecimal(Double.toString(tongTien));
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
//            tongTien += Double.parseDouble(tblGioHang.getValueAt(i, 7).toString());
            bgTongTien = bgTongTien.add(BigDecimal.valueOf(Double.parseDouble(tblGioHang.getValueAt(i, 8).toString())));
        }
        lbTongTien.setText(String.valueOf(bgTongTien));
        lbTongTien1.setText(String.valueOf(bgTongTien));

    }

    private void loadSoTienCanTra() {
        if (txtSoTienGiam.getText().trim().isEmpty()) {
            lbThanhTien.setText(lbTongTien.getText());
        } else {
            double tienCanTra = 0;
            BigDecimal bgtienCanTra = new BigDecimal(Double.toString(tienCanTra));
            bgtienCanTra = BigDecimal.valueOf(Double.parseDouble(lbTongTien.getText()))
                    .subtract(BigDecimal.valueOf(Double.parseDouble(txtSoTienGiam.getText())));
            lbThanhTien.setText(String.valueOf(bgtienCanTra));
            lbThanhTien1.setText(String.valueOf(bgtienCanTra));
        }

    }

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int chonCot = tblHoaDon.getSelectedRow();
        String layMa = (String) tblHoaDon.getValueAt(chonCot, 0);
        List<BHGioHangVM> listGH = ghsv.getOne(layMa);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Tên sản phẩm", "Tên loại", "Chất liệu", "Màu sắc", "Size", "Xuất xứ", "Số lượng", "Đơn giá"});
        for (BHGioHangVM x : listGH) {
            model.addRow(new Object[]{x.getMaHD(), x.getTenSP(), x.getTenLoaiSP(),
                x.getTenCL(), x.getTenMS(), x.getTenSize(), x.getTenSX(), x.getSoLuong(), x.getDonGia()});
        }
        tblGioHang.setModel(model);

        loadTongTien();
        loadSoTienCanTra();

//        ghsv.getOne(layMa);
    }//GEN-LAST:event_tblHoaDonMouseClicked
    private BHHoaDonDM getIputHD() {
        BHHoaDonDM hoaDon1 = new BHHoaDonDM();
        hoaDon1.setNgayTao(lbngayHienTai.getText());
        hoaDon1.setTenKH(txtTenKH.getText());
        hoaDon1.setSDTKH(txtSDTKH.getText());
        return hoaDon1;
    }
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void panelBorder2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBorder2MouseReleased

    private void tblHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseReleased
    private BHHoaDonDM getTrangThaiHD() {
        int chonCot = tblHoaDon.getSelectedRow();
        int layGiaTri = (int) tblHoaDon.getValueAt(chonCot, 3);
        BHHoaDonDM hoaDon1 = new BHHoaDonDM();
        hoaDon1.setTrangThai(layGiaTri);
        return hoaDon1;
    }
    private void tblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseEntered

    private void btnDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGiaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDaGiaoActionPerformed

    private void btnDoiTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoiTraActionPerformed

    private void btnThanhToan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan1ActionPerformed
        // TODO add your handling code here:
        int chonCot = tblHoaDon.getSelectedRow();
        if (chonCot < 0) {
            JOptionPane.showMessageDialog(this, "Mời Chọn Hóa Đơn Muốn Giao Hàng!!!");
        } else if (txtSoTienGiam.getText().isEmpty() || txtTienDua.getText().isEmpty() || lbThanhTien.getText().isEmpty() || lbTienThua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Số Tiền Giao Hàng!!!");
        } else if (Double.parseDouble(txtSoTienGiam.getText()) < 0 || Double.parseDouble(txtTienDua.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Thông Tin Thanh Toán Lớn Hơn 0!!!");
        } else if (Double.parseDouble(txtTienDua.getText()) < Double.parseDouble(lbThanhTien.getText())) {
            JOptionPane.showMessageDialog(this, "Tiền Đưa Lớn Hơn Số Tiền Cần Trả!!!");
        } else {
            String layGiaTri = (String) tblHoaDon.getValueAt(chonCot, 0);
            hdsv.update(getInputGiaoHang(), layGiaTri);
            JOptionPane.showMessageDialog(this, "Giao Hàng Thành Công!!!");
            loadTBDSHD();
        }
    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    private void btnTaoHoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDon1ActionPerformed
        // TODO add your handling code here:
        if (txtTenKH2.getText().trim().isEmpty() || txtSDTKH2.getText().trim().isEmpty() || txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Đầy Đủ Thông Tin Khách Hàng!!!");
        } else if (txtTenShip.getText().trim().isEmpty() || txtSDTShip.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Đầy Đủ Thông Tin Nhân Viên Giao Hàng!!!");
        } else {
            hdsv.addGiaoHang(getDataTaoDonGiaoHang());
            JOptionPane.showMessageDialog(this, "Tạo Đơn Giao Hàng Thành Công!!!");
            loadTBDSHD();
        }
        {
        }
    }//GEN-LAST:event_btnTaoHoaDon1ActionPerformed

    private void txtTienShipKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienShipKeyTyped
        // TODO add your handling code here:
        if (txtSoTienGiam1.getText().trim().isEmpty()) {
            lbThanhTien.setText(lbTongTien1.getText());
        } else if (Double.parseDouble(txtSoTienGiam1.getText()) >= Double.parseDouble(lbTongTien1.getText())) {
            JOptionPane.showMessageDialog(this, "Số Tiền Giảm Không Lớn Hơn Tổng Tiền");
        } else {
            double thanhTien = 0;
            BigDecimal bgThanhTien = new BigDecimal(Double.toString(thanhTien));
            //            BigDecimal tienShip = new BigDecimal(Double.parseDouble(txtTenShip.getText()));

            bgThanhTien = BigDecimal.valueOf(Double.parseDouble(lbTongTien1.getText()))
            .subtract(BigDecimal.valueOf(Double.parseDouble(txtSoTienGiam1.getText())))
            .add(BigDecimal.valueOf(Double.parseDouble(txtTenShip.getText())));
            lbThanhTien1.setText(String.valueOf(bgThanhTien));
        }
    }//GEN-LAST:event_txtTienShipKeyTyped

    private void txtTienShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienShipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienShipActionPerformed

    private void txtSoTienGiam1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoTienGiam1KeyTyped
        // TODO add your handling code here:
        if (txtSoTienGiam1.getText().trim().isEmpty()) {
            lbThanhTien.setText(lbTongTien1.getText());
        } else if (Double.parseDouble(txtSoTienGiam1.getText()) >= Double.parseDouble(lbTongTien1.getText())) {
            JOptionPane.showMessageDialog(this, "Số Tiền Giảm Không Lớn Hơn Tổng Tiền");
        } else {
            double thanhTien = 0;
            BigDecimal bgThanhTien = new BigDecimal(Double.toString(thanhTien));
            bgThanhTien = BigDecimal.valueOf(Double.parseDouble(lbTongTien1.getText()))
            .subtract(BigDecimal.valueOf(Double.parseDouble(txtSoTienGiam1.getText())));
            lbThanhTien1.setText(String.valueOf(bgThanhTien));
        }
    }//GEN-LAST:event_txtSoTienGiam1KeyTyped

    private void txtSoTienGiam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoTienGiam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTienGiam1ActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int chonCot = tblHoaDon.getSelectedRow();
        if (chonCot <= 0) {
            JOptionPane.showMessageDialog(this, "Mời Chọn Hóa Đơn Muốn Hủy!!!");
        } else {

            String layGiaTri = (String) tblHoaDon.getValueAt(chonCot, 0);
            hdsv.delete(layGiaTri);
            JOptionPane.showMessageDialog(this, "Hủy Thành Công!!!");
            loadTBDSHD();
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        int chonCot = tblHoaDon.getSelectedRow();
        if (chonCot < 0) {
            JOptionPane.showMessageDialog(this, "Mời Chọn Hóa Đơn Muốn Thanh Toán!!!");
        } else if (txtSoTienGiam.getText().isEmpty() || txtTienDua.getText().isEmpty() || lbThanhTien.getText().isEmpty() || lbTienThua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Số Tiền Thanh Toán!!!");
        } else if (Double.parseDouble(txtSoTienGiam.getText()) < 0 || Double.parseDouble(txtTienDua.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Thông Tin Thanh Toán Lớn Hơn 0!!!");
        } else if (Double.parseDouble(txtTienDua.getText()) < Double.parseDouble(lbThanhTien.getText())) {
            JOptionPane.showMessageDialog(this, "Tiền Đưa Lớn Hơn Số Tiền Cần Trả!!!");
        } else {
            String layGiaTri = (String) tblHoaDon.getValueAt(chonCot, 0);
            hdsv.update(getInputTT(), layGiaTri);
            JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công!!!");
            loadTBDSHD();
            //Update số lượng tồn
            //            int laySLM = (int) tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 7);
            //            int laySLT = (int) tblDSSP.getValueAt(tblDSSP.getSelectedRow(), 8);
            //            int soLuongTon = laySLT - laySLM;
            //
            //            BHSanPhamDM updateSL = new BHSanPhamDM();
            //            updateSL.setSoLuongTon(soLuongTon);
            //            String layMa = (String) tblDSSP.getValueAt(tblDSSP.getSelectedRow(), 0);
            //            spsv.updateSL(updateSL, layMa);
            //            loadTBDSSP();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        hdsv.add(getIputHD());
        loadTBDSHD();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void txtTienDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienDuaKeyTyped
        // TODO add your handling code here:
        if (txtTienDua.getText().trim().isEmpty()) {
            double thanhTien = 0;
            BigDecimal bgThanhTien = new BigDecimal(Double.toString(thanhTien));
            bgThanhTien = BigDecimal.valueOf(Double.parseDouble(lbTongTien.getText()))
            .subtract(BigDecimal.valueOf(Double.parseDouble(txtSoTienGiam.getText())));
            lbThanhTien.setText(String.valueOf(bgThanhTien));
        } else if (Double.parseDouble(txtTienDua.getText()) < Double.parseDouble(lbThanhTien.getText())) {
            lbTienThua.setText("0");
        } else {
            double thanhDu = 0;
            BigDecimal bgthanhDu = new BigDecimal(Double.toString(thanhDu));
            bgthanhDu = BigDecimal.valueOf(Double.parseDouble(txtTienDua.getText()))
            .subtract(BigDecimal.valueOf(Double.parseDouble(lbThanhTien.getText())));
            lbTienThua.setText(String.valueOf(bgthanhDu));
        }
    }//GEN-LAST:event_txtTienDuaKeyTyped

    private void txtSoTienGiamKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoTienGiamKeyTyped
        // TODO add your handling code here:
        if (txtSoTienGiam.getText().trim().isEmpty()) {
            lbThanhTien.setText(lbTongTien.getText());
        } else if (Double.parseDouble(txtSoTienGiam.getText()) >= Double.parseDouble(lbTongTien.getText())) {
            JOptionPane.showMessageDialog(this, "Số Tiền Giảm Không Lớn Hơn Tổng Tiền");
        } else {
            double thanhTien = 0;
            BigDecimal bgThanhTien = new BigDecimal(Double.toString(thanhTien));
            bgThanhTien = BigDecimal.valueOf(Double.parseDouble(lbTongTien.getText()))
            .subtract(BigDecimal.valueOf(Double.parseDouble(txtSoTienGiam.getText())));
            lbThanhTien.setText(String.valueOf(bgThanhTien));
        }
    }//GEN-LAST:event_txtSoTienGiamKeyTyped

    private void txtSoTienGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoTienGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTienGiamActionPerformed

    private void cbHinhThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHinhThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHinhThucActionPerformed

    private void tblGioHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMousePressed
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            popMenuGH.show(tblHoaDon, evt.getX(), evt.getX());
        }
    }//GEN-LAST:event_tblGioHangMousePressed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void tblDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSPMouseClicked
        // TODO add your handling code here:
        //Thông báo số lượng
        String soLuong = JOptionPane.showInputDialog(this, "Mời nhập số lượng!!!");
        int data = Integer.parseInt(soLuong);

        int chonCot = tblDSSP.getSelectedRow();
        int layGiaTri = (int) tblDSSP.getValueAt(chonCot, 8);

        //        String checkInput = (String) tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0);
        if (data > layGiaTri) {
            JOptionPane.showMessageDialog(this, "Nhập số lượng muốn mua nhỏ hơn số lượng tồn!!!");
        } else if (soLuong.trim().isEmpty() || soLuong == "" || data <= 0) {
            JOptionPane.showMessageDialog(this, "Nhập số lượng muốn mua lớn hơn 0");
        } else if (tblHoaDon.getSelectedRowCount() < 0) {
            JOptionPane.showMessageDialog(this, "Chọn Hóa Đơn Cần Thêm Sản Phẩm");
        } else {
            int dongChonIDHD = tblHoaDon.getSelectedRow();
            BHHoaDonVM hoaDonVM = hdsv.findAll().get(dongChonIDHD);
            int dongChonIDCTSP = tblDSSP.getSelectedRow();
            BHSanPhamVM sanPhamVM = spsv.findAll().get(dongChonIDCTSP);
            BHGioHangDM gioHang1 = new BHGioHangDM();
            gioHang1.setIDHoaDon(hoaDonVM.getIDHD());
            gioHang1.setIDCTSP(sanPhamVM.getIDCTSP());
            gioHang1.setSoLuong(data);
            //            Giá Bán
            BigDecimal layGiaSP = (BigDecimal) tblDSSP.getValueAt(chonCot, 7);
            //            Số Lượng * Giá Bán
            BigDecimal getSoLuong = new BigDecimal(data);
            BigDecimal getDonGia = layGiaSP.multiply(getSoLuong);
            gioHang1.setDonGia(getDonGia);
            ghsv.add(gioHang1);
            loadTBDSGH();
            //Update lại số lượng
            //            laySLM = (int) tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 7);
            int laySLT = (int) tblDSSP.getValueAt(tblDSSP.getSelectedRow(), 8);
            int soLuongTon = laySLT - data;

            BHSanPhamDM updateSL = new BHSanPhamDM();
            updateSL.setSoLuongTon(soLuongTon);
            String layMa = (String) tblDSSP.getValueAt(tblDSSP.getSelectedRow(), 0);
            spsv.updateSL(updateSL, layMa);
            loadTBDSSP();
            //Load lại số tiền
            loadTongTien();
            loadSoTienCanTra();
        }
    }//GEN-LAST:event_tblDSSPMouseClicked

    private void rdDoiTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdDoiTraMouseClicked
        // TODO add your handling code here:
        List<BHHoaDonVM> listHD = hdsv.findByTT(6);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "Tên khách hàng", "Trạng thái"});
        for (BHHoaDonVM x : listHD) {
            model.addRow(new Object[]{x.getMaHD(), x.getNgayTao(), x.getTenKH(), x.loadTrangThai()});
        }
        tblHoaDon.setModel(model);
    }//GEN-LAST:event_rdDoiTraMouseClicked

    private void rdDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDaGiaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDaGiaoActionPerformed

    private void rdDaGiaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdDaGiaoMouseClicked
        // TODO add your handling code here:
        List<BHHoaDonVM> listHD = hdsv.findByTT(5);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "Tên khách hàng", "Trạng thái"});
        for (BHHoaDonVM x : listHD) {
            model.addRow(new Object[]{x.getMaHD(), x.getNgayTao(), x.getTenKH(), x.loadTrangThai()});
        }
        tblHoaDon.setModel(model);
    }//GEN-LAST:event_rdDaGiaoMouseClicked

    private void rdDangGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDangGiaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDangGiaoActionPerformed

    private void rdDangGiaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdDangGiaoMouseClicked
        // TODO add your handling code here:
        List<BHHoaDonVM> listHD = hdsv.findByTT(4);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "Tên khách hàng", "Trạng thái"});
        for (BHHoaDonVM x : listHD) {
            model.addRow(new Object[]{x.getMaHD(), x.getNgayTao(), x.getTenKH(), x.loadTrangThai()});
        }
        tblHoaDon.setModel(model);
    }//GEN-LAST:event_rdDangGiaoMouseClicked

    private void rdTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTatCaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdTatCaActionPerformed

    private void rdTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdTatCaMouseClicked
        // TODO add your handling code here:
        loadTBDSHD();
    }//GEN-LAST:event_rdTatCaMouseClicked

    private void rdChuaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChuaThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdChuaThanhToanActionPerformed

    private void rdChuaThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdChuaThanhToanMouseClicked
        // TODO add your handling code here:
        List<BHHoaDonVM> listHD = hdsv.findByTT(2);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "Tên khách hàng", "Trạng thái"});
        for (BHHoaDonVM x : listHD) {
            model.addRow(new Object[]{x.getMaHD(), x.getNgayTao(), x.getTenKH(), x.loadTrangThai()});
        }
        tblHoaDon.setModel(model);
    }//GEN-LAST:event_rdChuaThanhToanMouseClicked

    private void rdThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdThanhToanActionPerformed
        // TODO add your handling code here:
        //        loadStatus();
        //        loadTBDSHD();
        //        hdsv.getOne(1);
        //        loadTBDSHD();
    }//GEN-LAST:event_rdThanhToanActionPerformed

    private void rdThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdThanhToanMouseClicked
        // TODO add your handling code here:
        List<BHHoaDonVM> listHD = hdsv.findByTT(2);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "Tên khách hàng", "Trạng thái"});
        for (BHHoaDonVM x : listHD) {
            model.addRow(new Object[]{x.getMaHD(), x.getNgayTao(), x.getTenKH(), x.loadTrangThai()});
        }
        tblHoaDon.setModel(model);
    }//GEN-LAST:event_rdThanhToanMouseClicked

    //Lấy dữ liệu nhập vào để tạo đơn giao hàng
    private BHHoaDonDM getDataTaoDonGiaoHang() {
        BHHoaDonDM hdGiaoHang = new BHHoaDonDM();
        hdGiaoHang.setTenKH(txtTenKH2.getText());
        hdGiaoHang.setSDTKH(txtSDTKH2.getText());
        hdGiaoHang.setTenShip(txtTenShip.getText());
        hdGiaoHang.setSDTShip(txtSDTShip.getText());
        hdGiaoHang.setDiaChi(txtDiaChi.getText());
//        hdGiaoHang.setNgayMuonNhan(txtNgayKhachMuon.getText());
        hdGiaoHang.setNgayTao(lbngayHienTai1.getText());
        return hdGiaoHang;
    }    private BHHoaDonDM getInputGiaoHang() {
        BHHoaDonDM a = new BHHoaDonDM();
//        a.set
//        a.setThanhTien(BigDecimal.valueOf(Double.parseDouble(lbThanhTien.getText())));
        a.setSoTienGiam(BigDecimal.valueOf(Double.parseDouble(txtSoTienGiam.getText())));
        a.setNgayGiao(lbngayHienTai.getText());
        return a;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.netbeans.modules.form.InvalidComponent btlDSSP1;
    private javax.swing.JButton btnDaGiao;
    private javax.swing.JButton btnDoiTra;
    private javax.swing.JButton btnFindMaKH1;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnTaoHoaDon1;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbHinhThuc;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbThanhTien;
    private javax.swing.JLabel lbThanhTien1;
    private javax.swing.JLabel lbTienThua;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbTongTien1;
    private javax.swing.JLabel lbngayHienTai;
    private javax.swing.JLabel lbngayHienTai1;
    private org.netbeans.modules.form.InvalidComponent panelBorder1;
    private org.netbeans.modules.form.InvalidComponent panelBorder10;
    private org.netbeans.modules.form.InvalidComponent panelBorder11;
    private org.netbeans.modules.form.InvalidComponent panelBorder12;
    private org.netbeans.modules.form.InvalidComponent panelBorder13;
    private org.netbeans.modules.form.InvalidComponent panelBorder2;
    private org.netbeans.modules.form.InvalidComponent panelBorder3;
    private org.netbeans.modules.form.InvalidComponent panelBorder4;
    private org.netbeans.modules.form.InvalidComponent panelBorder5;
    private org.netbeans.modules.form.InvalidComponent panelBorder6;
    private org.netbeans.modules.form.InvalidComponent panelBorder7;
    private org.netbeans.modules.form.InvalidComponent panelBorder8;
    private org.netbeans.modules.form.InvalidComponent panelBorder9;
    private javax.swing.JPanel panelWC;
    private javax.swing.JPopupMenu popMenuGH;
    private javax.swing.JRadioButton rdChuaThanhToan;
    private javax.swing.JRadioButton rdDaGiao;
    private javax.swing.JRadioButton rdDangGiao;
    private javax.swing.JRadioButton rdDoiTra;
    private javax.swing.JRadioButton rdTatCa;
    private javax.swing.JRadioButton rdThanhToan;
    private javax.swing.JScrollPane spTable1;
    private javax.swing.JScrollPane spTable3;
    private javax.swing.JTable tblDSSP;
    private javax.swing.JTable tblGioHang;
    private org.netbeans.modules.form.InvalidComponent tblHoaDon;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private org.netbeans.modules.form.InvalidComponent txtNgayKhachMuon;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtSDTKH2;
    private javax.swing.JTextField txtSDTShip;
    private javax.swing.JTextField txtSanPham;
    private javax.swing.JTextField txtSoTienGiam;
    private javax.swing.JTextField txtSoTienGiam1;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKH1;
    private javax.swing.JTextField txtTenKH2;
    private javax.swing.JTextField txtTenShip;
    private javax.swing.JTextField txtTienDiem;
    private javax.swing.JTextField txtTienDua;
    private javax.swing.JTextField txtTienShip;
    // End of variables declaration//GEN-END:variables

}
