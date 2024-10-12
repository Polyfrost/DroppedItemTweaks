package dev.microcontrollers.droppeditemtweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.microcontrollers.droppeditemtweaks.config.DroppedItemTweaksConfig;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {
    @ModifyExpressionValue(method = "render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;sin(F)F"))
    private float makeItemStatic(float value) {
        return DroppedItemTweaksConfig.CONFIG.instance().staticItems ? -1.0f : value;
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionf;)V"))
    private void scaleItems(ItemEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        ItemStack stack = entity.getItem();
        float scale = DroppedItemTweaksConfig.CONFIG.instance().itemScale;
        if (DroppedItemTweaksConfig.CONFIG.instance().uhcOverlay != 0F && DroppedItemTweaksConfig.CONFIG.instance().uchItemList.contains(stack.getItem()))
            scale = DroppedItemTweaksConfig.CONFIG.instance().uhcOverlay;
        poseStack.scale(scale, scale, scale);
    }

    @ModifyReturnValue(method = /*? if >=1.20.6 {*/ "getRenderedAmount" /*?} else {*/ /*"getRenderAmount" *//*?}*/, at = @At("RETURN"))
    private /*? if >=1.20.6 {*/ static /*?}*/ int forceStackAmount(int original, /*? if >=1.20.6 {*/ int stackSize /*?} else {*/ /*ItemStack stack *//*?}*/) {
        return DroppedItemTweaksConfig.CONFIG.instance().dropStackCount != 0 ? Math.min(DroppedItemTweaksConfig.CONFIG.instance().dropStackCount, /*? if >=1.20.6 {*/ stackSize /*?} else {*/ /*stack.getCount() *//*?}*/) : original;
    }
}
