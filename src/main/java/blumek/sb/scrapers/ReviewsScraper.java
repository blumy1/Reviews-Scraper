package blumek.sb.scrapers;

import blumek.sb.models.Review;
import org.jsoup.select.Elements;

import java.util.List;

public interface ReviewsScraper extends Scraper, Pagination{
    List<Review> retrieveReviews(Elements elements);
}
