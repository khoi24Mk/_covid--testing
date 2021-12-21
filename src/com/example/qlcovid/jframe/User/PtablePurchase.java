package com.example.qlcovid.jframe.User;

import com.example.qlcovid.jframe.User.Info.PurchaseUI;
import com.example.qlcovid.model.User.PackagePurchase;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PtablePurchase extends JPanel{

    static ArrayList<PackagePurchase> listPurchase;
    static JTable purchaseTable;
    static DefaultTableModel modelListPurchase;

    static String _username;

    public PtablePurchase(String username)  throws SQLException {

        _username = username;

        // getting data from database
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM ql_order\n"+
                "WHERE customer_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        listPurchase = new ArrayList<PackagePurchase>();
        while(rs.next()){

            listPurchase.add(new PackagePurchase(
                    rs.getString("order_id"),
                    rs.getString("customer_id"),
                    rs.getString("package_id"),
                    rs.getString("order_date")
            ));
        }

        for(PackagePurchase x : listPurchase){
            System.out.println(x.getInfo());
        }


        this.setOpaque(true);
        this.setBounds(50,0,300,100);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 250));

        modelListPurchase = new DefaultTableModel(
                new String[] { "ID","Customer ID","Package ID","Date"},
                0
        );
        for(PackagePurchase x : listPurchase){
            modelListPurchase.addRow(x.getObject());
        }
//        for(PackagePurchase x : listPurchase){
//            modelListPurchase.addRow(x.getObject());
//        }
//        for(PackagePurchase x : listPurchase){
//            modelListPurchase.addRow(x.getObject());
//        }

        purchaseTable = new JTable(modelListPurchase);//modelListPackage
        //packageTalbe.setBounds(10,10,400,200);
        //packageTalbe.setPreferredScrollableViewportSize(new Dimension(300, 10));

        purchaseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane = new JScrollPane(purchaseTable);
        this.add(pane, BorderLayout.CENTER);
        purchaseTable.setDefaultEditor(Object.class, null);
        purchaseTable.getTableHeader().setReorderingAllowed(false);


        purchaseTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {


                for(PackagePurchase x: listPurchase){
                    if(x.get_ID().equals(purchaseTable.getValueAt(purchaseTable.getSelectedRow(), 0).toString())){
                        PurchaseUI.selectRowData(x);
                    }

                }
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(purchaseTable.getValueAt(purchaseTable.getSelectedRow(), 0).toString());

            }
        });




    }

    public static void resetModel() throws SQLException {
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM ql_order\n"+
                "WHERE customer_id="+_username+";";
        ResultSet rs = statement.executeQuery(sql);

        listPurchase.clear();
        while(rs.next()){

            listPurchase.add(new PackagePurchase(
                    rs.getString("order_id"),
                    rs.getString("customer_id"),
                    rs.getString("package_id"),
                    rs.getString("order_date")
            ));
        }

        modelListPurchase = new DefaultTableModel(
                new String[] { "ID","Customer ID","Package ID","Date"},
                0
        );
        for(PackagePurchase x : listPurchase){
            modelListPurchase.addRow(x.getObject());
        }
        purchaseTable.setModel(modelListPurchase);

    }
}
