package fact.it.brankedgeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.brankedgeservice.model.*;
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

    private Album album1 = new Album("cd76f76b-ff15-3784-a71d-4da3078a6851","Pablo Honey", "a74b1b7f-71a5-4011-9441-d0b5e4122711", "rock", "1993-02-22", "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95");
    private Album album2 = new Album("2b98e6d7-a521-332f-961e-d281ba33ba3d","Reggatta de Blanc", "9e0e2b01-41db-4008-bd8b-988977d6019a", "rock", "1979-10-02", "https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486");
    private Album album3 = new Album("af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Black Holes and Revelations", "9c9f1380-2516-4fc9-a3e6-f9f61941d090", "rock", "2006-07-03", "https://i.scdn.co/image/ab67616d00001e0228933b808bfb4cbbd0385400");

    private Artist artist1 = new Artist("a74b1b7f-71a5-4011-9441-d0b5e4122711","Radiohead", "Rock band", "United Kingdom",Arrays.asList("Thom Yorke", "Jonny Greenwood", "Ed O'Brien", "Colin Greenwood", "Philip Selway"),"https://i.scdn.co/image/ab676186000010161802a4cbec82e078cc15cbb0")
    private Artist artist2 = new Artist("9e0e2b01-41db-4008-bd8b-988977d6019a", "The Police", "Rock band", "United Kingdom", Arrays.asList("Sting", "Stewart Copeland", "Andy Summers", "Henry Padovani"), "https://i.scdn.co/image/ab67618600001016af496a5f2377f1149d2a5cf3")
    private Artist artist3 = new Artist("9c9f1380-2516-4fc9-a3e6-f9f61941d090","Muse", "Rock band", "United Kingdom", Arrays.asList("Matt Bellamy", "Chris Wolstenholme", "Dominic Howard"), "https://i.scdn.co/image/ab67618600001016ef59f1c62339f247d38ded80")

    private Song song1 = new Song("GBAHT2000193","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Trouble’s Coming",228,"6voIJ7OWwRabSZDC77D5Hp");
    private Song song2 = new Song("GBAHT2001120","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Oblivion",161,"3Ye5icBka8ODjcaEQakPvZ");
    private Song song3 = new Song("GBAHT2001121","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Typhoons",236,"5aFGo8wHEntVxFI8IF7Wuj");
    private List<Song> songsFromAlbum1 = Arrays.asList(song1, song2, song3);

    private Song song4 = new Song("GBAAM0201170","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Message In A Bottle",290,"1oYYd2gnWZYrt89EBXdFiO");
    private Song song5 = new Song("GBAAM0201171","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Reggatta De Blanc",185,"2EEp2vTGSRDSLHWUF80EZZ");
    private Song song6 = new Song("GBAAM0201172","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","It's Alright For You",192,"5fTI7JCaMRK09WtwG8ZrRK");
    private List<Song> songsFromAlbum2 = Arrays.asList(song4, song5, song6);

    private Song song7 = new Song("GBAHT0500591","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Take a Bow",275,"4jrCMOG9OPe6iF4vWFxatb"));
    private Song song8 = new Song("GBAHT0500592","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Starlight",240,"3skn2lauGk7Dx6bVIt5DVj"));
    private Song song9 = new Song("GBAHT0500593","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Supermassive Black Hole",212,"3lPr8ghNDBLc2uZovNyLs9"));
    private List<Song> songsFromAlbum3 = Arrays.asList(song7, song8, song9);

    private FilledAlbum filledAlbum1 = new FilledAlbum(album1, artist1, songsFromAlbum1);
    private FilledAlbum filledAlbum2 = new FilledAlbum(album2, artist2, songsFromAlbum2);
    private FilledAlbum filledAlbum3 = new FilledAlbum(album3, artist3, songsFromAlbum3);
    private List<FilledAlbum> allFilledAlbums = Arrays.asList(filledAlbum1, filledAlbum2, filledAlbum3);

    @Test
    void whenGetAlbumByName_thenReturnFilledAlbumJson() throws Exception {
        // GET album by MAID 'dd7e7ced-a44d-4ce5-9654-c60a0d71fc51'
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + albumServiceBaseUrl + "/ablums/dd7e7ced-a44d-4ce5-9654-c60a0d71fc51")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(allReviewsFromUser1))
                );

        // GET artist for album dd7e7ced-a44d-4ce5-9654-c60a0d71fc51



        // GET songs for album dd7e7ced-a44d-4ce5-9654-c60a0d71fc51

    }

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
    public void whenGetOneGenres_thenReturnGenreJson() throws Exception {
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


}

