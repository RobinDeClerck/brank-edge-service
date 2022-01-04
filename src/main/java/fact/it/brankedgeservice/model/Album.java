package fact.it.brankedgeservice.model;


public class Album {
    private String id;
    private String MAID;
    private String name;
    private String image;
    private String genre;
    private String MBID;
    private String release;

    public Album() {
    }

    public Album(String MAID, String name, String MBID, String genre, String release, String image) {
        this.MAID = MAID;
        this.name = name;
        this.image = image;
        this.genre = genre;
        this.MBID = MBID;
        this.release = release;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMAID() {
        return MAID;
    }

    public void setMAID(String MAID) {
        this.MAID = MAID;
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

    public String getMBID() {
        return MBID;
    }

    public void setMBID(String MBID) {
        this.MBID = MBID;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}

