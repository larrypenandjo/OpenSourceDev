/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev.controller.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import opensourcedev.DbConnection;
import opensourcedev.model.UserAccount;

/**
 *
 * @author Larry
 */
public class AppLogin {

    private UserAccount userAccount;

    public AppLogin(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public LoginResult login(UserAccount userAccount) throws SQLException {
        Connection con = DbConnection.getConnection();
        String userName, passWord;
        PreparedStatement stmt = null;
        String query = "SELECT username, password FROM user";
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userName = rs.getString("username");
                passWord = rs.getString("password");
                
                if (userAccount.getUserName().equals(userName)) {
                    if(userAccount.getPassword().equals(passWord)){
                        return LoginResult.SUCCESS;
                    }else{
                        return LoginResult.PASSWORD_WRONG;
                    }
                }else{
                    return LoginResult.USERNAME_WRONG;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return null;
    }

}
