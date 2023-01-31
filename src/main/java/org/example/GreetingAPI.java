package org.example;

import io.javalin.Javalin;

public class GreetingAPI {

    private final Javalin server;

    public GreetingAPI() {
        this.server = Javalin.create();
        this.server.post("/greet", context -> { //We tell Javalin to handle a POST message for /greet.
            String name = context.body();//The name to greet is a string in the body of the POST message.
            context.result("Hello, " + name + "!");//Send the response with the body being a greeting as a string.
            context.status(201);//This time we explicitly return a status code of 201. This status code means "Created" and is usually used with a successful POST response. Semantically, it means that the server successfully created the greeting
        });
    }

    public Javalin start() {
        return this.server.start(7000);
    }

    public Javalin stop() {
        return this.server.stop();
    }

    public static void main(String[] args) {
        GreetingAPI api = new GreetingAPI();
        api.start();
    }
}