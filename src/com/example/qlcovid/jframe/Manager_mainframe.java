/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.qlcovid.jframe;

import com.example.qlcovid.model.ManagerDB;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nhonnhon
 */
public class Manager_mainframe extends javax.swing.JFrame {
    String ManagerID;
    Vector<String> Column1 = new Vector<String>();
    String [] columnNames2 = {"Package ID", "Name", "Limit", "Start date", "End date", "Price"};
    DefaultTableModel tbmodel1, tbmodel2;
    DefaultComboBoxModel cbmodel1search, cbmodel1sort, cbmodel2search, cbmodel2sort;
    ManagerDB db = new ManagerDB();
    String queryOfRelated;
    public Manager_mainframe(String mgID) {
        ManagerID = mgID;
        initComponents();
        this.setLocationRelativeTo(null);
        try {
			initUI();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("initUI line41 main_frame");
		}
        this.setTitle("Covid Management");
        queryOfRelated = "";
    }
    void initUI() throws SQLException{
    //!!!!!TAB 01!!!!!!!//
        //init checkbox button
        cbid1.setSelected(true);
        cbname1.setSelected(true);
        cbdob1.setSelected(true);
        cbaddress1.setSelected(true);
        cbcondition1.setSelected(true);
        cbplace1.setSelected(true);
        cbrelated1.setSelected(true);
        cbid1.setVisible(false);
        cbname1.setVisible(false);
        cbdob1.setVisible(false);
        cbaddress1.setVisible(false);
        cbcondition1.setVisible(false);
        cbplace1.setVisible(false);
        cbrelated1.setVisible(false);
        jLabel7.setVisible(false);
        btnup1.setEnabled(false);
        btndown1.setEnabled(false);
        btncancel1.setEnabled(false);
        btnupdate1.setEnabled(false);
        btnupdate2.setEnabled(false);
        btnremove2.setEnabled(false);
        
        //init table
        Object[][] data = db.getdata(getSelected1() + " where condition  is not null ");
        tbmodel1 = new DefaultTableModel(data, Column1.toArray()){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        tb1.setModel(tbmodel1);
        Object[] noCol1 = new Object[tbmodel1.getRowCount()];
        for(int i = 0; i<tbmodel1.getRowCount() ; i++) noCol1[i] = i + 1;
        tbmodel1.addColumn("No.", noCol1);
        tb1.setModel(tbmodel1);
        tb1.moveColumn(tb1.getColumnCount()-1, 0);
        
        //init combobox
        cbmodel1search = new DefaultComboBoxModel(Column1);
        cbmodel1sort = new DefaultComboBoxModel(Column1);
        cbsearch1.setModel(cbmodel1search);
        cbsort1.setModel(cbmodel1sort);
        
    //!!!!!TAB 02!!!!!!!//
        Object[][] data2 = db.getdata(getSelected2());
        tbmodel2 = new DefaultTableModel(data2, columnNames2){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        tb2.setModel(tbmodel2);
        Object[] noCol2 = new Object[tbmodel2.getRowCount()];
        for(int i = 0; i<tbmodel2.getRowCount() ; i++) noCol2[i] = i + 1;
        tbmodel2.addColumn("No.", noCol2);
        tb2.setModel(tbmodel2);
        tb2.moveColumn(tb2.getColumnCount()-1, 0);
        
        //init combobox
        cbmodel2search = new DefaultComboBoxModel(columnNames2);
        cbmodel2sort = new DefaultComboBoxModel(columnNames2);
        cbsearch2.setModel(cbmodel2search);
        cbsort2.setModel(cbmodel2sort);
        
    //!!!!!TAB 03!!!!!!!//
        initStat();
    }
    void initStat() throws SQLException{
        
    //--------------table1-----------------//
        f0.setText(db.get("select count(citizen_id) from covid_patient where condition = 'F0'"));
        f1.setText(db.get("select count(citizen_id) from covid_patient where condition = 'F1'"));
        f2.setText(db.get("select count(citizen_id) from covid_patient where condition = 'F2'"));
        f3.setText(db.get("select count(citizen_id) from covid_patient where condition = 'F3'"));
        cursed.setText(db.get("select count(patient_history_id) from patient_history where patient_action = 'cured'"));
        f1tof0.setText(db.get("select count(patient_history_id) from patient_history where patient_action = 'tof0'"));
        
    //--------------table2-----------------//
        typeofpackage.setText(db.get("select count(package_id) from package"));
        debt.setText(db.get("select sum(price) from ql_order join package on package.package_id = ql_order.package_id"));
    //--------------table3-----------------//
        Object[][] objs3;
        DefaultTableModel model3;    
        objs3 = db.getdata("select package.name, count(ql_order.package_id) from ql_order join package on package.package_id = ql_order.package_id group by package.name");
        String[] name3 = {"Package", "Number"};
        model3 = new DefaultTableModel(objs3,name3){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        stat3.setModel(model3);
        
    }
    String getSelected2(){
        return "select * from package";
    }
    void resettable2() throws SQLException{
        String newqr = "";
        newqr += getSelected2();
        newqr += getWhere2();
        newqr += getSort2();
        Object[][] data = db.getdata(newqr);
        tbmodel2 = new DefaultTableModel(data, columnNames2);
        tb2.setModel(tbmodel2);
        Object[] noCol1 = new Object[tbmodel2.getRowCount()];
        for(int i = 0; i<tbmodel2.getRowCount() ; i++) noCol1[i] = i + 1;
        tbmodel2.addColumn("No.", noCol1);
        tb2.setModel(tbmodel2);
        tb2.moveColumn(tb2.getColumnCount()-1, 0);
    }
    void resettable1() throws SQLException{
        String newqr = "";
        newqr += getSelected1();
        newqr += getWhere1();
        newqr += getSort1();
        Object[][] data = db.getdata(newqr);
        tbmodel1 = new DefaultTableModel(data, Column1.toArray());
        tb1.setModel(tbmodel1);
        Object[] noCol1 = new Object[tbmodel1.getRowCount()];
        for(int i = 0; i<tbmodel1.getRowCount() ; i++) noCol1[i] = i + 1;
        tbmodel1.addColumn("No.", noCol1);
        tb1.setModel(tbmodel1);
        tb1.moveColumn(tb1.getColumnCount()-1, 0);
    }
    String getcurquery(){
        String newqr = "";
        newqr += getSelected1();
        newqr += getWhere1();
        newqr += getSort1();
        return newqr;
    }
    String getSort1(){
        if(btnsort1.isSelected()){
            String newqr = " order by ";
            newqr += tocolname(cbsort1.getSelectedItem().toString()) + " ";
            if(cksort1.isSelected()) newqr += "desc ";
            return newqr;
        }
        return "";
    }
    String getSort2(){
        if(btnsort2.isSelected()){
            String newqr = " order by ";
            newqr += tocolname(cbsort2.getSelectedItem().toString()) + " ";
            if(cksort2.isSelected()) newqr += "desc ";
            return newqr;
        }
        return "";
    }
    String getWhere1(){
        String newqr = " where condition  is not null ";
        if(!"".equals(queryOfRelated)) {newqr += " and " + queryOfRelated;}
        
        if(btnsearch1.isSelected()){
            if(tbsearch1.getText().isEmpty() || tbsearch1.getText().length() ==0){
                JOptionPane.showMessageDialog(null, "Empty search!");
                this.btnsearch1.setSelected(false);
                return newqr;
            }
            newqr += " and ";
            if(tocolname(cbsearch1.getSelectedItem().toString()).equals("full_name")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " like N'%" + tbsearch1.getText()+ "%' ";
            else if(tocolname(cbsearch1.getSelectedItem().toString()).equals("citizen_address")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " like N'%" + tbsearch1.getText()+ "%' ";
            else if(tocolname(cbsearch1.getSelectedItem().toString()).equals("treatment_place.treatment_place_name")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " like N'%" + tbsearch1.getText()+ "%' ";
            else if(tocolname(cbsearch1.getSelectedItem().toString()).equals("date_of_birth")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " like '%" + tbsearch1.getText()+ "%' ";
            else if(tocolname(cbsearch1.getSelectedItem().toString()).equals("condition")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " = '" + tbsearch1.getText()+ "' ";
            else  
                try{
                    //newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " = '" + String.valueOf(Integer.valueOf(tbsearch1.getText()))+ "' ";
                	newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " = '" + String.valueOf(tbsearch1.getText())+ "' ";
                    System.out.println(String.valueOf(Integer.valueOf(tbsearch1.getText())));
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "ID must be integer!");
                    newqr = " where condition  is not null ";
                }
        }
        return newqr;
    }
    String getWhere2(){     
        String newqr = "";
        if(btnsearch2.isSelected()){
            if(tbsearch2.getText().isEmpty() || tbsearch2.getText().length() ==0){
                JOptionPane.showMessageDialog(null, "Empty search!");
                this.btnsearch2.setSelected(false);
                return newqr;
            }
            newqr += " where ";
            if(tocolname(cbsearch2.getSelectedItem().toString()).equals("name")) newqr+= tocolname(cbsearch2.getSelectedItem().toString()) + " like N'%" + tbsearch2.getText()+ "%' ";
            else if(tocolname(cbsearch2.getSelectedItem().toString()).equals("package_start")) newqr+= tocolname(cbsearch2.getSelectedItem().toString()) + " like N'%" + tbsearch2.getText()+ "%' ";
            else if(tocolname(cbsearch2.getSelectedItem().toString()).equals("package_end")) newqr+= tocolname(cbsearch2.getSelectedItem().toString()) + " like N'%" + tbsearch2.getText()+ "%' ";
            else if(tocolname(cbsearch2.getSelectedItem().toString()).equals("package_id")) newqr+= tocolname(cbsearch2.getSelectedItem().toString()) + " = '" + tbsearch2.getText()+ "' ";
            else  
                try{
                    newqr+= tocolname(cbsearch2.getSelectedItem().toString()) + " = '" + String.valueOf(Integer.valueOf(tbsearch2.getText()))+ "' ";
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Limit and price must be integer!");
                    newqr = "";
                }
        }
        return newqr;
    }
    String getSelected1(){
        String newqr = "select ";
        Column1 = new Vector<String>();
        if(cbid1.isSelected()){ //citizenid
            newqr += " citizen_id, ";
            Column1.add("Citizen ID");
        }
        if(cbname1.isSelected()){ //citizenid
            newqr += " full_name, ";
            Column1.add("Full name");
        }
        if(cbdob1.isSelected()){ //citizenid
            newqr += " date_of_birth, ";
            Column1.add("Date of birth");
        }
        if(cbaddress1.isSelected()){ //citizenid
            newqr += " citizen_address, ";
            Column1.add("Address");
        }
        if(cbcondition1.isSelected()){ //citizenid
            newqr += " condition, ";
            Column1.add("Condition");
        }
        if(cbplace1.isSelected()){
            newqr += " treatment_place_name, ";
            Column1.add("Treatment place");
        }
        if(cbrelated1.isSelected()){ //citizenid
            newqr += " related_to, ";
            Column1.add("Related");
        }
        newqr = newqr.substring(0, newqr.length() - 2);
        if(newqr.equals("selec")) newqr = "select citizen_id";
        newqr += " from covid_patient join treatment_place on covid_patient.treatment_place_id = treatment_place.treatment_place_id ";
        return newqr;
    }    
    private String tocolname(String name){
        if(name.equals("Citizen ID")) return "citizen_id";
        else if(name.equals("Full name")) return "full_name";
        else if(name.equals("Date of birth")) return "date_of_birth";
        else if(name.equals("Address")) return "citizen_address";
        else if(name.equals("Condition")) return "condition";
        else if(name.equals("Treatment place")) return "treatment_place.treatment_place_name";
        else if(name.equals("Related")) return "related_to";
        else if(name.equals("Package ID")) return "package_id";
        else if(name.equals("Name")) return "name";
        else if(name.equals("Limit")) return "limit";
        else if(name.equals("Start date")) return "package_start";
        else if(name.equals("End date")) return "package_end";
        else return "price";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedpane = new javax.swing.JTabbedPane();
        panel1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        sc1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnadd1 = new javax.swing.JButton();
        btnupdate1 = new javax.swing.JButton();
        btnback1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbsearch1 = new javax.swing.JComboBox<>();
        cbsort1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cksort1 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        btnup1 = new javax.swing.JButton();
        btndown1 = new javax.swing.JButton();
        btncancel1 = new javax.swing.JButton();
        btnsort1 = new javax.swing.JToggleButton();
        btnsearch1 = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        cbid1 = new javax.swing.JCheckBox();
        cbname1 = new javax.swing.JCheckBox();
        cbaddress1 = new javax.swing.JCheckBox();
        cbrelated1 = new javax.swing.JCheckBox();
        cbplace1 = new javax.swing.JCheckBox();
        cbcondition1 = new javax.swing.JCheckBox();
        cbdob1 = new javax.swing.JCheckBox();
        tbsearch1 = new javax.swing.JTextField();
        panel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        sc2 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnadd2 = new javax.swing.JButton();
        btnupdate2 = new javax.swing.JButton();
        btnremove2 = new javax.swing.JButton();
        btnback2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbsearch2 = new javax.swing.JComboBox<>();
        cbsort2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cksort2 = new javax.swing.JRadioButton();
        btnsort2 = new javax.swing.JToggleButton();
        btnsearch2 = new javax.swing.JToggleButton();
        tbsearch2 = new javax.swing.JTextField();
        panel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stat3 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        f0 = new javax.swing.JLabel();
        f1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        f3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        f2 = new javax.swing.JLabel();
        cursed = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        f1tof0 = new javax.swing.JLabel();
        typeofpackage = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        debt = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 700));

        tabbedpane.setBackground(new java.awt.Color(51, 51, 51));
        tabbedpane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabbedpane.setForeground(new java.awt.Color(255, 255, 255));
        tabbedpane.setToolTipText("");
        tabbedpane.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tabbedpane.setMinimumSize(new java.awt.Dimension(1200, 600));

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        sc1.setBackground(new java.awt.Color(51, 51, 51));
        sc1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sc1.setForeground(new java.awt.Color(255, 255, 255));

        tb1.setBackground(new java.awt.Color(255, 255, 255));
        tb1.setForeground(new java.awt.Color(102, 102, 102));
        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }

        ){public boolean isCellEditable(int row, int column){return false;}});
        tb1.setAutoscrolls(false);
        tb1.setCellSelectionEnabled(true);
        tb1.setRequestFocusEnabled(false);
        tb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb1MouseReleased(evt);
            }
        });
        sc1.setViewportView(tb1);

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Covid Patients");

