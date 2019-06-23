package blumek.sb.models;

public class Review {
    private String description;
    private Double rating;

    public Review() {
    }

    public Review(String description, Double rating) {
        this.description = description;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return rating + " | " + description;
    }
}
