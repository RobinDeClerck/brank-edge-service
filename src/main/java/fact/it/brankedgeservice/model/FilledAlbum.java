package fact.it.brankedgeservice.model;

public class FilledAlbum {
    private Album album;
    private Artist artist;

    public FilledAlbum(Album album, Artist artist) {
        this.album = album;
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
