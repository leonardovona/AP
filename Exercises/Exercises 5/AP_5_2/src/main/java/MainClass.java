
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

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
        List<Double> l = Arrays.asList(0.2, 25.0, -55.0, Math.PI / 2, Math.PI, 33.0, 101.22, 100.0, 10.0, 10.24);
        ImmutablePair<Integer, Double> pair1 = someCalculation1(l);
        System.out.println(pair1.getFirst() + " " + pair1.getSecond());
        ImmutablePair<Integer, Double> pair2 = someCalculation2(l);
        System.out.println(pair2.getFirst() + " " + pair2.getSecond());
        
    }
    
    static ImmutablePair<Integer, Double> someCalculation1(List<Double> lst){
        Integer first_element = 0;
        int aux_counter = 0;
        double second_element = 0;
        for(Double elem: lst){
            if(elem >= 0.2 && elem <= Math.PI)
                first_element++;
            if(elem >= 10 && elem <= 100){
                second_element += elem;
                aux_counter++;
            }
        }
        return new ImmutablePair<>(first_element, second_element / aux_counter);
    }
    
    static ImmutablePair<Integer, Double> someCalculation2(List<Double> lst){
        Integer first_element = (int) lst.stream().filter(elem -> elem >= 0.2 && elem <= Math.PI).count();
        OptionalDouble second_element = lst.stream().filter(elem -> elem >= 10 && elem <= 100).mapToDouble(elem -> elem).average();
        return new ImmutablePair<>(first_element, second_element.getAsDouble());
    }
}
