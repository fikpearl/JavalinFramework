package org.example;

import io.javalin.Javalin;

public class TestableWebbyHelloWorld {
    private final Javalin server; //We create a private variable
                                    // that will reference the running server.

    public TestableWebbyHelloWorld() { //The constructor instantiates the
                                            // Javalin server.
        this.server = Javalin.create();
        this.server.get("hello", context -> context.result("Hello, world!"));  //We create a handler for a GET request at /hello.
    }

    public Javalin start() {
        return this.server.start(7000);  //This simply delegates to the Javalin server to start it.
    }

    public Javalin stop() {
        return this.server.stop(); //also delegates to the Javalin server, but this time to stop it.
    }

    public static void main(String[] args) {
        TestableWebbyHelloWorld api = new TestableWebbyHelloWorld();  //main() method now instantiates a
        api.start();                                                      // HelloWorld object and starts the server
    }
}