package Models.DTO;

public class UpdateFeedbackDTO {
        private int id; // ID e feedback-ut që duam të përditësojmë
        private int rating; // Rating-u i ri
        private String comment; // Komenti i ri

        public UpdateFeedbackDTO(int id, int rating, String comment) {
            this.id = id;
            this.rating = rating;
            this.comment = comment;
        }

        public int getId() {
            return id;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
}

