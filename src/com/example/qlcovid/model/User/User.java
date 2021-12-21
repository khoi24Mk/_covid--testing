package com.example.qlcovid.model.User;

public abstract class User {
    // attributes
    private String _username;
    private String _password;
    private Role _role;
    private Boolean _validation;

    // constructor
    protected User(){

    }


    protected User(Role user) {
    }

    // method
    protected void changePassword(){

    }
}
