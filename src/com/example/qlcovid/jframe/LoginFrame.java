package com.example.qlcovid.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.event.MenuKeyEvent;


import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.ParseException;
import javax.swing.SwingConstants;

import com.example.qlcovid.model.*;
import com.example.qlcovid.string.*;
public class LoginFrame extends JFrame {

	public JFrame frame;
	private JTextField user;
	private JPasswordField pass;
	//private Account thisAccount;
	private JPanel panel, panel_1;
	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3;
	private JButton btnLogin;
	
	/**
	 * Create the application.
	 */
	void closeLogin() {
		frame.setVisible(false);
		frame.dispose();
	}
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 255, 255));
		frame.setBounds(100, 100, 673, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 204, 255));
		panel.setBounds(10, 10, 639, 413);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(115, 0, 524, 413);
		panel_1.setBackground(new Color(0, 153, 204));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		user = new JTextField();
		user.setBounds(179, 163, 197, 34);
		panel_1.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(179, 245, 197, 34);
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ResultSet rs;
					try {
						rs = checkLogin();
						if(rs.next()) {
							// check validation account
							if (rs.getObject("user_validation").equals("Y")) {
								JOptionPane.showMessageDialog(null, "Login Success!!");
								closeLogin();

								//Create account for constant role
								if (rs.getObject("user_role").equals("user")) {
									PatientGUI screen;
									try {
										screen = new PatientGUI(user.getText());
								        screen.setVisible(true);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (ParseException parseException) {
										parseException.printStackTrace();
									}
								}
								if (rs.getObject("user_role").equals("admin")) {
									
									AdminFrame adminFrame = new AdminFrame();
									adminFrame.setVisible(true);
								}
								if (rs.getObject("user_role").equals("supevisor")) {
									Manager_mainframe mf = new Manager_mainframe(user.getText());
					                mf.setVisible(true);
								}
							}
							// invalidation account
							else {
								JOptionPane.showMessageDialog(null, "Your Account has been BAN !!!");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Invalid Username or Password!!");
							user.setText("");
							pass.setText("");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel_1.add(pass);
		
		lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(179, 221, 68, 24);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(179, 126, 80, 42);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(102, 204, 153));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_1);
		
		btnLogin = new JButton("Enter");
		btnLogin.setBounds(203, 306, 155, 34);
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs;
				try {
					rs = checkLogin();
					if(rs.next()) {
						if (rs.getObject("user_validation").equals("Y")) {
							JOptionPane.showMessageDialog(null, "Login Success!!");
							closeLogin();
							//Create account for constant role
							if (rs.getObject("user_role").equals("user")) {
								PatientGUI screen;
								try {
									screen = new PatientGUI(user.getText());
							        screen.setVisible(true);
								} catch (IOException | ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								//thisAccount = new UserAccount(rs.getString("username"), rs.getString("user_password"));
							}
							if (rs.getObject("user_role").equals("admin")) {
								
								AdminFrame adminFrame = new AdminFrame();
								adminFrame.setVisible(true);
							}
							if (rs.getObject("user_role").equals("supevisor")) {
								Manager_mainframe mf = new Manager_mainframe(user.getText());
				                mf.setVisible(true);
							}
							
						}
						// invalidation account
						else {
							JOptionPane.showMessageDialog(null, "Your Account has been BAN !!!");
						}
					}
					else {
						//Login_Log.info( id + ": Login Fail");
						JOptionPane.showMessageDialog(null, "Invalid Username or Password!!");
						user.setText("");
						pass.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(btnLogin);
		
		lblNewLabel_2 = new JLabel(" ");
		lblNewLabel_2.setBounds(10, 102, 504, 301);
		lblNewLabel_2.setForeground(Color.WHITE);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/2.jpg"));
		lblNewLabel_2.setIcon(icon);
		panel_1.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("COVID MANAGEMENT");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 10, 504, 91);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setBackground(new Color(102, 204, 255));
		lblNewLabel_3.setOpaque(true);
	}
	
	public ResultSet checkLogin() {
		String usr = user.getText();
		String pas = Hashing.getHash(pass.getText());
		UtilsString dtb = new UtilsString();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String urlString = dtb.dbURL+"; databaseName="+dtb.DATABASENAME;
			System.out.println(urlString);
			Connection conn = DriverManager.getConnection(urlString, dtb.username, dtb.password);
			Statement st = conn.createStatement();
			return st.executeQuery("SELECT * FROM ql_user WHERE username= '"+usr+"'AND user_password='"+pas+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
