/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import opensourcedev.controller.logic.AppLogin;
import opensourcedev.controller.logic.LoginResult;
import opensourcedev.model.UserAccount;
import opensourcedev.view.ViewFactory;

/**
 * FXML Controller class
 *
 * @author Larry
 */
public class LoginWindowController implements Initializable{

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private UserAlert notification = new UserAlert();
    private ViewFactory viewFactory = new ViewFactory();
    private Image image = new Image("opensourcedev/images/delete.png");

    @FXML
    private void loginBtnAction() throws SQLException {
        UserAccount userAccount = new UserAccount(usernameField.getText(), passwordField.getText());
        AppLogin appLogin = new AppLogin(userAccount);
        
        if(isFieldValid()){
            LoginResult loginResult=appLogin.login(userAccount);
            switch(loginResult){
                case SUCCESS:
                    viewFactory.showDashboard();
                    Stage current = (Stage)usernameField.getScene().getWindow();
                    viewFactory.closeStage(current);
                    return;
                case USERNAME_WRONG:
                    notification.alert("ERROR", "Username wrong", image);
                    return;
                case PASSWORD_WRONG:
                    notification.alert("ERROR", "Password wrong", image);
                    return;
                default:
                    return;
            }
            
        }
    }

    private boolean isFieldValid() {
        if(usernameField.getText().isEmpty()||passwordField.getText().isEmpty()){
            notification.alert("ERROR", "Check fields", image);
            return false;
        }else{
            return true;
        }
    }
    
    @FXML
    private void loginOnKeyPressed(KeyEvent event) throws SQLException{
            if(event.getCode().equals(KeyCode.ENTER)){
                    loginBtnAction();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameField.setText("admin");
        passwordField.setText("12345");
    }
    
}
