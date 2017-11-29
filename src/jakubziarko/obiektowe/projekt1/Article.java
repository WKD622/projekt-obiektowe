package jakubziarko.obiektowe.projekt1;

/**
 * DOCUMENT:
 * SECTIONS [u] DZIAŁ 1 DZIAŁ 2
 *      Chapters [k|u] Rozdział 1 Rozdział 2
 *          Articles [k|u] Art. 1
 *              Points [k|u] 1. 2.
 *                  SubPoints [k|u] 1) 2) 2a)
 *                      Letters [u] a) b) c)
 */

public class Article extends AbstractDocument{

    Article(int articleNumber,String articleText){
        super.text=articleText;
        super.number=articleNumber;
    }

    private void addPoint(int pointNumber, String pointText){
        super.list.add(new Point(pointNumber,pointText));
    }
}