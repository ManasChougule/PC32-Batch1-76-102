package org.manas.jdbc;

import java.io.IOException;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseTester {
    static String dtypeMenu;
    static String menu;

    static {
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append("1. Create Table\n");
        menuBuilder.append("2. Display Table Structure\n");
        menuBuilder.append("0. EXIT\n");
        menuBuilder.append("Choice:");
        menu = menuBuilder.toString();

        StringBuilder dataTypeMenuBuilder = new StringBuilder();
        dataTypeMenuBuilder.append("\t\tSelect DataType for column:\n");
        dataTypeMenuBuilder.append("\t\t\t1.VARCHAR\n");
        dataTypeMenuBuilder.append("\t\t\t2.INT\n");
        dataTypeMenuBuilder.append("\t\t\t3.FLOAT\n");
        dtypeMenu = dataTypeMenuBuilder.toString();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("application.properties"));

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.uname"),
                props.getProperty("db.pass"));

        Scanner sc = new Scanner(System.in);
        String ch = "0";
        while (true) {
            System.out.print(menu);
            ch = sc.next();
            System.out.println();

            if (ch.equals("0")) {
                System.out.println("Exiting...");
                break;
            }

            if (ch.equals("1")) {
                createTable(conn, sc);
                continue;
            }

            if (ch.equals("2")) {
                displayTableStruct(conn, sc);
                continue;
            }

            System.out.println("\n!!Please Enter valid choice (1,2)");
        }
        sc.close();
        conn.close();
    }

    private static void displayTableStruct(Connection conn, Scanner sc) throws SQLException {
        String tname = Helpers.inputValidStringBasic(sc, "\t\tEnter table name: ", "\t\tInvalid table name");
        DatabaseMetaData dmd = conn.getMetaData();
        ResultSet columns = dmd.getColumns(null, null, tname, null);
        StringBuilder sb = new StringBuilder();
        while (columns.next()) {
            sb.append(columns.getString("COLUMN_NAME")).append(": ");
            sb.append(columns.getString("TYPE_NAME")).append("\n");
        }
        System.out.println(sb.toString() + "\n");
        columns.close();
    }

    private static void createTable(Connection conn, Scanner sc) throws SQLException {
        String tname = Helpers.inputValidStringBasic(sc, "\t\tEnter Table name: ", "\t\t!!Invalid Table name");
        ArrayList<ArrayList<String>> cols = new ArrayList<>();

        while (true) {
            String cname = Helpers.inputValidStringBasic(sc, "\t\tEnter column name: ", "\t\t!!Invalid Column name");
            System.out.print(dtypeMenu);
            String dTypeChoice = Helpers.inputValidString(sc, "\t\tChoice: ", "\t\t!!Invalid choice select (1,2,3)",
                    (str) -> Arrays.asList("1", "2", "3").contains(str.trim()));
            System.out.println();
            String dtype = null;

            if (dTypeChoice.equals("1")) {
                dtype = "VARCHAR(255)";
            }

            if (dTypeChoice.equals("2")) {
                dtype = "INT";
            }

            if (dTypeChoice.equals("3")) {
                String floatSize = Helpers.inputValidString(sc, "\t\tEnter float Size(Total, Decimal): ",
                        "\t\tInvalid Size", (str) -> {
                            try {
                                String[] tokens = str.split(",");
                                if (tokens.length != 2) return false;
                                Integer.parseInt(tokens[0]);
                                Integer.parseInt(tokens[1]);
                                return true;
                            } catch (Exception e) {
                                return false;
                            }
                        });
                dtype = "FLOAT(" + floatSize.trim() + ")";
            }

            cols.add(new ArrayList<>(Arrays.asList(cname, dtype)));

            String yn = Helpers.inputValidString(sc, "\t\t\ntWant to Add more Columns: (y/n)",
                    "\t\t!!invalid input give(y/n)", (str) -> Arrays.asList("y", "yes", "n", "no").contains(str.toLowerCase().trim())).toLowerCase().trim();

            if (yn.equals("n") || yn.equals("no")) {
                break;
            }
        }

        StringBuilder statementBuilder = new StringBuilder("CREATE TABLE " + tname + " (");
        for (int x = 0; x < cols.size(); x++) {
            statementBuilder.append(cols.get(x).get(0)).append(" ").append(cols.get(x).get(1));
            if (x + 1 < cols.size()) {
                statementBuilder.append(", ");
            }
        }
        statementBuilder.append(")");

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(statementBuilder.toString());
        stmt.close();
        System.out.println("Table " + tname + " created successfully!");
    }
}