package com.example.qlcovid.jframe.User.Info;

import com.example.qlcovid.jframe.User.PtablePurchase;
import com.example.qlcovid.model.User.PaymentHistory;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PaymentUI extends JPanel{

    Socket _client;
    String _msg;
    String _username;

    JTextField Tinput;


    public PaymentUI(String username) throws IOException {
        this.setLayout(null);/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);

        _username = username;

        JButton Bconnect = new JButton("Transfer");
        Bconnect.setBounds(10,10,100,30);


        JLabel Linput = new JLabel("Enter amount: ");
        Linput.setBounds(10,50,300,30);

        Tinput = new JTextField();
        Tinput.setBounds(100,50,100,30);


        this.add(Tinput);
        this.add(Linput);
        this.add(Bconnect);
        this.setVisible(false);


        Bconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = Tinput.getText();

                try {
                    Integer.parseInt(msg);

                    _msg =msg;
                    IOSocket write = new IOSocket(false);
                    write.start();

                } catch(NumberFormatException err){
                    System.out.println(err);
                }


            }
        });




        new Thread(){
          @Override public void run() {
              while(true){
                  try {
                      System.out.println("sth herererere");
                      _client = new Socket(InetAddress.getLocalHost(),5555);
                      IOSocket read = new IOSocket(true);
                      read.start();
                      break;
                  }
                  catch (ConnectException | UnknownHostException err){
                     System.out.println(err);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }

              }
          }
        }.start();

    }

    public void insertPayment() {
        String sql = "SET IDENTITY_INSERT payment_history ON \n" +
                "INSERT INTO payment_history(payment_history_id, citizen_id, payment_date,payment_amount) " +
                "VALUES (?, ?, ?, ?) \n" +
                "SET IDENTITY_INSERT payment_history OFF";
        PreparedStatement statement = null;
        try {
            statement = DatabaseConnection.getJDBC().prepareStatement(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        int id_order = 1;
        while(true) {
            try {
                statement.setString(1, String.valueOf(id_order));
                statement.setString(2, _username);
                statement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
                //ZonedDateTime.parse(LocalDateTime.now())
                //LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"))
                statement.setString(4, Tinput.getText() );

                statement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Succeeded", "Message", JOptionPane.INFORMATION_MESSAGE);
                PtablePayment.resetModel();
                break;
            } catch (SQLException | ParseException ex) {
                id_order+=1;
                ex.printStackTrace();
            }
        }




    }






    class IOSocket extends Thread{
        /*private Socket _client;*/
        private boolean _flag;

        public IOSocket( boolean flag){
            /*_client = client;*/
            _flag = flag;
        }

        @Override
        public void run() {
            if(_flag==true) {
                this.reader();
            }
            else{
                try {
                    this.writer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        public void reader(){
            BufferedReader input = null;

            try {
                input = new BufferedReader( new InputStreamReader( _client.getInputStream() ) );

                while (true){
                    String msg = input.readLine();
                    System.out.println(msg);
                }
            } catch (IOException e) {
                try {
                    input.close();
                    _client.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        }


        public void writer() throws IOException {
            System.out.println("sthhere");
            DataOutputStream out = null;
            BufferedWriter bfWriter;
            Scanner scanner = null;
            try {
                System.out.println("inn");
                out = new DataOutputStream(_client.getOutputStream());
                bfWriter =  new BufferedWriter(new OutputStreamWriter(_client.getOutputStream()));
                scanner = new Scanner(System.in);
                bfWriter.write(Tinput.getText());
                bfWriter.newLine(); //HERE!!!!!!
                bfWriter.flush();

                insertPayment();


               /* while (true){
                    System.out.println("sth");
                    String message = scanner.nextLine();
                    System.out.println("Sending: "+message);
                    *//*out.writeUTF(message);*//*
                 *//*bfWriter.println(message);
                    bfWriter.flush();*//*
                    bfWriter.write(message);
                    bfWriter.newLine(); //HERE!!!!!!
                    bfWriter.flush();
                }*/
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
                _client.close();
                out.close();
            }

        }


    }
}
