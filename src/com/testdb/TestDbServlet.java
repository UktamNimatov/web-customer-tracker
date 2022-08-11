package com.testdb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
//            throws javax.servlet.ServletException, IOException {
//
//    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        String user = "springstudent";
        String password = "springstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to the database: " + jdbcUrl);
            Class.forName(driver);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, password );
            out.println("Connection success");
            myConn.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
