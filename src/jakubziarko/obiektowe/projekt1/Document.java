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
        return "Dokument " + getName();
    }

    public void showTableOfContest(String sectionNumber){
        System.out.println("\n" + this.toString().toUpperCase() + "\n");
        if (sectionNumber.equals("")){
            for (Section sec : this.getList()) {
                printSectionsAndChapters(sec);
            }
        }
        else{
            for (Section sec : this.getList()) {
                if (sec.getNumber().equals(sectionNumber)){
                    printSectionsAndChapters(sec);
                    return;
                }
            }
            System.out.println("Enter correct data");
        }
    }

    private void printSectionsAndChapters(Section sec){
        int numberOfChars = 17;
        if (sec.getNumber() != "EMPTY") {
            System.out.print("\n  " + sec.toString());
            for (int i = 0; i < numberOfChars - sec.toString().length(); i++) System.out.print(".");
            System.out.print(sec.getText());
            System.out.print("\n\n");
        }
        for (Chapter chap : sec.getList()) {
            if (chap.getNumber() != "EMPTY") {
                System.out.print("          " + chap.toString());
                for (int i = 0; i < numberOfChars - chap.toString().length(); i++) System.out.print(" ");
                System.out.print(chap.getText());
                System.out.print("(Art. " + chap.getFirstArticleNumber() + "-" + chap.getLastArticleNumber() + ")\n");
            }
        }
    }

    public void viewRangeOfArticles(String startArticle, String endArticle) {
        boolean first = false;
        boolean last = false;

        for (Section sec : this.getList()) {
            for (Chapter chap : sec.getList()) {
                for (Article art : chap.getList()) {
                    if (art.getNumber().equals(startArticle)) first = true;
                    if (art.getNumber().equals(endArticle)) last = true;
                }
            }
        }
        if (first && last) {
            System.out.println("\n" + this.toString().toUpperCase() + "  ARTYKUŁY Z ZAKRESU: " + startArticle + "-" + endArticle + "\n");
            first = false;
            for (Section sec : this.getList()) {
                for (Chapter chap : sec.getList()) {
                    for (Article art : chap.getList()) {
                        if (art.getNumber().equals(startArticle)) {
                            first = true;
                        }
                        if (first) {
                            System.out.println(art.toString() + "\n" + art.getText());
                            viewAllPointsFor(art);
                        }
                        if (art.getNumber().equals(endArticle)) {
                            return;
                        }
                    }
                }
            }
        }
        else{
            System.out.println("Enter correct range of articles");
        }
    }

    public void showComponent(String articleNumber,String pointNumber, String subPointNumber, String letterNumber) {
        if (articleNumber != "") {
            for (Section sec : this.getList()) {
                for (Chapter chap : sec.getList()) {
                    for (Article art : chap.getList()) {
                        if (art.getNumber().equals(articleNumber)) {
                            if ((pointNumber.equals("") && !subPointNumber.equals("")) || !pointNumber.equals("")) {
                                searchForPoint(art, pointNumber, subPointNumber, letterNumber);
                                return;
                            }
                            else{
                                System.out.println(art.toString() +"\n" + art.getText());
                                viewAllPointsFor(art);
                                return;
                            }
                        }
                    }
                }
            }
            System.out.println("Incorrect article");
        }
    }

    private void searchForPoint(Article article, String pointNumber, String subPointNumber, String letterNumber) {
        if (pointNumber.equals("") && !subPointNumber.equals("")) {
            for (Point point : article.getList()) {
                if (point.getNumber().equals("EMPTY")) {
                    searchForSubPoint(point, subPointNumber, letterNumber);
                    return;
                }
            }
        } else if (!pointNumber.equals("")) {
            for (Point point : article.getList()) {
                if (point.getNumber().equals(pointNumber)) {
                    if (!subPointNumber.equals("")) {
                        searchForSubPoint(point, subPointNumber, letterNumber);
                        return;
                    } else {
                        System.out.println(point.toString() + "\n" + point.getText());
                        viewAllSubPointFor(point);
                        return;
                    }
                }
            }
            System.out.println("Incorrect point");
        }
    }

    private void searchForSubPoint(Point point, String subPointNumber, String letterNumber) {
        if (!subPointNumber.equals("")) {
            for (SubPoint subPoint : point.getList()) {
                if (subPoint.getNumber().equals(subPointNumber)) {
                    if (!letterNumber.equals("")){
                        searchForLetter(subPoint, letterNumber);
                        return;
                    }
                    else {
                        System.out.println(subPoint.toString() + "\n" + subPoint.getText());
                        viewAllLetterFor(subPoint);
                        return;
                    }
                }
            }
            System.out.println("Incorrect subpoint");
        }
    }

    private void searchForLetter(SubPoint subPoint, String letterNumber){
        for (Letter letter : subPoint.getList()){
            if (letter.getNumber().equals(letterNumber)){
                System.out.println(letter.toString() + "\n" + letter.getText());
                return;
            }
        }
        System.out.println("Incorrect letter");
    }


    public void viewSpecifiedChapter(String chapterNumber){
        for (Section sec : this.getList())
            for (Chapter chap : sec.getList())
                if (chap.getNumber().equals(chapterNumber)) {
                    System.out.println(chap.toString() + "\n" + chap.getText());
                    viewAllArticlesFor(chap);
                    return;
                }
        System.out.println("Enter correct chapter number");
    }

    private void viewAllArticlesFor(Chapter chapter){
        for (Article article : chapter.getList()){
            if (!article.getNumber().equals("EMPTY")) System.out.println(article.toString() + "\n" + article.getText());
            viewAllPointsFor(article);
        }
    }

    private void viewAllPointsFor(Article article){
        for (Point point : article.getList()){
            if (!point.getNumber().equals("EMPTY")) System.out.println(point.toString() + "\n" + point.getText());
            viewAllSubPointFor(point);
        }
    }

    private void viewAllSubPointFor(Point point){
        for (SubPoint subPoint : point.getList()){
            if (!subPoint.getNumber().equals("EMPTY")) System.out.println(subPoint.toString() + "\n" + subPoint.getText());
            viewAllLetterFor(subPoint);
        }
    }

    private void viewAllLetterFor(SubPoint subPoint){
        for (Letter letter : subPoint.getList()){
            if (!letter.getNumber().equals("EMPTY")) System.out.println(letter.toString() + "\n" + letter.getText());
        }
    }
}




