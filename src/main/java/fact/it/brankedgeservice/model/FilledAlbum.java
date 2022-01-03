package fact.it.brankedgeservice.model;

public class FilledAlbum {
    private String MAID;
    private String name;
    private String image;
    private String genre;

    private Artist artist;

    private String release;

    public FilledAlbum(Album album, Artist artist) {
        setMAID(album.getMAID());
        setName(album.getName());
        setGenre(album.getGenre());
        setImage(album.getImage());
        setRelease(album.getRelease());

        this.artist = artist;
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
