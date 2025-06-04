package ru.benos.cmm.client

import com.google.gson.JsonParser
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import java.io.InputStreamReader

object ModelMap {
    var map: MutableMap<String, String> = mutableMapOf()
        private set

    fun load(resourceManager: ResourceManager) {
        val id = ResourceLocation.parse("cmm:model_map.json") ?: return
        val stream = resourceManager.getResource(id).orElse(null)?.open() ?: return
        val json = JsonParser.parseReader(InputStreamReader(stream)).asJsonObject

        map.clear()

        for ((key, value) in json.entrySet())
            map[key] = value.asString ?: continue

        logger.info("Found CMM models: $map")
    }
}