package net.nexus.metaldetectors.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nexus.metaldetectors.MetalDetectors;
import net.nexus.metaldetectors.item.custom.MetalDetectorItem;

public class ModItems {
    public static final Item ADJUSTABLE_SHAFT = registerItem("adjustable_shaft", new Item(new FabricItemSettings()));
    public static final Item CONTROL_BOX = registerItem("control_box", new Item(new FabricItemSettings()));
    public static final Item PICKUP_COIL = registerItem("pickup_coil", new Item(new FabricItemSettings()));
    public static final Item CRUDE_METAL_DETECTOR = registerItem("crude_metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(100), 64, false));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MetalDetectors.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MetalDetectors.LOGGER.info("Registering ModItems for " + MetalDetectors.MOD_ID);
    }
}
