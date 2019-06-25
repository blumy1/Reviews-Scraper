package blumek.sb.scrapers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CeneoPageScraper implements PageScraper {
    private final String BASE_URL = "https://www.ceneo.pl";
    private String next;

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
        checkForNext(document);

        Elements elementsContainer = document.select("div.category-list-body.js_category-list-body.js_search-results");
        return elementsContainer.select("div.cat-prod-row.js_category-list-item.js_clickHashData.js_man-track-event");
    }

    public List<String> getSubpageLinks(Elements elements) {
        List<String> subpageLinks = new ArrayList<String>();
        for (Element element : elements) {
            Element linkElement = element.selectFirst("a.js_clickHash.js_seoUrl.go-to-product");
            if (linkElement == null)
                continue;
            String link = linkElement.attr("href");
            subpageLinks.add(link);
        }
        return subpageLinks;
    }

    public boolean hasNext() {
        return next != null;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void checkForNext(Document document) {
        Element nextElement = document.selectFirst("li.page-arrow.arrow-next a");
        if (nextElement != null)
            setNext(nextElement.attr("href"));
        else
            setNext(null);
    }
}
