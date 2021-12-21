package com.example.qlcovid.jframe.User.Info;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PaymentUI extends JPanel{

    Socket _client;
    String _msg;



    public PaymentUI() throws IOException {
        this.setLayout(null);/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);



        JButton Bconnect = new JButton("Transfer");
        Bconnect.setBounds(10,10,100,30);


        JLabel Linput = new JLabel("Enter amount: ");
        Linput.setBounds(10,50,300,30);

        JTextField Tinput = new JTextField();
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
                bfWriter.write("123124");
                bfWriter.newLine(); //HERE!!!!!!
                bfWriter.flush();

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
