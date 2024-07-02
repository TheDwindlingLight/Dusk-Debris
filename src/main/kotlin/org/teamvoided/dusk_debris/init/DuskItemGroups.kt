package org.teamvoided.dusk_debris.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_debris.DuskDebris.id
import kotlin.jvm.optionals.getOrNull


object DuskItemGroups {
    val DUSK_TAB: ItemGroup = register("dusk_items",
        FabricItemGroup.builder()
            .icon { ItemStack(DuskItems.GUNPOWDER_BARREL) }
            .name(Text.translatable("itemGroup.dusk_debris.dusk_items"))
            .entries { _, entries ->
                entries.addStacks(
                    mutableSetOf(
                        ItemStack(DuskItems.NETHERSHROOM_STEM),
                        ItemStack(DuskItems.BLUE_NETHERSHROOM_BLOCK),
                        ItemStack(DuskItems.PURPLE_NETHERSHROOM_BLOCK),
                        ItemStack(DuskItems.BLUE_NETHERSHROOM),
                        ItemStack(DuskItems.PURPLE_NETHERSHROOM),

                        ItemStack(DuskItems.GUNPOWDER_BARREL),
                        ItemStack(DuskItems.STRONGHOLD_GUNPOWDER_BARREL),
                        ItemStack(DuskItems.ANCIENT_BLACK_POWDER_BARREL),
                        ItemStack(DuskItems.BLUNDERBOMB_ITEM),
                        ItemStack(DuskItems.FIREBOMB_ITEM),
                        ItemStack(DuskItems.POCKETPOISON_ITEM),
                        ItemStack(DuskItems.BLINDBOMB_ITEM),
                        ItemStack(DuskItems.SMOKEBOMB_ITEM),

                        ItemStack(DuskItems.LIGHT_BLUE_RIBBON),

                        ItemStack(DuskItems.CYPRESS_LOG),
                        ItemStack(DuskItems.CYPRESS_WOOD),
                        ItemStack(DuskItems.STRIPPED_CYPRESS_LOG),
                        ItemStack(DuskItems.STRIPPED_CYPRESS_WOOD),
                        ItemStack(DuskItems.CYPRESS_PLANKS),
                        ItemStack(DuskItems.CYPRESS_STAIRS),
                        ItemStack(DuskItems.CYPRESS_SLAB),
                        ItemStack(DuskItems.CYPRESS_DOOR),
                        ItemStack(DuskItems.CYPRESS_TRAPDOOR),
                        ItemStack(DuskItems.CYPRESS_FENCE),
                        ItemStack(DuskItems.CYPRESS_FENCE_GATE),
                        ItemStack(DuskItems.CYPRESS_BUTTON),
                        ItemStack(DuskItems.CYPRESS_PRESSURE_PLATE),
                        ItemStack(DuskItems.CYPRESS_SIGN),
                        ItemStack(DuskItems.CYPRESS_HANGING_SIGN),

                        ItemStack(DuskItems.VOLCANIC_SAND),
                        ItemStack(DuskItems.SUSPICIOUS_VOLCANIC_SAND),
                        ItemStack(DuskItems.VOLCANIC_SANDSTONE),
                        ItemStack(DuskItems.VOLCANIC_SANDSTONE_STAIRS),
                        ItemStack(DuskItems.VOLCANIC_SANDSTONE_SLAB),
                        ItemStack(DuskItems.VOLCANIC_SANDSTONE_WALL),
                        ItemStack(DuskItems.CHISELED_VOLCANIC_SANDSTONE),
                        ItemStack(DuskItems.SMOOTH_VOLCANIC_SANDSTONE),
                        ItemStack(DuskItems.SMOOTH_VOLCANIC_SANDSTONE_STAIRS),
                        ItemStack(DuskItems.SMOOTH_VOLCANIC_SANDSTONE_SLAB),
                        ItemStack(DuskItems.CUT_VOLCANIC_SANDSTONE),
                        ItemStack(DuskItems.CUT_VOLCANIC_SANDSTONE_SLAB),

                        ItemStack(DuskItems.CHARRED_LOG),
                        ItemStack(DuskItems.CHARRED_WOOD),
                        ItemStack(DuskItems.STRIPPED_CHARRED_LOG),
                        ItemStack(DuskItems.STRIPPED_CHARRED_WOOD),
                        ItemStack(DuskItems.CHARRED_PLANKS),
                        ItemStack(DuskItems.CHARRED_STAIRS),
                        ItemStack(DuskItems.CHARRED_SLAB),
                        ItemStack(DuskItems.CHARRED_DOOR),
                        ItemStack(DuskItems.CHARRED_TRAPDOOR),
                        ItemStack(DuskItems.CHARRED_FENCE),
                        ItemStack(DuskItems.CHARRED_FENCE_GATE),
                        ItemStack(DuskItems.CHARRED_BUTTON),
                        ItemStack(DuskItems.CHARRED_PRESSURE_PLATE),
                        ItemStack(DuskItems.CHARRED_SIGN),
                        ItemStack(DuskItems.CHARRED_HANGING_SIGN),
                    )
                )
            }
            .build()
    )

    fun init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register(ItemGroupEvents.ModifyEntries {
                it.addAfter(
                    Items.CUT_RED_SANDSTONE_SLAB,
                    DuskItems.SUSPICIOUS_VOLCANIC_SAND,
                    DuskItems.VOLCANIC_SANDSTONE,
                    DuskItems.VOLCANIC_SANDSTONE_STAIRS,
                    DuskItems.VOLCANIC_SANDSTONE_SLAB,
                    DuskItems.VOLCANIC_SANDSTONE_WALL,
                    DuskItems.CHISELED_VOLCANIC_SANDSTONE,
                    DuskItems.SMOOTH_VOLCANIC_SANDSTONE,
                    DuskItems.SMOOTH_VOLCANIC_SANDSTONE_STAIRS,
                    DuskItems.SMOOTH_VOLCANIC_SANDSTONE_SLAB,
                    DuskItems.CUT_VOLCANIC_SANDSTONE,
                    DuskItems.CUT_VOLCANIC_SANDSTONE_SLAB,
                )
            })
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL_BLOCKS)
            .register(ItemGroupEvents.ModifyEntries {
                it.addAfter(
                    Items.RED_SAND,
                    DuskItems.VOLCANIC_SAND
                )
            })
    }

    @Suppress("SameParameterValue")
    private fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }
}