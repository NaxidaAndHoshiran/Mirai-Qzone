package cn.travellerr.qqzone.url;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum QzoneUrl {
    QZONE("https://user.qzone.qq.com/", new ArrayList<>()),

    /**
     * 获取QQ空间消息列表<br>
     * 参数：
     * <pre>
     *     <code>uin</code> QQ号
     *     <code>pos</code> 消息起始位置
     *     <code>num</code> 消息数量
     *     <code>need_private_comment</code> 是否需要私密评论
     *     <code>g_tk</code> g_tk
     * </pre>
     */
    QZONE_GET_MESSAGE_LIST("https://user.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6", new ArrayList<>(List.of("uin", "pos", "num", "need_private_comment", "g_tk"))),

    /**
     * 发送消息<br>
     * 参数：
     * <pre>
     *      <code>g_tk</code> g_tk
     * </pre>
     */
    QZONE_SEND_MESSAGE("https://user.qzone.qq.com/proxy/domain/taotao.qzone.qq.com/cgi-bin/emotion_cgi_publish_v6", new ArrayList<>(List.of("g_tk"))),

    /**
     * 删除消息<br>
     * 参数：
     * <pre>
     *     <code>g_tk</code> g_tk
     * </pre>
     */
    QZONE_DELETE_MESSAGE("https://user.qzone.qq.com/proxy/domain/taotao.qzone.qq.com/cgi-bin/emotion_cgi_delete_v6", new ArrayList<>(List.of("g_tk"))),;

    private final String url;
    private final List<String> params;
}
