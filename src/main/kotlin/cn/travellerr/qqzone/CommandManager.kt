package cn.travellerr.qqzone

import cn.travellerr.qqzone.config.AccountConfig
import net.mamoe.mirai.console.command.CommandContext
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.CompositeCommand

object Qzone : CompositeCommand(QQzone.INSTANCE, "qzone") {
    @SubCommand("设置cookie")
    suspend fun setCookie(commandContext: CommandContext, cookie: String) {
        AccountConfig.cookie = cookie
        commandContext.sender.sendMessage("设置成功!")
    }
}

object RegCommands {
    fun registerCommands() {
        CommandManager.registerCommand(Qzone)
    }
}