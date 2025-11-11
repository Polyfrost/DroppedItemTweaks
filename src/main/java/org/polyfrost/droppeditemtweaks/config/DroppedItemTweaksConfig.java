package org.polyfrost.droppeditemtweaks.config;

import org.polyfrost.droppeditemtweaks.DroppedItemTweaks;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Slider;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

public class DroppedItemTweaksConfig extends Config {
    public DroppedItemTweaksConfig() {
        super(DroppedItemTweaks.ID + ".json", DroppedItemTweaks.NAME, Category.QOL);

        loadFrom("patcher.toml");
    }


    @Switch(
        title = "Static Items",
        description = "Stop items from bobbing up and down when dropped on the ground."
    )
    public boolean staticItems;

    @Switch(
        title = "Prevent Item Spinning",
        description = "Stop items from spinning when dropped on the ground.\nThe Item will not face you."
    )
    public boolean preventItemSpinning;

    @Slider(
        title = "Dropped Stack Item Count",
        description = "The max amount of dropped items to render. This does not scale like vanilla. Set to 0 to disable.",
        max = 64
    )
    public int droppedStackItemCount = 0;

    /*
    @SerialEntry public float itemScale = 1F;
    @SerialEntry public float uhcOverlay = 0F;
    @SerialEntry public List<Item> uchItemList = new ArrayList<>(List.of(Items.APPLE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE, Items.GOLD_INGOT, Items.GOLD_NUGGET, Items.GOLD_BLOCK, Items.PLAYER_HEAD));
    */
}
