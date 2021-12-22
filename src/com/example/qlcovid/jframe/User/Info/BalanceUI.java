package com.example.qlcovid.jframe.User.Info;

import com.example.qlcovid.jframe.User.PtablePatientHistory;
import com.example.qlcovid.jframe.User.PtablePurchase;
import com.example.qlcovid.model.User.PatientHistory;
import com.example.qlcovid.model.User.PaymentHistory;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BalanceUI extends JPanel{

    PtablePayment tablePayment;

    static JLabel Lid;
    static JLabel Lcustomer_ID;
    static JLabel Ldate;
    static JLabel Lprice;
    static JButton Brefresh;

    static JLabel Ldebt;

    static String _username;

    public BalanceUI(String username) throws SQLException, ParseException {
        this.setLayout(null);/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);

        this.setVisible(false);

        this._username = username;

        tablePayment = new PtablePayment(_username);

        int debt = this.getAllOrders() - PtablePayment.getAllPayment();
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
        Brefresh = new JButton("Refresh");
        Brefresh.setBounds(200,200,120,30);

        this.add(Brefresh);
        this.add(Ldebt);
        this.add(Lprice);
        this.add(Ldate);
        this.add(Lcustomer_ID);
        this.add(Lid);
        this.add(tablePayment);




        Brefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    setDebt();
                    PtablePayment.resetModel();
                } catch (SQLException | ParseException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    public static void selectRowData(PaymentHistory x){
        Lid.setText("ID: "+x.get_ID());
        Lcustomer_ID.setText("Customer ID: "+x.get_customer_ID());
        Ldate.setText("Date: "+x.get_date());
        Lprice.setText("Price: "+x.get_price());

    }

    public static int getAllOrders() throws SQLException {
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT SUM(package.price) as price" +
                " FROM ql_order\n" +
                "JOIN package\n" +
                "ON ql_order.package_id = package.package_id\n" +
                "WHERE ql_order.customer_id = '"+_username+"';";
        ResultSet rs = statement.executeQuery(sql);

        int sum = 0;
        while (rs.next()) {
            sum =  rs.getInt("price");
        }
        return sum;
    }


    public void setDebt() throws SQLException {

        System.out.println("DEBT");
        System.out.println(getAllOrders());
        System.out.println(PtablePayment.getAllPayment());
        System.out.println(_username);

        int debt = getAllOrders() - PtablePayment.getAllPayment();
        Ldebt.setText("Debt: "+debt);

    }

    public static int getDebt() throws SQLException {
        return Integer.parseInt( Ldebt.getText().substring(6));

    }


}
