/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DomainModel.CTSanPham;
import DomainModel.KhachHang;
import DomainModel.NhanVien;
import DomainModel.SanPham;
import repo.NhanVienReponsitory;
import service.CTSanPhamService;

import service.KhachHangService;
import ViewModel.SanPhamViewmodel;

import serviceImpl.CTSanPhamServiceImpl;
import serviceImpl.KhachHangServiceImpl;
import serviceImpl.NhanVienServiceImpl;
import serviceImpl.SanPhamServiceImpl;

import util.DBContex2;
import ViewModel.CTSanPhamViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class FromMenu extends javax.swing.JFrame {
     private SanPhamServiceImpl spService = new SanPhamServiceImpl();
     private CTSanPhamService ctspservice = new CTSanPhamServiceImpl();

    private KhachHangService khService = new KhachHangServiceImpl();
    ButtonGroup buttonGroupg = new ButtonGroup();
    ButtonGroup buttonGroupt = new ButtonGroup();
    private NhanVienServiceImpl Service;
    private DefaultTableModel defaultTableModel;
    private String idwhenclick;
    private NhanVienReponsitory nhanVienReponsitory = new NhanVienReponsitory();

    /**
     * Creates new form FromMenu
     */
    public FromMenu() {
        initComponents();
        rdGioiTinh();
        rdTrangThai();
        getData();
        this.setLocationRelativeTo(null);
        Service = new NhanVienServiceImpl();
        LoadTable();
        getDataCTSP();
        this.getDataSP();
        this.chayChu();
    }
    
    
    //
        public void chayChu() {
        Thread t1 = new Thread() {
            public void run() {
                String txt = lblChu.getText() + " ";
                while (true) {
                    txt = txt.charAt(txt.length() - 1) + txt.substring(0, txt.length() - 1);
                    try {
                        sleep(350);
                    } catch (InterruptedException ex) {
                        //Logger.getLoggger(FormMenuChinh.getName()).log(Level.SEVERE,null,ex);
                    }
                    lblChu.setText(txt);
                }
            }
        };
        t1.start();
    }
    //
    
    //
         public void getDataSP() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblSP.getModel();
        dtm.setRowCount(0);
        for (ViewModel.SanPhamViewmodel s : this.spService.getAll()) {
            Object[] rowData = {
                s.getId(), s.getMa(), s.getTen()
            };
            dtm.addRow(rowData);
        }
    }
         
         private SanPham getLoadDataSP() {
        String id = this.txt_ID.getText();
        String ma = this.txtMa.getText().trim();
        String ten = this.txtTen.getText().trim();

        SanPham s = new SanPham(id, ma, ten);
        return s;
    }
    //
    
 //   
    
    public void getDataCTSP() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbllCTSP.getModel();
        dtm.setRowCount(0);
        for (CTSanPhamViewModel ctsp : this.ctspservice.getall()) {
            Object[] rowData = {
                ctsp.getId(), ctsp.getNamBH(), ctsp.getMoTa(), ctsp.getSoLuongTon(), ctsp.getGiaNhap(), ctsp.getGiaBan()
            };
            dtm.addRow(rowData);
        }
    }
    
    private CTSanPham getLoadDataCTSP() {
        String id = this.lblid1CTSP.getText();
        String namBH = this.txtNamBHCTSP.getText().trim();
        String moTa = this.txamotaCTSP.getText().trim();
        String soLuong = this.txtsoluong1CTSP.getText().trim();
        String giaNhap = this.txtgianhap1CTSP.getText().trim();
        String giaBan = this.txtgiaban1CTSP.getText().trim();
        CTSanPham s = new CTSanPham(id, namBH, moTa, soLuong, giaNhap, giaBan);
        return s;
    }

    private void clearCTSP() {
        lblid1CTSP.setText("");
        txtNamBHCTSP.setText("");
        txamotaCTSP.setText("");
        txtsoluong1CTSP.setText("");
        txtgianhap1CTSP.setText("");
        txtgiaban1CTSP.setText("");
    }
    
