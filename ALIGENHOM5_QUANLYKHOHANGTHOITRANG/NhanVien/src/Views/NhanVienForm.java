/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DomainModel.NhanVien;
import Reponsitories.NhanVienReponsitory;
import Services.impl.NhanVienServiceImpl;
import Util.DBContext11;
import ViewModels.NhanVienViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NamNguyenTien
 */
public class NhanVienForm extends javax.swing.JFrame {
    
    private NhanVienServiceImpl Service;
    private DefaultTableModel defaultTableModel;
    private String idwhenclick;
    private NhanVienReponsitory nhanVienReponsitory = new NhanVienReponsitory();

    /**
     * Creates new form NhanVienForm
     */
    public NhanVienForm() {
        initComponents();
        Service = new NhanVienServiceImpl();
        this.setLocationRelativeTo(null);
        LoadTable();
    }
    
    public void LoadTable() {
        defaultTableModel = (DefaultTableModel) tbNV.getModel();
        defaultTableModel.setRowCount(0);
        for (NhanVienViewModel x : Service.GetAll()) {
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
            Connection conn = DBContext11.getConnection();
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
        String ma = this.txtMa.getText().trim();
        String ten = this.txtTen.getText().trim();
        String tenDem = this.txtTenDem.getText().trim();
        String ho = this.txtHo.getText().trim();
        String ngaySinh = this.txtNgaySinh.getText().trim();
        String diaChi = this.txtDiaChi.getText().trim();
        String sdt = this.txtSdt.getText().trim();
        int gioitinh;
        
        if (rdNam.isSelected()) {
            gioitinh = 0;
        } else {
            gioitinh = 1;
        }
        int trangThai;
        if (rdDiLam.isSelected()) {
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
        if (txtMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không dược để trống");
            return false;
        }
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống");
            return false;
        }
        if (txtTenDem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đệm không được để trống");
            return false;
        }
        if (txtHo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ không được để trống");
            return false;
        }
        if (txtNgaySinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
            return false;
        }
        if (!txtNgaySinh.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (yyyy-MM-dd)");
            return false;
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
            return false;
        }
        if (txtSdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
            return false;
        }
        if (!txtSdt.getText().matches("0\\d{9}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng (0xxxxxxxxx)");
            return false;
        }
        return true;
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
        txtSdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        rdNu = new javax.swing.JRadioButton();
        lblID = new javax.swing.JLabel();
        rdDiLam = new javax.swing.JRadioButton();
        txtMa = new javax.swing.JTextField();
        rdNghiLam = new javax.swing.JRadioButton();
        txtTen = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        txtHo = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        txtDiaChi = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNV = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTenDem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("HọNV");

        buttonGroup1.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");

        jLabel6.setText("Giới Tính");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        lblID.setText("-");

        buttonGroup2.add(rdDiLam);
        rdDiLam.setSelected(true);
        rdDiLam.setText("Đi Làm");

        buttonGroup2.add(rdNghiLam);
        rdNghiLam.setText("Nghỉ Làm");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 51));
        jLabel1.setText("Nhân Viên");

        jLabel8.setText("Địa Chỉ");

        tbNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MãNV", "TênNV", "Tên Đệm", "HọNV", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "Trạng Thái"
            }
        ));
        tbNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNV);

        jLabel2.setText("Id");

        jLabel9.setText("SDT");

        jLabel3.setText("MãNV");

        jLabel10.setText("Trạng Thái");

        jLabel4.setText("TênNV");

        jLabel11.setText("Tên đệm");

        jLabel7.setText("Ngày Sinh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                            .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                            .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel11))
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(32, 32, 32)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel6))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdNam)
                                                .addGap(29, 29, 29)
                                                .addComponent(rdNu))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtDiaChi)
                                                .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnXoa)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdDiLam)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdNghiLam))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(125, 125, 125)
                                .addComponent(btnSua)))))
                .addContainerGap(346, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblID)
                    .addComponent(jLabel6)
                    .addComponent(rdNam)
                    .addComponent(rdNu))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rdDiLam)
                    .addComponent(rdNghiLam)
                    .addComponent(jLabel11)
                    .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String ma = this.txtMa.getText().trim();
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
                    Clear();
                }
            }
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        
        int selectedRow = tbNV.getSelectedRow();
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
                Clear();
            }
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tbNV.getSelectedRow();
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
                Clear();
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void tbNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNVMouseClicked
        // TODO add your handling code here:
        int row = tbNV.getSelectedRow();
        if (row == -1) {
            return;
        }
        idwhenclick = tbNV.getModel().getValueAt(row, 0).toString();
        NhanVienViewModel temp = Service.GetNVByID(idwhenclick);
        lblID.setText(temp.getId());
        txtMa.setText(temp.getManv());
        txtTen.setText(temp.getTennv());
        txtTenDem.setText(temp.getTendemNV());
        txtHo.setText(temp.getHoNV());
        txtNgaySinh.setText(temp.getNgaySinh());
        txtDiaChi.setText(temp.getDiaChi());
        txtSdt.setText(temp.getSdt());
        if (temp.getGioiTinh() == 0) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
        
        if (temp.getTrangThai() == 0) {
            rdDiLam.setSelected(true);
        } else {
            rdNghiLam.setSelected(true);
        }
    }//GEN-LAST:event_tbNVMouseClicked

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
            java.util.logging.Logger.getLogger(NhanVienForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienForm().setVisible(true);
            }
        });
    }
    
    public void Clear() {
        lblID.setText("");
        txtDiaChi.setText("");
        txtTenDem.setText("");
        txtMa.setText("");
        txtHo.setText("");
        txtSdt.setText("");
        txtTen.setText("");
        rdDiLam.setSelected(false);
        rdNam.setSelected(false);
        rdNghiLam.setSelected(false);
        rdNu.setSelected(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JRadioButton rdDiLam;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNghiLam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbNV;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    // End of variables declaration//GEN-END:variables

}
