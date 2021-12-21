package com.example.qlcovid.jframe.User.Info;

import com.example.qlcovid.model.User.CovidPatient;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InfoUI extends JPanel {

    ArrayList<CovidPatient> listPatient;

    public InfoUI(String username) throws SQLException {
        this.setLayout(null);
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);



        JLabel LID = new JLabel();
        LID.setBounds(10,10,200,30);
        JLabel Lname = new JLabel();
        Lname.setBounds(10,50,200,30);
        JLabel LDOB = new JLabel();
        LDOB.setBounds(270,50,200,30);
        JLabel Ladd = new JLabel();
        Ladd.setBounds(10,90,400,30);
        JLabel Lstatus = new JLabel();
        Lstatus.setBounds(10,130,200,30);
        JLabel Ltreatment = new JLabel();
        Ltreatment.setBounds(10,170,500,30);


        JLabel Lrelevant = new JLabel();
        Lrelevant.setBounds(10,220,500,200);
        //Lrelevant.setBackground(Color.red);  for debug
        //Lrelevant.setOpaque(true);

        DefaultTableModel treatmentInfo = new DefaultTableModel(new String[]{},0);

        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM covid_patient\n"+
                "WHERE citizen_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        listPatient = new ArrayList<CovidPatient>();
        CovidPatient temp = new CovidPatient();
        while(rs.next()){

            temp.set_citizen_id(rs.getString("citizen_id"));
            temp.set_name(rs.getString("full_name"));
            temp.set_dob(rs.getString("date_of_birth"));
            temp.set_address(rs.getString("citizen_address"));
            temp.set_status(rs.getString("condition"));
            temp.set_treatmentArea(rs.getString("treatment_place_id"));
            temp.set_patientRelavent(rs.getString("related_to"));

            listPatient.add(temp);
        }
        System.out.println(temp.getInfo());

        LID.setText("ID: "+temp.get_citizen_id());
        Lname.setText("Name: "+temp.get_name());
        LDOB.setText("DOB: "+temp.get_dob());
        Ladd.setText("Address:      "+temp.get_address());
        Lstatus.setText("Condition: "+temp.get_status());
        Ltreatment.setText("Treatment place: "+temp.get_treatmentArea());
        Lrelevant.setText("<html><style>tr {\n" +
                "    border-top: 1px solid black;\n" +
                "    border-collapse: collapse;\n" +
                "}\u200B</style><body>Relevant Patient<br>"+temp.get_patientRelavent(username)+"</body></html>");//
        Lrelevant.setVerticalAlignment(SwingConstants.TOP);
                         //+temp.get_patientRelavent(username)
//<html><body>sth<br>"+"</body></html>

        this.add(Lname);
        this.add(LDOB);
        this.add(Ladd);
        this.add(Lstatus);
        this.add(Ltreatment);
        this.add(Lrelevant);
        //------------------------------------------------
    }
}
