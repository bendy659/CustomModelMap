package ru.benos.cmm.client

import com.google.gson.JsonParser
import com.mojang.logging.LogUtils
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import java.io.InputStreamReader

val String.rl: ResourceLocation get() = ResourceLocation.parse(this)
val String.literal: MutableComponent get() = Component.literal(this)

operator fun MutableComponent.plus(c: Component): MutableComponent = this.copy().append(c)

val logger = LogUtils.getLogger()

val modelMap = mutableMapOf<String, ResourceLocation>()

fun load(resourceManager: ResourceManager) {
    val id = ResourceLocation.parse("cmm:model_map.json") ?: return
    val stream = resourceManager.getResource(id).orElse(null)?.open() ?: return
    val json = JsonParser.parseReader(InputStreamReader(stream)).asJsonObject

    modelMap.clear()
    for ((key, value) in json.entrySet()) {
        modelMap[key] = safeParseResourceLocation(value.asString) ?: continue
    }
}

private fun safeParseResourceLocation(resource: String): ResourceLocation? = try {
    resource.rl
} catch (e: IllegalAccessException) {
    logger.error("Error generate ResourceLocation on: [$resource]")
    null
}

