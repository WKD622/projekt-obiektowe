package jakubziarko.obiektowe.projekt1;

import java.io.*;

public class Program {
    public static void main(String args[]) {
        System.out.println("MAIN STARTS");
        TxtAnalizer analizer = new TxtAnalizer();
        try {
            analizer.analizeDocument(new File("txt/konstytucja.txt"));
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            analizer.analizeDocument(new File("txt/uokik.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("MAIN ENDS");
    }
}