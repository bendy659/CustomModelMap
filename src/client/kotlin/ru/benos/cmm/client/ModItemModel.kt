package ru.benos.cmm.client

import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoRenderer

class ModItemModel(value: String): GeoModel<ModItem>() {
    private val model = modelMap[value] ?: "error"

    override fun getModelResource(animatable: ModItem?, renderer: GeoRenderer<ModItem>?): ResourceLocation =
        "cmm:items/$model.geo.json".rl

    override fun getAnimationResource(animatable: ModItem?): ResourceLocation =
        "cmm:items/$model.animation.json".rl

    override fun getTextureResource(animatable: ModItem?, renderer: GeoRenderer<ModItem>?): ResourceLocation =
        "cmm:items/$model.png".rl
}