package me.drex.cleanchat.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import me.drex.cleanchat.CleanChatMod;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/GuiMessageTag;icon()Lnet/minecraft/client/GuiMessageTag$Icon;"))
    public GuiMessageTag.Icon removeIcon(GuiMessageTag original) {
        return CleanChatMod.CHAT_CONFIG.disableIcons ? null : original.icon();
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V", ordinal = 1))
    public void removeBar(PoseStack poseStack, int i, int j, int k, int l, int m) {
        if (!CleanChatMod.CHAT_CONFIG.disableBar) GuiComponent.fill(poseStack, i, j, k, l, m);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V", ordinal = 0), index = 0)
    public float moveTextLeft(float original) {
        return CleanChatMod.CHAT_CONFIG.disableBar ? 2 : original;
    }

    @ModifyConstant(method = "screenToChatX", constant = @Constant(doubleValue = 4.0))
    public double adjustChatCoordinates(double original) {
        return CleanChatMod.CHAT_CONFIG.disableBar ? 2 : original;
    }

    @ModifyConstant(method = "getTagIconLeft", constant = @Constant(intValue = 4))
    public int moveIcon(int original) {
        return CleanChatMod.CHAT_CONFIG.disableBar ? 2 : original;
    }

}
