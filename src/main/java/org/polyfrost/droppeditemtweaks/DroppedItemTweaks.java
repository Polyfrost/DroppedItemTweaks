package org.polyfrost.droppeditemtweaks;

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//#endif

import org.polyfrost.droppeditemtweaks.config.DroppedItemTweaksConfig;

//#if FORGE-LIKE
@Mod(modid = DroppedItemTweaks.ID, name = DroppedItemTweaks.NAME, version = DroppedItemTweaks.VERSION)
//#endif
public class DroppedItemTweaks
    //#if FABRIC
    //$$ implements ModInitializer
    //#endif
{
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static DroppedItemTweaksConfig config;

    //#if FABRIC
    //$$ @Override
    //#elseif FORGE
    @Mod.EventHandler
    //#endif
    public void onInitialize(
        //#if FORGE
        FMLInitializationEvent event
        //#endif
    ) {
        config = new DroppedItemTweaksConfig();
    }
}
