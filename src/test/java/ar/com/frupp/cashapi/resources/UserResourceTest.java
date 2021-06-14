package ar.com.frupp.cashapi.resources;

import ar.com.frupp.cashapi.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserResource.class)
public class UserResourceTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Value("classpath:tests/resources/user/get_user_by_id_response.json")
    private Resource resourceFile;

    @Test
    void shouldReturnUser() throws Exception {
        UserModel expected = mapper.readValue(resourceFile.getURL(), UserModel.class);
        int userId = expected.getId();
        String path = String.format("/users/%d", userId);


        MvcResult result = mvc.perform(
                get(path).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andReturn();

        UserModel actual = mapper.readValue(result.getResponse().getContentAsString(), UserModel.class);

        assertThat(actual)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expected);
    }
}
