
package Gui;
import javax.swing.JOptionPane;
public class TransactionsPanel extends javax.swing.JPanel {
   
    private int savedTransactionID = 0;
    
    private int transactionID = 0;

public void setTransactionID(int id) {
    this.transactionID = id;
}

public int getSavedTransactionID() {
    return savedTransactionID;
}

private void clearTransactionFields() {
    CBXCUSTOMERID.setSelectedIndex(0);

    CBXMOTORCYCLEID.removeAllItems();
    CBXMOTORCYCLEID.addItem("-- Select Motorcycle --");

    CBXMECHANICID.setSelectedIndex(0);
    TXTTRANSACTIONDATE.setText("");
    TXTTOTALAMOUNT.setText("");
    CBXSTATUS.setSelectedIndex(0);

    tblTransactions.clearSelection();
}

private void loadTransactions() {
    String sql = "SELECT st.TransactionID, c.CustomerID, "
            + "st.MotorcycleID, st.MechanicID, st.TransactionDate, "
            + "st.TotalAmount, st.Status "
            + "FROM service_transaction st "
            + "JOIN motorcycle m ON st.MotorcycleID = m.MotorcycleID "
            + "JOIN customer c ON m.CustomerID = c.CustomerID "
            + "ORDER BY st.TransactionID DESC";

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel)
                    tblTransactions.getModel();

    model.setRowCount(0);

    try (java.sql.Connection con =
                 database.DBConnection.getConnection()) {

        if (con == null) {
            JOptionPane.showMessageDialog(this,
                    "Database connection failed!");
            return;
        }

        try (java.sql.PreparedStatement pst =
                     con.prepareStatement(sql);
             java.sql.ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                java.sql.Date date = rs.getDate("TransactionDate");

                model.addRow(new Object[]{
                    rs.getInt("TransactionID"),
                    rs.getInt("CustomerID"),
                    rs.getInt("MotorcycleID"),
                    rs.getInt("MechanicID"),
                    date == null ? "" :
                        new java.text.SimpleDateFormat("MM/dd/yyyy")
                                .format(date),
                    rs.getBigDecimal("TotalAmount"),
                    rs.getString("Status")
                });
            }
        }
    } catch (java.sql.SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error loading transactions: " + e.getMessage());
    }
}

private void loadCustomerCombo() {
    String sql = "SELECT CustomerID, FirstName, LastName "
               + "FROM customer ORDER BY CustomerID";

    try (
        java.sql.Connection con =
            database.DBConnection.getConnection();
        java.sql.PreparedStatement pst =
            con.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery()
    ) {
        CBXCUSTOMERID.removeAllItems();
        CBXCUSTOMERID.addItem("-- Select Customer --");

        while (rs.next()) {
            String item = rs.getInt("CustomerID") + " - "
                + rs.getString("FirstName") + " "
                + rs.getString("LastName");

            CBXCUSTOMERID.addItem(item);
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Error loading customers: " + e.getMessage()
        );
    }
}

private void loadMotorcycleCombo(int customerID) {
    CBXMOTORCYCLEID.removeAllItems();
    CBXMOTORCYCLEID.addItem("-- Select Motorcycle --");

    String sql = "SELECT MotorcycleID, Brand, Model, PlateNumber "
               + "FROM motorcycle WHERE CustomerID = ?";

    try (
        java.sql.Connection con = database.DBConnection.getConnection();
        java.sql.PreparedStatement pst = con.prepareStatement(sql)
    ) {
        pst.setInt(1, customerID);

        try (java.sql.ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String motorcycle =
                    rs.getInt("MotorcycleID") + " - "
                    + rs.getString("Brand") + " "
                    + rs.getString("Model") + " ("
                    + rs.getString("PlateNumber") + ")";

                CBXMOTORCYCLEID.addItem(motorcycle);
            }
        }
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Error loading motorcycles: " + e.getMessage()
        );
    }
}

private void loadMechanicCombo() {
    String sql = "SELECT MechanicID, FirstName, LastName "
               + "FROM mechanic ORDER BY FirstName";

    try (
        java.sql.Connection con =
            database.DBConnection.getConnection();
        java.sql.PreparedStatement pst =
            con.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery()
    ) {
        CBXMECHANICID.removeAllItems();
        CBXMECHANICID.addItem("-- Select Mechanic --");

        while (rs.next()) {
            String item = rs.getInt("MechanicID") + " - "
                + rs.getString("FirstName") + " "
                + rs.getString("LastName");

            CBXMECHANICID.addItem(item);
        }
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Mechanic error: " + e.getMessage()
        );
    }
}

