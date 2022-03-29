
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class MainClass {
    public static void main(String[] args){
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(15);
        l.add(3);
        l.add(25);
        l.add(2);
        l.add(-12);
        l.add(-17);
        l.add(41);
        System.out.println(sumOdd1(l));
        System.out.println(sumOdd2(l));
    }
    
    static <E extends Integer> int sumOdd1(List<E> l){
        int sum = 0;
        for(E elem: l){
            if(elem % 2 != 0)
                sum += elem;
        }
        return sum;
    }
    
    static Integer sumOdd2(List<Integer> l){
        int sum = 0;
        return l.stream().filter(elem -> elem % 2 != 0).reduce(sum, Integer::sum);
    }
}
