package jakubziarko.obiektowe.projekt1;

import java.io.*;

public class TxtAnalizer {

    public void analizeDocument(File file) throws IOException {
        Document document = new Document(file.getName(), 1, "");
        BufferedReader in = null;

        in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        String str;
        while ((str = in.readLine()) != null && !str.startsWith("Art.") && !str.startsWith("DZIAŁ") && !str.startsWith("Rozdział")){
            System.out.println(str);
        }

    }
    private void analizeSections(){

    }

    private void analizeChapters(){

    }

    private void analizeArticles(){

    }

    private void analizePoints(){

    }

    private void analizeSubPoints(){

    }

    private void analizeLetters(){

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
