/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev.controller;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Larry
 */
public class UserAlert {

    public void alert(String title, String message, Image image) {
        Notifications.create()
                .title(title)
                .text(message)
                .graphic(new ImageView(image))
                .position(Pos.BOTTOM_CENTER)
                .darkStyle()
                .show();
    }
}
