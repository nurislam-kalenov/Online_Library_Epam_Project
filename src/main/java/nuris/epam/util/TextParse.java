package nuris.epam.util;

/**
 * Created by User on 30.03.2017.
 */
public class TextParse {
    public static String upperCaseFirstLetter(String word) {
        String firstLetter;
        firstLetter = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        return firstLetter;
    }

    public static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
