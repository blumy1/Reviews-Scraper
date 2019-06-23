package blumek.sb.scrapers;

import blumek.sb.models.Review;
import blumek.sb.utils.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CeneoScraper implements ReviewsScraper{
    private final String BASE_URL = "https://www.ceneo.pl";

    public Connection getConnection(String urlComplement) {
        return Jsoup.connect(BASE_URL + urlComplement);
    }

    public Document getDocument(Connection connection) {
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public Elements getElements(Document document) {
        if (document == null) return null;
        Elements elementsContainer = document.select("ol.product-reviews.js_product-reviews.js_reviews-hook.js_product-reviews-container");
        return elementsContainer.select("li.review-box.js_product-review div.show-review-content");
    }

    public List<Review> retrieveReviews(Elements elements) {
        List<Review> reviews = new ArrayList<Review>();

        for (Element element : elements) {
            String description = element.selectFirst("p.product-review-body").text();
            String rating = element.selectFirst("span.review-score-count").text();
            rating = extractRating(rating);
            if (rating == null)
                continue;
            rating = StringUtils.replaceComamsWithDots(rating);

            if (!StringUtils.isNumeric(rating))
                continue;

            Review review = new Review(description, Double.valueOf(rating));
            reviews.add(review);
        }

        return reviews;
    }

    private String extractRating(String expression) {
        String regexPattern = "(\\d(,\\d)?)(/(\\d(,\\d)?))?";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(expression);
        return matcher.matches() ? matcher.group(1) : null;
    }

    public boolean hasNext(Document document) {
        Element element = document.selectFirst("li.page-arrow.arrow-next a");
        return element != null;
    }

    public String getNext(Document document) {
        return  document.selectFirst("li.page-arrow.arrow-next a").attr("href");
    }
}
