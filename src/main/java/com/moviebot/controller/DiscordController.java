package com.moviebot.controller;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordController extends ListenerAdapter {
    private void onGuildMessageReceived(MessageReceivedEvent event) {
        String eventString = event.getMessage().getContentStripped();
        System.out.println(eventString);
    }
}
