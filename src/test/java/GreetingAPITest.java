
import kong.unirest.HttpResponse;
        import kong.unirest.Unirest;
import org.example.GreetingAPI;
import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingAPITest {

    @Test
    @DisplayName("POST /greet")
    public void shouldGreet() {
        GreetingAPI api = new GreetingAPI(); //create the API
        api.start(); //start the server
        HttpResponse<String> response = Unirest.post("http://localhost:7000/greet") // 	Send a POST HTTP message
                .body("World") //In the body of the request, send the string World.
                .asString(); //Get the response back as a string.
        assertEquals(201, response.getStatus()); //Check that the status code is indeed 201 - Created!
        assertEquals("Hello, World!", response.getBody()); // 	And the body has the correct greeting text.
        api.stop(); //stop the server
    }
}