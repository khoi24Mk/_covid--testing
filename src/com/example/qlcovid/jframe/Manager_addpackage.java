package com.example.qlcovid.jframe;

import com.example.qlcovid.model.ManagerDB;
import com.example.qlcovid.string.UtilsString;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author nhonnhon
 */
public class Manager_addpackage extends javax.swing.JPanel {

    /**
     * Creates new form Manager_createpackage
     */
    int minyear;
    int maxyear;
    JDialog d;
    ManagerDB db;
    DefaultComboBoxModel cbmodel_year1, cbmodel_month1, cbmodel_day1, cbmodel_year2, cbmodel_month2, cbmodel_day2;
    public Manager_addpackage() {
        initComponents();
        UtilsString s = new UtilsString();
        minyear = s.minyear;
        maxyear = s.maxyear;
        db = new ManagerDB();
        initDate();
        initDialog();
    }
    void initDate(){
        
        initDate1();
        initDate2();
    }
    void initDate1(){
        int curyearindex = curyear() - minyear;
        Vector<String> year = new Vector<String>();
        for(int i = minyear; i<= maxyear; i++){
            year.add(String.valueOf(i));
        }
        cbmodel_year1 = new DefaultComboBoxModel(year);
        d1year.setModel(cbmodel_year1);
        d1year.setSelectedIndex(curyearindex);
        
        Vector<String> month1 = new Vector<String>();
        for(int i = 1; i<= 12; i++){
            month1.add(String.valueOf(i));
        }
        cbmodel_month1 = new DefaultComboBoxModel(month1);
        d1month.setModel(cbmodel_month1);
        d1month.setSelectedIndex(curmonth()-1);
        
        Vector<String> day1 = new Vector<String>();
        for(int i = 1; i<= maxday(Integer.valueOf(d1month.getSelectedItem().toString()) ,Integer.valueOf(d1year.getSelectedItem().toString())); i++){
            day1.add(String.valueOf(i));
        }
        cbmodel_day1 = new DefaultComboBoxModel(day1);
        d1day.setModel(cbmodel_day1);
        d1day.setSelectedIndex(curday()-1);
        
        d1year.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Vector<String> month1 = new Vector<String>();
                for(int i = 1; i<= 12; i++){
                    month1.add(String.valueOf(i));
                }
                cbmodel_month1 = new DefaultComboBoxModel(month1);
                d1month.setModel(cbmodel_month1);
            }
        });
        d1month.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Vector<String> day1 = new Vector<String>();
                for(int i = 1; i<= maxday(Integer.valueOf(d1month.getSelectedItem().toString()) ,Integer.valueOf(d1year.getSelectedItem().toString())); i++){
                    day1.add(String.valueOf(i));
                }
                cbmodel_day1 = new DefaultComboBoxModel(day1);
                d1day.setModel(cbmodel_day1);
            }
        });
    }
    
    void initDate2(){
        int curyearindex = curyear() - minyear;
        Vector<String> year = new Vector<String>();
        for(int i = minyear; i<= maxyear; i++){
            year.add(String.valueOf(i));
        }
        cbmodel_year2 = new DefaultComboBoxModel(year);
        d2year.setModel(cbmodel_year2);
        d2year.setSelectedIndex(curyearindex);
        
        Vector<String> month1 = new Vector<String>();
        for(int i = 1; i<= 12; i++){
            month1.add(String.valueOf(i));
        }
        cbmodel_month2 = new DefaultComboBoxModel(month1);
        d2month.setModel(cbmodel_month2);
        d2month.setSelectedIndex(curmonth()-1);
        
        Vector<String> day1 = new Vector<String>();
        for(int i = 1; i<= maxday(Integer.valueOf(d2month.getSelectedItem().toString()) ,Integer.valueOf(d2year.getSelectedItem().toString())); i++){
            day1.add(String.valueOf(i));
        }
        cbmodel_day2 = new DefaultComboBoxModel(day1);
        d2day.setModel(cbmodel_day2);
        d2day.setSelectedIndex(curday()-1);
        
        d2year.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Vector<String> month1 = new Vector<String>();
                for(int i = 1; i<= 12; i++){
                    month1.add(String.valueOf(i));
                }
                cbmodel_month2 = new DefaultComboBoxModel(month1);
                d2month.setModel(cbmodel_month2);
            }
        });
        d2month.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Vector<String> day1 = new Vector<String>();
                for(int i = 1; i<= maxday(Integer.valueOf(d2month.getSelectedItem().toString()) ,Integer.valueOf(d2year.getSelectedItem().toString())); i++){
                    day1.add(String.valueOf(i));
                }
                cbmodel_day2 = new DefaultComboBoxModel(day1);
                d2day.setModel(cbmodel_day2);
            }
        });
    }
    int curyear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    int curmonth(){
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }
    int curday(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    int maxday(int month, int year){
        int maxday = 31;
        if(month == 2){
            if(year%4 ==0 && year%100!=0)  maxday = 29;
            else maxday = 28;
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11)maxday = 30;
        return maxday;
    }
    
    void initDialog(){
        d = new JDialog();
        d.setSize(360, 470);
        d.add(this);
        d.setResizable(false);
        d.setModal(true);
        d.setLocationRelativeTo(null);
        d.setTitle("Add package");
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

        jPanel3 = new javax.swing.JPanel();
        textlabel = new javax.swing.JLabel();
        d1id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        d1name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        d1year = new javax.swing.JComboBox<>();
        d1day = new javax.swing.JComboBox<>();
        d1month = new javax.swing.JComboBox<>();
        d2year = new javax.swing.JComboBox<>();
        Address = new javax.swing.JLabel();
        d2month = new javax.swing.JComboBox<>();
        d2day = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        d1limit = new javax.swing.JTextField();
        button = new javax.swing.JButton();
        d1error = new javax.swing.JLabel();
        d1price = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        textlabel.setBackground(java.awt.Color.white);
        textlabel.setForeground(new java.awt.Color(102, 102, 102));
        textlabel.setText("Package ID");

        d1id.setBackground(java.awt.Color.white);
        d1id.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setBackground(java.awt.Color.white);
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Name");

        d1name.setBackground(java.awt.Color.white);
        d1name.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setBackground(java.awt.Color.white);
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Start date");

        d1year.setBackground(java.awt.Color.white);
        d1year.setForeground(new java.awt.Color(102, 102, 102));
        d1year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d1day.setBackground(java.awt.Color.white);
        d1day.setForeground(new java.awt.Color(102, 102, 102));
        d1day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d1month.setBackground(java.awt.Color.white);
        d1month.setForeground(new java.awt.Color(102, 102, 102));
        d1month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d2year.setBackground(java.awt.Color.white);
        d2year.setForeground(new java.awt.Color(102, 102, 102));
        d2year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Address.setBackground(java.awt.Color.white);
        Address.setForeground(new java.awt.Color(102, 102, 102));
        Address.setText("End date");

        d2month.setBackground(java.awt.Color.white);
        d2month.setForeground(new java.awt.Color(102, 102, 102));
        d2month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        d2day.setBackground(java.awt.Color.white);
        d2day.setForeground(new java.awt.Color(102, 102, 102));
        d2day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setBackground(java.awt.Color.white);
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Limit");

        d1limit.setBackground(java.awt.Color.white);
        d1limit.setForeground(new java.awt.Color(102, 102, 102));

        button.setText("Add package");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMouseClicked(evt);
            }
        });

        d1error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d1error.setText(" ");

        d1price.setBackground(java.awt.Color.white);
        d1price.setForeground(new java.awt.Color(102, 102, 102));

        jLabel18.setBackground(java.awt.Color.white);
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Price");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addComponent(d1error, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(d1day, 0, 96, Short.MAX_VALUE))
                                .addComponent(d1name, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(d1id, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(d2year, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(d2month, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(d2day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(d1limit, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(9, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(d1price, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d2year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d2day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d2month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1limit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(d1error)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMouseClicked
        try {
            if(check() == true){
                close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Manager_addpackage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonMouseClicked

    private boolean check() throws SQLException{
        //check id
        if(d1id.getText().isEmpty()||d1id.getText().length()==0) {d1error.setText("Empty package ID!"); return false;}
        Object[][] obj = db.getdata("select package_id from package");
        for(int i =0;i <obj.length; i++){
            if(d1id.getText().equals(obj[i][0])){d1error.setText("Existed package ID"); return false;}
        }
        //check date
        int startdate = Integer.parseInt(d1day.getSelectedItem().toString()) + Integer.parseInt(d1month.getSelectedItem().toString())*31 + (Integer.parseInt(d1year.getSelectedItem().toString())-2000) *366;
        int enddate = Integer.parseInt(d2day.getSelectedItem().toString()) + Integer.parseInt(d2month.getSelectedItem().toString())*31 + (Integer.parseInt(d2year.getSelectedItem().toString())-2000) *366;
        if(startdate>enddate){d1error.setText("Invalid date: End date > Start date"); return false;}
        
        //check limit, price
        if(d1limit.getText().isEmpty() || d1limit.getText().length()==0){
            d1error.setText("Empty limit!"); return false;
        }
        else try{
                int limit = Integer.parseInt(d1limit.getText());
            }
            catch (NumberFormatException ex){
                d1error.setText("Limit must be integer"); return false;
            }
        
        //check limit, price
        if(d1price.getText().isEmpty() || d1limit.getText().length()==0) d1price.setText("0");
        try{
            int price = Integer.parseInt(d1price.getText());
        }
        catch (NumberFormatException ex){
            d1error.setText("Price must be integer"); return false;
        }
        d1error.setText("Successfully!");
        return true;
        //
    }
    void close(){
        String startdate = d1year.getSelectedItem().toString() + "-"  + d1month.getSelectedItem().toString() +"-" + d1day.getSelectedItem().toString();
        String enddate = d2year.getSelectedItem().toString() + "-"  + d2month.getSelectedItem().toString() +"-" + d2day.getSelectedItem().toString();
        String newqr = "insert into package(package_id, name, limit, package_start, package_end, price) values( ";
        newqr += "'"+d1id.getText() + "' , '" + d1name.getText() +"' , " + d1limit.getText() + " , '" +startdate +"', '" + enddate +"',"+ d1price.getText() + ")";
        try {
			db.insert(newqr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JOptionPane.showMessageDialog(null, "Add package successfully!");
        d.setVisible(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address;
    private javax.swing.JButton button;
    private javax.swing.JComboBox<String> d1day;
    private javax.swing.JLabel d1error;
    private javax.swing.JTextField d1id;
    private javax.swing.JTextField d1limit;
    private javax.swing.JComboBox<String> d1month;
    private javax.swing.JTextField d1name;
    private javax.swing.JTextField d1price;
    private javax.swing.JComboBox<String> d1year;
    private javax.swing.JComboBox<String> d2day;
    private javax.swing.JComboBox<String> d2month;
    private javax.swing.JComboBox<String> d2year;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel textlabel;
    // End of variables declaration//GEN-END:variables
}
