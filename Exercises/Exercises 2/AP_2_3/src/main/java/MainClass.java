
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Class bean_class = null;
        try {
            bean_class = Class.forName(bean);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class " + bean + " not found");
            System.exit(-1);
        }
        try {
            System.out.println("Properties:");
            for (PropertyDescriptor pd : Introspector.getBeanInfo(bean_class).getPropertyDescriptors()) {
                System.out.print(pd.getName());
                if (pd.getReadMethod() != null) {
                    if (pd.getWriteMethod() != null) {
                        System.out.println(": Read / Write");
                    } else {
                        System.out.println(": Read");
                    }
                } else if (pd.getWriteMethod() != null) {
                    System.out.println(": Write");
                } else {
                    System.out.println("");
                }
            }
            System.out.println("\nEvents:");
            for (EventSetDescriptor esd : Introspector.getBeanInfo(bean_class).getEventSetDescriptors()) {
                System.out.println(esd.getName());
            }
        } catch (IntrospectionException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
