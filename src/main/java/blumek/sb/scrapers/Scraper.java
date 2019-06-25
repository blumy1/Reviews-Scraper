package blumek.sb.scrapers;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public interface Scraper {
    Connection getConnection(String urlComplement);
    Document getDocument(Connection connection);
    Elements getElements(Document document);
}
