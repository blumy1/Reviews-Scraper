package blumek.sb.scrapers;

public interface Pagination {
    boolean hasNext();
    String getNext();
}
