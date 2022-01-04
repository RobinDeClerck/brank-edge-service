package fact.it.brankedgeservice.controller;

import fact.it.brankedgeservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FilledAlbumController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${albumservice.baseurl}")
    private String albumServiceBaseUrl;

    @Value("${artistervice.baseurl}")
    private String artistServiceBaseUrl;

    @Value("${genreservice.baseurl}")
    private String genreServiceBaseUrl;

    @Value("${songservice.baseurl}")
    private String songServiceBaseUrl;

    @GetMapping("/albums")
    public List<FilledAlbum> getAlbums() {
        List<FilledAlbum> returnList= new ArrayList();

        ResponseEntity<List<Album>> responseEntityAlbums =
                restTemplate.exchange("http://" + albumServiceBaseUrl + "/albums",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {}
                );

        List<Album> albums = responseEntityAlbums.getBody();

        for (Album album: albums) {
            System.out.println(album.getMAID());
            ResponseEntity<List<Song>> responseEntitySongs =
                    restTemplate.exchange("http://" + songServiceBaseUrl + "/songs/album/{MAID}",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Song>>() {}, album.getMAID()
                    );
            List<Song> songs = responseEntitySongs.getBody();

            Artist artist = restTemplate.getForObject("http://" + artistServiceBaseUrl + "/artists/{MBID}",
                    Artist.class, album.getMBID());

            returnList.add(new FilledAlbum(album, artist, songs));
        }

        return returnList;
    }

    @GetMapping("/albums/{MAID}")
    public FilledAlbum getFilledAlbumByName(@PathVariable String MAID) {
        Album album = restTemplate.getForObject("http://" + albumServiceBaseUrl + "/albums/{MAID}", Album.class, MAID);

        Artist artist = restTemplate.getForObject("http://" + artistServiceBaseUrl + "/artists/{MBID}", Artist.class, album.getMBID());

        ResponseEntity<List<Song>> responseEntitySongs =
                restTemplate.exchange("http://" + songServiceBaseUrl + "/songs/album/{MAID}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Song>>() {}, MAID
                );
        List<Song> songs = responseEntitySongs.getBody();

        return new FilledAlbum(album, artist, songs);
    }

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        ResponseEntity<List<Genre>> responseEntityGenres =
                restTemplate.exchange("http://" + genreServiceBaseUrl + "/genres",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Genre>>() {}
                );
        List<Genre> genres = responseEntityGenres.getBody();

        return genres;
    }

    @GetMapping("/genres/{genreName}")
    public Genre getGenreByName(@PathVariable String genreName) {
        Genre genre = restTemplate.getForObject("http://" + genreServiceBaseUrl + "/genres/{genreName}", Genre.class, genreName);
        String outputName = genre.getGenreName();
        String outputDescription = genre.getDescription();
        return new Genre(outputName, outputDescription);
    }

}

