package net.nexus.metaldetectors;

import net.fabricmc.api.ModInitializer;

import net.nexus.metaldetectors.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetalDetectors implements ModInitializer {
	public static final String MOD_ID = "metaldetectors";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}