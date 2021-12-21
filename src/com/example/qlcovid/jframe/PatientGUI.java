package com.example.qlcovid.jframe;

import com.example.qlcovid.jframe.User.*;
import com.example.qlcovid.jframe.User.Info.*;
import com.example.qlcovid.jframe.User.Package.PackageInfoUI;
import com.example.qlcovid.jframe.User.Package.PackageLookUpUI;
import com.example.qlcovid.jframe.User.Package.PackagePurchaseUI;
import com.example.qlcovid.string.DatabaseConnection;
//import logintest.loginUserTesting;
import com.example.qlcovid.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.ActionListener;
import java.sql.Statement;



public class PatientGUI extends JFrame {

    String _username;

    // attributes
    // default width and height
    final int WIDTH =500;
    final int HEIGHT =500;

    // option button [3]:
    JButton _BinfoOption;
    JButton _BpackageOption;
    JButton _BpaymentOption;

    //logout
    JButton _Blogout;
    JButton _BchangePass;

    // barPanel
    JPanel _Poption;
    JPanel _Pheader;
    JPanel _Pswapping;

    // // info panel: INFO//////////////////////////////
    static InfoAbility PinfoAbility ;
    static InfoUI PbasicInfo ;
    static ManagementUI PmanagementInfo ;
    static PurchaseUI PpackageInfo;
    static BalanceUI PbalanceInfo;
    static PaymentUI PpaymentInfo;
    
    // // package panel: PURCHASE///////////////////////////////
    static PackageAbility PpackageAbility;
    static PackageInfoUI PlistPackage;
    static PackageLookUpUI PlookupPackage;
    static PackagePurchaseUI PpurchasePackge;
    // ---------------------------------------




    /////////////////////////////////////
    JPanel paneltop;
    JPanel panelleft;
    JButton button;


