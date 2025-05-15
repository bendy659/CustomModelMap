package ru.benos.cmm.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.ResourceManager

class CustomModelMapClient : ClientModInitializer {
    override fun onInitializeClient() {
        logger.info("Load [CustomModelData] mod.")

        // Load resources
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(ReloadMyResources)
    }
}

object ReloadMyResources: SimpleSynchronousResourceReloadListener {
    override fun getFabricId(): ResourceLocation = "cmm:resources".rl

    override fun onResourceManagerReload(resourceManager: ResourceManager) = load(resourceManager)

}