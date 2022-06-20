package net.lol6216.classicui.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class hudmixin {
	@Inject(at=@At("HEAD"), method="renderExperienceBar",cancellable=true)
	public void renderExperienceBar(MatrixStack m, int x, CallbackInfo callback) {
		MinecraftClient client = MinecraftClient.getInstance();
		TextRenderer renderer = client.textRenderer;
		Object text =  Text.translatable("deathScreen.score").append(": ").append(Text.literal(Integer.toString(client.player.experienceLevel)));
		renderer.drawWithShadow(m,(Text)text,2,2,16777215);
		callback.cancel();
	}
	/*
	@Inject(at=@At("HEAD"), method="renderStatusBars")
	public void statusbar1(MatrixStack m, CallbackInfo callback) {
		m.push();
		m.translate(0,6.8,0);
	}

	@Inject(at=@At("Return"), method="renderStatusBars")
	public void statusbarreturn(MatrixStack m, CallbackInfo callback) {
		m.pop();
	}
	*/
	//Give it a more Legacy Console vibe
	@Inject(at=@At("head"), method="renderHotbar")
	private void hotbar1(float t, MatrixStack m, CallbackInfo callback) {
		m.push();
		m.translate(0,-6.8,0);
	}
	@Inject(at=@At("return"), method="renderHotbar")
	private void hotbar2(float t, MatrixStack m, CallbackInfo callback) {
		m.pop();
	}
}
