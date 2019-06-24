package blumek.sb.scrapers;

import blumek.sb.models.Review;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ReviewsScrapeManager {

    public List<Review> scrapeAllPages(ReviewsScraper reviewsScraper, String urlComplement) {
        List<Review> reviews = new ArrayList<Review>();
        boolean hasNext;
        String complement = urlComplement;
        do {
            List<Review> subpageReviews = scrapePage(reviewsScraper, complement);
            reviews.addAll(subpageReviews);
            hasNext = reviewsScraper.hasNext();
            if (hasNext) {
                complement = reviewsScraper.getNext();
            }
        } while (hasNext);
        return reviews;
    }

    public List<Review> scrapePage(ReviewsScraper reviewsScraper, String urlComplement) {
        Connection connection = reviewsScraper.getConnection(urlComplement);
        Document document = reviewsScraper.getDocument(connection);
        Elements elements = reviewsScraper.getElements(document);
        List<Review> reviews = reviewsScraper.retrieveReviews(elements);
        System.out.println(urlComplement);
        return reviews;
    }
}
