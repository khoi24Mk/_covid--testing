package com.example.qlcovid.jframe.User.Info;

import com.example.qlcovid.jframe.User.PtablePatientHistory;
import com.example.qlcovid.model.User.PatientHistory;
import com.example.qlcovid.model.User.PaymentHistory;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BalanceUI extends JPanel{

    PtablePayment tablePayment;

    static JLabel Lid;
    static JLabel Lcustomer_ID;
    static JLabel Ldate;
    static JLabel Lprice;

    static JLabel Ldebt;

    public BalanceUI(String username) throws SQLException {
        this.setLayout(null);/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);

        this.setVisible(false);

        tablePayment = new PtablePayment(username);

        int debt = this.getAllOrders(username) - PtablePayment.getAllPayment();
        Ldebt = new JLabel("Debt: "+debt);
        Ldebt.setBounds(10,125,200,30);
        if (debt ==0){
            Ldebt.setForeground(Color.green);
        }
        else{
            Ldebt.setForeground(Color.red);
        }

        Lid = new JLabel("ID: ");
        Lid.setBounds(10,150,200,30);
        Lcustomer_ID = new JLabel("Customer ID: ");
        Lcustomer_ID.setBounds(200,150,300,30);
        Ldate = new JLabel("Date: ");
        Ldate.setBounds(10,180,300,30);
        Lprice = new JLabel("Action: ");
        Lprice.setBounds(10,210,400,100);


        this.add(Ldebt);
        this.add(Lprice);
        this.add(Ldate);
        this.add(Lcustomer_ID);
        this.add(Lid);
        this.add(tablePayment);
    }

    public static void selectRowData(PaymentHistory x){
        Lid.setText("ID: "+x.get_ID());
        Lcustomer_ID.setText("Customer ID: "+x.get_customer_ID());
        Ldate.setText("Date: "+x.get_date());
        Lprice.setText("Price: "+x.get_price());

    }

    public int getAllOrders(String username) throws SQLException {
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT SUM(package.price) as price" +
                " FROM ql_order\n" +
                "JOIN package\n" +
                "ON ql_order.package_id = package.package_id\n" +
                "WHERE ql_order.customer_id = "+username+";";
        ResultSet rs = statement.executeQuery(sql);

        int sum = 0;
        while (rs.next()) {
            sum =  rs.getInt("price");
        }
        return sum;
    }
}
