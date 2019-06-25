package blumek.sb.Vowpal;

import blumek.sb.utils.StringUtils;

import java.util.List;

public class VowpalManager {
    private final List<String> characteristics;

    public VowpalManager(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public String convertTextToCharacteristics(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = text.length();
        stringBuilder.append("length:")
                .append(length)
                .append(" ");

        int starsAmount = StringUtils.countStars(text);
        stringBuilder.append("vulg:")
                .append(starsAmount)
                .append(" ");

        for (String characteristic: characteristics) {
            int count = StringUtils.countOccurrences(text, characteristic);
            stringBuilder
                    .append("n_")
                    .append(characteristic.replaceAll(" ", "_"))
                    .append(":")
                    .append(count)
                    .append(" ");
        }
        return stringBuilder.toString();
    }
}
