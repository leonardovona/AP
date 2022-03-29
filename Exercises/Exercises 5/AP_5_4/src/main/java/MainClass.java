
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {
    public static void main(String[] args){
        System.out.println(titlecase2("ciao ciao mondo ciao ciao Mondo prova AAA"));
    }
    
    public static String titlecase1(String s){
        String[] s1 = s.split(" ");
        String s2 = "";
        for(int i = 0; i < s1.length; i++){
            s2 += s1[i].substring(0, 1).toUpperCase() + s1[i].substring(1) + " ";
        }
        return s2.trim();
    }
    
    public static String titlecase2(String s){
        return Stream.of(s.split(" "))
                .map(elem -> elem.substring(0, 1).toUpperCase() + elem.substring(1))
                .collect(Collectors.joining(" "));
    }
}
