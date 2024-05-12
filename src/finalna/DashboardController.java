/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package finalna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class DashboardController implements Initializable {


    @FXML
    private Button clear_visit;

    @FXML
    private Button save_visit;

    @FXML
    private Button student_clearBtn;

    @FXML
    private TableColumn<?, ?> student_col_contact;

    @FXML
    private TableColumn<?, ?> student_col_course;

    @FXML
    private TableColumn<?, ?> student_col_date;

    @FXML
    private TableColumn<?, ?> student_col_email;

    @FXML
    private TableColumn<?, ?> student_col_firstName;

    @FXML
    private TableColumn<?, ?> student_col_idNO;

    @FXML
    private TableColumn<?, ?> student_col_lastName;

    @FXML
    private TableColumn<?, ?> student_col_middleName;

    @FXML
    private TableColumn<?, ?> student_col_section;

    @FXML
    private TableColumn<?, ?> student_col_year;

    @FXML
    private TextField student_contact;

    @FXML
    private ComboBox<?> student_course;

    @FXML
    private TextField student_email;

    @FXML
    private TextField student_firstName;

    @FXML
    private AnchorPane student_form;

    @FXML
    private TextField student_idNo;

    @FXML
    private TextField student_lastName;

    @FXML
    private TextField student_middleName;

    @FXML
    private Button student_saveBtn;

    @FXML
    private ComboBox<?> student_section;

    @FXML
    private TableView<?> student_tableView;

    @FXML
    private ComboBox<?> student_year;

    @FXML
    private AnchorPane visitorForm;

    @FXML
    private TextField visitor_IdNo;

    @FXML
    private TextField student_address;

    @FXML
    private TableColumn<?, ?> visitor_col_IdNo;

    @FXML
    private TableColumn<?, ?> visitor_col_address;

    @FXML
    private TableColumn<?, ?> visitor_col_contact;

    @FXML
    private TableColumn<?, ?> visitor_col_date;

    @FXML
    private TableColumn<?, ?> visitor_col_email;

    @FXML
    private TableColumn<?, ?> visitor_col_firstName;

    @FXML
    private TableColumn<?, ?> visitor_col_lastName;

    @FXML
    private TableColumn<?, ?> visitor_col_middleName;

    @FXML
    private TableColumn<?, ?> visitor_col_purposeVisit;

    @FXML
    private TextField visitor_contact;

    @FXML
    private DatePicker visitor_date;

    @FXML
    private TextField visitor_email;

    @FXML
    private TextField visitor_firstName;

    @FXML
    private AnchorPane visitor_form;

    @FXML
    private TextField visitor_lastName;

    @FXML
    private TextField visitor_purposeVisit;

    @FXML
    private TableView<?> visitor_tableView;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Alert alert;
 


    @FXML
    public void studentAddBtn() {
        System.out.println(student_idNo.getText());
        System.out.println(student_firstName.getText());
        System.out.println(student_middleName.getText());
        System.out.println(student_lastName.getText());
        System.out.println(student_contact.getText());
        System.out.println(student_email.getText());
        
        if (student_idNo.getText().isEmpty() || student_firstName.getText().isEmpty() || student_middleName.getText().isEmpty() || student_lastName.getText().isEmpty()
                || student_contact.getText().isEmpty() || student_email.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!!!");
            alert.showAndWait();
        } else {
            String checkIdNo = "SELECT id_number FROM student WHERE id_number = '" + student_idNo.getText() + "'";
            connect = database.connectDB();

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkIdNo);
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(student_idNo.getText() + " is not your id number");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO student " + "(id_number,firstName,middleName,lastName,course,section,year,contact,email,date)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, student_idNo.getText());
                    prepare.setString(2, student_firstName.getText());
                    prepare.setString(2, student_middleName.getText());
                    prepare.setString(3, student_lastName.getText());
                    prepare.setString(4, (String) student_course.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) student_section.getSelectionModel().getSelectedItem());
                    prepare.setString(6, (String) student_year.getSelectionModel().getSelectedItem());
                    prepare.setString(7, student_contact.getText());
                    prepare.setString(8, student_email.getText());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(9, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Save successfully!");
                    alert.showAndWait();
                    
                  //  studentShowData();
                    studentClearBtn();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void studentClearBtn() {
        student_idNo.setText("");
        student_firstName.setText("");
        student_middleName.setText("");
        student_lastName.setText("");
        student_course.getSelectionModel().clearSelection();
        student_section.getSelectionModel().clearSelection();
        student_year.getSelectionModel().clearSelection();
        student_contact.setText("");
        student_email.setText("");

    }

    public ObservableList<studentData> studentDataList() {
        ObservableList<studentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student";
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            studentData stuData;

            while (result.next()) {

                stuData = new studentData(result.getInt("id"), result.getString("id_number"),
                        result.getString("firstName"), result.getString("middleName") ,result.getString("lastName"), result.getString("course"),
                        result.getString("section"), result.getString("year"), result.getString("contact"), result.getString("email"),
                        result.getDate("date"));

                listData.add(stuData);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<studentData> studentListData;

   /* public void studentShowData() {
        studentListData = studentDataList();

        student_col_idNO.setCellValueFactory(new PropertyValueFactory<>("idNo"));
        student_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        student_col_middleName.setCellValueFactory(new PropertyValueFactory<>("middletName"));
        student_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        student_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        student_col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        student_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        student_col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        student_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        student_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        student_tableView.setItems(studentListData);

    }*/

    private String[] courseList = {"BSIT", "BSED", "BEED", "BSBA"};

    public void studentCourseList() {
        List<String> courseL = new ArrayList<>();
        for (String data : courseList) {
            courseL.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(courseL);
        student_course.setItems(listData);
    }

    private String[] sectionList = {"A", "B"};

    public void studentSectionList() {
        List<String> sectionL = new ArrayList<>();
        for (String data : sectionList) {
            sectionL.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(sectionL);
        student_section.setItems(listData);
    }

    private String[] yearList = {"1", "2", "3", "4"};

    public void studentYearList() {
        List<String> yearL = new ArrayList<>();
        for (String data : yearList) {
            yearL.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(yearL);
        student_year.setItems(listData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        studentCourseList();
        studentSectionList();
        studentYearList();
     //   studentShowData();
    }

}

   /*   public void visitorAddBtn() {
             if (visitor_IdNo.getText().isEmpty() || visitor_firstName.getText().isEmpty() || visitor_middleName.getText().isEmpty() || visitor_lastName.getText().isEmpty() || visitor_contact.getText().isEmpty() || visitor_email.getText().isEmpty() || visitor_address.getText().isEmpty() || visitor_purpose.getText().isEmpty()) {
                alert  = new Alert (AlertType.ERROR);Visit
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields!!!");
                alert.showAndWait();
            }else {
                String checkIdNo = "Select firstName FROM visitor WHERE id"-------------
                connect = database.connectDB();
     
            try { 
                 statement = connect.createStatement();
                result = statement.executeQuery(checkIdNo);;
                if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle ("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(visitor_IdNo.getText() + " is not your id number");
            }else {
                String insertData = "INSERT INTO visitor " + " (Id_number,firstName,middleName,lastName,contact,email,address,purposeVisit,date)" + "VALUE(?,?,?,?,?,?,?,?,?)";
                
                prepare = connect.prepareStatement_insertData);
                prepare.setString(1, visitor_idNo.getText());
                prepare.setString(2, visitor_firstName.getText());
                prepare.setString(3, visitor_middleName.getText());
                prepare.setString(4, visitor_lastName.getText());
                prepare.setString(5, visitor_contact.getText());
                prepare.setString(6, visitor_email.getText());
                prepare.setString(7, visitor_address.getText());
                prepare.setString(8, visitor_purposeVisit.getText());
                prepare.setString(9, visitor_date.getText());

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(9, String.valueOf(sqlDate));
                prepare.executeUpdate();
                
                alert = new Alert(AlertType.INFORMATION):
                Alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Save Successfully!");
                alert.showAndWait();
                
                visitorShowData();
                visitorClearBtn();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    public void visitorClearBtn() {
         visitor_IdNo.setText("");
         visitor_firstName.setText("");
         visitor_middleName.setText("");
         visitor_lastName.setText("");
         visitor_contact.setText("");
         visitor_email.setText("");
         visitor_address.setText("");
         visitor_purpose.setText("");
}

    public ObservableList<visitorData> visitorDataList() {
         ObservableList<visitorData>ListData = FXCollections.observableArrayList();

         String sql = "SELECT * FROM visitor";
         connect = database.connectDB();

         try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            visitorData visData;

            while (result.next()) {
            
            
            visData = new studentData(result.getInt("id"), result.getString("id_number"), result.getString("firstName"),result.getString("middleName"), 
                            result.getString("contact"), result.getString("email"), result.getString("address"), result.getString("purposeofVisit"), result.getDate("date"));

               listData.add(visData);
            }
        }catchh (exception e)  {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<visDta> visitorListData;

    public void visitorShowData() {
        visitorListData = studentDataList();

        visitor_col_IdNo.setCellValueFactory(new PropertyValueFactory<>("idNo"));
        visitor_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        visitor_col_middleName.setCellValueFactory(new PropertyValueFactory<>("middlleName"));
        visitor_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        visitor_col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        visitor_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        visitor_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        visitor_col_purposeVisit.setCellValueFactory(new PropertyValueFactory<>("purposeofVisit"));
        visitor_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        visitor_tableView.setItems(visitorListData);
*/ 