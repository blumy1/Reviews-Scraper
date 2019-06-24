package blumek.sb;

import blumek.sb.Vowpal.VowpalManager;
import blumek.sb.models.Review;
import blumek.sb.scrapers.*;
import blumek.sb.utils.FileManager;
import blumek.sb.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PageScrapeManager pageScrapeManager = new PageScrapeManager();
        PageScraper pageScraper = new CeneoPageScraper();
        List<String> categories = StringUtils.fileLinesToList(new File("Categories.txt"));
        List<String> links = new ArrayList<String>();

        for (String category : categories) {
            links.addAll(pageScrapeManager.scrapeAllPages(pageScraper, category));
            System.out.println(category);
        }

        ReviewsScrapeManager scrapeManager = new ReviewsScrapeManager();
        ReviewsScraper ceneoScraper = new CeneoReviewsScraper();
        List<Review> reviews = new ArrayList<Review>();
        VowpalManager vowpalManager = new VowpalManager(StringUtils.fileLinesToList(new File("Characteristics.txt")));

        for (String link : links) {
            List<Review> subpageReviews = scrapeManager.scrapeAllPages(ceneoScraper, link + "/opinie-1");
            reviews.addAll(subpageReviews);
        }

        int trainingDataLength = (int) (reviews.size() * 0.9);
        List<Review> trainingReviews = reviews.subList(0, trainingDataLength);
        for (Review review : trainingReviews) {
            String desc = review.getDescription();
            String convertedDesc = vowpalManager.convertTextToCharacteristics(desc);
            review.setDescription(convertedDesc);
        }

        List<Review> testReviews = reviews.subList(trainingDataLength, reviews.size());
        for (Review review : testReviews) {
            String desc = review.getDescription();
            String convertedDesc = vowpalManager.convertTextToCharacteristics(desc);
            review.setDescription(convertedDesc);
        }

        //Save original data for testing
        FileManager.appendReviewsToFile(testReviews, "test-original.txt");

        for (Review review : testReviews) {
            review.setRating(0.0);
        }

        //Save data for training
        FileManager.appendReviewsToFile(trainingReviews, "train.txt");

        //Save data for testing
        FileManager.appendReviewsToFile(testReviews, "test.txt");

        System.out.println(reviews.size());

    }
}
