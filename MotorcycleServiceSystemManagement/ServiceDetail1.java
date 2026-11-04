
package Gui;
import javax.swing.JOptionPane;
public class ServiceDetail1 extends javax.swing.JPanel {
 
    private void loadTransactionIDs() {
    String sql = "SELECT st.TransactionID, "
               + "c.FirstName, c.LastName "
               + "FROM service_transaction st "
               + "JOIN motorcycle m ON st.MotorcycleID = m.MotorcycleID "
               + "JOIN customer c ON m.CustomerID = c.CustomerID "
               + "WHERE st.IsArchived = 0 "
               + "AND m.IsArchived = 0 "
               + "AND c.IsArchived = 0 "
               + "ORDER BY st.TransactionID DESC";

    try (java.sql.Connection con =
             database.DBConnection.getConnection()) {

        if (con == null) {
            JOptionPane.showMessageDialog(
                this, "Database connection failed!");
            return;
        }

        try (java.sql.PreparedStatement pst =
                 con.prepareStatement(sql);
             java.sql.ResultSet rs = pst.executeQuery()) {

            CBXTRANSACTIONID.removeAllItems();
            CBXTRANSACTIONID.addItem("-- Select Transaction --");

            while (rs.next()) {
                String item = "Transaction #"
                    + rs.getInt("TransactionID")
                    + " - "
                    + rs.getString("FirstName") + " "
                    + rs.getString("LastName");

                CBXTRANSACTIONID.addItem(item);
            }
        }
    } catch (java.sql.SQLException e) {
        JOptionPane.showMessageDialog(
            this, "Error loading transactions: " + e.getMessage());
    }
}
    
    private void loadServices() {
    String sql = "SELECT ServiceID, ServiceName "
        + "FROM service "
        + "WHERE IsArchived = 0 "
        + "ORDER BY ServiceID ASC";

    try (java.sql.Connection con =
             database.DBConnection.getConnection()) {

        if (con == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Database connection failed!");
            return;
        }

        try (java.sql.PreparedStatement pst =
                 con.prepareStatement(sql);
             java.sql.ResultSet rs = pst.executeQuery()) {

            CBXSERVICE.removeAllItems();
            CBXSERVICE.addItem("-- Select Service --");

            while (rs.next()) {
                CBXSERVICE.addItem(
                    rs.getInt("ServiceID") + " - "
                    + rs.getString("ServiceName"));
            }
        }
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Error loading services: " + e.getMessage());
    }
}
    
    private void loadServiceDetails() {
    String sql = "SELECT sd.ServiceDetailID, "
               + "sd.TransactionID, "
               + "c.FirstName, c.LastName, "
               + "s.ServiceName, "
               + "sd.Quantity, sd.Subtotal "
               + "FROM service_detail sd "
               + "JOIN service_transaction st "
               + "ON sd.TransactionID = st.TransactionID "
               + "JOIN motorcycle m "
               + "ON st.MotorcycleID = m.MotorcycleID "
               + "JOIN customer c "
               + "ON m.CustomerID = c.CustomerID "
               
+ "JOIN service s "
+ "ON sd.ServiceID = s.ServiceID "
+ "WHERE sd.IsArchived = 0 "
+ "AND st.IsArchived = 0 "
+ "AND m.IsArchived = 0 "
+ "AND c.IsArchived = 0 "
+ "AND s.IsArchived = 0 "

               + "ORDER BY sd.ServiceDetailID DESC";

    javax.swing.table.DefaultTableModel model =
        (javax.swing.table.DefaultTableModel)
            tblServiceDetail.getModel();

    model.setRowCount(0);

    try (java.sql.Connection con =
             database.DBConnection.getConnection()) {

        if (con == null) {
            JOptionPane.showMessageDialog(
                this, "Database connection failed!");
            return;
        }

        try (java.sql.PreparedStatement pst =
                 con.prepareStatement(sql);
             java.sql.ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
               model.addRow(new Object[]{
    rs.getInt("ServiceDetailID"),
    rs.getInt("TransactionID"),
    rs.getString("ServiceName"),
    rs.getInt("Quantity"),
    rs.getBigDecimal("Subtotal")
});
            }
        }

    } catch (java.sql.SQLException e) {
        JOptionPane.showMessageDialog(
            this, "Error loading service details: "
                + e.getMessage());
    }
}
    
