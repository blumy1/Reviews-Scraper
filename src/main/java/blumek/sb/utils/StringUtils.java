package blumek.sb.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static List<String> fileLinesToList(File file) {
        List<String> lines = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static int countOccurrences(String text, String searchingFor) {
        int counter = 0;
        Pattern pattern = Pattern.compile(searchingFor, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find())
            counter++;

        return counter;
    }

    public static int countStars(String text) {
        int counter = 0;
        Pattern pattern = Pattern.compile("\\*");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find())
            counter++;

        return counter;
    }

    public static int countCapitalized(String text) {
        int counter = 0;
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find())
            counter++;

        return counter;
    }
}
