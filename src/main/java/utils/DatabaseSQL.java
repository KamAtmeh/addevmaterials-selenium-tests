package utils;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static portalHR.IConstantes.*;

/* Class for defining tools related to the database and that can be used in any project */

public class DatabaseSQL extends Logging {

    // Method to connect to the database
    private static Connection connectToDatabase() {
        Connection connection = null;
        try {
            // Connect to the databse
            connection = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }


    // Method to retrieve random values of the portal tools along with the corresponding email
    public static Map<String, String> getPortalToolsMap(String pays) {

        // Create a Map to store the column values
        Map<String, String> columnMap = new LinkedHashMap<>();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        // Change country name to acronym
        switch (pays){
            case "United States" :
                pays = "usa";
                break;

            case "United Kingdom" :
                pays = "uk";
                break;

            case "United Arab Emirates" :
                pays = "uae";
                break;

            default :
                // do nothing
        }

        try {
            // Connect to the databse
            connection = connectToDatabase();

            // Initialize statement
            statement = connection.createStatement();
            // Execute query
            resultSet = statement.executeQuery("SELECT * FROM (SELECT * FROM es.Application WHERE EMAIL != '' " + "AND REGION = '" + pays + "' ORDER BY RAND() LIMIT 6) AS subquery ORDER BY Email;");

            // Process the result set
            while (resultSet.next()) {
                // Retrieve values from the two columns
                String column1Value = resultSet.getString("name").replace("x3 licence", "licence x3");
                String column2Value = resultSet.getString("email");
                // Map the column values
                columnMap.put(column1Value, column2Value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in the finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return columnMap;
        }
    }


    // Method to retrieve the email trace that allows to verify the email address of the corresponding tool
    public static Map<String, String> getEmailTrace(String name) {

        // Create a Map to to stock email trace
        Map<String, String> responseMap = new LinkedHashMap<>();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        try {
            // Connect to the databse
            connection = connectToDatabase();
            // Initialize statement
            statement = connection.createStatement();
            // Execute query
            resultSet = statement.executeQuery("SELECT meta,trace FROM es.ra_log WHERE meta LIKE '%" + name + "%' AND meta LIKE '%Test for%' ORDER BY meta;");


            // Set pattern for email retrieval
            Pattern pattern = Pattern.compile("for\\s+(.*?)\\s+<c");

            // Process the result set
            while (resultSet.next()) {

                // Retrieve HTML trace
                String htmlTable= resultSet.getString("trace");
                // Parse String to HTML
                Document doc = Jsoup.parse(htmlTable);
                // Get besoins node
                Elements besoinsNode = doc.selectXpath("//p[contains(text(), 'Besoins')]/../following-sibling::td/p");
                // Retrieve the text of the selected node(s)
                String besoinsText = besoinsNode.first().text().replace(" (Amelkis : No Amelkis)", "").replace("clipper", "CLIPPER");

                // Retrieve email trace from the meta column
                String emailTrace = resultSet.getString("meta");
                // Retrieve email address based on pattern
                Matcher matcher = pattern.matcher(emailTrace);
                String email = null;
                if (matcher.find()) {
                    email = matcher.group(1);
                }

                responseMap.put(besoinsText, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in the finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return responseMap;
        }
    }
}