//
    public void LoadTable() {
        defaultTableModel = (DefaultTableModel) tblbangNv.getModel();
        defaultTableModel.setRowCount(0);
        for (ViewModel.NhanVienViewModel x : Service.GetAll()) {
            defaultTableModel.addRow(new Object[]{
                x.getId(), x.getManv(), x.getTennv(), x.getTendemNV(), x.getHoNV(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), TrangThai(x.getTrangThai())
            });
        }
    }

    public String TrangThai(int tt) {
        if (tt == 0) {
            return "Đi làm";
        } else if (tt == 1) {
            return "Nghỉ làm";
        } else {
            return null;
        }
    }

    private boolean checkMaTrung(String ma) {
        try {
            Connection conn = DBContex2.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM NHANVIEN WHERE Ma = ?");
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi kết nối tại checkMaTrung()");
            e.printStackTrace();
        }
        return false;
    }

    public NhanVien GetDataFromGui() {
        String id = this.lblID.getText();
        String ma = this.txtMaNv.getText().trim();
        String ten = this.txtTenNv.getText().trim();
        String tenDem = this.txtTenDemNv.getText().trim();
        String ho = this.txtHoNv.getText().trim();
        String ngaySinh = this.txtNgaySinhNv.getText().trim();
        String diaChi = this.txtDiaChiNv.getText().trim();
        String sdt = this.txtSdtNv.getText().trim();
        int gioitinh;

        if (rdNamNv.isSelected()) {
            gioitinh = 0;
        } else {
            gioitinh = 1;
        }
        int trangThai;
        if (rdDiLamNv.isSelected()) {
            trangThai = 0;
        } else {
            trangThai = 1;
        }

        if (ten.equals("") || tenDem.equals("") || ho.equals("") || diaChi.equals("") || sdt.equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }
        if (ma.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống");
            return null;
        }

        if (ngaySinh.equals("")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
            return null;
        }
        if (!ngaySinh.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (yyyy-MM-dd)");
            return null;
        }
        NhanVien nv = new NhanVien(id, ma, ten, tenDem, ho, ngaySinh, gioitinh, diaChi, sdt, trangThai);
        return nv;

    }

    boolean testdata() {
        if (txtMaNv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không dược để trống");
            return false;
        }
        if (txtTenNv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống");
            return false;
        }
        if (txtTenDemNv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đệm không được để trống");
            return false;
        }
        if (txtHoNv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ không được để trống");
            return false;
        }
        if (txtNgaySinhNv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
            return false;
        }
        if (!txtNgaySinhNv.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (yyyy-MM-dd)");
            return false;
        }
        if (txtDiaChiNv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
            return false;
        }
        if (txtSdtNv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
            return false;
        }
        if (!txtSdtNv.getText().matches("0\\d{9}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng (0xxxxxxxxx)");
            return false;
        }
        return true;
    }

    public String getGioiTinh(int gioiTinh) {
        if (gioiTinh == 1) {
            return "Nam";
        } else {
            return "Nữ";
        }

    }

    public String getTrangThai(int trangThai) {
        if (trangThai == 1) {
            return "Khách Hàng Cũ";
        } else if (trangThai == 2) {
            return "Khách Hàng Mới";
        } else {
            return null;
        }
    }

    public void rdGioiTinh() {
        buttonGroupg.add(rdNamKh);
        buttonGroupg.add(rdNuKh);
    }

    public void rdTrangThai() {
        buttonGroupt.add(rdCuKh);
        buttonGroupt.add(rdMoiKh);
    }

    public void getData() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbKhachHang.getModel();
        dtm.setRowCount(0);
        for (ViewModel.KhachHangVM kh : this.khService.getall()) {
            Object[] rowData = {
                kh.getId(), kh.getMaKH(), kh.getTenKH(), getGioiTinh(kh.getGioiTinh()), kh.getSdt(), kh.getDiaChi(), getTrangThai(kh.getTrangThai())
            };
            dtm.addRow(rowData);
        }
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        btnSanPham = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        lblMo = new javax.swing.JLabel();
        btnNhanVien = new javax.swing.JButton();
        btnCTSP = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        NV = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblChu = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnThemSP = new javax.swing.JButton();
        txtMa = new javax.swing.JTextField();
        btnSuaSP = new javax.swing.JButton();
        txt_ID = new javax.swing.JTextField();
        btnXoaSP = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        lblid1CTSP = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtNamBHCTSP = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        txamotaCTSP = new javax.swing.JTextArea();
        txtsoluong1CTSP = new javax.swing.JTextField();
        txtgiaban1CTSP = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btnthemCTSP = new javax.swing.JButton();
        btnsuaCTSP = new javax.swing.JButton();
        btnxoaCTSP = new javax.swing.JButton();
        txtgianhap1CTSP = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbllCTSP = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        txtHoNv = new javax.swing.JTextField();
        btnSuaNv = new javax.swing.JButton();
        txtDiaChiNv = new javax.swing.JTextField();
        btnXoaNv = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblbangNv = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtSdtNv = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        rdNamNv = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtTenDemNv = new javax.swing.JTextField();
        rdNuNv = new javax.swing.JRadioButton();
        jLabel40 = new javax.swing.JLabel();
        lblINv = new javax.swing.JLabel();
        txtNgaySinhNv = new javax.swing.JTextField();
        rdDiLamNv = new javax.swing.JRadioButton();
        txtMaNv = new javax.swing.JTextField();
        rdNghiLamNv = new javax.swing.JRadioButton();
        txtTenNv = new javax.swing.JTextField();
        btnThemNv = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtMaKh = new javax.swing.JTextField();
        txtTenKh = new javax.swing.JTextField();
        rdNamKh = new javax.swing.JRadioButton();
        rdNuKh = new javax.swing.JRadioButton();
        txtDCKh = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        rdCuKh = new javax.swing.JRadioButton();
        rdMoiKh = new javax.swing.JRadioButton();
        lblIdKh = new javax.swing.JLabel();
        btnThemKh = new javax.swing.JButton();
        btnSuaKh = new javax.swing.JButton();
        btnXoaKh = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbhd = new javax.swing.JTable();
        txttimkiem2 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        txttimkiem3 = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbhdct = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbgiohang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        txtTimkiemDssp = new javax.swing.JTextField();
        txtSize = new javax.swing.JTextField();
        txtMS = new javax.swing.JTextField();
        txtNCC = new javax.swing.JTextField();
        txtCL = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btntaohd = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbKhachHang = new javax.swing.JComboBox<KhachHangVM>();
        cbNhanVien = new javax.swing.JComboBox<NhanVienViewModel>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblsdtkhachhang = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        lblTienThanhToan = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblNgayTao = new javax.swing.JLabel();
        btnLamMoi = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbhdc = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDisplay = new javax.swing.JTextArea();
        btnInHD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));

        btnSanPham.setBackground(new java.awt.Color(102, 255, 204));
        btnSanPham.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnSanPham.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\brand-identity (1).png")); // NOI18N
        btnSanPham.setText("Sản Phẩm");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnBanHang.setBackground(new java.awt.Color(102, 255, 204));
        btnBanHang.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnBanHang.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\pngtree-shopping-cart-icon-design-image_1071385.png")); // NOI18N
        btnBanHang.setText("Bán Hàng");
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(102, 255, 204));
        btnKhachHang.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnKhachHang.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("file:/F:/Agile/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/src/Icon/Users.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        btnKhachHang.setText("Khách Hàng");
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(102, 255, 204));
        btnDangXuat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\icons8_shutdown_25px.png")); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(102, 255, 204));
        btnExit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\Exit.png")); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnHoaDon.setBackground(new java.awt.Color(102, 255, 204));
        btnHoaDon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnHoaDon.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\Price list.png")); // NOI18N
        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(102, 255, 204));
        btnNhanVien.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnNhanVien.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\User.png")); // NOI18N
        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnCTSP.setBackground(new java.awt.Color(102, 255, 204));
        btnCTSP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnCTSP.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\List.png")); // NOI18N
        btnCTSP.setText("Chi Tiết SP");
        btnCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTSPActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\logo.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMo, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
            .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        NV.setBackground(new java.awt.Color(153, 51, 255));
        NV.setForeground(new java.awt.Color(255, 0, 51));

        lblChu.setBackground(new java.awt.Color(102, 255, 255));
        lblChu.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        lblChu.setText("Hệ Thống Quản Lý Cửa Hàng Quần Áo ");

        jLabel5.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\anh-cua-hang-thoi-trang-12.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(274, Short.MAX_VALUE)
                .addComponent(lblChu)
                .addGap(196, 196, 196))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblChu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        NV.addTab("Home", jPanel11);

        jPanel25.setBackground(new java.awt.Color(0, 255, 204));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(255, 255, 0))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("MãSP");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("TênSP");

        btnThemSP.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("file:/F:/Agile/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/src/Icon/Add.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\icons8_edit_25px.png")); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnXoaSP.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\Delete.png")); // NOI18N
        btnXoaSP.setText("Xóa");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MãSP", "TênSP"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSP);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(118, 118, 118)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemSP)
                            .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnThemSP)
                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSP))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        NV.addTab("SP", jPanel21);

        jPanel20.setBackground(new java.awt.Color(102, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Sản Phẩm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(255, 255, 0))); // NOI18N

        jLabel42.setText("Mô tả:");

        lblid1CTSP.setText("-");

        jLabel44.setText("ID:");

        jLabel45.setText("Nam BH:");

        txamotaCTSP.setColumns(20);
        txamotaCTSP.setRows(5);
        jScrollPane9.setViewportView(txamotaCTSP);

        txtsoluong1CTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsoluong1CTSPActionPerformed(evt);
            }
        });

        jLabel46.setText("Gía Nhập:");

        jLabel47.setText("Số lượng:");

        btnthemCTSP.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("file:/F:/Agile/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/src/Icon/Add.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        btnthemCTSP.setText("Thêm");
        btnthemCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemCTSPActionPerformed(evt);
            }
        });

        btnsuaCTSP.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\icons8_edit_25px.png")); // NOI18N
        btnsuaCTSP.setText("Sửa");
        btnsuaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaCTSPActionPerformed(evt);
            }
        });

        btnxoaCTSP.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\Delete.png")); // NOI18N
        btnxoaCTSP.setText("Xóa");
        btnxoaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaCTSPActionPerformed(evt);
            }
        });

        jLabel48.setText("Gía bán:");

        tbllCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, "", "", null},
                {"", null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nam BH", "Mô tả", "Số Lượng", "Gía Nhập", "Gía Bán"
            }
        ));
        tbllCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbllCTSPMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbllCTSP);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamBHCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblid1CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsoluong1CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtgiaban1CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(txtgianhap1CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnthemCTSP)
                        .addGap(21, 21, 21)
                        .addComponent(btnsuaCTSP)
                        .addGap(41, 41, 41)
                        .addComponent(btnxoaCTSP))
                    .addComponent(jScrollPane10))
                .addContainerGap(368, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lblid1CTSP))
                .addGap(33, 33, 33)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtNamBHCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsoluong1CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtgiaban1CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtgianhap1CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(btnthemCTSP)
                    .addComponent(btnsuaCTSP)
                    .addComponent(btnxoaCTSP))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        NV.addTab("CTSP", jPanel19);

        jPanel18.setBackground(new java.awt.Color(51, 255, 204));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân Viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(255, 51, 0))); // NOI18N

        btnSuaNv.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\icons8_edit_25px.png")); // NOI18N
        btnSuaNv.setText("Sửa");
        btnSuaNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNvActionPerformed(evt);
            }
        });

        btnXoaNv.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\Delete.png")); // NOI18N
        btnXoaNv.setText("Xóa");
        btnXoaNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNvActionPerformed(evt);
            }
        });

        jLabel31.setText("Địa Chỉ");

        tblbangNv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MãNV", "TênNV", "Tên Đệm", "HọNV", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "Trạng Thái"
            }
        ));
        tblbangNv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangNvMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblbangNv);

        jLabel32.setText("Id");

        jLabel33.setText("SDT");

        jLabel34.setText("MãNV");

        jLabel35.setText("Trạng Thái");

        jLabel36.setText("HọNV");

        jLabel37.setText("TênNV");

        buttonGroup1.add(rdNamNv);
        rdNamNv.setText("Nam");

        jLabel38.setText("Tên đệm");

        jLabel39.setText("Giới Tính");

        buttonGroup1.add(rdNuNv);
        rdNuNv.setText("Nữ");

        jLabel40.setText("Ngày Sinh");

        lblINv.setText("-");

        buttonGroup2.add(rdDiLamNv);
        rdDiLamNv.setText("Đi Làm");

        txtMaNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNvActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdNghiLamNv);
        rdNghiLamNv.setText("Nghỉ Làm");

        btnThemNv.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("file:/F:/Agile/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/src/Icon/Add.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        btnThemNv.setText("Thêm");
        btnThemNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel40))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgaySinhNv, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblINv, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaNv, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenNv, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenDemNv, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHoNv, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(109, 109, 109)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel35)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdDiLamNv)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdNghiLamNv)
                                                .addGap(0, 7, Short.MAX_VALUE))
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel39)
                                                    .addComponent(jLabel31)
                                                    .addComponent(jLabel33))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtSdtNv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDiaChiNv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(rdNamNv)))))))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(btnThemNv)
                                .addGap(96, 96, 96)
                                .addComponent(btnSuaNv)
                                .addGap(111, 111, 111)
                                .addComponent(btnXoaNv)))
                        .addComponent(rdNuNv)
                        .addGap(0, 394, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jScrollPane8)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(lblINv))
                .addGap(33, 33, 33)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtMaNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(rdNamNv)
                    .addComponent(rdNuNv))
                .addGap(36, 36, 36)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtTenNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(txtDiaChiNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtTenDemNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(txtSdtNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtHoNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(rdDiLamNv)
                    .addComponent(rdNghiLamNv))
                .addGap(41, 41, 41)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtNgaySinhNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNv)
                    .addComponent(btnSuaNv)
                    .addComponent(btnXoaNv))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        NV.addTab("NV", jPanel18);

        jPanel14.setBackground(new java.awt.Color(102, 255, 255));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 102, 0));
        jLabel29.setText("Khách Hàng");

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel12.setBackground(new java.awt.Color(0, 255, 204));

        jLabel22.setBackground(new java.awt.Color(51, 51, 51));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("ID:");

        buttonGroup2.add(rdNamKh);
        rdNamKh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdNamKh.setForeground(new java.awt.Color(51, 51, 51));
        rdNamKh.setSelected(true);
        rdNamKh.setText("Nam");

        buttonGroup2.add(rdNuKh);
        rdNuKh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdNuKh.setForeground(new java.awt.Color(51, 51, 51));
        rdNuKh.setText("Nữ");

        buttonGroup1.add(rdCuKh);
        rdCuKh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdCuKh.setForeground(new java.awt.Color(51, 51, 51));
        rdCuKh.setSelected(true);
        rdCuKh.setText("Khách Hàng Cũ");
        rdCuKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdCuKhActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdMoiKh);
        rdMoiKh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdMoiKh.setForeground(new java.awt.Color(51, 51, 51));
        rdMoiKh.setText("Khách Hàng Mới");

        lblIdKh.setText("-");

        btnThemKh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemKh.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("file:/F:/Agile/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG/src/Icon/Add.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        btnThemKh.setText("Thêm");
        btnThemKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhActionPerformed(evt);
            }
        });

        btnSuaKh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaKh.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\icons8_edit_25px.png")); // NOI18N
        btnSuaKh.setText("Sửa");
        btnSuaKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKhActionPerformed(evt);
            }
        });

        btnXoaKh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaKh.setIcon(new javax.swing.ImageIcon("F:\\Agile\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\PMA101_ALIGENHOM5_QUANLYKHOHANGTHOITRANG\\src\\Icon\\Delete.png")); // NOI18N
        btnXoaKh.setText("Xóa");
        btnXoaKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("MaKH:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("TenKH:");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("SDT:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Giới Tính:");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Địa Chỉ:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Trạng Thái:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnThemKh, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addGap(62, 62, 62)
                        .addComponent(btnSuaKh, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addGap(111, 111, 111)
                        .addComponent(btnXoaKh, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(jLabel22))
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(rdNamKh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdNuKh))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(rdCuKh)
                                .addGap(18, 18, 18)
                                .addComponent(rdMoiKh))
                            .addComponent(lblIdKh, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTenKh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addComponent(txtMaKh, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtDCKh, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(290, 290, 290))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lblIdKh))
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(29, 29, 29)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdNamKh)
                    .addComponent(rdNuKh)
                    .addComponent(jLabel26))
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDCKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdCuKh)
                    .addComponent(rdMoiKh)
                    .addComponent(jLabel28))
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKh)
                    .addComponent(btnSuaKh)
                    .addComponent(btnXoaKh))
                .addContainerGap(398, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cập Nhật Khách Hàng", jPanel12);

        jPanel9.setBackground(new java.awt.Color(0, 255, 204));

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã KH", "Tên KH", "Giới Tính", "SĐT", "Địa Chỉ", "Trạng Thái"
            }
        ));
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbKhachHang);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(305, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh Sách Khách Hàng", jPanel9);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(435, 435, 435)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        NV.addTab("KH", jPanel14);

        jPanel22.setBackground(new java.awt.Color(102, 255, 255));

        jPanel23.setBackground(new java.awt.Color(204, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        tbhd.setBackground(new java.awt.Color(204, 255, 255));
        tbhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên KH", "Tên NV", "MaHD", "NgayThanhToan", "TrangThai"
            }
        ));
        tbhd.setGridColor(new java.awt.Color(204, 255, 255));
        jScrollPane11.setViewportView(tbhd);

        txttimkiem2.setText("Tìm kiếm ...");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txttimkiem2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(204, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        txttimkiem3.setText("Tìm kiếm ...");

        tbhdct.setBackground(new java.awt.Color(204, 255, 255));
        tbhdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Hóa Đơn", "ID Chi Tiết SP", "Số lượng ", "Đơn giá"
            }
        ));
        jScrollPane12.setViewportView(tbhdct);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(txttimkiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txttimkiem3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(389, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        NV.addTab("HD", jPanel22);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        tbgiohang.setBackground(new java.awt.Color(204, 255, 255));
        tbgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID", "Tên SP", "Số lượng ", "Đơn giá", "Thành tiền"
            }
        ));
        tbgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbgiohangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbgiohang);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách SP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        tbSanPham.setBackground(new java.awt.Color(204, 255, 255));
        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID SP", "Sản Phẩm", "Size", "Màu Sắc", "Nhà Cung Cấp", "Chất Liệu", "Số Lượng", "Giá Bán"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSanPham);

        txtTimkiemDssp.setText("Tìm kiếm ...");
        txtTimkiemDssp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimkiemDsspCaretUpdate(evt);
            }
        });
        txtTimkiemDssp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimkiemDsspMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTimkiemDsspMouseExited(evt);
            }
        });

        txtSize.setText("Tim Kiem Size");
        txtSize.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSizeCaretUpdate(evt);
            }
        });
        txtSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSizeMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtSizeMouseExited(evt);
            }
        });

        txtMS.setText("Tim Kiem MS");
        txtMS.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMSCaretUpdate(evt);
            }
        });
        txtMS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMSMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtMSMouseExited(evt);
            }
        });

        txtNCC.setText("Tim Kiem NCC");
        txtNCC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNCCCaretUpdate(evt);
            }
        });
        txtNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNCCMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtNCCMouseExited(evt);
            }
        });

        txtCL.setText("Tim Kiem CL");
        txtCL.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCLCaretUpdate(evt);
            }
        });
        txtCL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCLMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCLMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTimkiemDssp, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMS)
                        .addGap(18, 18, 18)
                        .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCL, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimkiemDssp, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtSize)
                    .addComponent(txtMS)
                    .addComponent(txtNCC)
                    .addComponent(txtCL))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btntaohd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btntaohd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohdActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(204, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("SĐT Khách Hàng");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Tên Khách Hàng");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Tên Nhân Viên");

        cbKhachHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<KhachHangVM>());
        cbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbKhachHangMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbKhachHangMouseExited(evt);
            }
        });
        cbKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKhachHangActionPerformed(evt);
            }
        });

        cbNhanVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbNhanVien.setModel(new javax.swing.DefaultComboBoxModel<NhanVienViewModel>());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbKhachHang, 0, 107, Short.MAX_VALUE)
                            .addComponent(cbNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(lblsdtkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblsdtkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Mã HD");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Tổng Tiền");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Giảm Giá");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Tiền Thanh Toán");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Tiền Khách Đưa");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Tiền Thừa");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Ngày Tạo");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 51, 0));

        txtGiamGia.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGiamGiaCaretUpdate(evt);
            }
        });

        lblTienThanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienThanhToan.setForeground(new java.awt.Color(255, 51, 0));

        lblTienThua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(255, 51, 0));

        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        lblNgayTao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(255, 51, 0));

        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiMouseClicked(evt);
            }
        });

        btnHuyHoaDon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHuyHoaDon.setText("Hủy Hóa Đơn");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(46, 46, 46)
                                        .addComponent(btntaohd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(33, 33, 33)
                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuyHoaDon)
                        .addGap(34, 34, 34))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan)
                .addGap(127, 127, 127))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btntaohd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        tbhdc.setBackground(new java.awt.Color(204, 255, 255));
        tbhdc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbhdc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbhdcMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbhdc);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtDisplay.setColumns(20);
        txtDisplay.setRows(5);
        jScrollPane6.setViewportView(txtDisplay);

        btnInHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInHD.setText("In \nHóa \nĐơn");
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(176, Short.MAX_VALUE))
        );

        NV.addTab("BH", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NV, javax.swing.GroupLayout.PREFERRED_SIZE, 1162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NV, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        NV.setSelectedIndex(1);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        // TODO add your handling code here:
        NV.setSelectedIndex(6);
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        NV.setSelectedIndex(4);
//        KhachHangForm kh = new KhachHangForm();
//        kh.setVisible(true);


    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        //JOptionPane.showConfirmDialog(this, "bạn có muốn đăng xuất không");
        int dk = JOptionPane.showConfirmDialog(this, "bạn có muốn đăng xuất tài khoản không", "Confirm", JOptionPane.YES_NO_OPTION);
        if (dk != JOptionPane.YES_OPTION) {
            return;
        }
        FromDangNhap dn = new FromDangNhap();
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        NV.setSelectedIndex(5);

    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        NV.setSelectedIndex(3);
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTSPActionPerformed
        NV.setSelectedIndex(2);
    }//GEN-LAST:event_btnCTSPActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        // TODO add your handling code here:
        int row = tblSP.getSelectedRow();
        txt_ID.setText(tblSP.getValueAt(row, 0).toString());
        txtMa.setText(tblSP.getValueAt(row, 1).toString());
        txtTen.setText(tblSP.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        int row = tblSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần xóa");
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Xóa thành công");
            if (confirm == JOptionPane.YES_NO_OPTION) {
                String id = tblSP.getValueAt(row, 0).toString();
                this.spService.delete(id);
                this.getDataSP();
            }
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        int row = tblSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần sửa");
        }
        SanPham s = this.getLoadDataSP();
        if (s == null) {
            return;
        }
        this.spService.update(s, s.getId());
        this.getDataSP();
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        SanPham s = this.getLoadDataSP();
        if (s == null) {
            return;
        }
        this.spService.insert(s);
        this.getDataSP();
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void tbllCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllCTSPMouseClicked
        int row = tbllCTSP.getSelectedRow();
        lblid1CTSP.setText(tbllCTSP.getValueAt(row, 0).toString());
        txtNamBHCTSP.setText(tbllCTSP.getValueAt(row, 1).toString());
        txamotaCTSP.setText(tbllCTSP.getValueAt(row, 2).toString());
        txtsoluong1CTSP.setText(tbllCTSP.getValueAt(row, 3).toString());
        txtgianhap1CTSP.setText(tbllCTSP.getValueAt(row, 4).toString());
        txtgiaban1CTSP.setText(tbllCTSP.getValueAt(row, 5).toString());
    }//GEN-LAST:event_tbllCTSPMouseClicked

    private void btnxoaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaCTSPActionPerformed
        int row = tbllCTSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần xóa");
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa??");
            if (confirm == JOptionPane.YES_NO_OPTION) {
                String id = tbllCTSP.getValueAt(row, 0).toString();
                this.ctspservice.delete(id);
                this.getDataCTSP();
                this.clear();
            }
        }
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btnxoaCTSPActionPerformed

    private void btnsuaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaCTSPActionPerformed
        int row = tbllCTSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần sửa");
        }
        CTSanPham m = this.getLoadDataCTSP();
        if (m == null) {
            return;
        }
        this.ctspservice.update(m, m.getId());
        this.getDataCTSP();
        this.clear();
        JOptionPane.showMessageDialog(this, "Sửa thành công");
    }//GEN-LAST:event_btnsuaCTSPActionPerformed

    private void btnthemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemCTSPActionPerformed
        CTSanPham s = this.getLoadDataCTSP();
        if (s == null) {
            return;
        }
        this.ctspservice.insert(s);
        this.getDataCTSP();
        this.clear();
    }//GEN-LAST:event_btnthemCTSPActionPerformed

    private void txtsoluong1CTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsoluong1CTSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsoluong1CTSPActionPerformed

    private void btnThemNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNvActionPerformed
        String ma = this.txtMaNv.getText().trim();
        if (this.testdata()) {
            if (checkMaTrung(ma)) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
                return;
            } else {
                NhanVien temp = GetDataFromGui();
                temp.setId(idwhenclick);
                int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm nhân viên này?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, Service.Add(temp));
                    idwhenclick = "";
                    LoadTable();
                    clear();
                }
            }
        }
    }//GEN-LAST:event_btnThemNvActionPerformed

    private void txtMaNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNvActionPerformed

    private void tblbangNvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangNvMouseClicked
        int row = tblbangNv.getSelectedRow();
        if (row == -1) {
            return;
        }
        idwhenclick = tblbangNv.getModel().getValueAt(row, 0).toString();
        ViewModel.NhanVienViewModel temp = Service.GetNVByID(idwhenclick);
        lblID.setText(temp.getId());
        txtMaNv.setText(temp.getManv());
        txtTenNv.setText(temp.getTennv());
        txtTenDemNv.setText(temp.getTendemNV());
        txtHoNv.setText(temp.getHoNV());
        txtNgaySinhNv.setText(temp.getNgaySinh());
        txtDiaChiNv.setText(temp.getDiaChi());
        txtSdtNv.setText(temp.getSdt());
        if (temp.getGioiTinh() == 0) {
            rdNamNv.setSelected(true);
        } else {
            rdNuNv.setSelected(true);
        }

        if (temp.getTrangThai() == 0) {
            rdDiLamNv.setSelected(true);
        } else {
            rdNghiLamNv.setSelected(true);
        }
    }//GEN-LAST:event_tblbangNvMouseClicked

    private void btnXoaNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNvActionPerformed
        int selectedRow = tblbangNv.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng để xóa");
            return;
        } else {
            NhanVien temp = new NhanVien();
            temp.setId(idwhenclick);
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, Service.Delete(temp));
                idwhenclick = " ";
                LoadTable();
                clear();
            }
        }
    }//GEN-LAST:event_btnXoaNvActionPerformed

    private void btnSuaNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNvActionPerformed
        int selectedRow = tblbangNv.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng để sửa");
            return;
        }
        if (this.testdata()) {
            NhanVien temp = GetDataFromGui();
            temp.setId(idwhenclick);
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa nhân viên này?", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, Service.Update(temp));
                idwhenclick = "";
                LoadTable();
                clear();
            }
        }
    }//GEN-LAST:event_btnSuaNvActionPerformed

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        // TODO add your handling code here:
        int row = tbKhachHang.getSelectedRow();
        lblIdKh.setText(tbKhachHang.getValueAt(row, 0).toString());
        txtMaKh.setText(tbKhachHang.getValueAt(row, 1).toString());
        txtTenKh.setText(tbKhachHang.getValueAt(row, 2).toString());
        if (tbKhachHang.getValueAt(row, 3).equals("Nam")) {
            rdNamKh.setSelected(true);
        } else {
            rdNuKh.setSelected(true);
        }
        txtDCKh.setText(tbKhachHang.getValueAt(row, 4).toString());
        txtsdt.setText(tbKhachHang.getValueAt(row, 5).toString());
        if (tbKhachHang.getValueAt(row, 6).equals("Khách Hàng Cũ")) {
            rdCuKh.setSelected(true);
        } else {
            rdMoiKh.setSelected(true);
        }
    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void btnXoaKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhActionPerformed
        // TODO add your handling code here:
        int row = tbKhachHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần xóa");
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa??");
            if (confirm == JOptionPane.YES_NO_OPTION) {
                String id = tbKhachHang.getValueAt(row, 0).toString();
                this.khService.delete(id);
                this.getData();
                this.clear();
            }
        }
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btnXoaKhActionPerformed

    private void btnSuaKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKhActionPerformed
        // TODO add your handling code here:
        int row = tbKhachHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần sửa");
        }
        KhachHang kh = this.getLoadData();
        if (kh == null) {
            return;
        }
        if (rdNamKh.isSelected()) {
            kh.setGioiTinh(1);
        } else {
            kh.setGioiTinh(0);
        }

        if (rdCuKh.isSelected()) {
            kh.setTrangThai(1);
        } else {
            kh.setTrangThai(2);
        }
        this.khService.update(kh, kh.getId());
        this.getData();
        this.clear();
        JOptionPane.showMessageDialog(this, "Sửa thành công");
    }//GEN-LAST:event_btnSuaKhActionPerformed

    private void btnThemKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhActionPerformed
        // TODO add your handling code here:
        KhachHang kh = this.getLoadData();
        if (kh == null) {
            return;
        }

        if (rdNamKh.isSelected()) {
            kh.setGioiTinh(1);
        } else if (rdNuKh.isSelected()) {
            kh.setGioiTinh(2);
        }

        if (rdCuKh.isSelected()) {
            kh.setTrangThai(1);
        } else if (rdMoiKh.isSelected()) {
            kh.setTrangThai(2);
        }

        this.khService.insert(kh);
        getData();
        JOptionPane.showMessageDialog(this, "Thêm Thành Công");
    }//GEN-LAST:event_btnThemKhActionPerformed

    private void rdCuKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCuKhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdCuKhActionPerformed

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed

    }//GEN-LAST:event_btnInHDActionPerformed

    private void tbhdcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhdcMouseClicked

    }//GEN-LAST:event_tbhdcMouseClicked

    private void btnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiMouseClicked

    }//GEN-LAST:event_btnLamMoiMouseClicked

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate

    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtGiamGiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGiamGiaCaretUpdate

    }//GEN-LAST:event_txtGiamGiaCaretUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKhachHangActionPerformed

    }//GEN-LAST:event_cbKhachHangActionPerformed

    private void cbKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbKhachHangMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKhachHangMouseExited

    private void cbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbKhachHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKhachHangMouseClicked

    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed

    }//GEN-LAST:event_btntaohdActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtCLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCLMouseExited

    }//GEN-LAST:event_txtCLMouseExited

    private void txtCLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCLMouseClicked

    }//GEN-LAST:event_txtCLMouseClicked

    private void txtCLCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCLCaretUpdate

    }//GEN-LAST:event_txtCLCaretUpdate

    private void txtNCCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNCCMouseExited

    }//GEN-LAST:event_txtNCCMouseExited

    private void txtNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNCCMouseClicked

    }//GEN-LAST:event_txtNCCMouseClicked

    private void txtNCCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNCCCaretUpdate

    }//GEN-LAST:event_txtNCCCaretUpdate

    private void txtMSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMSMouseExited

    }//GEN-LAST:event_txtMSMouseExited

    private void txtMSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMSMouseClicked

    }//GEN-LAST:event_txtMSMouseClicked

    private void txtMSCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMSCaretUpdate

    }//GEN-LAST:event_txtMSCaretUpdate

    private void txtSizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSizeMouseExited

    }//GEN-LAST:event_txtSizeMouseExited

    private void txtSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSizeMouseClicked

    }//GEN-LAST:event_txtSizeMouseClicked

    private void txtSizeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSizeCaretUpdate

    }//GEN-LAST:event_txtSizeCaretUpdate

    private void txtTimkiemDsspMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemDsspMouseExited

    }//GEN-LAST:event_txtTimkiemDsspMouseExited

    private void txtTimkiemDsspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemDsspMouseClicked

    }//GEN-LAST:event_txtTimkiemDsspMouseClicked

    private void txtTimkiemDsspCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimkiemDsspCaretUpdate

    }//GEN-LAST:event_txtTimkiemDsspCaretUpdate

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked

    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void tbgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbgiohangMouseClicked

    }//GEN-LAST:event_tbgiohangMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FromMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane NV;
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnCTSP;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnInHD;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSuaKh;
    private javax.swing.JButton btnSuaNv;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemKh;
    private javax.swing.JButton btnThemNv;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoaKh;
    private javax.swing.JButton btnXoaNv;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnsuaCTSP;
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btnthemCTSP;
    private javax.swing.JButton btnxoaCTSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<KhachHangVM> cbKhachHang;
    private javax.swing.JComboBox<NhanVienViewModel> cbNhanVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChu;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblINv;
    private javax.swing.JLabel lblIdKh;
    private javax.swing.JLabel lblMo;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblTienThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblid1CTSP;
    private javax.swing.JLabel lblsdtkhachhang;
    private javax.swing.JRadioButton rdCuKh;
    private javax.swing.JRadioButton rdDiLamNv;
    private javax.swing.JRadioButton rdMoiKh;
    private javax.swing.JRadioButton rdNamKh;
    private javax.swing.JRadioButton rdNamNv;
    private javax.swing.JRadioButton rdNghiLamNv;
    private javax.swing.JRadioButton rdNuKh;
    private javax.swing.JRadioButton rdNuNv;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbgiohang;
    private javax.swing.JTable tbhd;
    private javax.swing.JTable tbhdc;
    private javax.swing.JTable tbhdct;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblbangNv;
    private javax.swing.JTable tbllCTSP;
    private javax.swing.JTextArea txamotaCTSP;
    private javax.swing.JTextField txtCL;
    private javax.swing.JTextField txtDCKh;
    private javax.swing.JTextField txtDiaChiNv;
    private javax.swing.JTextArea txtDisplay;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtHoNv;
    private javax.swing.JTextField txtMS;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaKh;
    private javax.swing.JTextField txtMaNv;
    private javax.swing.JTextField txtNCC;
    private javax.swing.JTextField txtNamBHCTSP;
    private javax.swing.JTextField txtNgaySinhNv;
    private javax.swing.JTextField txtSdtNv;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDemNv;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTenNv;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimkiemDssp;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txtgiaban1CTSP;
    private javax.swing.JTextField txtgianhap1CTSP;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtsoluong1CTSP;
    private javax.swing.JTextField txttimkiem2;
    private javax.swing.JTextField txttimkiem3;
    // End of variables declaration//GEN-END:variables

    private static class ChatLieuViewModel {

        public ChatLieuViewModel() {
        }
    }

    private static class NhanVienViewModel {

        public NhanVienViewModel() {
        }
    }

    private static class MauSacViewModel {

        public MauSacViewModel() {
        }
    }

    private static class NhaCungCapVM {

        public NhaCungCapVM() {
        }
    }

    private static class SizeVM {

        public SizeVM() {
        }
    }

    private static class SanPhamViewmodel {

        public SanPhamViewmodel() {
        }
    }

    private static class KhachHangVM {

        public KhachHangVM() {
        }
    }

    private KhachHang getLoadData() {
        String id = this.lblIdKh.getText();
        String ma = this.txtMaKh.getText().trim();
        String ten = this.txtTenKh.getText().trim();
        String sdt = this.txtDCKh.getText().trim();
        String dc = this.txtsdt.getText().trim();

        if (ma.equals("") || ten.equals("") || dc.equals("") || sdt.equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống!!!");
            return null;
        }
        KhachHang kh = new KhachHang(id, ma, ten, WIDTH, sdt, dc, WIDTH);
        return kh;
    }

    private void clear() {
        lblIdKh.setText("");
        txtMaKh.setText("");
        txtTenKh.setText("");
        buttonGroupg.clearSelection();
        txtsdt.setText("");
        txtDCKh.setText("");
        buttonGroupt.clearSelection();
    }
}
