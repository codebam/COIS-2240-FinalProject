package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        // launch(args);
        Connection conn = connectToDB();
        Statement statement = getStatement(conn);


        ArrayList<String> types = generateTypes(statement);
        for (String t : types) {
            ArrayList<String> items = itemsFromType(statement, t);
            System.out.println("Type: " + t);
            for (String x : items){
                System.out.println(x);
            }
            System.out.println("");
        }
        closeConnection(statement, conn);
    }

    public static Connection connectToDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src\\menu.db");
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return conn;
    }

    public static Statement getStatement(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return statement;
    }

    public static void closeConnection(Statement statement, Connection conn) {
        try {
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

}
