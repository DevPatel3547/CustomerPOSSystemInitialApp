import java.sql.*;
import javax.naming.spi.DirStateFactory.Result;
import java.awt.*;  
import javax.swing.*; 
import java.io.*;
import java.util.*;

public class Drink {
    private String name;
    //Size values: 0 for regular, 1 for large
    private int size;
    //Milk values: 0 for Organic, 1 for Soy, 2 for Oat
    private int milk;
    //Ice values: 0 for no ice, 1 for less ice, 2 for regular ice, 3 for extra ice
    private int ice;
    //Sugar values: 0 for no sugar, 1 for 25% sugar, 2 for 50% sugar, 3 for 75% sugar, 4 for 100% sugar
    private int sugar;
    //Boba values: 0 for no boba, 1 for less boba, 2 for regular boba, 3 for extra boba, 4 for extra extra boba
    private int boba;
    //Toppins - DO NOT MESS UP THE SPELLING OF TOPPINGS
    //First letter of each word is capitalized
    // "Coconut Jelly", "Snow Velvet", "Rainbow Jelly", "Brown Sugar Boba", "Crystal Boba", "Creme Brulee"
    private ArrayList<String> toppings; 

    public Drink() {
        name = "";
        size = milk = ice = sugar = boba = 0;
        toppings = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }
    public void setName(String s) {
        name = s;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int i) {
        size = i;
    }

    public int getMilk() {
        return milk;
    }
    public void setMilk(int i) {
        milk = i;
    }
    
    public int getIce() {
        return ice;
    }
    public void setIce(int i) {
        ice = i;
    }

    public int getSugar() {
        return sugar;
    }
    public void setSugar(int i) {
        sugar = i;
    }

    public int getBoba() {
        return boba;
    }
    public void setBoba(int i) {
        boba = i;
    }

    public void addTopping(String topping) {
        toppings.add(topping);
    }
    public void removeTopping(String topping) {
        toppings.remove(topping);
    }
    public ArrayList<String> getToppings() {
        return toppings;
    }

}