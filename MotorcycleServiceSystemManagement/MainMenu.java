
package Gui;
import java.awt.CardLayout;
import javax.swing.JPanel;
public class MainMenu extends javax.swing.JPanel {

    
    private void showDashboard() {
    pnlDashboard.removeAll();
    pnlDashboard.setLayout(new java.awt.BorderLayout());

    Dashboard dashboard = new Dashboard();
    pnlDashboard.add(dashboard, java.awt.BorderLayout.CENTER);

    pnlDashboard.revalidate();
    pnlDashboard.repaint();
}
    
    
    
    public MainMenu() {
    initComponents();
    showDashboard();
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlsidebar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnDASHBOARD = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        BTNMOTORCYCLE = new javax.swing.JButton();
        MECHANICBTN = new javax.swing.JButton();
        SERVICEBTN = new javax.swing.JButton();
        TRANSACTIONBTN = new javax.swing.JButton();
        SERVICEDETAILBTN = new javax.swing.JButton();
        PARTSBTN = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        BTNLOGOUT = new javax.swing.JButton();
        BTNARCHIVE = new javax.swing.JButton();
        PAYMENTSBTN = new javax.swing.JButton();
        pnlDashboard = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlTotalCustomers = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblRecentTransactions = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecentTransactions = new javax.swing.JTable();
        pnlTotalMotorcycles = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnlPartsStock = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnlAvailableServices = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlQuickActions = new javax.swing.JPanel();
        BTNADDCUSTOMER = new javax.swing.JButton();
        BTNADDMOTORCYCLE = new javax.swing.JButton();
        BTNNEWTRANSACTION = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 51));
        jLabel1.setText("MOTORCARE");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Motor Parts & Service Management System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1622, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pnlsidebar.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MAIN MENU");

        btnDASHBOARD.setBackground(new java.awt.Color(37, 99, 235));
        btnDASHBOARD.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        btnDASHBOARD.setForeground(new java.awt.Color(255, 255, 255));
        btnDASHBOARD.setText("Dashboard");
        btnDASHBOARD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDASHBOARD.addActionListener(this::btnDASHBOARDActionPerformed);

        btnCustomer.setBackground(new java.awt.Color(37, 99, 235));
        btnCustomer.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        btnCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btnCustomer.setText("Customers");
        btnCustomer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCustomer.addActionListener(this::btnCustomerActionPerformed);

        BTNMOTORCYCLE.setBackground(new java.awt.Color(37, 99, 235));
        BTNMOTORCYCLE.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        BTNMOTORCYCLE.setForeground(new java.awt.Color(255, 255, 255));
        BTNMOTORCYCLE.setText("Motorcycles");
        BTNMOTORCYCLE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTNMOTORCYCLE.addActionListener(this::BTNMOTORCYCLEActionPerformed);

        MECHANICBTN.setBackground(new java.awt.Color(37, 99, 235));
        MECHANICBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        MECHANICBTN.setForeground(new java.awt.Color(255, 255, 255));
        MECHANICBTN.setText("Mechanic");
        MECHANICBTN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MECHANICBTN.addActionListener(this::MECHANICBTNActionPerformed);

        SERVICEBTN.setBackground(new java.awt.Color(37, 99, 235));
        SERVICEBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        SERVICEBTN.setForeground(new java.awt.Color(255, 255, 255));
        SERVICEBTN.setText("Service");
        SERVICEBTN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SERVICEBTN.addActionListener(this::SERVICEBTNActionPerformed);

        TRANSACTIONBTN.setBackground(new java.awt.Color(37, 99, 235));
        TRANSACTIONBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        TRANSACTIONBTN.setForeground(new java.awt.Color(255, 255, 255));
        TRANSACTIONBTN.setText("Transactions");
        TRANSACTIONBTN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TRANSACTIONBTN.addActionListener(this::TRANSACTIONBTNActionPerformed);

        SERVICEDETAILBTN.setBackground(new java.awt.Color(37, 99, 235));
        SERVICEDETAILBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        SERVICEDETAILBTN.setForeground(new java.awt.Color(255, 255, 255));
        SERVICEDETAILBTN.setText("Service Detail");
        SERVICEDETAILBTN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SERVICEDETAILBTN.addActionListener(this::SERVICEDETAILBTNActionPerformed);

        PARTSBTN.setBackground(new java.awt.Color(37, 99, 235));
        PARTSBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        PARTSBTN.setForeground(new java.awt.Color(255, 255, 255));
        PARTSBTN.setText("Motor Parts");
        PARTSBTN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PARTSBTN.addActionListener(this::PARTSBTNActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 5)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("_________________________________________________________________________________________________________________________________________________");

        BTNLOGOUT.setBackground(new java.awt.Color(204, 0, 0));
        BTNLOGOUT.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        BTNLOGOUT.setText("Log-out");
        BTNLOGOUT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTNLOGOUT.addActionListener(this::BTNLOGOUTActionPerformed);

        BTNARCHIVE.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        BTNARCHIVE.setText("Archive");
        BTNARCHIVE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTNARCHIVE.addActionListener(this::BTNARCHIVEActionPerformed);

        PAYMENTSBTN.setBackground(new java.awt.Color(37, 99, 235));
        PAYMENTSBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        PAYMENTSBTN.setForeground(new java.awt.Color(255, 255, 255));
        PAYMENTSBTN.setText("Payments");
        PAYMENTSBTN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PAYMENTSBTN.addActionListener(this::PAYMENTSBTNActionPerformed);

        javax.swing.GroupLayout pnlsidebarLayout = new javax.swing.GroupLayout(pnlsidebar);
        pnlsidebar.setLayout(pnlsidebarLayout);
        pnlsidebarLayout.setHorizontalGroup(
            pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlsidebarLayout.createSequentialGroup()
                .addGroup(pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlsidebarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                    .addGroup(pnlsidebarLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDASHBOARD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTNMOTORCYCLE, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MECHANICBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SERVICEBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TRANSACTIONBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SERVICEDETAILBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PARTSBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PAYMENTSBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlsidebarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTNLOGOUT, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(BTNARCHIVE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlsidebarLayout.setVerticalGroup(
            pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlsidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDASHBOARD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(BTNMOTORCYCLE, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(MECHANICBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(SERVICEBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(TRANSACTIONBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(SERVICEDETAILBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(PARTSBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(PAYMENTSBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(BTNARCHIVE, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTNLOGOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel5.setText("Dashboard");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setText("Welcome back, Administrator! Here's your shop overview.");

        pnlTotalCustomers.setBackground(new java.awt.Color(255, 153, 153));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Total Customers");

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel9.setText("0");

        javax.swing.GroupLayout pnlTotalCustomersLayout = new javax.swing.GroupLayout(pnlTotalCustomers);
        pnlTotalCustomers.setLayout(pnlTotalCustomersLayout);
        pnlTotalCustomersLayout.setHorizontalGroup(
            pnlTotalCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalCustomersLayout.createSequentialGroup()
                .addGroup(pnlTotalCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTotalCustomersLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTotalCustomersLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTotalCustomersLayout.setVerticalGroup(
            pnlTotalCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalCustomersLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblRecentTransactions.setBackground(new java.awt.Color(51, 204, 255));

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel16.setText("Recent Service Transactions");

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

        javax.swing.GroupLayout lblRecentTransactionsLayout = new javax.swing.GroupLayout(lblRecentTransactions);
        lblRecentTransactions.setLayout(lblRecentTransactionsLayout);
        lblRecentTransactionsLayout.setHorizontalGroup(
            lblRecentTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblRecentTransactionsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(lblRecentTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        lblRecentTransactionsLayout.setVerticalGroup(
            lblRecentTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblRecentTransactionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pnlTotalMotorcycles.setBackground(new java.awt.Color(255, 153, 153));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Total Motorcycles");

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel11.setText("0");

        javax.swing.GroupLayout pnlTotalMotorcyclesLayout = new javax.swing.GroupLayout(pnlTotalMotorcycles);
        pnlTotalMotorcycles.setLayout(pnlTotalMotorcyclesLayout);
        pnlTotalMotorcyclesLayout.setHorizontalGroup(
            pnlTotalMotorcyclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalMotorcyclesLayout.createSequentialGroup()
                .addGroup(pnlTotalMotorcyclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTotalMotorcyclesLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel10))
                    .addGroup(pnlTotalMotorcyclesLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        pnlTotalMotorcyclesLayout.setVerticalGroup(
            pnlTotalMotorcyclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalMotorcyclesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pnlPartsStock.setBackground(new java.awt.Color(255, 153, 153));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setText("Parts Stock");

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel15.setText("0");

        javax.swing.GroupLayout pnlPartsStockLayout = new javax.swing.GroupLayout(pnlPartsStock);
        pnlPartsStock.setLayout(pnlPartsStockLayout);
        pnlPartsStockLayout.setHorizontalGroup(
            pnlPartsStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPartsStockLayout.createSequentialGroup()
                .addGroup(pnlPartsStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPartsStockLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel14))
                    .addGroup(pnlPartsStockLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPartsStockLayout.setVerticalGroup(
            pnlPartsStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPartsStockLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pnlAvailableServices.setBackground(new java.awt.Color(255, 153, 153));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Available Services");

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel13.setText("0");

        javax.swing.GroupLayout pnlAvailableServicesLayout = new javax.swing.GroupLayout(pnlAvailableServices);
        pnlAvailableServices.setLayout(pnlAvailableServicesLayout);
        pnlAvailableServicesLayout.setHorizontalGroup(
            pnlAvailableServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvailableServicesLayout.createSequentialGroup()
                .addGroup(pnlAvailableServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAvailableServicesLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel12))
                    .addGroup(pnlAvailableServicesLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        pnlAvailableServicesLayout.setVerticalGroup(
            pnlAvailableServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvailableServicesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        jLabel7.setText("__________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");

        pnlQuickActions.setBackground(new java.awt.Color(51, 255, 102));

        BTNADDCUSTOMER.setBackground(new java.awt.Color(37, 99, 235));
        BTNADDCUSTOMER.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BTNADDCUSTOMER.setForeground(new java.awt.Color(255, 255, 255));
        BTNADDCUSTOMER.setText("+ Add Customer");
        BTNADDCUSTOMER.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTNADDCUSTOMER.setFocusable(false);
        BTNADDCUSTOMER.addActionListener(this::BTNADDCUSTOMERActionPerformed);

        BTNADDMOTORCYCLE.setBackground(new java.awt.Color(37, 99, 235));
        BTNADDMOTORCYCLE.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BTNADDMOTORCYCLE.setForeground(new java.awt.Color(255, 255, 255));
        BTNADDMOTORCYCLE.setText("+ Add Motorcycle");
        BTNADDMOTORCYCLE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTNADDMOTORCYCLE.setFocusable(false);

        BTNNEWTRANSACTION.setBackground(new java.awt.Color(37, 99, 235));
        BTNNEWTRANSACTION.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BTNNEWTRANSACTION.setForeground(new java.awt.Color(255, 255, 255));
        BTNNEWTRANSACTION.setText("+ New Transaction");
        BTNNEWTRANSACTION.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTNNEWTRANSACTION.setFocusable(false);

        javax.swing.GroupLayout pnlQuickActionsLayout = new javax.swing.GroupLayout(pnlQuickActions);
        pnlQuickActions.setLayout(pnlQuickActionsLayout);
        pnlQuickActionsLayout.setHorizontalGroup(
            pnlQuickActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuickActionsLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(BTNADDCUSTOMER, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(BTNADDMOTORCYCLE, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(BTNNEWTRANSACTION, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        pnlQuickActionsLayout.setVerticalGroup(
            pnlQuickActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuickActionsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlQuickActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNADDCUSTOMER, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNADDMOTORCYCLE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNNEWTRANSACTION, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnlQuickActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDashboardLayout.createSequentialGroup()
                            .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlDashboardLayout.createSequentialGroup()
                                    .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pnlTotalCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnlAvailableServices, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(186, 186, 186)
                                    .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pnlTotalMotorcycles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnlPartsStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGap(149, 149, 149)))
                    .addComponent(lblRecentTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTotalMotorcycles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTotalCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAvailableServices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPartsStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(lblRecentTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlQuickActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlsidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlsidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(pnlDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTNADDCUSTOMERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNADDCUSTOMERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNADDCUSTOMERActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        // TODO add your handling code here:
        pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new CustomerPanel1(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnDASHBOARDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDASHBOARDActionPerformed
        // TODO add your handling code here:
        pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new Dashboard(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_btnDASHBOARDActionPerformed

    private void BTNMOTORCYCLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNMOTORCYCLEActionPerformed
        // TODO add your handling code here:
        pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new MotorcyclePanel1(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_BTNMOTORCYCLEActionPerformed

    private void MECHANICBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MECHANICBTNActionPerformed
        // TODO add your handling code here:
        pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new MechanicPanel11(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_MECHANICBTNActionPerformed

    private void SERVICEBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SERVICEBTNActionPerformed
        // TODO add your handling code here:
        pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new ServicePanel11(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_SERVICEBTNActionPerformed

    private void TRANSACTIONBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRANSACTIONBTNActionPerformed
        // TODO add your handling code here:
       pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new TransactionsPanel(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_TRANSACTIONBTNActionPerformed

    private void SERVICEDETAILBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SERVICEDETAILBTNActionPerformed
        // TODO add your handling code here:
      pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new ServiceDetail1(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_SERVICEDETAILBTNActionPerformed

    private void PARTSBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PARTSBTNActionPerformed
        // TODO add your handling code here:
        pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new MotorPartsPanel(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_PARTSBTNActionPerformed

    private void BTNLOGOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNLOGOUTActionPerformed
        // TODO add your handling code here:
      
int confirm = javax.swing.JOptionPane.showConfirmDialog(
    this,
    "Are you sure you want to log out?",
    "Confirm Logout",
    javax.swing.JOptionPane.YES_NO_OPTION
);

if (confirm == javax.swing.JOptionPane.YES_OPTION) {
    System.exit(0);
}
    }//GEN-LAST:event_BTNLOGOUTActionPerformed

    private void BTNARCHIVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNARCHIVEActionPerformed
        // TODO add your handling code here:
pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new ArchiveForm(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();

    }//GEN-LAST:event_BTNARCHIVEActionPerformed

    private void PAYMENTSBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PAYMENTSBTNActionPerformed
        // TODO add your handling code here:
        pnlDashboard.removeAll();
pnlDashboard.setLayout(new java.awt.BorderLayout());
pnlDashboard.add(new PaymentPanel(), java.awt.BorderLayout.CENTER);
pnlDashboard.revalidate();
pnlDashboard.repaint();
    }//GEN-LAST:event_PAYMENTSBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNADDCUSTOMER;
    private javax.swing.JButton BTNADDMOTORCYCLE;
    private javax.swing.JButton BTNARCHIVE;
    private javax.swing.JButton BTNLOGOUT;
    private javax.swing.JButton BTNMOTORCYCLE;
    private javax.swing.JButton BTNNEWTRANSACTION;
    private javax.swing.JButton MECHANICBTN;
    private javax.swing.JButton PARTSBTN;
    private javax.swing.JButton PAYMENTSBTN;
    private javax.swing.JButton SERVICEBTN;
    private javax.swing.JButton SERVICEDETAILBTN;
    private javax.swing.JButton TRANSACTIONBTN;
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnDASHBOARD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lblRecentTransactions;
    private javax.swing.JPanel pnlAvailableServices;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlPartsStock;
    private javax.swing.JPanel pnlQuickActions;
    private javax.swing.JPanel pnlTotalCustomers;
    private javax.swing.JPanel pnlTotalMotorcycles;
    private javax.swing.JPanel pnlsidebar;
    private javax.swing.JTable tblRecentTransactions;
    // End of variables declaration//GEN-END:variables

}
