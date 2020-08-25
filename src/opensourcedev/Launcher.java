/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev;

import javafx.application.Application;
import javafx.stage.Stage;
import opensourcedev.view.ViewFactory;

/**
 *
 * @author Larry
 */
public class Launcher extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showLoginWindow();
//        viewFactory.updateStyles();
    }
    
}
