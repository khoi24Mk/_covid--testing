package com.example.qlcovid.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PackagePurchase {

    // attribute
    private String _ID;
    private String _customer_ID;
    private String _package_ID;
    private LocalDate _date;

    // constructor
    public PackagePurchase(String _ID, String _customer_ID, String _package_ID, String _date) {
        this._ID = _ID;
        this._customer_ID = _customer_ID;
        this._package_ID = _package_ID;
        this.set_date(_date);
    }

    // getter and setter
    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_customer_ID() {
        return _customer_ID;
    }

    public void set_customer_ID(String _customer_ID) {
        this._customer_ID = _customer_ID;
    }

    public String get_package_ID() {
        return _package_ID;
    }

    public void set_package_ID(String _package_ID) {
        this._package_ID = _package_ID;
    }

    public LocalDate get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = LocalDate.parse(_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // method
    public String getInfo(){
        return this.get_ID()+" "+
                this.get_customer_ID()+" "+
                this.get_package_ID()+" "+
                this.get_date();
    }

    public Object[] getObject() {
        return new Object[]{
                this.get_ID(),
                this.get_customer_ID(),
                this.get_package_ID(),
                this.get_date()
        };
    }
}
