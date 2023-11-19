package me.drex.cleanchat.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.drex.cleanchat.CleanChatMod;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/GuiMessageTag;icon()Lnet/minecraft/client/GuiMessageTag$Icon;"))
    public GuiMessageTag.Icon removeIcon(GuiMessageTag.Icon original) {
        return CleanChatMod.CHAT_CONFIG.disableIcons ? null : original;
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 1))
    public void removeBar(GuiGraphics instance, int x1, int y1, int x2, int y2, int color, Operation<Void> original) {
        if (!CleanChatMod.CHAT_CONFIG.disableBar) original.call(instance, x1, y1, x2, y2, color);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V", ordinal = 0), index = 0)
    public float moveTextLeft(float original) {
        return CleanChatMod.CHAT_CONFIG.disableBar ? 2 : original;
    }

    @ModifyExpressionValue(method = "screenToChatX", at = @At(value = "CONSTANT", args = "doubleValue=4.0"))
    public double adjustChatCoordinates(double original) {
        return CleanChatMod.CHAT_CONFIG.disableBar ? 2 : original;
    }

    @ModifyExpressionValue(method = "getTagIconLeft", at = @At(value = "CONSTANT", args = "intValue=4"))
    public int moveIcon(int original) {
        return CleanChatMod.CHAT_CONFIG.disableBar ? 2 : original;
    }

}
