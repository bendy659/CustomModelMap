package ru.benos.cmm.client

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.world.item.Item
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class DummyItem(props: Properties): Item(props), GeoItem {
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar?) { }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache =
        GeckoLibUtil.createInstanceCache(this)

    override fun getTick(`object`: Any?): Double = currentTick

    override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider>) {
        consumer.accept(object : GeoRenderProvider {
            private var renderer: DummyRenderer? = null

            override fun getGeoItemRenderer(): BlockEntityWithoutLevelRenderer? {
                if (renderer == null)
                    renderer = DummyRenderer()

                return renderer
            }
        })
    }
}