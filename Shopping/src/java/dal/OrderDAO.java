/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext {

    public boolean addOrder(Order order) {
        PreparedStatement statement = null;

        try {
            // Create the SQL query
           String query = "INSERT INTO orders (account_id, product_id, size_id, color_id, quantity, price) "
        + "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            // Set the parameter values
            statement.setInt(1, order.getAccountId());
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getSizeId());
            statement.setInt(4, order.getColorId());
            statement.setInt(5, order.getQuantity());
            statement.setDouble(6, order.getPrice());
//            statement.setTimestamp(5, order.getCreatedAt());

            // Execute the query
            int rowsInserted = statement.executeUpdate();

            // Check if any rows were inserted
            return rowsInserted > 0;
        } catch (SQLException e) {
            // Handle any database errors here

        }

        return true;
    }

    public List<Order> getOrderToManage(int page, int pageSize) {
        List<Order> orders = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (
                 PreparedStatement stmt = connection
                        .prepareStatement("SELECT o.id, a.fullname, p.name, o.quantity, o.price, o.created_at, o.status\n"
                                + "FROM orders o\n"
                                + "JOIN accounts a ON a.id = o.account_id\n"
                                + "JOIN products p ON o.product_id = p.id\n"
                                + "ORDER BY o.created_at DESC\n"
                                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;")) {
                    stmt.setInt(1, offset);
                    stmt.setInt(2, pageSize);
                    try ( ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            Order order = new Order(
                                    rs.getInt("id"),
                                    rs.getString("fullname"),
                                    rs.getString("name"),
                                    rs.getInt("quantity"),
                                    rs.getDouble("price"),
                                    rs.getTimestamp("created_at"),
                                    rs.getInt("status")
                            );
                            orders.add(order);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return orders;
    }

    public List<Order> getOrderByAccountID(int page, int pageSize, int accId) {
        List<Order> orders = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (
                 PreparedStatement stmt = connection
                        .prepareStatement("SELECT o.id, a.fullname, p.name, o.quantity, o.price, o.created_at, o.status\n"
                                + "                                FROM orders o\n"
                                + "                                JOIN accounts a ON a.id = o.account_id\n"
                                + "                                JOIN products p ON o.product_id = p.id\n"
                                + "                                WHERE a.id = ?\n"
                                + "                                ORDER BY o.created_at DESC\n"
                                + "                                OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                    stmt.setInt(1, accId);
                    stmt.setInt(2, offset);
                    stmt.setInt(3, pageSize);
                    try ( ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            Order order = new Order(
                                    rs.getInt("id"),
                                    rs.getString("fullname"),
                                    rs.getString("name"),
                                    rs.getInt("quantity"),
                                    rs.getDouble("price"),
                                    rs.getTimestamp("created_at"),
                                    rs.getInt("status")
                            );
                            orders.add(order);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return orders;
    }

    public int getTotalOrdersCount() {
        int count = 0;
        try (
                 PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM Orders");  ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalOrdersUserCount(int accountId) {
        int count = 0;
        try (
                 PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM Orders WHERE account_id = ?");) {
            stmt.setInt(1, accountId); // Set the account_id value
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean confirmOrder(int orderId) {
        try ( PreparedStatement stmt = connection.prepareStatement("UPDATE Orders SET status = 1 WHERE id = ?")) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean cancelOrder(int orderId) {
        try ( PreparedStatement stmt = connection.prepareStatement("UPDATE Orders SET status = 2 WHERE id = ?")) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

//    public static void main(String[] args) {
//        OrderDAO dao = new OrderDAO();
//        System.out.println("a");
//        List<Order> list = dao.getOrderByAccountID(6, 1, 1);
//        for (Order order : list) {
//            System.out.println("Order details:");
//            System.out.println(order);
//        }
//
//    }
}
