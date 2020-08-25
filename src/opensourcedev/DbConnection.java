/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensourcedev;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Larry
 */
public class DbConnection {
    static Connection con;
    static String url = "jdbc:mysql://localhost:3306/opensourcedev";
    static String driverConnection = "com.mysql.cj.jdbc.Driver";
    static String username = "root";
    static String password = "Tchoufack24";
    public static Connection getConnection(){
        try {
            Class.forName(driverConnection);
            System.out.println("enter driver connection");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("connected");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
    
