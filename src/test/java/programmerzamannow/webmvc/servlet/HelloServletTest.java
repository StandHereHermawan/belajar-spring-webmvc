package programmerzamannow.webmvc.servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloServletTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void servlet() {
        String domain = "http://localhost:";
        String path = "/servlet/hello";

        String response = restTemplate.getForObject(domain + port + path, String.class);
        Assertions.assertEquals("Hello from Servlet",response.trim());
    }
}