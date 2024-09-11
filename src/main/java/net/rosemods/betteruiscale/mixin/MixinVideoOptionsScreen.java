package net.rosemods.betteruiscale.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VideoOptionsScreen.class)
public class MixinVideoOptionsScreen extends Screen {

    @Unique
    private int prevGuiScale = 0;

    public MixinVideoOptionsScreen(Text title) {
        super(title);
    }

    // TODO: Fix, Redirect yells at me and i dont know what that annotation is
    //    @Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;onResolutionChanged()V"))
    //    private void preventGuiScaleUpdateClick(MinecraftClient instance) {
    //        // do nothing
    //    }


    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void captureGuiScale(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        this.prevGuiScale = this.getGameOptions().getGuiScale().getValue();
    }

    // FIXME: gui scale does not update when using keyboard controls, see: https://bugs.mojang.com/browse/MC-166361
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        boolean result = super.mouseReleased(mouseX, mouseY, button);
        assert this.client != null;
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && this.getGameOptions().getGuiScale().getValue() != prevGuiScale) {
            this.client.onResolutionChanged();
        }
        return result;
    }

    @Unique
    private GameOptions getGameOptions() {
        return MinecraftClient.getInstance().options;
    }

}