private void setupSearchPlaceholder() {
    String placeholder = "Search transaction...";

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


    public TransactionsPanel() {
        initComponents();
        setupSearchPlaceholder();
        loadCustomerCombo();
        loadMechanicCombo();
        loadTransactions();
        
        
CBXMOTORCYCLEID.removeAllItems();
CBXMOTORCYCLEID.addItem("-- Select Motorcycle --");
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
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TXTTRANSACTIONDATE = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransactions = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        TXTSEARCH = new javax.swing.JTextField();
        BTNSEARCH = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        TXTTOTALAMOUNT = new javax.swing.JTextField();
        CBXSTATUS = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        CBXCUSTOMERID = new javax.swing.JComboBox<>();
        CBXMOTORCYCLEID = new javax.swing.JComboBox<>();
        CBXMECHANICID = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        jLabel1.setText("Service Transaction");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Record motorcycle repairs and track service progress.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setText("Transaction Information");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setText("Customer");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setText("Motorcycle");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel4.setText("Mechanic");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setText("Transaction Date");

        TXTTRANSACTIONDATE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSave.setBackground(new java.awt.Color(37, 99, 235));
        btnSave.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText(" Save Transaction");
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

        tblTransactions.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Customer ID", "Motorcycle ID", "Mechanic ID", "Date", "Total Amount", "Status"
            }
        ));
        tblTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransactionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransactions);

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setText("Transactions Records");

        TXTSEARCH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        BTNSEARCH.setBackground(new java.awt.Color(37, 99, 235));
        BTNSEARCH.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BTNSEARCH.setForeground(new java.awt.Color(255, 255, 255));
        BTNSEARCH.setText("Search");
        BTNSEARCH.addActionListener(this::BTNSEARCHActionPerformed);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel8.setText("Total Amount (₱)");

        TXTTOTALAMOUNT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        CBXSTATUS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBXSTATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "In Progress", "Completed" }));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel10.setText("Status");

        CBXCUSTOMERID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBXCUSTOMERID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBXCUSTOMERID.setPreferredSize(new java.awt.Dimension(72, 40));
        CBXCUSTOMERID.addActionListener(this::CBXCUSTOMERIDActionPerformed);

        CBXMOTORCYCLEID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBXMOTORCYCLEID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CBXMECHANICID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBXMECHANICID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                            .addComponent(TXTTRANSACTIONDATE, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                            .addComponent(CBXCUSTOMERID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TXTTOTALAMOUNT)
                                            .addComponent(CBXMOTORCYCLEID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                            .addComponent(CBXSTATUS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(CBXMECHANICID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(0, 87, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
                                .addComponent(TXTSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(BTNSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)))))
                .addContainerGap())
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
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBXCUSTOMERID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CBXMOTORCYCLEID, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBXMECHANICID, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CBXSTATUS)
                    .addComponent(TXTTOTALAMOUNT)
                    .addComponent(TXTTRANSACTIONDATE, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TXTSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTNSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(195, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    if (CBXCUSTOMERID.getSelectedIndex() <= 0
            || CBXMOTORCYCLEID.getSelectedIndex() <= 0
            || CBXMECHANICID.getSelectedIndex() <= 0
            || TXTTRANSACTIONDATE.getText().trim().isEmpty()
            || TXTTOTALAMOUNT.getText().trim().isEmpty()
            || CBXSTATUS.getSelectedIndex() < 0) {

        JOptionPane.showMessageDialog(this,
                "Please complete all transaction fields.");
        return;
    }

    try {
        int motorcycleID = Integer.parseInt(
                CBXMOTORCYCLEID.getSelectedItem().toString()
                        .split(" - ")[0].trim());

        int mechanicID = Integer.parseInt(
                CBXMECHANICID.getSelectedItem().toString()
                        .split(" - ")[0].trim());

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);

        java.util.Date parsedDate =
                sdf.parse(TXTTRANSACTIONDATE.getText().trim());

        java.sql.Date transactionDate =
                new java.sql.Date(parsedDate.getTime());

        java.math.BigDecimal amount =
                new java.math.BigDecimal(
                        TXTTOTALAMOUNT.getText().trim());

        if (amount.signum() < 0) {
            JOptionPane.showMessageDialog(this,
                    "Total amount cannot be negative.");
            return;
        }

        String sql = "INSERT INTO service_transaction "
                + "(MotorcycleID, MechanicID, TransactionDate, "
                + "TotalAmount, Status) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.Connection con =
                     database.DBConnection.getConnection()) {

            if (con == null) {
                JOptionPane.showMessageDialog(this,
                        "Database connection failed!");
                return;
            }

            try (java.sql.PreparedStatement pst =
                         con.prepareStatement(sql,
                             java.sql.Statement.RETURN_GENERATED_KEYS)) {

                pst.setInt(1, motorcycleID);
                pst.setInt(2, mechanicID);
                pst.setDate(3, transactionDate);
                pst.setBigDecimal(4, amount);
                pst.setString(5,
                        CBXSTATUS.getSelectedItem().toString());

                pst.executeUpdate();

                try (java.sql.ResultSet keys = pst.getGeneratedKeys()) {
                    if (keys.next()) {
                        savedTransactionID = keys.getInt(1);

                        JOptionPane.showMessageDialog(this,
                                "Transaction saved successfully!\n"
                                + "Transaction ID: "
                                + savedTransactionID);
                    }
                }
            }
        }

        loadTransactions();
        clearTransactionFields();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Error saving transaction: " + e.getMessage());
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void BTNSEARCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSEARCHActionPerformed
        // TODO add your handling code here:        
    String keyword = TXTSEARCH.getText().trim();

    if (keyword.equals("Search transaction...")) {
        keyword = "";
    }

    String sql = "SELECT st.TransactionID, c.CustomerID, "
            + "st.MotorcycleID, st.MechanicID, st.TransactionDate, "
            + "st.TotalAmount, st.Status "
            + "FROM service_transaction st "
            + "JOIN motorcycle m ON st.MotorcycleID=m.MotorcycleID "
            + "JOIN customer c ON m.CustomerID=c.CustomerID "
            + "WHERE CAST(st.TransactionID AS CHAR) LIKE ? "
            + "OR CAST(c.CustomerID AS CHAR) LIKE ? "
            + "OR CONCAT(c.FirstName, ' ', c.LastName) LIKE ? "
            + "OR CAST(st.MotorcycleID AS CHAR) LIKE ? "
            + "OR st.Status LIKE ? "
            + "ORDER BY st.TransactionID DESC";

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel)
                    tblTransactions.getModel();

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

            String search = "%" + keyword + "%";

            for (int i = 1; i <= 5; i++) {
                pst.setString(i, search);
            }

            try (java.sql.ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    java.sql.Date date =
                            rs.getDate("TransactionDate");

                    model.addRow(new Object[]{
                        rs.getInt("TransactionID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("MotorcycleID"),
                        rs.getInt("MechanicID"),
                        date == null ? "" :
                            new java.text.SimpleDateFormat("MM/dd/yyyy")
                                    .format(date),
                        rs.getBigDecimal("TotalAmount"),
                        rs.getString("Status")
                    });
                }
            }
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No matching transactions found.");
        }

    } catch (java.sql.SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Search error: " + e.getMessage());
    }
    }//GEN-LAST:event_BTNSEARCHActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransactionsMouseClicked
        // TODO add your handling code here:
    int row = tblTransactions.getSelectedRow();

    if (row < 0) {
        return;
    }

    int customerID = Integer.parseInt(
            tblTransactions.getValueAt(row, 1).toString());

    int motorcycleID = Integer.parseInt(
            tblTransactions.getValueAt(row, 2).toString());

    int mechanicID = Integer.parseInt(
            tblTransactions.getValueAt(row, 3).toString());

    String date = tblTransactions.getValueAt(row, 4).toString();
    String amount = tblTransactions.getValueAt(row, 5).toString();
    String status = tblTransactions.getValueAt(row, 6).toString();

    for (int i = 1; i < CBXCUSTOMERID.getItemCount(); i++) {
        if (CBXCUSTOMERID.getItemAt(i).startsWith(
                customerID + " - ")) {
            CBXCUSTOMERID.setSelectedIndex(i);
            break;
        }
    }

    loadMotorcycleCombo(customerID);

    for (int i = 1; i < CBXMOTORCYCLEID.getItemCount(); i++) {
        if (CBXMOTORCYCLEID.getItemAt(i).startsWith(
                motorcycleID + " - ")) {
            CBXMOTORCYCLEID.setSelectedIndex(i);
            break;
        }
    }

    for (int i = 1; i < CBXMECHANICID.getItemCount(); i++) {
        if (CBXMECHANICID.getItemAt(i).startsWith(
                mechanicID + " - ")) {
            CBXMECHANICID.setSelectedIndex(i);
            break;
        }
    }

    TXTTRANSACTIONDATE.setText(date);
    TXTTOTALAMOUNT.setText(amount);
    CBXSTATUS.setSelectedItem(status);
    }//GEN-LAST:event_tblTransactionsMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    int row = tblTransactions.getSelectedRow();

    if (row < 0) {
        JOptionPane.showMessageDialog(this,
                "Select a transaction from the table first.");
        return;
    }

    if (CBXMOTORCYCLEID.getSelectedIndex() <= 0
            || CBXMECHANICID.getSelectedIndex() <= 0
            || TXTTRANSACTIONDATE.getText().trim().isEmpty()
            || TXTTOTALAMOUNT.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Please complete all fields.");
        return;
    }

    try {
        int id = Integer.parseInt(
                tblTransactions.getValueAt(row, 0).toString());

        int motorcycleID = Integer.parseInt(
                CBXMOTORCYCLEID.getSelectedItem().toString()
                        .split(" - ")[0].trim());

        int mechanicID = Integer.parseInt(
                CBXMECHANICID.getSelectedItem().toString()
                        .split(" - ")[0].trim());

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);

        java.sql.Date date = new java.sql.Date(
                sdf.parse(TXTTRANSACTIONDATE.getText().trim())
                        .getTime());

        java.math.BigDecimal amount =
                new java.math.BigDecimal(
                        TXTTOTALAMOUNT.getText().trim());

        if (amount.signum() < 0) {
            JOptionPane.showMessageDialog(this,
                    "Total amount cannot be negative.");
            return;
        }

        String sql = "UPDATE service_transaction SET "
                + "MotorcycleID=?, MechanicID=?, TransactionDate=?, "
                + "TotalAmount=?, Status=? WHERE TransactionID=?";

        try (java.sql.Connection con =
         database.DBConnection.getConnection()) {

    if (con == null) {
        JOptionPane.showMessageDialog(this,
                "Database connection failed!");
        return;
    }

    try (java.sql.PreparedStatement pst =
             con.prepareStatement(sql)) {

        pst.setInt(1, motorcycleID);
        pst.setInt(2, mechanicID);
        pst.setDate(3, date);
        pst.setBigDecimal(4, amount);
        pst.setString(5,
                CBXSTATUS.getSelectedItem().toString());
        pst.setInt(6, id);

        pst.executeUpdate();
    }
}
        JOptionPane.showMessageDialog(this,
                "Transaction updated successfully!");

        loadTransactions();
        clearTransactionFields();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Error updating transaction: " + e.getMessage());
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
   
