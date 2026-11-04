
package Gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
public class ArchiveForm extends javax.swing.JPanel {
    
    private String currentFilter = "All Records";
    

private void showArchivedRecords() {
    DefaultTableModel model =
            (DefaultTableModel) tblArchivedRecords.getModel();
    model.setRowCount(0);

    String filter = currentFilter;

    try (Connection con = database.DBConnection.getConnection()) {

        if (filter.equals("All Records") || filter.equals("Customers")) {
            String sql = "SELECT CustomerID, FirstName, LastName, ContactNumber, Address "
                    + "FROM customer WHERE IsArchived = 1";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("CustomerID"), "Customer",
                        rs.getString("FirstName") + " " + rs.getString("LastName")
                        + " | Contact: " + rs.getString("ContactNumber")
                        + " | Address: " + rs.getString("Address")
                    });
                }
            }
        }

        if (filter.equals("All Records") || filter.equals("Motorcycles")) {
            String sql = "SELECT MotorcycleID, CustomerID, Brand, Model, PlateNumber "
                    + "FROM motorcycle WHERE IsArchived = 1";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("MotorcycleID"), "Motorcycle",
                        rs.getString("Brand") + " " + rs.getString("Model")
                        + " | Plate: " + rs.getString("PlateNumber")
                        + " | Customer ID: " + rs.getInt("CustomerID")
                    });
                }
            }
        }

        if (filter.equals("All Records") || filter.equals("Mechanics")) {
            String sql = "SELECT MechanicID, FirstName, LastName, ContactNumber, Specialization "
                    + "FROM mechanic WHERE IsArchived = 1";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("MechanicID"), "Mechanic",
                        rs.getString("FirstName") + " " + rs.getString("LastName")
                        + " | Contact: " + rs.getString("ContactNumber")
                        + " | Specialization: " + rs.getString("Specialization")
                    });
                }
            }
        }

        if (filter.equals("All Records") || filter.equals("Services")) {
            String sql = "SELECT ServiceID, ServiceName, Description, ServiceFee "
                    + "FROM service WHERE IsArchived = 1";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("ServiceID"), "Service",
                        rs.getString("ServiceName")
                        + " | " + rs.getString("Description")
                        + " | Fee: " + rs.getBigDecimal("ServiceFee")
                    });
                }
            }
        }

        if (filter.equals("All Records") || filter.equals("Parts")) {
            String sql = "SELECT PartID, PartName, Brand, Category, StockQuantity, Price "
                    + "FROM parts WHERE IsArchived = 1";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("PartID"), "Part",
                        rs.getString("PartName")
                        + " | Brand: " + rs.getString("Brand")
                        + " | Category: " + rs.getString("Category")
                        + " | Stock: " + rs.getInt("StockQuantity")
                        + " | Price: " + rs.getBigDecimal("Price")
                    });
                }
            }
        }

        if (filter.equals("All Records") || filter.equals("Transactions")) {
            String sql = "SELECT TransactionID, MotorcycleID, MechanicID, "
                    + "TransactionDate, TotalAmount, Status "
                    + "FROM service_transaction WHERE IsArchived = 1";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("TransactionID"), "Transaction",
                        "Motorcycle ID: " + rs.getInt("MotorcycleID")
                        + " | Mechanic ID: " + rs.getInt("MechanicID")
                        + " | Date: " + rs.getString("TransactionDate")
                        + " | Total: " + rs.getBigDecimal("TotalAmount")
                        + " | Status: " + rs.getString("Status")
                    });
                }
            }
        }

        if (filter.equals("All Records") || filter.equals("Payments")) {
            String sql = "SELECT PaymentID, TransactionID, PaymentDate, AmountPaid, PaymentMethod "
                    + "FROM payment WHERE IsArchived = 1";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("PaymentID"), "Payment",
                        "Transaction ID: " + rs.getInt("TransactionID")
                        + " | Date: " + rs.getString("PaymentDate")
                        + " | Amount: " + rs.getBigDecimal("AmountPaid")
                        + " | Method: " + rs.getString("PaymentMethod")
                    });
                }
            }
        }

        if (filter.equals("All Records") || filter.equals("Service Details")) {
            String sql = "SELECT ServiceDetailID, TransactionID, ServiceID, Quantity, Subtotal "
                    + "FROM service_detail WHERE IsArchived = 1";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("ServiceDetailID"), "Service Detail",
                        "Transaction ID: " + rs.getInt("TransactionID")
                        + " | Service ID: " + rs.getInt("ServiceID")
                        + " | Quantity: " + rs.getInt("Quantity")
                        + " | Subtotal: " + rs.getBigDecimal("Subtotal")
                    });
                }
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
                this, "Error loading archived records: " + e.getMessage()
        );
    }
}


    public ArchiveForm() {
        initComponents();
        showArchivedRecords();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        TXTSEARCH = new javax.swing.JTextField();
        BTNSEARCH = new javax.swing.JButton();
        btnAllRecords = new javax.swing.JButton();
        btnCustomers = new javax.swing.JButton();
        btnMotorcycles = new javax.swing.JButton();
        btnMechanics = new javax.swing.JButton();
        btnServices = new javax.swing.JButton();
        btnTransactions = new javax.swing.JButton();
        btnServiceDetails = new javax.swing.JButton();
        btnParts = new javax.swing.JButton();
        btnPayments = new javax.swing.JButton();
        btnpartUsed = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();
        pnlArchivedRecords = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArchivedRecords = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        jLabel1.setText("Archived Records");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Manage and restore records removed from active modules.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setText("Archived Records List");

        TXTSEARCH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        BTNSEARCH.setBackground(new java.awt.Color(37, 99, 235));
        BTNSEARCH.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BTNSEARCH.setForeground(new java.awt.Color(255, 255, 255));
        BTNSEARCH.setText("Search");
        BTNSEARCH.addActionListener(this::BTNSEARCHActionPerformed);

        btnAllRecords.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAllRecords.setText("All Records");
        btnAllRecords.addActionListener(this::btnAllRecordsActionPerformed);

        btnCustomers.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCustomers.setText("Customers");
        btnCustomers.addActionListener(this::btnCustomersActionPerformed);

        btnMotorcycles.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMotorcycles.setText("Motorcycles");
        btnMotorcycles.addActionListener(this::btnMotorcyclesActionPerformed);

        btnMechanics.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMechanics.setText("Mechanics");
        btnMechanics.addActionListener(this::btnMechanicsActionPerformed);

        btnServices.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnServices.setText("Services");
        btnServices.addActionListener(this::btnServicesActionPerformed);

        btnTransactions.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnTransactions.setText("Transactions");
        btnTransactions.addActionListener(this::btnTransactionsActionPerformed);

        btnServiceDetails.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnServiceDetails.setText("Service Details");
        btnServiceDetails.addActionListener(this::btnServiceDetailsActionPerformed);

        btnParts.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnParts.setText("Parts");
        btnParts.addActionListener(this::btnPartsActionPerformed);

        btnPayments.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnPayments.setText("Payments");
        btnPayments.addActionListener(this::btnPaymentsActionPerformed);

        btnpartUsed.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnpartUsed.setText("Parts Used");

        btnRestore.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRestore.setText("Restore");
        btnRestore.addActionListener(this::btnRestoreActionPerformed);

        tblArchivedRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Record ID", "Record Type", "Record Details"
            }
        ));
        jScrollPane1.setViewportView(tblArchivedRecords);

        javax.swing.GroupLayout pnlArchivedRecordsLayout = new javax.swing.GroupLayout(pnlArchivedRecords);
        pnlArchivedRecords.setLayout(pnlArchivedRecordsLayout);
        pnlArchivedRecordsLayout.setHorizontalGroup(
            pnlArchivedRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        pnlArchivedRecordsLayout.setVerticalGroup(
            pnlArchivedRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlArchivedRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAllRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMotorcycles, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnServiceDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnParts, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnpartUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnMechanics, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnServices, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TXTSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BTNSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137)))
                        .addGap(0, 30, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnRestore, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAllRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMotorcycles, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMechanics, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnServices, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnServiceDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnParts, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpartUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTNSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TXTSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlArchivedRecords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(830, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTNSEARCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSEARCHActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_BTNSEARCHActionPerformed

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        // TODO add your handling code here:
    int row = tblArchivedRecords.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a record to restore.");
        return;
    }

    int modelRow = tblArchivedRecords.convertRowIndexToModel(row);

    int id = Integer.parseInt(
        tblArchivedRecords.getModel().getValueAt(modelRow, 0).toString()
    );

    String type = tblArchivedRecords.getModel().getValueAt(modelRow, 1).toString();

    String table = "";
    String idColumn = "";

    switch (type) {
        case "Customer":
            table = "customer";
            idColumn = "CustomerID";
            break;
        case "Motorcycle":
            table = "motorcycle";
            idColumn = "MotorcycleID";
            break;
        case "Mechanic":
            table = "mechanic";
            idColumn = "MechanicID";
            break;
        case "Service":
            table = "service";
            idColumn = "ServiceID";
            break;
        case "Part":
            table = "parts";
            idColumn = "PartID";
            break;
        case "Transaction":
            table = "service_transaction";
            idColumn = "TransactionID";
            break;
        case "Payment":
            table = "payment";
            idColumn = "PaymentID";
            break;
        case "Service Detail":
            table = "service_detail";
            idColumn = "ServiceDetailID";
            break;
        default:
            JOptionPane.showMessageDialog(this, "Unknown record type.");
            return;
    }

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Restore this " + type + "?",
        "Confirm Restore",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    String sql = "UPDATE " + table + " SET IsArchived = 0 WHERE "
            + idColumn + " = ?";

    try (Connection con = database.DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, id);

        if (pst.executeUpdate() > 0) {
            showArchivedRecords();
            JOptionPane.showMessageDialog(this, type + " restored successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Record not found.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Restore failed: " + e.getMessage());
    }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomersActionPerformed
        // TODO add your handling code here:
        currentFilter = "Customers";
showArchivedRecords();
    }//GEN-LAST:event_btnCustomersActionPerformed

    private void btnAllRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllRecordsActionPerformed
        // TODO add your handling code here:
        currentFilter = "All Records";
