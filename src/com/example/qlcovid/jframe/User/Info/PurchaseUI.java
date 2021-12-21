package com.example.qlcovid.jframe.User.Info;

import com.example.qlcovid.jframe.User.PtablePurchase;
import com.example.qlcovid.model.User.PackagePurchase;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class PurchaseUI extends JPanel {

    ArrayList<PackagePurchase> listPurchase;
    PtablePurchase tablePurchase;

    static JLabel Lid;
    static JLabel Lcustomer_ID;
    static JLabel Ldate;
    static JLabel Lpackage_ID;

    String _username;

    public PurchaseUI(String username) throws SQLException {
        this.setLayout(null); /////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0, 80, 400, 390);
        this.setVisible(false);

        _username = username;
        // getting data from database
//        Statement statement = DatabaseConnection.getJDBC().createStatement();
//        String sql = "SELECT DISTINCT * FROM ql_order "+
//                "WHERE customer_id='"+username+"';";
//        ResultSet rs = statement.executeQuery(sql);
//
//        listPurchase = new ArrayList<PackagePurchase>();
//        while(rs.next()){
//            listPurchase.add(new PackagePurchase(
//                    rs.getString("order_id"),
//                    rs.getString("customer_id"),
//                    rs.getString("package_id"),
//                    rs.getString("order_date")
//            ));
//        }
//        for(PackagePurchase x : listPurchase){
//            System.out.println(x.getInfo()+"hello");
//        }
        tablePurchase = new PtablePurchase(username);


        Lid = new JLabel("Order ID: ");
        Lid.setBounds(10,150,200,30);
        Lcustomer_ID = new JLabel("Customer ID: ");
        Lcustomer_ID.setBounds(200,150,300,30);
        Ldate = new JLabel("Package ID: ");
        Ldate.setBounds(10,180,200,30);
        Lpackage_ID = new JLabel("Date: ");
        Lpackage_ID.setBounds(10,210,400,100);


        this.add(Lpackage_ID);
        this.add(Ldate);
        this.add(Lcustomer_ID);
        this.add(Lid);


        this.add(tablePurchase);
    }

    public static void selectRowData(PackagePurchase x){
        Lid.setText("Order ID: "+x.get_ID());
        Lcustomer_ID.setText("Customer ID: "+x.get_customer_ID());
        Ldate.setText("Date: "+x.get_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Lpackage_ID.setText("Package ID: "+x.get_package_ID());

    }
}
