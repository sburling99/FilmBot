package com.filmbot.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filmbot.service.FilmBotService;
import com.filmbot.web.TMDBClient;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.filmbot.constants.Constants.*;

public class DiscordController {
    public static TMDBClient tmdbClient = new TMDBClient();
    public static FilmBotService filmBotService = new FilmBotService();

    public void findFilmDetailsByName(String movieName, SlashCommandInteractionEvent event) throws IOException, InterruptedException {
        //Retrieve raw response from TMDB
        HttpResponse<String> response = tmdbClient.returnFilmDetailsByName(movieName);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode arrayResponse = mapper.readTree(response.body());

        int results = arrayResponse.get("total_results").asInt();

        if (results == 0) {
            //event reply here
        } else if (results == 1) {
            //single movie detail by ID
        } else {
            multipleMoviesReturnedView(filmBotService.topThreeFilms(arrayResponse, results), event, movieName);
        }
    }

    public void multipleMoviesReturnedView(JsonNode response, SlashCommandInteractionEvent event, String movieName) throws IOException, InterruptedException {
        String userId = event.getUser().getId();

        List<JsonNode> films = new ArrayList<>();
        for (JsonNode film : response.get("popularity")) {films.add(film);}
        //TextChannel channel = event.getGuild().getTextChannelsByName("general", true).get(0);
        //File file new File();
        //channel.sendMessage()

        int results = films.size();

        if (results == 2) {
            JsonNode movieOne = films.get(0);
            JsonNode movieTwo = films.get(1);
            event.reply("Multiple films returned under name " + ESCAPE_PARAN + movieName + ESCAPE_PARAN + ". Choose which one you want details for:")
                    .addContent("\nYear " + movieOne.get(""))
                    .addActionRow(
                            Button.secondary(userId + ":firstfilm", "1"),
                            Button.secondary(userId + ":secondfilm", "2")
                    );
            //add two buttons
        } else {
            //add 3 buttons
            event.reply("Multiple films returned under name " + ESCAPE_PARAN + movieName + ESCAPE_PARAN + ". Choose which one you want details for: ")
                    .addContent("\nTesting")
                    .addContent("\nTesting")
                    .addContent("\nTesting")
                    .addActionRow(
                            Button.secondary(userId + ":firstfilm", "1"),
                            Button.secondary(userId + ":secondfilm", "2"),
                            Button.secondary(userId + ":thirdfilm", "3")
                    )
                    .queue();
        }
    }
    public String movieInfoStringBuilder(JsonNode films) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Film Name: ").append(films.get("")).append("\n");
    }

    public void findFilmDetailsById(String id) throws IOException, InterruptedException {}

}