    // constructor
    public PatientGUI(String username) throws SQLException, IOException {

        // title
        super("Covid Patient");

        _username = username;

        // set up frame
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(515,500);
        this.setResizable(false);
        //--

        // init 3 option:
        _BinfoOption = new JButton("Info");
        _BpackageOption = new JButton("Package");
        _BpaymentOption = new JButton("Payment");
        _BinfoOption.setFocusable(false);
        _BpackageOption.setFocusable(false);
        _BpaymentOption.setFocusable(false);
        _BinfoOption.setBackground(new Color(0x71DFE7));
        _BpackageOption.setBackground(new Color(0x71DFE7));
        _BpaymentOption.setBackground(new Color(0x71DFE7));
        _BinfoOption.setBounds(0,0,100,30);
        _BpackageOption.setBounds(0,30,100,30);
        _BpaymentOption.setBounds(0,60,100,30);
        //--


        // info panel: INFO ability
        // setup panel
        PinfoAbility = new InfoAbility();
        PbasicInfo = new InfoUI(username);
        PmanagementInfo = new ManagementUI(username);
        PpackageInfo = new PurchaseUI(username);
        PbalanceInfo = new BalanceUI(username);
        PpaymentInfo= new PaymentUI();
        //--

        // package panel: PURCHASE ability
        //setup panel
        PpackageAbility = new PackageAbility();
        PlistPackage = new PackageInfoUI(username);
        PlookupPackage = new PackageLookUpUI();
        PpurchasePackge = new PackagePurchaseUI();
        //--



        //bar 1: OPTION
        //setup panel
        _Poption = new JPanel();
        _Poption.setLayout(null);
        _Poption.setBackground(new Color(0x71DFE7)); // for debug
        _Poption.setBounds(0,30,100,470);

        // init and add element
        // add 3 option
        _Poption.add(_BinfoOption);
        _Poption.add(_BpackageOption);
        _Poption.add(_BpaymentOption);
        //--

        //bar 2: HEADER
        //setup panel
        _Pheader = new JPanel();
        _Pheader.setLayout(null);
        _Pheader.setBackground(new Color(0x009DAE)); // for debug
        _Pheader.setBounds(0,0,500,30);

        // its option
        _Blogout = new JButton("Log out");
        _Blogout.setBounds(300,0,80,30);

        _BchangePass = new JButton("Change Pass");
        _BchangePass.setBounds(380,0,120,30);

        _Pheader.add(_Blogout);
        _Pheader.add(_BchangePass);
        //--

        //bar 3: SWAPPING
        _Pswapping = new JPanel();
        _Pswapping.setLayout(null);
        _Pswapping.setBounds(100,30,400,470);
        //--

        // info scene
        // add INFO panel
        _Pswapping.add(PinfoAbility);
        _Pswapping.add(PbasicInfo);
        _Pswapping.add(PmanagementInfo);
        _Pswapping.add(PpackageInfo);
        _Pswapping.add(PbalanceInfo);
        _Pswapping.add(PpaymentInfo);
        // add PURCHASE panel
        _Pswapping.add(PpackageAbility);
        _Pswapping.add(PlistPackage);
        _Pswapping.add(PlookupPackage);
        _Pswapping.add(PpurchasePackge);
//-----------------------------------------



        // frame adding panel
        this.add(_Pswapping);
        this.add(_Poption);
        this.add(_Pheader);



        // Button Listioner
        _BinfoOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_BinfoOption");
                showInfoAbility();
            }
        });

        _BpackageOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_BpackageOption");
                showPurchaseAbility();
            }
        });


        // header option++++++++++++++
        //logout
        _Blogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_Blogout");

                dispose();
                setVisible(false);

                //loginUserTesting window = new loginUserTesting();
                LoginFrame window = new LoginFrame();
                window.frame.setVisible(true);
            }
        });

        //change password
        _BchangePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("_BchangePass");

                String oldPass = JOptionPane.showInputDialog("What's your current password?");


                Statement statement = null;

                try {
                    statement = DatabaseConnection.getJDBC().createStatement();

                    System.out.println("Hashing:  "+Hashing.getHash(oldPass));
                    String sql = "SELECT *  from ql_user\n" +
                            "WHERE user_password = '"+Hashing.getHash(oldPass)+"'";
                    System.out.println(sql);
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.next()) {
                        System.out.println("start change");
                        changePassword();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Wrong password","Warning",JOptionPane.WARNING_MESSAGE);
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();

                }


            }
        });


    }
    //-- end constructor


    //method

    // Group of function for switch INFO screen
    public static void setInfoInvisible() {
        PbasicInfo.setVisible(false);
        PmanagementInfo.setVisible(false);
        PpackageInfo.setVisible(false);
        PbalanceInfo.setVisible(false);
        PpaymentInfo.setVisible(false);

    }
    public static void showBasicInfo() {
        setInfoInvisible();
        setPackageInvisible();
        PbasicInfo.setVisible(true);
    }
    public static void showManagementInfo() {
        setInfoInvisible();
        PmanagementInfo.setVisible(true);
    }
    public static void showPackageInfo() {
        setInfoInvisible();
        PpackageInfo.setVisible(true);
    }
    public static void showBalanceInfo() {
        setInfoInvisible();
        PbalanceInfo.setVisible(true);
    }
    public static void showPaymentInfo() {
        setInfoInvisible();
        PpaymentInfo.setVisible(true);
    }


    // Group of function for switch INFO screen
    public static void setPackageInvisible() {
        PlistPackage.setVisible(false);
        PlookupPackage.setVisible(false);
        PpurchasePackge.setVisible(false);

    }
    public static void showListPackage() {
        setPackageInvisible();
        setInfoInvisible();
        PlistPackage.setVisible(true);
    }
    public static void showLookupPackage() {
        setPackageInvisible();
        PlookupPackage.setVisible(true);
    }
    public static void showPurchasePackage() {
        setPackageInvisible();
        PpurchasePackge.setVisible(true);
    }





    // Group of function for switch ABILITY screen

    public static void setAbilityInvisible() {
        setInfoInvisible();

        PinfoAbility.setVisible(false);
        PpackageAbility.setVisible(false);

    }

    public static void showInfoAbility() {
        setAbilityInvisible();

        showBasicInfo();
        PinfoAbility.setVisible(true);
    }

    public static void showPurchaseAbility() {
        setAbilityInvisible();

        showListPackage();
        PpackageAbility.setVisible(true);
    }



    //change pass
    public void changePassword() throws SQLException {
        JTextField Tpass = new JTextField(10);

        JTextField Tcomfirm = new JTextField(10);


        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("Password"));
        myPanel.add(Tpass);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Comfirm password"));
        myPanel.add(Tcomfirm);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Enter new password", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            if (Tpass.getText().equals(Tcomfirm.getText())){

                Statement statement = DatabaseConnection.getJDBC().createStatement();
                String sql = "UPDATE ql_user\n" +
                        " SET user_password = '"+ Hashing.getHash(Tcomfirm.getText()) +"'\n" +
                        " WHERE  username = '"+ _username +"'";
                System.out.println(sql);
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Your password was changed successful","Message",JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                JOptionPane.showMessageDialog(null,"Comfirm passoword was incorrect!!","Warning",JOptionPane.WARNING_MESSAGE);

            }
        }
    }




//-------------------------------------------------------

//    public static void main(String[] args) throws InterruptedException, SQLException, IOException {
//        PatientGUI screen = new PatientGUI("0323812314");
//        screen.setVisible(true);
//
//
////        TimeUnit.SECONDS.sleep(3);
////        screen.refresh();
//        //TimeUnit.SECONDS.sleep(3);
//        //screen.re();
//
//    }

}

