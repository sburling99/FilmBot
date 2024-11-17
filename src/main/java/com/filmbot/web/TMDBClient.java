package com.filmbot.web;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.filmbot.constants.Constants.*;

public class TMDBClient {

    public static int standardPageCount = 1;
    public static final String TMDB_API_KEY = System.getenv("TMDB_API_KEY");

    public HttpResponse<String> returnFilmDetailsByName(String filmName) throws IOException, InterruptedException {
        //encode for safe entry over http, need UTF-8 to standardize
        String URLSafeFilmName = URLEncoder.encode(filmName, "UTF-8");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TMDB_API_BASE_URL
                        + SEARCH_MOVIE_NAME_COMPONENT + URLSafeFilmName
                        + INCLUDE_ADULT_FILM_COMPONENT + false
                        + LANGUAGE_COMPONENT + ENGLISH_FILTER
                        + PAGINATION_COUNT_COMPONENT + standardPageCount))
                .header(ACCEPT_H, APPLICATION_JSON)
                .header(AUTH, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDBlMTY3YmRlZjg5YmNlZWVmMjlhMzJiMzhhZDA5MCIsIm5iZiI6MTczMTY5OTQ3NC45ODE3ODMyLCJzdWIiOiI2NzM3YTI0ODgwZmRhNmUzZTM3NDZiODEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.vm7VXDZGLaycTCAS5u_a7dh8eTX4tzdF46NuWfT62iU")
                .method(GET_METHOD, HttpRequest.BodyPublishers.noBody())
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
