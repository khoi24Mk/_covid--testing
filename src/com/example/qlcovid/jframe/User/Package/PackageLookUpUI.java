package com.example.qlcovid.jframe.User.Package;

import com.example.qlcovid.jframe.User.PtablePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PackageLookUpUI extends JPanel{

    PtablePackage Ptable;

    public PackageLookUpUI() throws SQLException {
        this.setLayout(null);/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.add(new JButton("PackageLookUpUI"));
        this.setVisible(false);

        Ptable = new PtablePackage();

        JButton Border = new JButton("Order");
        Border.setBounds(280,280,100,30);

        JLabel Lid = new JLabel("ID: ");
        Lid.setBounds(10,110,50,30);
        JComboBox CBid = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBid.setBounds(95,100,70,20);
        CBid.setSelectedItem("--");

        JLabel Lname = new JLabel("Name: ");
        Lname.setBounds(210,110,50,30);
        JComboBox CBname = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBname.setBounds(310,100,70,20);
        CBname.setSelectedItem("--");

        JLabel Llimit = new JLabel("Limit: ");
        Llimit.setBounds(5,160,50,30);
        JComboBox CBlimit = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBlimit.setBounds(95,150,70,20);
        CBlimit.setSelectedItem("--");

        JLabel Lstart = new JLabel("Start: ");
        Lstart.setBounds(5,215,50,30);
        JComboBox CBstart = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBstart.setBounds(110,205,70,20);
        CBstart.setSelectedItem("--");

        JLabel Lend = new JLabel("End: ");
        Lend.setBounds(210,215,50,30);
        JComboBox CBend = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBend.setBounds(310,205,70,20);
        CBend.setSelectedItem("--");

        JLabel Lprice = new JLabel("Price: ");
        Lprice.setBounds(210,160,50,30);
        JComboBox CBprice = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBprice.setBounds(310,150,70,20);
        CBprice.setSelectedItem("--");

        // searching
        JButton Bsearch = new JButton("Search");
        Bsearch.setBounds(20,280,100,30);

        JTextField Tid = new JTextField();
        Tid.setBounds(40,110,50,30);

        JTextField Tname = new JTextField();
        Tname.setBounds(250,110,55,30);

        JTextField Tlimit = new JTextField();
        Tlimit.setBounds(40,160,50,30);

        JTextField Tstart = new JTextField("yyyy-mm-dd");
        Tstart.setBounds(40,215,70,30);

        JTextField Tend = new JTextField("yyyy-mm-dd");
        Tend.setBounds(240,215,70,30);

        JTextField Tprice = new JTextField();
        Tprice.setBounds(250,160,55,30);


        JButton Bfilter = new JButton("Filter");
        Bfilter.setBounds(150,280,100,30);

        JComboBox CBlimitFilter = new JComboBox(new String[]{">","<=","--"});
        CBlimitFilter.setBounds(95,175,70,20);
        CBlimitFilter.setSelectedItem("--");

        JComboBox CBstartFilter = new JComboBox(new String[]{">","<=","--"});
        CBstartFilter.setBounds(110,230,70,20);
        CBstartFilter.setSelectedItem("--");

        JComboBox CBendFilter = new JComboBox(new String[]{">","<=","--"});
        CBendFilter.setBounds(310,230,70,20);
        CBendFilter.setSelectedItem("--");

        JComboBox CBpriceFilter = new JComboBox(new String[]{">","<=","--"});
        CBpriceFilter.setBounds(310,175,70,20);
        CBpriceFilter.setSelectedItem("--");

        /*showAdllButton*/
        JButton BshowAll = new JButton("Show All");
        BshowAll.setBounds(140,320,120,30);

        /*button*/
        this.add(Bsearch);
        this.add(Bfilter);
        this.add(Border);
        this.add(BshowAll);

        /*filter*/
        this.add(CBlimitFilter);
        this.add(CBpriceFilter);
        this.add(CBstartFilter);
        this.add(CBendFilter);

        /*combobox*/
        this.add(CBid);
        this.add(CBname);
        this.add(CBlimit);
        this.add(CBprice);
        this.add(CBstart);
        this.add(CBend);

        /*textfile*/
        this.add(Tid);
        this.add(Tname);
        this.add(Tlimit);
        this.add(Tprice);
        this.add(Tstart);
        this.add(Tend);

        /*label*/
        this.add(Lid);
        this.add(Lname);
        this.add(Llimit);
        this.add(Lprice);
        this.add(Lstart);
        this.add(Lend);

        this.add(Ptable);


        System.out.println(CBid.getSelectedItem());
        System.out.println(CBname.getSelectedItem());
        System.out.println(CBlimit.getSelectedItem());

        Border.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ptable.sortTable(
                        (String)CBid.getSelectedItem(),
                        (String)CBname.getSelectedItem(),
                        (String) CBlimit.getSelectedItem(),
                        (String)CBstart.getSelectedItem(),
                        (String)CBend.getSelectedItem(),
                        (String) CBprice.getSelectedItem()
                );
            }
        });

        Bsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Ptable.searchingTable(
                            Tid.getText(),
                            Tname.getText(),
                            Tlimit.getText(),
                            Tstart.getText(),
                            Tend.getText(),
                            Tprice.getText()
                    );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        Bfilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Ptable.filterTable(
                            Tid.getText(),
                            Tname.getText(),
                            Tlimit.getText(),
                            Tstart.getText(),
                            Tend.getText(),
                            Tprice.getText(),

                            (String)CBlimitFilter.getSelectedItem(),
                            (String)CBstartFilter.getSelectedItem(),
                            (String)CBendFilter.getSelectedItem(),
                            (String)CBpriceFilter.getSelectedItem()
                    );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        BshowAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Ptable.refreshTalbe();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
