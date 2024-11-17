package com.filmbot;

import com.filmbot.controller.DiscordController;
import com.filmbot.listener.DiscordEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.EnumSet;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

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
				.addEventListeners(new DiscordEventListener())
				.build();
		CommandListUpdateAction commandListUpdateActions = jda.updateCommands();
		commandListUpdateActions.addCommands(
				Commands.slash("find-film-details-by-name", "Type name of film to retrieve details from TMDB")
						.addOption(STRING, "film_name", "Name of film to search", true)
		);
		commandListUpdateActions.queue();
		SpringApplication.run(FilmBotApplication.class, args);
	}

}
