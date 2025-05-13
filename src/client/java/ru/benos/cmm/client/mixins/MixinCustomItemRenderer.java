package ru.benos.cmm.client.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.benos.cmm.client.CustomItemRenderer;

@Mixin(ItemRenderer.class)
public class MixinCustomItemRenderer {
    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    public void onRenderItem(
            ItemStack itemStack,
            ItemDisplayContext displayContext,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay,
            BakedModel model,
            boolean renderOpenBundle,
            CallbackInfo ci
    ) {
        if(itemStack.has(DataComponents.CUSTOM_MODEL_DATA)) {
            //var tag = itemStack.get(DataComponents.CUSTOM_MODEL_DATA);
            CustomItemRenderer.Companion.getINSTANCE().renderByItem(itemStack, displayContext, poseStack, bufferSource, packedLight, packedOverlay);
            ci.cancel();
        }
    }
}