        btnadd1.setBackground(new java.awt.Color(51, 51, 51));
        btnadd1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnadd1.setForeground(new java.awt.Color(255, 255, 255));
        btnadd1.setText("Add");
        btnadd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnadd1MouseClicked(evt);
            }
        });

        btnupdate1.setBackground(new java.awt.Color(51, 51, 51));
        btnupdate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnupdate1.setForeground(new java.awt.Color(255, 255, 255));
        btnupdate1.setText("Update");
        btnupdate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdate1MouseClicked(evt);
            }
        });

        btnback1.setBackground(new java.awt.Color(51, 51, 51));
        btnback1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnback1.setForeground(new java.awt.Color(255, 255, 255));
        btnback1.setText("Reset");
        btnback1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnback1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sc1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnupdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnback1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnback1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sc1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnadd1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(btnupdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Search");

        cbsearch1.setBackground(new java.awt.Color(255, 255, 255));
        cbsearch1.setForeground(new java.awt.Color(102, 102, 102));
        cbsearch1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbsort1.setBackground(new java.awt.Color(255, 255, 255));
        cbsort1.setForeground(new java.awt.Color(102, 102, 102));
        cbsort1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Sort");

        cksort1.setBackground(new java.awt.Color(0, 255, 255));
        cksort1.setForeground(new java.awt.Color(102, 102, 102));
        cksort1.setText("descesding");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Find related");

        btnup1.setBackground(new java.awt.Color(51, 51, 51));
        btnup1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnup1.setForeground(new java.awt.Color(255, 255, 255));
        btnup1.setText("Up");
        btnup1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnup1MouseClicked(evt);
            }
        });

        btndown1.setBackground(new java.awt.Color(51, 51, 51));
        btndown1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btndown1.setForeground(new java.awt.Color(255, 255, 255));
        btndown1.setText("Down");
        btndown1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndown1MouseClicked(evt);
            }
        });

        btncancel1.setBackground(new java.awt.Color(204, 204, 204));
        btncancel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btncancel1.setForeground(new java.awt.Color(102, 102, 102));
        btncancel1.setText("x");
        btncancel1.setToolTipText("");
        btncancel1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btncancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncancel1MouseClicked(evt);
            }
        });

        btnsort1.setBackground(new java.awt.Color(51, 51, 51));
        btnsort1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsort1.setForeground(new java.awt.Color(255, 255, 255));
        btnsort1.setText("Sort");
        btnsort1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsort1MouseClicked(evt);
            }
        });

        btnsearch1.setBackground(new java.awt.Color(51, 51, 51));
        btnsearch1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsearch1.setForeground(new java.awt.Color(255, 255, 255));
        btnsearch1.setText("Search");
        btnsearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsearch1MouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Show columns");

        cbid1.setBackground(new java.awt.Color(0, 255, 255));
        cbid1.setForeground(new java.awt.Color(102, 102, 102));
        cbid1.setText("CitizenID");

        cbname1.setBackground(new java.awt.Color(0, 255, 255));
        cbname1.setForeground(new java.awt.Color(102, 102, 102));
        cbname1.setText("Full name");

        cbaddress1.setBackground(new java.awt.Color(0, 255, 255));
        cbaddress1.setForeground(new java.awt.Color(102, 102, 102));
        cbaddress1.setText("Address");

        cbrelated1.setBackground(new java.awt.Color(0, 255, 255));
        cbrelated1.setForeground(new java.awt.Color(102, 102, 102));
        cbrelated1.setText("Related");

        cbplace1.setBackground(new java.awt.Color(0, 255, 255));
        cbplace1.setForeground(new java.awt.Color(102, 102, 102));
        cbplace1.setText("Treatment place");

        cbcondition1.setBackground(new java.awt.Color(0, 255, 255));
        cbcondition1.setForeground(new java.awt.Color(102, 102, 102));
        cbcondition1.setText("Condition");

        cbdob1.setBackground(new java.awt.Color(0, 255, 255));
        cbdob1.setForeground(new java.awt.Color(102, 102, 102));
        cbdob1.setText("Date of birth");

        tbsearch1.setBackground(new java.awt.Color(255, 255, 255));
        tbsearch1.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbaddress1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbname1)
                                    .addComponent(cbdob1)
                                    .addComponent(cbid1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbrelated1)
                                    .addComponent(cbplace1)
                                    .addComponent(cbcondition1)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbsort1, 0, 134, Short.MAX_VALUE)
                            .addComponent(cbsearch1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnsearch1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsort1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnup1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndown1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cksort1))
                    .addComponent(tbsearch1))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cksort1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsort1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbsort1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnup1)
                    .addComponent(btndown1)
                    .addComponent(btncancel1))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbid1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbname1)
                                .addGap(26, 26, 26))
                            .addComponent(cbdob1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbcondition1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbplace1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbrelated1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbaddress1)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedpane.addTab("Covid patients", panel1);

        panel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        sc2.setBackground(new java.awt.Color(51, 51, 51));
        sc2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sc2.setForeground(new java.awt.Color(255, 255, 255));

        tb2.setBackground(new java.awt.Color(255, 255, 255));
        tb2.setForeground(new java.awt.Color(102, 102, 102));
        tb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }

        ){public boolean isCellEditable(int row, int column){return false;}});
        tb2.setAutoscrolls(false);
        tb2.setCellSelectionEnabled(true);
        tb2.setRequestFocusEnabled(false);
        tb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb2MouseReleased(evt);
            }
        });
        sc2.setViewportView(tb2);

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Necessary package");

        btnadd2.setBackground(new java.awt.Color(51, 51, 51));
        btnadd2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnadd2.setForeground(new java.awt.Color(255, 255, 255));
        btnadd2.setText("Add");
        btnadd2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnadd2MouseClicked(evt);
            }
        });

        btnupdate2.setBackground(new java.awt.Color(51, 51, 51));
        btnupdate2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnupdate2.setForeground(new java.awt.Color(255, 255, 255));
        btnupdate2.setText("Update");
        btnupdate2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdate2MouseClicked(evt);
            }
        });

        btnremove2.setBackground(new java.awt.Color(51, 51, 51));
        btnremove2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnremove2.setForeground(new java.awt.Color(255, 255, 255));
        btnremove2.setText("Remove");
        btnremove2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnremove2MouseClicked(evt);
            }
        });

        btnback2.setBackground(new java.awt.Color(51, 51, 51));
        btnback2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnback2.setForeground(new java.awt.Color(255, 255, 255));
        btnback2.setText("Reset");
        btnback2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnback2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sc2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnremove2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnupdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnback2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)))
                .addGap(35, 35, 35))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnback2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sc2, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnadd2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                        .addComponent(btnupdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnremove2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 255, 255));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Search");

        cbsearch2.setBackground(new java.awt.Color(255, 255, 255));
        cbsearch2.setForeground(new java.awt.Color(102, 102, 102));
        cbsearch2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbsort2.setBackground(new java.awt.Color(255, 255, 255));
        cbsort2.setForeground(new java.awt.Color(102, 102, 102));
        cbsort2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Sort");

        cksort2.setBackground(new java.awt.Color(0, 255, 255));
        cksort2.setForeground(new java.awt.Color(102, 102, 102));
        cksort2.setText("descesding");

        btnsort2.setBackground(new java.awt.Color(51, 51, 51));
        btnsort2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsort2.setForeground(new java.awt.Color(255, 255, 255));
        btnsort2.setText("Sort");
        btnsort2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsort2MouseClicked(evt);
            }
        });

        btnsearch2.setBackground(new java.awt.Color(51, 51, 51));
        btnsearch2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsearch2.setForeground(new java.awt.Color(255, 255, 255));
        btnsearch2.setText("Search");
        btnsearch2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsearch2MouseClicked(evt);
            }
        });

        tbsearch2.setBackground(new java.awt.Color(255, 255, 255));
        tbsearch2.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cbsort2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsort2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cbsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearch2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cksort2))
                    .addComponent(tbsearch2))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cksort2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsort2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsort2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedpane.addTab("Necessary package", panel4);

        panel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setBackground(new java.awt.Color(51, 51, 51));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("System statistics");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Package");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Patient and related");

        jLabel17.setBackground(new java.awt.Color(102, 102, 102));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("F0");

        jLabel18.setBackground(new java.awt.Color(102, 102, 102));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("F0");

        jLabel23.setBackground(new java.awt.Color(102, 102, 102));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("highest consumption");
        jLabel23.setToolTipText("");

        stat3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(stat3);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("number of F0");

        f0.setBackground(new java.awt.Color(51, 51, 51));
        f0.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        f0.setForeground(new java.awt.Color(0, 51, 255));
        f0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f0.setText("2000");

        f1.setBackground(new java.awt.Color(51, 51, 51));
        f1.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        f1.setForeground(new java.awt.Color(0, 51, 255));
        f1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f1.setText("2000");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("number of F1");

        f3.setBackground(new java.awt.Color(51, 51, 51));
        f3.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        f3.setForeground(new java.awt.Color(0, 51, 255));
        f3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f3.setText("2000");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Number of F3");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Number of F2");

        f2.setBackground(new java.awt.Color(51, 51, 51));
        f2.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        f2.setForeground(new java.awt.Color(0, 51, 255));
        f2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f2.setText("2000");

        cursed.setBackground(new java.awt.Color(51, 51, 51));
        cursed.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        cursed.setForeground(new java.awt.Color(0, 51, 255));
        cursed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cursed.setText("2000");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("cured case");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("F1 -> F0");

        f1tof0.setBackground(new java.awt.Color(51, 51, 51));
        f1tof0.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        f1tof0.setForeground(new java.awt.Color(0, 51, 255));
        f1tof0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f1tof0.setText("2000");

        typeofpackage.setBackground(new java.awt.Color(51, 51, 51));
        typeofpackage.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        typeofpackage.setForeground(new java.awt.Color(0, 51, 255));
        typeofpackage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typeofpackage.setText("2000");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Type of package");

        debt.setBackground(new java.awt.Color(51, 51, 51));
        debt.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        debt.setForeground(new java.awt.Color(0, 51, 255));
        debt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        debt.setText("2000");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Total of debt");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel9))
                            .addComponent(f0, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel22))
                            .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(debt, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeofpackage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(47, 47, 47)))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(183, 183, 183))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(f2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel26)))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel25))
                                    .addComponent(f3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(f1tof0, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cursed, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jLabel23))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap(270, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addGap(152, 152, 152)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16)
                .addGap(32, 32, 32)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(f0, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(typeofpackage, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(debt, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(f2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(f3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(f1tof0, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(cursed, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(56, 56, 56))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(148, 148, 148)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(423, Short.MAX_VALUE)))
        );

        jPanel12.setBackground(new java.awt.Color(0, 255, 255));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedpane.addTab("System statistics", panel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedpane, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb1MouseClicked
        resetbtn1();
        //System.out.println(tb1.getValueAt(tb1.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_tb1MouseClicked

    private void btnup1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnup1MouseClicked
        if(btnup1.isEnabled()){
            String upID = tb1.getValueAt(tb1.getSelectedRow(), 7).toString();
            this.queryOfRelated = " citizen_id = '" + upID +"' ";
            try {
				resettable1();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("resettable1 1361");
				e.printStackTrace();
			}
            resetbtn1();
        }
    }//GEN-LAST:event_btnup1MouseClicked

    private void btndown1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndown1MouseClicked
        if(btndown1.isEnabled()){
            String downID = tb1.getValueAt(tb1.getSelectedRow(), 1).toString();
            this.queryOfRelated = " related_to = '" + downID+ "' ";
            try {
				resettable1();
			} catch (SQLException e) {
				System.out.println("resettable1 1374");
				e.printStackTrace();
			}
            resetbtn1();
        }
    }//GEN-LAST:event_btndown1MouseClicked

    private void btncancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancel1MouseClicked
        if(btncancel1.isEnabled()){
            this.queryOfRelated = "";
            try {
				resettable1();
			} catch (SQLException e) {
				System.out.println("resettable1 1388");
				e.printStackTrace();
			}
            resetbtn1();
        }
    }//GEN-LAST:event_btncancel1MouseClicked

    private void tb1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb1MouseReleased
        resetbtn1();
    }//GEN-LAST:event_tb1MouseReleased

    private void btnsort1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsort1MouseClicked
        if(btnsort1.isEnabled()){
            try {
				resettable1();
			} catch (SQLException e) {
				System.out.println("resettable1 1405");
				e.printStackTrace();
			}
            resetbtn1();
        }
    }//GEN-LAST:event_btnsort1MouseClicked

    private void btnsearch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsearch1MouseClicked
        if(btnsearch1.isEnabled()){
            try {
				resettable1();
			} catch (SQLException e) {
				System.out.println("resettable1 1417");
				e.printStackTrace();
			}
            resetbtn1();
        }
    }//GEN-LAST:event_btnsearch1MouseClicked

    private void btnback1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnback1MouseClicked
        if(btnback1.isEnabled()){
            this.queryOfRelated="";
            btnsearch1.setSelected(false);
            btnsort1.setSelected(false);
            try {
				resettable1();
			} catch (SQLException e) {
				System.out.println("resettable1 1432");
				e.printStackTrace();
			}
            resetbtn1();
        }
    }//GEN-LAST:event_btnback1MouseClicked

    private void btnsearch2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsearch2MouseClicked
       if(btnsearch2.isEnabled()){
            try {
				resettable2();
			} catch (SQLException e) {
				System.out.println("resettable2 1444");
				e.printStackTrace();
			}
            resetbtn2();
        }
    }//GEN-LAST:event_btnsearch2MouseClicked

    private void btnsort2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsort2MouseClicked
        if(btnsort2.isEnabled()){
            try {
				resettable2();
			} catch (SQLException e) {
				System.out.println("resettable2 1456");
				e.printStackTrace();
			}
            resetbtn2();
        }
    }//GEN-LAST:event_btnsort2MouseClicked

    private void btnback2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnback2MouseClicked
        if(btnback2.isEnabled()){
            btnsearch2.setSelected(false);
            btnsort2.setSelected(false);
            try {
				resettable2();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            resetbtn2();
        }
    }//GEN-LAST:event_btnback2MouseClicked

    private void tb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb2MouseClicked
        resetbtn2();
    }//GEN-LAST:event_tb2MouseClicked

    private void tb2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb2MouseReleased
        resetbtn2();
    }//GEN-LAST:event_tb2MouseReleased

    private void btnadd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnadd1MouseClicked
        try {
            if(db.count("select count(ward_id) from district_has_ward")==0 || db.count("select count(district_id) from province_has_district")==0 || db.count("select count(treatment_place_id) from treatment_place")==0){
                    JOptionPane.showMessageDialog(null, "Empty Address or Treatment place!");
                return;
            }
            Manager_addpatient cp = new Manager_addpatient(ManagerID);
            resettable1();
            initStat();
        } catch (SQLException ex) {
            Logger.getLogger(Manager_mainframe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnadd1MouseClicked

    private void btnupdate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdate1MouseClicked
        if(tb1.getSelectedRow()!= -1){
            try {
                if(db.count("select count(ward_id) from district_has_ward")==0 || db.count("select count(district_id) from province_has_district")==0 || db.count("select count(treatment_place_id) from treatment_place")==0){
                    JOptionPane.showMessageDialog(null, "Empty Address or Treatment place!");
                    return;
                }
                Manager_updatepatient up = new Manager_updatepatient(tb1.getValueAt(tb1.getSelectedRow(), 1).toString(), ManagerID);
                resettable1();
                initStat();
            } catch (SQLException ex) {
                Logger.getLogger(Manager_mainframe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnupdate1MouseClicked

    private void btnadd2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnadd2MouseClicked
        Manager_addpackage ap = new Manager_addpackage();
        try {
			resettable2();
			initStat();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }//GEN-LAST:event_btnadd2MouseClicked

    private void btnremove2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnremove2MouseClicked
        if(tb2.getSelectedRow()!= -1){
            String pid = tb2.getValueAt(tb2.getSelectedRow(), 1).toString();
            String newqr = "delete from package where package_id = '" + pid +"'";
            try {
				db.insert(newqr);
				 resettable2();
		         JOptionPane.showMessageDialog(null, "Remove package successfully!");
		         initStat();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }//GEN-LAST:event_btnremove2MouseClicked

    private void btnupdate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdate2MouseClicked
        if(tb2.getSelectedRow()!= -1){
            String pid = tb2.getValueAt(tb2.getSelectedRow(), 1).toString();
            try {
				Manager_updatepackage up = new Manager_updatepackage(pid);
				resettable2();
	            initStat();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
    }//GEN-LAST:event_btnupdate2MouseClicked
    void resetbtn2(){
        btnupdate2.setEnabled(false);
        btnremove2.setEnabled(false);
        if(tb2.getSelectedRow()!=-1){
            btnupdate2.setEnabled(true);
            btnremove2.setEnabled(true);
        }
        
    }
    void resetbtn1(){
        btnup1.setEnabled(false);
        btndown1.setEnabled(false);
        btncancel1.setEnabled(false);
        btnupdate1.setEnabled(false);
        //for updown, remove, update
        if(tb1.getSelectedRow()!=-1){
            if(!"F0".equals(tb1.getValueAt(tb1.getSelectedRow(), 5).toString())) btnup1.setEnabled(true);
            if(!"F3".equals(tb1.getValueAt(tb1.getSelectedRow(), 5).toString())) btndown1.setEnabled(true);
            btnupdate1.setEnabled(true);
        }
        if(!this.queryOfRelated.equals("")) btncancel1.setEnabled(true);
    }
    
    //</editor-fold>
    
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
            java.util.logging.Logger.getLogger(Manager_mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager_mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager_mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager_mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd1;
    private javax.swing.JButton btnadd2;
    private javax.swing.JButton btnback1;
    private javax.swing.JButton btnback2;
    private javax.swing.JButton btncancel1;
    private javax.swing.JButton btndown1;
    private javax.swing.JButton btnremove2;
    private javax.swing.JToggleButton btnsearch1;
    private javax.swing.JToggleButton btnsearch2;
    private javax.swing.JToggleButton btnsort1;
    private javax.swing.JToggleButton btnsort2;
    private javax.swing.JButton btnup1;
    private javax.swing.JButton btnupdate1;
    private javax.swing.JButton btnupdate2;
    private javax.swing.JCheckBox cbaddress1;
    private javax.swing.JCheckBox cbcondition1;
    private javax.swing.JCheckBox cbdob1;
    private javax.swing.JCheckBox cbid1;
    private javax.swing.JCheckBox cbname1;
    private javax.swing.JCheckBox cbplace1;
    private javax.swing.JCheckBox cbrelated1;
    private javax.swing.JComboBox<String> cbsearch1;
    private javax.swing.JComboBox<String> cbsearch2;
    private javax.swing.JComboBox<String> cbsort1;
    private javax.swing.JComboBox<String> cbsort2;
    private javax.swing.JRadioButton cksort1;
    private javax.swing.JRadioButton cksort2;
    private javax.swing.JLabel cursed;
    private javax.swing.JLabel debt;
    private javax.swing.JLabel f0;
    private javax.swing.JLabel f1;
    private javax.swing.JLabel f1tof0;
    private javax.swing.JLabel f2;
    private javax.swing.JLabel f3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel6;
    private javax.swing.JScrollPane sc1;
    private javax.swing.JScrollPane sc2;
    private javax.swing.JTable stat3;
    private javax.swing.JTabbedPane tabbedpane;
    private javax.swing.JTable tb1;
    private javax.swing.JTable tb2;
    private javax.swing.JTextField tbsearch1;
    private javax.swing.JTextField tbsearch2;
    private javax.swing.JLabel typeofpackage;
    // End of variables declaration//GEN-END:variables
}
