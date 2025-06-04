package ru.benos.cmm.client

import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoRenderer
import kotlin.properties.Delegates

class DummyModel: GeoModel<DummyItem>() {
    var custom_model_data: Int? = null
    private val model = ModelMap.map[custom_model_data.toString()] ?: "error"

    // Resources path //
    private var modelPath = "cmm:geo/$model.geo.json".rl
    private val animationPath = "cmm:animations/$model.animation.json".rl
    private val texturePath = "cmm:textures/item/$model.png".rl

    override fun getModelResource(animatable: DummyItem?, renderer: GeoRenderer<DummyItem>?): ResourceLocation = modelPath

    override fun getAnimationResource(animatable: DummyItem?): ResourceLocation = animationPath

    override fun getTextureResource(animatable: DummyItem?, renderer: GeoRenderer<DummyItem>?): ResourceLocation = texturePath
}