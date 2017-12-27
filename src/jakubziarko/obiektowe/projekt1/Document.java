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

public class Document extends AbstractComponent<Section> {
    private String name;

    public String getName() {
        return this.name;
    }

    Document(String documentName){
        super();
        this.name=documentName;
    }

    @Override
    public String toString() {
        return "Dokument";
    }

    public void getTableOfContest(){
        int numberOfChars = 17;
        System.out.println("TABLE OF CONTEST: " + this.name + "\n");
        for (Section sec : list){
            if (sec.getNumber() != "EMPTY"){
                System.out.print("  " + sec.toString());
                for (int i =0; i < numberOfChars - sec.toString().length(); i++) System.out.print(".");
                System.out.print(sec.getText());
                System.out.print("\n\n");
            }
            for (Chapter chap : sec.getList()){
                if (chap.getNumber() != "EMPTY") System.out.print("          " + chap.toString());
                for (int i =0; i < numberOfChars - chap.toString().length(); i++) System.out.print(" ");
                System.out.print(chap.getText());
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }
}
