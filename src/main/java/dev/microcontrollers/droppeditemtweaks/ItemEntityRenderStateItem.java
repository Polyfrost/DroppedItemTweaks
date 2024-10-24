package dev.microcontrollers.droppeditemtweaks;

//? if >=1.21.3 {
import net.minecraft.world.entity.item.ItemEntity;

public interface ItemEntityRenderStateItem {
    ItemEntity droppeditemtweaks$getLivingEntity();

    void droppeditemtweaks$setLivingEntity(ItemEntity itemEntity);
}

//?}
