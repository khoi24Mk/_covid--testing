package com.example.qlcovid.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PackageClass {
    // attribute
    private String _ID;
    private String _name;
    private int _limit;
    private LocalDate _startDate;
    private LocalDate _endDate;
    private int _price;

    // constructor
    public PackageClass(){

    }

    public PackageClass(String _ID, String _name, int _limit, String _startDate, String _endDate, int _price) {
        this.set_ID(_ID);
        this.set_name(_name);
        this.set_limit(_limit);
        this.set_startDate(_startDate);
        this.set_endDate(_endDate);
        this.set_price(_price);
    }


    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_limit() {
        return _limit;
    }

    public void set_limit(int _limit) {
        this._limit = _limit;
    }

    public LocalDate get_startDate() {
        return _startDate;
    }

    public void set_startDate(String _startDate) {
        this._startDate = LocalDate.parse(_startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDate get_endDate() {
        return _endDate;
    }

    public void set_endDate(String _endDate) {
        this._endDate = LocalDate.parse(_endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }


    public String getInfo(){
        return this.get_ID()+", " + this.get_name()+", " + String.valueOf(this.get_limit()) +", "
                + this.get_startDate()+", " + this.get_endDate()+", "
                + String.valueOf(this.get_price());
    }

    public Object[] getObjects() {
        return new Object[]{
                this.get_ID(),
                this.get_name(),
                this.get_limit(),
                this.get_startDate(),
                this.get_endDate(),
                this.get_price()
        };
    }
}





























