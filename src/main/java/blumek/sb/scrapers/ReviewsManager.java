package blumek.sb.scrapers;

import blumek.sb.models.Review;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ReviewsManager {

    public List<Review> scrapeReviews(ReviewsScraper reviewsScraper, String urlComplement) {
        List<Review> reviews = new ArrayList<Review>();
        boolean hasNext;
        String complement = urlComplement;
        do {
            Connection connection = reviewsScraper.getConnection(complement);
            Document document = reviewsScraper.getDocument(connection);
            Elements elements = reviewsScraper.getElements(document);
            List<Review> subpageReviews = reviewsScraper.retrieveReviews(elements);
            reviews.addAll(subpageReviews);
            hasNext = reviewsScraper.hasNext(document);
            if (hasNext) {
                complement = reviewsScraper.getNext(document);
            }
        } while (hasNext);
        System.out.println(complement);
        return reviews;
    }
}
