package ru.benos.cmm.client

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.world.item.Item
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.RenderUtil
import java.util.function.Consumer

class DummyItem(props: Properties): Item(props), GeoItem {
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "controller", 0) {
                logger.info("AnimationController predicate called at tick ${RenderUtil.getCurrentTick()}")
                it.setAndContinue(RawAnimation.begin().thenLoop("idle"))
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache =
        SingletonAnimatableInstanceCache(this)

    override fun getTick(`object`: Any?): Double = RenderUtil.getCurrentTick()

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