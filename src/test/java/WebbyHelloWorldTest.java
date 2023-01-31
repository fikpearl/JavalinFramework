
import kong.unirest.HttpResponse;
        import kong.unirest.Unirest;
import org.example.TestableWebbyHelloWorld;
import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebbyHelloWorldTest {

    @Test
    @DisplayName("GET /hello")
    public void shouldGetHelloWorld() {
        TestableWebbyHelloWorld api = new TestableWebbyHelloWorld(); //create a new instance of our API
        api.start(); //start the server
        HttpResponse<String> response = Unirest.get("http://localhost:7000/hello").asString(); //Let’s send the server a GET /hello request and get the response back as a string.
        assertEquals(200, response.getStatus());//Check that the status code is 200 meaning the request was good.
        assertEquals("Hello, world!", response.getBody());//Check that the body of the response has the correct string.
        api.stop(); //stop the server
    }
}