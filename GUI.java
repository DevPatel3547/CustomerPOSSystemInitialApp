import java.awt.*;  
import javax.swing.*;
import java.io.*;
import java.util.*;

public class GUI {
    public static void main(String[] args) {

        Database database = new Database();
        database.openJDBC();







        database.closeJDBC();
        return;
    }
}