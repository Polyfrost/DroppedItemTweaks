package dev.microcontrollers.droppeditemtweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
//? if <=1.21.3
/*import com.llamalad7.mixinextras.injector.ModifyReturnValue;*/
import com.mojang.blaze3d.vertex.PoseStack;
import dev.microcontrollers.droppeditemtweaks.config.DroppedItemTweaksConfig;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
//? if >=1.21.3
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
//? if >=1.21.3
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {
    //? if >=1.21.3 {
    @Unique
    private ItemEntity entity;

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/item/ItemEntity;Lnet/minecraft/client/renderer/entity/state/ItemEntityRenderState;F)V", at = @At("TAIL"))
    public void updateRenderState(ItemEntity itemEntity, ItemEntityRenderState itemEntityRenderState, float f, CallbackInfo ci){
        entity = itemEntity;
    }
    //?}

    @ModifyExpressionValue(method = /*? if >=1.21.3 {*/ "render(Lnet/minecraft/client/renderer/entity/state/ItemEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V" /*?} else {*/ /*"render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V" *//*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;sin(F)F"))
    private float makeItemStatic(float value) {
        return DroppedItemTweaksConfig.CONFIG.instance().staticItems ? -1.0f : value;
    }

    @Inject(method = /*? if >=1.21.3 {*/ "render(Lnet/minecraft/client/renderer/entity/state/ItemEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V" /*?} else {*/ /*"render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V" *//*?}*/, at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionf;)V"))
    private void scaleItems(/*? if >=1.21.2 {*/ ItemEntityRenderState itemEntityRenderState, /*?} else {*/ /*ItemEntity entity, float entityYaw, float partialTicks,*//*?}*/ PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, CallbackInfo ci) {
        ItemStack stack = entity.getItem();
        float scale = DroppedItemTweaksConfig.CONFIG.instance().itemScale;
        if (DroppedItemTweaksConfig.CONFIG.instance().uhcOverlay != 0F && DroppedItemTweaksConfig.CONFIG.instance().uchItemList.contains(stack.getItem()))
            scale = DroppedItemTweaksConfig.CONFIG.instance().uhcOverlay;
        poseStack.scale(scale, scale, scale);
    }

    //? if <=1.21.3 {
    /*@ModifyReturnValue(method = /^? if >=1.20.6 {^/ "getRenderedAmount" /^?} else {^/ /^"getRenderAmount" ^//^?}^/, at = @At("RETURN"))
    private /^? if >=1.20.6 {^/ static /^?}^/ int forceStackAmount(int original, /^? if >=1.20.6 {^/ int stackSize /^?} else {^/ /^ItemStack stack ^//^?}^/) {
        return DroppedItemTweaksConfig.CONFIG.instance().dropStackCount != 0 ? Math.min(DroppedItemTweaksConfig.CONFIG.instance().dropStackCount, /^? if >=1.20.6 {^/ stackSize /^?} else {^/ /^stack.getCount() ^//^?}^/) : original;
    }
    *///?}
}
