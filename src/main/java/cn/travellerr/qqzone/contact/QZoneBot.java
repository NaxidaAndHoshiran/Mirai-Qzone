
package cn.travellerr.qqzone.contact;

import cn.travellerr.qqzone.QQzone;
import cn.travellerr.qqzone.config.AccountConfig;
import cn.travellerr.qqzone.entity.QzoneMsgList;
import cn.travellerr.qqzone.tokenAlgorithm.TokenGenerator;
import cn.travellerr.qqzone.url.QzoneUrl;
import cn.travellerr.qqzone.utils.Log;
import lombok.Getter;
import lombok.Setter;
import net.mamoe.mirai.Bot;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class QZoneBot {
    private final long uin;
    private final Bot bot;
    private final long g_tk;
    private final String originCookie;

    public QZoneBot(Bot bot) {
        this.bot = bot;
        this.uin = bot.getId();
        this.g_tk = TokenGenerator.getTk(/*CookieParser.parseToCookie(cookie).getP_skey()*/QQzone.cookie.getP_skey());
        this.originCookie = AccountConfig.INSTANCE.getCookie();
    }


    public boolean sendMessage(String content) {
        try {
            URL url = this.buildQzoneUrl(QzoneUrl.QZONE_SEND_MESSAGE, this.g_tk);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cookie", this.originCookie);

            String body = "syn_tweet_verson=1&paramstr=1&pic_template=&richtype=&richval=&special_url=&subrichtype=&who=1&con="+
                    URLEncoder.encode(content, StandardCharsets.UTF_8)
                    +"&feedversion=1&ver=1&ugc_right=1&to_sign=0&hostuin="+this.uin+"&code_version=1&format=fs&qzreferrer=https%3A%2F%2Fuser.qzone.qq.com%2F"
                    + this.uin;

            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            String response = new String(connection.getInputStream().readAllBytes());

            return connection.getResponseCode() == 200 && response.contains("tid");
        } catch (Exception e) {
            Log.error("发送消息失败", e);
//            e.fillInStackTrace();
            return false;
        }
    }


    /**
     * 获取QQ空间指定范围内消息列表
     * @param pos 消息起始位置
     * @param num 消息数量
     * @return {@link QzoneMsgList}或<strong>null</strong>
     * @see QzoneMsgList
     */
    public QzoneMsgList getMessageList(int pos, int num, boolean need_private_comment) {

        try {
            URL url = this.buildQzoneUrl(QzoneUrl.QZONE_GET_MESSAGE_LIST, this.uin, pos, num, need_private_comment ? 1 : 0, this.g_tk);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cookie", this.originCookie);
            String response = new String(connection.getInputStream().readAllBytes());
            response = response.substring(response.indexOf("(") + 1, response.lastIndexOf(")"));
            return QzoneMsgList.Companion.parseFromString(response);
        } catch (Exception e) {
            Log.error("构建URL失败", e);
            //e.fillInStackTrace();
            return null;
        }
    }

    /**
     * 构建QQ空间URL
     * @param qzoneUrl QQ空间URL {@link QzoneUrl}
     * @param params 参数
     * @return URL
     * @throws Exception URL构建异常或参数数量不匹配
     * @see QzoneUrl
     */
    public URL buildQzoneUrl(QzoneUrl qzoneUrl, Object... params) throws Exception {
        if (qzoneUrl.getParams().size() != params.length) {
            throw new InvalidParamsException("参数数量不匹配");
        }
        StringBuilder url = new StringBuilder(qzoneUrl.getUrl());


        if (qzoneUrl.getParams().isEmpty()) {
            return new URL(url.toString());
        }

        url.append("?");
        AtomicInteger i = new AtomicInteger();
        for (String param: qzoneUrl.getParams()) {
            url.append(param).append("=").append(params[i.getAndIncrement()]).append("&");
        }


        return new URL(url.deleteCharAt(url.length() - 1).toString());
    }
}

