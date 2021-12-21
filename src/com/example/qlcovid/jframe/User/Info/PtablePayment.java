package com.example.qlcovid.jframe.User.Info;

import com.example.qlcovid.jframe.User.Package.PackageInfoUI;
import com.example.qlcovid.model.User.PackageClass;
import com.example.qlcovid.model.User.PatientHistory;
import com.example.qlcovid.model.User.PaymentHistory;
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

public class PtablePayment extends JPanel {

    static ArrayList<PaymentHistory> historyList;
    JTable paymentTalbe;
    DefaultTableModel modelListPayment;


    public PtablePayment(String username) throws SQLException {

        // getting data from database
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM payment_history\n" +
                "WHERE citizen_id=" + username + ";";
        ResultSet rs = statement.executeQuery(sql);

        historyList = new ArrayList<PaymentHistory>();
        while (rs.next()) {

            historyList.add(new PaymentHistory(
                    rs.getString("payment_history_id"),
                    rs.getString("citizen_id"),
                    rs.getString("payment_date"),
                    Integer.parseInt(rs.getString("payment_amount"))
            ));
        }

        for (PaymentHistory x : historyList) {
            System.out.println(x.getInfo());
        }


        this.setOpaque(true);
        this.setBounds(50, 0, 300, 100);

        this.setLayout(new BorderLayout());

        modelListPayment = new DefaultTableModel(
                new String[]{"ID", "Citizen ID", "Date", "Price"},
                0
        );
        for (PaymentHistory x : historyList) {
            modelListPayment.addRow(x.getObject());
        }
       /* for(PatientHistory x : historyList){
            modelListHistory.addRow(x.getObject());
        }
        for(PatientHistory x : historyList){
            modelListHistory.addRow(x.getObject());
        }*/

        paymentTalbe = new JTable(modelListPayment);//modelListPackage
        //PatientHistoryTalbe.setBounds(10,10,400,200);
        //PatientHistoryTalbe.setPreferredScrollableViewportSize(new Dimension(400, 250));

        paymentTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //PatientHistoryTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);


        JScrollPane pane = new JScrollPane(paymentTalbe);
        this.add(pane, BorderLayout.CENTER);
        paymentTalbe.setDefaultEditor(Object.class, null);
        paymentTalbe.getTableHeader().setReorderingAllowed(false);



        paymentTalbe.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {


                for(PaymentHistory x: historyList){
                    if(x.get_ID().equals(paymentTalbe.getValueAt(paymentTalbe.getSelectedRow(), 0).toString())){
                        BalanceUI.selectRowData(x);
                    }

                }
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(paymentTalbe.getValueAt(paymentTalbe.getSelectedRow(), 0).toString());

            }
        });
    }

    public static int getAllPayment(){
        int sum = 0;
        for (PaymentHistory x : historyList){
            sum+= x.get_price();
        }
        return sum;
    }

}







