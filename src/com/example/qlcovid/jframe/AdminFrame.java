package com.example.qlcovid.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.example.qlcovid.string.*;
import javax.swing.SwingConstants;
public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel AddPanel, AccountPanel, PlacePanel;
	private JButton addNewButton, BlockButton, AddPlace, DeletePlace, UpdatePlace, RefreshButton;
	private JLabel UserLabel, PassLabel, ConPassLabel, BlockLabel, NameLabel, IdLabel, CapacityLabel, CurrentLabel;
	private JTextField UserText, PassText, BlockText;
	private JTextField SearchText;
	private JTextField NameText, IdText, CapText, HoldText;
	private JTable TablePlace, TableAccount;
	private JScrollPane scrollPane, scrollPane1;
	private DefaultTableModel model, model1;
	private AdminString dtb;
	private JTextField ConPassText;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminFrame frame = new AdminFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dtb=new AdminString();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 767, 454);
		contentPane.add(tabbedPane);
		
		AddPanel = new JPanel();
		tabbedPane.addTab("CREATE MANAGER ACCOUNT", null, AddPanel, null);
		AddPanel.setLayout(null);
		
		UserLabel = new JLabel("USERNAME");
		UserLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		UserLabel.setBounds(82, 53, 100, 56);
		AddPanel.add(UserLabel);
		
		PassLabel = new JLabel("PASSWORD");
		PassLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		PassLabel.setBounds(82, 145, 100, 56);
		AddPanel.add(PassLabel);
		
		UserText = new JTextField();
		UserText.setBounds(289, 66, 225, 35);
		AddPanel.add(UserText);
		UserText.setColumns(10);
		
		PassText = new JTextField();
		PassText.setColumns(10);
		PassText.setBounds(289, 158, 225, 35);
		AddPanel.add(PassText);
		
		addNewButton = new JButton("ADD");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(dtb.checkAccountExist(UserText.getText())==1)
						JOptionPane.showMessageDialog(null, "Username Exist");
					else {
						if(UserText.getText()=="" || PassText.getText()=="" || ConPassText.getText()=="")
							JOptionPane.showMessageDialog(null, "Please fill in all information");
						if(!PassText.getText().equals(ConPassText.getText())) {
							JOptionPane.showMessageDialog(null, "Password not match");
							PassText.setText(""); ConPassText.setText("");
						}
						else {
							dtb.addManagerAccount(UserText.getText(), PassText.getText());
							JOptionPane.showMessageDialog(null, "Add Success");
						}
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addNewButton.setBounds(339, 350, 145, 43);
		AddPanel.add(addNewButton);
		
		ConPassLabel = new JLabel("CONFIRM PASSWORD");
		ConPassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ConPassLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ConPassLabel.setBounds(72, 244, 170, 56);
		AddPanel.add(ConPassLabel);
		
		ConPassText = new JTextField();
		ConPassText.setColumns(10);
		ConPassText.setBounds(289, 257, 225, 35);
		AddPanel.add(ConPassText);
		
		AccountPanel = new JPanel();
		tabbedPane.addTab("MANAGE ACCOUNT", null, AccountPanel, null);
		AccountPanel.setLayout(null);
		
		BlockLabel = new JLabel("Enter ID to block");
		BlockLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		BlockLabel.setBounds(59, 22, 133, 29);
		AccountPanel.add(BlockLabel);
		
		BlockText = new JTextField();
		BlockText.setBounds(228, 19, 215, 39);
		AccountPanel.add(BlockText);
		BlockText.setColumns(10);
		
		BlockButton = new JButton("OK");
		BlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(dtb.checkManagerAccountExist(BlockText.getText())==0)
						JOptionPane.showMessageDialog(null, "Username doesn't exist");
					else {
						dtb.banManagerAccount(BlockText.getText());;
						JOptionPane.showMessageDialog(null, "Ban Success");
						UserText.setText("");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		BlockButton.setBounds(505, 23, 125, 30);
		AccountPanel.add(BlockButton);
		
		String[] column1 = {"Edit ID","Supevisor ID", "Action","Date"};
		model1=new DefaultTableModel();
		model1.setColumnIdentifiers(column1);
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(21, 169, 717, 248);
		AccountPanel.add(scrollPane1);
		TableAccount = new JTable();
		TableAccount.setModel(model1);
		scrollPane1.setViewportView(TableAccount);
		

		JLabel SearchLabel = new JLabel("Enter ID to check");
		SearchLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		SearchLabel.setBounds(59, 110, 133, 29);
		AccountPanel.add(SearchLabel);
		
		SearchText = new JTextField();
		SearchText.setColumns(10);
		SearchText.setBounds(228, 100, 215, 39);
		AccountPanel.add(SearchText);
		
		JButton SearchButton = new JButton("SEARCH");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = dtb.searchManagerHistory(SearchText.getText());
					model1.setRowCount(0);
					while(rs.next()) {
						int eid = rs.getInt("edit_history_id");
						String sid = rs.getString("supevisor_id");
						String act = rs.getString("supevisor_action");
						Date dt = rs.getDate("supevisor_date");
						Object editData[] = {eid,sid,act,dt};
						model1.addRow(editData);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		SearchButton.setBounds(505, 109, 125, 30);
		AccountPanel.add(SearchButton);
		
		PlacePanel = new JPanel();
		tabbedPane.addTab("MANAGE TREATMENT PLACE", null, PlacePanel, null);
		PlacePanel.setLayout(null);
		
		NameLabel = new JLabel("NAME");
		NameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		NameLabel.setBounds(25, 55, 93, 27);
		PlacePanel.add(NameLabel);
		
		IdLabel = new JLabel("ID");
		IdLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		IdLabel.setBounds(25, 102, 93, 27);
		PlacePanel.add(IdLabel);
		
		CapacityLabel = new JLabel("CAPACITY");
		CapacityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		CapacityLabel.setBounds(25, 149, 93, 27);
		PlacePanel.add(CapacityLabel);
		
		CurrentLabel = new JLabel("CURRENT HOLD");
		CurrentLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		CurrentLabel.setBounds(25, 204, 115, 27);
		PlacePanel.add(CurrentLabel);
		
		NameText = new JTextField();
		NameText.setBounds(169, 55, 140, 25);
		PlacePanel.add(NameText);
		NameText.setColumns(10);
		
		IdText = new JTextField();
		IdText.setColumns(10);
		IdText.setBounds(169, 108, 140, 25);
		PlacePanel.add(IdText);
		
		CapText = new JTextField();
		CapText.setColumns(10);
		CapText.setBounds(169, 155, 140, 25);
		PlacePanel.add(CapText);
		
		HoldText = new JTextField();
		HoldText.setColumns(10);
		HoldText.setBounds(169, 210, 140, 25);
		PlacePanel.add(HoldText);
		
		String[] column = {"Treatment Place Name","Treatment Place ID", "Capacity","Current holding"};
		model=new DefaultTableModel();
		model.setColumnIdentifiers(column);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 38, 389, 339);
		PlacePanel.add(scrollPane);
		TablePlace = new JTable();
		TablePlace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NameText.setText(""); IdText.setText(""); CapText.setText(""); HoldText.setText("");
				int selectedRowIndex = TablePlace.getSelectedRow();
				NameText.setText(model.getValueAt(selectedRowIndex, 0).toString());
				IdText.setText(model.getValueAt(selectedRowIndex, 1).toString());
				CapText.setText(model.getValueAt(selectedRowIndex, 2).toString());
				HoldText.setText(model.getValueAt(selectedRowIndex, 3).toString());
			}
		});
		TablePlace.setModel(model);
		scrollPane.setViewportView(TablePlace);
		
		AddPlace = new JButton("ADD");
		AddPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(dtb.checkTreatmentPlaceExist(IdText.getText())==1)
						JOptionPane.showMessageDialog(null, "ID is already existed");
					else {
						if(checkEmpty())
							JOptionPane.showMessageDialog(null, "Please fill in all blank");
						else {
							dtb.addTreatmentPlace(NameText.getText(), IdText.getText(), Integer.parseInt(CapText.getText()), Integer.parseInt(HoldText.getText()));
							JOptionPane.showMessageDialog(null, "Add complete");
							refreshTable(model);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error in input data, please check again");
					e1.printStackTrace();
				}
				catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Capacity and Current Holding must be an INTEGER NUMBER");
					e1.printStackTrace();
				}
			}
		});
		AddPlace.setBounds(10, 269, 85, 21);
		PlacePanel.add(AddPlace);
		
		DeletePlace = new JButton("DELETE");
		DeletePlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dtb.deleteTreatmentPlace(NameText.getText(), IdText.getText(), Integer.parseInt(CapText.getText()), Integer.parseInt(HoldText.getText()));
					JOptionPane.showMessageDialog(null, "Delete Success");
					refreshTable(model);
				} catch (NumberFormatException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "Please choose the Delete line again");
				}
			}
		});
		DeletePlace.setBounds(114, 269, 85, 21);
		PlacePanel.add(DeletePlace);
		
		UpdatePlace = new JButton("UPDATE");
		UpdatePlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(dtb.checkTreatmentPlaceExist(IdText.getText())==0)
						JOptionPane.showMessageDialog(null, "Can't find this Treatment Place. Please choose again");
					else {
						if(checkEmpty()) {
							JOptionPane.showMessageDialog(null, "Please fill in all blank");
						}
						else {
							dtb.updateTreatmentPlace(NameText.getText(), IdText.getText(), Integer.parseInt(CapText.getText()), Integer.parseInt(HoldText.getText()));
							JOptionPane.showMessageDialog(null, "Update complete");
							refreshTable(model);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error in input data, please check again");
					e1.printStackTrace();
				}
				catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Capacity and Current Holding must be an INTEGER NUMBER");
					e1.printStackTrace();
				}
			}
		});
		UpdatePlace.setBounds(213, 269, 85, 21);
		PlacePanel.add(UpdatePlace);
		
		RefreshButton = new JButton("REFRESH");
		RefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable(model);
			}
		});
		RefreshButton.setBounds(114, 334, 93, 21);
		PlacePanel.add(RefreshButton);
	}
	
	
	// Refresh table
	void refreshTable( DefaultTableModel model) {
		ResultSet rs;
		try {
			rs = dtb.searchTreatmentPlace();
			model.setRowCount(0);
			NameText.setText(""); IdText.setText(""); CapText.setText(""); HoldText.setText("");
			while(rs.next()) {
				String pname = rs.getString("treatment_place_name");
				String pid = rs.getString("treatment_place_id");
				int cap = rs.getInt("capacity");
				int chold = rs.getInt("current_holding");
				Object editData[] = {pname, pid, cap, chold};
				model.addRow(editData);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Can't search Treatment Place");
		}
	}
	
	//Check empty box
	boolean checkEmpty() {
		if(UserText.getText()==""||IdText.getText()==""||CapText.getText()==""||HoldText.getText()=="")
			return true;
		return false;
	}
}
