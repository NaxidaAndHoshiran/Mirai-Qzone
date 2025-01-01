package cn.travellerr.qqzone;

import cn.travellerr.qqzone.config.AccountConfig;
import cn.travellerr.qqzone.entity.Cookie;
import cn.travellerr.qqzone.utils.CookieParser;
import cn.travellerr.qqzone.utils.Log;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

public final class QQzone extends JavaPlugin {
    public static final QQzone INSTANCE = new QQzone();

    public static AccountConfig accountConfig;

    public static Cookie cookie;

    private QQzone() {
        super(new JvmPluginDescriptionBuilder("cn.travellerr.qqzone.QQzone", "0.1.0")
                .name("QQzone")
                .author("Travellerr")
                .info("喵~")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
        QQzone.INSTANCE.reloadPluginConfig(accountConfig);
        QQzone.accountConfig= AccountConfig.INSTANCE;

        RegCommands.INSTANCE.registerCommands();

        if (!accountConfig.getCookie().isEmpty()) {
            Log.info("配置文件Cookie 不为空，初始化中……");
            cookie = CookieParser.parseToCookie(accountConfig.getCookie());
        } else {
            Log.warning("配置文件Cookie 为空，停止设置");
        }
    }
}