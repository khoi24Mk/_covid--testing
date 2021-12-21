package com.example.qlcovid.jframe.User;

import com.example.qlcovid.jframe.User.Info.BalanceUI;
import com.example.qlcovid.jframe.User.Package.PackageInfoUI;
import com.example.qlcovid.model.User.PackageClass;
import com.example.qlcovid.model.User.PaymentHistory;
import com.example.qlcovid.string.DatabaseConnection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class PtablePackage  extends JPanel{

    ArrayList<PackageClass> listPackage;
    JTable packageTalbe;
    DefaultTableModel modelListPackage;

    public PtablePackage()  throws SQLException{
        // getting data from database
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM package\n"+";";
        ResultSet rs = statement.executeQuery(sql);

        listPackage = new ArrayList<PackageClass>();
        while(rs.next()){

            listPackage.add(new PackageClass(
                    rs.getString("package_id"),
                    rs.getString("name"),
                    rs.getInt("limit"),
                    rs.getString("package_start"),
                    rs.getString("package_end"),
                    rs.getInt("price")));
        }

        for(PackageClass x : listPackage){
            System.out.println(x.getInfo());
        }


        this.setOpaque(true);
        this.setBounds(0,0,400,100);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 250));

        modelListPackage = new DefaultTableModel(
                new String[] { "ID","Name","Limit","Start Date", "End Date", "Price"},
                0
        );
        for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }
        /*for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }
        for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }*/

        packageTalbe = new JTable(modelListPackage);//modelListPackage
        //packageTalbe.setBounds(10,10,400,200);
        //packageTalbe.setPreferredScrollableViewportSize(new Dimension(300, 10));

        packageTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane = new JScrollPane(packageTalbe);
        this.add(pane, BorderLayout.CENTER);
        packageTalbe.setDefaultEditor(Object.class, null);
        packageTalbe.getTableHeader().setReorderingAllowed(false);



        packageTalbe.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                PackageInfoUI.turnOnButton();

                for(PackageClass x: listPackage){
                    System.out.println("selected: "+packageTalbe.convertRowIndexToModel(packageTalbe.getSelectedRow()));
                    if(x.get_ID().equals(packageTalbe.getValueAt(packageTalbe.getSelectedRow(), 0).toString())){
                        PackageInfoUI.selectRowData(x);
                    }

                }
                System.out.println(packageTalbe.getValueAt(packageTalbe.getSelectedRow(), 0).toString());

            }
        });



    }

    public void refreshTalbe() throws SQLException {
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM package\n"+";";
        ResultSet rs = statement.executeQuery(sql);

        listPackage.clear();
        while(rs.next()){

            listPackage.add(new PackageClass(
                    rs.getString("package_id"),
                    rs.getString("name"),
                    rs.getInt("limit"),
                    rs.getString("package_start"),
                    rs.getString("package_end"),
                    rs.getInt("price")));
        }

        modelListPackage = new DefaultTableModel(
                new String[] { "ID","Name","Limit","Start Date", "End Date", "Price"},
                0
        );
        for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }

        packageTalbe.setModel(modelListPackage);
    }

    public String getSelectedPackageID(){
        String id = packageTalbe.getValueAt(packageTalbe.getSelectedRow(), 0).toString();
        for(PackageClass x: listPackage){
            if(x.get_ID().equals(packageTalbe.getValueAt(packageTalbe.getSelectedRow(), 0).toString())){
                return x.get_ID();
            }
        }
        return null;
    }

    public void sortTable(String ID, String name, String limit, String start, String end, String price){
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(packageTalbe.getModel());
        packageTalbe.setRowSorter(sorter);

        List<RowSorter.SortKey> sortList = new ArrayList<>(6);
        if(ID.equals("\uD83D\uDD3A")){
            sortList.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        }
        else if(ID.equals("\uD83D\uDD3B")){
            sortList.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        }

        if(name.equals("\uD83D\uDD3A")){
            sortList.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        }
        else if(name.equals("\uD83D\uDD3B")){
            sortList.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        }

        if(limit.equals("\uD83D\uDD3A")){
            sortList.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        }
        else if(limit.equals("\uD83D\uDD3B")){
            sortList.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
        }

        if(start.equals("\uD83D\uDD3A")){
            sortList.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
        }
        else if(start.equals("\uD83D\uDD3B")){
            sortList.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
        }

        if(end.equals("\uD83D\uDD3A")){
            sortList.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        }
        else if(end.equals("\uD83D\uDD3B")){
            sortList.add(new RowSorter.SortKey(4, SortOrder.DESCENDING));
        }

        if(price.equals("\uD83D\uDD3A")){
            sortList.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
        }
        else if(price.equals("\uD83D\uDD3B")){
            sortList.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
        }

        sorter.setSortKeys(sortList);
    }

    public void searchingTable(String ID, String name, String limit, String start, String end, String price) throws SQLException {

        boolean flag = false;
        System.out.println(ID+name+limit);
        String str_condition="";
        if(!ID.equals("ID") && ID.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            str_condition += " "+"package_id" +" = "+"'"+ID+"'";
        }

        if(!name.equals("Name") && name.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            str_condition += "name" +" = "+"'"+name+"'";
        }

        if(!limit.equals("Limit") && limit.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            str_condition +="limit" +" = "+limit;
        }

        if(!start.equals("yyyy-mm-dd") && start.length()!=0){
            try {
                LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            catch(DateTimeParseException e){
                JOptionPane.showMessageDialog(null,"Your start invalid\n form of date: yyyy-mm-dd"
                        ,"message",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            str_condition += "package_start" +" = "+"'"+start+"'";
        }

        if(!end.equals("yyyy-mm-dd") && end.length()!=0){
            try {
                LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            catch(DateTimeParseException e){
                JOptionPane.showMessageDialog(null,"Your end invalid\n form of date: yyyy-mm-dd"
                        ,"message",JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            str_condition += "(package_end)" +" = "+"'"+end+"'";
        }

        if(!price.equals("Price") && price.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            str_condition += "price" +" = "+"'"+price+"'";
        }
        if(str_condition.length()==0){
            return;
        }
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM package\n";
        sql += "WHERE "+str_condition+";";
        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);

        listPackage = new ArrayList<PackageClass>();
        while(rs.next()){

            listPackage.add(new PackageClass(
                    rs.getString("package_id"),
                    rs.getString("name"),
                    rs.getInt("limit"),
                    rs.getString("package_start"),
                    rs.getString("package_end"),
                    rs.getInt("price")));
        }

        modelListPackage.setRowCount(0);
        /*modelListPackage = new DefaultTableModel(
                new String[] { "ID","Name","Limit","Start Date", "End Date", "Price"},
                0
        );*/

        for(PackageClass x : listPackage){
            System.out.println(x.getInfo());
        }

        for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }
        /*for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }
        for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }*/
        packageTalbe.setModel(modelListPackage);
    }



    public void filterTable(String ID, String name, String limit, String start, String end, String price
    , String flagLimit, String flagStart, String flagEnd, String flagPrice) throws SQLException {

        boolean flag = false;
        System.out.println(ID+name+limit);
        String str_condition="";
        if(ID.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            str_condition += " "+"package_id" +" LIKE "+"'%"+ID+"%'";
        }

        if(name.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            str_condition += "name" +" LIKE "+"'%"+name+"%'";
        }

        if( limit.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            if(flagLimit.equals(">")){
                str_condition += "_limit " +" > "+limit;
            }
            else if(flagLimit.equals("<=")){
                str_condition += "_limit " +" <= "+limit;
            }

        }

        if(!start.equals("yyyy-mm-dd") && start.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }

            if(flagStart.equals(">")){
                str_condition += "package_start" +" > "+"'"+start+"'";
            }
            else if(flagStart.equals("<=")){
                str_condition += "package_start" +" <= "+"'"+start+"'";
            }

        }

        if(!end.equals("yyyy-mm-dd") && end.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }

            if(flagEnd.equals(">")){
                str_condition += "package_end" +" > "+"'"+end+"'";
            }
            else if(flagEnd.equals("<=")){
                str_condition += "package_end" +" <= "+"'"+end+"'";
            }

        }

        if(price.length()!=0){
            if(flag){
                str_condition+=" AND ";
            }
            else{
                flag = true;
            }
            if(flagPrice.equals(">")){
                str_condition += "price" +" > "+"'"+price+"'";
            }
            else if(flagPrice.equals("<=")){
                str_condition += "price" +" >= "+"'"+price+"'";
            }

        }
        if(str_condition.length()==0){
            return;
        }
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM package\n";
        sql += "WHERE "+str_condition+";";
        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);

        listPackage = new ArrayList<PackageClass>();
        while(rs.next()){

            listPackage.add(new PackageClass(
                    rs.getString("package_id"),
                    rs.getString("name"),
                    rs.getInt("limit"),
                    rs.getString("package_start"),
                    rs.getString("package_end"),
                    rs.getInt("price")));
        }

        modelListPackage.setRowCount(0);
        /*modelListPackage = new DefaultTableModel(
                new String[] { "ID","Name","Limit","Start Date", "End Date", "Price"},
                0
        );*/

        for(PackageClass x : listPackage){
            System.out.println(x.getInfo());
        }

        for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }
        /*for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }
        for(PackageClass x : listPackage){
            modelListPackage.addRow(x.getObjects());
        }*/
        packageTalbe.setModel(modelListPackage);
    }
}
