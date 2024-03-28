/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Color {
     private int id;
    private String name;
//    private String code;

    public Color(int id, String name) {
        this.id = id;
        this.name = name;
//        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public String getCode() {
//        return code;
//    }

    @Override
    public String toString() {
       return  name ;
    }
    
}
