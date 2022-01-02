package fact.it.brankedgeservice.model;


public class Album {
    private String id;
    private String name;
    private String image;
    private String genre;
    private String artist;
    private String release;

    public Album() {
    }

    public Album(String name, String artist, String genre, String release, String image) {
        this.name = name;
        this.image = image;
        this.genre = genre;
        this.artist = artist;
        this.release = release;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
