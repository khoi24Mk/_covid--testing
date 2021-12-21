package com.example.qlcovid.jframe.User;

import com.example.qlcovid.jframe.PatientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoAbility extends JPanel{
    // its functionality
    JButton _Binfo;
    JButton _Bmanagement;
    JButton _BpackageConsume;
    JButton _Bbalance;
    JButton _Bpayment;

    public InfoAbility(){
        //setup panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
        this.setBackground(new Color(0xFFE652)); // for debug
        this.setBounds(0,0,400,80);
        this.setVisible(true);
        // init button
        _Binfo = new JButton("Info");
        _Bmanagement = new JButton("Management");
        _BpackageConsume = new JButton("Package");
        _Bbalance = new JButton("Balance");
        _Bpayment = new JButton("Payment");


        // add button
        this.add(_Binfo);
        this.add(_Bmanagement);
        this.add(_BpackageConsume);
        this.add(_Bbalance);
        this.add(_Bpayment);

        // Button listioner
        _Binfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_Binfo");
                PatientGUI.showBasicInfo();
            }
        });

        _Bmanagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_Bmanagement");
                PatientGUI.showManagementInfo();
            }
        });

        _BpackageConsume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_BpackageConsume");
                PatientGUI.showPackageInfo();
            }
        });

        _Bbalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_Bbalance");
                PatientGUI.showBalanceInfo();
            }
        });

        _Bpayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_Bpayment");
                PatientGUI.showPaymentInfo();
            }
        });
    }



}





































