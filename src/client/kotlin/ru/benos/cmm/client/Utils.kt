package ru.benos.cmm.client

import com.google.gson.JsonParser
import com.mojang.logging.LogUtils
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import java.io.InputStreamReader

val logger = LogUtils.getLogger()

val String.rl: ResourceLocation get() = ResourceLocation.parse(this)

val modelMap: MutableMap<String, String> = mutableMapOf()

fun load(resourceManager: ResourceManager) {
    val id = ResourceLocation.parse("cmm:model_map.json") ?: return
    val stream = resourceManager.getResource(id).orElse(null)?.open() ?: return
    val json = JsonParser.parseReader(InputStreamReader(stream)).asJsonObject

    modelMap.clear()

    for ((key, value) in json.entrySet()) {
        modelMap[key] = value.asString ?: continue
    }

    logger.info("Found CMM models: $modelMap")
}