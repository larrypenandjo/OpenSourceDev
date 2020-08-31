/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev.controller.logic;

import opensourcedev.DbConnection;
import opensourcedev.model.UserAccount;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import opensourcedev.controller.UserAlert;

/**
 *
 * @author Larry
 */
public class ChangeLoginPassword {
    private UserAccount userAccount;

    public ChangeLoginPassword(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
    public void updateLoginPassword() throws SQLException{
        Connection con = DbConnection.getConnection();
        UserAlert userAlert = new UserAlert();
        Image image = new Image("opensourcedev/images/mooo.png");
        String query = "UPDATE user SET password = ? WHERE username = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, userAccount.getPassword());
            stmt.setString(2, userAccount.getUserName());
            stmt.executeUpdate();
            userAlert.alert("SUCCESS", "Password changed", image);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            if(stmt != null){
                stmt.close();
            }
        }
    }
}