private void setupSearchPlaceholder() {
    String placeholder = "Search service...";

    TXTSEARCH.setText(placeholder);
    TXTSEARCH.setForeground(java.awt.Color.GRAY);

    TXTSEARCH.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (TXTSEARCH.getText().equals(placeholder)) {
                TXTSEARCH.setText("");
                TXTSEARCH.setForeground(java.awt.Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (TXTSEARCH.getText().trim().isEmpty()) {
                TXTSEARCH.setText(placeholder);
                TXTSEARCH.setForeground(java.awt.Color.GRAY);
            }
        }
    });
}

private void calculateSubtotal() {
    int serviceID = getSelectedServiceID();

    if (serviceID == 0
            || TXTQUANTITY.getText().trim().isEmpty()) {
        LBLTOTAL.setText("Subtotal: ₱0.00");
        return;
    }

    try {
        int quantity = Integer.parseInt(
                TXTQUANTITY.getText().trim());

        if (quantity <= 0) {
            LBLTOTAL.setText("Subtotal: ₱0.00");
            return;
        }

        java.math.BigDecimal fee =
                getServiceFee(serviceID);

        java.math.BigDecimal subtotal = fee.multiply(
                java.math.BigDecimal.valueOf(quantity));

        LBLTOTAL.setText(
                "Subtotal: ₱" + subtotal.toPlainString());

    } catch (NumberFormatException e) {
        LBLTOTAL.setText("Subtotal: ₱0.00");
    } catch (Exception e) {
        LBLTOTAL.setText("Subtotal: Error");
    }
}

    public ServiceDetail1() {
        initComponents();
        setupSearchPlaceholder();
        loadTransactionIDs();
        loadServices();
        loadServiceDetails();
}

    private int getSelectedTransactionID() {
    Object selected = CBXTRANSACTIONID.getSelectedItem();

    if (CBXTRANSACTIONID.getSelectedIndex() <= 0
            || selected == null) {
        return 0;
    }

    String text = selected.toString();
    String id = text.split(" - ")[0]
                    .replace("Transaction #", "").trim();

    return Integer.parseInt(id);
}

private int getSelectedServiceID() {
    Object selected = CBXSERVICE.getSelectedItem();

    if (CBXSERVICE.getSelectedIndex() <= 0
            || selected == null) {
        return 0;
    }

    return Integer.parseInt(
        selected.toString().split(" - ")[0].trim()
    );
}

