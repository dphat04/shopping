/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Color;
import model.Size;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProduct(int page, int pageSize) {
        List<Product> productList = new ArrayList<>();

        try {
            // Calculate the offset based on the page number and page size
            int offset = (page - 1) * pageSize;

            // Create SQL query with LIMIT and OFFSET clauses
            String query = "SELECT *\n"
                    + "FROM products\n"
                    + "WHERE quantity > 1\n"
                    + "ORDER BY id\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve product details from each row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                int category = resultSet.getInt("category_id");
                String description = resultSet.getString("description");

                // Create a Product object with the retrieved data
                Product product = new Product(id, name, price, category, quantity, image, description);

                // Add the product to the list
                productList.add(product);
            }

            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return productList;
    }

    public List<Product> Get3Product() {
        List<Product> productList = new ArrayList<>();

        try {
            // Calculate the offset based on the page number and page size

            // Create SQL query with LIMIT and OFFSET clauses
            String query = "SELECT top 3*     FROM products   WHERE quantity >= 1 order by id desc";

            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve product details from each row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                int category = resultSet.getInt("category_id");
                String description = resultSet.getString("description");

                // Create a Product object with the retrieved data
                Product product = new Product(id, name, price, category, quantity, image, description);

                // Add the product to the list
                productList.add(product);
            }

            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return productList;
    }

    public Product getProductByID(int productId) {
        Product product = null;

        try {
            String query = "SELECT p.id, p.name, p.price, p.quantity, p.image,c.id as category_id, c.name AS category, p.description,\n"
                    + "    STUFF((SELECT DISTINCT ',' + s.name FROM sizes s\n"
                    + "           JOIN product_variants pv ON pv.size_id = s.id AND pv.product_id = p.id FOR XML PATH('')), 1, 1, '') AS sizes,\n"
                    + "    STUFF((SELECT DISTINCT ',' + co.name FROM colors co\n"
                    + "           JOIN product_variants pv ON pv.color_id = co.id AND pv.product_id = p.id FOR XML PATH('')), 1, 1, '') AS colors\n"
                    + "FROM products p\n"
                    + "JOIN categories c ON p.category_id = c.id\n"
                    + "where p.id =? \n"
                    + "GROUP BY p.id, p.name, p.price, p.quantity, p.image,c.id, c.name, p.description";
            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);

            // Execute the query
            ResultSet rs = statement.executeQuery();

            // Check if a matching product is found
            if (rs.next()) {
                // Retrieve product details from the result set
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("image");
                int category = rs.getInt("category_id");
                String description = rs.getString("description");
                String category_name = rs.getString("category");
                String sizeString = rs.getString("sizes");
                String colorString = rs.getString("colors");

                // Create a Product object with the retrieved data
                product = new Product(id, name, price, category, quantity, image, description, category_name);

                if (sizeString != null && !sizeString.isEmpty()) {
                    List<String> sizes = Arrays.asList(sizeString.split(","));
                    product.setSizes(sizes);
                }

                if (colorString != null && !colorString.isEmpty()) {
                    List<String> colors = Arrays.asList(colorString.split(","));
                    product.setColors(colors);
                }
            }

            // Close the resources
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("jo" + ex);
        }

        return product;
    }
    List<Color> colors = new ArrayList<>();

    public Size getSizesbyId(int cid) {
        Size size = null;

        try {
            String query = "select * from sizes where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cid);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                size = new Size(id, name);

            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return size;
    }

    public Color getColorbyId(int cid) {
        Color color = null;

        try {
            String query = "select * from colors where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cid);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                color = new Color(id, name);

            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return color;
    }

    public List<Size> getSizesForProduct(int productId) {
        List<Size> sizes = new ArrayList<>();

        try {
            String query = "SELECT distinct  s.id, s.name FROM sizes s JOIN product_variants pv ON s.id = pv.size_id \n"
                    + "		   JOIN products p on p.id = pv.product_id\n"
                    + "		   WHERE p.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Size size = new Size(id, name);
                sizes.add(size);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sizes;
    }

    public List<Color> getColorsForProduct(int productId) {

        try {
            String query = "SELECT distinct  c.id, c.name FROM colors c \n"
                    + "		   JOIN product_variants pv ON c.id = pv.color_id\n"
                    + "		     JOIN products p on p.id = pv.product_id\n"
                    + "WHERE p.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Color color = new Color(id, name);
                colors.add(color);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return colors;
    }

    public List<Product> searchProductsWithConditions(String searchParam, String categoryParam, String sortOption, String colorId, String sizeId) {
        List<Product> productList = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT distinct p.* FROM products p ");
            query.append("INNER JOIN product_variants pv ON p.id = pv.product_id ");
            query.append("WHERE p.quantity > 0 ");

            // Create SQL query with sorting, LIMIT, and OFFSET clauses
            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append("AND p.name LIKE ? ");
                list.add("%" + searchParam + "%");
            }
            if (categoryParam != null && !categoryParam.trim().isEmpty()) {
                query.append("AND p.category_id = ? ");
                list.add(Integer.parseInt(categoryParam));
            }
            if (colorId != null && !colorId.trim().isEmpty()) {
                query.append("AND pv.color_id = ? ");
                list.add(colorId);
            }
            if (sizeId != null && !sizeId.trim().isEmpty()) {
                query.append("AND pv.size_id = ? ");
                list.add(sizeId);
            }

            query.append("ORDER BY ");
            switch (sortOption) {
                case "asc":
                    query.append("p.price ASC");
                    break;
                case "desc":
                    query.append("p.price DESC");
                    break;
                case "new":
                    query.append("p.id DESC");
                    break;
                case "sale":
                    query.append("p.quantity DESC");
                    break;
                default:
                    query.append("p.id ASC");
                    break;
            }

            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query.toString());
            mapParams(statement, list);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve product details from each row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                int category = resultSet.getInt("category_id");
                String description = resultSet.getString("description");

                // Create a Product object with the retrieved data
                Product product = new Product(id, name, price, category, quantity, image, description);

                // Add the product to the list
                productList.add(product);
            }

            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return productList;
    }

    public List<Size> getAllSizes() {
        List<Size> sizes = new ArrayList<>();
        try {
            String query = "SELECT * FROM sizes";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Size size = new Size(id, name);
                sizes.add(size);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sizes;
    }

    public List<Color> getAllColors() {
        List<Color> colors = new ArrayList<>();
        try {
            String query = "SELECT * FROM colors";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
//                String code = resultSet.getString("code");

                Color color = new Color(id, name);
                colors.add(color);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return colors;
    }

    public int getTotalProductCount() {
        int count = 0;
        try (
                 PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM products");  ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Product> getProductsToManage(int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (
                 PreparedStatement stmt = connection.prepareStatement("SELECT p.id, p.name, p.price, p.quantity, p.image, c.name AS category, p.description,\n"
                        + "    STUFF((SELECT DISTINCT ',' + s.name FROM sizes s\n"
                        + "           JOIN product_variants pv ON pv.size_id = s.id AND pv.product_id = p.id FOR XML PATH('')), 1, 1, '') AS sizes,\n"
                        + "    STUFF((SELECT DISTINCT ',' + co.name FROM colors co\n"
                        + "           JOIN product_variants pv ON pv.color_id = co.id AND pv.product_id = p.id FOR XML PATH('')), 1, 1, '') AS colors\n"
                        + "FROM products p\n"
                        + "JOIN categories c ON p.category_id = c.id\n"
                        + "GROUP BY p.id, p.name, p.price, p.quantity, p.image, c.name, p.description\n"
                        + "ORDER BY p.id\n"
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setCategoryName(rs.getString("category"));
                    product.setImage(rs.getString("image"));
                    product.setDescription(rs.getString("description"));
                    String sizeString = rs.getString("sizes");
                    if (sizeString != null && !sizeString.isEmpty()) {
                        List<String> sizes = Arrays.asList(sizeString.split(","));
                        product.setSizes(sizes);
                    }
                    String colorString = rs.getString("colors");
                    if (colorString != null && !colorString.isEmpty()) {
                        List<String> colors = Arrays.asList(colorString.split(","));
                        product.setColors(colors);
                    }
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void mapParams(PreparedStatement ps, List<Object> args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Float) {
                ps.setFloat(i++, (Float) arg);
            } else {
                ps.setString(i++, (String) arg);
            }
        }
    }

    public List<Product> Paging(List<Product> list, int pageParam, int size) {
        if (list.isEmpty()) {
            return list;
        }
        return list.subList((pageParam - 1) * size, size * pageParam >= list.size() ? list.size() : size * pageParam);
    }

    public boolean deleteProduct(int productId) {

        PreparedStatement statement = null;

        try {
            // Get a database connection

            // Create the SQL query
            String query = "DELETE FROM products WHERE id = ?";

            // Create the prepared statement
            statement = connection.prepareStatement(query);

            // Set the parameter values
            statement.setInt(1, productId);

            // Execute the query
            int rowsDeleted = statement.executeUpdate();

            // Check if any rows were deleted
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors here
        } finally {
            // Close the statement and connection
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("loooo " + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public void updateProduct(Product product) {
        try {
            // Update the product information
            String updateProductQuery = "UPDATE products SET name = ?, price = ?, quantity = ?, image = ?, category_id = ?, description = ? WHERE id = ?";
            PreparedStatement updateProductStatement = connection.prepareStatement(updateProductQuery);
            updateProductStatement.setString(1, product.getName());
            updateProductStatement.setDouble(2, product.getPrice());
            updateProductStatement.setInt(3, product.getQuantity());
            updateProductStatement.setString(4, product.getImage());
            updateProductStatement.setInt(5, product.getCategory());
            updateProductStatement.setString(6, product.getDescription());
            updateProductStatement.setInt(7, product.getId());
            updateProductStatement.executeUpdate();
            updateProductStatement.close();

            // Update the product variants (sizes and colors)
            String deleteProductVariantsQuery = "DELETE FROM product_variants WHERE product_id = ?";
            PreparedStatement deleteProductVariantsStatement = connection.prepareStatement(deleteProductVariantsQuery);
            deleteProductVariantsStatement.setInt(1, product.getId());
            deleteProductVariantsStatement.executeUpdate();
            deleteProductVariantsStatement.close();

            String insertProductVariantQuery = "INSERT INTO product_variants (product_id, size_id, color_id) VALUES (?, ?, ?)";
            PreparedStatement insertProductVariantStatement = connection.prepareStatement(insertProductVariantQuery);

            List<Size> sizes = product.getListSize();
            List<Color> colors = product.getListColor();

            // Insert the new product variants
            for (Size size : sizes) {
                for (Color color : colors) {
                    insertProductVariantStatement.setInt(1, product.getId());
                    insertProductVariantStatement.setInt(2, size.getId());
                    insertProductVariantStatement.setInt(3, color.getId());
                    insertProductVariantStatement.executeUpdate();
                }
            }

            insertProductVariantStatement.close();

        } catch (SQLException ex) {
            System.out.println("Error updating product: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

 public boolean addProduct(Product product, List<String> sizes, List<String> colors) {
    PreparedStatement statement = null;
    ResultSet generatedKeys = null;

    try {
        // Create the SQL query for inserting the product
        String query = "INSERT INTO products (name, price, quantity, image, category_id, description) VALUES (?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        // Set the parameter values for the product
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setString(4, product.getImage());
        statement.setInt(5, product.getCategory());
        statement.setString(6, product.getDescription());

        // Execute the query for inserting the product
        int rowsInserted = statement.executeUpdate();

        // Check if any rows were inserted
        if (rowsInserted > 0) {
            // Retrieve the generated product ID
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int productId = generatedKeys.getInt(1);

                // Insert sizes and colors into the product_variants table
                if (sizes != null && colors != null) {
                    for (String size : sizes) {
                        for (String color : colors) {
                            // Insert size and color for the product variant
                            insertProductVariant(productId, size, color);
                        }
                    }
                }
            }
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any database errors here
    } finally {
        // Close the result set
        if (generatedKeys != null) {
            try {
                generatedKeys.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Close the statement
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return false;
}

    private void insertProductVariant(int productId, String size, String color) throws SQLException {
        String variantQuery = "INSERT INTO product_variants (product_id, size_id, color_id) VALUES (?, ?, ?)";
        PreparedStatement variantStatement = connection.prepareStatement(variantQuery);

        variantStatement.setInt(1, productId);
        variantStatement.setString(2, size);
        variantStatement.setString(3, color);

        variantStatement.executeUpdate();
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Color> list = dao.getColorsForProduct(1);
        for (Color product : list) {
            System.out.println(product);
        }

//        Product p = new Product(1, "haha", 1, 1, 1, "hah", "haha");
//        boolean a = dao.updateProduct(p);
//        System.out.println(a);
    }
}
