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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
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
    protected PasswordField newPasswordField;
    @FXML
    protected PasswordField repeatNewPasswordField;
    @FXML
    private TextField usernameField;
    @FXML
    private AnchorPane staffPane;
    @FXML
    private Pane censorPane;
    @FXML
    private Label censorLbl;
    @FXML
    private Pane presidentPane;
    @FXML
    private Pane vpPane;
    @FXML
    private Pane AutorPane;
    @FXML
    private Pane secretaryPane;
    @FXML
    private Pane treasurerPane;
    @FXML
    private HBox staffPaneHbox2;
    @FXML
    private HBox staffPaneHbox1;
    @FXML
    private Label censorName;
    @FXML
    private AnchorPane staffPaneElement;
    @FXML
    private Pane staffPaneBaner_Pane;
    @FXML
    private Label staffPaneBaner_Label;
    @FXML
    private FontAwesomeIconView staffPaneBaner_arrow;
    @FXML
    private Label staffPaneElementLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Node> staffPane_Panes = FXCollections.observableArrayList();
        staffPane_Panes.addAll(staffPaneHbox1.getChildren());
        staffPane_Panes.addAll(staffPaneHbox2.getChildren());

        if (!dashboardPane.isVisible()) {//default displayed pane should be the main dashboard
            dashboardPane.setVisible(true);
            editAdminDataPane.setVisible(false);
            staffPane.setVisible(false);
            staffPaneElement.setVisible(false);
        }
        currentMenuLbl.setText("");//label that displays the name of the current content of the dashboard
        staffPaneElementLbl.setText("");
        usernameField.setText("admin");

        viewFactory.resizeLayoutXAdminPaneChildNodes(adminPane);
        viewFactory.resizeLayoutXStaffPaneChildNodes(staffPane_Panes);

        repeatNewPasswordField.setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                try {
                    saveUserPassword();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        newPasswordField.setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.TAB)) {
                repeatNewPasswordField.requestFocus();//move caret to password field on TAB-Key pressed
            }
        });

        adminBtn.setOnMouseClicked((e) -> {
            editAdminData();
        });
    }

    @FXML
    private void editAdminData() {
        if (!editAdminDataPane.isVisible()) {
            editAdminDataPane.setVisible(true);
            dashboardPane.setVisible(false);
            staffPane.setVisible(false);
            staffPaneElement.setVisible(false);
        }
        currentMenuLbl.setText("ADMIN");
        staffPaneElementLbl.setText("");
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
            staffPane.setVisible(false);
            staffPaneElement.setVisible(false);
        }
        currentMenuLbl.setText("");
        staffPaneElementLbl.setText("");
    }

    @FXML
    private void displayStaffPane() {
        if (!staffPane.isVisible()) {
            staffPane.setVisible(true);
            editAdminDataPane.setVisible(false);
            dashboardPane.setVisible(false);
            staffPaneElement.setVisible(false);
        }
        currentMenuLbl.setText("STAFF");
        staffPaneElementLbl.setText("");
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
        if (isFieldValid()) {
            UserAccount userAccount = new UserAccount(usernameField.getText(), newPasswordField.getText());
            ChangeLoginPassword changeLoginPassword = new ChangeLoginPassword(userAccount);
            changeLoginPassword.updateLoginPassword();
            newPasswordField.setDisable(true);
            repeatNewPasswordField.setDisable(true);
        } else {
            Image image = new Image("opensourcedev/images/delete.png");
            UserAlert userAlert = new UserAlert();
            userAlert.alert("ERROR", "Empty fields or passwords wrong", image);
        }
    }

    @FXML
    private void displayStaffPaneElement_President() {
        displayStaffPaneElement();
        staffPaneBaner_Pane.setStyle("-fx-background-color: #96A24C;");
        staffPaneBaner_Label.setText("President");
        staffPaneElementLbl.setText("President");
        staffPaneBaner_arrow.setStyleClass("staffPaneBaner-arrow-president");
    }

    @FXML
    private void displayStaffPaneElement_VP() {
        displayStaffPaneElement();
        staffPaneBaner_Pane.setStyle("-fx-background-color: #71AD85;");
        staffPaneBaner_Label.setText("Vice President");
        staffPaneElementLbl.setText("Vice President");
        staffPaneBaner_arrow.setStyleClass("staffPaneBaner-arrow-vp");
    }

    @FXML
    private void displayStaffPaneElement_Auditor() {
        displayStaffPaneElement();
        staffPaneBaner_Pane.setStyle("-fx-background-color: #4C83A2;");
        staffPaneBaner_Label.setText("Auditor");
        staffPaneElementLbl.setText("Auditor");
        staffPaneBaner_arrow.setStyleClass("staffPaneBaner-arrow-auditor");
    }

    @FXML
    private void displayStaffPaneElement_Censor() {
        displayStaffPaneElement();
        staffPaneBaner_Pane.setStyle("-fx-background-color: #9673AC;");
        staffPaneBaner_Label.setText("Censor");
        staffPaneElementLbl.setText("Censor");
        staffPaneBaner_arrow.setStyleClass("staffPaneBaner-arrow-censor");
    }

    @FXML
    private void displayStaffPaneElement_Secretary() {
        displayStaffPaneElement();
        staffPaneBaner_Pane.setStyle("-fx-background-color: #607773;");
        staffPaneBaner_Label.setText("Secretary");
        staffPaneElementLbl.setText("Secretary");
        staffPaneBaner_arrow.setStyleClass("staffPaneBaner-arrow-secretary");
    }

    @FXML
    private void displayStaffPaneElement_Treasurer() {
        displayStaffPaneElement();
        staffPaneBaner_Pane.setStyle("-fx-background-color: #625462;");
        staffPaneBaner_Label.setText("Treasurer");
        staffPaneElementLbl.setText("Treasurer");
        staffPaneBaner_arrow.setStyleClass("staffPaneBaner-arrow-treasurer");
    }

    public void displayStaffPaneElement() {
        staffPaneBaner_arrow.getStyleClass().clear();
        staffPaneBaner_arrow.setStyleClass(null);
        if (!staffPaneElement.isVisible()) {
            staffPaneElement.setVisible(true);
            staffPane.setVisible(false);
            editAdminDataPane.setVisible(false);
            dashboardPane.setVisible(false);
            staffPaneBaner_Label.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            currentMenuLbl.setText("STAFF");
            staffPaneElementLbl.setText("");
        }
    }

    private boolean isFieldValid() {
        if (newPasswordField.getText().isBlank() && repeatNewPasswordField.getText().isBlank()) {
            return false;
        } else {
            if (newPasswordField.getText().equals(repeatNewPasswordField.getText())) {
                return true;
            } else {
                return false;
            }
        }
    }

}
