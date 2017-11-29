package jakubziarko.obiektowe.projekt1;


import java.io.*;

public class Program {
    public static void main(String args[]) {
        System.out.println("MAIN STARTS");
        TxtAnalizer analizer = new TxtAnalizer();
        try {
            analizer.analizeDocument(new File("txt/konstytucja.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("MAIN ENDS");
    }
}




            /*
            File fileDir = new File("txt/konstytucja.txt");

            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                FileReader fr = new FileReader("txt/konstytucja.txt");
                BufferedReader br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) System.out.println(str + " ");
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File not found");
            }
    */

            /*
            try {
                FileWriter fw = new FileWriter("ala.txt");
                PrintWriter pw = new PrintWriter(fw);

                pw.println("sialalalalbabla");
                pw.println("never say never");

                pw.close();
            } catch (IOException e) {
                System.out.println("Error!");
            }


            try {
                FileReader fr = new FileReader("ala.txt");
                BufferedReader br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) System.out.println(str + " ");
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File not found");
            }
    */