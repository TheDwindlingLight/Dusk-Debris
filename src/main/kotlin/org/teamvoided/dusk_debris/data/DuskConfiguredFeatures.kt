package org.teamvoided.dusk_debris.data

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.*
import org.teamvoided.dusk_debris.DuskDebris.id

@Suppress("MemberVisibilityCanBePrivate")
object DuskConfiguredFeatures {

    val DISK_MUD = create("disk_mud")
    val SWAMP_CYPRESS = create("swamp_cyprus")
    val TALL_SWAMP_CYPRESS = create("tall_swamp_cypress")
    val TREES_SWAMP = create("trees_swamp")

    val BLUE_NETHERSHROOM = create("blue_nethershroom")
    val HUGE_BLUE_NETHERSHROOM = create("huge_blue_nethershroom")
    val LARGE_BLUE_NETHERSHROOM_PATCH = create("large_blue_nethershroom_patch")
    val BLUE_NETHERSHROOM_PATCH = create("blue_nethershroom_patch")
    val PURPLE_NETHERSHROOM = create("purple_nethershroom")
    val HUGE_PURPLE_NETHERSHROOM = create("huge_purple_nethershroom")
    val LARGE_PURPLE_NETHERSHROOM_PATCH = create("large_purple_nethershroom_patch")
    val PURPLE_NETHERSHROOM_PATCH = create("purple_nethershroom_patch")

    fun create(id: String): RegistryKey<ConfiguredFeature<*, *>> =
        RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(id))

}