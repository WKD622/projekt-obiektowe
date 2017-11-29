package jakubziarko.obiektowe.projekt1;

public class RomanToArabic {
    public static int turnRomanToArabic(String roman){
        if (roman.equals("I")) return 1;
            else if (roman.equals("II")) return 2;
                else if (roman.equals("III")) return 3;
                    else if (roman.equals("IV")) return 4;
                        else if (roman.equals("V")) return 5;
                            else if (roman.equals("VI")) return 6;
                                else if (roman.equals("VII")) return 7;
                                    else if (roman.equals("VIII")) return 8;
                                        else if (roman.equals("IX")) return 9;
                                            else if (roman.equals("X")) return 10;
                                                else if (roman.equals("XI")) return 11;
                                                    else if (roman.equals("XII")) return 12;
                                                        else return 13;
    }
}
