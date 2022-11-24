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

        panelBorder4 = new com.raven.swing.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable3 = new javax.swing.JScrollPane();
        btlDSSP1 = new com.raven.swing.Table();
        buttonGroup1 = new javax.swing.ButtonGroup();
        popMenuGH = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();
        txtSanPham = new javax.swing.JTextField();
        panelBorder2 = new com.raven.swing.PanelBorder();
        jLabel4 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        panelBorder3 = new com.raven.swing.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        spTable1 = new javax.swing.JScrollPane();
        tblHoaDon = new com.raven.swing.Table();
        rdThanhToan = new javax.swing.JRadioButton();
        rdChuaThanhToan = new javax.swing.JRadioButton();
        rdTatCa = new javax.swing.JRadioButton();
        rdDangGiao = new javax.swing.JRadioButton();
        rdDaGiao = new javax.swing.JRadioButton();
        rdDoiTra = new javax.swing.JRadioButton();
        panelBorder5 = new com.raven.swing.PanelBorder();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelBorder7 = new com.raven.swing.PanelBorder();
        panelBorder8 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lbngayHienTai = new javax.swing.JLabel();
        txtTenKH1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtTienDiem = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        panelBorder9 = new com.raven.swing.PanelBorder();
        jLabel9 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbHinhThuc = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSoTienGiam = new javax.swing.JTextField();
        lbTienThua = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbThanhTien = new javax.swing.JLabel();
        txtTienDua = new javax.swing.JTextField();
        panelBorder10 = new com.raven.swing.PanelBorder();
        btnTaoHoaDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panelBorder11 = new com.raven.swing.PanelBorder();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTenKH2 = new javax.swing.JTextField();
        txtSDTKH2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lbngayHienTai1 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        btnFindMaKH1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTenShip = new javax.swing.JTextField();
        txtSDTShip = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtNgayKhachMuon = new com.github.lgooddatepicker.components.DatePicker();
        panelBorder12 = new com.raven.swing.PanelBorder();
        jLabel22 = new javax.swing.JLabel();
        lbTongTien1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtSoTienGiam1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lbThanhTien1 = new javax.swing.JLabel();
        txtTienShip = new javax.swing.JTextField();
        panelBorder13 = new com.raven.swing.PanelBorder();
        btnTaoHoaDon1 = new javax.swing.JButton();
        btnThanhToan1 = new javax.swing.JButton();
        btnDoiTra = new javax.swing.JButton();
        btnDaGiao = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        panelBorder6 = new com.raven.swing.PanelBorder();
        jLabel7 = new javax.swing.JLabel();
        panelWC = new javax.swing.JPanel();

        panelBorder4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Danh Sách Sản Phẩm");

        spTable3.setBorder(null);

        btlDSSP1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email", "User Type", "Joined", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable3.setViewportView(btlDSSP1);

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable3)
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable3, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Xoa.png"))); // NOI18N
        jMenuItem2.setText("Xóa");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        popMenuGH.add(jMenuItem2);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("Danh Sách Sản Phẩm");

        tblDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSSP);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelBorder2MouseReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 127, 127));
        jLabel4.setText("Giỏ Hàng");

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Xoa.png"))); // NOI18N
        btnXoa.setText("Xóa tất cả");

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Tên Sản Phẩm", "Tên Loại", "Chất Liệu", "Màu Sắc", "Size", "Xuất Xứ", "Số Lượng", "Đơn Giá"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblGioHangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(btnXoa))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa)
                .addContainerGap())
        );

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 127, 127));
        jLabel3.setText("Danh Sách Hóa Đơn");

        spTable1.setBorder(null);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email", "User Type", "Joined", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseReleased(evt);
            }
        });
        spTable1.setViewportView(tblHoaDon);

        buttonGroup1.add(rdThanhToan);
        rdThanhToan.setText("Chưa Thanh Toán");
        rdThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdThanhToanMouseClicked(evt);
            }
        });
        rdThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdThanhToanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdChuaThanhToan);
        rdChuaThanhToan.setText("Chưa Hoàn Thành");
        rdChuaThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdChuaThanhToanMouseClicked(evt);
            }
        });
        rdChuaThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChuaThanhToanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdTatCa);
        rdTatCa.setText("Tất Cả");
        rdTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdTatCaMouseClicked(evt);
            }
        });
        rdTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTatCaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdDangGiao);
        rdDangGiao.setText("Đơn Đang Giao");
        rdDangGiao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdDangGiaoMouseClicked(evt);
            }
        });
        rdDangGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDangGiaoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdDaGiao);
        rdDaGiao.setText("Đơn Giao Thành Công");
        rdDaGiao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdDaGiaoMouseClicked(evt);
            }
        });
        rdDaGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDaGiaoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdDoiTra);
        rdDoiTra.setText("Đơn Đổi Trả");
        rdDoiTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdDoiTraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdTatCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdChuaThanhToan))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rdDangGiao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdDaGiao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdDoiTra)))
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdThanhToan)
                        .addComponent(rdChuaThanhToan)
                        .addComponent(rdTatCa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdDangGiao)
                    .addComponent(rdDaGiao)
                    .addComponent(rdDoiTra))
                .addContainerGap())
        );

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 127, 127));
        jLabel6.setText("Tạo Hóa Đơn");

        panelBorder8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Tên Khách Hàng");

        jLabel8.setText("Số Điện Thoại");

        jLabel11.setText("Mã Khách Hàng");

        lbngayHienTai.setText("jLabel12");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/find.png"))); // NOI18N

        jLabel28.setText("Số Tiền Điểm");

        javax.swing.GroupLayout panelBorder8Layout = new javax.swing.GroupLayout(panelBorder8);
        panelBorder8.setLayout(panelBorder8Layout);
        panelBorder8Layout.setHorizontalGroup(
            panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder8Layout.createSequentialGroup()
                .addGroup(panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(panelBorder8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(txtSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(txtTienDiem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                    .addGroup(panelBorder8Layout.createSequentialGroup()
                        .addComponent(txtTenKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
            .addGroup(panelBorder8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbngayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder8Layout.setVerticalGroup(
            panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbngayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenKH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(12, 12, 12)
                .addGroup(panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelBorder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(4, 4, 4))
        );

        panelBorder9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Tổng Tiền");

        lbTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel13.setText("Hình Thức");

        cbHinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "a", "b" }));
        cbHinhThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHinhThucActionPerformed(evt);
            }
        });

        jLabel14.setText("Tiền Đưa");

        jLabel15.setText("Tiền Dư");

        txtSoTienGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoTienGiamActionPerformed(evt);
            }
        });
        txtSoTienGiam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoTienGiamKeyTyped(evt);
            }
        });

        lbTienThua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTienThua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel12.setText("Số Tiền Giảm");

        jLabel18.setText("Số Tiền Cần Trả");

        lbThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbThanhTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtTienDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienDuaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelBorder9Layout = new javax.swing.GroupLayout(panelBorder9);
        panelBorder9.setLayout(panelBorder9Layout);
        panelBorder9Layout.setHorizontalGroup(
            panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder9Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder9Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder9Layout.createSequentialGroup()
                        .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTienThua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder9Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbHinhThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienDua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder9Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoTienGiam)))
                .addContainerGap())
        );
        panelBorder9Layout.setVerticalGroup(
            panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder9Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtSoTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(lbThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBorder10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTaoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/CreateHoaDon.png"))); // NOI18N
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Pay.png"))); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Cancel.png"))); // NOI18N
        btnHuy.setText("Hủy Đơn");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder10Layout = new javax.swing.GroupLayout(panelBorder10);
        panelBorder10.setLayout(panelBorder10Layout);
        panelBorder10Layout.setHorizontalGroup(
            panelBorder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder10Layout.createSequentialGroup()
                .addGroup(panelBorder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder10Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder10Layout.setVerticalGroup(
            panelBorder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder10Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelBorder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon)
                    .addComponent(btnHuy))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout panelBorder7Layout = new javax.swing.GroupLayout(panelBorder7);
        panelBorder7.setLayout(panelBorder7Layout);
        panelBorder7Layout.setHorizontalGroup(
            panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelBorder9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder10, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelBorder7Layout.setVerticalGroup(
            panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", panelBorder7);

        panelBorder11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Tên Khách Hàng");

        jLabel16.setText("Số Điện Thoại");

        jLabel17.setText("Mã Khách Hàng");

        lbngayHienTai1.setText("jLabel12");

        btnFindMaKH1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/find.png"))); // NOI18N

        jLabel19.setText("Địa Chỉ");

        jLabel20.setText("Tên Shipper");

        jLabel21.setText("SĐT Shipper");

        jLabel24.setText("Ngày Muốn");

        javax.swing.GroupLayout panelBorder11Layout = new javax.swing.GroupLayout(panelBorder11);
        panelBorder11.setLayout(panelBorder11Layout);
        panelBorder11Layout.setHorizontalGroup(
            panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder11Layout.createSequentialGroup()
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnFindMaKH1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addGroup(panelBorder11Layout.createSequentialGroup()
                            .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbngayHienTai1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelBorder11Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTenKH2))
                                .addGroup(panelBorder11Layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSDTKH2))
                                .addGroup(panelBorder11Layout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDiaChi))
                                .addGroup(panelBorder11Layout.createSequentialGroup()
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTenShip))
                                .addGroup(panelBorder11Layout.createSequentialGroup()
                                    .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSDTShip, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(panelBorder11Layout.createSequentialGroup()
                        .addComponent(txtNgayKhachMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelBorder11Layout.setVerticalGroup(
            panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbngayHienTai1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFindMaKH1))
                .addGap(12, 12, 12)
                .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtTenKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtSDTKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTenShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtSDTShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayKhachMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        panelBorder12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setText("Tổng Tiền");

        lbTongTien1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTongTien1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel23.setText("Số Tiền Cần Trả");

        txtSoTienGiam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoTienGiam1ActionPerformed(evt);
            }
        });
        txtSoTienGiam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoTienGiam1KeyTyped(evt);
            }
        });

        jLabel26.setText("Số Tiền Giảm");

        jLabel27.setText("Tiền Ship");

        lbThanhTien1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbThanhTien1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtTienShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienShipActionPerformed(evt);
            }
        });
        txtTienShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienShipKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelBorder12Layout = new javax.swing.GroupLayout(panelBorder12);
        panelBorder12.setLayout(panelBorder12Layout);
        panelBorder12Layout.setHorizontalGroup(
            panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder12Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(12, 12, 12)
                        .addComponent(lbThanhTien1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder12Layout.createSequentialGroup()
                        .addGroup(panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26)
                            .addComponent(jLabel22))
                        .addGap(24, 24, 24)
                        .addGroup(panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSoTienGiam1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTongTien1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienShip, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelBorder12Layout.setVerticalGroup(
            panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(12, 12, 12)
                .addGroup(panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtSoTienGiam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtTienShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(lbThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBorder13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTaoHoaDon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/CreateHoaDon.png"))); // NOI18N
        btnTaoHoaDon1.setText("Tạo Đơn");
        btnTaoHoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDon1ActionPerformed(evt);
            }
        });

        btnThanhToan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Ship.png"))); // NOI18N
        btnThanhToan1.setText("Giao Hàng");
        btnThanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan1ActionPerformed(evt);
            }
        });

        btnDoiTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Tra.png"))); // NOI18N
        btnDoiTra.setText("Đổi Trả");
        btnDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTraActionPerformed(evt);
            }
        });

        btnDaGiao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Done.png"))); // NOI18N
        btnDaGiao.setText("Đã Giao");
        btnDaGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaGiaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder13Layout = new javax.swing.GroupLayout(panelBorder13);
        panelBorder13.setLayout(panelBorder13Layout);
        panelBorder13Layout.setHorizontalGroup(
            panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDaGiao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaoHoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(btnDoiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        panelBorder13Layout.setVerticalGroup(
            panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon1)
                    .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder13Layout.createSequentialGroup()
                        .addComponent(btnDaGiao)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnDoiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBorder11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelBorder12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jTabbedPane1.addTab("Đặt Hàng", jPanel1);

        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/in.png"))); // NOI18N

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(btnInHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        panelBorder6.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(127, 127, 127));
        jLabel7.setText("Cam");
        panelBorder6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 8, -1, -1));

        panelWC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelWC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelBorder6.add(panelWC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBorder6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        hdsv.add(getIputHD());
        loadTBDSHD();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void rdTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTatCaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdTatCaActionPerformed

    private void rdThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdThanhToanActionPerformed
        // TODO add your handling code here:
//        loadStatus();
//        loadTBDSHD();
//        hdsv.getOne(1);
//        loadTBDSHD();
    }//GEN-LAST:event_rdThanhToanActionPerformed

    private void rdChuaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChuaThanhToanActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_rdChuaThanhToanActionPerformed

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void panelBorder2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBorder2MouseReleased

    private void tblHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseReleased

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

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblGioHangMouseClicked

    private void tblGioHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMousePressed
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            popMenuGH.show(tblHoaDon, evt.getX(), evt.getX());
        }
    }//GEN-LAST:event_tblGioHangMousePressed

    private void rdTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdTatCaMouseClicked
        // TODO add your handling code here:
        loadTBDSHD();
    }//GEN-LAST:event_rdTatCaMouseClicked

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
    private BHHoaDonDM getTrangThaiHD() {
        int chonCot = tblHoaDon.getSelectedRow();
        int layGiaTri = (int) tblHoaDon.getValueAt(chonCot, 3);
        BHHoaDonDM hoaDon1 = new BHHoaDonDM();
        hoaDon1.setTrangThai(layGiaTri);
        return hoaDon1;
    }
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

    private void cbHinhThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHinhThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHinhThucActionPerformed

    private void txtSoTienGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoTienGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTienGiamActionPerformed

    private void tblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseEntered

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

    private void txtSoTienGiam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoTienGiam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTienGiam1ActionPerformed

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
    }
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
    private BHHoaDonDM getInputGiaoHang() {
        BHHoaDonDM a = new BHHoaDonDM();
//        a.set
//        a.setThanhTien(BigDecimal.valueOf(Double.parseDouble(lbThanhTien.getText())));
        a.setSoTienGiam(BigDecimal.valueOf(Double.parseDouble(txtSoTienGiam.getText())));
        a.setNgayGiao(lbngayHienTai.getText());
        return a;
    }
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

    private void btnDoiTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoiTraActionPerformed

    private void btnDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGiaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDaGiaoActionPerformed

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

    private void rdDangGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDangGiaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDangGiaoActionPerformed

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

    private void rdDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDaGiaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDaGiaoActionPerformed

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

    private void txtTienShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienShipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienShipActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Table btlDSSP1;
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
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder10;
    private com.raven.swing.PanelBorder panelBorder11;
    private com.raven.swing.PanelBorder panelBorder12;
    private com.raven.swing.PanelBorder panelBorder13;
    private com.raven.swing.PanelBorder panelBorder2;
    private com.raven.swing.PanelBorder panelBorder3;
    private com.raven.swing.PanelBorder panelBorder4;
    private com.raven.swing.PanelBorder panelBorder5;
    private com.raven.swing.PanelBorder panelBorder6;
    private com.raven.swing.PanelBorder panelBorder7;
    private com.raven.swing.PanelBorder panelBorder8;
    private com.raven.swing.PanelBorder panelBorder9;
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
    private com.raven.swing.Table tblHoaDon;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private com.github.lgooddatepicker.components.DatePicker txtNgayKhachMuon;
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
