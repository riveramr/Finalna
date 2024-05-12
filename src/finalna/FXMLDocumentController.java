package finalna;

import finalna.database;
import finalna.getData;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.scene.Parent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button Close;

    @FXML
    private AnchorPane Homepage;

    @FXML
    private Button Login_btn;

    @FXML
    private Label campusname;

    @FXML
    private Label logbook;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView logo1;

    @FXML
    private StackPane stack_home;
    
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    
    @FXML
    public void Login() throws IOException {
        System.out.println("im clicked");

        String user = username.getText();
        String pass = password.getText();
        String sql = "SELECT * FROM admin WHERE username = ? and password = ? ";

        try {
            connect = database.connectDB();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            result = prepare.executeQuery();
            Alert alert;

            if (result.next()) {

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login");
                alert.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
//                stack_home i klose yung pannel

            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println("error here!");
            e.printStackTrace();
        } 

    }

    @FXML
    public void Exit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
