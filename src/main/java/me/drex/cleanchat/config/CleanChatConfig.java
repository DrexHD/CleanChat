package me.drex.cleanchat.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "cleanchat")
public class CleanChatConfig implements ConfigData {

    public boolean disableIcons = true;

    public boolean disableBar = false;

}