private java.math.BigDecimal getServiceFee(int serviceID)
        throws java.sql.SQLException {

    String sql = "SELECT ServiceFee FROM service WHERE ServiceID = ?";

    try (java.sql.Connection con =
             database.DBConnection.getConnection();
         java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, serviceID);

        try (java.sql.ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal("ServiceFee");
            }
        }
    }

    throw new java.sql.SQLException("Service fee not found.");
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
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TXTQUANTITY = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServiceDetail = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        TXTSEARCH = new javax.swing.JTextField();
        BTNSEARCH = new javax.swing.JButton();
        CBXSERVICE = new javax.swing.JComboBox<>();
        LBLTOTAL = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CBXTRANSACTIONID = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        jLabel1.setText("Service Details");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Add the services included in this transaction.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(820, Short.MAX_VALUE))
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

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setText("Select Service");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setText("Service Name");

        TXTQUANTITY.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TXTQUANTITY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTQUANTITYKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setText("Quantity");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel4.setText("Subtotal");

        btnSave.setBackground(new java.awt.Color(37, 99, 235));
        btnSave.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText(" Save Service");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(0, 153, 0));
        btnUpdate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnRemove.setBackground(new java.awt.Color(204, 51, 0));
        btnRemove.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setText("Remove");
        btnRemove.addActionListener(this::btnRemoveActionPerformed);

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblServiceDetail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblServiceDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Service Detail ID", "Transaction ID", "Service Name", "Quantity", "Subtotal"
            }
        ));
        tblServiceDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServiceDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblServiceDetail);

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setText("Service Records");

        TXTSEARCH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        BTNSEARCH.setBackground(new java.awt.Color(37, 99, 235));
        BTNSEARCH.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BTNSEARCH.setForeground(new java.awt.Color(255, 255, 255));
        BTNSEARCH.setText("Search");
        BTNSEARCH.addActionListener(this::BTNSEARCHActionPerformed);

        CBXSERVICE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBXSERVICE.setPreferredSize(new java.awt.Dimension(72, 40));
        CBXSERVICE.addActionListener(this::CBXSERVICEActionPerformed);

        LBLTOTAL.setEditable(false);
        LBLTOTAL.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setText("Transaction");

        CBXTRANSACTIONID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBXTRANSACTIONID.setPreferredSize(new java.awt.Dimension(72, 40));
        CBXTRANSACTIONID.addActionListener(this::CBXTRANSACTIONIDActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(CBXSERVICE, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(CBXTRANSACTIONID, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTQUANTITY, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(15, 15, 15)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TXTSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(BTNSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LBLTOTAL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(205, 205, 205))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBXSERVICE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTQUANTITY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBXTRANSACTIONID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LBLTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TXTSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTNSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
                .addContainerGap(204, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
 int transactionID = getSelectedTransactionID();
int serviceID = getSelectedServiceID();

if (transactionID == 0 || serviceID == 0
        || TXTQUANTITY.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(this,
            "Please select a transaction, service, and quantity.");
    return;
}

try {
    int quantity = Integer.parseInt(TXTQUANTITY.getText().trim());

    if (quantity <= 0) {
        JOptionPane.showMessageDialog(this,
                "Quantity must be greater than zero.");
        return;
    }

    java.math.BigDecimal fee = getServiceFee(serviceID);
    java.math.BigDecimal subtotal = fee.multiply(
            java.math.BigDecimal.valueOf(quantity));

    String sql = "INSERT INTO service_detail "
            + "(TransactionID, ServiceID, Quantity, Subtotal) "
            + "VALUES (?, ?, ?, ?)";

    try (java.sql.Connection con =
                 database.DBConnection.getConnection()) {

        if (con == null) {
            JOptionPane.showMessageDialog(this,
                    "Database connection failed!");
            return;
        }

        try (java.sql.PreparedStatement pst =
                     con.prepareStatement(sql)) {

            pst.setInt(1, transactionID);
            pst.setInt(2, serviceID);
            pst.setInt(3, quantity);
            pst.setBigDecimal(4, subtotal);
            pst.executeUpdate();
        }
    }

    LBLTOTAL.setText("Subtotal: ₱" + subtotal.toPlainString());
    JOptionPane.showMessageDialog(this,
            "Service detail saved successfully!");

    loadServiceDetails();

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this,
            "Enter a valid whole number for quantity.");
} catch (Exception e) {
    JOptionPane.showMessageDialog(this,
            "Error saving service detail: " + e.getMessage());
}
    }//GEN-LAST:event_btnSaveActionPerformed

    private void BTNSEARCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSEARCHActionPerformed
        // TODO add your handling code here:     
    String search = TXTSEARCH.getText().trim();

    if (search.equalsIgnoreCase("Search service...")
            || search.isEmpty()) {
        loadServiceDetails();
        return;
    }

    String sql = "SELECT sd.ServiceDetailID, "
            + "sd.TransactionID, "
            + "s.ServiceName, "
            + "sd.Quantity, "
            + "sd.Subtotal "
            + "FROM service_detail sd "
            + "JOIN service_transaction st "
            + "ON sd.TransactionID = st.TransactionID "
            + "JOIN motorcycle m "
            + "ON st.MotorcycleID = m.MotorcycleID "
            + "JOIN customer c "
            + "ON m.CustomerID = c.CustomerID "
            + "JOIN service s "
            + "ON sd.ServiceID = s.ServiceID "
            + "WHERE sd.IsArchived = 0 "
            + "AND st.IsArchived = 0 "
            + "AND m.IsArchived = 0 "
            + "AND c.IsArchived = 0 "
            + "AND s.IsArchived = 0 "
            + "AND (CAST(sd.ServiceDetailID AS CHAR) LIKE ? "
            + "OR CAST(sd.TransactionID AS CHAR) LIKE ? "
            + "OR s.ServiceName LIKE ? "
            + "OR CAST(sd.Quantity AS CHAR) LIKE ? "
            + "OR CAST(sd.Subtotal AS CHAR) LIKE ?) "
            + "ORDER BY sd.ServiceDetailID DESC";

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel)
                    tblServiceDetail.getModel();

    model.setRowCount(0);

    try (java.sql.Connection con =
                 database.DBConnection.getConnection()) {

        if (con == null) {
            JOptionPane.showMessageDialog(this,
                    "Database connection failed!");
            return;
        }

        try (java.sql.PreparedStatement pst =
                     con.prepareStatement(sql)) {

            String keyword = "%" + search + "%";

            for (int i = 1; i <= 5; i++) {
                pst.setString(i, keyword);
            }

            try (java.sql.ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("ServiceDetailID"),
                        rs.getInt("TransactionID"),
                        rs.getString("ServiceName"),
                        rs.getInt("Quantity"),
                        rs.getBigDecimal("Subtotal")
                    });
                }
            }
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No service detail records found.");
        }

    } catch (java.sql.SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error searching service details: "
                        + e.getMessage());
    }
    }//GEN-LAST:event_BTNSEARCHActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblServiceDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServiceDetailMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblServiceDetailMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblServiceDetail.getSelectedRow();

