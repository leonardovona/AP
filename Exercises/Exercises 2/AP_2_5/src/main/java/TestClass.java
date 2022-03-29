/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author leonardo
 */

public class TestClass {
    
    @Test(expectedResult = "hello")
    public String test1(){
        return "hello";
    }
    
    @Test(expectedResult = "hello")
    public String test2(){
        return "hi";
    }
    
    @Test(expectedResult = "12")
    public String test3(){
        return Integer.toString(12);
    }
    
    @Test(expectedResult = "10")
    public String test4(){
        return Integer.toString(12);
    }
    
    @Test(expectedResult = "")
    public String test5(){
        return "";
    }
}
