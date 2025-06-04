package ru.benos.cmm.client

import com.google.common.eventbus.Subscribe
import net.minecraft.resources.ResourceLocation
import org.apache.commons.logging.impl.Log4JLogger
import java.util.logging.Logger

// Logger //
val logger: Logger = Logger.getLogger("CMM")

// String to ResourceLocation //
val String.rl: ResourceLocation get() = ResourceLocation.parse(this)