showArchivedRecords();
    }//GEN-LAST:event_btnAllRecordsActionPerformed

    private void btnMotorcyclesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMotorcyclesActionPerformed
        // TODO add your handling code here:
        currentFilter = "Motorcycles";
showArchivedRecords();
    }//GEN-LAST:event_btnMotorcyclesActionPerformed

    private void btnMechanicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMechanicsActionPerformed
        // TODO add your handling code here:
        currentFilter = "Mechanics";
    showArchivedRecords();
    }//GEN-LAST:event_btnMechanicsActionPerformed

    private void btnServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicesActionPerformed
        // TODO add your handling code here:
        currentFilter = "Services";
    showArchivedRecords();
    }//GEN-LAST:event_btnServicesActionPerformed

    private void btnPartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartsActionPerformed
        // TODO add your handling code here:
        currentFilter = "Parts";
    showArchivedRecords();
    }//GEN-LAST:event_btnPartsActionPerformed

    private void btnTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransactionsActionPerformed
        // TODO add your handling code here:
        currentFilter = "Transactions";
    showArchivedRecords();
    }//GEN-LAST:event_btnTransactionsActionPerformed

    private void btnPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentsActionPerformed
        // TODO add your handling code here:
        currentFilter = "Payments";
    showArchivedRecords();
    }//GEN-LAST:event_btnPaymentsActionPerformed

    private void btnServiceDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiceDetailsActionPerformed
        // TODO add your handling code here:
        currentFilter = "Service Details";
    showArchivedRecords();
    }//GEN-LAST:event_btnServiceDetailsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSEARCH;
    private javax.swing.JTextField TXTSEARCH;
    private javax.swing.JButton btnAllRecords;
    private javax.swing.JButton btnCustomers;
    private javax.swing.JButton btnMechanics;
    private javax.swing.JButton btnMotorcycles;
    private javax.swing.JButton btnParts;
    private javax.swing.JButton btnPayments;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnServiceDetails;
    private javax.swing.JButton btnServices;
    private javax.swing.JButton btnTransactions;
    private javax.swing.JButton btnpartUsed;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlArchivedRecords;
    private javax.swing.JTable tblArchivedRecords;
    // End of variables declaration//GEN-END:variables
}
