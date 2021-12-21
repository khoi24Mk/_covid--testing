/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlcovid.jframe;

import com.example.qlcovid.model.Hashing;
import com.example.qlcovid.model.ManagerDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author nhonnhon
 */
public class Manager_addpatient extends javax.swing.JPanel {
    String ManagerID;
    public Manager_mainframe mf;
    JDialog d;
    ManagerDB db = new ManagerDB();
    DefaultComboBoxModel cbmodel_year, cbmodel_month, cbmodel_day, cbmodel_province, cbmodel_district, cbmodel_ward, cbmodel_tplace;
    Object[][] province, district, ward, tplace;
    public Manager_addpatient(String mgID) throws SQLException {
        ManagerID = mgID;
        initComponents();
        initTreatmentplace();
        initAddress();
        initDate();
        initDialog();
    }
    void initTreatmentplace() throws SQLException{
        cbmodel_tplace = new DefaultComboBoxModel();
        tplace = db.getdata("select treatment_place_id, treatment_place_name from treatment_place where current_holding < capacity");
        for(int i =0;i <tplace.length; i++){
            cbmodel_tplace.addElement(tplace[i][1].toString());
        }
        d1tplace.setModel(cbmodel_tplace);
    }
    void initAddress() throws SQLException{
        cbmodel_province = new DefaultComboBoxModel();
        province = db.getdata("select province_id, province_name from province");
        for(int i =0;i <province.length; i++){
            cbmodel_province.addElement(province[i][1].toString());
        }
        d1pro.setModel(cbmodel_province);
        
        cbmodel_district = new DefaultComboBoxModel();
        String province_id = province[d1pro.getSelectedIndex()][0].toString();
        district = db.getdata("select district.district_id, district_name from province_has_district join district on province_has_district.district_id = district.district_id where province_id = "+ province_id);
        for(int i =0;i <district.length; i++){
            cbmodel_district.addElement(district[i][1].toString());
        }
        d1dis.setModel(cbmodel_district);
        
        cbmodel_ward = new DefaultComboBoxModel();
        String district_id = province[d1dis.getSelectedIndex()][0].toString();
        ward = db.getdata("select ward.ward_id, ward.ward_name from ward join district_has_ward on ward.ward_id = district_has_ward.ward_id where district_id = "+ district_id);
        for(int i =0;i <ward.length; i++){
            cbmodel_ward.addElement(ward[i][1].toString());
        }
        d1ward.setModel(cbmodel_ward);
        
        d1pro.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                cbmodel_district = new DefaultComboBoxModel();
                String province_id = province[d1pro.getSelectedIndex()][0].toString();
                try {
                    district = db.getdata("select district.district_id, district_name from province_has_district join district on province_has_district.district_id = district.district_id where province_id = "+ province_id);
                } catch (SQLException ex) {
                    Logger.getLogger(Manager_addpatient.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(int i =0;i <district.length; i++){
                    cbmodel_district.addElement(district[i][1].toString());
                }
                d1dis.setModel(cbmodel_district);
            }
        });
        
