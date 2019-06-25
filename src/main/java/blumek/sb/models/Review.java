package blumek.sb.models;

public class Review {
    private String description;
    private double rating;

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
        if (rating % 1 == 0)
            return (int) rating + " | " + description;
        else
            return rating + " | " + description;
    }
}
