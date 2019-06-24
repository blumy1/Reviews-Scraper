package blumek.sb;

import blumek.sb.models.Review;
import blumek.sb.scrapers.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PageScrapeManager pageScrapeManager = new PageScrapeManager();
        PageScraper pageScraper = new CeneoPageScraper();
        List<String> links = pageScrapeManager.scrapeAllPages(pageScraper, "/Telefony_komorkowe");
        ReviewsScrapeManager scrapeManager = new ReviewsScrapeManager();
        ReviewsScraper ceneoScraper = new CeneoReviewsScraper();
        List<Review> reviews = new ArrayList<Review>();

        for (String link : links) {
            List<Review> subpageReviews = scrapeManager.scrapeAllPages(ceneoScraper, link + "/opinie-1");
            reviews.addAll(subpageReviews);
        }

        for (Review review : reviews) {
            System.out.println(review);
        }
        System.out.println(reviews.size());
    }
}
