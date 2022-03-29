
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author leonardo
 */
public class MainClass {

    public static void main(String[] args) throws IOException {
        listDir(".").forEach(System.out::println);
    }

    public static List<String> listDir(String dir) throws IOException {
        return Files.walk(Paths.get(dir))
                .filter(Files::isDirectory)
                .map(Object::toString)
                .collect(Collectors.toList());
        
    }
}
