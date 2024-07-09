package org.teamvoided.dusk_debris.data

import net.minecraft.entity.decoration.painting.PaintingVariant
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import org.teamvoided.dusk_debris.DuskDebris.id

object DuskPaintingVariantTags {
    val TEST = create("test")
    val DROPS_SELF = create("drops_self")

    fun create(id: String): TagKey<PaintingVariant> = TagKey.of(RegistryKeys.PAINTING_VARIANT, id(id))
}