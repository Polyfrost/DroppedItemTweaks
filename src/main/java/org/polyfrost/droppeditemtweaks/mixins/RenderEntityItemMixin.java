package org.polyfrost.droppeditemtweaks.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import org.polyfrost.droppeditemtweaks.DroppedItemTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(RenderEntityItem.class)
public class RenderEntityItemMixin {
    @Inject(method = "shouldBob", at = @At("HEAD"), cancellable = true, remap = false)
    private void dit$checkOption(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!DroppedItemTweaks.config.staticItems);
    }

    @ModifyReturnValue(method = "func_177077_a", at = @At("RETURN"))
    private int dit$changeStackType(int original) {
        int amount = DroppedItemTweaks.config.droppedStackItemCount;
        return amount != 0 ? Math.min(amount, original) : original;
    }

    @ModifyArgs(method = "func_177077_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V"))
    private void dit$preventItemSpinning(Args args) {
        if (DroppedItemTweaks.config.preventItemSpinning) {
            args.set(0, 0F);
        }
    }
}
