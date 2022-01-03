package fact.it.brankedgeservice.controller;

import fact.it.brankedgeservice.model.Album;
import fact.it.brankedgeservice.model.Artist;
import fact.it.brankedgeservice.model.FilledAlbum;
import fact.it.brankedgeservice.model.Genre;
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

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/albums")
    public List<Album> getAlbums() {
        ResponseEntity<List<Album>> responseEntityAlbums =
                restTemplate.exchange("https://" + albumServiceBaseUrl + "/albums",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {}
                );

        List<Album> albums = responseEntityAlbums.getBody();

        return albums;
    }

    @GetMapping("/albums/{name}")
    public FilledAlbum getFilledAlbumByName(@PathVariable String name) {
        Album album = restTemplate.getForObject("https://" + albumServiceBaseUrl + "/albums/{name}", Album.class, name);

        Artist artist = restTemplate.getForObject("https://" + artistServiceBaseUrl + "/artists/{uuid}", Artist.class, album.getArtist());

        return new FilledAlbum(album, artist);
    }

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        ResponseEntity<List<Genre>> responseEntityGenres =
                restTemplate.exchange("https://" + genreServiceBaseUrl + "/genres",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Genre>>() {}
                );

        List<Genre> genres = responseEntityGenres.getBody();

        return genres;
    }

    @GetMapping("/genres/{genreName}")
    public Genre getGenreByName(@PathVariable String genreName) {
        Genre genre = restTemplate.getForObject("https://" + genreServiceBaseUrl + "/genres/{genreName}", Genre.class, genreName);
        String outputName = genre.getGenreName();
        String outputDescription = genre.getDescription();
        return new Genre(outputName, outputDescription);
    }

}

