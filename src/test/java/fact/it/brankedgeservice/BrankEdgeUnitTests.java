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

    private Album album1 = new Album("cd76f76b-ff15-3784-a71d-4da3078a6851","Pablo Honey", "a74b1b7f-71a5-4011-9441-d0b5e4122711", "Rock", "1993-02-22", "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95");
    private Album album2 = new Album("2b98e6d7-a521-332f-961e-d281ba33ba3d","Reggatta de Blanc", "9e0e2b01-41db-4008-bd8b-988977d6019a", "Rock", "1979-10-02", "https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486");

    private Artist artist1 = new Artist("a74b1b7f-71a5-4011-9441-d0b5e4122711","Radiohead", "Rock band", "United Kingdom",Arrays.asList("Thom Yorke", "Jonny Greenwood", "Ed O'Brien", "Colin Greenwood", "Philip Selway"),"https://i.scdn.co/image/ab676186000010161802a4cbec82e078cc15cbb0");
    private Artist artist2 = new Artist("9e0e2b01-41db-4008-bd8b-988977d6019a", "The Police", "Rock band", "United Kingdom", Arrays.asList("Sting", "Stewart Copeland", "Andy Summers", "Henry Padovani"), "https://i.scdn.co/image/ab67618600001016af496a5f2377f1149d2a5cf3");
    private List<Artist> allArtists = Arrays.asList(artist1, artist2);

    private Song song1 = new Song("GBAYE9200113","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","You",208,"5KZ0qobWEFl892YjIC02SE");
    private Song song2 = new Song("GBAYE9200070","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Creep",238,"70LcF31zb1H0PyJoS1Sx1r");
    private Song song3 = new Song("GBAYE9300105","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","How Do You?",132,"5qsgK2wcodYCEbgbdCpYOG");
    private List<Song> songsFromAlbum1 = Arrays.asList(song1, song2, song3);

    private Song song4 = new Song("GBAAM0201170","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Message In A Bottle",290,"1oYYd2gnWZYrt89EBXdFiO");
    private Song song5 = new Song("GBAAM0201171","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Reggatta De Blanc",185,"2EEp2vTGSRDSLHWUF80EZZ");
    private Song song6 = new Song("GBAAM0201172","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","It's Alright For You",192,"5fTI7JCaMRK09WtwG8ZrRK");
    private List<Song> songsFromAlbum2 = Arrays.asList(song4, song5, song6);

    private FilledAlbum filledAlbum1 = new FilledAlbum(album1, artist1, songsFromAlbum1);
    private FilledAlbum filledAlbum2 = new FilledAlbum(album2, artist2, songsFromAlbum2);
    private List<FilledAlbum> allFilledAlbums = Arrays.asList(filledAlbum1, filledAlbum2);

    @Test
    void whenGetAlbums_thenReturnFilledAlbumsJson() throws Exception {
        // GET all albums
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + albumServiceBaseUrl + "/albums")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(allFilledAlbums))
                );

        // GET songs for album 1
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + songServiceBaseUrl + "/songs/album/" + album1.getMAID())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(songsFromAlbum1))
                );

        // GET artist for album 1
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + artistServiceBaseUrl + "/artists/" + album1.getMBID())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(artist1))
                );

        // GET songs for album 2
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + songServiceBaseUrl + "/songs/album/" + album2.getMAID())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(songsFromAlbum2))
                );

        // GET artist for album 2
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + artistServiceBaseUrl + "/artists/" + album2.getMBID())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(artist2))
                );

        mockMvc.perform(get("/albums"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$[0].name", is("Pablo Honey")))
                .andExpect(jsonPath("$[0].image", is("https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95")))
                .andExpect(jsonPath("$[0].genre", is("Rock")))

                .andExpect(jsonPath("$[0].artist.name", is("Radiohead")))
                .andExpect(jsonPath("$[0].artist.type", is("Rock band")))
                .andExpect(jsonPath("$[0].artist.originCountry", is("United Kingdom")))
                .andExpect(jsonPath("$[0].artist.members", is(Arrays.asList("Thom Yorke", "Jonny Greenwood", "Ed O'Brien", "Colin Greenwood", "Philip Selway"))))
                .andExpect(jsonPath("$[0].artist.bannerImage", is("https://i.scdn.co/image/ab676186000010161802a4cbec82e078cc15cbb0")))
                .andExpect(jsonPath("$[0].artist.mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))

                .andExpect(jsonPath("$[0].songs[0].genre", is("Rock")))
                .andExpect(jsonPath("$[0].songs[0].title", is("You")))
                .andExpect(jsonPath("$[0].songs[0].length", is(208)))
                .andExpect(jsonPath("$[0].songs[0].url", is("5KZ0qobWEFl892YjIC02SE")))
                .andExpect(jsonPath("$[0].songs[0].mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$[0].songs[0].maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$[0].songs[0].isrc", is("GBAYE9200113")))

                .andExpect(jsonPath("$[0].songs[1].genre", is("Rock")))
                .andExpect(jsonPath("$[0].songs[1].title", is("Creep")))
                .andExpect(jsonPath("$[0].songs[1].length", is(238)))
                .andExpect(jsonPath("$[0].songs[1].url", is("70LcF31zb1H0PyJoS1Sx1r")))
                .andExpect(jsonPath("$[0].songs[1].mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$[0].songs[1].maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$[0].songs[1].isrc", is("GBAYE9200070")))

                .andExpect(jsonPath("$[0].songs[2].genre", is("Rock")))
                .andExpect(jsonPath("$[0].songs[2].title", is("How Do You?")))
                .andExpect(jsonPath("$[0].songs[2].length", is(132)))
                .andExpect(jsonPath("$[0].songs[2].url", is("5qsgK2wcodYCEbgbdCpYOG")))
                .andExpect(jsonPath("$[0].songs[2].mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$[0].songs[2].maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$[0].songs[2].isrc", is("GBAYE9300105")))


                .andExpect(jsonPath("$[1].maid", is("2b98e6d7-a521-332f-961e-d281ba33ba3d")))
                .andExpect(jsonPath("$[1].name", is("Reggatta de Blanc")))
                .andExpect(jsonPath("$[1].image", is("https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486")))
                .andExpect(jsonPath("$[1].genre", is("Rock")))

                .andExpect(jsonPath("$[1].artist.name", is("The Police")))
                .andExpect(jsonPath("$[1].artist.type", is("Rock band")))
                .andExpect(jsonPath("$[1].artist.originCountry", is("United Kingdom")))
                .andExpect(jsonPath("$[1].artist.members", is(Arrays.asList("Sting", "Stewart Copeland", "Andy Summers", "Henry Padovani"))))
                .andExpect(jsonPath("$[1].artist.bannerImage", is("https://i.scdn.co/image/ab67618600001016af496a5f2377f1149d2a5cf3")))
                .andExpect(jsonPath("$[1].artist.mbid", is("9e0e2b01-41db-4008-bd8b-988977d6019a")))

                .andExpect(jsonPath("$[1].songs[0].genre", is("Rock")))
                .andExpect(jsonPath("$[1].songs[0].title", is("Message In A Bottle")))
                .andExpect(jsonPath("$[1].songs[0].length", is(290)))
                .andExpect(jsonPath("$[1].songs[0].url", is("1oYYd2gnWZYrt89EBXdFiO")))
                .andExpect(jsonPath("$[1].songs[0].mbid", is("9e0e2b01-41db-4008-bd8b-988977d6019a")))
                .andExpect(jsonPath("$[1].songs[0].maid", is("2b98e6d7-a521-332f-961e-d281ba33ba3d")))
                .andExpect(jsonPath("$[1].songs[0].isrc", is("GBAAM0201170")))

                .andExpect(jsonPath("$[1].songs[1].genre", is("Rock")))
                .andExpect(jsonPath("$[1].songs[1].title", is("Reggatta De Blanc")))
                .andExpect(jsonPath("$[1].songs[1].length", is(185)))
                .andExpect(jsonPath("$[1].songs[1].url", is("2EEp2vTGSRDSLHWUF80EZZ")))
                .andExpect(jsonPath("$[1].songs[1].mbid", is("9e0e2b01-41db-4008-bd8b-988977d6019a")))
                .andExpect(jsonPath("$[1].songs[1].maid", is("2b98e6d7-a521-332f-961e-d281ba33ba3d")))
                .andExpect(jsonPath("$[1].songs[1].isrc", is("GBAAM0201171")))

                .andExpect(jsonPath("$[1].songs[2].genre", is("Rock")))
                .andExpect(jsonPath("$[1].songs[2].title", is("It's Alright For You")))
                .andExpect(jsonPath("$[1].songs[2].length", is(192)))
                .andExpect(jsonPath("$[1].songs[2].url", is("5fTI7JCaMRK09WtwG8ZrRK")))
                .andExpect(jsonPath("$[1].songs[2].mbid", is("9e0e2b01-41db-4008-bd8b-988977d6019a")))
                .andExpect(jsonPath("$[1].songs[2].maid", is("2b98e6d7-a521-332f-961e-d281ba33ba3d")))
                .andExpect(jsonPath("$[1].songs[2].isrc", is("GBAAM0201172")));
    }

    @Test
    void whenGetAlbumByName_thenReturnFilledAlbumJson() throws Exception {
        // GET album by MAID from album 1
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + albumServiceBaseUrl + "/albums/" + album1.getMAID())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(album1))
                );

        // GET artist for album 1
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + artistServiceBaseUrl + "/artists/" + album1.getMBID())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(artist1))
                );

        // GET songs for album 1
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + songServiceBaseUrl + "/songs/album/" + album1.getMAID())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(songsFromAlbum1))
                );

        mockMvc.perform(get("/albums/{MAID}", album1.getMAID()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$.name", is("Pablo Honey")))
                .andExpect(jsonPath("$.image", is("https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95")))
                .andExpect(jsonPath("$.genre", is("Rock")))

                .andExpect(jsonPath("$.artist.name", is("Radiohead")))
                .andExpect(jsonPath("$.artist.type", is("Rock band")))
                .andExpect(jsonPath("$.artist.originCountry", is("United Kingdom")))
                .andExpect(jsonPath("$.artist.members", is(Arrays.asList("Thom Yorke", "Jonny Greenwood", "Ed O'Brien", "Colin Greenwood", "Philip Selway"))))
                .andExpect(jsonPath("$.artist.bannerImage", is("https://i.scdn.co/image/ab676186000010161802a4cbec82e078cc15cbb0")))
                .andExpect(jsonPath("$.artist.mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))

                .andExpect(jsonPath("$.songs[0].genre", is("Rock")))
                .andExpect(jsonPath("$.songs[0].title", is("You")))
                .andExpect(jsonPath("$.songs[0].length", is(208)))
                .andExpect(jsonPath("$.songs[0].url", is("5KZ0qobWEFl892YjIC02SE")))
                .andExpect(jsonPath("$.songs[0].mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$.songs[0].maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$.songs[0].isrc", is("GBAYE9200113")))

                .andExpect(jsonPath("$.songs[1].genre", is("Rock")))
                .andExpect(jsonPath("$.songs[1].title", is("Creep")))
                .andExpect(jsonPath("$.songs[1].length", is(238)))
                .andExpect(jsonPath("$.songs[1].url", is("70LcF31zb1H0PyJoS1Sx1r")))
                .andExpect(jsonPath("$.songs[1].mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$.songs[1].maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$.songs[1].isrc", is("GBAYE9200070")))

                .andExpect(jsonPath("$.songs[2].genre", is("Rock")))
                .andExpect(jsonPath("$.songs[2].title", is("How Do You?")))
                .andExpect(jsonPath("$.songs[2].length", is(132)))
                .andExpect(jsonPath("$.songs[2].url", is("5qsgK2wcodYCEbgbdCpYOG")))
                .andExpect(jsonPath("$.songs[2].mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$.songs[2].maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$.songs[2].isrc", is("GBAYE9300105")));
    }

    @Test
    public void whenGetArtists_thenReturnArtistsJson() throws Exception {
        // GET all artists
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + artistServiceBaseUrl + "/artists")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(allArtists))
                );

        mockMvc.perform(get("/artists"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$[0].name", is("Radiohead")))
                .andExpect(jsonPath("$[0].type", is("Rock band")))
                .andExpect(jsonPath("$[0].originCountry", is("United Kingdom")))
                .andExpect(jsonPath("$[0].members", is(Arrays.asList("Thom Yorke", "Jonny Greenwood", "Ed O'Brien", "Colin Greenwood", "Philip Selway"))))
                .andExpect(jsonPath("$[0].bannerImage", is("https://i.scdn.co/image/ab676186000010161802a4cbec82e078cc15cbb0")))

                .andExpect(jsonPath("$[1].mbid", is("9e0e2b01-41db-4008-bd8b-988977d6019a")))
                .andExpect(jsonPath("$[1].name", is("The Police")))
                .andExpect(jsonPath("$[1].type", is("Rock band")))
                .andExpect(jsonPath("$[1].originCountry", is("United Kingdom")))
                .andExpect(jsonPath("$[1].members", is(Arrays.asList("Sting", "Stewart Copeland", "Andy Summers", "Henry Padovani"))))
                .andExpect(jsonPath("$[1].bannerImage", is("https://i.scdn.co/image/ab67618600001016af496a5f2377f1149d2a5cf3")));
    }

    @Test
    public void whenGetArtistByMBID_thenReturnArtistJson() throws Exception {
        // GET all artist 1 by mbid
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + artistServiceBaseUrl + "/artists/" + artist1.getMBID())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(artist1))
                );

        mockMvc.perform(get("/artists/{mbid}", artist1.getMBID()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Radiohead")))
                .andExpect(jsonPath("$.type", is("Rock band")))
                .andExpect(jsonPath("$.originCountry", is("United Kingdom")))
                .andExpect(jsonPath("$.members", is(Arrays.asList("Thom Yorke", "Jonny Greenwood", "Ed O'Brien", "Colin Greenwood", "Philip Selway"))))
                .andExpect(jsonPath("$.bannerImage", is("https://i.scdn.co/image/ab676186000010161802a4cbec82e078cc15cbb0")))
                .andExpect(jsonPath("$.mbid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")));
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
                .param("isrc", song1.getISRC())
                .param("mbid", song1.getMBID())
                .param("maid", song1.getMAID())
                .param("genre", song1.getGenre())
                .param("title", song1.getTitle())
                .param("length", song1.getLength().toString())
                .param("url", song1.getUrl())

                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("GBAHT2000193")))
                .andExpect(jsonPath("$.mbid", is("aa62b28e-b6d4-4086-91d4-e5fac1ed56f3")))
                .andExpect(jsonPath("$.maid", is("dd7e7ced-a44d-4ce5-9654-c60a0d71fc51")))
                .andExpect(jsonPath("$.genre", is("Rock")))
                .andExpect(jsonPath("$.title", is("Trouble’s Coming")))
                .andExpect(jsonPath("$.length", is(228)))
                .andExpect(jsonPath("$.url", is("6voIJ7OWwRabSZDC77D5Hp")));
    }

    @Test
    public void whenUpdateSong_thenReturnSongJson() throws Exception {

        Song song2 = new Song("GBAHT20001931111","aa62b28e-b6d4-4086-91d4-e5fac1ed56f31111","dd7e7ced-a44d-4ce5-9654-c60a0d71fc511111","Rock1111","Trouble’s Coming1111",2281111,"6voIJ7OWwRabSZDC77D5Hp1111");

        // GET song 2 info
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://" + songServiceBaseUrl + "/songs/GBAHT20001931111")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(song2))
                );

        // PUT Song
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + songServiceBaseUrl + "/songs")))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(song2))
                );


        mockMvc.perform(put("/songs")
                .param("isrc", song2.getISRC())
                .param("mbid", song2.getMBID())
                .param("maid", song2.getMAID())
                .param("genre", song2.getGenre())
                .param("title", song2.getTitle())
                .param("length", song2.getLength().toString())
                .param("url", song2.getUrl())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("GBAHT20001931111")))
                .andExpect(jsonPath("$.mbid", is("aa62b28e-b6d4-4086-91d4-e5fac1ed56f31111")))
                .andExpect(jsonPath("$.maid", is("dd7e7ced-a44d-4ce5-9654-c60a0d71fc511111")))
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

