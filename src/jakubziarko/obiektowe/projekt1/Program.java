package jakubziarko.obiektowe.projekt1;

import java.io.File;
import java.io.IOException;

public class Program {

    /*
    Pod args[0] jest zawsze ścieżka do pliku więc linię zaczynamy zawsze od ścieżki do pliku
    np. /txt/konstytucja.txt
    albo /txt/uokik.txt
    jeśli chcemy wyświetlić spis treści wpisujemy po nazwie pliku tableofcontent
    np. /txt/konstytucja.txt tableofcontent
    jeśli chcemy wyświetlić spis treści określonego działu wpisujemy na końcu dz.* (gdzie * jest dowolonym numerem działu)
    np. /txt/konstytucja.txt tableofcontent dz.3
    jeśli chcemy wyświetlić konkretny rozdział wpisujemy po ścieżce do pliku rdz.* (gdzie * jest dowolonym numerem rozdziału)
    np. /txt/konstytucja.txt rdz.6
    jeśli chcemy wyświetlić zakres artykułów po ścieżce do pliku wpisujemy art.*-^ (gdzie * numerem artykułu startowego, a ^ numerem artykułu końcowego)
    np. /txt/konstytucja.txt art.67a-113j
    jeśli chcemy wyświetlić konkretny element, np artykuł 5, ustęp 3, punkt 2, litera c wpiszemy
    np. /txt/konstytucja.txt art.5 ust.5 pkt.2 lit.c
    czasami artykuł nie ma ustępów tylko od razu punkty, wtedy po prostu pomijamy ustep, piszemy
    np. /txt/konstytucja.txt art.5 pkt.2 lit.d
    mozemy tez wyswietlic okreslony ustep,punkt lub artykuł piszac po prostu
    np. /txt/konstytucja.txt art.5 ust.2 pkt.4
    lub
    /txt/konstytucja.txt art.5 ust.2
    lub
    /txt/konstytucja.txt art.5

    JESLI NUMER SZUKANEGO ELELEMNTU JEST RZYMSKI WPISUJEMY RZYMSKI, JESLI ARABSKI TO ARABSKI.

    Nazewnictwo które przyjąłem:
    Document - cały dokument (przechowuje treść wstępu)
    Section - dział
    Chapter - rozdział
    Article - artykuł
    Point - ustęp
    Subpoint - punkt
    letter - litera

    a więc jest: Document (przechowuje listę sections) -> Section(posiada liste chapterów) -> chapter (posiada listę articles)
    -> article (posiada listę points) -> point (posiada listę subpoints) -> subpoint (posiada listę letters)
    to zaimplementowałem dzięki klasie abstrakcyjnej AbstractComponent z typem generycznym który motywuje co będzie
    przechowywane w liście.

    DocumentParser ma w sobie główną metodę parseDocument która bierze za argument ścieżkę do pliku i parsuje go do
    formy obiektowej wykorzystując inne prywatne metody i klase scanner. Wtedy też usuwane są z pliku wszystkie rzeczy, które
    miały być usuwane, w tym przejścia do nowej linii włącznie z przenieniem słowa linikę niżej.

    Wszystkie metody wykorzystywane do obsługi pliku, tj. wyświetlania konkretnych jego fragmentów znajdują się w klasie Document.
     */

    public static void main(String[] args) {
        final String fileRegex = "(.+\\.txt)";
        final String sectionRegex = "dz\\.([A-Z0-9]+)";
        final String chapterRegex = "rdz\\.([A-Z0-9]+)";
        final String articleRegex = "art\\.([0-9]+[a-z]?\\-?[0-9]*[a-z]?)";
        final String pointRegex = "ust\\.([0-9]+[a-z]?)";
        final String subPointRegex = "pkt\\.([0-9]+[a-z]?)";
        final String letterRegex = "lit\\.([a-z])";
        final String articleRange = "([0-9]+[a-z]?)\\-([0-9]+[a-z]?)";
        final String tableOfContent = "tableofcontent";
        String filePath = "";
        String sectionNumber = "";
        String chapterNumber = "";
        String articleNumber = "";
        String pointNumber = "";
        String subPointNumber = "";
        String letterNumber = "";
        String articleRangeStart = "";
        String articleRangeEnd = "";
        boolean tableOfContentBool = false;

        if (args[0].matches(fileRegex)) filePath = args[0];

        for (String arg : args){
            if (arg.matches(chapterRegex)){
                chapterNumber = arg.replaceAll(chapterRegex,"$1");
            }
            if (arg.matches(articleRegex)){
                articleNumber = arg.replaceAll(articleRegex,"$1");
                if (articleNumber.matches(articleRange)){
                    articleRangeStart = articleNumber.replaceAll(articleRange, "$1");
                    articleRangeEnd = articleNumber.replaceAll(articleRange, "$2");
                }
            }
            if (arg.matches(pointRegex)){
                pointNumber = arg.replaceAll(pointRegex,"$1");
            }
            if (arg.matches(subPointRegex)){
                subPointNumber = arg.replaceAll(subPointRegex,"$1");
            }
            if (arg.matches(letterRegex)){
                letterNumber = arg.replaceAll(letterRegex, "$1");
            }
            if (arg.matches(tableOfContent)){
                tableOfContentBool = true;
            }
            if (arg.matches(sectionRegex)){
                sectionNumber = arg.replaceAll(sectionRegex, "$1");
            }
        }

        DocumentParser documentParser = new DocumentParser();
        try {
            Document document = documentParser.parseDocument(new File(filePath));
            if (!chapterNumber.equals("") && args.length==2) document.viewSpecifiedChapter(chapterNumber);
            else if (!articleRangeStart.equals("") && args.length==2) document.viewRangeOfArticles(articleRangeStart,articleRangeEnd);
            else if (!articleNumber.equals("") && args.length <= 5 && !tableOfContentBool && sectionNumber.equals("") && chapterNumber.equals("")) document.showComponent(articleNumber,pointNumber,subPointNumber,letterNumber);
            else if (tableOfContentBool && ((sectionNumber.equals("") && args.length == 2) || (args.length == 3 && !sectionNumber.equals(""))) && chapterNumber.equals("") && articleNumber.equals("")
                    && pointNumber.equals("") && subPointNumber.equals("") && letterNumber.equals("")){
                document.showTableOfContest(sectionNumber);
            }
            else {
                System.out.println("Enter correct arguments");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
