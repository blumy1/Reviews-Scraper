package blumek.sb;

import blumek.sb.models.Review;
import blumek.sb.scrapers.CeneoScraper;
import blumek.sb.scrapers.ReviewsManager;
import blumek.sb.scrapers.ReviewsScraper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReviewsManager reviewsManager = new ReviewsManager();
        ReviewsScraper ceneoScraper = new CeneoScraper();
        List<Review> reviews = reviewsManager.scrapeReviews(ceneoScraper,"/50851290/opinie-1");
        for (Review review : reviews) {
            System.out.println(review);
        }
        System.out.println(reviews.size());
    }
}
