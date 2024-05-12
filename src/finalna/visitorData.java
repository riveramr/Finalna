/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalna;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class visitorData {
    private Integer id;
    private String idNo;
    private String firstName;
    private String middleName;
    private String lastName;
    private String contact;
    private String email;
    private String address;
    private String purposeofVisit;
    private Date date;
    
    public visitorData(int id,String idNo, String firstName, String middleName, String lastName, String contact, String email, String address, String purposeofVisit, Date date){
        this.id = id;
        this.idNo = idNo;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.purposeofVisit = purposeofVisit;
        this.date = date;
        
    }
    public Integer getID(){
     return id;   
    }
    public String getIdNo(){
        return idNo;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getMiddleName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getContact(){
        return contact;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public String getPurposeofVisit(){
        return purposeofVisit;
    }
    public Date getDate(){
        return date;
    }
}
