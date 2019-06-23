package blumek.sb.scrapers;

import org.jsoup.nodes.Document;

public interface Pagination {
    boolean hasNext(Document document);
    String getNext(Document document);
}
