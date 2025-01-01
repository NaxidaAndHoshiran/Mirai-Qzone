package cn.travellerr.qqzone.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString

/**
 * QzoneMsgList 数据类，表示 Qzone 消息列表
 *
 * @property auth_flag 认证标志
 * @property censor_count 审核计数
 * @property censor_flag 审核标志
 * @property censor_total 审核总数
 * @property cginame CGI 名称
 * @property code 返回码
 * @property logininfo 登录信息
 * @property mentioncount 提及计数
 * @property message 消息内容
 * @property msglist 消息列表
 * @property name 名称
 * @property num 数量
 * @property result 结果
 * @property sign 签名
 * @property smoothpolicy 平滑策略
 * @property subcode 子码
 * @property timertotal 计时总数
 * @property total 总数
 * @property usrinfo 用户信息
 */
@Serializable
data class QzoneMsgList(
    val auth_flag: Int,
    val censor_count: Int,
    val censor_flag: Int,
    val censor_total: Int,
    val cginame: Int,
    val code: Int,
    val logininfo: Logininfo,
    val mentioncount: Int? = null,
    val message: String,
    val msglist: List<Msglist>,
    val name: String,
    val num: Int,
    val result: Result,
    val sign: Int? = null,
    val smoothpolicy: Smoothpolicy,
    val subcode: Int,
    val timertotal: Int,
    val total: Int,
    val usrinfo: Usrinfo
) {
    companion object {
        private val json = kotlinx.serialization.json.Json { prettyPrint = true; ignoreUnknownKeys = true }

        /**
         * 从字符串解析 QzoneMsgList 对象
         *
         * @param qzoneMsgListStr Qzone 消息列表的 JSON 字符串
         * @return 解析后的 QzoneMsgList 对象
         */
        fun parseFromString(qzoneMsgListStr: String): QzoneMsgList {
            return json.decodeFromString(qzoneMsgListStr)
        }
    }
}

/**
 * Logininfo 数据类，表示登录信息
 *
 * @property name 用户名
 * @property uin 用户唯一标识
 */
@Serializable
data class Logininfo(
    val name: String,
    val uin: Long
)

/**
 * Msglist 数据类，表示消息列表中的单条消息
 *
 * @property certified 认证标志
 * @property cmtnum 评论数
 * @property commentlist 评论列表
 * @property conlist 内容列表
 * @property content 内容
 * @property createTime 创建时间
 * @property created_time 创建时间戳
 * @property editMask 编辑标志
 * @property fwdnum 转发数
 * @property has_more_con 是否有更多内容
 * @property isEditable 是否可编辑
 * @property issigin 是否签到
 * @property lastmodify 最后修改时间
 * @property lbs 地理位置信息
 * @property name 名称
 * @property pic 图片列表
 * @property pic_template 图片模板
 * @property pictotal 图片总数
 * @property right 权限
 * @property rt_sum 转发总数
 * @property secret 是否保密
 * @property source_appid 来源应用 ID
 * @property source_name 来源名称
 * @property source_url 来源 URL
 * @property t1_source 一级来源
 * @property t1_subtype 一级子类型
 * @property t1_termtype 一级终端类型
 * @property tid 消息 ID
 * @property ugc_right 用户生成内容权限
 * @property uin 用户唯一标识
 * @property wbid 微博 ID
 */
@Serializable
data class Msglist(
    val certified: Int,
    val cmtnum: Int,
    val commentlist: List<Commentlist>? = null,
    val conlist: List<Conlist>,
    val content: String,
    val createTime: String,
    val created_time: Int,
    val editMask: Long,
    val fwdnum: Int,
    val has_more_con: Int? = null,
    val isEditable: Int,
    val issigin: Int,
    val lastmodify: Int,
    val lbs: Lbs,
    val name: String,
    val pic: List<PicX>? = null,
    val pic_template: String,
    val pictotal: Int? = null,
    val right: Int,
    val rt_sum: Int,
    val secret: Int,
    val source_appid: String,
    val source_name: String,
    val source_url: String,
    val t1_source: Int,
    val t1_subtype: Int,
    val t1_termtype: Int,
    val tid: String,
    val ugc_right: Int,
    val uin: Long,
    val wbid: Int
)

/**
 * Result 数据类，表示结果信息
 *
 * @property code 返回码
 * @property msg 返回消息
 * @property now 当前时间戳
 */
@Serializable
data class Result(
    val code: Int,
    val msg: String,
    val now: Int
)

/**
 * Smoothpolicy 数据类，表示平滑策略
 *
 * @property disable_soso_search 禁用搜索
 * @property read_first_cache_only 仅读取第一级缓存
 * @property dont_get_reply_cmt 不获取回复评论
 * @property mixsvr_frdnum_per_time 每次获取好友数
 * @property hide_reply_cmt 隐藏回复评论
 * @property read_tdb_only 仅读取 TDB
 * @property read_cache_only 仅读取缓存
 */
