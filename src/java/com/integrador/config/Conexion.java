    package com.integrador.config;

    import java.sql.Connection;
    import java.sql.DriverManager;
    public class Conexion {
        Connection con;
        String url="jdbc:mysql://127.0.0.1:3306/integrador";
        String user="root";
        String pass="";
        public Connection getConnection(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Se conectó a bdd");
                con=DriverManager.getConnection(url,user,pass);
            } catch (Exception e) {
                System.out.println("No se conectó a la bdd");
            }
            return con; 
        }
    }