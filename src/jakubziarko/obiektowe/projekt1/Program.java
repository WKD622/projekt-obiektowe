package jakubziarko.obiektowe.projekt1;

import java.io.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String string = "1. <tekst stringa>";
        if (string.matches("\\d+\\w*\\.\\s(.)+")) System.out.println("matches");
        System.out.println("MAIN STARTS");
        /*
        String s = "Hello World! 3+3.0=6";

        // create a new scanner with the specified String Object
        Scanner scanner = new Scanner(s);

        // check if the scanner has a token
        System.out.println("" + scanner.hasNext());

        // print the rest of the string
        System.out.println("" + scanner.nextLine());

        // check if the scanner has a token after printing the line
        System.out.println("" + scanner.hasNext());

        // close the scanner
        scanner.close();
*/

        try {
            File kuba = new File("txt/kuba.txt");
            Parser parser = new Parser(kuba);
            parser.parseDocument(kuba);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try {
            File costitiution = new File("txt/konstytucja.txt");
            Parser parser = new Parser(costitiution);
            parser.parseDocument(costitiution);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
        try {
            File uokik = new File("txt/uokik.txt");
            Parser parser = new Parser(uokik);
            parser.parseDocument(uokik);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

*/
        System.out.println("MAIN ENDS");
    }
}
