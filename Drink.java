import java.sql.*;
import javax.naming.spi.DirStateFactory.Result;
import java.awt.*;  
import javax.swing.*; 
import java.io.*;
import java.util.*;

public class Drink {
    private int id;
    private String name;
    //Size values: 0 for regular, 1 for large
    private String size;
    //Milk values: 0 for Organic, 1 for Soy, 2 for Oat
    private String milk;
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

    private String ingredients;
    
    private double flavorPrice;
    private double totalPrice;

    public Drink() {
        name = size = milk = ingredients = "";
        ice = sugar = boba = 0;
        id = 0;
        flavorPrice = totalPrice = 0.0;
        toppings = new ArrayList<String>();
    }

    public Drink(String drinkName) {
        name = drinkName;
        size = milk = ingredients = "";
        ice = sugar = boba = 0;
        id = 0;
        flavorPrice = totalPrice = 0.0;
        toppings = new ArrayList<String>();
    }

    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String s) {
        ingredients = s;
    }

    public double getFlavorPrice() {
        return flavorPrice;
    }
    public void setFlavorPrice(double price) {
        flavorPrice = price;
        totalPrice = flavorPrice;
    }

    public double getTotalPrice() {
        totalPrice = totalPrice * 100;
        totalPrice = Math.floor(totalPrice);
        totalPrice = totalPrice / 100;

        return totalPrice;
    }
    public void setTotalPrice(double price) {
        totalPrice = price;
    }

    public int getId() {
        return id;
    }
    public void setId(int i) {
        id = i;
    }

    public String getName() {
        return name;
    }
    public void setName(String s) {
        name = s;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String i) {
        size = i;
        if (size.equals("Large")) totalPrice += 1.0;
    }

    public String getMilk() {
        return milk;
    }
    public void setMilk(String i) {
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
        if (boba == 3) totalPrice += 0.60;
        if (boba == 4) totalPrice += 1.20;
    }

    public void addTopping(String topping, double price) {
        toppings.add(topping);
        totalPrice += price;
    }
    public void removeTopping(String topping) {
        toppings.remove(topping);
    }
    public String getToppings() {
        String str = "";
        if (toppings.size() == 0) return "";
        for (int i = 0; i < toppings.size() - 1; ++i) {
            str += toppings.get(i) + ", ";
        }
        str += toppings.get(toppings.size() - 1);
        return str;
    }
    public ArrayList<String> getToppingList() {
        return toppings;
    }

    public String toString() {
        String str = "Name: " + name + "\nFlavor Price: " + flavorPrice + "\nTotal Price: " + getTotalPrice() + "\n Toppings: ";
        for (int i = 0; i < toppings.size(); ++i) {
            str += toppings.get(i) + ", ";
        }
        return str += "\n-------------------";
    }

}