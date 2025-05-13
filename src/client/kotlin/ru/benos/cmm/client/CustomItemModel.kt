package ru.benos.cmm.client

import net.minecraft.core.component.DataComponents
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.commands.data.DataCommands
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animation.Animation
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoRenderer

object CustomItemModel : GeoModel<CustomItem>() {
    private val stack: ItemStack? = CustomItemRenderer.STACK
    private val cmd = stack?.components?.getTyped(DataComponents.CUSTOM_MODEL_DATA)?.value ?: -1
    private val modelId = modelMap[cmd]

    override fun getModelResource(animatable: CustomItem?, renderer: GeoRenderer<CustomItem>?): ResourceLocation =
        "items/$modelId.geo.json".rl

    override fun getTextureResource(animatable: CustomItem?, renderer: GeoRenderer<CustomItem>?): ResourceLocation =
        "items/$modelId.png".rl

    override fun getAnimationResource(animatable: CustomItem?): ResourceLocation =
        "items/${modelId}.animation.json".rl
}