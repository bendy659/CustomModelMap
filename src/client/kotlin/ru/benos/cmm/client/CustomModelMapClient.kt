package ru.benos.cmm.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.ResourceManager
import ru.benos.cmm.client.ModelMap.load

class CustomModelMapClient : ClientModInitializer {
    override fun onInitializeClient() {
        logger.info("Load [CustomModelData] mod.")

        // Register [DummyGeoItem] //
        Dummy.DUMMY_ITEM

        // Load resources //
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(
            object: SimpleSynchronousResourceReloadListener {
                override fun getFabricId(): ResourceLocation = "cmm:resources".rl
                override fun onResourceManagerReload(resourceManager: ResourceManager) = load(resourceManager)
            }
        )

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            currentTick = (client.player?.tickCount ?: 0) + 0.0
        }
    }
}