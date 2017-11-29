package jakubziarko.obiektowe.projekt1;

public class RomanToArabic {
    public static int turnRomanToArabic(String roman){
        int number=0;
        if (roman.startsWith("X")) number+=10;
        return number;
    }
}
