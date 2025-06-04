package dev.microcontrollers.droppeditemtweaks.config;

//? if fabric {
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.shcm.shsupercm.fabric.fletchingtable.api.Entrypoint;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Entrypoint("modmenu")
@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return DroppedItemTweaksConfig::configScreen;
    }
}
//?}
