package com.moviebot;

import com.moviebot.controller.DiscordController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.EnumSet;

@SpringBootApplication
public class FilmBotApplication {
	static EnumSet<GatewayIntent> intents = EnumSet.of(
			GatewayIntent.GUILD_MEMBERS,
			GatewayIntent.GUILD_MODERATION,
			GatewayIntent.GUILD_EXPRESSIONS,
			GatewayIntent.GUILD_WEBHOOKS,
			GatewayIntent.GUILD_INVITES,
			GatewayIntent.GUILD_VOICE_STATES,
			GatewayIntent.GUILD_PRESENCES,
			GatewayIntent.GUILD_MESSAGES,
			GatewayIntent.GUILD_MESSAGE_REACTIONS,
			GatewayIntent.GUILD_MESSAGE_TYPING,
			GatewayIntent.DIRECT_MESSAGES,
			GatewayIntent.DIRECT_MESSAGE_REACTIONS,
			GatewayIntent.DIRECT_MESSAGE_TYPING,
			GatewayIntent.MESSAGE_CONTENT,
			GatewayIntent.SCHEDULED_EVENTS,
			GatewayIntent.AUTO_MODERATION_CONFIGURATION,
			GatewayIntent.AUTO_MODERATION_EXECUTION,
			GatewayIntent.GUILD_MESSAGE_POLLS,
			GatewayIntent.DIRECT_MESSAGE_POLLS
	);

	public static void main(String[] args) {
		JDA jda = JDABuilder.createLight(System.getenv("BOT_TOKEN"), intents)
				.setActivity(Activity.watching("Learning....."))
				.addEventListeners(new DiscordController())
				.build();
		SpringApplication.run(FilmBotApplication.class, args);
	}

}
