/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author leonardo
 */
public class ImmutablePair<T1, T2> {
    private T1 first_element;
    private T2 second_element;
    
    public ImmutablePair(T1 first_element, T2 second_element){
        this.first_element = first_element;
        this.second_element = second_element;
    }
    
    public T1 getFirst(){
        return first_element;
    }
    
    public T2 getSecond(){
        return second_element;
    }
}
