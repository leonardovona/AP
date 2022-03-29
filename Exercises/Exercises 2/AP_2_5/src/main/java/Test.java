
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */

/**
 *
 * @author leonardo
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    String expectedResult ();
}
