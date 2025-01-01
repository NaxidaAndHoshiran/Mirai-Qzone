package cn.travellerr.qqzone.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object AccountConfig : AutoSavePluginConfig("AccountConfig") {
    var cookie : String by value("")

}