
package Gui;
import javax.swing.JOptionPane;
public class Dashboard extends javax.swing.JPanel {

   private void loadDashboardCounts() {
    String customerSQL = "SELECT COUNT(*) FROM customer";
    String motorcycleSQL = "SELECT COUNT(*) FROM motorcycle";
    String transactionSQL = "SELECT COUNT(*) FROM service_transaction";
    String partsSQL = "SELECT COALESCE(SUM(StockQuantity), 0) FROM parts";

    try (java.sql.Connection con = database.DBConnection.getConnection()) {

        if (con == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Database connection failed!");
            return;
        }

        try (java.sql.Statement st = con.createStatement();
             java.sql.ResultSet rs = st.executeQuery(customerSQL)) {
            if (rs.next()) {
                lblTotalCustomers.setText(rs.getString(1));
            }
        }

        try (java.sql.Statement st = con.createStatement();
             java.sql.ResultSet rs = st.executeQuery(motorcycleSQL)) {
            if (rs.next()) {
                lblTotalMotorcycles.setText(rs.getString(1));
            }
        }

        try (java.sql.Statement st = con.createStatement();
             java.sql.ResultSet rs = st.executeQuery(transactionSQL)) {
            if (rs.next()) {
                lblTotalTransactions.setText(rs.getString(1));
            }
        }

        try (java.sql.Statement st = con.createStatement();
             java.sql.ResultSet rs = st.executeQuery(partsSQL)) {
            if (rs.next()) {
                lblPartsInStock.setText(rs.getString(1));
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Error loading dashboard counts: " + e.getMessage()
        );
    }
}
   
   private void loadRecentTransactions() {
    String sql =
        "SELECT st.TransactionID, " +
        "CONCAT(c.FirstName, ' ', c.LastName) AS CustomerName, " +
        "CONCAT(m.Brand, ' ', m.Model) AS Motorcycle, " +
        "st.TransactionDate, st.Status " +
        "FROM service_transaction st " +
        "JOIN motorcycle m ON st.MotorcycleID = m.MotorcycleID " +
        "JOIN customer c ON m.CustomerID = c.CustomerID " +
        "ORDER BY st.TransactionID DESC LIMIT 10";

    try (
        java.sql.Connection con = database.DBConnection.getConnection();
        java.sql.PreparedStatement pst = con.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery()
    ) {
        javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) tblRecentTransactions.getModel();

        model.setRowCount(0);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("TransactionID"),
                rs.getString("CustomerName"),
                rs.getString("Motorcycle"),
                rs.getString("TransactionDate"),
                rs.getString("Status")
            });
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Error loading recent transactions: " + e.getMessage()
        );
    }
}
   
    public Dashboard() {
        initComponents();
        loadDashboardCounts();
         loadRecentTransactions();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jpanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblTotalCustomers = new javax.swing.JLabel();
        jpanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalMotorcycles = new javax.swing.JLabel();
        jpanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblTotalTransactions = new javax.swing.JLabel();
        jpanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblPartsInStock = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecentTransactions = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        jLabel1.setText("Dashboard");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Shop overview");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jpanel1.setPreferredSize(new java.awt.Dimension(240, 130));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("Total Customers");

        lblTotalCustomers.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblTotalCustomers.setText("0");

        javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
        jpanel1.setLayout(jpanel1Layout);
        jpanel1Layout.setHorizontalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lblTotalCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jpanel1Layout.setVerticalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblTotalCustomers)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jpanel2.setPreferredSize(new java.awt.Dimension(240, 130));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setText("Total Motorcycles");

        lblTotalMotorcycles.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblTotalMotorcycles.setText("0");

        javax.swing.GroupLayout jpanel2Layout = new javax.swing.GroupLayout(jpanel2);
        jpanel2.setLayout(jpanel2Layout);
        jpanel2Layout.setHorizontalGroup(
            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel2Layout.createSequentialGroup()
                .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(lblTotalMotorcycles, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jpanel2Layout.setVerticalGroup(
            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lblTotalMotorcycles)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jpanel3.setPreferredSize(new java.awt.Dimension(240, 130));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Service Transactions");

        lblTotalTransactions.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblTotalTransactions.setText("0");

        javax.swing.GroupLayout jpanel3Layout = new javax.swing.GroupLayout(jpanel3);
        jpanel3.setLayout(jpanel3Layout);
        jpanel3Layout.setHorizontalGroup(
            jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel3Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel3Layout.createSequentialGroup()
                        .addComponent(lblTotalTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))))
        );
        jpanel3Layout.setVerticalGroup(
            jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(lblTotalTransactions)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("Parts in Stock");

        lblPartsInStock.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblPartsInStock.setText("0");

        javax.swing.GroupLayout jpanel4Layout = new javax.swing.GroupLayout(jpanel4);
        jpanel4.setLayout(jpanel4Layout);
        jpanel4Layout.setHorizontalGroup(
            jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel4Layout.createSequentialGroup()
                .addGroup(jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel4Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanel4Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(lblPartsInStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jpanel4Layout.setVerticalGroup(
            jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(lblPartsInStock)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        tblRecentTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Customer", "Motorcycle", "Date", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblRecentTransactions);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setText("Recent Service Transactions");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jpanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 58, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JPanel jpanel3;
    private javax.swing.JPanel jpanel4;
    private javax.swing.JLabel lblPartsInStock;
    private javax.swing.JLabel lblTotalCustomers;
    private javax.swing.JLabel lblTotalMotorcycles;
    private javax.swing.JLabel lblTotalTransactions;
    private javax.swing.JTable tblRecentTransactions;
    // End of variables declaration//GEN-END:variables
}
