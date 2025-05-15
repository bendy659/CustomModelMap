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
import ru.benos.cmm.client.ModItemModel;
import ru.benos.cmm.client.UtilsKt;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
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
        var cmd = "" + itemStack.getComponents().get(DataComponents.CUSTOM_MODEL_DATA).value();

        if(itemStack.getComponents().has(DataComponents.CUSTOM_MODEL_DATA) && UtilsKt.getModelMap().containsKey(cmd)) {
            new GeoItemRenderer(new ModItemModel(cmd))
                    .renderByItem(itemStack, displayContext, poseStack, bufferSource, packedLight, packedOverlay);
            ci.cancel();
        }
    }
}