
import java.util.Optional;

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
        Integer[] array = {1,2,3,4,5,6,7,8,9,-1};
        System.out.println(sqrtOfHalf(array, 9));     
    }
    
    public static <T> Optional<T> getElement(T[] array, Integer index){
        return index >= array.length ? Optional.empty() : Optional.of(array[index]);
    }
    
    public static Optional<Double> sqrt(Integer num){
        return num < 0 ? Optional.empty() : Optional.of(Math.sqrt(num));
    }
    
    public static Optional<Integer> half(Integer num){
        return num % 2 != 0 ? Optional.empty() : Optional.of(num / 2);
    }
    
    public static Optional<Double> sqrtOfHalf(Integer[] array, int index){
        Optional<Integer> elem = getElement(array, index);
        Optional<Double> elem1 = Optional.empty();
        if (elem.isPresent())
            elem = half(elem.get());
         if(elem.isPresent())
             elem1 = sqrt(elem.get());
         return elem1;
    }
}
