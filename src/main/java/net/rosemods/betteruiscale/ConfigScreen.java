package net.rosemods.betteruiscale;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ConfigScreen extends GameOptionsScreen {

    private final Config config;

    public ConfigScreen(Screen parent, Config config) {
        super(parent, MinecraftClient.getInstance().options, Text.translatable("betteruiscale.options.title"));
        this.config = config;
    }

    @Override
    protected void addOptions() {
        if (this.body == null) return;

        this.config.getOptions().values().forEach(simpleOption -> {
            this.body.addAll(simpleOption);
        });
    }

    @Override
    public void removed() {
        this.config.save(Main.configPath());
    }

}

