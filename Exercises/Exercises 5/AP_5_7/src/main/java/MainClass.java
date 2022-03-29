
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author leonardo
 */
public class MainClass {
    public static void main(String[] args){
        //System.out.println(serialEvenSum(1000));
        //System.out.println(parallelEvenSum(1000));
        testSum(1);
        testSum(10);
        testSum(100);
        testSum(1000);
        testSum(10000);
        testSum(30000);
        testSum(50000);
        testSum(70000);        
        testSum(100000);
        testSum(1000000);
        
    }
    
    public static long serialEvenSum(long threshold){
        return LongStream.rangeClosed(1, threshold)
                .filter(n -> n % 2 != 0)
                .sum();
    }
    
    public static long parallelEvenSum(long threshold){
        return LongStream.rangeClosed(1, threshold)
                .parallel()
                .filter(n -> n % 2 != 0)
                .sum();
    }
    
    public static void testSum(long threshold){
        System.out.println(threshold + "\n");
        long elapsedTime = 0;
        for(int i = 0; i < 10; i++){
            long startTime = System.nanoTime();
            serialEvenSum(threshold);
            elapsedTime += (System.nanoTime() - startTime); 
        }
        System.out.println("Serial:   " + (elapsedTime/10));
        
        elapsedTime = 0;
        for(int i = 0; i < 10; i++){
            long startTime = System.nanoTime();
            parallelEvenSum(threshold);
            elapsedTime += (System.nanoTime() - startTime); 
        }
        System.out.println("Parallel: " + (elapsedTime/10));
        System.out.println("");
    }
}
