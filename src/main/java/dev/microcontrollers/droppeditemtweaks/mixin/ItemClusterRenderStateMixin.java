package dev.microcontrollers.droppeditemtweaks.mixin;

//? if >=1.21.4 {
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.microcontrollers.droppeditemtweaks.config.DroppedItemTweaksConfig;
import net.minecraft.client.renderer.entity.state.ItemClusterRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemClusterRenderState.class)
public class ItemClusterRenderStateMixin {
    @ModifyReturnValue(method = "getRenderedAmount", at = @At("RETURN"))
    private static int forceStackAmount(int original, int stackSize) {
        return DroppedItemTweaksConfig.CONFIG.instance().dropStackCount != 0 ? Math.min(DroppedItemTweaksConfig.CONFIG.instance().dropStackCount, stackSize) : original;
    }
}
//?}
