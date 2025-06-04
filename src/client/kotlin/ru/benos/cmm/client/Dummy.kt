package ru.benos.cmm.client

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item

object Dummy {
    val DUMMY_ITEM: DummyItem = Registry.register(
        BuiltInRegistries.ITEM,
        "cmm:dummy_item".rl,
        DummyItem(Item.Properties().useItemDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, "dummy_item".rl)))
    )
}