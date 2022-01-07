package fact.it.brankedgeservice.controller;

import fact.it.brankedgeservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public List<FilledAlbum> getFilledAlbums() {
        List<FilledAlbum> returnList = new ArrayList();

        ResponseEntity<List<Album>> responseEntityAlbums =
                restTemplate.exchange("http://" + albumServiceBaseUrl + "/albums",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {
                        }
                );

        List<Album> albums = responseEntityAlbums.getBody();

        for (Album album : albums) {
            ResponseEntity<Song[]> response =
                    restTemplate.getForEntity("http://" + songServiceBaseUrl + "/songs/album/{MAID}",
                            Song[].class, album.getMAID());
            List<Song> songs = List.of(response.getBody());

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
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Song>>() {
                        }, MAID
                );
        List<Song> songs = responseEntitySongs.getBody();

        return new FilledAlbum(album, artist, songs);
    }

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        ResponseEntity<List<Genre>> responseEntityGenres =
                restTemplate.exchange("http://" + genreServiceBaseUrl + "/genres",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Genre>>() {
                        }
                );
        List<Genre> genres = responseEntityGenres.getBody();

        return genres;
    }

    @GetMapping("/genres/{genreName}")
    public Genre getGenreByName(@PathVariable String genreName) {
        Genre genre = restTemplate.getForObject("http://" + genreServiceBaseUrl + "/genres/{genreName}", Genre.class, genreName);
        return genre;
    }

    @GetMapping("/artists")
    public List<Artist> getArtists() {
        ResponseEntity<List<Artist>> responseEntityGenres =
                restTemplate.exchange("http://" + artistServiceBaseUrl + "/artists",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Artist>>() {
                        }
                );
        List<Artist> artists = responseEntityGenres.getBody();

        return artists;
    }

    @GetMapping("/artists/{MBID}")
    public Artist getArtistByMBID(@PathVariable String MBID) {
        Artist artist = restTemplate.getForObject("http://" + artistServiceBaseUrl + "/artists/{MBID}", Artist.class, MBID);
        return artist;
    }
//---------------------------------------------------------------------------------------------------

//    @GetMapping("/songs")
//    public List<Song> getSongs(){
//        ResponseEntity<List<Song>> responseEntityGenres =
//                restTemplate.exchange("http://" + songServiceBaseUrl + "/genres",
//                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Song>>() {
//                        }
//                );
//        List<Song> songs = responseEntityGenres.getBody();
//
//        return songs;
//    }


    @GetMapping("/songs/{ISRC}")
    public Song getSongByISRC(@PathVariable String ISRC) {
        Song song = restTemplate.getForObject("http://" + songServiceBaseUrl + "/songs/{ISRC}", Song.class, ISRC);
        return song;
    }

    @PostMapping("/songs")
    public Song addSong(@RequestParam String isrc, @RequestParam String mbid, @RequestParam String maid, @RequestParam String genre, @RequestParam String title, @RequestParam Integer length, @RequestParam String url) {

        Song song = restTemplate.postForObject("http://" + songServiceBaseUrl + "/songs",
                new Song(isrc, mbid, maid, genre, title, length, url), Song.class);

        return song;
    }

    @PutMapping("/songs")
    public Song updateSong(@RequestParam String isrc, @RequestParam String mbid, @RequestParam String maid, @RequestParam String genre, @RequestParam String title, @RequestParam Integer length, @RequestParam String url) {
        Song song = restTemplate.getForObject("http://" + songServiceBaseUrl + "/songs/" + isrc, Song.class);
        song.setMBID(mbid);
        song.setMAID(maid);
        song.setGenre(genre);
        song.setTitle(title);
        song.setLength(length);
        song.setUrl(url);

        ResponseEntity<Song> responseEntitySong = restTemplate.exchange("http://" + songServiceBaseUrl + "/songs", HttpMethod.PUT, new HttpEntity<>(song), Song.class);
        Song retrievedSong = responseEntitySong.getBody();

        return retrievedSong;
    }

    @DeleteMapping("/songs/{ISRC}")
    public ResponseEntity deleteSong(@PathVariable String ISRC) {

//        Song song = restTemplate.getForObject("http://" + songServiceBaseUrl + "/songs/{ISRC}", Song.class, ISRC);
//        if (song != null) {

            restTemplate.delete("http://" + songServiceBaseUrl + "/songs/{ISRC}", ISRC);
            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }


}

