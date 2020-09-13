/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import opensourcedev.controller.DashBoardController;

/**
 *
 * @author Larry
 */
public class ViewFactory {

    ArrayList<Parent> activeParentNodes = new ArrayList<>();

    public void showLoginWindow() {
        System.out.println("Show loginwindow called");
        openStage("LoginWindow.fxml");
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    public void showDashboard() {
        System.out.println("Show dashbord called");
//        DashBoardController dashBoardController = new DashBoardController();
//        openStage(dashBoardController, "DashBoard.fxml");
        openStage("DashBoard.fxml");
    }

//private void openStage(DashBoardController dashBoardController, String fxmlFile) {//method should receive an abstract base controller class which can either be a dashboardwindow- or loginwindowcontroller
    private void openStage(String fxmlFile) {
        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
//            fxmlLoader.setController(dashBoardController);
//            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            if (fxmlFile.equals("DashBoard.fxml")) {  //set stage min and max height for dashboard
                stage.setMinHeight(575);
                stage.setMaxHeight(700);
                stage.setMinWidth(800);
                stage.setMaxWidth(1000);
            }
            stage.show();
            activeParentNodes.add(root);
//            updateStyles();
        } catch (IOException ex) {
            Logger.getLogger(ViewFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void updateStyles() {
//        ObservableList<Node> rootList = null;
//        for (Parent root : activeParentNodes) {
//            rootList = root.getChildrenUnmodifiable();
//        }
//        for (Node node : rootList) {
//            if (node instanceof Button) {
//                node.setOnMouseClicked((e) -> {
//                    node.setStyle("-fx-background-color: #4C83A2;");
//                });
//
//            }
//        }
//   }
    public void resizeLayoutXAdminPaneChildNodes(Pane pane) {//resizing X position of Nodes within a pane
        pane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldValue, Number newValue) {
                double xPosition = 0;
                for (Node node : pane.getChildren()) {//new x-position = initial x position + (deltaWidth of pane)/2
                    if (oldValue.doubleValue() != 0) {
                        xPosition = ((newValue.doubleValue() - oldValue.doubleValue()) / 2) + node.getLayoutX();
                        node.layoutXProperty().bind(new SimpleDoubleProperty(xPosition));//actualize the position of the node
                    }
                }
            }
        });
    }

    public void resizeLayoutXStaffPaneChildNodes(ObservableList<Node> staffPane_Panes) {
        for (Node node : staffPane_Panes) {
            if (node instanceof Pane) {
                ((Pane) node).widthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                        double xPosition = 0;
                        for (Node childNode : ((Pane) node).getChildren()) {
                            if (oldValue.doubleValue() != 0) {
                                xPosition = ((newValue.doubleValue() - oldValue.doubleValue()) / 2) + childNode.getLayoutX();
                                childNode.layoutXProperty().bind(new SimpleDoubleProperty(xPosition));//actualize the position of the node
                            }
                        }
                    }
                });
            }
        }
    }
}
