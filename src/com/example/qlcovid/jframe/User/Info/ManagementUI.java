package com.example.qlcovid.jframe.User.Info;

import com.example.qlcovid.jframe.User.PtablePatientHistory;
import com.example.qlcovid.model.User.PatientHistory;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ManagementUI extends JPanel {

    ArrayList<PatientHistory> listTreament;
    PtablePatientHistory PtableHistory;

    static JLabel Lid;
    static JLabel Lpatient_ID;
    static JLabel Ldate;
    static JLabel Laction;

    public ManagementUI(String username) throws SQLException {
        this.setLayout(null);/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.setVisible(false);


        // connect to database
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM patient_history\n"+
                "WHERE patient_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        listTreament = new ArrayList<PatientHistory>();
        PatientHistory temp = new PatientHistory();
        while(rs.next()){

            temp.set_treatment_ID(rs.getString("patient_history_id"));
            temp.set_patient_ID(rs.getString("patient_id"));
            temp.set_patientAction(rs.getString("patient_action"));
            temp.set_startDate(rs.getString("patient_date"));

            listTreament.add(temp);
        }
        System.out.println("Management History "+temp.getInfo());

        PtableHistory = new PtablePatientHistory(username);

        Lid = new JLabel("ID: ");
        Lid.setBounds(10,150,200,30);
        Lpatient_ID = new JLabel("Patient ID: ");
        Lpatient_ID.setBounds(200,150,300,30);
        Ldate = new JLabel("Start Date: ");
        Ldate.setBounds(10,180,300,30);
        Laction = new JLabel("Action: ");
        Laction.setBounds(10,210,400,100);

        this.add(Laction);
        this.add(Ldate);
        this.add(Lpatient_ID);
        this.add(Lid);
        this.add(PtableHistory);



    }
     public static void selectRowData(PatientHistory x){
        Lid.setText("ID: "+x.get_treatment_ID());
        Lpatient_ID.setText("Patient ID: "+x.get_patient_ID());
        Ldate.setText("Start Date: "+x.get_startDate());
        Laction.setText("Action: "+x.get_patientAction());

    }
}



















