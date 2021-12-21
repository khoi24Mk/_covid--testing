package com.example.qlcovid.jframe.User;

import com.example.qlcovid.jframe.PatientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PackageAbility extends JPanel {
    // attributes
    JButton _BpackageInfo;
    JButton _BlookUp;
    JButton _BbuyPackage;

    public PackageAbility() {
        //setup panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        this.setBackground(new Color(0xFFE652)); // for debug
        this.setBounds(0, 0, 400, 80);
        this.setVisible(false);
        // init button
        _BpackageInfo = new JButton("Package Info");
        _BlookUp = new JButton("Look Up");
        _BbuyPackage = new JButton("Purchase");


        // add button
        this.add(_BpackageInfo);
        this.add(_BlookUp);
        this.add(_BbuyPackage);

        // Button listioner
        _BpackageInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_BpackageInfo");
                PatientGUI.showListPackage();
            }
        });

        _BlookUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_BlookUp");
                PatientGUI.showLookupPackage();
            }
        });

        _BbuyPackage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_BbuyPackage");
                PatientGUI.showPurchasePackage();
            }
        });
    }
}




































