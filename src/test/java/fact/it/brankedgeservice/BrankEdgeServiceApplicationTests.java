package fact.it.brankedgeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.brankedgeservice.model.Album;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
class BrankEdgeServiceApplicationTests {

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


    Album album1 = new Album("cd76f76b-ff15-3784-a71d-4da3078a6851","Pablo Honey", "a74b1b7f-71a5-4011-9441-d0b5e4122711", "rock", "1993-02-22", "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95");
    Album album2 = new Album("2b98e6d7-a521-332f-961e-d281ba33ba3d","Reggatta de Blanc", "9e0e2b01-41db-4008-bd8b-988977d6019a", "rock", "1979-10-02", "https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486");
    Album album3 = new Album("af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Black Holes and Revelations", "9c9f1380-2516-4fc9-a3e6-f9f61941d090", "rock", "2006-07-03", "https://i.scdn.co/image/ab67616d00001e0228933b808bfb4cbbd0385400");



    @BeforeEach
    public void initializeMockserver() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

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

}
