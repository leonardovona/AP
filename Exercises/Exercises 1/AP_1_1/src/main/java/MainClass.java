import java.util.Arrays;

/**
 *
 * @author leonardo
 */
public class MainClass {
    private static final int N_STRINGS = 3;
    public static void main(String args[]) {
        String[] strings = new String[N_STRINGS];
        if (args.length != N_STRINGS) {
            System.err.println("Error: unvalid number of arguments, 3 strings expected");
            return;
        }
        System.arraycopy(args, 0, strings, 0, N_STRINGS);
        Arrays.sort(strings);
        for(int i = 0; i < N_STRINGS; i++)
            if(strings[i].length() % 2 == 0)
                System.out.println(strings[i]);
    }
}
