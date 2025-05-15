package ru.benos.cmm.client

import net.minecraft.world.item.Item
import software.bernie.geckolib.GeckoLib
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

object ModItem: Item(Properties()), GeoItem {
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar?) { }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
}