package blumek.sb.utils;

import blumek.sb.models.Review;

import java.io.*;
import java.util.List;

public class FileManager {
    public static void appendReviewsToFile(List<Review> reviews, String fileName) {
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (Review review : reviews) {
                bw.write(review.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