if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this,
            "Please select a service detail record to update.");
    return;
}

int modelRow = tblServiceDetail.convertRowIndexToModel(selectedRow);
javax.swing.table.DefaultTableModel model =
        (javax.swing.table.DefaultTableModel) tblServiceDetail.getModel();

int serviceDetailID = Integer.parseInt(
        model.getValueAt(modelRow, 0).toString());

int currentServiceID = 0;
String currentServiceName = model.getValueAt(modelRow, 2).toString();

String sql = "SELECT ServiceID FROM service WHERE ServiceName = ?";

try (java.sql.Connection con = database.DBConnection.getConnection();
     java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

    pst.setString(1, currentServiceName);

    try (java.sql.ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
            currentServiceID = rs.getInt("ServiceID");
        }
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(this,
            "Error loading current service: " + e.getMessage());
    return;
}

javax.swing.JComboBox<String> serviceCombo = new javax.swing.JComboBox<>();
javax.swing.JTextField quantityField = new javax.swing.JTextField(
        model.getValueAt(modelRow, 3).toString());
javax.swing.JLabel subtotalLabel = new javax.swing.JLabel();

try (java.sql.Connection con = database.DBConnection.getConnection();
     java.sql.PreparedStatement pst = con.prepareStatement(
             "SELECT ServiceID, ServiceName FROM service "
             + "WHERE IsArchived = 0 ORDER BY ServiceID ASC");
     java.sql.ResultSet rs = pst.executeQuery()) {

    while (rs.next()) {
        serviceCombo.addItem(
                rs.getInt("ServiceID") + " - "
                + rs.getString("ServiceName"));
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(this,
            "Error loading services: " + e.getMessage());
    return;
}

for (int i = 0; i < serviceCombo.getItemCount(); i++) {
    if (serviceCombo.getItemAt(i).startsWith(currentServiceID + " - ")) {
        serviceCombo.setSelectedIndex(i);
        break;
    }
}

javax.swing.JPanel panel = new javax.swing.JPanel(
        new java.awt.GridLayout(0, 1, 5, 5));

panel.add(new javax.swing.JLabel("Service Name:"));
panel.add(serviceCombo);
panel.add(new javax.swing.JLabel("Quantity:"));
panel.add(quantityField);
panel.add(subtotalLabel);

java.awt.event.ActionListener calculate = e -> {
    try {
        int serviceID = Integer.parseInt(
                serviceCombo.getSelectedItem().toString()
                        .split(" - ")[0]);
        int quantity = Integer.parseInt(quantityField.getText().trim());

        if (quantity <= 0) {
            subtotalLabel.setText("Subtotal: Enter quantity above 0");
            return;
        }

        try (java.sql.Connection con = database.DBConnection.getConnection();
             java.sql.PreparedStatement pst = con.prepareStatement(
                     "SELECT ServiceFee FROM service WHERE ServiceID = ?")) {

            pst.setInt(1, serviceID);

            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    java.math.BigDecimal subtotal =
                            rs.getBigDecimal("ServiceFee")
                                    .multiply(java.math.BigDecimal.valueOf(quantity));

                    subtotalLabel.setText(
                            "Subtotal: ₱" + subtotal.toPlainString());
                }
            }
        }
    } catch (Exception ex) {
        subtotalLabel.setText("Subtotal: Enter a valid quantity");
    }
};

