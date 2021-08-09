package me.scaldings.gildednetherite.init.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import me.scaldings.gildednetherite.init.materials.GildedArmorMaterial;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class GildedArmorItem extends ArmorItem {
    private final EquipmentSlot slot;
    private static final UUID[] MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public GildedArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
        this.slot = slot;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        UUID uUID = MODIFIERS[slot.getEntitySlotId()];
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uUID, "Armor modifier", (double)getProtection(), EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", (double)getToughness(), EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(uUID, "Armor knockback resistance", (double)getKnockbackResistance(), EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public int getEnchantability() {return GildedArmorMaterial.GILDED.getEnchantability();}

    @Override
    public ArmorMaterial getMaterial() {return GildedArmorMaterial.GILDED;}
    
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {return ((GildedArmorItem) stack.getItem()).getMaterial().getRepairIngredient().test(ingredient);}

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == this.slot) {return this.attributeModifiers;}
        return ImmutableMultimap.of();
    }

    public int getProtection() {return GildedArmorMaterial.GILDED.getProtectionAmount(this.slot);}

    public float getKnockbackResistance() {return GildedArmorMaterial.GILDED.getKnockbackResistance();}

    public float getToughness() {return GildedArmorMaterial.GILDED.getToughness();}
}
