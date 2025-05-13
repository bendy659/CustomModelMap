package ru.benos.cmm.client

import com.mojang.authlib.minecraft.client.MinecraftClient
import net.minecraft.client.Minecraft
import net.minecraft.world.item.Item
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.RawAnimation

class CustomItem: Item(Properties()), GeoAnimatable {
    override fun registerControllers(p0: AnimatableManager.ControllerRegistrar?) {
        p0?.add(AnimationController(this, "controller", 0) {
            it.setAndContinue(RawAnimation.begin().thenLoop("basic"))
        })
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = SingletonAnimatableInstanceCache(this)

    override fun getTick(p0: Any?): Double = (Minecraft.getInstance().level?.gameTime ?: 0).toDouble()
}