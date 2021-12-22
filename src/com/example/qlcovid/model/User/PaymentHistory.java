package com.example.qlcovid.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PaymentHistory {
    private String _ID;
    private String _customer_ID;
    private Date _date;
    private long _price;

    public PaymentHistory() {

    }

    public PaymentHistory(String _ID, String _customer_ID, String _date, long _price) throws ParseException {
        this._ID = _ID;
        this._customer_ID = _customer_ID;
        set_date(_date);
        this._price = _price;
    }

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

    public String get_date() {
        if(_date == null){
            return "";
        }
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(_date);
    }

    public void set_date(String _date) throws ParseException {
        if(_date == null || _date.trim().isEmpty()){
            this._date = null;
        }
        this._date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(_date);
    }//LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))

    public double get_price() {
        return _price;
    }

    public void set_price(long _price) {
        this._price = _price;
    }

    public String getInfo() {
        return this.get_ID() +", "+ this.get_customer_ID()+", " +this.get_date() +", "+
                this.get_price();

    }

    public Object[] getObject() {
        return new Object[]{
                this.get_ID(),
                this.get_customer_ID(),
                this.get_date(),
                this.get_price()
        };
    }

}

















