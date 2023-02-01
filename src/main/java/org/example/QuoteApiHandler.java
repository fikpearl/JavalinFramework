package org.example;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;


public class QuoteApiHandler {
    private static final QuoteDB database = new TestDatabase();//Get a reference to the Data Access Layer class using the QuoteDB interface referencing the TestDatabase implementation.

    /**
     * Get all quotes
     *
     * @param context The Javalin Context for the HTTP GET Request
     */
    public static void getAll(Context context) {
        context.json(database.all()); //This single line uses the convenience of Javalin’s built in JSON serialiser. It takes the list of quote objects from the database.all() call and serialises it a JSON representation and adds it to the body of the response.
    }

    /**
     * Get one quote
     *
     * @param context The Javalin Context for the HTTP GET Request
     */
    public static void getOne(Context context) {
        Integer id = context.pathParamAsClass("id", Integer.class).get();//Remember that we specified the id of the quote will be in the path of the URI as /quote/{id}. Here we use Javalin’s context class to extract the id as an integer.
        Quote quote = database.get(id);// call the database to retrieve the quote with that id.
        context.json(quote);//Javalin conveniently converts the quote to JSON and adds it to the body of the response.
    }

    /**
     * Create a new quote
     *
     * @param context The Javalin Context for the HTTP POST Request
     */
    public static void create(Context context) {
        Quote quote = context.bodyAsClass(Quote.class);//Once again, Javalin offers us a convenient way to convert the body of the request which was JSON into a Quote object.
        Quote newQuote = database.add(quote);//Call the database to add the quote.
        context.header("Location", "/quote/" + newQuote.getId());
        context.status(HttpCode.CREATED);//We set the status code to created
        context.json(newQuote);//we send the newly added Quote object back as JSON in the body of the response.
    }
}