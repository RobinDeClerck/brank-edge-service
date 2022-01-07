package fact.it.brankedgeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.brankedgeservice.model.Genre;
import fact.it.brankedgeservice.model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BrankEdgeUnitTests {

    @Value("${albumservice.baseurl}")
    private String albumServiceBaseUrl;

    @Value("${artistervice.baseurl}")
    private String artistServiceBaseUrl;

    @Value("${genreservice.baseurl}")
    private String genreServiceBaseUrl;

    @Value("${songservice.baseurl}")
    private String songServiceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void initializeMockserver() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    private Genre genre1 = new Genre("Rock", "Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the late 1940s and early 1950s, developing into a range of different styles in the mid-1960s and later, particularly in the United States and the United Kingdom.");
    private Genre genre2 = new Genre("Heavy Metal", "Heavy metal (or simply metal) is a genre of rock music that developed in the late 1960s and early 1970s, largely in the United Kingdom and the United States.");

    private List<Genre> allGenres = Arrays.asList(genre1, genre2);

    @Test
    public void whenGetAllGenres_thenReturnGenreJson() throws Exception {

        // GET all genres
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + genreServiceBaseUrl + "/genres")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(allGenres))
                );

        mockMvc.perform(get("/genres"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].genreName", is("Rock")))
                .andExpect(jsonPath("$[0].description", is("Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the late 1940s and early 1950s, developing into a range of different styles in the mid-1960s and later, particularly in the United States and the United Kingdom.")))
                .andExpect(jsonPath("$[1].genreName", is("Heavy Metal")))
                .andExpect(jsonPath("$[1].description", is("Heavy metal (or simply metal) is a genre of rock music that developed in the late 1960s and early 1970s, largely in the United Kingdom and the United States.")));




    }

    @Test
    public void whenGetOneGenre_thenReturnGenreJson() throws Exception {
        // GET Genre 1
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + genreServiceBaseUrl + "/genres/Rock")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(genre1))
                );

        mockMvc.perform(get("/genres/{genreName}", "Rock"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genreName", is("Rock")))
                .andExpect(jsonPath("$.description", is("Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the late 1940s and early 1950s, developing into a range of different styles in the mid-1960s and later, particularly in the United States and the United Kingdom.")));


    }


    //---------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void whenAddSong_thenReturnSongJson() throws Exception {

        Song song1 = new Song("GBAHT2000193","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Trouble’s Coming",228,"6voIJ7OWwRabSZDC77D5Hp");

        // POST Song
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + songServiceBaseUrl + "/songs")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(song1))
                );

        // GET song 1 info
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + songServiceBaseUrl + "/songs/GBAHT2000193")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(song1))
                );

        mockMvc.perform(post("/songs")
                .param("ISRC", song1.getISRC())
                .param("MBID", song1.getMBID())
                .param("MAID", song1.getMAID())
                .param("genre", song1.getGenre())
                .param("title", song1.getTitle())
                .param("length", song1.getLength().toString())
                .param("url", song1.getUrl())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ISRC", is("GBAHT2000193")))
                .andExpect(jsonPath("$.MBID", is("aa62b28e-b6d4-4086-91d4-e5fac1ed56f3")))
                .andExpect(jsonPath("$.MAID", is("dd7e7ced-a44d-4ce5-9654-c60a0d71fc51")))
                .andExpect(jsonPath("$.genre", is("Rock")))
                .andExpect(jsonPath("$.title", is("Trouble’s Coming")))
                .andExpect(jsonPath("$.length", is(228)))
                .andExpect(jsonPath("$.url", is("6voIJ7OWwRabSZDC77D5Hp")));
    }

    @Test
    public void whenUpdateSong_thenReturnSongJson() throws Exception {

        Song song2 = new Song("GBAHT20001931111","aa62b28e-b6d4-4086-91d4-e5fac1ed56f31111","dd7e7ced-a44d-4ce5-9654-c60a0d71fc511111","Rock1111","Trouble’s Coming1111",2281111,"6voIJ7OWwRabSZDC77D5Hp1111");

        // PUT Song
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + songServiceBaseUrl + "/songs")))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(song2))
                );

        // GET song 2 info
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + songServiceBaseUrl + "/songs/GBAHT20001931111")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(song2))
                );

        mockMvc.perform(put("/songs/GBAHT20001931111")
                .param("ISRC", song2.getISRC())
                .param("MBID", song2.getMBID())
                .param("MAID", song2.getMAID())
                .param("genre", song2.getGenre())
                .param("title", song2.getTitle())
                .param("length", song2.getLength().toString())
                .param("url", song2.getUrl())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ISRC", is("GBAHT20001931111")))
                .andExpect(jsonPath("$.MBID", is("aa62b28e-b6d4-4086-91d4-e5fac1ed56f31111")))
                .andExpect(jsonPath("$.MAID", is("dd7e7ced-a44d-4ce5-9654-c60a0d71fc511111")))
                .andExpect(jsonPath("$.genre", is("Rock1111")))
                .andExpect(jsonPath("$.title", is("Trouble’s Coming1111")))
                .andExpect(jsonPath("$.length", is(2281111)))
                .andExpect(jsonPath("$.url", is("6voIJ7OWwRabSZDC77D5Hp1111")));

    }

    @Test
    public void whenDeleteSong_thenReturnStatusOk() throws Exception {

        // DELETE song
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + songServiceBaseUrl + "/songs/GBAHT2000193")))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                );

        mockMvc.perform(delete("http://" + songServiceBaseUrl + "/songs/{ISRC}", "GBAHT2000193"))
                .andExpect(status().isOk());
    }

}

