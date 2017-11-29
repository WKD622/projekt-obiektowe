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

public class SubPoint extends AbstractDocument{

    SubPoint(int subPointNumber, String subPointText){
        super.number=subPointNumber;
        super.text=subPointText;
    }

    void addLetter(int letterNumber, String letterText){
        super.list.add(new Letter(letterNumber,letterText));
    }
}
