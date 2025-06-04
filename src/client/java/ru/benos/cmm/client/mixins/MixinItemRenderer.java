package ru.benos.cmm.client.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.benos.cmm.client.Dummy;
import ru.benos.cmm.client.DummyItem;
import ru.benos.cmm.client.DummyModel;
import ru.benos.cmm.client.ModelMap;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    private void onRenderItem(
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
        CustomModelData custom_model_data = itemStack.getComponents().get(DataComponents.CUSTOM_MODEL_DATA);

        if( custom_model_data != null && ModelMap.INSTANCE.getMap().get(custom_model_data.value() + "") != null ) {
            // Setting model for item //
            var geoModel = new DummyModel();
            geoModel.setCustom_model_data(custom_model_data.value());

            // Create new item stack //
            var dummy = Dummy.INSTANCE.getDUMMY_ITEM();
            //var dummy = new DummyItem(new Item.Properties());
            var proxyStack = new ItemStack(dummy);

            if(dummy.getRenderProvider() == null) dummy.createGeoRenderer(null);

            // Setup renderer //
            var renderer = new GeoItemRenderer<>(geoModel);
            renderer.renderByItem(proxyStack, displayContext, poseStack, bufferSource, packedLight, packedOverlay);

            ci.cancel();
        }
    }
}