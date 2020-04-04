package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean isWritingNotes = true;
        Scanner scan = new Scanner(System.in);

        Connection connection = null;
        while(isWritingNotes) {
            System.out.println("Möchten Sie eine Notiz hinzufügen? Mit x sehen Sie sich Ihr Notizbuch an.");
            String noteFromUser = scan.nextLine();
            if (noteFromUser.equalsIgnoreCase("x")) {
                isWritingNotes = false;
            }
            if (isWritingNotes) {
                try {
                    String url = "jdbc:mysql://localhost:3306/codingcampus?user=root";
                    connection = DriverManager.getConnection(url);
                    Statement st = null;
                    String sql = "INSERT INTO note (note) VALUES ('"+noteFromUser+"')";
                    st = connection.createStatement();
                    st.executeUpdate(sql);

                } catch (SQLException ex) {
                    ex.fillInStackTrace();
                }
            }
        }

        try {
            String url = "jdbc:mysql://localhost:3306/codingcampus?user=root";
            connection = DriverManager.getConnection(url);
            Statement statement = null;
            String query = "select * from note";
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String note = rs.getString("note");
                String date = rs.getDate("date").toString();
                System.out.println(note + " " + date);
            }
        } catch (SQLException ex) {
            ex.fillInStackTrace();
        }


    }
}
