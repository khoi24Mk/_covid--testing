package com.example.qlcovid.model.User;

import com.example.qlcovid.string.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TreatmentArea {

    // attributes
    private String _id;
    private String _name;
    private int _capacity;
    private int _currentHolding;

    // constructor
    public TreatmentArea(String _ID) throws SQLException {
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM treatment_place\n"+
                "WHERE treatment_place_id = "+_ID+";";

        ResultSet res = statement.executeQuery(sql);

        while (res.next()) {

            this.set_name(res.getString("treatment_place_name"));
            this.set_id(_ID);
            this.set_capacity(res.getInt("capacity"));
            this.set_currentHoldingHolding(res.getInt("current_holding"));
        }
    }



    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_capacity() {
        return _capacity;
    }

    public void set_capacity(int _capacity) {
        this._capacity = _capacity;
    }

    public int get_currentHoldingHolding() {
        return _currentHolding;
    }

    public void set_currentHoldingHolding(int _available) {
        this._currentHolding = _available;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
