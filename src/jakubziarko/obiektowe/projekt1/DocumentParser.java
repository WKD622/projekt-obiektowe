/**
 * DOCUMENT:
 * SECTIONS [u] DZIAŁ 1 DZIAŁ 2 DZIAŁ I/II
 *      Chapters [k|u] Rozdział 1 Rozdział 2
 *          Articles [k|u] Art. 1
 *              Points [k|u] 1. 2.
 *                  SubPoints [k|u] 1) 2) 2a)
 *                      Letters [u] a) b) c)
 */

package jakubziarko.obiektowe.projekt1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DocumentParser {
    private final String sectionRegex = "DZIAŁ\\s*[A-Za-z]*";
    private final String chapterRegex = "Rozdział\\s*[A-Z1-9a-z]*";
    private final String articleRegex = "Art\\.\\s*[0-9a-z]*";
    private final String pointRegex = "[0-9]+[a-z]?\\.";
    private final String subPointRegex = "[0-9]+[a-z]?\\)";
    private final String letterRegex = "[a-z]\\)";
    private String lastReadLine = "";
    private String dividedWord = "";
    private Scanner scanner;

    public DocumentParser(){
    }


    public Document parseDocument(File file) throws IOException {
        this.scanner = new Scanner(file);
        Document document = new Document(file.getName());
        document.setText(parseTextTill(chapterRegex + "|" + sectionRegex, ""));
        //System.out.println("INTRODUCTION:\n" + document.getText());
        parseSections(document);
        scanner.close();
        addArticleRangetoChapters(document);
        return document;
    }

    private void parseSections(Document document) throws IOException {
        while (scanner.hasNextLine()) {
            Section section = new Section();
            document.add(section);
            if (scanner.hasNext(sectionRegex)) {
                section.setNumber(scanner.nextLine().replaceAll("(DZIAŁ)\\s*([1-9A-Z]*)", "$2"));
                section.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex, ""));
                section.setText(section.getText().replaceAll("\n",", "));
            } else {
                section.setNumber("EMPTY");
            }
            //System.out.println("\n\nSECTION: " + section.getNumber() + "\n\n" + section.getText());
            parseChapters(section);
        }
    }

    private void parseChapters(Section section) throws IOException {
        while (!scanner.hasNext(sectionRegex) && scanner.hasNextLine()) {
            Chapter chapter = new Chapter();
            section.add(chapter);
            if (scanner.hasNext(chapterRegex)) {
                chapter.setNumber(scanner.nextLine().replaceAll("(Rozdział)\\s*([1-9a-zA-Z]*)", "$2"));
                chapter.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex, ""));
                chapter.setText(chapter.getText().replaceAll("\n"," "));
            } else {
                chapter.setNumber("EMPTY");
                chapter.setText("EMPTY");
            }
            //System.out.println("\n\nCHAPTER: " + chapter.getNumber() + "\n\n" + chapter.getText());
            parseArticles(chapter);
        }
    }

    private void parseArticles(Chapter chapter) throws IOException {
        while (!scanner.hasNext(sectionRegex) && !scanner.hasNext(chapterRegex) && scanner.hasNextLine()) {
            String string = "";
            Article article = new Article();
            chapter.add(article);
            if (scanner.hasNext(articleRegex)) {
                string = scanner.nextLine();
                article.setNumber(string.replaceAll("(Art\\.)\\s*([0-9a-z]+)\\.(.*)", "$2"));
                if (string.length() - article.getNumber().length() > 6)
                    article.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex + "|" + pointRegex + "|" + subPointRegex, string.replaceAll("(Art\\.)\\s*([0-9a-z]+)\\.\\s(.*)?", "$3")));
                else
                    article.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex + "|" + pointRegex + "|" + subPointRegex, ""));
            } else {
                article.setNumber("EMPTY");
                article.setText("EMPTY");
                scanner.nextLine();
            }
            //System.out.println("\n\nARTICLE: " + article.getNumber() + "\n\n" + article.getText());
            parsePoints(article);
        }
    }

    private void parsePoints(Article article) throws IOException {
        if (lastReadLine.matches("[0-9]+[a-z]?\\. (.*)")){
            Point point = new Point();
            article.add(point);
            point.setNumber(lastReadLine.replaceAll("([0-9]+[a-z]?)\\. (.*)", "$1").trim());
            point.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex + "|" + pointRegex + "|" + subPointRegex, lastReadLine.replaceAll("([0-9]+[a-z]?)\\. (.*)", "$2")));
            //System.out.println("\n\nPoint: " + point.getNumber() + "\n\n" + point.getText());
            lastReadLine = "";
            parseSubPoints(point);
        }
        while (!scanner.hasNext(sectionRegex) &&
                !scanner.hasNext(chapterRegex) &&
                !scanner.hasNext(articleRegex) &&
                scanner.hasNextLine()) {
            String string = "";
            Point point = new Point();
            article.add(point);
            if (scanner.hasNext(pointRegex)) {
                string = scanner.nextLine();
                point.setNumber(string.replaceAll("([0-9]+[a-z]?)\\. (.*)", "$1"));
                point.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex + "|" + pointRegex + "|" + subPointRegex, string.replaceAll("([0-9]+[a-z]?)\\. (.+)", "$2")));
            }
            else{
                point.setNumber("EMPTY");
                point.setText("EMPTY");
            }
            //System.out.println("\n\nPoint: " + point.getNumber() + "\n\n" + point.getText());
            parseSubPoints(point);
        }
    }

    private void parseSubPoints(Point point) {
        while (!scanner.hasNext(sectionRegex)  &&
                !scanner.hasNext(chapterRegex) &&
                !scanner.hasNext(articleRegex) &&
                !scanner.hasNext(pointRegex)   &&
                scanner.hasNextLine()){
            String string = "";
            SubPoint subPoint = new SubPoint();
            point.add(subPoint);
            if(scanner.hasNext(subPointRegex)){
                string = scanner.nextLine();
                subPoint.setNumber(string.replaceAll("([0-9]+[a-z]?)\\) (.*)","$1"));
                subPoint.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex + "|" + pointRegex + "|" + subPointRegex + "|" + letterRegex, string.replaceAll("([0-9]+[a-z]?)\\) (.*)","$2")));
            }
            else {
                subPoint.setNumber("EMPTY");
                subPoint.setText("EMPTY");
            }
            //System.out.println("\n\nSubpoint: " + subPoint.getNumber() + "\n\n" + subPoint.getText());
            parseLetters(subPoint);
        }
    }

    private void parseLetters(SubPoint subPoint){
        while (!scanner.hasNext(sectionRegex)  &&
                !scanner.hasNext(chapterRegex) &&
                !scanner.hasNext(articleRegex) &&
                !scanner.hasNext(pointRegex)   &&
                !scanner.hasNext(subPointRegex) &&
                scanner.hasNextLine()){
            String string = "";
            Letter letter = new Letter();
            subPoint.add(letter);
            if(scanner.hasNext(letterRegex)){
                string = scanner.nextLine();
                letter.setNumber(string.replaceAll("([a-z])\\) (.*)","$1"));
                letter.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex + "|" + pointRegex + "|" + subPointRegex + "|" + letterRegex, string.replaceAll("([a-z])\\) (.*)","$2")));
            }
            else {
                letter.setNumber("EMPTY");
                letter.setText("EMPTY");
                scanner.nextLine();
            }
            //System.out.println("\n\nLetter: " + letter.getNumber() + "\n\n" + letter.getText());
        }
    }

    private String parseTextTill(String regex, String text) {
        StringBuilder ret = new StringBuilder(256);
        if (text.matches("[1-9]\\. (.)*")){
            lastReadLine = text;
            return "";
        }
        if (text != "") {
            if (text.endsWith("-")){
                dividedWord = text.replaceAll("(.*) ([a-zA-Złęćźńąóż]+)-","$2");
                text = text.replaceAll(" [a-zA-Złęćźńąóż]+-","");
            }
            ret.append(text);
            ret.append("\n");
            if (dividedWord != "") {
                ret.append(dividedWord);
                dividedWord = "";
            }
        }
        while (!scanner.hasNext(regex) && scanner.hasNextLine()) {
            String string = scanner.nextLine();
            if (!string.startsWith("©Kancelaria Sejmu") && !string.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                if (string.endsWith("-")){
                    dividedWord = string.replaceAll("(.*) ([a-zA-Złęćźńąóż]+)-","$2");
                    string=string.replaceAll(" [a-zA-Złęćźńąóż]+-","");
                }
                ret.append(string);
                ret.append("\n");
                if (dividedWord != ""){
                    ret.append(dividedWord);
                    dividedWord = "";
                }
            }
        }
        return ret.toString();
    }

    void addArticleRangetoChapters(Document document){
        for (Section sec : document.getList()){
            for (Chapter chap : sec.getList()){
                boolean first = true;
                for (Article art : chap.getList()){
                    String string = art.getNumber().replaceAll("\\p{ASCII}", "");
                    if (first) {
                        first = false;
                        chap.setFirstArticleNumber(art.getNumber());
                    }
                    chap.setLastArticleNumber(art.getNumber());
                }
            }
        }
    }

}