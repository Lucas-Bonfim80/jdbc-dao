package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e){
                throw new DbExeption(e.getMessage());
            }
        }
        return  conn;
    }

    public static void closeConnection(){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e){
                throw new DbExeption(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (InputStream input = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            props.load(input);
            return props;
        } catch (IOException | NullPointerException e) {
            throw new DbExeption("Erro ao carregar db.properties: " + e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbExeption(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet  rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbExeption(e.getMessage());
            }
        }
    }

}
