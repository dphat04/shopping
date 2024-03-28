/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DBContext {

    public Account getAccountByUsernameAndPassword(String username, String password) {
        Account account = null;
        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String fullname = resultSet.getString("fullname");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    int status = resultSet.getInt("Status");
                    int role = resultSet.getInt("role");

                    account = new Account(id, username,fullname, password, email, createdAt, status, role);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return account;
    }

    public Account getAccountByID(int id) {
        Account account = null;
        String query = "SELECT * FROM accounts WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    Timestamp create_at = rs.getTimestamp("created_at");
                    int status = rs.getInt("Status");
                    int role = rs.getInt("role");

                    account = new Account(id, username, password, fullname, email, address, create_at, status, role);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return account;
    }

    public int getTotalAccountCount() {
        int count = 0;
        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM Accounts"); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Loi "+e);
        }
        return count;
    }

    public List<Account> getAccountToManage(int page, int pageSize) {
        List<Account> accounts = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT *\n"
                        + "FROM Accounts\n"
                        + "ORDER BY id\n"
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;")) {
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setEmail(rs.getString("email"));
                    account.setCreatedAt(rs.getTimestamp("created_at"));
                    account.setStatus(rs.getInt("Status"));
                    account.setRole(rs.getInt("role"));
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void updateAccountStatus(int accountId) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Accounts SET status = (CASE WHEN status = 1 THEN 0 ELSE 1 END) WHERE id = ?")) {
            stmt.setInt(1, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addAccount(Account account) {
        boolean success = false;
        String query = "INSERT INTO accounts (username, password, fullname, email, address) VALUES (?, ?, ?, ?, ?)";

        try (
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getFullname());
            statement.setString(4, account.getEmail());
            statement.setString(5, account.getAddress());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateAccount(int userId, String name, String email, String address) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE accounts SET fullname = ?, email = ?, address = ? WHERE id = ?")) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, address);
            stmt.setInt(4, userId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updatePassword(int userId, String newPassword) {
        try (
             PreparedStatement stmt = connection.prepareStatement("UPDATE accounts SET password = ? WHERE id = ?")) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.updatePassword(11, "11"));
//        System.out.println(dao.updateAccount(1, "Thai", "thai@gmail.com", "HN"));
//        System.out.println(a);
    }
}
