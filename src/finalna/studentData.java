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
public class studentData {

    private Integer id;
    private String idNo;
    private String firstName;
    private String middleName;
    private String lastName;
    private String course;
    private String section;
    private String year;
    private String contact;
    private String email;
    private Date date;

    public studentData(int id, String idNo, String firstName, String middleName,String lastName, String course, String section, String year, String contact, String email, Date date) {
            
        this.id = id;
        this.idNo = idNo;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.course = course;
        this.section = section;
        this.year = year;
        this.contact = contact;
        this.email = email;
        this.date = date;
    }
    
public Integer getId(){
    return id;
}
  public String getIdNo(){
      return idNo;
  }
  public String getFirstName(){
      return firstName;
  }
  public String getMiddleName(){
      return middleName;
  }
  public String getLastName(){
      return lastName;
  }
  public String getCourse(){
      return course;
  }
  public String getSection(){
      return section;
  }
  public String getYear(){
      return year;
  }
  public String getContact(){
      return contact;
  }
  public String getEmail(){
      return email;
  }
  public Date getDate(){
      return date;
  }
}
