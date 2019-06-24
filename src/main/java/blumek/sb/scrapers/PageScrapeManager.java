package blumek.sb.scrapers;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PageScrapeManager {

    public List<String> scrapeAllPages(PageScraper pageScraper, String urlComplement) {
        List<String> links = new ArrayList<String>();
        String complement = urlComplement;
        do {
            List<String> subpageLinks = scrapePage(pageScraper, complement);
            links.addAll(subpageLinks);
            complement = pageScraper.getNext();
        } while (pageScraper.hasNext());
        return links;
    }

    public List<String> scrapePage(PageScraper pageScraper, String urlComplement) {
        Connection connection = pageScraper.getConnection(urlComplement);
        Document document = pageScraper.getDocument(connection);
        Elements elements = pageScraper.getElements(document);
        List<String> links = pageScraper.getSubpageLinks(elements);
        System.out.println(urlComplement);
        return links;
    }
}
