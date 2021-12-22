package com.example.qlcovid.jframe.User.Package;

import com.example.qlcovid.jframe.User.Info.BalanceUI;
import com.example.qlcovid.jframe.User.PtablePackage;
import com.example.qlcovid.jframe.User.PtablePurchase;
import com.example.qlcovid.model.User.PackageClass;
import com.example.qlcovid.model.User.PaymentHistory;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PackageInfoUI extends JPanel{


    PtablePackage Ptable;
    String _username;

    static JLabel Lid;
    static JLabel Lname;
    static JLabel Llimit;
    static JLabel LstartDate;
    static JLabel LendDate;
    static JLabel Lprice;
    final int LIMIT = 100000;

    static JButton BbuyPackage;

    public PackageInfoUI(String username) throws SQLException {
        // init panel
        this.setLayout(null);
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        //setPreferredSize(new Dimension(500, 250));
        this.setVisible(false);

        _username = username;

        Ptable = new PtablePackage();


        Lid = new JLabel("ID: ");
        Lid.setBounds(10,150,200,30);
        Lname = new JLabel("Name: ");
        Lname.setBounds(200,150,300,30);
        LstartDate = new JLabel("Start Date: ");
        LstartDate.setBounds(10,180,300,30);
        LendDate = new JLabel("End Date: ");
        LendDate.setBounds(200,180,300,30);
        Llimit = new JLabel("Limit: ");
        Llimit.setBounds(10,210,300,30);
        Lprice = new JLabel("Price: ");
        Lprice.setBounds(200,210,300,30);

        BbuyPackage = new JButton("Buy");
        BbuyPackage.setBounds(10,250,70,30);
        BbuyPackage.setEnabled(false);

        this.add(BbuyPackage);
        this.add(Lid);
        this.add(Lname);
        this.add(LstartDate);
        this.add(LendDate);
        this.add(Llimit);
        this.add(Lprice);
        this.add(Ptable);


        BbuyPackage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (LIMIT < BalanceUI.getDebt()){
                        JOptionPane.showMessageDialog(null, "Debt has reached its limit \n Please settle the account.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                String sql = "INSERT INTO ql_order(order_id, customer_id, package_id,order_date) " +
                        "VALUES (?, ?, ?, ?)";
                PreparedStatement statement = null;
                try {
                    statement = DatabaseConnection.getJDBC().prepareStatement(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                int id_order = 1;
                while(true) {
                    try {
                        statement.setString(1, String.valueOf(id_order));
                        statement.setString(2, _username);
                        statement.setString(3, Ptable.getSelectedPackageID());
                        statement.setDate(4, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

                        statement.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Succeeded", "Message", JOptionPane.INFORMATION_MESSAGE);

                        PtablePurchase.resetModel();
                        break;
                    } catch (SQLException ex) {
                        id_order+=1;
                        ex.printStackTrace();
                    }
                }



            }
        });

    }

    public static void selectRowData(PackageClass x){
        Lid.setText("ID: "+x.get_ID());
        Lname.setText("Name: "+x.get_name());
        LstartDate.setText("Start Date: "+x.get_startDate());
        LendDate.setText("End Date: "+x.get_endDate());
        Llimit.setText("Limit: "+x.get_limit());
        Lprice.setText("Price: "+x.get_price());

    }

    public static void turnOnButton(){
        BbuyPackage.setEnabled(true);
    }






}
