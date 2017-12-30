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

public class Chapter extends AbstractComponent<Article> {
    private String firstArticleNumber;
    private String lastArticleNumber;

    Chapter(){
        super();
        firstArticleNumber = "";
        lastArticleNumber = "";
    }

    public String getFirstArticleNumber() {
        return firstArticleNumber;
    }

    public String getLastArticleNumber() {
        return lastArticleNumber;
    }

    public void setFirstArticleNumber(String firstArticleNumber) {
        this.firstArticleNumber = firstArticleNumber;
    }

    public void setLastArticleNumber(String lastArticleNumber) {
        this.lastArticleNumber = lastArticleNumber;
    }

    @Override
    public String toString() {
        if (this.getNumber().equals("EMPTY")) return "Rozdział -";
        else return "Rozdział " + this.getNumber();
    }
}