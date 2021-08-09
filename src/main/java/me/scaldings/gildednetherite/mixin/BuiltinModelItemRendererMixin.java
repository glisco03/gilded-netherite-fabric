package me.scaldings.gildednetherite.mixin;

import me.scaldings.gildednetherite.render.shield.GildedShieldItemRenderer;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {

    @Inject(method = "reload", at = @At("RETURN"))
    private void loadShieldModel(ResourceManager manager, CallbackInfo ci) {
        GildedShieldItemRenderer.loadShieldModel();
    }

}
