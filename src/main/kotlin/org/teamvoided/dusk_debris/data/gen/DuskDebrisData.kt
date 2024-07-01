package org.teamvoided.dusk_debris.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.dusk_debris.DuskDebris.log
import org.teamvoided.dusk_debris.data.gen.providers.EnglishTranslationProvider
import org.teamvoided.dusk_debris.data.gen.providers.ModelProvider
import org.teamvoided.dusk_debris.data.gen.tags.BiomeTagsProvider
import org.teamvoided.dusk_debris.data.gen.tags.BlockTagsProvider
import org.teamvoided.dusk_debris.data.gen.tags.EntityTypeTagsProvider
import org.teamvoided.dusk_debris.data.gen.providers.EntityLootTableProvider
import org.teamvoided.dusk_debris.data.gen.tags.ItemTagsProvider
import org.teamvoided.dusk_debris.data.gen.world.gen.ConfiguredFeatureCreator
import org.teamvoided.dusk_debris.init.worldgen.DuskConfiguredFeatures

@Suppress("unused")
class DuskDebrisData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::ItemTagsProvider)
        pack.addProvider(::EntityTypeTagsProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnglishTranslationProvider)
        pack.addProvider(::WorldgenProvider)
//        pack.addProvider(::RecipesProvider)
//        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::EntityLootTableProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
//        gen.add(RegistryKeys.BIOME, DuskBiomes::boostrap)
        gen.add(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
//        gen.add(RegistryKeys.PLACED_FEATURE, DuskPlacedFeature::bootstrapPlacedFeatures)
    }
}
