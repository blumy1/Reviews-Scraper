package blumek.sb.scrapers;

import org.jsoup.select.Elements;

import java.util.List;

public interface PageScraper extends Scraper, Pagination {
    List<String> getSubpageLinks(Elements elements);
}
