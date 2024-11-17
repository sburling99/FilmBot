package com.filmbot.controller;


import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiscordController {

    public String findFilmDetailsByName(String content) throws IOException, InterruptedException {
        String URLSafeContent = URLEncoder.encode(content, "UTF-8");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + URLSafeContent + "&include_adult=false&language=en-US&page=1"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDBlMTY3YmRlZjg5YmNlZWVmMjlhMzJiMzhhZDA5MCIsIm5iZiI6MTczMTY5OTQ3NC45ODE3ODMyLCJzdWIiOiI2NzM3YTI0ODgwZmRhNmUzZTM3NDZiODEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.vm7VXDZGLaycTCAS5u_a7dh8eTX4tzdF46NuWfT62iU")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return "Incoming logic";
    }
}
