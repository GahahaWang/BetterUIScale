package net.rosemods.betteruiscale;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;

import java.nio.file.Path;

public class Main implements ClientModInitializer {
    public static final String MODID = "advanced-ui-scale";
    public static final boolean IS_DEV = FabricLoader.getInstance().isDevelopmentEnvironment();
    
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        // Unused
    }
}
