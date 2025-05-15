package ru.benos.cmm.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SwordItem
import software.bernie.geckolib.renderer.GeoItemRenderer

class ModItemRenderer(customModelData: String): GeoItemRenderer<ModItem>(ModItemModel(customModelData)) {
    override fun renderByItem(
        stack: ItemStack,
        transformType: ItemDisplayContext,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        if(stack.item is Item && stack.item !is SwordItem && stack.item !is BlockItem)
            super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay)
    }
}