@Serializable
data class Smoothpolicy(
    @SerialName("comsw.disable_soso_search") val disable_soso_search: Int,
    @SerialName("l1sw.read_first_cache_only") val read_first_cache_only: Int,
    @SerialName("l2sw.dont_get_reply_cmt") val dont_get_reply_cmt: Int,
    @SerialName("l2sw.mixsvr_frdnum_per_time") val mixsvr_frdnum_per_time: Int,
    @SerialName("l3sw.hide_reply_cmt") val hide_reply_cmt: Int,
    @SerialName("l4sw.read_tdb_only") val read_tdb_only: Int,
    @SerialName("l5sw.read_cache_only") val read_cache_only: Int
)

/**
 * Usrinfo 数据类，表示用户信息
 *
 * @property concern 关注数
 * @property createTime 创建时间
 * @property fans 粉丝数
 * @property followed 被关注数
 * @property msg 消息内容
 * @property msgnum 消息数
 * @property name 用户名
 * @property uin 用户唯一标识
 */
@Serializable
data class Usrinfo(
    val concern: Int,
    val createTime: String,
    val fans: Int,
    val followed: Int,
    val msg: String,
    val msgnum: Int,
    val name: String,
    val uin: Long
)

/**
 * Commentlist 数据类，表示评论列表中的单条评论
 *
 * @property IsPasswordLuckyMoneyCmtRight 是否有密码红包评论权限
 * @property abledel 是否可删除
 * @property content 评论内容
 * @property createTime 创建时间
 * @property createTime2 创建时间2
 * @property create_time 创建时间戳
 * @property name 用户名
 * @property pic 图片列表
 * @property pictotal 图片总数
 * @property `private` 是否私密
 * @property reply_num 回复数
 * @property rich_info 富文本信息
 * @property source_name 来源名称
 * @property source_url 来源 URL
 * @property stored_extend_info 存储的扩展信息
 * @property t2_source 二级来源
 * @property t2_subtype 二级子类型
 * @property t2_termtype 二级终端类型
 * @property tid 评论 ID
 * @property uin 用户唯一标识
 */
@Serializable
data class Commentlist(
    val IsPasswordLuckyMoneyCmtRight: String,
    val abledel: Int,
    val content: String,
    val createTime: String,
    val createTime2: String,
    val create_time: Long,
    val name: String,
    val pic: List<Pic>? = null,
    val pictotal: Int? = null,
    val `private`: Int,
    val reply_num: Int,
    val rich_info: List<RichInfo>? = null,
    val source_name: String,
    val source_url: String,
    val stored_extend_info: List<StoredExtendInfo>,
    val t2_source: Int,
    val t2_subtype: Int,
    val t2_termtype: Int,
    val tid: Int,
    val uin: Long
)

/**
 * Conlist 数据类，表示内容列表中的单条内容
 *
 * @property con 内容
 * @property type 类型
 */
@Serializable
data class Conlist(
    val con: String? = null,
    val type: Int
)

/**
 * Lbs 数据类，表示地理位置信息
 *
 * @property id ID
 * @property idname ID 名称
 * @property name 名称
 * @property pos_x X 坐标
 * @property pos_y Y 坐标
 */
@Serializable
data class Lbs(
    val id: String,
    val idname: String,
    val name: String,
    val pos_x: String,
    val pos_y: String
)

/**
 * PicX 数据类，表示图片信息
 *
 * @property absolute_position 绝对位置
 * @property b_height 大图高度
 * @property b_width 大图宽度
 * @property curlikekey 当前点赞键
 * @property height 高度
 * @property pic_id 图片 ID
 * @property pictype 图片类型
 * @property richsubtype 富文本子类型
 * @property rtype 类型
 * @property smallurl 小图 URL
 * @property unilikekey 取消点赞键
 * @property url1 URL1
 * @property url2 URL2
 * @property url3 URL3
 * @property who 谁
 * @property width 宽度
 */
@Serializable
data class PicX(
    val absolute_position: Int,
    val b_height: Int,
    val b_width: Int,
    val curlikekey: String,
    val height: Int,
    val pic_id: String,
    val pictype: Int,
    val richsubtype: Int,
    val rtype: Int,
    val smallurl: String,
    val unilikekey: String,
    val url1: String,
    val url2: String,
    val url3: String,
    val who: Int,
    val width: Int
)

/**
 * Pic 数据类，表示图片信息
 *
 * @property b_height 大图高度
 * @property b_url 大图 URL
 * @property b_width 大图宽度
 * @property hd_height 高���图高度
 * @property hd_url 高清图 URL
 * @property hd_width 高清图宽度
 * @property o_url 原图 URL
 * @property s_height 小图高度
 * @property s_url 小图 URL
 * @property s_width 小图宽度
 * @property who 谁
 */
@Serializable
data class Pic(
    val b_height: Int,
    val b_url: String,
    val b_width: Int,
    val hd_height: Int,
    val hd_url: String,
    val hd_width: Int,
    val o_url: String,
    val s_height: Int,
    val s_url: String,
    val s_width: Int,
    val who: Int
)

/**
 * RichInfo 数据类，表示富文本信息
 *
 * @property burl URL
 * @property type 类型
 * @property who 谁
 */
@Serializable
data class RichInfo(
    val burl: String,
    val type: Int,
    val who: Int
)

/**
 * StoredExtendInfo 数据类，表示存储的扩展信息
 *
 * @property k 键
 * @property v 值
 */
@Serializable
data class StoredExtendInfo(
    val k: String,
    val v: String
)