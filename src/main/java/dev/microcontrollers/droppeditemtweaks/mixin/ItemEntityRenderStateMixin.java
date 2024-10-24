package dev.microcontrollers.droppeditemtweaks.mixin;

//? if >=1.21.3 {
import dev.microcontrollers.droppeditemtweaks.ItemEntityRenderStateItem;
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemEntityRenderState.class)
public class ItemEntityRenderStateMixin implements ItemEntityRenderStateItem {
    @Unique
    private ItemEntity itemEntity;

    @Override
    public ItemEntity droppeditemtweaks$getLivingEntity() {
        return itemEntity;
    }

    @Override
    public void droppeditemtweaks$setLivingEntity(ItemEntity itemEntity1) {
        itemEntity = itemEntity1;
    }
}
//?}
