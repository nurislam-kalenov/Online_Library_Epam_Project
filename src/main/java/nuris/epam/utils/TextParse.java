package nuris.epam.utils;

/**
 * Created by User on 02.04.2017.
 */
public class TextParse {

    public static int toInt(String s) {
        if(s.equals("")){
            s = "0";
        }
        return Integer.parseInt(s);
    }

}
