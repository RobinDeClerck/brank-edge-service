package fact.it.brankedgeservice.model;


public class Genre {
    private String genreName;
    private String description;

    public Genre() {
    }

    public Genre(String genreName, String description) {
        this.genreName = genreName;
        this.description = description;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



