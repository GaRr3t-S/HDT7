import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class App {

    private static void loadAssociationsFromFile(BinaryTree<Association<String, String>> dictionary, String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // remover parentesis y dividir las associaciones 
                String[] parts = line.replaceAll("[\\(\\)]", "").split(",");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    // crea una nueva asociacion y la añade al arbol
                    Association<String, String> association = new Association<>(key, value);
                    dictionary.insert(association);
                    System.out.println("Loaded association: " + association.key + " -> " + association.value);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + filename);
        }
    }
    public static void main(String[] args) {
        BinaryTree<Association<String, String>> dictionary = new BinaryTree<>(new Association<>("house", "casa"));

        // Cargar las asociaciones desde el archivo diccionario.txt
        loadAssociationsFromFile(dictionary, "diccionario.txt");



        String text = "The woman asked me to do my homework about my town.";
        String[] words = text.split(" ");
        for (String word : words) {
            Association<String, String> translation = dictionary.search(new Association<>(word.toLowerCase(), null));
            if (translation != null) {
                System.out.print(translation.value + " ");
            } else {
                System.out.print("*" + word + "* ");
            }
        }
    }    


}