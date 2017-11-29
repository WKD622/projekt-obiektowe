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

public class Document extends AbstractDocument {
    private String name;
    private String introduction;

    public String getName() {
        return this.name;
    }
    public String getIntroduction() {return this.introduction;}

    Document(String documentName,int documentNumber, String documentText){
        this.name=documentName;
        super.number=documentNumber;
        super.text=documentText;
    }

    private void addSection(int sectionNumber, String sectionText){
        super.add(new Section(sectionNumber,sectionText));
    }
}
