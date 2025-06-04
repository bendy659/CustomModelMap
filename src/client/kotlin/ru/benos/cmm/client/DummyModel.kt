package ru.benos.cmm.client

import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.cache.GeckoLibCache
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoRenderer

class DummyModel: GeoModel<DummyItem>() {
    var model = "error" // Default

    // Resources path //
    private var modelPath = "cmm:geo/$model.geo.json".rl
    private val animationPath = "cmm:animations/$model.animation.json".rl
    private val texturePath = "cmm:textures/$model.png".rl

    override fun getModelResource(animatable: DummyItem?, renderer: GeoRenderer<DummyItem>?): ResourceLocation = modelPath

    override fun getAnimationResource(animatable: DummyItem?): ResourceLocation = animationPath

    override fun getTextureResource(animatable: DummyItem?, renderer: GeoRenderer<DummyItem>?): ResourceLocation = texturePath

    override fun getBakedModel(location: ResourceLocation?): BakedGeoModel {
        return super.getBakedModel(location)
    }
}