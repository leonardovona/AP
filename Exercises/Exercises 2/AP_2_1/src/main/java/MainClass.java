/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author leonardo
 */
public class MainClass {
    public static void main(String args[]){
        TempLabel t = new TempLabel();
        t.setText("0");
        System.out.println("0, " + t.getText());
        t.setText("1");
        System.out.println("1, " + t.getText());
        t.setText("-1");
        System.out.println("-1, " + t.getText());
        t.setText("25.7");
        System.out.println("25.7, " + t.getText());
        t.setText("-31.22");
        System.out.println("-31.22, " + t.getText());
    }
}
