package me.drex.cleanchat;

import me.drex.cleanchat.config.CleanChatConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanChatMod implements ClientModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("cleanchat");
	public static CleanChatConfig CHAT_CONFIG;

	@Override
	public void onInitializeClient() {
		LOGGER.info("CleanChat mod loading...");
		AutoConfig.register(CleanChatConfig.class, Toml4jConfigSerializer::new);
		CHAT_CONFIG = AutoConfig.getConfigHolder(CleanChatConfig.class).getConfig();
	}
}
