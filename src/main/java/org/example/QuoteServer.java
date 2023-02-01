package org.example;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

//Web API layer

public class QuoteServer {
    private final Javalin server;

    public QuoteServer() {
        server = Javalin.create(config -> {
            config.defaultContentType = "application/json"; //Since we are only supporting JSON, we configure our Web API server to set the content type in the HTTP response headers to default to application/json.
        });

        this.server.get("/quotes", context -> QuoteApiHandler.getAll(context));//This handles GET /quotes by asking QuoteApiHandler to do the work of parsing the request and building the response.
        this.server.get("/quote/{id}", context -> QuoteApiHandler.getOne(context));//This handles GET /quote/{id} by delegating the work to QuoteApiHandler
        this.server.post("/quotes", context -> QuoteApiHandler.create(context));//This handles POST /quotes with the assumption that the body of the HTTP request contains a JSON representation of the quote to add.
    }

    public static void main(String[] args) {
        QuoteServer server = new QuoteServer();
        server.start(5000);
    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }
}