package patterns;

import java.util.HashMap;
import java.util.Map;

public class ReviewComponent {
    private Map<String, String> reviews = new HashMap<>();

    public void addReview(String username, String reviewText) {
        reviews.put(username, reviewText);
    }

    public void displayReviews() {
        System.out.println("Reviews:");
        for (Map.Entry<String, String> entry : reviews.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}