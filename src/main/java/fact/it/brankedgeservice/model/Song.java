package fact.it.brankedgeservice.model;


public class Song {
    private String id;
    private String ISRC;
    private String MBID;
    private String MAID;
    private String genre;
    private String title;
    private Integer length;
    private String url;

    public Song() {
    }

    public Song(String ISRC, String MBID, String MAID, String genre, String title, Integer length, String url) {
        setISRC(ISRC);
        setMBID(MBID);
        setMAID(MAID);
        setGenre(genre);
        setTitle(title);
        setLength(length);
        setUrl(url);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    public String getMBID() { return MBID; }

    public void setMBID(String MBID) { this.MBID = MBID; }

    public String getMAID() {
        return MAID;
    }

    public void setMAID(String MAID) {
        this.MAID = MAID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
