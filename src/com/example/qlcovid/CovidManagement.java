package com.example.qlcovid;

import com.example.qlcovid.jframe.LoginFrame;
import com.example.qlcovid.jframe.Manager_mainframe;
import com.example.qlcovid.model.Hashing;
import com.example.qlcovid.model.ManagerDB;
import java.sql.SQLException;


public class CovidManagement {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		LoginFrame window = new LoginFrame();
		window.frame.setVisible(true);
		System.out.println(Hashing.getHash("12345678"));
		
//		Manager_mainframe mf = new Manager_mainframe("012250012");
//        mf.setVisible(true);
	}
}
