package programmerzamannow.webmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.webmvc.model.HelloRequest;
import programmerzamannow.webmvc.model.HelloResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BodyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void bodyHello() throws Exception {
        HelloRequest request = new HelloRequest();
        request.setName("Arief");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/body/hello")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk(),
                header().string(
                        HttpHeaders.CONTENT_TYPE,
                        Matchers.containsString(MediaType.APPLICATION_JSON_VALUE)
                )
        ).andExpect(
                result -> {
                    String responseJson = result.getResponse().getContentAsString();

                    HelloResponse response = objectMapper.readValue(responseJson, HelloResponse.class);
                    assertEquals("Hello Arief", response.getHello());
                }
        );
    }
}