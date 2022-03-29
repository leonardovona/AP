
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author leonardo
 */
public class MainClass {

    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("Unvalid number of arguments");
            System.exit(-1);
        }
        String bean = args[0];
        Class c = null;
        try {
            c = Class.forName(bean);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class " + bean + " not found");
            System.exit(-1);
        }
        try {
            Object obj = c.getDeclaredConstructor().newInstance();
            Method[] methods = c.getMethods();
            for (Method m : methods) {
                Test tc = (Test) m.getAnnotation(Test.class);
                if (tc != null) {
                    String expectedResult = tc.expectedResult();
                    String result = (String) m.invoke(obj);
                    if (expectedResult.equals(result)) {
                        System.out.println(m.getName() + ": OK");
                    } else {
                        System.out.println(m.getName() + ": KO");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
