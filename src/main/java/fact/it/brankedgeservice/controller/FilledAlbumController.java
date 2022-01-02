package fact.it.brankedgeservice.controller;

import fact.it.brankedgeservice.model.Album;
import fact.it.brankedgeservice.model.Artist;
import fact.it.brankedgeservice.model.FilledAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class FilledAlbumController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${albumservice.baseurl}")
    private String albumServiceBaseUrl;

    @Value("${artistervice.baseurl}")
    private String artistServiceBaseUrl;


    @GetMapping("/albums")
    public List<Album> getAlbums() {
        ResponseEntity<List<Album>> responseEntityBooks =
                restTemplate.exchange("http://" + albumServiceBaseUrl + "/albums",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {}
                );

        List<Album> albums = responseEntityBooks.getBody();

        return albums;
    }


}

