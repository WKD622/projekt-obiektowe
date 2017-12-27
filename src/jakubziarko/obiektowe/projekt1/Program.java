package jakubziarko.obiektowe.projekt1;

import java.io.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
            File costitiution = new File("txt/konstytucja.txt");
            Document document1 = parser.parseDocument(costitiution);
            document1.getTableOfContest();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            File uokik = new File("txt/uokik.txt");
            Document document2 = parser.parseDocument(uokik);
            document2.getTableOfContest();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
