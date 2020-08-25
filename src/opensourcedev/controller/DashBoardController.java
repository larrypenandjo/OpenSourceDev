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
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    private void editUserPassword(ActionEvent event) {
        if (usernameField.isDisabled() || newPasswordField.isDisabled() || repeatNewPasswordField.isDisabled()) {
            usernameField.setDisable(false);
            newPasswordField.setDisable(false);
            repeatNewPasswordField.setDisable(false);
        }
        
    }

    @FXML
    private void saveUserPassword(ActionEvent event) {
    }

}