int selectedRow = tblTransactions.getSelectedRow();

if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Please select a transaction first.");
    return;
}

int modelRow = tblTransactions.convertRowIndexToModel(selectedRow);
int transactionID = Integer.parseInt(
    tblTransactions.getModel().getValueAt(modelRow, 0).toString()
);

int confirm = JOptionPane.showConfirmDialog(
    this,
    "Are you sure you want to archive this transaction?",
    "Confirm Archive",
    JOptionPane.YES_NO_OPTION
);

if (confirm != JOptionPane.YES_OPTION) {
    return;
}

String sql = "UPDATE service_transaction SET IsArchived = 1 WHERE TransactionID = ?";

try (java.sql.Connection con = database.DBConnection.getConnection();
     java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

    pst.setInt(1, transactionID);

    if (pst.executeUpdate() > 0) {
        JOptionPane.showMessageDialog(this, "Transaction archived successfully!");
        loadTransactions();
    } else {
        JOptionPane.showMessageDialog(this, "Transaction not found.");
    }

} catch (java.sql.SQLException e) {
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
}

    }//GEN-LAST:event_btnRemoveActionPerformed

    private void CBXCUSTOMERIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXCUSTOMERIDActionPerformed
        // TODO add your handling code here:    
    CBXMOTORCYCLEID.removeAllItems();
    CBXMOTORCYCLEID.addItem("-- Select Motorcycle --");

    Object selected = CBXCUSTOMERID.getSelectedItem();

    if (selected == null || selected.toString().startsWith("--")) {
        return;
    }

    int customerID = Integer.parseInt(
        selected.toString().split(" - ")[0].trim()
    );

    loadMotorcycleCombo(customerID);
    }//GEN-LAST:event_CBXCUSTOMERIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSEARCH;
    private javax.swing.JComboBox<String> CBXCUSTOMERID;
    private javax.swing.JComboBox<String> CBXMECHANICID;
    private javax.swing.JComboBox<String> CBXMOTORCYCLEID;
    private javax.swing.JComboBox<String> CBXSTATUS;
    private javax.swing.JTextField TXTSEARCH;
    private javax.swing.JTextField TXTTOTALAMOUNT;
    private javax.swing.JTextField TXTTRANSACTIONDATE;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransactions;
    // End of variables declaration//GEN-END:variables
}