        d1dis.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                cbmodel_ward = new DefaultComboBoxModel();
                String district_id = province[d1dis.getSelectedIndex()][0].toString();
                try {
                    ward = db.getdata("select ward.ward_id, ward.ward_name from ward join district_has_ward on ward.ward_id = district_has_ward.ward_id where district_id = "+ district_id);
                } catch (SQLException ex) {
                    Logger.getLogger(Manager_addpatient.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(int i =0;i <ward.length; i++){
                    cbmodel_ward.addElement(ward[i][1].toString());
                }
                d1ward.setModel(cbmodel_ward);
            }
        });
    }
    void initDate(){
        cbmodel_month = new DefaultComboBoxModel();
        cbmodel_month.addElement(Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1));
        d1month.setModel(cbmodel_month);
        cbmodel_day = new DefaultComboBoxModel();
        cbmodel_day.addElement(Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        d1day.setModel(cbmodel_day);
        Vector<String> year = new Vector<String>();
        for(int i = Calendar.getInstance().get(Calendar.YEAR); i> 1900; i--){
            year.add(String.valueOf(i));
        }
        cbmodel_year = new DefaultComboBoxModel(year);
        d1year.setModel(cbmodel_year);
        d1year.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
              int maxmonth = 12;
              if(Integer.parseInt(d1year.getSelectedItem().toString()) == Calendar.getInstance().get(Calendar.YEAR)) maxmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
              Vector<String> month = new Vector<String>();
              for(int i =0; i<maxmonth; i++) month.add(String.valueOf(i+1));
              cbmodel_month = new DefaultComboBoxModel(month);
              d1month.setModel(cbmodel_month);
            }
        });
        d1month.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
              int maxday = 31;
              int month = Integer.parseInt(d1month.getSelectedItem().toString());
              if(month == 2){
                  if(Integer.parseInt(d1year.getSelectedItem().toString())% 4 ==0 && Integer.parseInt(d1year.getSelectedItem().toString())% 100!=0)  maxday = 29;
                  else maxday = 28;
              }
              else if(month == 4 || month == 6 || month == 9 || month == 11)maxday = 30;
              if(month == Calendar.getInstance().get(Calendar.MONTH)+1 && Integer.parseInt(d1year.getSelectedItem().toString()) == Calendar.getInstance().get(Calendar.YEAR)) maxday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
              Vector<String> day = new Vector<String>();
              for(int i =1; i<=maxday; i++) day.add(String.valueOf(i));
              cbmodel_day = new DefaultComboBoxModel(day);
              d1day.setModel(cbmodel_day);
            }
        });
        
        
        int maxmonth = 12;
        if(Integer.parseInt(d1year.getSelectedItem().toString()) == Calendar.getInstance().get(Calendar.YEAR)) maxmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Vector<String> month = new Vector<String>();
        for(int i =0; i<maxmonth; i++) month.add(String.valueOf(i+1));
        cbmodel_month = new DefaultComboBoxModel(month);
        d1month.setModel(cbmodel_month);
        int maxday = 31;
        int month1 = Integer.parseInt(d1month.getSelectedItem().toString());
        if(month1 == 2){
            if(Integer.parseInt(d1year.getSelectedItem().toString())% 4 ==0 && Integer.parseInt(d1year.getSelectedItem().toString())% 100!=0)  maxday = 29;
            else maxday = 28;
        }
        else if(month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11)maxday = 30;
        if(month1 == Calendar.getInstance().get(Calendar.MONTH)+1 && Integer.parseInt(d1year.getSelectedItem().toString()) == Calendar.getInstance().get(Calendar.YEAR)) maxday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Vector<String> day = new Vector<String>();
        for(int i =1; i<=maxday; i++) day.add(String.valueOf(i));
        cbmodel_day = new DefaultComboBoxModel(day);
        d1day.setModel(cbmodel_day);
    }
    void initDialog(){
        d = new JDialog();
        d.setSize(360, 600);
        d.add(this);
        d.setResizable(false);
        d.setModal(true);
        d.setLocationRelativeTo(null);
        d.setTitle("Add patient");
        d.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valpannel = new javax.swing.JPanel();
        valpw = new javax.swing.JPasswordField();
        valbtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        textlabel = new javax.swing.JLabel();
        d1id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        d1name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        d1year = new javax.swing.JComboBox<>();
        d1day = new javax.swing.JComboBox<>();
        d1month = new javax.swing.JComboBox<>();
        d1pro = new javax.swing.JComboBox<>();
        Address = new javax.swing.JLabel();
        d1dis = new javax.swing.JComboBox<>();
        d1ward = new javax.swing.JComboBox<>();
        d1as = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        d1relate = new javax.swing.JTextField();
        d1tplace = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        d1add = new javax.swing.JButton();
        d1error = new javax.swing.JLabel();
        d1pw = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        d1b = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        valpannel.setBackground(new java.awt.Color(255, 255, 255));

        valpw.setText("jPasswordField1");

        valbtn.setText("OK");

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Input your password:");

        javax.swing.GroupLayout valpannelLayout = new javax.swing.GroupLayout(valpannel);
        valpannel.setLayout(valpannelLayout);
        valpannelLayout.setHorizontalGroup(
            valpannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valpannelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(valpannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(valpannelLayout.createSequentialGroup()
                        .addComponent(valpw, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valbtn)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        valpannelLayout.setVerticalGroup(
            valpannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valpannelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(valpannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valbtn)
                    .addComponent(valpw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        textlabel.setBackground(java.awt.Color.white);
        textlabel.setForeground(new java.awt.Color(102, 102, 102));
        textlabel.setText("Citizen ID");

        d1id.setBackground(java.awt.Color.white);
        d1id.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setBackground(java.awt.Color.white);
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Full name");

        d1name.setBackground(java.awt.Color.white);
        d1name.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setBackground(java.awt.Color.white);
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Date of birth");

        d1year.setBackground(java.awt.Color.white);
        d1year.setForeground(new java.awt.Color(102, 102, 102));
        d1year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d1day.setBackground(java.awt.Color.white);
        d1day.setForeground(new java.awt.Color(102, 102, 102));
        d1day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d1month.setBackground(java.awt.Color.white);
        d1month.setForeground(new java.awt.Color(102, 102, 102));
        d1month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d1pro.setBackground(java.awt.Color.white);
        d1pro.setForeground(new java.awt.Color(102, 102, 102));
        d1pro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Address.setBackground(java.awt.Color.white);
        Address.setForeground(new java.awt.Color(102, 102, 102));
        Address.setText("Address");

        d1dis.setBackground(java.awt.Color.white);
        d1dis.setForeground(new java.awt.Color(102, 102, 102));
        d1dis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d1ward.setBackground(java.awt.Color.white);
        d1ward.setForeground(new java.awt.Color(102, 102, 102));
        d1ward.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d1as.setBackground(java.awt.Color.white);
        d1as.setForeground(new java.awt.Color(102, 102, 102));

        jLabel14.setBackground(java.awt.Color.white);
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Related to");

        d1relate.setBackground(java.awt.Color.white);
        d1relate.setForeground(new java.awt.Color(102, 102, 102));

        d1tplace.setBackground(java.awt.Color.white);
        d1tplace.setForeground(new java.awt.Color(102, 102, 102));
        d1tplace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setBackground(java.awt.Color.white);
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Treatment place");

        d1add.setBackground(new java.awt.Color(51, 51, 51));
        d1add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d1add.setForeground(new java.awt.Color(255, 255, 255));
        d1add.setText("Add patient");
        d1add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                d1addMouseClicked(evt);
            }
        });

        d1error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d1error.setText(" ");

        d1pw.setBackground(java.awt.Color.white);
        d1pw.setForeground(new java.awt.Color(102, 102, 102));

        jLabel18.setBackground(java.awt.Color.white);
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Password");

        d1b.setBackground(java.awt.Color.white);
        d1b.setForeground(new java.awt.Color(102, 102, 102));

        jLabel19.setBackground(java.awt.Color.white);
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Balance");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(d1add, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14)
                        .addComponent(jLabel17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(d1tplace, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(d1relate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(d1pw, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(textlabel)
                    .addComponent(Address)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(d1year, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(d1month, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(d1day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(d1name, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(d1id, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(d1pro, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(d1dis, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(d1ward, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(d1as, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d1error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(d1b, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(textlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d1year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d1day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d1month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Address)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1as, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d1pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d1ward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d1dis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1relate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1tplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1pw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(d1error)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(d1add, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void d1addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_d1addMouseClicked
        try {
            if(check()==true) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Enter your password:");
                JPasswordField pass = new JPasswordField(10);
                panel.add(label);
                panel.add(pass);
                String[] options = new String[]{"OK", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, panel, "Confirm password",
                                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                         null, options, options[1]);
                if(option == 0) // pressing OK button
                {
                    if(db.count("select count(username) from ql_user where user_role = 'supevisor' and username = '"+ ManagerID + "' and user_password = '" + Hashing.getHash(pass.getText().toString())+"'")==1){
                        close(); d.setVisible(false); 
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Wrong password!!");
                    }
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Manager_addpatient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_d1addMouseClicked
    private void close() throws SQLException{
        JOptionPane.showMessageDialog(null, "Add patient successfully!!");
        this.d1error.setText("Successfully!");
        
        String uID = d1id.getText();
        String uPw = d1pw.getText();
        String uName = d1name.getText();
        String uYear = d1year.getSelectedItem().toString();
        String uMonth = d1month.getSelectedItem().toString();
        String uDay = d1day.getSelectedItem().toString();
        String related = d1relate.getText();
        String condition = "";
        String balance = d1b.getText();
        if(d1relate.getText().isEmpty()||d1relate.getText().length()==0) {condition = "F0"; related = "NULL";}
        else{
            String relatedCon = db.get("select condition from covid_patient where citizen_id = '"+ related+"'");
            if(relatedCon.equals("F0")) condition = "F1";
            else if(relatedCon.equals("F1")) condition = "F2";
            else if(relatedCon.equals("F2")) condition = "F3";
        }
        String utplace = tplace[d1tplace.getSelectedIndex()][0].toString();
        String uaddress = d1as.getText()+ ward[d1ward.getSelectedIndex()][1].toString() + ", " + district[d1dis.getSelectedIndex()][1].toString()+ ", " +province[d1pro.getSelectedIndex()][1].toString();
        String query = "insert into ql_user \n values ('"+ uID +"', '" + Hashing.getHash(uPw) +"', 'user', 'Y') \n";
        db.insert(query);
        query = "insert into covid_patient(citizen_id, full_name, date_of_birth, condition, treatment_place_id, related_to, citizen_address, balance) \n" +
                        "values ('"+ uID +"', N'" + uName + "', '" + uYear + "-" +uMonth + "-" + uDay +"', '"+condition+"',N'" +  utplace+ "', '"+ related+"', N'"+ uaddress+"', "+balance +") ";
        db.insert(query);
        query = "update treatment_place set current_holding = current_holding+ 1 where treatment_place_id = '" + utplace+"'";
        db.insert(query);
        query = "INSERT INTO patient_history(patient_id, patient_action, patient_date) VALUES('"+ uID + "', 'new', GETDATE())";
        db.insert(query);
        query = "INSERT INTO edit(supevisor_id, supevisor_action, supevisor_date) VALUES('"+ ManagerID + "', 'new patient "+uID+"', GETDATE())";
        db.insert(query);
    }
    private boolean check() throws SQLException {
        Object[][] data = this.db.getdata(" select * from covid_patient join treatment_place on covid_patient.treatment_place_id = treatment_place.treatment_place_id ");
        //check id
        if(d1id.getText().isEmpty() || this.d1id.getText().length() < 3  || this.d1id.getText().length() > 10){ this.d1error.setText("Invalid ID!"); return false; }
        try { int intValue = Integer.parseInt(this.d1id.getText()); } catch (NumberFormatException e) { this.d1error.setText("Invalid ID!"); return false; }
        if(db.count("Select count(username) from ql_user where username = '" + this.d1id.getText()+"'")>=1){ this.d1error.setText("Existed ID!"); return false; }
        //check full name
        if(this.d1name.getText().isEmpty()||this.d1name.getText().length() < 1 || this.d1name.getText().length() > 50){ this.d1error.setText("Invalid name!"); return false;}
        //check related
        if(!d1relate.getText().isEmpty() && d1relate.getText().length() > 0){
            String related = d1relate.getText();
            if(db.count("select count(citizen_id) from covid_patient where citizen_id = '"+related+"'") == 1){
                if("F3".equals(db.get("select condition from covid_patient where citizen_id = '" + related+"'"))){this.d1error.setText("Unexisted related ID!"); return false;}
            }
            else {this.d1error.setText("Unexisted related ID!"); return false;}
        }
        //check password
        if(d1pw.getText().isEmpty()||d1pw.getText().length()<6){this.d1error.setText("Invalid password!"); return false;}
        
        //edit address
        if(d1as.getText().isEmpty()){
            d1as.setText("");
        }
        else{
            d1as.setText(d1as.getText() + ", ");
        }
        if(d1b.getText().isEmpty() || this.d1b.getText().length() == 0){ d1b.setText("0"); }
        try { int intValue = Integer.parseInt(this.d1id.getText()); } catch (NumberFormatException e) { this.d1error.setText("Invalid Balance!"); return false; }
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address;
    private javax.swing.JButton d1add;
    private javax.swing.JTextField d1as;
    private javax.swing.JTextField d1b;
    private javax.swing.JComboBox<String> d1day;
    private javax.swing.JComboBox<String> d1dis;
    private javax.swing.JLabel d1error;
    private javax.swing.JTextField d1id;
    private javax.swing.JComboBox<String> d1month;
    private javax.swing.JTextField d1name;
    private javax.swing.JComboBox<String> d1pro;
    private javax.swing.JTextField d1pw;
    private javax.swing.JTextField d1relate;
    private javax.swing.JComboBox<String> d1tplace;
    private javax.swing.JComboBox<String> d1ward;
    private javax.swing.JComboBox<String> d1year;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel textlabel;
    private javax.swing.JButton valbtn;
    private javax.swing.JPanel valpannel;
    private javax.swing.JPasswordField valpw;
    // End of variables declaration//GEN-END:variables

   
}
