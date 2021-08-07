package dev.oink.pip_mc

import net.fabricmc.api.ModInitializer

@Suppress("UNUSED")
object ModName: ModInitializer {
    public const val MOD_ID = "pip_mc"
    override fun onInitialize() {
        println("Init pip_mc")
    }
}
