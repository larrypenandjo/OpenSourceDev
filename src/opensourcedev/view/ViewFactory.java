/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        openStage("DashBoard.fxml");
    }

    private void openStage(String fxmlFile) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            if (fxmlFile.equals("DashBoard.fxml")) {  //set stage min and max height for dashboard
                stage.setMinHeight(575);
                stage.setMaxHeight(700);
                stage.setMinWidth(800);
                stage.setMaxWidth(900);
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
////    }
    public void resizeLayoutX(Pane pane) {//resizing X position of Nodes within a pane
        ObservableList<Node> paneChildren = null;
        paneChildren = pane.getChildren();
        HashMap<Node, Double> layoutXByNodes = new HashMap<>();
        for (Node node : paneChildren) {
            layoutXByNodes.put(node, node.getLayoutX());//this operation needs to be outside the listener to avoid getLayoutX() 
        }                                               //returning updated value of the X current node                
        pane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldvalue, Number newvalue) {
                oldvalue = pane.getPrefWidth();
                for (Node node : layoutXByNodes.keySet()) {
                    node.layoutXProperty().bind(new SimpleDoubleProperty(layoutXByNodes.get(node) + (newvalue.doubleValue() - oldvalue.doubleValue()) / 2));
                }
            }
        });
    }

}
