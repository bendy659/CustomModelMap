package ru.benos.cmm.client

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoItemRenderer
import software.bernie.geckolib.renderer.GeoRenderer

class CustomItemRenderer: GeoItemRenderer<CustomItem>(CustomItemModel) {
    companion object {
        var STACK: ItemStack? = null
        val INSTANCE = CustomItemRenderer()
    }

    override fun renderByItem(
        stack: ItemStack,
        transformType: ItemDisplayContext,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        STACK = stack
        logger.debug("I found this [${STACK?.item?.name?.string ?: "none"}] item on [${STACK?.item?.components()?.get(DataComponents.CUSTOM_MODEL_DATA) ?: "none"}]")
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay)
        STACK = null
    }
}