package cn.travellerr.qqzone.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Cookie(
    val QZ_FE_WEBP_SUPPORT: String,
    val RK: String,
    val __Q_w_s__QZN_TodoMsgCnt: String,
    val __layoutStat: String,
    val _qpsvr_localtk: String,
    val _qz_referrer: String? = null,
    val cpu_performance_v8: String,
    val p_skey: String,
    val p_uin: String,
    val pgv_pvid: String,
    val pt4_token: String,
    val ptcz: String,
    val ptui_loginuin: String,
    val qz_screen: String,
    val skey: String,
    val uin: String
) {

    companion object {
        private val json = Json { prettyPrint = true; ignoreUnknownKeys=true }

        fun parseFromString(cookieStr : String) : Cookie {
            return json.decodeFromString(cookieStr)
        }
        fun parseFromMap(cookieMap : Map<String, String>) : Cookie {
            val str = json.encodeToString(MapSerializer(String.serializer(), String.serializer())
            , cookieMap)
            return json.decodeFromString(str)
        }
    }
}