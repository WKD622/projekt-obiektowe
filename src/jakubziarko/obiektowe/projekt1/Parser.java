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

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Parser {
    private final String sectionRegex = "DZIAŁ\\s*[A-Za-z]*";
    private final String chapterRegex = "Rozdział\\s*[A-Z1-9a-z]*";
    private final String articleRegex = "Art\\.\\s*[0-9a-z]*";
    private final String pointRegex = "\\d+\\w*\\.\\s(.)+";
    private Scanner scanner;
    private String endOfLine = "";
    int i = 0;

    public Parser(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    public Parser(InputStream stream) {
        this.scanner = new Scanner(stream);
    }

    public Document parseDocument(File file) throws IOException {
        Document document = new Document(file.getName());
        document.setText(parseTextTill(chapterRegex + "|" + sectionRegex, ""));

        System.out.println("INTRODUCTION:\n" + document.getText());
        parseSections(document);
        scanner.close();
        return document;
    }

    private void parseSections(Document document) throws IOException {
        while (scanner.hasNextLine()) {
            Section section = new Section();
            document.add(section);
            if (scanner.hasNext(sectionRegex)) {
                section.setNumber(scanner.nextLine().replaceAll("(DZIAŁ)\\s*([1-9A-Z]*)", "$2"));
                section.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex, ""));
            } else {
                section.setNumber("EMPTY");
            }
            System.out.println("\n\nSECTION: " + section.getNumber() + "\n\n" + section.getText());
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
            } else {
                chapter.setNumber("EMPTY");
            }
            System.out.println("\n\nCHAPTER: " + chapter.getNumber() + "\n\n" + chapter.getText());
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
                    article.setText(parseTextTill("(" + chapterRegex + ")|(" + sectionRegex + ")|(" + articleRegex + ")|(" + pointRegex + ")", string.replaceAll("(Art\\.)\\s*([0-9a-z]+)\\.\\s(.*)?", "$3")));
                else
                    article.setText(parseTextTill("(" + chapterRegex + ")|(" + sectionRegex + ")|(" + articleRegex + ")|(" + pointRegex + ")", ""));
            } else {
                article.setNumber("EMPTY");
            }
            System.out.println("\n\nARTICLE: " + article.getNumber() + "\n\n" + article.getText());
            parsePoints(article);
        }
    }

    private void parsePoints(Article article) throws IOException {
        /*while (!scanner.hasNext(sectionRegex) &&
                !scanner.hasNext(chapterRegex) &&
                !scanner.hasNext(articleRegex) &&
                !scanner.hasNext(pointRegex) &&
                scanner.hasNextLine()) {
            String string = "";
            Point point = new Point();
            article.add(point);
            if (scanner.hasNext(pointRegex)) {
                string = scanner.nextLine();
                point.setNumber(string.replaceAll("([1-9]+)\\. (.*)", "$1"));
                point.setText(parseTextTill(chapterRegex + "|" + sectionRegex + "|" + articleRegex + "|" + pointRegex, string.replaceAll("([1-9]+)\\.(.+)", "$2")));
            }
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n    Point: " + point.getNumber() + "\n\n" + point.getText());
        }*/
    }

    private void parsePointsAndSubPoints(String text) {

    }


    private String parseTextTill(String regex, String text) {
        StringBuilder ret = new StringBuilder(256);
        if (text.matches("[1-9]+\\. .+")) return "EMPTY";
        ret.append(text);
        while (!scanner.hasNext(regex) && scanner.hasNextLine()) {
            ret.append("\n");
            ret.append(scanner.nextLine());
        }

        return ret.toString();
    }

}