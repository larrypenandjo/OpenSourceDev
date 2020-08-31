/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev.controller;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import opensourcedev.controller.logic.ChangeLoginPassword;
import opensourcedev.model.UserAccount;
import opensourcedev.view.ViewFactory;

/**
 * FXML Controller class
 *
 * @author Larry
 */
public class DashBoardController implements Initializable {

    @FXML
    private Label currentMenuLbl;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private AnchorPane editAdminDataPane;
    @FXML
    private Button adminBtn;
    @FXML
    private Pane adminPane;
    private ViewFactory viewFactory = new ViewFactory();
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField repeatNewPasswordField;
    @FXML
    private TextField usernameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!dashboardPane.isVisible()) {//default displayed pane should be the main dashboard
            dashboardPane.setVisible(true);
            editAdminDataPane.setVisible(false);
        }
        currentMenuLbl.setText("");//label that displays the name of the current content of the dashboard
        usernameField.setText("admin");
        viewFactory.resizeLayoutX(adminPane);
        
        repeatNewPasswordField.setOnKeyPressed((e)->{
            if(e.getCode().equals(KeyCode.ENTER)){
                try {
                    saveUserPassword();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        newPasswordField.setOnKeyPressed((e)->{
            if(e.getCode().equals(KeyCode.TAB)){
                repeatNewPasswordField.requestFocus();
            }
        });
        
        adminBtn.setOnMouseClicked((e)->{
            editAdminData();
        });
    }

    @FXML
    private void editAdminData() {
        if (!editAdminDataPane.isVisible()) {
            editAdminDataPane.setVisible(true);
            dashboardPane.setVisible(false);
        }
        currentMenuLbl.setText("ADMIN");
    }

    @FXML
    private void editApplicationSettings(MouseEvent event) {
    }

    @FXML
    private void shutDownApplication(MouseEvent event) {
    }

    @FXML
    private void displayDashboardPane() {
        if (!dashboardPane.isVisible()) {
            dashboardPane.setVisible(true);
            editAdminDataPane.setVisible(false);
        }
        currentMenuLbl.setText("");
    }

    @FXML
    private void editUserPassword() {
        if (newPasswordField.isDisabled() || repeatNewPasswordField.isDisabled()) {
            newPasswordField.setDisable(false);
            repeatNewPasswordField.setDisable(false);
        }
    }

    @FXML
    private void saveUserPassword() throws SQLException {
        if (newPasswordField.getText().equals(repeatNewPasswordField.getText())) {
            UserAccount userAccount = new UserAccount(usernameField.getText(), newPasswordField.getText());
            ChangeLoginPassword changeLoginPassword = new ChangeLoginPassword(userAccount);
            changeLoginPassword.updateLoginPassword();
            newPasswordField.setDisable(true);
            repeatNewPasswordField.setDisable(true);
            
        } else {
            Image image = new Image("opensourcedev/images/delete.png");
            UserAlert userAlert = new UserAlert();
            userAlert.alert("ERROR", "Passwords do not match", image);
        }
    }

}
