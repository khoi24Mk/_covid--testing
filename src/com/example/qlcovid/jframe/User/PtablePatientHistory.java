package com.example.qlcovid.jframe.User;


import com.example.qlcovid.jframe.User.Info.ManagementUI;
import com.example.qlcovid.model.User.PatientHistory;
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

public class PtablePatientHistory extends JPanel{

    ArrayList<PatientHistory> historyList;
    JTable PatientHistoryTalbe;
    DefaultTableModel modelListHistory;

    public PtablePatientHistory(String username)  throws SQLException {

        // getting data from database
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM patient_history\n"+
                "WHERE patient_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        historyList = new ArrayList<PatientHistory>();
        while(rs.next()){

            historyList.add(new PatientHistory(
                    rs.getString("patient_history_id"),
                    rs.getString("patient_id"),
                    rs.getString("patient_action"),
                    rs.getString("patient_date")
                    ));
        }

        for(PatientHistory x : historyList){
            System.out.println(x.getInfo());
        }


        this.setOpaque(true);
        this.setBounds(50,0,300,100);

        this.setLayout(new BorderLayout());

        modelListHistory = new DefaultTableModel(
                new String[] { "ID","Patient ID","Patient action","Date"},
                0
        );
        for(PatientHistory x : historyList){
            modelListHistory.addRow(x.getObject());
        }
       /* for(PatientHistory x : historyList){
            modelListHistory.addRow(x.getObject());
        }
        for(PatientHistory x : historyList){
            modelListHistory.addRow(x.getObject());
        }*/

        PatientHistoryTalbe = new JTable(modelListHistory);//modelListPackage
        //PatientHistoryTalbe.setBounds(10,10,400,200);
        //PatientHistoryTalbe.setPreferredScrollableViewportSize(new Dimension(400, 250));

        PatientHistoryTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //PatientHistoryTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);


        JScrollPane pane = new JScrollPane(PatientHistoryTalbe);
        this.add(pane, BorderLayout.CENTER);
        PatientHistoryTalbe.setDefaultEditor(Object.class, null);
        PatientHistoryTalbe.getTableHeader().setReorderingAllowed(false);

        PatientHistoryTalbe.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {


                for(PatientHistory x: historyList){
                    if(x.get_treatment_ID().equals(PatientHistoryTalbe.getValueAt(PatientHistoryTalbe.getSelectedRow(), 0).toString())){
                        ManagementUI.selectRowData(x);
                    }

                }
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(PatientHistoryTalbe.getValueAt(PatientHistoryTalbe.getSelectedRow(), 0).toString());

            }
        });







    }
}
