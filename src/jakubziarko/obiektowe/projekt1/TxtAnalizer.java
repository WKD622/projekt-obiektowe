package jakubziarko.obiektowe.projekt1;

import java.io.*;
import java.util.List;
import java.util.Iterator;

public class TxtAnalizer {

    public void analizeDocument(File file) throws IOException {

        //PLIKI
        Document document = new Document(file.getName(), 1, "");
        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        //PLIKI KONIEC

        //PRZEPISANIE WSTEPU JESLI ISTNIEJE
        String str;
        String strOut="";
        while ((str = in.readLine()) != null && !str.startsWith("DZIAŁ") && !str.startsWith("Rozdział")){
            strOut=strOut.concat(str);
            //System.out.println(stro);
            if (strOut.endsWith("-")){
                strOut=strOut.substring(0,strOut.length()-1);
            }
            else strOut=strOut.concat(" ");
        }
        document.addText(strOut);
        //PRZEPISANIE WSTEPU JESLI ISTNIEJE KONIEC


        /**
         * Jeśli następną liniką jest Rozdział to zakładm że cały dokument jest wielkim DZIAŁEM 1, i zaczynam realizować
         * sprawdzanie kolejnych rodziałów. Jeśli nie to zaczynam zliczać DZIAŁY i wpisywać ich tekst;
         */
        if (str.startsWith("Rozdział")){
            Section section = new Section(1,"");
            document.getList().add(section);
            analizeSection(0,in,str, (Section)document.getList().get(0));
        }else{
            int numberSections=1;
            while ((str = in.readLine()) != null) {
                if (str.startsWith("DZIAŁ")) {
                    Section section = new Section(numberSections,"");
                    document.getList().add(section);
                    analizeSection(numberSections,in,str,(Section)document.getList().get(numberSections-1));
                    numberSections++;
                }
            }
        }
    }



    private void analizeSection(int sectionNumber, BufferedReader in, String str, Section section){
        analizeChapter();
    }

    private void analizeChapter(){
        analizeArticle();
    }

    private void analizeArticle(){
        analizePoint();
    }

    private void analizePoint(){
        analizeSubPoint();
    }

    private void analizeSubPoint(){
        analizeLetter();
    }

    private void analizeLetter(){

    }
    /**
     * konstytucja
     * Rozdział I
     *     Art. 1
     *          1.
     *              1)
     *              2)
     *              3)
     *          2.
     *              1)
     *              2)
     *      Art. 2
     * Rozdział II
     *      Art. 3
     *          1.
     *          2.
     *      Art. 4
     *          1.
     *          2.
     *          3.
     *              1)
     *              2)
     *              3)
     *              4)
     *          4.
     *
     *
     * uokik
     * DZIAŁ 1
     *      Rozdział 1
     *          Art. 1
     *              1.
     *              2.
     *                  1)
     *                  2)
     *                  2a)
     *                      a)
     *                      b)
     *                      c)
     *              3.
     *      Rozdział 2
     *          Art. 2
     *              1.
     *              2.
     * DZIAŁ 2
     *      Rozdział 1
     *          Art. 3
     *              1.
     *                  1)
     *                  1a)
     *                  1b)
     *                      a)
     *                      b)
     *                      c)
     *                  2)
     *                  3)
     *              2.
     *              3.
     *
     * SECTIONS [u] DZIAŁ 1 DZIAŁ 2
     *      Chapters [k|u] Rozdział 1 Rozdział 2
     *          Articles [k|u] Art. 1
     *              Points [k|u] 1. 2.
     *                  SubPoints [k|u] 1) 2) 2a)
     *                      Letters [u] a) b) c)
     */
}
