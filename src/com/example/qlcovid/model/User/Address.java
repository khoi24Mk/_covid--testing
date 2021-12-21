package com.example.qlcovid.model.User;

import com.example.qlcovid.string.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Address {
    // attributes
    private String _ward;
    private String _district;
    private String _city;

    // constructor
    public Address(String address) throws SQLException {

//        Statement statement = DatabaseConnection.getJDBC().createStatement();
//        String sql = "SELECT W.ward_name,D.district_name,P.province_name FROM WARD AS W\n" +
//                "JOIN DISTRICT_HAS_WARD AS HAS_W\n" +
//                "ON W.ward_id = HAS_W.ward_id\n" +
//                "JOIN DISTRICT AS D\n" +
//                "ON D.district_id = HAS_W.district_id\n" +
//                "JOIN province_has_district AS HAS_D\n" +
//                "ON HAS_D.district_id = D.district_id\n" +
//                "JOIN province AS P\n" +
//                "ON P.province_id = HAS_D.province_id\n" +
//                "WHERE  W.ward_id = "+address+";";
//        System.out.println(sql);
//        ResultSet rs = statement.executeQuery(sql);
//
//
//        while(rs.next()){
//            this.set_ward(rs.getString("W.ward_name"));
//            this.set_district(rs.getString("D.district_name"));
//            this.set_city(rs.getString("P.province_name"));
//        }
    	String[] parts = address.split(",");
    	 this.set_ward(parts[0]);
    	 this.set_district(parts[1]);
       	 this.set_city(parts[2]+", "+parts[3]);


    }


    public Address(){

    }


    // getter and setter

    public String get_ward() {
        return _ward;
    }

    public void set_ward(String _ward) {
        this._ward = _ward;
    }

    public String get_district() {
        return _district;
    }

    public void set_district(String _district) {
        this._district = _district;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }





    //methods
    public String getInfo() {
        return this.get_ward()+", "+ this.get_district()+", "+this.get_city();
    }



}
