package me.scaldings.gildednetherite;

import me.scaldings.gildednetherite.init.Items;
import me.scaldings.gildednetherite.render.ModelProvider;
import me.scaldings.gildednetherite.render.elytra.GildedElytraFeatureRenderer;
import me.scaldings.gildednetherite.render.shield.GildedShieldItemRenderer;
import me.scaldings.gildednetherite.render.shield.ItemTextures;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.item.ItemConvertible;

public class GildedNetheriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTextures.register();
        ModelProvider.registerModels();
        registerRenderer(Items.GILDED_SHIELD, new GildedShieldItemRenderer());

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer.getModel() instanceof PlayerEntityModel || entityRenderer.getModel() instanceof BipedEntityModel || entityRenderer.getModel() instanceof ArmorStandEntityModel) {
                registrationHelper.register(new GildedElytraFeatureRenderer(entityRenderer, context.getModelLoader()));
            }
        });
    }

    private static void registerRenderer(ItemConvertible item, BuiltinItemRendererRegistry.DynamicItemRenderer itemRenderer) {
        BuiltinItemRendererRegistry.INSTANCE.register(item, itemRenderer);
    }
}