serviceCombo.addActionListener(calculate);
quantityField.addActionListener(calculate);
quantityField.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        calculate.actionPerformed(null);
    }
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        calculate.actionPerformed(null);
    }
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        calculate.actionPerformed(null);
    }
});

calculate.actionPerformed(null);

int result = JOptionPane.showConfirmDialog(
        this, panel, "Update Service Detail",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

if (result != JOptionPane.OK_OPTION) {
    return;
}

try {
    int newServiceID = Integer.parseInt(
            serviceCombo.getSelectedItem().toString()
                    .split(" - ")[0]);
    int newQuantity = Integer.parseInt(quantityField.getText().trim());

    if (newQuantity <= 0) {
        JOptionPane.showMessageDialog(this,
                "Quantity must be greater than zero.");
        return;
    }

    java.math.BigDecimal fee = getServiceFee(newServiceID);
    java.math.BigDecimal newSubtotal = fee.multiply(
            java.math.BigDecimal.valueOf(newQuantity));

    String updateSQL = "UPDATE service_detail "
        + "SET ServiceID = ?, Quantity = ?, Subtotal = ? "
        + "WHERE ServiceDetailID = ? AND IsArchived = 0";

    try (java.sql.Connection con = database.DBConnection.getConnection();
         java.sql.PreparedStatement pst = con.prepareStatement(updateSQL)) {

        pst.setInt(1, newServiceID);
        pst.setInt(2, newQuantity);
        pst.setBigDecimal(3, newSubtotal);
        pst.setInt(4, serviceDetailID);

        int updated = pst.executeUpdate();

        if (updated > 0) {
            JOptionPane.showMessageDialog(this,
                    "Service detail updated successfully!");
            loadServiceDetails();
            LBLTOTAL.setText("Subtotal: ₱" + newSubtotal.toPlainString());
        } else {
            JOptionPane.showMessageDialog(this,
                    "No record was updated.");
        }
    }

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this,
            "Enter a valid whole number for quantity.");
} catch (Exception e) {
    JOptionPane.showMessageDialog(this,
            "Error updating service detail: " + e.getMessage());
}
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void CBXSERVICEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXSERVICEActionPerformed
        // TODO add your handling code here:
if (CBXSERVICE.getSelectedIndex() > 0) {
    calculateSubtotal();
}
    }//GEN-LAST:event_CBXSERVICEActionPerformed

    private void CBXTRANSACTIONIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXTRANSACTIONIDActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_CBXTRANSACTIONIDActionPerformed

    private void TXTQUANTITYKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTQUANTITYKeyReleased
        // TODO add your handling code here:
        calculateSubtotal();
    }//GEN-LAST:event_TXTQUANTITYKeyReleased

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        
int selectedRow = tblServiceDetail.getSelectedRow();

if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Please select a service detail first.");
    return;
}

int modelRow = tblServiceDetail.convertRowIndexToModel(selectedRow);
int serviceDetailID = Integer.parseInt(
    tblServiceDetail.getModel().getValueAt(modelRow, 0).toString()
);

int confirm = JOptionPane.showConfirmDialog(
    this,
    "Are you sure you want to archive this service detail?",
    "Confirm Archive",
    JOptionPane.YES_NO_OPTION
);

if (confirm != JOptionPane.YES_OPTION) {
    return;
}

String sql = "UPDATE service_detail SET IsArchived = 1 WHERE ServiceDetailID = ?";

try (java.sql.Connection con = database.DBConnection.getConnection();
     java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

    pst.setInt(1, serviceDetailID);

    if (pst.executeUpdate() > 0) {
        JOptionPane.showMessageDialog(this, "Service detail archived successfully!");
        loadServiceDetails();
    } else {
        JOptionPane.showMessageDialog(this, "Service detail not found.");
    }

} catch (java.sql.SQLException e) {
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
}

    }//GEN-LAST:event_btnRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSEARCH;
    private javax.swing.JComboBox<String> CBXSERVICE;
    private javax.swing.JComboBox<String> CBXTRANSACTIONID;
    private javax.swing.JTextField LBLTOTAL;
    private javax.swing.JTextField TXTQUANTITY;
    private javax.swing.JTextField TXTSEARCH;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServiceDetail;
    // End of variables declaration//GEN-END:variables
}
