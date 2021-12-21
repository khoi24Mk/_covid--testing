package com.example.qlcovid.model.User;
import com.example.qlcovid.string.DatabaseConnection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CovidPatient extends User {



    // attributes
    private String _citizen_id;
    private String _name;
    private LocalDate _dob;
    private Address _address;
    private TreatmentArea _treatmentAreaID;
    private String _status;
    private String _patientRelavent;

    // constructor
    public CovidPatient(){
        super(Role.USER);
    }

    public CovidPatient(String citizen_id,String _name,
                        String _dob,
                        Address _address,
                        TreatmentArea _treatmentAreaID,
                        String _status,
                        String _patientRelavent) {
        super(Role.USER);
        this._citizen_id = citizen_id;
        this._name = _name;
        set_dob(_dob);
        this._address = _address;
        this._treatmentAreaID = _treatmentAreaID;
        this._status = _status;
        this._patientRelavent = _patientRelavent;
    }

    // method

    // setter and getter

    public String get_citizen_id() {
        return _citizen_id;
    }

    public void set_citizen_id(String _citizen_id) {
        this._citizen_id = _citizen_id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_dob() {
        if(_dob == null){
            return "";
        }
        return this._dob.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void set_dob(String _dob) {
        if(_dob == null || _dob.trim().isEmpty()){
            this._dob = null;
        }
        this._dob = LocalDate.parse(_dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String get_address() {
        if(_address == null){
            return "";
        }
        return _address.getInfo();
    }

    public void set_address(String _address) throws SQLException {
        this._address = new Address(_address);
    }

    public String get_treatmentArea() {
        if(_treatmentAreaID == null){
            return "";
        }
        return this._treatmentAreaID.get_name();
    }

    public void set_treatmentArea(String _ID) throws SQLException {

        this._treatmentAreaID = new TreatmentArea(_ID);
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {

        this._status = _status;
    }

    public String get_patientRelavent(String username) throws SQLException {
        // WILL BE CONFLICT///////////////////////////
        if(this._patientRelavent == null) {
            return "Empty";
        }

        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT relavent.full_name,relavent.date_of_birth,relavent.citizen_address, relavent.condition, treatment.treatment_place_name FROM covid_patient as patient\n" +
                "JOIN covid_patient as relavent\n" +
                "ON patient.related_to = relavent.citizen_id\n" +
                "JOIN treatment_place as treatment\n" +
                "ON relavent.treatment_place_id = treatment.treatment_place_id\n" +
                "WHERE patient.citizen_id = "+username+";";
        ResultSet rs = statement.executeQuery(sql);
        CovidPatient temp = new CovidPatient();
        String treatmentName = "";
        while(rs.next()){


            temp.set_name(rs.getString("full_name"));
            temp.set_dob(rs.getString("date_of_birth"));
            temp.set_address(rs.getString("citizen_address"));
            temp.set_status(rs.getString("condition"));
            treatmentName = rs.getString("treatment_place_name");

        }


        return "<table >\n" +
                "    <tbody>\n" +
                "      <tr >\n" +
                "        <td border-bottom: 1px solid black>Name: " +temp.get_name()+"</td>\n" +
                "        <td>     DOB: "+temp.get_dob()+"</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td>Address: "+temp.get_address()+"</td>\n"+
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td>Condition: "+temp.get_status()+"</td>\n" +
                "      </tr>\n" +
                "      <tr  >\n" +
                "        <td>Treatment Place: "+treatmentName+"</td>\n" +
                "      </tr>\n" +

                "    </tbody>\n" +
                "  </table>";
        //1px 0xC2FFF9


    }

    public void set_patientRelavent(String _patientRelavent) {
        this._patientRelavent = _patientRelavent;
    }

    //--

    public String getInfo() {

        return this.get_citizen_id()+" "+
                this.get_name()+" "+
                this.get_dob() +" "+
                this.get_address()+" "+
                this.get_status();
    }

}
