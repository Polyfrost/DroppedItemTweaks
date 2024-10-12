package dev.microcontrollers.droppeditemtweaks;

import dev.microcontrollers.droppeditemtweaks.config.DroppedItemTweaksConfig;
//? if fabric
import net.fabricmc.api.ModInitializer;
//? if neoforge {
/*import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
*///?}

//? if neoforge
/*@Mod(value = "droppeditemtweaks", dist = Dist.CLIENT)*/
public class DroppedItemTweaks /*? if fabric {*/ implements ModInitializer /*?}*/ {
	//? if fabric {
	@Override
	public void onInitialize() {
		DroppedItemTweaksConfig.CONFIG.load();
	}
	//?}

	//? if neoforge {
    /*public DroppedItemTweaks() {
        DroppedItemTweaksConfig.CONFIG.load();
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, parent) -> DroppedItemTweaksConfig.configScreen(parent));
    }
	*///?}
}
