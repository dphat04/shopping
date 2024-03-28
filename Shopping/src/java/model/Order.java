/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
import java.sql.Timestamp;

public class Order {

    private int id;
    private int accountId;
    private int productId;
    private int sizeId;
    private int colorId;
    private String sizeNmae;
    private String colorName;
    private int quantity;
    private double price;
    private Timestamp createdAt;
    private int status;
    private String customerFullName;
    private String productName;
    

    public Order() {
    }

    
    public Order(int id,  String customerFullName,String productName, int quantity, double price, Timestamp createdAt, int status) {
        this.id = id;
        this.customerFullName = customerFullName;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
        this.status = status;
    }

    
    public Order(int id, int accountId, int productId, int quantity, double price, Timestamp createdAt, int status) {
        this.id = id;
        this.accountId = accountId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Order(int accountId, int productId, int sizeId, int colorId, int quantity, double price) {
        this.accountId = accountId;
        this.productId = productId;
        this.sizeId =sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
        this.price = price;
    
    }
    public Order(int accountId, int productId, int quantity, double price, Timestamp createdAt) {
        this.accountId = accountId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Order(int accountId, int productId, int quantity, double price) {

        this.accountId = accountId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;

    }

    // Getters and setters

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getSizeNmae() {
        return sizeNmae;
    }

    public void setSizeNmae(String sizeNmae) {
        this.sizeNmae = sizeNmae;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
    
    
    public int getId() {
        return id;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // toString() method

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", accountId=" + accountId + ", productId=" + productId + ", quantity=" + quantity + ", price=" + price + ", createdAt=" + createdAt + ", status=" + status + ", customerFullName=" + customerFullName + ", productName=" + productName + '}';
    }
    

}
