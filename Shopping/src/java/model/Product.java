/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private int category;
    private int quantity;
    private String image;
    private String description;
    private String categoryName;
    int colorId;
    int sizeId;
    String sizeName;
    String colorName;
    List<String> sizes;
    List<String> colors;
    List<Color> listColor;
    List<Size> listSize;

    public List<Color> getListColor() {
        return listColor;
    }

    public void setListColor(List<Color> listColor) {
        this.listColor = listColor;
    }

    public List<Size> getListSize() {
        return listSize;
    }

    public void setListSize(List<Size> listSize) {
        this.listSize = listSize;
    }
    private List<ProductVariant> variants;

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Product(int id, String name, double price, int category, int quantity, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
    }

    public Product(int id, String name, double price, String category, int quantity, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryName = category;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
    }

    public Product(String name, double price, int quantity, String image, int category, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.category = category;
        this.description = description;
    }

    public Product(int id, String name, double price, int category, int quantity, String image, String description, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.categoryName = categoryName;
    }

    public Product() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // toString() method
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", quantity=" + quantity + ", image=" + image + ", description=" + description + ", categoryName=" + categoryName + ", colorId=" + colorId + ", sizeId=" + sizeId + ", sizeName=" + sizeName + ", colorName=" + colorName + ", sizes=" + sizes + ", colors=" + colors + ", variants=" + variants + '}';
    }


}
