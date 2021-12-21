package com.example.qlcovid.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientHistory {

    // attributes
    private String _treatment_ID;
    private String _patient_ID;
    private String _patientAction;
    private LocalDateTime _startDate;
    // constructor
    public PatientHistory() {
    }

    public PatientHistory(String _treatment_ID, String _patient_ID, String _patientAction, String _startDate) {
        this._treatment_ID = _treatment_ID;
        this._patient_ID = _patient_ID;
        this._patientAction = _patientAction;
        set_startDate(_startDate);
    }

    public String get_treatment_ID() {
        return _treatment_ID;
    }

    public void set_treatment_ID(String _treatment_ID) {
        this._treatment_ID = _treatment_ID;
    }

    public String get_patient_ID() {
        return _patient_ID;
    }

    public void set_patient_ID(String _patient_ID) {
        this._patient_ID = _patient_ID;
    }

    public String get_patientAction() {
        return _patientAction;
    }

    public void set_patientAction(String _patientAction) {
        this._patientAction = _patientAction;
    }

    public String get_startDate() {
        if(_startDate==null){
            return "";
        }

        return _startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.s"));
    }

    public void set_startDate(String _startDate) {
        this._startDate = LocalDateTime.parse(_startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

    }

    public String getInfo() {
        return this.get_treatment_ID() +", "+ this.get_patient_ID()+", " +this.get_patientAction() +", "+
                this.get_startDate();

    }

    public Object[] getObject() {
        return new Object[]{
                this.get_treatment_ID(),
                this.get_patient_ID(),
                this.get_patientAction(),
                this.get_startDate()
        };
    }
}
