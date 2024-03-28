/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends DBContext {

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();

        try {
            // Calculate the offset based on the page number and page size

            // Create SQL query with LIMIT and OFFSET clauses
            String query = "select * from categories";

            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve product details from each row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                // Create a Product object with the retrieved data
                Category category = new Category(id, name);

                // Add the product to the list
                categories.add(category);
            }

            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return categories;
    }

    public boolean addCategory(Category category) {
        PreparedStatement statement = null;

        try {
            // Create the SQL query
            String query = "INSERT INTO categories (name) VALUES (?)";
            statement = connection.prepareStatement(query);

// Set the parameter values
            statement.setString(1, category.getName());

// Execute the query
            int rowsInserted = statement.executeUpdate();

            // Check if any rows were inserted
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors here
        
        }

        return false;
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
//        List<Category> categories = dao.getAllCategory();
//        for (Category category : categories) {
//            System.out.println(category);
//        }
        Category category = new Category("HAHA");
        dao.addCategory(category);
    }
}
