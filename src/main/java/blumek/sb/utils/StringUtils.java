package blumek.sb.utils;

public class StringUtils {

    public static boolean isNumeric(String expression) {
        try {
            Double.parseDouble(expression);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static String replaceComamsWithDots(String expression) {
        return expression.replaceAll(",", ".");
    }
}
